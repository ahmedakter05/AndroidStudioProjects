package com.aa.taseen.sdpdemo1.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aa.taseen.sdpdemo1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

public class StaffProfiles extends AppCompatActivity {

    private EditText email_si, pass_si, email_su, pass_su;
    Button signin, signup;
    TextView signin_su, signup_si;
    ProgressBar progress_si, progress_su;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_profiles);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Staff Profiles");

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fuser = mAuth.getCurrentUser();

        ImageView imageView = (ImageView) findViewById(R.id.imageView_pfimage);
        TextView tv_name = (TextView) findViewById(R.id.textView_pfname);
        TextView tv_email = (TextView) findViewById(R.id.textView_pfemail);

        if(fuser != null){

            Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();

            if (fuser.getDisplayName()==null || fuser.getPhotoUrl()==null){
                //
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName("Ahmed Akter")
                        .setPhotoUri(Uri.parse("https://cdn.pixabay.com/photo/2017/08/20/23/04/girl-2663559_960_720.jpg"))
                        .build();

                fuser.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(StaffProfiles.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(StaffProfiles.this, "Pf Update not Success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            if (!(fuser.getDisplayName()==null && fuser.getPhotoUrl()==null)){
                String pfname = fuser.getDisplayName();
                String pfemail = fuser.getEmail();
                Uri pfimage = fuser.getPhotoUrl();
                String uid = fuser.getUid();

                Picasso.with(StaffProfiles.this).load(pfimage).into(imageView);
                tv_name.setText(pfname);
                tv_email.setText(pfemail);

            }







        } else {
            Intent intent = new Intent(StaffProfiles.this, LoginStaffs.class);
            startActivity(intent);
        }

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
