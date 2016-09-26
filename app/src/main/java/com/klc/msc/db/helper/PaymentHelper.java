package com.klc.msc.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.klc.msc.db.contract.PaymentContract;


public class PaymentHelper extends SQLiteOpenHelper
{
    private static final String CREATE_TABLE =
            "create table " + PaymentContract.PaymentEntry.TABLE + " (" +
                    PaymentContract.PaymentEntry._ID + " integer primary key autoincrement, " +
                    PaymentContract.PaymentEntry.COL_AMOUNT + " integer, " +
                    PaymentContract.PaymentEntry.COL_DEADLINE + " text, " +
                    PaymentContract.PaymentEntry.COL_RECEIVED_AT + " text, " +
                    PaymentContract.PaymentEntry.COL_CREATED_AT + " text, " +
                    PaymentContract.PaymentEntry.COL_UPDATED_AT + " text)";

    private static final String DROP_TABLE =
            "drop table if exists " + PaymentContract.PaymentEntry.TABLE;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Payment.db";

    public PaymentHelper(Context context)
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
