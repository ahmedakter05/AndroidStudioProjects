package com.aa.taseen.kobitashomogrodemo1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private int GALLERY_REQUEST = 21;
    private Uri imageUri = null;
    private Uri downloadUri = null;

    private EditText mTitle, mDesc;
    private Button insertButton;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ProgressDialog progressDialog = new ProgressDialog(this);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog_Post");

        mSelectImage = (ImageButton) findViewById(R.id.imageButton);
        mTitle = (EditText) findViewById(R.id.editTextTitleId);
        mDesc = (EditText) findViewById(R.id.editTextDescId);
        insertButton = (Button) findViewById(R.id.insertButton);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            imageUri = data.getData();
            mSelectImage.setImageURI(imageUri);

        }
    }

    private void startPosting() {

        final String title_val = mTitle.getText().toString().trim();
        final String desc_val = mDesc.getText().toString().trim();

        if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && imageUri!=null){

            final StorageReference filepath = mStorage.child("Blog_Images").child(imageUri.getLastPathSegment());
            UploadTask uploadTask = filepath.putFile(imageUri);

            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        System.out.println("Upload " + downloadUri);
                        Toast.makeText(PostActivity.this, "Upload Success /n " + downloadUri, Toast.LENGTH_SHORT).show();

                        //DatabaseReference newPost = mDatabase.push();
                        DatabaseReference newPost = mDatabase;
                        String keyId = newPost.push().getKey();

                        newPost.child(keyId).child("id").setValue(keyId);
                        newPost.child(keyId).child("title").setValue(title_val);
                        newPost.child(keyId).child("desc").setValue(desc_val);
                        newPost.child(keyId).child("image").setValue(downloadUri.toString());

                        Toast.makeText(PostActivity.this, "Upload Success final", Toast.LENGTH_SHORT).show();

                        Intent intentback = new Intent(PostActivity.this, MainActivity.class);
                        startActivity(intentback);
                    } else {
                        Toast.makeText(PostActivity.this, "Failed, Try Again ...", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    }

                }
            });



        } else {
            Toast.makeText(this, "InCorrect, Enter Something", Toast.LENGTH_SHORT).show();
        }
    }
}