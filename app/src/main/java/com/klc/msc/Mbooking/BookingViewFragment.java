package com.klc.msc.Mbooking;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.klc.msc.Mclass.ClassViewFragment;
import com.klc.msc.R;
import com.klc.msc.db.MBooking;
import com.klc.msc.db.MClass;
import com.klc.msc.db.MContact;
import com.klc.msc.db.MPayment;
import com.klc.msc.db.contract.MSC_Contract;
import com.klc.msc.utils.DatabaseUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingViewFragment extends Fragment implements View.OnClickListener
{
    private static List<MBooking> bookingList = new ArrayList<>();
    private static List<MPayment> paymentList = new ArrayList<>();
    private static List<MContact> contactList = new ArrayList<>();
    private static Map<Long, MPayment> bookingPayments = new HashMap<>();
    private static Map<Long, MContact> bookingContacts = new HashMap<>();
    private static Map<Long, MClass>   bookingClasses  = new HashMap<>();
    private static List<MClass> classList;

    private final String[] bookingColumns = {
            MSC_Contract.MSCEntry.COL_BOOKING_ID,
            MSC_Contract.MSCEntry.COL_CONTACT_ID,
            MSC_Contract.MSCEntry.COL_CLASS_ID,
            MSC_Contract.MSCEntry.COL_STATUS_ID,
            MSC_Contract.MSCEntry.COL_PAYMENT_ID,
            MSC_Contract.MSCEntry.COL_CREATED_AT,
            MSC_Contract.MSCEntry.COL_UPDATED_AT
    };
    private final String[] paymentColumns = {
            MSC_Contract.MSCEntry.COL_PAYMENT_ID,
            MSC_Contract.MSCEntry.COL_AMOUNT,
            MSC_Contract.MSCEntry.COL_DEADLINE,
            MSC_Contract.MSCEntry.COL_RECEIVED_AT,
            MSC_Contract.MSCEntry.COL_CREATED_AT,
            MSC_Contract.MSCEntry.COL_UPDATED_AT
    };

    private String name, mobile, className;
    private int price;

    private static int flag = 0;

    private SQLiteDatabase db;
    private Cursor         cursor;
    private BookingAdapter bookingAdapter;

    private Fragment bookingCreatefragment;
    private ListView lvBooking;

    private com.joanzapata.iconify.widget.IconButton          btnCreateBooking;
    private com.beardedhen.androidbootstrap.BootstrapEditText etSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View bookingView = inflater.inflate(R.layout.fragment_booking_view, container, false);

        lvBooking = (ListView) bookingView.findViewById(R.id.lvBooking);
        btnCreateBooking = (com.joanzapata.iconify.widget.IconButton) bookingView.findViewById(R.id.btnCreateBooking);
        etSearch = (com.beardedhen.androidbootstrap.BootstrapEditText) bookingView.findViewById(R.id.etSearch);

        classList = ClassViewFragment.getClassList();

        btnCreateBooking.setOnClickListener(this);

        return bookingView;
    }

    @Override
    public void onResume()
    {
        try {
            if (flag == 0) {
                initBooking();
                bookingAdapter = new BookingAdapter(getActivity(), R.layout.item_booking, bookingList);
                lvBooking.setAdapter(bookingAdapter);
                flag = 1;
            } else {
                bookingList.clear();
                paymentList.clear();
                contactList.clear();
                bookingClasses.clear();
                bookingPayments.clear();
                bookingContacts.clear();
                initBooking();
                bookingAdapter = new BookingAdapter(getActivity(), R.layout.item_booking, bookingList);
                lvBooking.setAdapter(bookingAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    private void initBooking()
    {
        db = DatabaseUtils.openForWrite(getActivity());

        getAllBookings();
        getAllPayments();
        getAllContacts();

        for (int i = 0; i < bookingList.size(); i++) {
            for (int j = 0; j < paymentList.size(); j++) {
                if (paymentList.get(j).getId() == bookingList.get(i).getPaymentId()) {
                    bookingPayments.put(bookingList.get(i).getPaymentId(), paymentList.get(j));
                }
            }
            for (int k = 0; k < contactList.size(); k++) {
                if (contactList.get(k).getId() == bookingList.get(i).getContactId()) {
                    bookingContacts.put(bookingList.get(i).getContactId(), contactList.get(k));
                }
            }
            for (int l = 0; l < classList.size(); l++) {
                if (classList.get(l).getId() == bookingList.get(i).getClassId()) {
                    bookingClasses.put(bookingList.get(i).getClassId(), classList.get(l));
                }
            }
        }

        DatabaseUtils.close();
    }

    private void getAllBookings()
    {
        try {
            cursor = db.query(
                    MSC_Contract.MSCEntry.TABLE_BOOKING, bookingColumns,
                    null, null, null, null, null
            );

            if (cursor.moveToFirst()) {
                do {
                    bookingList.add(
                            new MBooking(
                                    cursor.getLong(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_BOOKING_ID)),
                                    cursor.getLong(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_CONTACT_ID)),
                                    cursor.getLong(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_CLASS_ID)),
                                    cursor.getLong(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_STATUS_ID)),
                                    cursor.getLong(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_PAYMENT_ID)),
                                    cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_CREATED_AT)),
                                    cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_UPDATED_AT))
                            )
                    );

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    private void getAllPayments()
    {
        try {
            cursor = db.query(
                    MSC_Contract.MSCEntry.TABLE_PAYMENT, paymentColumns,
                    null, null, null, null, null
            );

            if (cursor.moveToFirst()) {
                do {
                    paymentList.add(
                            new MPayment(
                                    cursor.getLong(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_PAYMENT_ID)),
                                    cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_AMOUNT)),
                                    cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_DEADLINE)),
                                    cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_RECEIVED_AT)),
                                    cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_CREATED_AT)),
                                    cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_UPDATED_AT))
                            )
                    );
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    private void getAllContacts()
    {
        try {
            ContentResolver resolver = getActivity().getContentResolver();

            for (int i = 0; i < bookingList.size(); i++) {
                cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                        ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID + " = " + bookingList.get(i).getContactId(), null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    contactList.add(
                            new MContact(
                                    cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID)),
                                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            )
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btnCreateBooking:
                bookingCreatefragment = new BookingCreateFragment();
                replaceFragment(bookingCreatefragment);
                break;
        }
    }

    public static Map<Long, MPayment> getBookingPayments()
    {
        return bookingPayments;
    }

    public static Map<Long, MContact> getBookingContacts()
    {
        return bookingContacts;
    }

    public static Map<Long, MClass> getBookingClasses()
    {
        return bookingClasses;
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(this.getId(), fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
