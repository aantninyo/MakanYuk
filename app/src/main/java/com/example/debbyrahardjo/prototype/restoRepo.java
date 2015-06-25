package com.example.debbyrahardjo.prototype;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by debbyrahardjo on 02/06/2015.
 */
public class restoRepo {

    private DBHelper dbHelper;

    public restoRepo(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<HashMap<String,String>> getRestoList(){
        //buat koneksi read-only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                placesAttribute.KEY_nama + "," +
                placesAttribute.KEY_alamat + "," +
                placesAttribute.KEY_jarak +
                " FROM " + placesAttribute.TABLE;

        ArrayList<HashMap<String,String>> restoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> resto = new HashMap<String,String>();
                resto.put("nama",cursor.getString(cursor.getColumnIndex(placesAttribute.KEY_nama)));
                resto.put("alamat",cursor.getString(cursor.getColumnIndex(placesAttribute.KEY_alamat)));
                resto.put("jarak",cursor.getString(cursor.getColumnIndex(placesAttribute.KEY_jarak)));
                restoList.add(resto);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return restoList;
    }
}
