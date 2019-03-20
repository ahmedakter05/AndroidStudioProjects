package com.aa.taseen.imageuploadtest1.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aa.taseen.imageuploadtest1.Model.Photo;
import com.aa.taseen.imageuploadtest1.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Photo> arrayList;

    public CustomAdapter(Context context, ArrayList<Photo> arrayList) {
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
        //convertView = inflater.inflate(R.layout.list_custom_view_1, null);
        convertView = inflater.inflate(R.layout.list_custom_view, null);
        TextView addTitle = (TextView) convertView.findViewById(R.id.textView_photo);
        ImageView addPhoto = (ImageView) convertView.findViewById(R.id.imageView_photo);


        Photo list = arrayList.get(position);

        addTitle.setText(list.getTitle());
        addPhoto.setImageBitmap(list.getPhoto());



        return convertView;
    }
}