package com.aa.taseen.sqllitecrudtest1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    Button add_data;
    EditText add_name;
    EditText add_password;
    ListView userlist;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        listItem = new ArrayList<>();

        add_data = (Button) findViewById(R.id.add_data);
        add_name = (EditText) findViewById(R.id.add_name);
        add_password = (EditText) findViewById(R.id.add_password);
        userlist = (ListView) findViewById(R.id.users_list);

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = add_name.getText().toString();
                String password = add_password.getText().toString();
                if (!name.equals("") && db.insertData(name, password)){
                    Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                    add_name.setText("");
                    add_password.setText("");
                    /*finish();
                    startActivity(getIntent());*/
                    listItem.clear();
                    viewData();

                } else {
                    Toast.makeText(MainActivity.this, "Data not Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewData();

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = userlist.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewData() {
        Cursor cursor = db.viewData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data Show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                listItem.add("\nID: " + cursor.getString(0) + "\nName: " + cursor.getString(1) + "\nPassword: " +cursor.getString(2)+"\n");
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            userlist.setAdapter(adapter);
        }
    }
}

