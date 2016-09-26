package com.klc.msc.db.contract;

import android.provider.BaseColumns;

public class PaymentContract
{
    public static abstract class PaymentEntry implements BaseColumns
    {
        // Table name
        public static final String TABLE = "PAYMENT";

        // Payment amount
        public static final String COL_AMOUNT = "AMOUNT";

        // Payment deadline
        public static final String COL_DEADLINE = "DEADLINE";

        // Payment received date
        public static final String COL_RECEIVED_AT = "RECEIVED_AT";

        // Payment created time
        public static final String COL_CREATED_AT = "CREATED_AT";

        // Payment updated time
        public static final String COL_UPDATED_AT = "UPDATED_AT";


    }
}
