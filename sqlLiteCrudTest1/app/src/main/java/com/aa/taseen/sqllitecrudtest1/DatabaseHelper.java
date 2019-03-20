package com.aa.taseen.sqllitecrudtest1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Users.db";
    private static final String DB_TABLE = "Users_Table";

    //Columns
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String PASSWORD = "PASSWORD";

    private static final String CREATE_TABLE = "CREATE TABLE "+ DB_TABLE +" (" +
    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
    NAME + " TEXT, " +
    PASSWORD + " TEXT)";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase usersdb) {
        usersdb.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase usersdb, int oldVersion, int newVersion) {
        usersdb.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);

        onCreate(usersdb);

    }

    public boolean insertData(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PASSWORD, password);

        long result = db.insert(DB_TABLE, null, contentValues);
        System.out.println("Data: " + result);

        return result != -1;


    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+DB_TABLE+"";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }


}
