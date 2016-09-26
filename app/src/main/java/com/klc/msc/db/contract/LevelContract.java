package com.klc.msc.db.contract;

import android.provider.BaseColumns;

public class LevelContract
{
    public  static abstract class LevelEntry implements BaseColumns
    {
        // Table name
        public static final String TABLE = "LEVEL";

        // Level name
        public static final String COL_NAME = "NAME";

        // Level created time
        public static final String COL_CREATED_AT  = "CREATED_AT";

        // Level updated time
        public static final String COL_UPDATED_AT = "UPDATED_AT";

    }
}
