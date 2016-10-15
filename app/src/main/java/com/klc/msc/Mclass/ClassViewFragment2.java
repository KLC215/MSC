package com.klc.msc.Mclass;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.TextView;

import com.klc.msc.BaseFragment;
import com.klc.msc.R;
import com.klc.msc.db.contract.ClassContract;
import com.klc.msc.db.helper.ClassHelper;

import java.util.ArrayList;
import java.util.List;


public class ClassViewFragment2 extends BaseFragment
{
    private static List<Class> classList = new ArrayList<>();
    private String title;
    private final String[] columns = {
            ClassContract.ClassEntry.COL_NAME,
            ClassContract.ClassEntry.COL_DESCRIPTION
    };
    private ClassHelper  classHelper;
    private ClassAdapter classAdapter;
    private ListView     lvClass;

    public static ClassViewFragment2 getInstance(String title)
    {
        ClassViewFragment2 classViewFragment2 = new ClassViewFragment2();
        classViewFragment2.title = title;
        return classViewFragment2;
    }

    @Override
    public int getLayoutID()
    {
        return R.layout.fragment_class_view;
    }

    @Override
    public void initView()
    {
        initClass();

        classAdapter = new ClassAdapter(getActivity(), R.layout.item_class, classList);
        ListView lvClass  = (ListView) view.findViewById(R.id.lvClass);
        TextView txtEmpty = (TextView) view.findViewById(R.id.txtEmpty);

        lvClass.setEmptyView(txtEmpty);
        lvClass.setAdapter(classAdapter);

    }

    private void initClass()
    {
        classHelper = new ClassHelper(getActivity());
        SQLiteDatabase db = classHelper.getWritableDatabase();

        Cursor cursor = db.query(ClassContract.ClassEntry.TABLE, columns, null, null, null, null, null);

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
}
