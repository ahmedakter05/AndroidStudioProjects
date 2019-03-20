package com.aa.taseen.listviewtest2_sqlcuslview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aa.taseen.listviewtest2_sqlcuslview.Model.Student;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String database_name = "Students.db";
    private static final String table_name = "students_detail";
    private static final String col_id = "_Id";
    private static final String col_name = "Name";
    private static final String col_age = "Age";
    private static final int version = 1;
    private static final String create_table = "CREATE TABLE "+table_name+"( "+col_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+col_name+" VARCHAR(255), "+col_age+" INTEGER ); ";



    public DatabaseHelper(Context context) {
        super(context, database_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name+"");
        onCreate(db);

    }

    public boolean insertData(String name, String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_name, name);
        contentValues.put(col_age, age);

        long result = db.insert(table_name, null, contentValues);
        System.out.println("Data: " + result);

        return result != -1;


    }




    public ArrayList<Student> getAllData()
    {
        ArrayList<Student> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+table_name+"", null);

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);

            Student student = new Student(id, name, age);

            arrayList.add(student);

        }

        return arrayList;
    }
}
