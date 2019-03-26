package com.aa.taseen.recyclerviewdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    int[] images = {R.drawable.Aland_Islands_128, R.drawable.Angola_Flag_128, R.drawable.Armenia_Flag_128,
            R.drawable.Australia_Flag_128, R.drawable.Austria_Flag_128,R.drawable.Bangladesh_Flag,
            R.drawable.Belgium_Flag_128, R.drawable.Canada_Flag_128, R.drawable.China_Flag_128,
            R.drawable.Greece_Flag_128, R.drawable.Panama_Flag_icon, R.drawable.Slovenia_Flag_icon};

    String[] title, desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);

        title = getResources().getStringArray(R.array.country_name);
        desc = getResources().getStringArray(R.array.country_desc);

        myAdapter = new MyAdapter(this, title, desc, images);




    }
}
