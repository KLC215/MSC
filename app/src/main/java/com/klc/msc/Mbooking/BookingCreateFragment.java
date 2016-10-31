package com.klc.msc.Mbooking;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.klc.msc.db.MStatus;
import com.klc.msc.Mclass.ClassViewFragment;
import com.klc.msc.R;
import com.klc.msc.db.MClass;
import com.klc.msc.db.contract.MSC_Contract;
import com.klc.msc.utils.AlertDialogUtils;
import com.klc.msc.utils.DatabaseUtils;
import com.klc.msc.utils.DateTimeUtils;
import com.klc.msc.utils.RegexUtils;

import java.util.ArrayList;
import java.util.List;


public class BookingCreateFragment extends Fragment implements View.OnClickListener
{
    private String[] splitFirstName, splitLastName, splitMobileNo, splitEmail, splitSelectedClass;
    private String selectedClass, firstName, lastName, mobile, email, rawContactId;
    private int price = 0;
    private long rawContactNewId;

    private SQLiteDatabase  db;
    private ContentValues   values;
    private Cursor          cursor;
    private ContentResolver resolver;

    private List<MClass>         classList; // get from ClassViewFragment
    private List<String>         classes;   // for spinner
    private ArrayAdapter<String> adapter;

    private Spinner spClass;

    private com.beardedhen.androidbootstrap.BootstrapEditText
            etFirstName, etLastName, etMobileNo, etEmail;
    private com.beardedhen.androidbootstrap.BootstrapButton
            btnCreateBooking, btnBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View bookingCreateView = inflater.inflate(R.layout.fragment_booking_create, container, false);
        initView(bookingCreateView);

