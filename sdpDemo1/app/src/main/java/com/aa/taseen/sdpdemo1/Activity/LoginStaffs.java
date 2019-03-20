package com.aa.taseen.sdpdemo1.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aa.taseen.sdpdemo1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginStaffs extends AppCompatActivity  implements View.OnClickListener {

    private EditText email_si, pass_si, email_su, pass_su;
    Button signin, signup;
    TextView signin_su, signup_si;
    ProgressBar progress_si, progress_su;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_staffs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Staff Login Page");

        mAuth = FirebaseAuth.getInstance();

        email_si = (EditText) findViewById(R.id.editText_email_si);
        email_su = (EditText) findViewById(R.id.editText_email_su);
        pass_si = (EditText) findViewById(R.id.editText_password_si);
        pass_su = (EditText) findViewById(R.id.editText_password_su);

        signin = (Button) findViewById(R.id.button_signin);
        signup = (Button) findViewById(R.id.button_signup);

        signin_su = (TextView) findViewById(R.id.textView_signup);
        signup_si = (TextView) findViewById(R.id.textView_signin);

        progress_si = (ProgressBar) findViewById(R.id.progressBar_si);
        progress_su = (ProgressBar) findViewById(R.id.progressBar_su);

        signin.setOnClickListener(this);
        signin_su.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_signin:
                //firebase
                userLogin();
                break;
            case R.id.textView_signup:
                Intent intent = new Intent(LoginStaffs.this, SignupStaffs.class);
                startActivity(intent);
                break;
        }
    }

    private void userLogin() {
        String emailaddr = email_si.getText().toString();
        String passaddr = pass_si.getText().toString();

        if (emailaddr.isEmpty()){
            email_si.setError("Enter your email Address");
            email_si.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailaddr).matches()){
            email_si.setError("Enter proper email Address");
            email_si.requestFocus();
            return;
        }

        if (passaddr.isEmpty()) {
            pass_si.setError("Enter password");
            pass_si.requestFocus();
            return;
        }


        if (passaddr.length() < 6) {
            pass_si.setError("Min Length");
            pass_si.requestFocus();
            return;
        }

        progress_si.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailaddr,passaddr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progress_si.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginStaffs.this, StaffProfiles.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Unsuccess"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                super.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
