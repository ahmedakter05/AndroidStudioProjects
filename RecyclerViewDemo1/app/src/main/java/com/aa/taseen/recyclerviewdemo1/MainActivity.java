package com.aa.taseen.recyclerviewdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    int[] images = {R.drawable.alandislands, R.drawable.angola, R.drawable.armenia,
            R.drawable.australia, R.drawable.austria,R.drawable.bangladesh,
            R.drawable.belgium, R.drawable.canada, R.drawable.china,
            R.drawable.greece, R.drawable.panama, R.drawable.slovenia};

    String[] title, desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewID);

        title = getResources().getStringArray(R.array.country_name);
        desc = getResources().getStringArray(R.array.country_desc);

        myAdapter = new MyAdapter(this, title, desc, images);




    }
}
