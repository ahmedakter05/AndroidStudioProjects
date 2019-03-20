package com.example.taseen.andro3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class test3 extends AppCompatActivity {
    private TextSwitcher switcher;
    private Button btnPrev, btnNext;
    private static final String[] texts = {"I love u", "I hate u", "I kill u", "I forgive you"};
    private int mPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        btnPrev = (Button) findViewById(R.id.btnprevious);
        btnNext = (Button) findViewById(R.id.btnnext);

        switcher = (TextSwitcher) findViewById(R.id.idTextSwitcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getApplicationContext());
                textView.setTextSize(18);
                return textView;
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mPosition<texts.length-1){
                    mPosition = mPosition+1;
                    switcher.setText(texts[mPosition]);
                }
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mPosition>0){
                    mPosition = mPosition-1;
                    switcher.setText(texts[mPosition]);
                }
            }
        });



    }
}
