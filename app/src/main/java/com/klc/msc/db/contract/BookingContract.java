package com.klc.msc.db.contract;

import android.provider.BaseColumns;

public class BookingContract
{
    public static abstract class BookingEntry implements BaseColumns
    {
        // Table name
        public static final String TABLE = "BOOKING";

        // Who have booking
        public static final String COL_STUDENT_ID = "STUDENT_ID";

        // Which class(es) has been booked
        public static final String COL_CLASS_ID = "CLASS_ID";

        // Booking status
        public static final String COL_STATUS_ID = "STATUS_ID";

        // Booking payment
        public static final String COL_PAYMENT_ID = "PAYMENT_ID";

        // Booking created time
        public static final String COL_CREATED_AT = "CREATED_AT";

        // Booking updated time
        public static final String COL_UPDATED_AT = "UPDATED_AT";

    }
}
