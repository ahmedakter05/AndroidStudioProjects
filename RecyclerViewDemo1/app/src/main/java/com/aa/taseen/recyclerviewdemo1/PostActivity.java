package com.aa.taseen.recyclerviewdemo1;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private int GALLERY_REQUEST = 21;
    private Uri imageUri = null;

    private EditText mTitle, mDesc;
    private Button insertButton;

    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mStorage = FirebaseStorage.getInstance().getReference();

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

        String title_val = mTitle.getText().toString().trim();
        String desc_val = mDesc.getText().toString().trim();

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
                        Toast.makeText(getApplicationContext(), "Successfully uploaded", Toast.LENGTH_SHORT).show();
                        if (downloadUri != null) {

                            String photoStringLink = downloadUri.toString(); //YOU WILL GET THE DOWNLOAD URL HERE !!!!
                            System.out.println("Upload " + photoStringLink);

                        }

                    } else {
                        // Handle failures
                        // ...
                    }

                }
            });

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "InCorrect", Toast.LENGTH_SHORT).show();
        }
    }
}
