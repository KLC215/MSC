package com.klc.msc;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;


public class MSC extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Iconify.with(new FontAwesomeModule());
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        TypefaceProvider.registerDefaultIconSets();

    }
}
