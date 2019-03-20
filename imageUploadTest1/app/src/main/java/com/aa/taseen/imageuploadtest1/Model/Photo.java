package com.aa.taseen.imageuploadtest1.Model;

import android.graphics.Bitmap;

public class Photo {
    Bitmap photo;
    String title;

    public Photo(String title, Bitmap photo) {

        this.title = title;
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
