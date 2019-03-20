package com.aa.taseen.intentbackcalctext2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity  implements View.OnClickListener {

    Button sum, min, ssq;
    String ip1, ip2;
    String res = "defaultText";
    int RES_CODE = 13;

    int inip1, inip2, inres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sum = (Button) findViewById(R.id.btn_sum3);
        min = (Button) findViewById(R.id.btn_min3);
        ssq = (Button) findViewById(R.id.btn_ssquare3);

        sum.setOnClickListener(this);
        min.setOnClickListener(this);
        ssq.setOnClickListener(this);

        if(getIntent()!= null) {
            ip1 = getIntent().getExtras().getString("ip1");
            ip2 = getIntent().getExtras().getString("ip2");

            inip1 = Integer.valueOf(ip1);
            inip2 = Integer.valueOf(ip2);

        } else {
            System.out.println("No intent data found");
        }
    }

    public void callIntentBack(String res){
        Intent intent = new Intent(Main3Activity.this, MainActivity.class);
        res = "Result3: " + res;
        intent.putExtra("res", res);
        setResult(RES_CODE, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sum3:
                inres = inip1 * inip2;
                res = Integer.toString(inres);
                callIntentBack(res);
                break;
            case R.id.btn_min3:
                inres = inip1 / inip2;
                res = Integer.toString(inres);
                callIntentBack(res);
                break;
            case R.id.btn_ssquare3:
                inres = (inip1*inip1) - (inip2 * inip2);
                res = Integer.toString(inres);
                callIntentBack(res);
                break;
        }

        callIntentBack(res);


    }
}
