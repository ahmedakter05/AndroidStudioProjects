package com.aa.taseen.sdpdemo1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aa.taseen.sdpdemo1.R;

public class SignupStaffs extends AppCompatActivity implements View.OnClickListener {

    private EditText email_si, pass_si, email_su, pass_su;
    Button signin, signup;
    TextView signin_su, signup_si;
    ProgressBar progress_si, progress_su;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_staffs);

        setTitle("Staff Signup Page");

        email_su = (EditText) findViewById(R.id.editText_email_su);
        pass_si = (EditText) findViewById(R.id.editText_password_si);
        pass_su = (EditText) findViewById(R.id.editText_password_su);

        signin = (Button) findViewById(R.id.button_signin);
        signup = (Button) findViewById(R.id.button_signup);

        signin_su = (TextView) findViewById(R.id.textView_signup);
        signup_si = (TextView) findViewById(R.id.textView_signin);

        progress_si = (ProgressBar) findViewById(R.id.progressBar_si);
        progress_su = (ProgressBar) findViewById(R.id.progressBar_su);

        signup.setOnClickListener(this);
        signup_si.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_signup:
                //firebase user registration
                userRegistration();
                break;
            case R.id.textView_signin:
                Intent intent = new Intent(SignupStaffs.this, LoginStaffs.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegistration() {

        String emailaddr = email_su.getText().toString();
        String passaddr = pass_su.getText().toString();

        if(passaddr.length()<6){
            pass_su.setError("Min Length");
            pass_su.requestFocus();
            return;
        }



    }
}
