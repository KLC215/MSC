package com.klc.msc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity
{
    private String[] titles = {"CREATE"};

    private ArrayList<BaseFragment> myFragments = new ArrayList<>();

    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    private MyPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView()
    {
        tabLayout = (SlidingTabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        myFragments.add(FragmentA.getInstance(titles[0]));


        adapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager, titles);
    }

    private class MyPageAdapter extends FragmentPagerAdapter
    {
        public MyPageAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            return myFragments.get(position);
        }

        @Override
        public int getCount()
        {
            return myFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return titles[position];
        }
    }
}
