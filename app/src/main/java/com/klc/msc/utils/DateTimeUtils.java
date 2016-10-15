package com.klc.msc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils
{
    private DateTimeUtils()
    {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String getDateTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("zh", "HK"));
        Date date = new Date();
        return dateFormat.format(date);
    }
}
