package com.klc.msc.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.klc.msc.db.contract.MSC_Contract;

public class MSC_Helper extends SQLiteOpenHelper
{
    private final static String[] status = {"Open", "Waiting for payment", "Reserved", "Full", "Confirmed", "Completed"};
    private ContentValues values;

    private static final String CREATE_BOOKING =
            "create table " + MSC_Contract.MSCEntry.TABLE_BOOKING + " (" +
                    MSC_Contract.MSCEntry.COL_BOOKING_ID + " integer primary key autoincrement, " +
                    MSC_Contract.MSCEntry.COL_CONTACT_ID + " integer, " +
                    MSC_Contract.MSCEntry.COL_CLASS_ID + " integer, " +
                    MSC_Contract.MSCEntry.COL_STATUS_ID + " integer, " +
                    MSC_Contract.MSCEntry.COL_PAYMENT_ID + " integer, " +
                    MSC_Contract.MSCEntry.COL_CREATED_AT + " text, " +
                    MSC_Contract.MSCEntry.COL_UPDATED_AT + " text, " +
                    "FOREIGN KEY(" + MSC_Contract.MSCEntry.COL_CLASS_ID + ") REFERENCES " +
                    MSC_Contract.MSCEntry.TABLE_CLASS + "(" + MSC_Contract.MSCEntry.COL_CLASS_ID + ") ON DELETE CASCADE, " +
                    "FOREIGN KEY(" + MSC_Contract.MSCEntry.COL_PAYMENT_ID + ") REFERENCES " +
                    MSC_Contract.MSCEntry.TABLE_PAYMENT + "(" + MSC_Contract.MSCEntry.COL_PAYMENT_ID + ") ON DELETE CASCADE, " +
                    "FOREIGN KEY(" + MSC_Contract.MSCEntry.COL_STATUS_ID + ") REFERENCES " +
                    MSC_Contract.MSCEntry.TABLE_STATUS + "(" + MSC_Contract.MSCEntry.COL_STATUS_ID + ") ON DELETE CASCADE)";

    private static final String CREATE_CLASS =
            "create table " + MSC_Contract.MSCEntry.TABLE_CLASS + " (" +
                    MSC_Contract.MSCEntry.COL_CLASS_ID + " integer primary key autoincrement, " +
                    MSC_Contract.MSCEntry.COL_NAME + " text, " +
                    MSC_Contract.MSCEntry.COL_PRICE + " integer, " +
                    MSC_Contract.MSCEntry.COL_DESCRIPTION + " text, " +
                    MSC_Contract.MSCEntry.COL_LOCATION + " text, " +
                    MSC_Contract.MSCEntry.COL_LESSON_NO + " integer, " +
                    MSC_Contract.MSCEntry.COL_HOURS + " integer, " +
                    MSC_Contract.MSCEntry.COL_MAX_STUDENT_NO + " integer, " +
                    MSC_Contract.MSCEntry.COL_START_DATE + " text, " +
                    MSC_Contract.MSCEntry.COL_END_DATE + " text, " +
                    MSC_Contract.MSCEntry.COL_START_TIME + " text, " +
                    MSC_Contract.MSCEntry.COL_END_TIME + " text, " +
                    MSC_Contract.MSCEntry.COL_STATUS_ID + " integer, " +
                    MSC_Contract.MSCEntry.COL_CREATED_AT + " text, " +
                    MSC_Contract.MSCEntry.COL_UPDATED_AT + " text, " +
                    "FOREIGN KEY(" + MSC_Contract.MSCEntry.COL_STATUS_ID + ") REFERENCES " +
                    MSC_Contract.MSCEntry.TABLE_STATUS + "(" + MSC_Contract.MSCEntry.COL_STATUS_ID + ") ON DELETE CASCADE)";

    private static final String CREATE_PAYMENT =
            "create table " + MSC_Contract.MSCEntry.TABLE_PAYMENT + " (" +
                    MSC_Contract.MSCEntry.COL_PAYMENT_ID + " integer primary key autoincrement, " +
                    MSC_Contract.MSCEntry.COL_AMOUNT + " integer, " +
                    MSC_Contract.MSCEntry.COL_DEADLINE + " text, " +
                    MSC_Contract.MSCEntry.COL_RECEIVED_AT + " text, " +
                    MSC_Contract.MSCEntry.COL_CREATED_AT + " text, " +
                    MSC_Contract.MSCEntry.COL_UPDATED_AT + " text)";

    private static final String CREATE_STATUS =
            "create table " + MSC_Contract.MSCEntry.TABLE_STATUS + " (" +
                    MSC_Contract.MSCEntry.COL_STATUS_ID + " integer primary key autoincrement, " +
                    MSC_Contract.MSCEntry.COL_NAME + " text, " +
                    MSC_Contract.MSCEntry.COL_CREATED_AT + " text, " +
                    MSC_Contract.MSCEntry.COL_UPDATED_AT + " text)";



    private static final String DROP_BOOKING =
            "drop table if exists " + MSC_Contract.MSCEntry.TABLE_BOOKING;
    private static final String DROP_CLASS   =
            "drop table if exists " + MSC_Contract.MSCEntry.TABLE_CLASS;
    private static final String DROP_PAYMENT =
            "drop table if exists " + MSC_Contract.MSCEntry.TABLE_PAYMENT;
    private static final String DROP_STATUS  =
            "drop table if exists " + MSC_Contract.MSCEntry.TABLE_STATUS;


    public static final int    DATABASE_VERSION = 1;
    public static final String DATABASE_NAME    = "MSC.db";

    public MSC_Helper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        values = new ContentValues();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_STATUS);
        insertStatus(db, values);
        db.execSQL(CREATE_CLASS);
        db.execSQL(CREATE_PAYMENT);
        db.execSQL(CREATE_BOOKING);
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_BOOKING);
        db.execSQL(DROP_PAYMENT);
        db.execSQL(DROP_CLASS);
        db.execSQL(DROP_STATUS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void insertStatus(SQLiteDatabase db, ContentValues values)
    {
        for (String state : status) {
            values.put(MSC_Contract.MSCEntry.COL_NAME, state);
            db.insert(MSC_Contract.MSCEntry.TABLE_STATUS, null, values);
            values.clear();
        }
    }
}
