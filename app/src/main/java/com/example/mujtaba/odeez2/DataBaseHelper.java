package com.example.mujtaba.odeez2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mujtaba on 3/21/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "personal.db";
    public static final String TABLE_NAME = "CART";
    public static final String COL_1 = "name";
    public static final String COL_2 = "quantity";
    public static final String COL_3 = "image";

//    SQLiteDatabase db;
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        this.onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID string, Pass string)");
//        this.db=db;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("Select * from CART",null);
        return x;
    }
    public boolean insertData(String id, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1,id);
        cv.put(COL_2,pass);
        long result = db.insert(TABLE_NAME,null,cv);
        db.close();
        if (result == -1){
            return false ;
        }return true;
    }
}
