package com.klc.msc.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.klc.msc.db.contract.BookingContract;
import com.klc.msc.db.contract.ClassContract;
import com.klc.msc.db.contract.StudentContract;

public class StudentHelper extends SQLiteOpenHelper
{
    private static final String CREATE_TABLE =
            "create table " + StudentContract.StudentEntry.TABLE + " (" +
                    StudentContract.StudentEntry._ID + " integer primary key autoincrement, " +
                    StudentContract.StudentEntry.COL_FIRST_NAME + " text, " +
                    StudentContract.StudentEntry.COL_LAST_NAME + " text, " +
                    StudentContract.StudentEntry.COL_MOBILE_NO + " text, " +
                    StudentContract.StudentEntry.COL_EMAIL + " text, " +
                    StudentContract.StudentEntry.COL_BOOKING_ID + " integer, " +
                    StudentContract.StudentEntry.COL_CLASS_ID + " integer, " +
                    StudentContract.StudentEntry.COL_CREATED_AT + " text, " +
                    StudentContract.StudentEntry.COL_UPDATED_AT + " text, " +
                    "FOREIGN KEY(" + StudentContract.StudentEntry.COL_BOOKING_ID + ") REFERENCES " +
                    BookingContract.BookingEntry.TABLE + "(" + BookingContract.BookingEntry._ID + "), " +
                    "FOREIGN KEY(" + StudentContract.StudentEntry.COL_CLASS_ID + ") REFERENCES " +
                    ClassContract.ClassEntry.TABLE + "(" + ClassContract.ClassEntry._ID + "))";

    private static final String DROP_TABLE =
            "drop table if exists " + StudentContract.StudentEntry.TABLE;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Student.db";

    public StudentHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
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
        onDowngrade(db, oldVersion, newVersion);
    }
}
