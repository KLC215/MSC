package com.klc.msc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.klc.msc.Mbooking.BookingRootFragment;
import com.klc.msc.Mclass.ClassRootFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private ViewPager          viewPager;
    private SliderPagerAdapter pagerAdapter;
    private List<Fragment>     fragments;

    private LinearLayout tabBooking;
    private LinearLayout tabClass;
    private LinearLayout tab1;
    private LinearLayout tab2;

    private ImageButton ibBooking;
    private ImageButton ibClass;
    private ImageButton ib3;
    private ImageButton ib4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initViewPage();
        initEvent();
    }

    private void initView()
    {
        viewPager = (ViewPager) findViewById(R.id.vpMain);

        tabBooking = (LinearLayout) findViewById(R.id.tabBooking);
        tabClass = (LinearLayout) findViewById(R.id.tabClass);
        tab1 = (LinearLayout) findViewById(R.id.tab1);
        tab2 = (LinearLayout) findViewById(R.id.tab2);

        ibBooking = (ImageButton) findViewById(R.id.ibBooking);
        ibClass = (ImageButton) findViewById(R.id.ibClass);
        ib3 = (ImageButton) findViewById(R.id.ib3);
        ib4 = (ImageButton) findViewById(R.id.ib4);

    }

    private void initViewPage()
    {
        //TODO: Add fragment
        fragments = new ArrayList<>();

        fragments.add(new BookingRootFragment());
        fragments.add(new ClassRootFragment());

        //MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);
        pagerAdapter = new SliderPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    private void initEvent()
    {
        tabBooking.setOnClickListener(this);
        tabClass.setOnClickListener(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                Fragment fragment = ((SliderPagerAdapter) viewPager.getAdapter()).getFragment(position);

                if(fragment != null) {
                    switch (position) {
                        case 1:
                            fragment.onResume();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.tabBooking:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tabClass:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tab1:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tab2:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    private class SliderPagerAdapter extends FragmentPagerAdapter
    {
        private Map<Integer, String> fragmentTags;
        private FragmentManager      fm;

        public SliderPagerAdapter(FragmentManager fm)
        {
            super(fm);
            this.fm = fm;
            fragmentTags = new HashMap<>();
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position) {
                case 0:
                    return new BookingRootFragment();
                case 1:
                    return new ClassRootFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount()
        {
            return fragments.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            Object obj = super.instantiateItem(container, position);

            if (obj instanceof Fragment) {
                Fragment f   = (Fragment) obj;
                String   tag = f.getTag();
                fragmentTags.put(position, tag);
            }
            return obj;
        }

        public Fragment getFragment(int position)
        {
            String tag = fragmentTags.get(position);
            if (tag == null) {
                return null;
            }
            return fm.findFragmentByTag(tag);
        }
    }
}
