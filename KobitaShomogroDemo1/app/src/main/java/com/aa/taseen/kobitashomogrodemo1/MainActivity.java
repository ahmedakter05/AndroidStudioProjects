package com.aa.taseen.kobitashomogrodemo1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    boolean firstTime = true;

    DatabaseReference databaseReference;

    private List<DataModel> dataList;
    ListView listViewId;
    DataViewAdapter dataViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listViewId = (ListView) findViewById(R.id.list);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Blog_Post");

        dataList = new ArrayList<>();

        loadDatafromFirebase();


    }

    private void loadDatafromFirebase() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot childdataSnapshot : dataSnapshot.getChildren()){
                    DataModel dataModel = childdataSnapshot.getValue(DataModel.class);
                    //String id_key = childdataSnapshot.getKey();
                    dataList.add(dataModel);
                    //System.out.println(id_key);
                }
                //Toast.makeText(getApplicationContext(), "LoadDatabase", Toast.LENGTH_SHORT).show();

                loadView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadView() {
        //Toast.makeText(this, "LoadView", Toast.LENGTH_SHORT).show();
        dataViewAdapter = new DataViewAdapter(this, dataList);
        listViewId.setAdapter(dataViewAdapter);
        dataViewAdapter.notifyDataSetChanged();

        listViewId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel2 = dataList.get(position);
                String id1 = String.valueOf(dataModel2.getId());
                String title1 = String.valueOf(dataModel2.getTitle());
                String desc1 = String.valueOf(dataModel2.getDesc());
                String image1 = String.valueOf(dataModel2.getImage());

                //Toast.makeText(MainActivity.this, "ID: " +  id1, Toast.LENGTH_SHORT).show();

                Intent intentDetail = new Intent(MainActivity.this, DetailActivity.class);
                intentDetail.putExtra("id", id1);
                intentDetail.putExtra("title", title1);
                intentDetail.putExtra("desc", desc1);
                intentDetail.putExtra("image", image1);
                startActivity(intentDetail);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.action_add:
                Intent intentpost = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intentpost);
                break;

            case R.id.action_settings:
                Toast.makeText(this, "Nothing to Share", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
