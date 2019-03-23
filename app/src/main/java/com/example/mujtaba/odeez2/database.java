package com.example.mujtaba.odeez2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mujtaba on 3/22/2018.
 */

public class database extends SQLiteAssetHelper {
    private static final String DB_name = "mycart.db";
    public database(Context context) {
        super(context,DB_name,null,1);
    }

    public List<testitems> getCarts(Context context)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlselect = {"ID","price","quantity","imageURL"};
        String sqltables ="cartitems";
        qb.setTables(sqltables);
        Cursor res = qb.query(db,sqlselect,null,null,null,null,null);
        final List<testitems> result = new ArrayList<>();
        if (res!=null && res.getCount()>0) {
            while (res.moveToNext()) {
                result.add(new testitems(res.getString(0),res.getInt(1),res.getString(2),res.getString(3)));
            }
        }else {
            Toast.makeText(context, "SORRY! Your Cart seems to be Empty", Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    public void addtocart(testitems TT,Context context){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("INSERT INTO cartitems (ID,price,quantity,imageURL) VALUES('%s','%s','%s','%s');",TT.getProductName(),  TT.getProductPrice(),TT.getProductQuantity(),TT.getProductURL());

        db.execSQL(query);
    }

    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("DELETE FROM cartitems");
        db.execSQL(query);
    }
    public void deletefrom(String x){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("DELETE FROM cartitems WHERE ID='"+x+"'");
        db.execSQL(query);
    }


}
