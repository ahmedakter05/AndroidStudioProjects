package com.aa.taseen.sdpdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aa.taseen.sdpdemo1.Adapters.CustomAdapter;
import com.aa.taseen.sdpdemo1.Models.Staff;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Test2Activity extends AppCompatActivity {

    private Button btn_insert, btn_load;
    private EditText data_name, data_age;
    private ListView listView;
    private List<Staff> staffList;
    private CustomAdapter customAdapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        saveDatatoFirebase();
    }

    private void saveDatatoFirebase() {
        String name = "Ahmed AA";
        String age = "25";

        String key = databaseReference.push().getKey();

        Staff staff = new Staff(name, age);

        databaseReference.child("user").child(key).setValue(staff);

        Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();


    }
}
