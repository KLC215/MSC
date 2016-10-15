package com.klc.msc.utils;

import java.util.regex.Pattern;

public class RegexUtils
{
    private static final String REGEX_EMPTY = "^$";
    private static final String REGEX_MOBILE = "[1-9][0-9]{7}";
    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String REGEX_FORMATED_DATE = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
    private static final String REGEX_FORMATED_TIME = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    private RegexUtils()
    {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isEmpty(String string)
    {
        return isMatch(REGEX_EMPTY, string);
    }

    public static boolean isMobile(String mobile)
    {
        return isMatch(REGEX_MOBILE, mobile);
    }

    public static boolean isEmail(String email)
    {
        return isMatch(REGEX_EMAIL, email);
    }

    public static boolean isFormatedDate(String date)
    {
        return isMatch(REGEX_FORMATED_DATE, date);
    }

    public static boolean isFormatedTime(String time)
    {
        return isMatch(REGEX_FORMATED_TIME, time);
    }

    private static boolean isMatch(String regex, String string)
    {
        return Pattern.matches(regex, string);
    }
}
