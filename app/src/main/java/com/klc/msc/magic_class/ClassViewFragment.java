package com.klc.msc.magic_class;

import com.klc.msc.BaseFragment;
import com.klc.msc.R;


public class ClassViewFragment extends BaseFragment
{
    private String title;

    public static ClassViewFragment getInstance(String title)
    {
        ClassViewFragment classViewFragment = new ClassViewFragment();
        classViewFragment.title = title;
        return classViewFragment;
    }

    @Override
    public int getLayoutID()
    {
        return R.layout.fragment_class_view;
    }

    @Override
    public void initView()
    {

    }
}
