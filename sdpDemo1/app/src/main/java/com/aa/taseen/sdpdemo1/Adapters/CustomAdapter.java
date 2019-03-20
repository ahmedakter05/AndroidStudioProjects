package com.aa.taseen.sdpdemo1.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aa.taseen.sdpdemo1.Models.Staff;
import com.aa.taseen.sdpdemo1.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Staff> {

    private Activity context;
    private List<Staff> staffList;

    public CustomAdapter(Activity context, List<Staff> staffList) {
        super(context, R.layout.sub_blood_finder_list_adapter, staffList);
        this.context = context;
        this.staffList = staffList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view  =  layoutInflater.inflate(R.layout.sub_blood_finder_list_adapter, null, true);
        Staff staff = staffList.get(position);

        TextView tv_name = (TextView) view.findViewById(R.id.textView_swname);
        TextView tv_age = (TextView) view.findViewById(R.id.textView_swage);

        tv_name.setText("Name: "+staff.getName());
        tv_age.setText("Age: " + staff.getAge());



        return view;
    }
}
