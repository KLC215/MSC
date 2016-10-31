package com.klc.msc.db.contract;

import android.provider.BaseColumns;

public class MSC_Contract
{
    public static abstract class MSCEntry implements BaseColumns
    {
        /********************************************************
         *                      Class table
         *******************************************************/
        public static final String TABLE_CLASS = "CLASS"; // Table name
        /* public static final String COL_NAME = "NAME"; */
        public static final String COL_PRICE = "PRICE"; // Class tuition fee
        public static final String COL_DESCRIPTION = "DESCRIPTION"; // Class description
        public static final String COL_LOCATION = "LOCATION"; // Class location
        public static final String COL_LESSON_NO = "LESSON_NO"; // How many lesson in the class
        public static final String COL_HOURS = "HOURS"; // How long of a class
        public static final String COL_MAX_STUDENT_NO = "MAX_STUDENT_NO"; // Class maximum student number
        public static final String COL_START_DATE = "START_DATE"; // Class starting date
        public static final String COL_END_DATE = "END_DATE"; // Class ending date
        public static final String COL_START_TIME = "START_TIME"; // Class starting time
        public static final String COL_END_TIME = "END_TIME"; // Class ending time
        /* public static final String COL_STATUS_ID = "STATUS_ID"; */

        /********************************************************
         *                      Booking table
         *******************************************************/
        public static final String TABLE_BOOKING = "BOOKING"; // Table name
        //public static final String COL_STUDENT_ID = "STUDENT_ID"; // Who have booking
        /*public static final String COL_CLASS_ID = "CLASS_ID"; */ // Which class(es) has been booked
        //public static final String COL_PAYMENT_ID = "PAYMENT_ID"; // Booking payment
        /* public static final String COL_STATUS_ID = "STATUS_ID"; */

        /********************************************************
         *                      Payment table
         *******************************************************/
        public static final String TABLE_PAYMENT = "PAYMENT"; // Table name
        public static final String COL_AMOUNT = "AMOUNT"; // Payment amount
        public static final String COL_DEADLINE = "DEADLINE"; // Payment deadline
        public static final String COL_RECEIVED_AT = "RECEIVED_AT"; // Payment received date

        /********************************************************
         *                      MStatus table
         *******************************************************/
        public static final String TABLE_STATUS = "STATUS"; // Table name
        /* public static final String COL_NAME = "NAME"; */

        // Common columns
        public static final String COL_NAME = "NAME"; // Class, MStatus name
        public static final String COL_CLASS_ID = "CLASS_ID"; // Which booking, class(s) that student takes
        public static final String COL_CONTACT_ID = "CONTACT_ID";
        public static final String COL_PAYMENT_ID = "PAYMENT_ID";
        public static final String COL_BOOKING_ID = "BOOKING_ID";
        public static final String COL_STATUS_ID = "STATUS_ID"; // Class, Booking status
        public static final String COL_CREATED_AT = "CREATED_AT"; // Record created time
        public static final String COL_UPDATED_AT = "UPDATED_AT"; // Record updated time
    }
}
