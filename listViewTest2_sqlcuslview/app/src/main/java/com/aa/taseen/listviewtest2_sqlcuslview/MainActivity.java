package com.aa.taseen.listviewtest2_sqlcuslview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aa.taseen.listviewtest2_sqlcuslview.Adapters.CustomAdapter;
import com.aa.taseen.listviewtest2_sqlcuslview.Model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText addName, addAge;
    Button insertData;
    ListView studentList;
    ArrayList<Student> arrayList;
    CustomAdapter customAdapter;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addName = (EditText) findViewById(R.id.add_name);
        addAge = (EditText) findViewById(R.id.add_age);
        insertData = (Button) findViewById(R.id.add_data);

        studentList = (ListView) findViewById(R.id.student_list);

        databaseHelper = new DatabaseHelper(this);
        arrayList = new ArrayList<>();

        addDatabase();

        loadDatabase();
        loadView();
        

    }

    private void addDatabase()
    {
        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addName.getText().toString();
                String age = addAge.getText().toString();
                if(!name.equals("") && databaseHelper.insertData(name, age))
                {
                    Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                    addName.setText("");
                    addAge.setText("");
                    /*finish();
                    startActivity(getIntent());*/
                    arrayList.clear();
                    loadDatabase();
                    loadView();
                }
            }
        });
    }

    private void loadView() {
        customAdapter = new CustomAdapter(this, arrayList);
        studentList.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = arrayList.get(position);
                String id1 = String.valueOf(student.getId());
                String name1 = student.getName().toString();
                String age1 = student.getAge().toString();
                Toast.makeText(MainActivity.this, "ID: " +  id1 + " \nName: " + name1 + " \nAge: "+ age1, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDatabase()
    {


        arrayList = databaseHelper.getAllData();

        if (arrayList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show();
        }
    }
}
