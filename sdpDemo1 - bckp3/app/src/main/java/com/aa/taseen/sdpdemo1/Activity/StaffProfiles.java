package com.aa.taseen.sdpdemo1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aa.taseen.sdpdemo1.R;

public class StaffProfiles extends AppCompatActivity {

    private EditText email_si, pass_si, email_su, pass_su;
    Button signin, signup;
    TextView signin_su, signup_si;
    ProgressBar progress_si, progress_su;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_profiles);

        email_si = (EditText) findViewById(R.id.editText_email_si);
        email_su = (EditText) findViewById(R.id.editText_email_su);
        pass_si = (EditText) findViewById(R.id.editText_password_si);
        pass_su = (EditText) findViewById(R.id.editText_password_su);

        signin = (Button) findViewById(R.id.button_signup);
        signup = (Button) findViewById(R.id.button_signin);

        signin_su = (TextView) findViewById(R.id.textView_signup);
        signup_si = (TextView) findViewById(R.id.textView_signin);

        progress_si = (ProgressBar) findViewById(R.id.progressBar_si);
        progress_su = (ProgressBar) findViewById(R.id.progressBar_su);

        Intent intent = new Intent(StaffProfiles.this, LoginStaffs.class);
        startActivity(intent);

    }
}
