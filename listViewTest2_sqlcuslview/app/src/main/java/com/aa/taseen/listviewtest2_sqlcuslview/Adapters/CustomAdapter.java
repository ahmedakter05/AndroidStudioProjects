package com.aa.taseen.listviewtest2_sqlcuslview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aa.taseen.listviewtest2_sqlcuslview.Model.Student;
import com.aa.taseen.listviewtest2_sqlcuslview.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Student> arrayList;

    public CustomAdapter(Context context, ArrayList<Student> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.student_details_custom_view, null);

        TextView addId = (TextView) convertView.findViewById(R.id.textView_id);
        TextView addName = (TextView) convertView.findViewById(R.id.textView_name);
        TextView addAge = (TextView) convertView.findViewById(R.id.textView_age);

        Student student = arrayList.get(position);

        addId.setText(String.valueOf(student.getId()));
        addName.setText(student.getName());
        addAge.setText(student.getAge());

        return convertView;
    }
}
