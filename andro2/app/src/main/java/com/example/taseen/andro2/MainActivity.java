package com.example.taseen.andro2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.aa_button);
        final TextView txtView = (TextView) findViewById(R.id.aa_textView);



        String btnValue = button.getText().toString();
        System.out.println(btnValue);

        final String txtValue = txtView.getText().toString();
        System.out.println(txtValue);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText simpleEditText = (EditText) findViewById(R.id.aa_editText);
                final String strValue = simpleEditText.getText().toString();
                System.out.println(strValue);
                txtView.setText("Hello "+strValue+"\nThis is my First App");
            }
        });
    }
}
