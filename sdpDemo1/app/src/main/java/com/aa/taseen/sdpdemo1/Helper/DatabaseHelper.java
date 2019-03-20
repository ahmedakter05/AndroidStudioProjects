package com.aa.taseen.sdpdemo1.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "SDP_DW.db";
    private static final String DB_TABLE = "Staff_Profiles";

    //column
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String AGE = "AGE";
    private static final String DES = "DESIGNATION";
    private static final String BASE = "BASE";
    private static final String MOBILE = "MOBILE";
    private static final String BLOOD = "BLOOD_GROUP";
    private static final String EMERGENCY = "EMERGENCY_NUMBER";
    private static final String OTHERS = "OTHER_DETAILS";
    private static final String PHOTO = "PHOTO";


    private static final String CREATE_TABLE = "CREATE TABLE "+DB_TABLE+" (" +
            ""+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+NAME+" VARCHAR(255), " +
            ""+AGE+" VARCHAR(255), " +
            ""+DES+" VARCHAR(255), " +
            ""+BASE+" VARCHAR(255), " +
            ""+MOBILE+" VARCHAR(255), " +
            ""+BLOOD+" VARCHAR(255), " +
            ""+EMERGENCY+" VARCHAR(255), " +
            ""+OTHERS+" TEXT, " +
            ""+PHOTO+" VARCHAR(255));";

    private static final int VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE + "");
    }

    public boolean insertData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, "Ahmed");
        contentValues.put(AGE, "28");
        contentValues.put(DES, "Technical Quality Specialist");
        contentValues.put(BASE, "Shyamoli R/O");
        contentValues.put(MOBILE, "01712203145");
        contentValues.put(BLOOD, "AB+");
        contentValues.put(PHOTO, "dcworker");

        long result = db.insert(DB_TABLE, null, contentValues);
        System.out.println("Data: " + result);

        return result != -1;


    }

    public String getStaffProfile(int id) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String namee = null;
        try {
            cursor = db.rawQuery("SELECT * FROM "+DB_TABLE+" WHERE ID=?", new String[] {id + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                namee = cursor.getString(cursor.getColumnIndex(NAME));
            }
            return namee;
        }finally {
            cursor.close();
        }
    }


}
