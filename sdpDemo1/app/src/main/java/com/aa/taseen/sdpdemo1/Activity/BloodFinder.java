package com.aa.taseen.sdpdemo1.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aa.taseen.sdpdemo1.Adapters.CustomAdapter;
import com.aa.taseen.sdpdemo1.Models.Staff;
import com.aa.taseen.sdpdemo1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BloodFinder extends AppCompatActivity {

    private Button btn_insert, btn_load;
    private EditText data_name, data_age;
    private ListView listView;
    private List<Staff> staffList;
    private CustomAdapter customAdapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_finder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference("Staffs");

        staffList = new ArrayList<>();

        customAdapter = new CustomAdapter(BloodFinder.this, staffList);


        btn_insert = (Button) findViewById(R.id.button_add);
        btn_load = (Button) findViewById(R.id.button_load);
        data_name = (EditText) findViewById(R.id.editText_name);
        data_age = (EditText) findViewById(R.id.editText_age);

        listView = (ListView) findViewById(R.id.listview_bf);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDatatoFirebase();

            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatafromFirebase();

            }
        });
    }


    protected void loadDatafromFirebase(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Staff staff = dataSnapshot1.getValue(Staff.class);
                    staffList.add(staff);
                }

                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //super.onStart();
    }

    private void saveDatatoFirebase() {
        String name = data_name.getText().toString();
        String age = data_age.getText().toString();

        String key = databaseReference.push().getKey();

        Staff staff = new Staff(name, age);

        databaseReference.child(key).setValue(staff);
        finish();
        startActivity(getIntent());
        Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                super.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
