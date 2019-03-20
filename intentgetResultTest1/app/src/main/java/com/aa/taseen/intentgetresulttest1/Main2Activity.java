package com.aa.taseen.intentgetresulttest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private Button btn2;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn2 = (Button) findViewById(R.id.btn2_back);
        editText = (EditText) findViewById(R.id.editText);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = editText.getText().toString();

                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                intent.putExtra("res", res);
                setResult(2, intent);
                finish();
            }
        });
    }
}
