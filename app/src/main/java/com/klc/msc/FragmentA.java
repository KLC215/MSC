package com.klc.msc;

import android.widget.TextView;


/**
 *  Create / Update / Delete
 */
public class FragmentA extends BaseFragment
{
    private String title;
    private TextView txt;

    public static FragmentA getInstance(String title)
    {
        FragmentA fa = new FragmentA();
        fa.title = title;
        return fa;
    }

    @Override
    public int getLayoutID()
    {
        return R.layout.fragment_a;
    }

    @Override
    public void initView()
    {
        txt = (TextView) view.findViewById(R.id.msg);
        txt.setText(title);
    }
}
