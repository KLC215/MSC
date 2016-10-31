package com.klc.msc.Mclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.klc.msc.R;
import com.klc.msc.db.MClass;
import com.klc.msc.db.contract.MSC_Contract;
import com.klc.msc.db.helper.MSC_Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.klc.msc.R.id.btnDelete;
import static com.klc.msc.R.id.btnEdit;

public class ClassAdapter extends ArrayAdapter<MClass> implements View.OnClickListener,
                                                                  Filterable
{
    private int          resourceId;
    private List<MClass> items;
    private List<MClass> searchList;
    private ClassFilter classFilter = new ClassFilter();
    private Context        context;
    private Fragment       fragment;
    private LayoutInflater layoutInflater;

    private Bundle editBundle;

    public ClassAdapter(Context context, int resource, List<MClass> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.resourceId = resource;
        this.items = objects;
        this.searchList = objects;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return searchList.size();
    }

    @Nullable
    @Override
    public MClass getItem(int position)
    {
        return searchList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;

        try {
            if (convertView == null) {

                convertView = layoutInflater.inflate(R.layout.item_class, null);
                viewHolder = new ViewHolder();

                viewHolder.tvClassName =
                        (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.tvClassName);
                viewHolder.tvLocation =
                        (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.tvLocation);
                viewHolder.tvDate =
                        (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.tvDate);
                viewHolder.tvTime =
                        (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.tvTime);
                viewHolder.btnEdit =
                        (com.beardedhen.androidbootstrap.BootstrapButton) convertView.findViewById(R.id.btnEdit);
                viewHolder.btnDelete =
                        (com.beardedhen.androidbootstrap.BootstrapButton) convertView.findViewById(R.id.btnDelete);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.btnEdit.setTag(position);
            viewHolder.btnDelete.setTag(position);

            viewHolder.btnEdit.setOnClickListener(this);
            viewHolder.btnDelete.setOnClickListener(this);

            viewHolder.tvClassName.setText(searchList.get(position).getName());
            viewHolder.tvLocation.setText(searchList.get(position).getLocation());
            viewHolder.tvDate.setText(searchList.get(position).getStartDate() + " - " + searchList.get(position).getEndDate());
            viewHolder.tvTime.setText(searchList.get(position).getStartTime() + " - " + searchList.get(position).getEndTime());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case btnEdit:
                fragment = new ClassEditFragment();
                editBundle = new Bundle();
                editBundle.putParcelable("classData", items.get((int) v.getTag()));
                fragment.setArguments(editBundle);
                replaceFragment(fragment);
                break;
            case btnDelete:
                final Integer index = (Integer) v.getTag();
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Warning !");
                builder.setMessage("Are you sure to delete \n\nMClass:\t" + items.get(index).getName() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try {
                            //ClassHelper    classHelper = new ClassHelper(context);
                            //SQLiteDatabase db          = classHelper.getWritableDatabase();
                            MSC_Helper     mscHelper = new MSC_Helper(context);
                            SQLiteDatabase db        = mscHelper.getWritableDatabase();

                            db.delete(
                                    MSC_Contract.MSCEntry.TABLE_CLASS,
                                    MSC_Contract.MSCEntry.COL_CLASS_ID + " = ?",
                                    new String[]{String.valueOf(items.get(index).getId())}
                            );

                            items.remove(index.intValue());
                            notifyDataSetChanged();
                            db.close();
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

    private void replaceFragment(Fragment fragment)
    {
        FragmentActivity    aty         = (FragmentActivity) (context);
        FragmentTransaction transaction = aty.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flClassRoot, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private class ViewHolder
    {
        com.beardedhen.androidbootstrap.AwesomeTextView tvClassName;
        com.beardedhen.androidbootstrap.AwesomeTextView tvLocation;
        com.beardedhen.androidbootstrap.AwesomeTextView tvDate;
        com.beardedhen.androidbootstrap.AwesomeTextView tvTime;
        com.beardedhen.androidbootstrap.BootstrapButton btnEdit;
        com.beardedhen.androidbootstrap.BootstrapButton btnDelete;
    }

    public Filter getClassFilter()
    {
        return classFilter;
    }

    private class ClassFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            String             filter   = constraint.toString().toLowerCase();
            FilterResults      results  = new FilterResults();
            final List<MClass> templist = items;

            int                     count = templist.size();
            final ArrayList<MClass> nList = new ArrayList<>(count);

            MClass filterableClass;

            for (int i = 0; i < count; i++) {
                filterableClass = templist.get(i);
                if (filterableClass.getName().toLowerCase(Locale.getDefault()).contains(filter)) {
                    nList.add(filterableClass);
                }
            }
            results.values = nList;
            results.count = nList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            searchList = (List<MClass>) results.values;
            notifyDataSetChanged();
        }
    }
}
