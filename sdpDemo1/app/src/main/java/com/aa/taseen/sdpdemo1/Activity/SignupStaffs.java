package com.aa.taseen.sdpdemo1.Activity;

import android.content.Intent;
import android.os.PatternMatcher;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupStaffs extends AppCompatActivity implements View.OnClickListener {

    private EditText email_si, pass_si, email_su, pass_su;
    Button signin, signup;
    TextView signin_su, signup_si;
    ProgressBar progress_si, progress_su;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_staffs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Staff Signup Page");

        mAuth = FirebaseAuth.getInstance();

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
                //progress_su.setVisibility(View.VISIBLE);
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

        if (emailaddr.isEmpty()){
            email_su.setError("Enter your email Address");
            email_su.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailaddr).matches()){
            email_su.setError("Enter proper email Address");
            email_su.requestFocus();
            return;
        }

        if (passaddr.isEmpty()) {
            pass_su.setError("Enter password");
            pass_su.requestFocus();
            return;
        }


        if (passaddr.length() < 6) {
            pass_su.setError("Min Length");
            pass_su.requestFocus();
            return;
        }

        progress_su.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailaddr, passaddr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    progress_su.setVisibility(View.GONE);
                } else {
                    //Toast.makeText(getApplicationContext(), "UnSuccess", Toast.LENGTH_SHORT).show();
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        progress_su.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Already Registered", Toast.LENGTH_SHORT).show();
                        email_su.setError("Already Register");
                        email_su.requestFocus();
                        return;
                    } else {
                        Toast.makeText(getApplicationContext(), "UnSuccess: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
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
