package com.aa.taseen.recyclerviewdemo1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    DatabaseReference databaseReference;
    private List<Blog> postList;
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        postList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewFID);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Blog_Post");

        loadDatafromFirebase();






    }

    protected void loadDatafromFirebase(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Blog blog = dataSnapshot1.getValue(Blog.class);
                    postList.add(blog);

                }

                recyclerAdapter = new RecyclerAdapter(Main2Activity.this, postList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View view) {
                        Blog blog = postList.get(position);
                        Toast.makeText(getApplicationContext(), "Short: " + blog.getTitle(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(int position, View view) {
                        Blog blog = postList.get(position);
                        Toast.makeText(getApplicationContext(), "Long: " + blog.getDesc(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(Main2Activity.this, PostActivity.class);
                startActivity(intent);
                break;

            case R.id.action_profile:
                Intent intent2 = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent2);

            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_minus:
                Toast.makeText(this, "Testing", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
