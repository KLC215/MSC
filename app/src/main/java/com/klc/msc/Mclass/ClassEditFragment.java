package com.klc.msc.Mclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klc.msc.R;

public class ClassEditFragment extends Fragment implements View.OnClickListener
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_class_edit, container, false);

        return view;
    }

    @Override
    public void onClick(View v)
    {

    }
}
