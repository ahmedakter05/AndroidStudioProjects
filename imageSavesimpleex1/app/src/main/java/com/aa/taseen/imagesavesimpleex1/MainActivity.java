package com.aa.taseen.imagesavesimpleex1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aa.taseen.imagesavesimpleex1.library.ImageSaver;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView addPhoto, loadedPhoto;
    EditText titleName;
    Button loadPhoto;
    private Context context;
    Bitmap imageBitmap;

    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPhoto = (ImageView) findViewById(R.id.imageView_upload);
        loadedPhoto = (ImageView) findViewById(R.id.imageView_upload);
        titleName = (EditText) findViewById(R.id.editText_title);
        loadPhoto = (Button) findViewById(R.id.btn_save);


        loadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
            }
        });

        //Bitmap image = ((BitmapDrawable)addPhoto.getDrawable()).getBitmap();

        ImageSaver imageSaver = new ImageSaver(context);
        imageSaver.setFileName("myImage.png");
        imageSaver.setDirectoryName("images");
        imageSaver.save(imageBitmap);

        ImageSaver imageSaverl = new ImageSaver(context);
        imageSaverl.setFileName("myImage.png");
        imageSaverl.setDirectoryName("images");
        Bitmap loadedImage = (Bitmap) imageSaverl.load();
        loadedPhoto.setImageBitmap(loadedImage);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Gallery Request Code
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try {
                Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                addPhoto.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        //Camera Request Code - For future use
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            addPhoto.setImageBitmap(imageBitmap);
        }
    }
}
