package com.klc.msc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.klc.msc.booking.BookingActivity;
import com.klc.msc.magic_class.ClassActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private com.beardedhen.androidbootstrap.BootstrapButton
            btnManageBooking, btnManageClass, btnSchedule;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView()
    {
        btnManageBooking = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.btnManageBooking);
        btnManageClass = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.btnManageClass);
        btnSchedule = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.btnSchedule);

        btnManageBooking.setOnClickListener(this);
        btnManageClass.setOnClickListener(this);
        btnSchedule.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btnManageBooking:
                intent = new Intent(MainActivity.this, BookingActivity.class);
                startActivity(intent);
                break;
            case R.id.btnManageClass:
                intent = new Intent(MainActivity.this, ClassActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSchedule:
                break;
        }
    }
}
