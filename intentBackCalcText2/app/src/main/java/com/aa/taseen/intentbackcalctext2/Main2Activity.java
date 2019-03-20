package com.aa.taseen.intentbackcalctext2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    Button sum, min, ssq;
    String ip1, ip2;
    String res = "defaultText";
    int RES_CODE = 13;

    int inip1, inip2, inres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sum = (Button) findViewById(R.id.btn_sum);
        min = (Button) findViewById(R.id.btn_min);
        ssq = (Button) findViewById(R.id.btn_ssquare);

        if(getIntent()!= null) {
            ip1 = getIntent().getExtras().getString("ip1");
            ip2 = getIntent().getExtras().getString("ip2");

            inip1 = Integer.valueOf(ip1);
            inip2 = Integer.valueOf(ip2);

        } else {
            System.out.println("No intent data found");
        }

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inres = inip1 + inip2;
                res = Integer.toString(inres);
                callIntentBack(res);
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inres = inip1 - inip2;
                res = Integer.toString(inres);
                callIntentBack(res);
            }
        });

        ssq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inres = (inip1*inip1) + (inip2 * inip2);
                res = Integer.toString(inres);
                callIntentBack(res);
            }
        });

    }

    public void callIntentBack(String res){
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        intent.putExtra("res", res);
        setResult(RES_CODE, intent);
        finish();
    }
}
