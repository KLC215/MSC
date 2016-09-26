package com.klc.msc;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;


public class MSC extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }
}
