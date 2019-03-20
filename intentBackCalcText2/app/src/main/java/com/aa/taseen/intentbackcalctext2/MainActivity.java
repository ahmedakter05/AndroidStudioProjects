package com.aa.taseen.intentbackcalctext2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ip1, ip2;
    Button intentGo, intentGo3;
    TextView resultView;
    int REQ_CODE = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip1 = (EditText) findViewById(R.id.editText_ip1);
        ip2 = (EditText) findViewById(R.id.editText_ip2);
        intentGo = (Button) findViewById(R.id.btn_go);
        intentGo3 = (Button) findViewById(R.id.btn_go3);
        resultView = (TextView) findViewById(R.id.textView_res);

        intentGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Intent 2", Toast.LENGTH_SHORT).show();

                String ipval1 = ip1.getText().toString();
                String ipval2 = ip2.getText().toString();

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("ip1", ipval1);
                intent.putExtra("ip2", ipval2);

                startActivityForResult(intent, REQ_CODE);
            }
        });

        intentGo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Intent 3", Toast.LENGTH_SHORT).show();

                String ipval1 = ip1.getText().toString();
                String ipval2 = ip2.getText().toString();

                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("ip1", ipval1);
                intent.putExtra("ip2", ipval2);

                startActivityForResult(intent, REQ_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE && resultCode == 13){
            String res = data.getStringExtra("res");
            resultView.setText(res);
        }
    }
}
