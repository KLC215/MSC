package com.klc.msc.db;

import java.util.List;

public class MBookingPayment
{
    private List<MBooking> bookings;
    private List<MPayment> payments;

    public MBookingPayment(List<MBooking> bookings, List<MPayment> payments)
    {
        this.bookings = bookings;
        this.payments = payments;
    }

    public List<MBooking> getBookings()
    {
        return bookings;
    }

    public List<MPayment> getPayments()
    {
        return payments;
    }
}
