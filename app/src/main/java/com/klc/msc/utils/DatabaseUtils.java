package com.klc.msc.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.klc.msc.db.helper.MSC_Helper;

public class DatabaseUtils
{
    private static MSC_Helper     mscHelper;
    private static SQLiteDatabase db;
    private static ContentValues values;

    private static MSC_Helper getDatabaseHelper(Context context)
    {
        if (mscHelper == null) {
            mscHelper = new MSC_Helper(context);
        }
        return mscHelper;
    }

    public static SQLiteDatabase openForRead(Context context)
    {
        if (db == null || !db.isOpen()) {
            db = getDatabaseHelper(context).getReadableDatabase();
        }
        return db;
    }

    public static SQLiteDatabase openForWrite(Context context)
    {
        if (db == null || !db.isOpen()) {
            db = getDatabaseHelper(context).getWritableDatabase();
        }
        return db;
    }

    public static void close()
    {
        if (mscHelper != null) {
            mscHelper.close();
        }
    }

}
