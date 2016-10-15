package com.klc.msc.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.klc.msc.db.contract.ClassContract;
import com.klc.msc.db.contract.StatusContract;


public class ClassHelper extends SQLiteOpenHelper
{
    private static final String CREATE_TABLE =
            "create table " + ClassContract.ClassEntry.TABLE + " (" +
                    ClassContract.ClassEntry._ID + " integer primary key autoincrement, " +
                    ClassContract.ClassEntry.COL_NAME + " text, " +
                    ClassContract.ClassEntry.COL_PRICE + " integer, " +
                    ClassContract.ClassEntry.COL_DESCRIPTION + " text, " +
                    ClassContract.ClassEntry.COL_LOCATION + " text, " +
                    ClassContract.ClassEntry.COL_LESSON_NO + " integer, " +
                    ClassContract.ClassEntry.COL_MAX_STUDENT_NO + " integer, " +
                    ClassContract.ClassEntry.COL_START_DATE + " text, " +
                    ClassContract.ClassEntry.COL_END_DATE + " text, " +
                    ClassContract.ClassEntry.COL_START_TIME + " text, " +
                    ClassContract.ClassEntry.COL_END_TIME + " text, " +
                    ClassContract.ClassEntry.COL_STATUS_ID + " integer, " +
                    ClassContract.ClassEntry.COL_CREATED_AT + " text, " +
                    ClassContract.ClassEntry.COL_UPDATED_AT + " text, " +
                    "FOREIGN KEY(" + ClassContract.ClassEntry.COL_STATUS_ID + ") REFERENCES " +
                    StatusContract.StatusEntry.TABLE + "(" + StatusContract.StatusEntry._ID + "))";

    private static final String DROP_TABLE =
            "drop table if exists " + ClassContract.ClassEntry.TABLE;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Class.db";

    public ClassHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}
