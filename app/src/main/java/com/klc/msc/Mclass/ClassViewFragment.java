package com.klc.msc.Mclass;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.klc.msc.R;
import com.klc.msc.db.MClass;
import com.klc.msc.db.contract.MSC_Contract;
import com.klc.msc.db.helper.MSC_Helper;

import java.util.ArrayList;
import java.util.List;

public class ClassViewFragment extends Fragment implements View.OnClickListener
{
    private static List<MClass> classList = new ArrayList<>();
    private final  String[]     columns   = {
            MSC_Contract.MSCEntry.COL_CLASS_ID,
            MSC_Contract.MSCEntry.COL_NAME,
            MSC_Contract.MSCEntry.COL_PRICE,
            MSC_Contract.MSCEntry.COL_DESCRIPTION,
            MSC_Contract.MSCEntry.COL_LOCATION,
            MSC_Contract.MSCEntry.COL_LESSON_NO,
            MSC_Contract.MSCEntry.COL_HOURS,
            MSC_Contract.MSCEntry.COL_MAX_STUDENT_NO,
            MSC_Contract.MSCEntry.COL_START_DATE,
            MSC_Contract.MSCEntry.COL_END_DATE,
            MSC_Contract.MSCEntry.COL_START_TIME,
            MSC_Contract.MSCEntry.COL_END_TIME,
            MSC_Contract.MSCEntry.COL_STATUS_ID,
            MSC_Contract.MSCEntry.COL_CREATED_AT,
            MSC_Contract.MSCEntry.COL_UPDATED_AT
    };

    private static int flag = 0;

    private SQLiteDatabase db;
    private Cursor         cursor;

    private MSC_Helper   mscHelper;
    private ClassAdapter classAdapter;

    private Fragment fragment;
    private ListView lvClass;

    private com.joanzapata.iconify.widget.IconButton          btnCreateClass;
    private com.beardedhen.androidbootstrap.BootstrapEditText etSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View classView = inflater.inflate(R.layout.fragment_class_view, container, false);

        lvClass = (ListView) classView.findViewById(R.id.lvClass);
        btnCreateClass = (com.joanzapata.iconify.widget.IconButton) classView.findViewById(R.id.btnCreateClass);
        etSearch = (com.beardedhen.androidbootstrap.BootstrapEditText) classView.findViewById(R.id.etSearch);

        btnCreateClass.setOnClickListener(this);
        etSearch.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (classAdapter != null) {
                    classAdapter.getClassFilter().filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });


        return classView;
    }

    @Override
    public void onResume()
    {
        try {
            if (flag == 0) {
                initClass();
                classAdapter = new ClassAdapter(getActivity(), R.layout.item_class, classList);
                lvClass.setAdapter(classAdapter);
                flag = 1;
            } else {
                classList.clear();
                initClass();
                classAdapter = new ClassAdapter(getActivity(), R.layout.item_class, classList);
                lvClass.setAdapter(classAdapter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btnCreateClass:
                fragment = new ClassCreateFragment();
                replaceFragment(fragment);
                break;
        }

    }

    private void initClass()
    {
        mscHelper = new MSC_Helper(getActivity());
        db = mscHelper.getWritableDatabase();

        cursor = db.query(MSC_Contract.MSCEntry.TABLE_CLASS, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                classList.add(
                        new MClass(
                                cursor.getLong(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_CLASS_ID)),
                                cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_NAME)),
                                cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_PRICE)),
                                cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_DESCRIPTION)),
                                cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_LOCATION)),
                                cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_LESSON_NO)),
                                cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_HOURS)),
                                cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_MAX_STUDENT_NO)),
                                cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_START_DATE)),
                                cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_END_DATE)),
                                cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_START_TIME)),
                                cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_END_TIME)),
                                cursor.getInt(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_STATUS_ID)),
                                cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_CREATED_AT)),
                                cursor.getString(cursor.getColumnIndex(MSC_Contract.MSCEntry.COL_UPDATED_AT))
                        )
                );
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
    }

    public static List<MClass> getClassList()
    {
        return classList;
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
