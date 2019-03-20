package com.aa.taseen.imageuploadtest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.aa.taseen.imageuploadtest1.Adapters.CustomAdapter;
import com.aa.taseen.imageuploadtest1.Model.Memory;
import com.aa.taseen.imageuploadtest1.Model.Photo;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    MemoryDbHelper dbHelper;
    GridView gridView;
    ArrayList<Photo> arrayList;
    CustomAdapter customAdapter;
    ListView listView;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        listView = (ListView) findViewById(R.id.listView);
        dbHelper = new MemoryDbHelper(this);

        back = (Button) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_intent = new Intent(GalleryActivity.this, MainActivity.class);
                startActivity(main_intent);
            }
        });


        loadDatabase();
        loadView();


    }

    private void loadDatabase()
    {
         arrayList = dbHelper.getAllData();

        if (arrayList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show();
        }
    }

    private void loadView() {
        customAdapter = new CustomAdapter(this, arrayList);
        listView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();

        /*studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = arrayList.get(position);
                String id1 = String.valueOf(student.getId());
                String name1 = student.getName().toString();
                String age1 = student.getAge().toString();
                Toast.makeText(MainActivity.this, "ID: " +  id1 + " \nName: " + name1 + " \nAge: "+ age1, Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
