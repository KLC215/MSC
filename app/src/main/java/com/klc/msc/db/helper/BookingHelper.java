package com.klc.msc.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.klc.msc.db.contract.BookingContract;
import com.klc.msc.db.contract.ClassContract;
import com.klc.msc.db.contract.PaymentContract;
import com.klc.msc.db.contract.StatusContract;
import com.klc.msc.db.contract.StudentContract;

public class BookingHelper extends SQLiteOpenHelper
{
    private static final String CREATE_TABLE =
            "create table " + BookingContract.BookingEntry.TABLE + " (" +
                    BookingContract.BookingEntry._ID + " integer primary key autoincrement, " +
                    BookingContract.BookingEntry.COL_STUDENT_ID + " integer, " +
                    BookingContract.BookingEntry.COL_CLASS_ID + " integer, " +
                    BookingContract.BookingEntry.COL_STATUS_ID + " integer, " +
                    BookingContract.BookingEntry.COL_PAYMENT_ID + " integer, " +
                    BookingContract.BookingEntry.COL_CREATED_AT + " text, " +
                    BookingContract.BookingEntry.COL_UPDATED_AT + " text, " +
                    "FOREIGN KEY(" + BookingContract.BookingEntry.COL_STUDENT_ID + ") REFERENCES " +
                    StudentContract.StudentEntry.TABLE + "(" + StudentContract.StudentEntry._ID + "), " +
                    "FOREIGN KEY(" + BookingContract.BookingEntry.COL_CLASS_ID + ") REFERENCES " +
                    ClassContract.ClassEntry.TABLE + "(" + ClassContract.ClassEntry._ID + "), " +
                    "FOREIGN KEY(" + BookingContract.BookingEntry.COL_PAYMENT_ID + ") REFERENCES " +
                    PaymentContract.PaymentEntry.TABLE + "(" + PaymentContract.PaymentEntry._ID + "), " +
                    "FOREIGN KEY(" + BookingContract.BookingEntry.COL_STATUS_ID + ") REFERENCES " +
                    StatusContract.StatusEntry.TABLE + "(" + StatusContract.StatusEntry._ID + "))";

    private static final String DROP_TABLE =
            "drop table if exists " + BookingContract.BookingEntry.TABLE;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Booking.db";

    public BookingHelper(Context context)
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
