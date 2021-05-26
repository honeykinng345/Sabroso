package com.sabroso.qms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "Users.db";
    public final static String TABLE_NAME = "CustomerList";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);



        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) return false;
        else return true;
    }



    public Cursor getData(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        String query="SELECT * FROM "+TABLE_NAME+" WHERE ID='"+id+"'";
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }

/*
    public boolean updateData(String email, String name, String address, String city, String country, String state, String latitude, String longitude, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_6, address);
        contentValues.put(COL_7, city);
        contentValues.put(COL_8, country);
        contentValues.put(COL_9, state);
        contentValues.put(COL_10, latitude);
        contentValues.put(COL_11, longitude);
        contentValues.put(COL_12, phone);
        db.update(TABLE_NAME, contentValues, "EMAIL=?", new String[]{email});
        return true;
    }
*/

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return res;
    }

    public void removeAll()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();



    }
}