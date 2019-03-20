package com.example.taseen.andro4;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navigation);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item1:
                        Intent i = new Intent(Main2Activity.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.item2:
                        loadFragment(new FirstFragment());
                        break;
                    case R.id.item3:
                        loadFragment(new secondFragment());
                        break;
                }
                return true;
            }
        });


    }

    public void onImageClick(View view) {
        Toast.makeText(this, "Image Clicked", Toast.LENGTH_SHORT).show();
    }

    private void loadFragment(Fragment fragment) {
        ImageView imageView = (ImageView) findViewById(R.id.idimageView);
        imageView .setVisibility(View.GONE);
        TextView txtview1 = (TextView) findViewById(R.id.textViewac2);
        txtview1.setText("Activity 2");

        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout2, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
