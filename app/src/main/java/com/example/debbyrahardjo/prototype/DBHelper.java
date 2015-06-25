package com.example.debbyrahardjo.prototype;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by debbyrahardjo on 02/06/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    public static final String TABLE_JENIS = "ms_jenis";
    public static final String KOLOM_ID_JENIS = "id";
    public static final String KOLOM_NAMA_JENIS= "nama";
    public static final String KOLOM_CREATE_AT_JENIS = "create_at";
    public static final String KOLOM_UPDATE_AT_JENIS = "update_at";
    public static final String KOLOM_PUBLISHED_JENIS = "published";

    public static final String TABLE_MENU = "ms_menu";
    public static final String KOLOM_ID_MENU = "id";
    public static final String KOLOM_ID_RESTORAN_MENU = "id_restoran";
    public static final String KOLOM_ID_JENIS_MENU = "id_jenis";
    public static final String KOLOM_NAMA_MENU = "nama";
    public static final String KOLOM_DEKRIPSI_MENU = "dekripsi";
    public static final String KOLOM_HARGA_MENU = "harga";
    public static final String KOLOM_IMAGES_MENU = "images";
    public static final String KOLOM_CREATE_AT_MENU = "create_at";
    public static final String KOLOM_UPDATE_AT_MENU = "update_at";
    public static final String KOLOM_PUBLISHED_MENU = "published";

    public static final String TABLE_RESTORAN = "ms_restoran";
    public static final String KOLOM_ID_RESTORAN = "id";
    public static final String KOLOM_NAMA_RESTORAN = "nama";
    public static final String KOLOM_ALAMAT_RESTORAN = "alamat";
    public static final String KOLOM_NO_TELEPON_RESTORAN = "no_telepon";
    public static final String KOLOM_PROFILE_RESTORAN = "profile";
    public static final String KOLOM_IMAGES_RESTORAN = "images";
    public static final String KOLOM_LATITUDE_RESTORAN = "latitude";
    public static final String KOLOM_LONGITUDE_RESTORAN = "longitude";
    public static final String KOLOM_VIEWS_RESTORAN = "views";
    public static final String KOLOM_CREATE_AT_RESTORAN = "create_at";
    public static final String KOLOM_UPDATE_AT_RESTORAN = "update_at";
    public static final String KOLOM_PUBLISHED_RESTORAN = "published";

    public static final String TABLE_DB_VERSION = "db_version";
    public static final String KOLOM_VERSION = "version";

    private static final String db_name ="makanyuk_systemdb.db";
    private static final int db_version=1;

    private static final String db_create_jenis = "create table " +
            TABLE_JENIS + "(" +
            KOLOM_ID_JENIS + " integer primary key, " +
            KOLOM_NAMA_JENIS + " varchar(50) not null, " +
            KOLOM_CREATE_AT_JENIS + " varchar(50) not null, " +
            KOLOM_UPDATE_AT_JENIS + " varchar(50) not null, " +
            KOLOM_PUBLISHED_JENIS + " varchar(50) not null); ";

    private static final String db_create_menu = "create table " +
            TABLE_MENU + "(" +
            KOLOM_ID_MENU + " integer primary key, " +
            KOLOM_ID_RESTORAN_MENU + " integer not null, " +
            KOLOM_ID_JENIS_MENU + " integer not null, " +
            KOLOM_NAMA_MENU + " varchar(50) not null, " +
            KOLOM_DEKRIPSI_MENU + " varchar(50) not null, " +
            KOLOM_HARGA_MENU + " varchar(50) not null, " +
            KOLOM_IMAGES_MENU + " varchar(50) not null, " +
            KOLOM_CREATE_AT_MENU + " varchar(50) not null, " +
            KOLOM_UPDATE_AT_MENU + " varchar(50) not null, " +
            KOLOM_PUBLISHED_MENU + " varchar(50) not null);";

    private static final String db_create_restoran = "create table " +
            TABLE_RESTORAN + "(" +
            KOLOM_ID_RESTORAN + " integer primary key, " +
            KOLOM_NAMA_RESTORAN + " varchar(50) not null, " +
            KOLOM_ALAMAT_RESTORAN + " varchar(50) not null, " +
            KOLOM_NO_TELEPON_RESTORAN + " varchar(50) not null, " +
            KOLOM_PROFILE_RESTORAN + " varchar(50) not null, " +
            KOLOM_IMAGES_RESTORAN + " varchar(50) not null, " +
            KOLOM_LATITUDE_RESTORAN + " varchar(50) not null, " +
            KOLOM_LONGITUDE_RESTORAN + " varchar(50) not null, " +
            KOLOM_VIEWS_RESTORAN + " varchar(50) not null, " +
            KOLOM_CREATE_AT_RESTORAN + " varchar(50) not null, " +
            KOLOM_UPDATE_AT_RESTORAN + " varchar(50) not null, " +
            KOLOM_PUBLISHED_RESTORAN + " varchar(50) not null);";

    private static final String db_create_version = "create table " +
            TABLE_DB_VERSION + "(" +
            KOLOM_VERSION + " varchar(10) not null);";

    public DBHelper (Context context)
    {
        super(context, db_name,null, db_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(db_create_jenis);
        db.execSQL(db_create_menu);
        db.execSQL(db_create_restoran);
        db.execSQL(db_create_version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JENIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTORAN);
        onCreate(db);
    }

    public static String[] getAllcoloumn_jenis(){
        String[] kolom = {KOLOM_ID_JENIS,  KOLOM_NAMA_JENIS, KOLOM_CREATE_AT_JENIS, KOLOM_UPDATE_AT_JENIS, KOLOM_PUBLISHED_JENIS};
        return kolom;
    }

    public static String[] getAllcoloumn_menu(){
        String[] kolom = {KOLOM_ID_MENU, KOLOM_ID_RESTORAN_MENU, KOLOM_ID_JENIS_MENU, KOLOM_NAMA_MENU, KOLOM_DEKRIPSI_MENU, KOLOM_HARGA_MENU, KOLOM_IMAGES_MENU, KOLOM_CREATE_AT_MENU, KOLOM_UPDATE_AT_MENU,KOLOM_PUBLISHED_MENU};
        return kolom;
    }

    public static String[] getAllcoloumn_restoran(){
        String[] kolom = {KOLOM_ID_RESTORAN, KOLOM_NAMA_RESTORAN, KOLOM_ALAMAT_RESTORAN, KOLOM_NO_TELEPON_RESTORAN, KOLOM_PROFILE_RESTORAN, KOLOM_IMAGES_RESTORAN, KOLOM_LATITUDE_RESTORAN, KOLOM_LONGITUDE_RESTORAN, KOLOM_VIEWS_RESTORAN, KOLOM_CREATE_AT_RESTORAN, KOLOM_UPDATE_AT_RESTORAN, KOLOM_PUBLISHED_RESTORAN};
        return kolom;
    }

    public static String[] getAllcoloumn_version(){
        String[] kolom= {KOLOM_VERSION};
        return kolom;
    }


}
