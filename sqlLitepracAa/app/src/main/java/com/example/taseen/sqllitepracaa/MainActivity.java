package com.example.taseen.sqllitepracaa;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtSurName, txtMarks;
    Button btnClick, btnRead;
    DataBaseHelper myDb;

    TextView txtResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(this);
        txtName = (EditText) findViewById(R.id.idName);
        txtSurName = (EditText) findViewById(R.id.idSurname);
        txtMarks = (EditText) findViewById(R.id.idMarks);
        btnClick = (Button) findViewById(R.id.idBtn);
        btnRead = (Button) findViewById(R.id.idRead);
        txtResult = (TextView) findViewById(R.id.idResult);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadMe();
            }
        });
    }

    private void ReadMe() {
        //
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res!=null && res.getCount()>0){
            while(res.moveToNext()){
                stringBuffer.append("ID: " + res.getString(0) + "\n");
                stringBuffer.append("Name: "+res.getString(1) + "\n");
                stringBuffer.append("SurName: " + res.getString(2) + "\n");
                stringBuffer.append("Marks: "+res.getString(3) + "\n");
                //
            }
            System.out.println("arr: " + Arrays.toString(res));
            txtResult.setText(stringBuffer.toString());
            Toast.makeText(this, "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Retrieved UnSuccessfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void ClickMe() {
        String name = txtName.getText().toString();
        String surname = txtSurName.getText().toString();
        String marks = txtMarks.getText().toString();
        Boolean results = myDb.insertData(name, surname, marks);
        if (results == true){
            Toast.makeText(MainActivity.this, "Data inserted successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data insertation unSuccessful", Toast.LENGTH_SHORT).show();
        }
    }


}
