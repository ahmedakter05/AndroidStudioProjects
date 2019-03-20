package com.aa.taseen.imageuploadtest1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.aa.taseen.imageuploadtest1.Model.Memory;
import com.aa.taseen.imageuploadtest1.Model.Photo;

import java.util.ArrayList;

public class MemoryDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "memories.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "memories";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE = "image";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_IMAGE + TEXT_TYPE + " )";

    public MemoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        System.out.println("DB Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //This method has been intentionally left empty. There is only one version of the database.
    }

    public Cursor readAllMemories() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public ArrayList<Photo> getAllData()
    {
        ArrayList<Photo> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+"", null);

        while (cursor.moveToNext())
        {

            String title = cursor.getString(1);
            Bitmap image = stringToBitmap(cursor.getString(2));

            Photo photo = new Photo(title, image);

            arrayList.add(photo);

        }

        return arrayList;
    }

    public boolean addMemory(Memory memory) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, memory.getTitle());
        values.put(COLUMN_IMAGE, memory.getImageAsString());

        return db.insert(TABLE_NAME, null, values) != -1;
    }

    private static Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}