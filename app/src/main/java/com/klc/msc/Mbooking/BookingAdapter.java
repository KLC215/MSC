package com.klc.msc.Mbooking;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;

import com.klc.msc.R;
import com.klc.msc.db.MBooking;
import com.klc.msc.db.MClass;
import com.klc.msc.db.MContact;
import com.klc.msc.db.MPayment;

import java.util.List;
import java.util.Map;

public class BookingAdapter extends ArrayAdapter<MBooking> implements View.OnClickListener,
                                                                      Filterable
{
    private int            resourceId;
    private List<MBooking> bookings;
    private List<MBooking> searchList;
    private Map<Long, MClass> bookingClasses;
    private Map<Long, MPayment> bookingPayment;
    private Map<Long, MContact> bookingContacts;
    private Context        context;
    private Fragment       fragment;
    private LayoutInflater layoutInflater;
    private SQLiteDatabase db;
    private Cursor         cursor;

    public BookingAdapter(Context context, int resource, List<MBooking> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.resourceId = resource;
        this.bookings = objects;
        this.searchList = objects;
        layoutInflater = LayoutInflater.from(context);
        this.bookingClasses = BookingViewFragment.getBookingClasses();
        this.bookingPayment = BookingViewFragment.getBookingPayments();
        this.bookingContacts = BookingViewFragment.getBookingContacts();
    }

    private class ViewHolder
    {
        com.beardedhen.androidbootstrap.AwesomeTextView tvBookingName;
        com.beardedhen.androidbootstrap.AwesomeTextView tvMobile;
        com.beardedhen.androidbootstrap.AwesomeTextView tvClassName;
        com.beardedhen.androidbootstrap.AwesomeTextView tvPrice;
        com.beardedhen.androidbootstrap.BootstrapButton btnReceivedPayment;
        com.beardedhen.androidbootstrap.BootstrapButton btnEdit;
        com.beardedhen.androidbootstrap.BootstrapButton btnDelete;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;

        try {
            if (convertView == null) {

                convertView = layoutInflater.inflate(R.layout.item_booking, null);
                viewHolder = new ViewHolder();

                viewHolder.tvBookingName =
                        (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.tvBookingName);
                viewHolder.tvMobile =
                        (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.tvMobile);
                viewHolder.tvClassName =
                        (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.tvClassName);
                viewHolder.tvPrice =
                        (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.tvPrice);
                viewHolder.btnReceivedPayment =
                        (com.beardedhen.androidbootstrap.BootstrapButton) convertView.findViewById(R.id.btnReceivedPayment);
                viewHolder.btnEdit =
                        (com.beardedhen.androidbootstrap.BootstrapButton) convertView.findViewById(R.id.btnEdit);
                viewHolder.btnDelete =
                        (com.beardedhen.androidbootstrap.BootstrapButton) convertView.findViewById(R.id.btnDelete);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.btnReceivedPayment.setTag(position);
            viewHolder.btnEdit.setTag(position);
            viewHolder.btnDelete.setTag(position);

            viewHolder.btnReceivedPayment.setOnClickListener(this);
            viewHolder.btnEdit.setOnClickListener(this);
            viewHolder.btnDelete.setOnClickListener(this);

            String name = bookingContacts.get(bookings.get(position).getContactId()).getName();
            String mobile = bookingContacts.get(bookings.get(position).getContactId()).getMobile();
            String className = bookingClasses.get(bookings.get(position).getClassId()).getName();
            String price = String.valueOf(bookingPayment.get(bookings.get(position).getPaymentId()).getAmount());

            viewHolder.tvBookingName.setText(name);
            viewHolder.tvMobile.setText(mobile);
            viewHolder.tvClassName.setText(className);
            viewHolder.tvPrice.setText(price);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public void onClick(View v)
    {

    }

}
