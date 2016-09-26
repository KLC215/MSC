package com.klc.msc.db.contract;

import android.provider.BaseColumns;

public class ClassContract
{
    public static abstract class ClassEntry implements BaseColumns
    {
        // Table name
        public static final String TABLE = "CLASS";

        // Class name
        public static final String COL_NAME = "NAME";

        // Class tuition fee
        public static final String COL_PRICE = "PRICE";

        // Class description
        public static final String COL_DESCRIPTION = "DESCRIPTION";

        // Class level
        public static final String COL_LEVEL_ID = "LEVEL_ID";

        // Class location
        public static final String COL_LOCATION = "LOCATION";

        // How many lesson in the class
        public static final String COL_LESSON_NO = "LESSON_NO";

        // Class will be held at one day of week
        public static final String COL_WEEK_NO = "WEEK_NO";

        // Class maximum student number
        public static final String COL_MAX_STUDENT_NO = "MAX_STUDENT_NO";

        // Class starting date
        public static final String COL_START_DATE = "START_DATE";

        // Class ending date
        public static final String COL_END_DATE = "END_DATE";

        // Class starting time
        public static final String COL_START_TIME = "START_TIME";

        // Class ending time
        public static final String COL_END_TIME = "END_TIME";

        // Class status
        public static final String COL_STATUS_ID = "STATUS_ID";

        // Record created time
        public static final String COL_CREATED_AT = "CREATED_AT";

        // Record updated time
        public static final String COL_UPDATED_AT = "UPDATED_AT";


    }
}
