package com.klc.msc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils
{
    private static SimpleDateFormat dateFormat;
    private static Locale zh_HK = new Locale("zh", "HK");
    private static Calendar calendar;
    private static Date date;

    private static final int MSEC = 1;
    private static final int SEC  = 1000;
    private static final int MIN  = 60000;
    private static final int HOUR = 3600000;
    private static final int DAY  = 86400000;

    public enum TimeUnit
    {
        MSEC, SEC, MIN, HOUR, DAY
    }

    private DateTimeUtils()
    {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static long milliseconds2Unit(long milliseconds, TimeUnit unit)
    {
        switch (unit) {
            case MSEC:
                return milliseconds / MSEC;
            case SEC:
                return milliseconds / SEC;
            case MIN:
                return milliseconds / MIN;
            case HOUR:
                return milliseconds / HOUR;
            case DAY:
                return milliseconds / DAY;
        }
        return -1;
    }

    public static long string2Milliseconds(String time, SimpleDateFormat format)
    {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getDateTime()
    {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", zh_HK);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDateTimeAfter7Days()
    {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", zh_HK);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        return dateFormat.format(calendar.getTime());
    }

    public static long getIntervalTime(String time0, String time1, TimeUnit unit, SimpleDateFormat format)
    {
        return milliseconds2Unit(Math.abs(string2Milliseconds(time0, format)
                                                  - string2Milliseconds(time1, format)), unit);
    }
}
