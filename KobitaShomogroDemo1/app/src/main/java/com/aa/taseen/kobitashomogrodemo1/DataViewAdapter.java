package com.aa.taseen.kobitashomogrodemo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

class DataViewAdapter extends BaseAdapter {

    Context context;
    List<DataModel> listData;

    public DataViewAdapter(Context context, List<DataModel> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_row, null);

        TextView title_val = (TextView) convertView.findViewById(R.id.title);
        TextView desc_val = (TextView) convertView.findViewById(R.id.desc);
        TextView id_val = (TextView) convertView.findViewById(R.id.duration);
        ImageView image_val = (ImageView) convertView.findViewById(R.id.list_image);

        DataModel dataModel = listData.get(position);

        title_val.setText(dataModel.getTitle());
        desc_val.setText(dataModel.getDesc());
        id_val.setText(dataModel.getId());
        Glide.with(context).load(dataModel.getImage()).into(image_val);


        return convertView;
    }
}
