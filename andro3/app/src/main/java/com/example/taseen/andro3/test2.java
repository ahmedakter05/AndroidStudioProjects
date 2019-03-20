package com.example.taseen.andro3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.view.View;
import android.widget.Toast;

public class test2 extends AppCompatActivity {
    Button btn;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        btn = (Button) findViewById(R.id.button);
        ratingBar = (RatingBar) findViewById(R.id.idratingBar);
    }

    public void onBtnClick(View v){
        float ratingvalue = ratingBar.getRating();
        Toast.makeText(getApplicationContext(), "Rating is" + ratingvalue, Toast.LENGTH_SHORT).show();
    }
}