        return bookingCreateView;
    }

    private void initView(View view)
    {
        classes = new ArrayList<>();

        classList = ClassViewFragment.getClassList();
        etFirstName = (com.beardedhen.androidbootstrap.BootstrapEditText) view.findViewById(R.id.etFirstName);
        etLastName = (com.beardedhen.androidbootstrap.BootstrapEditText) view.findViewById(R.id.etLastName);
        etMobileNo = (com.beardedhen.androidbootstrap.BootstrapEditText) view.findViewById(R.id.etMobileNo);
        etEmail = (com.beardedhen.androidbootstrap.BootstrapEditText) view.findViewById(R.id.etEmail);
        btnCreateBooking = (com.beardedhen.androidbootstrap.BootstrapButton) view.findViewById(R.id.btnCreateBooking);
        btnBack = (com.beardedhen.androidbootstrap.BootstrapButton) view.findViewById(R.id.btnBack);
        spClass = (Spinner) view.findViewById(R.id.spClass);

        try {
            classes.add("");
            for (MClass mClass : classList) {
                classes.add(mClass.getId() + ": " + mClass.getName());
            }
            adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_class, classes);
            adapter.setDropDownViewResource(R.layout.spinner_class);
            spClass.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedClass = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        btnCreateBooking.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btnCreateBooking:
                try {

                    // Open database for writing data
                    db = DatabaseUtils.openForWrite(getActivity());

                    if (formIsNotEmpty()) {

                        // Get information form EditText
                        firstName = etFirstName.getText().toString();
                        lastName = etLastName.getText().toString();
                        mobile = etMobileNo.getText().toString();
                        email = etEmail.getText().toString();

                        // Split data for multi students
                        splitData();

                        // Check if single or not
                        if (!firstName.contains(",") && !lastName.contains(",") &&
                                !mobile.contains(",") && !email.contains(",")) {

                            if (checkFormForSingleStudent()) {
                                createStudent(true);
                            }

                        } else {

                            if (checkFormForMultiStudent()) {
                                createStudent(false);
                            }

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    AlertDialogUtils.errorDialog(getActivity(), e.getMessage());
                } finally {
                    DatabaseUtils.close();
                }
                break;
            case R.id.btnBack:
                getFragmentManager().popBackStack();
                break;
        }
    }

    private void createStudent(boolean single) throws Exception
    {
        values = new ContentValues();

        // Get price from selected class
        for (MClass mClass : classList) {
            if (mClass.getId() == Integer.parseInt(splitSelectedClass[0])) {
                price = mClass.getPrice();
            }
        }

        if (single) {
            try {
                rawContactId = getRawContactId(mobile);
                checkRawContactId(true, rawContactId, 0);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } else {
            try {
                for (int i = 0; i < splitFirstName.length; i++) {
                    rawContactId = getRawContactId(splitMobileNo[i]);
                    checkRawContactId(false, rawContactId, i);
                }
            } catch (Exception e) {
                e.printStackTrace();
                AlertDialogUtils.errorDialog(getActivity(), e.getMessage());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        values.clear();
    }

    private void createPayment(String rawContactId) throws Exception
    {
        //values = new ContentValues();

        if (!checkBookingExist(rawContactId)) {
            if (insertPayment() != -1) {
                values.clear();
                Log.d("BookingCreateFragment", "createPayment: Success!");

                try {
                    createBooking(rawContactId);
                } catch (Exception e) {
                    e.printStackTrace();
                    AlertDialogUtils.errorDialog(getActivity(), e.getMessage());
                }

            }
        }


    }

    private void createBooking(String rawContactId) throws Exception
    {
        try {
            checkAndChangeClassStatusToFull();

            //values = new ContentValues();

            try {
                if (insertBooking(rawContactId) != -1) {
                    values.clear();
                    Log.d("BookingCreateFragment", "createBooking: Success !");
                    AlertDialogUtils.successDialog(getActivity(), "Booking has been created !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getPaymentId()
    {
        int paymentId = 0;

        Cursor cursor = db.query(
                MSC_Contract.MSCEntry.TABLE_PAYMENT,
                new String[]{MSC_Contract.MSCEntry.COL_PAYMENT_ID},
                null, null, null, null,
                MSC_Contract.MSCEntry.COL_PAYMENT_ID + " DESC LIMIT 1"
        );
        if (cursor.moveToFirst()) {
            do {
                paymentId = cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_PAYMENT_ID));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return paymentId;
    }

    private String getRawContactId(String mobile)
    {
        String rawContactId = "";

        resolver = getActivity().getContentResolver();
        cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.NUMBER + " = " + mobile, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            try {
                rawContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID));
                Log.d("BookingCreateFragment", "getRawContactId: " + rawContactId);
            } catch (Exception e) {
                Log.d("BookingCreateFragment", "getRawContactId: " + e.getMessage());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        return rawContactId;
    }

    private boolean checkClassStatus()
    {
        boolean result = false;
        try {
            List<Integer> classIds = new ArrayList<>();

            cursor = db.query(
                    MSC_Contract.MSCEntry.TABLE_CLASS,
                    new String[]{MSC_Contract.MSCEntry.COL_CLASS_ID},
                    MSC_Contract.MSCEntry.COL_STATUS_ID + " = ?",
                    new String[]{String.valueOf(MStatus.FULL)},
                    null, null, null
            );

            if (cursor.moveToFirst()) {
                do {
                    classIds.add(cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_CLASS_ID)));
                } while (cursor.moveToNext());
            }
            cursor.close();

            result = classIds.contains(Integer.parseInt(splitSelectedClass[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private void checkAndChangeClassStatusToFull()
    {
        try {
            int studentCount;
            int maxStudentNo = 0;

            cursor = db.query(
                    MSC_Contract.MSCEntry.TABLE_BOOKING,
                    null,
                    MSC_Contract.MSCEntry.COL_CLASS_ID + " = ?",
                    new String[]{splitSelectedClass[0]},
                    null, null, null
            );
            studentCount = cursor.getCount();

            cursor.close();


            cursor = db.query(
                    MSC_Contract.MSCEntry.TABLE_CLASS,
                    new String[]{MSC_Contract.MSCEntry.COL_MAX_STUDENT_NO},
                    MSC_Contract.MSCEntry.COL_CLASS_ID + " = ?",
                    new String[]{splitSelectedClass[0]},
                    null, null, null
            );

            if (cursor.moveToFirst()) {
                do {
                    maxStudentNo = cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_MAX_STUDENT_NO));
                } while (cursor.moveToNext());
            }

            cursor.close();

            if (studentCount > maxStudentNo) {
                values = new ContentValues();

                values.put(MSC_Contract.MSCEntry.COL_STATUS_ID, MStatus.FULL);
                db.update(MSC_Contract.MSCEntry.TABLE_CLASS,
                          values,
                          MSC_Contract.MSCEntry.COL_CLASS_ID + " = ?",
                          new String[]{splitSelectedClass[0]}
                );

                values.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkRawContactId(boolean single, String rawContactId, int count) throws Exception
    {
        if (rawContactId.equals("")) {
            if (single) {
                insertContact(firstName, lastName, mobile, email);
                createPayment(String.valueOf(rawContactNewId));
            } else {
                insertContact(splitFirstName[count], splitLastName[count], splitMobileNo[count], splitEmail[count]);
                createPayment(String.valueOf(rawContactNewId));

            }
        } else {
            createPayment(rawContactId);
        }
    }

    private boolean checkBookingExist(String rawContactId) throws Exception
    {
        int bookedClassId   = 0;
        int bookedContactId = 0;

        cursor = db.query(
                MSC_Contract.MSCEntry.TABLE_BOOKING,
                new String[]{MSC_Contract.MSCEntry.COL_CLASS_ID, MSC_Contract.MSCEntry.COL_CONTACT_ID},
                MSC_Contract.MSCEntry.COL_CLASS_ID + " = ? and " + MSC_Contract.MSCEntry.COL_CONTACT_ID + " = ?",
                new String[]{splitSelectedClass[0], rawContactId}, null, null, null
        );

        if (cursor.moveToFirst()) {
            bookedClassId = cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_CLASS_ID));
            bookedContactId = cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_CONTACT_ID));
        }
        cursor.close();

        if (rawContactId.equals(String.valueOf(bookedContactId)) &&
                Integer.parseInt(splitSelectedClass[0]) == bookedClassId) {

            AlertDialogUtils.errorDialog(getActivity(), "The class has been booked !");
            Log.d("BookingCreateFragment", "createPayment: Fail(The class has been booked !)");
            throw new Exception("The class has been booked !");
        }

        return false;
    }

    private boolean checkFormForSingleStudent() throws Exception
    {
        if (!RegexUtils.isMobile(etMobileNo.getText().toString())) {
            throw new Exception("Please enter a correct mobile number !");
        }
        if (!RegexUtils.isEmail(etEmail.getText().toString())) {
            throw new Exception("Please enter a correct email address !");
        }

        return true;
    }

    private boolean checkFormForMultiStudent() throws Exception
    {
        if (!(splitFirstName.length == splitLastName.length
                && splitMobileNo.length == splitEmail.length
                && splitFirstName.length == splitMobileNo.length)) {
            throw new Exception("Missing personal information!");
        }

        for (int i = 0; i < splitFirstName.length; i++) {
            if (!RegexUtils.isMobile(splitMobileNo[i])) {
                throw new Exception("Please enter correct mobile number!\nYour input: " + splitMobileNo[i]);
            }
            if (!RegexUtils.isEmail(splitEmail[i])) {
                throw new Exception("Please enter correct email!\nYour input: " + splitEmail[i]);
            }
        }

        return true;
    }

    private boolean formIsNotEmpty() throws Exception
    {
        if (RegexUtils.isEmpty(etFirstName.getText().toString())) {
            throw new Exception("Must at least has 1 person!");
        }
        if (RegexUtils.isEmpty(etLastName.getText().toString())) {
            throw new Exception("Must at least has 1 person!");
        }
        if (RegexUtils.isEmpty(etMobileNo.getText().toString())) {
            throw new Exception("Mobile number cannot be empty!");
        }
        if (RegexUtils.isEmpty(etEmail.getText().toString())) {
            throw new Exception("Email cannot be empty!");
        }
        if (RegexUtils.isEmpty(selectedClass)) {
            throw new Exception("Please select a class!");
        }

        return true;
    }

    private void insertContact(String firstName, String lastName, String mobile, String email)
    {
        try {
            resolver = getActivity().getContentResolver();

            rawContactNewId = ContentUris.parseId(resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values));

            // Name
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactNewId);
            values.put(ContactsContract.Data.DATA2, lastName + " " + firstName);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            resolver.insert(ContactsContract.Data.CONTENT_URI, values);
            Log.d("BookingCreateFragment", "createStudent: " + lastName + " " + firstName);
            values.clear();

            // Mobile
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactNewId);
            values.put(ContactsContract.Data.DATA1, mobile);
            values.put(ContactsContract.Data.DATA2, "2");
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            resolver.insert(ContactsContract.Data.CONTENT_URI, values);
            Log.d("BookingCreateFragment", "createStudent: " + mobile);
            values.clear();

            // Email
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactNewId);
            values.put(ContactsContract.Data.DATA1, email);
            values.put(ContactsContract.Data.DATA2, "1");
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
            resolver.insert(ContactsContract.Data.CONTENT_URI, values);
            Log.d("BookingCreateFragment", "createStudent: " + email);
            values.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long insertPayment()
    {
        values.put(MSC_Contract.MSCEntry.COL_AMOUNT, price);
        values.put(MSC_Contract.MSCEntry.COL_DEADLINE, DateTimeUtils.getDateTimeAfter7Days());
        values.put(MSC_Contract.MSCEntry.COL_RECEIVED_AT, "");
        values.put(MSC_Contract.MSCEntry.COL_CREATED_AT, DateTimeUtils.getDateTime());
        values.put(MSC_Contract.MSCEntry.COL_UPDATED_AT, DateTimeUtils.getDateTime());

        return db.insert(MSC_Contract.MSCEntry.TABLE_PAYMENT, null, values);
    }

    private long insertBooking(String rawContactId)
    {
        values.put(MSC_Contract.MSCEntry.COL_CONTACT_ID, Integer.valueOf(rawContactId));
        values.put(MSC_Contract.MSCEntry.COL_CLASS_ID, Integer.parseInt(splitSelectedClass[0]));
        values.put(MSC_Contract.MSCEntry.COL_STATUS_ID, checkClassStatus() ? MStatus.RESERVED : MStatus.WAITING_FOR_PAYMENT);
        values.put(MSC_Contract.MSCEntry.COL_PAYMENT_ID, getPaymentId());
        values.put(MSC_Contract.MSCEntry.COL_CREATED_AT, DateTimeUtils.getDateTime());
        values.put(MSC_Contract.MSCEntry.COL_UPDATED_AT, DateTimeUtils.getDateTime());

        return db.insert(MSC_Contract.MSCEntry.TABLE_BOOKING, null, values);
    }

    private void clearForm()
    {
        etFirstName.setText("");
        etLastName.setText("");
        etMobileNo.setText("");
        etEmail.setText("");
        spClass.setId(0);
    }

    private void splitData()
    {
        splitText(etFirstName.getText().toString(), etFirstName.getId());
        splitText(etLastName.getText().toString(), etLastName.getId());
        splitText(etMobileNo.getText().toString(), etMobileNo.getId());
        splitText(etEmail.getText().toString(), etEmail.getId());
        splitText(selectedClass, spClass.getId());
    }

    private void splitText(String text, int id)
    {
        if (!text.equals("")) {
            switch (id) {
                case R.id.etFirstName:
                    splitFirstName = text.split(",");
                    break;
                case R.id.etLastName:
                    splitLastName = text.split(",");
                    break;
                case R.id.etMobileNo:
                    splitMobileNo = text.split(",");
                    break;
                case R.id.etEmail:
                    splitEmail = text.split(",");
                    break;
                case R.id.spClass:
                    splitSelectedClass = text.split(":");
            }
        }
    }


}
