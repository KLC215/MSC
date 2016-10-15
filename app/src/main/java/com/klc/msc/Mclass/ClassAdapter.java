package com.klc.msc.Mclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.klc.msc.R;
import com.klc.msc.db.contract.ClassContract;
import com.klc.msc.db.helper.ClassHelper;

import java.util.List;

public class ClassAdapter extends ArrayAdapter<Class> implements View.OnClickListener
{
    private int         resourceId;
    private List<Class> items;
    private Context     context;

    public ClassAdapter(Context context, int resource, List<Class> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.resourceId = resource;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout itemView;
        Class        aClass = getItem(position);

        if (convertView == null) {
            itemView = new LinearLayout(getContext());

            String         inflater       = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(inflater);

            layoutInflater.inflate(resourceId, itemView, true);
        } else {
            itemView = (LinearLayout) convertView;
        }

        com.beardedhen.androidbootstrap.AwesomeTextView tvClassName =
                (com.beardedhen.androidbootstrap.AwesomeTextView) itemView.findViewById(R.id.tvClassName);
        com.beardedhen.androidbootstrap.BootstrapButton btnEdit =
                (com.beardedhen.androidbootstrap.BootstrapButton) itemView.findViewById(R.id.btnEdit);
        com.beardedhen.androidbootstrap.BootstrapButton btnDelete =
                (com.beardedhen.androidbootstrap.BootstrapButton) itemView.findViewById(R.id.btnDelete);

        btnEdit.setTag(position);
        btnDelete.setTag(position);

        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        tvClassName.setText(aClass.getClassName());

        return itemView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btnEdit:
                break;
            case R.id.btnDelete:
                final Integer index = (Integer) v.getTag();
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Warning !");
                builder.setMessage("Are you sure to delete \n\nClass:\t" + items.get(index).getClassName() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try {
                            ClassHelper    classHelper = new ClassHelper(context);
                            SQLiteDatabase db          = classHelper.getWritableDatabase();

                            db.delete(ClassContract.ClassEntry.TABLE, ClassContract.ClassEntry._ID + " = ?", new String[]{String.valueOf(items.get(index).getId())});
                            items.remove(index.intValue());
                            notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
                break;
        }
    }
}
