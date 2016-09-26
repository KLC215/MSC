package com.klc.msc.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.klc.msc.db.contract.StatusContract;


public class StatusHelper extends SQLiteOpenHelper
{
    private static final String CREATE_TABLE =
            "create table " + StatusContract.StatusEntry.TABLE + " (" +
                    StatusContract.StatusEntry._ID + " integer primary key autoincrement, " +
                    StatusContract.StatusEntry.COL_NAME + " text, " +
                    StatusContract.StatusEntry.COL_CREATED_AT + " text, " +
                    StatusContract.StatusEntry.COL_UPDATED_AT + " text)";

    private static final String DROP_TABLE =
            "drop table if exists " + StatusContract.StatusEntry.TABLE;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Status.db";

    public StatusHelper(Context context)
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
