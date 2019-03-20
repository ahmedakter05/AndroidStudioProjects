package com.aa.taseen.listviewtest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] IMAGES = {R.drawable.userp1, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, };

    String[] NAMES = {"Ahmed", "Tonni", "Taseen", "Rabeya", "Mohammad"};

    String[] DESCRIPTIONS = {"FATMAN", "Women", "SmallMan", "FATWoMAN", "FullFATMAN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return NAMES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlayout, null);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            TextView textView_name = (TextView) convertView.findViewById(R.id.textView_name);
            TextView textView_desc = (TextView) convertView.findViewById(R.id.textView_desc);

            imageView.setImageResource(IMAGES[position]);
            textView_name.setText(NAMES[position]);
            textView_desc.setText(DESCRIPTIONS[position]);

            return convertView;
        }
    }
}
