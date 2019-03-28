package com.aa.taseen.kobitashomogrodemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    String title, desc, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentReceived = getIntent();

        title = intentReceived.getStringExtra("title");
        desc = intentReceived.getStringExtra("desc");
        image = intentReceived.getStringExtra("image");

        TextView textView_title = (TextView) findViewById(R.id.textView_title);
        TextView textView_desc = (TextView) findViewById(R.id.textView_desc);
        ImageView imageViewUrl = (ImageView) findViewById(R.id.imageView_url);

        textView_title.setText(title);
        textView_desc.setText(desc);
        Glide.with(getApplicationContext()).load(image).into(imageViewUrl);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.action_share:
                String myShare = "#".concat(title).concat(" \n").concat(desc);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, myShare);
                startActivity(Intent.createChooser(share, "Share to ... "));
                break;

            case R.id.action_settings:
                Toast.makeText(this, "Under Processing", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                //finish();
                super.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
