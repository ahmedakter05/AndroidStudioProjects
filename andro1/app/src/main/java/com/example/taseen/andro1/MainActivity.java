package com.example.taseen.andro1;

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


        final TextView txtViewsum = (TextView) findViewById(R.id.textView_sum);
        //final String txtValuesum = txtViewsum.getText().toString();
        Button button2 = (Button) findViewById(R.id.button_sum);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView txtEditn1 = (TextView) findViewById(R.id.editText_num1);
                String txtValuen1 = txtEditn1.getText().toString();
                int txtEditn11 = Integer.parseInt(txtValuen1);
                System.out.println(txtEditn11);

                TextView txtEditn2 = (TextView) findViewById(R.id.editText_num2);
                String txtValuen2 = txtEditn2.getText().toString();
                int txtEditn22 = Integer.parseInt(txtValuen2);
                System.out.println(txtEditn22);

                int sum = txtEditn11 + txtEditn22;
                System.out.println("Sum: " + sum);
                System.out.println(sum);
                txtViewsum.setText("Hello, The Sum is:  "+sum+"\nThank You");
            }
        });

    }
}
