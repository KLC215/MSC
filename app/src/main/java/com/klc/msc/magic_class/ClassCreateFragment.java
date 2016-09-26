package com.klc.msc.magic_class;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.klc.msc.BaseFragment;
import com.klc.msc.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassCreateFragment extends BaseFragment implements View.OnClickListener
{
    private String title, startDate, endDate, startTime, endTime;

    private static Calendar calendar;
    private static int year, month, dayOfMonth;

    private Spinner spClassLevel, spClassStatus;

    private EditText etClassName, etClassPrice, etClassDescription, etClassLocation,
            etClassLessonNumber, etClassWeekNumber, etClassStudentNumber;

    private com.beardedhen.androidbootstrap.BootstrapButton
            btnClassStartDate, btnClassEndDate, btnClassStartTime, btnClassEndTime,
            btnCreateClass, btnClearForm;

    private static final int START_DATE = 0, END_DATE = 1, START_TIME = 2, END_TIME = 3;

    public static ClassCreateFragment getInstance(String title)
    {
        ClassCreateFragment classCreateFragment = new ClassCreateFragment();
        classCreateFragment.title = title;
        return classCreateFragment;
    }


    @Override
    public int getLayoutID()
    {
        return R.layout.fragment_class_create;
    }

    @Override
    public void initView()
    {
        spClassLevel = (Spinner) view.findViewById(R.id.spClassLevel);
        spClassStatus = (Spinner) view.findViewById(R.id.spClassStatus);

        etClassName = (EditText) view.findViewById(R.id.etClassName);
        etClassPrice = (EditText) view.findViewById(R.id.etClassPrice);
        etClassDescription = (EditText) view.findViewById(R.id.etClassDescription);
        etClassLocation = (EditText) view.findViewById(R.id.etClassLocation);
        etClassLessonNumber = (EditText) view.findViewById(R.id.etClassLessonNumber);
        etClassWeekNumber = (EditText) view.findViewById(R.id.etClassWeekNumber);
        etClassStudentNumber = (EditText) view.findViewById(R.id.etClassStudentNumber);

        btnClassStartDate =
                (com.beardedhen.androidbootstrap.BootstrapButton) view.findViewById(R.id.btnClassStartDate);
        btnClassEndDate =
                (com.beardedhen.androidbootstrap.BootstrapButton) view.findViewById(R.id.btnClassEndDate);
        btnClassStartTime =
                (com.beardedhen.androidbootstrap.BootstrapButton) view.findViewById(R.id.btnClassStartTime);
        btnClassEndTime =
                (com.beardedhen.androidbootstrap.BootstrapButton) view.findViewById(R.id.btnClassEndTime);
        btnCreateClass =
                (com.beardedhen.androidbootstrap.BootstrapButton) view.findViewById(R.id.btnCreateClass);
        btnClearForm =
                (com.beardedhen.androidbootstrap.BootstrapButton) view.findViewById(R.id.btnClearForm);

        btnClassStartDate.setOnClickListener(this);
        btnClassEndDate.setOnClickListener(this);
        btnClassStartTime.setOnClickListener(this);
        btnClassEndTime.setOnClickListener(this);
        btnCreateClass.setOnClickListener(this);
        btnClearForm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btnClassStartDate:
                createDialog(START_DATE);
                break;
            case R.id.btnClassEndDate:
                createDialog(END_DATE);
                break;
            case R.id.btnClassStartTime:
                createDialog(START_TIME);
                break;
            case R.id.btnClassEndTime:
                createDialog(END_TIME);
                break;
            case R.id.btnCreateClass:
                // TODO: create class button, check input, compare date and time, share to facebook
                break;
            case R.id.btnClearForm:
                clearForm();
                break;
        }
    }

    private void clearForm()
    {
        // TODO: clear form button
    }

    /**
     * Handle DatePickerDialog and TimePickerDialog
     *
     * @param id Stage id ( START_DATE, END_DATE, START_TIME, END_TIME )
     */
    private void createDialog(int id)
    {
        switch (id) {
            case START_DATE:
                calendar = Calendar.getInstance();
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        startDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                        btnClassStartDate.setText(startDate);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case END_DATE:
                calendar = Calendar.getInstance();
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        endDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                        btnClassEndDate.setText(endDate);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case START_TIME:
                calendar = Calendar.getInstance();
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        startTime = minute == 0
                                    ? hourOfDay + ":" + minute + "0"
                                    : hourOfDay + ":" + minute;
                        btnClassStartTime.setText(startTime);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                break;
            case END_TIME:
                calendar = Calendar.getInstance();
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        endTime = minute == 0
                                  ? hourOfDay + ":" + minute + "0"
                                  : hourOfDay + ":" + minute;
                        btnClassEndTime.setText(endTime);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                break;
        }
    }
}
