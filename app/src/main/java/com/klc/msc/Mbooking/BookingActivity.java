package com.klc.msc.Mbooking;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.SlidingTabLayout;
import com.klc.msc.BaseFragment;
import com.klc.msc.R;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity
{

    // TODO: Booking titles
    private String[] titles = {
            "Create"
    };

    private ArrayList<BaseFragment> myFragments = new ArrayList<>();

    private SlidingTabLayout stlBooking;
    private ViewPager vpBooking;
    private BookingAdapter bookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        initView();
    }

    private void initView()
    {
        stlBooking = (SlidingTabLayout) findViewById(R.id.stlBooking);
        vpBooking = (ViewPager) findViewById(R.id.vpBooking);

        // TODO: Booking activity -> add fragment
        //myFragments.add();
    }

    private class BookingAdapter
    {
        // TODO: Implements Booking adapter
    }
}
