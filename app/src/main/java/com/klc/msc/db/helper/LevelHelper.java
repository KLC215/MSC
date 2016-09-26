package com.klc.msc.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.klc.msc.db.contract.LevelContract;


public class LevelHelper extends SQLiteOpenHelper
{
    private static final String CREATE_TABLE =
            "create table " + LevelContract.LevelEntry.TABLE + " (" +
                    LevelContract.LevelEntry._ID + " integer primary key autoincrement, " +
                    LevelContract.LevelEntry.COL_NAME + " text, " +
                    LevelContract.LevelEntry.COL_CREATED_AT + " text, " +
                    LevelContract.LevelEntry.COL_UPDATED_AT + " text, " + ")";

    private static final String DROP_TABLE =
            "drop table if exists " + LevelContract.LevelEntry.TABLE;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Level.db";

    public LevelHelper(Context context)
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
