package com.klc.msc.magic_class;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.SlidingTabLayout;
import com.klc.msc.BaseFragment;
import com.klc.msc.R;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity
{
    private String[] titles = {
            "Create Class", "View Class"
    };

    private ArrayList<BaseFragment> classFragments = new ArrayList<>();

    private SlidingTabLayout stlClass;
    private ViewPager vpClass;
    private ClassAdapter classAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        initView();
    }

    private void initView()
    {
        stlClass = (SlidingTabLayout) findViewById(R.id.stlClass);
        vpClass = (ViewPager) findViewById(R.id.vpClass);

        // TODO: add class fragments
        classFragments.add(ClassCreateFragment.getInstance(titles[0]));
        classFragments.add(ClassViewFragment.getInstance(titles[1]));

        classAdapter = new ClassAdapter(getSupportFragmentManager());
        vpClass.setAdapter(classAdapter);
        stlClass.setViewPager(vpClass, titles);
    }

    private class ClassAdapter extends FragmentPagerAdapter
    {
        public ClassAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            return classFragments.get(position);
        }

        @Override
        public int getCount()
        {
            return classFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return titles[position];
        }
    }
}
