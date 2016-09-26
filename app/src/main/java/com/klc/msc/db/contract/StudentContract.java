package com.klc.msc.db.contract;

import android.provider.BaseColumns;

public class StudentContract
{
    public static abstract class StudentEntry implements BaseColumns
    {
        // Table name
        public static final String TABLE = "STUDENT";

        // Student first name
        public static final String COL_FIRST_NAME = "FIRST_NAME";

        // Student last name
        public static final  String COL_LAST_NAME = "LAST_NAME";

        // Student mobile number
        public static final String COL_MOBILE_NO = "MOBILE_NO";

        // Student email address
        public static final String COL_EMAIL = "EMAIL";

        // Which booking(s) that student has
        public static final String COL_BOOKING_ID = "BOOKING_ID";

        // Which class(s) that student takes
        public static final String COL_CLASS_ID = "CLASS_ID";

        // Student created time
        public static final String COL_CREATED_AT = "CREATED_AT";

        // Student updated time
        public static final String COL_UPDATED_AT = "UPDATED_AT";
    }
}
