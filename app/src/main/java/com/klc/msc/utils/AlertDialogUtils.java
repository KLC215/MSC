package com.klc.msc.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogUtils
{
    public static void successDialog(Context context, String message)
    {
        final AlertDialog.Builder success = new AlertDialog.Builder(context);
        success.setTitle("Success !");
        success.setMessage(message);
        success.setCancelable(false);
        success.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int i)
            {
                dialog.dismiss();
            }
        });
        success.show();
    }

    public static void errorDialog(Context context, String message)
    {
        final AlertDialog.Builder error = new AlertDialog.Builder(context);
        error.setTitle("Error !");
        error.setMessage(message);
        error.setCancelable(false);
        error.setNegativeButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        error.show();
    }

    public static void confirmDialog(final Context context, String message)
    {
        final AlertDialog.Builder confirm = new AlertDialog.Builder(context);
        confirm.setTitle("Alert !");
        confirm.setMessage(message);
        confirm.setCancelable(false);
        confirm.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                dialog.dismiss();
            }
        });
        confirm.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        confirm.show();
    }
}
