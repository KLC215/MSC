package com.klc.msc.Mclass;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.klc.msc.R;
import com.klc.msc.db.contract.ClassContract;
import com.klc.msc.db.helper.ClassHelper;

import java.util.ArrayList;
import java.util.List;

public class ClassViewFragment extends Fragment implements View.OnClickListener
{
    private static List<Class> classList = new ArrayList<>();
    private final  String[]    columns   = {
            ClassContract.ClassEntry._ID,
            ClassContract.ClassEntry.COL_NAME,
            ClassContract.ClassEntry.COL_DESCRIPTION
    };
    private static int         flag      = 0;

    private SQLiteDatabase db;
    private Cursor         cursor;

    private ClassHelper  classHelper;
    private ClassAdapter classAdapter;

    private Fragment fragment;
    private ListView lvClass;

    private com.joanzapata.iconify.widget.IconButton btnCreateClass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_class_view, container, false);

        lvClass = (ListView) view.findViewById(R.id.lvClass);
        btnCreateClass = (com.joanzapata.iconify.widget.IconButton) view.findViewById(R.id.btnCreateClass);

        btnCreateClass.setOnClickListener(this);


        return view;
    }

    @Override
    public void onResume()
    {
        try {
            if (flag == 0) {
                initClass();
                lvClass.setAdapter(new ClassAdapter(getActivity(), R.layout.item_class, classList));
                flag = 1;
            } else {
                classList.clear();
                initClass();
                lvClass.setAdapter(new ClassAdapter(getActivity(), R.layout.item_class, classList));
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
        classHelper = new ClassHelper(getActivity());
        db = classHelper.getWritableDatabase();

        cursor = db.query(ClassContract.ClassEntry.TABLE, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                classList.add(
                        new Class(
                                cursor.getLong(cursor.getColumnIndex(ClassContract.ClassEntry._ID)),
                                cursor.getString(cursor.getColumnIndex(ClassContract.ClassEntry.COL_NAME)),
                                cursor.getString(cursor.getColumnIndex(ClassContract.ClassEntry.COL_DESCRIPTION))
                        )
                );
            } while (cursor.moveToNext());
        }

        cursor.close();
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
