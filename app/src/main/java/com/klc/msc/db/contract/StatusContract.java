package com.klc.msc.db.contract;

import android.provider.BaseColumns;

public class StatusContract
{
    public static abstract class StatusEntry implements BaseColumns
    {
        // Table name
        public static final String TABLE = "STATUS";

        // Status name
        public static final String COL_NAME = "NAME";

        // Status created time
        public static final String COL_CREATED_AT = "CREATED_AT";

        // Status updated time
        public static final String COL_UPDATED_AT = "UPDATED_AT";
    }
}
