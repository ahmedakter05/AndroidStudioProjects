package com.example.taseen.andro3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.edit_text);
        text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String myvar = text.getText().toString();
                System.out.println(myvar);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), myvar, duration);
                toast.show();
                //or Toast.makeText(context, text, duration).show();
            }
        });

        final Button btnToast = (Button) findViewById(R.id.btn_toast);
        btnToast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String myvar = "Hurrah, Alas i'm done";
                //System.out.println(myvar);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), myvar, duration);
                toast.show();
                //or Toast.makeText(context, text, duration).show();
            }
        });

        Button btn = (Button) findViewById(R.id.button2);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.idratingBar);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float ratingvalue = ratingBar.getRating();
                Toast.makeText(getApplicationContext(), "Rating is" + ratingvalue, Toast.LENGTH_SHORT).show();
            }
        });



    }
}
