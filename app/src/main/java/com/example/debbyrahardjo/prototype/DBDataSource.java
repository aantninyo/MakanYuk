package com.example.debbyrahardjo.prototype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Khaerul on 6/23/2015.
 */

public class DBDataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private String[] allColoumn_jenis = DBHelper.getAllcoloumn_jenis();
    private String[] allColoumn_menu = DBHelper.getAllcoloumn_menu();
    private String[] allColoumn_restoran = DBHelper.getAllcoloumn_restoran();
    private String[] allColoumn_version = DBHelper.getAllcoloumn_version();
    private int Results;

    public DBDataSource(){};
    public DBDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }


    // ------------------------------------------------- JENIS MENU -------------------------------------------

    public void insertJenis(int id, String nama, String create_at, String update_at, String published){
        //membuat contentValue untuk memasangkan data dengan nama nama pada kolom database
        ContentValues values =  new ContentValues();
        String[] param= {String.valueOf(id) ,nama,create_at,update_at,published};

        int i;
        for(i = 0;i< allColoumn_jenis.length;i++){
            values.put(allColoumn_jenis[i],param[i]);
        }
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.insert(DBHelper.TABLE_JENIS, null,values);
        close();
    }



    public JenisMenu cursorToJenis(Cursor cursor){
        JenisMenu jenis = new JenisMenu();

        jenis.setId(cursor.getInt(0));
        jenis.setNama(cursor.getString(1));
        jenis.setCreate_at(cursor.getString(2));
        jenis.setUpdate_at(cursor.getString(3));
        jenis.setPublished(cursor.getString(4));

        return jenis;
    }



    public ArrayList<JenisMenu> getAllJenis(){
        ArrayList<JenisMenu> daftarJenis = new ArrayList<JenisMenu>();

        //Select All SQL Query
        Cursor cursor = database.query(DBHelper.TABLE_JENIS,allColoumn_jenis,null,null,null,null,null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            JenisMenu jenis = cursorToJenis(cursor);
            daftarJenis.add(jenis);
            cursor.moveToNext();
        }

        cursor.close();
        return  daftarJenis;
    }
    // ------------------------------------------------- JENIS MENU -------------------------------------------


    // -----------------------------------MENU -------------------------------------------------------
    public long insertMenu( int id,int id_restoran, int id_jenis, String nama, String dekripsi, String harga,String images, String create_at, String update_at, int published){
        //membuat contentValue untuk memasangkan data dengan nama nama pada kolom database
        ContentValues values =  new ContentValues();
        String[] param= {String.valueOf(id),String.valueOf(id_restoran), String.valueOf(id_jenis), nama, dekripsi, harga, images, create_at, update_at,String.valueOf(published)};

        values.put(allColoumn_menu[0],id);
        values.put(allColoumn_menu[1],id_restoran);
        values.put(allColoumn_menu[2],id_jenis);
        values.put(allColoumn_menu[allColoumn_menu.length - 1],published);
        int i;
        for(i = 3;i< allColoumn_menu.length - 1 ;i++){
            values.put(allColoumn_menu[i],param[i]);
        }

        long l = 0;
        try {
            open();
            l = database.insert(DBHelper.TABLE_MENU, null,values);
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;

    }

    public void updateMenuFromResto(int id_resto){

        try {
            open();
            database.execSQL("DELETE FROM "+DBHelper.TABLE_MENU+" WHERE "+DBHelper.KOLOM_ID_RESTORAN_MENU+" = '"+id_resto+"';");
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ServiceHelper SH = new ServiceHelper();
        ArrayList<MenuKu> menu = null;
        menu = SH.getMenuFromResto(id_resto);

        int i;

        for (i = 0; i < menu.size(); i++) {
            insertMenu( menu.get(i).getId(),menu.get(i).getId_restoran(),menu.get(i).getId_jenis(),menu.get(i).getNama(),menu.get(i).getDekripsi(),menu.get(i).getHarga(),menu.get(i).getImages(),menu.get(i).getCreate_at(),menu.get(i).getUpdate_at(),menu.get(i).getPublished());
        }

    }


    public MenuKu cursorToMenu(Cursor cursor){
        MenuKu menu = new MenuKu();

        menu.setId(cursor.getInt(0));
        menu.setId_restoran(cursor.getInt(1));
        menu.setId_jenis(cursor.getInt(2));
        menu.setNama(cursor.getString(3));
        menu.setDekripsi(cursor.getString(4));
        menu.setHarga(cursor.getString(5));
        menu.setImages(cursor.getString(6));
        menu.setCreate_at(cursor.getString(7));
        menu.setUpdate_at(cursor.getString(8));
        menu.setPublished(cursor.getInt(9));

        return menu;
    }

    public ArrayList<MenuKu> getAllMenuFromResto(int id_resto){
        ArrayList<MenuKu> daftarMenu = new ArrayList<MenuKu>();

        //Select All SQL Query
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_MENU + " WHERE " + DBHelper.KOLOM_ID_RESTORAN_MENU + " = " + id_resto + " ;", null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            MenuKu menu = cursorToMenu(cursor);
            daftarMenu.add(menu);
            cursor.moveToNext();
        }

        cursor.close();
        return  daftarMenu;
    }

    public ArrayList<String> getAttMenu_nama(int id){
        ArrayList<String> nama = new ArrayList<String>();

        //Select All SQL Query
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = database.rawQuery("SELECT "+DBHelper.KOLOM_NAMA_MENU+" FROM "+DBHelper.TABLE_MENU+" WHERE "+DBHelper.KOLOM_ID_RESTORAN_MENU+" = "+id+" ;",null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            String namaAdd = cursor.getString(0);
            nama.add(namaAdd);
            cursor.moveToNext();
        }

        cursor.close();
        close();
        return nama;

    }

    public ArrayList<String> getAttMenu_harga(int id){
        ArrayList<String> harga = new ArrayList<String>();

        //Select All SQL Query
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = database.rawQuery("SELECT "+DBHelper.KOLOM_HARGA_MENU+" FROM "+DBHelper.TABLE_MENU+" WHERE "+DBHelper.KOLOM_ID_RESTORAN_MENU+" = "+id+" ;",null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            String hargaAdd = cursor.getString(0);
            harga.add(hargaAdd);
            cursor.moveToNext();
        }

        cursor.close();
        close();

        return harga;

    }

    public ArrayList<String> getAttMenu_dekripsi(int id){
        ArrayList<String> desk =  new ArrayList<String>();

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Select All SQL Query
        Cursor cursor = database.rawQuery("SELECT "+DBHelper.KOLOM_DEKRIPSI_MENU+" FROM "+DBHelper.TABLE_MENU+" WHERE "+DBHelper.KOLOM_ID_RESTORAN_MENU+" = "+id+" ;",null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            String deskAdd = cursor.getString(0);
            desk.add(deskAdd);
            cursor.moveToNext();
        }

        cursor.close();

        close();

        return desk;

    }
    // -----------------------------------MENU -------------------------------------------------------


    // ------------------------------------------ RESTORAN -------------------------
    public Restoran getRestobyId(int id){
        Cursor cursor = null;
        try {
            open();
            cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABLE_RESTORAN+" WHERE "+DBHelper.KOLOM_ID_RESTORAN+" = "+id+" ;",null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        cursor.moveToFirst();
        Restoran retur = cursorToRestoran(cursor);
        close();
        return retur;
    }


    public long insertRestoran(int id,String nama,String alamat,String no_telepon,String profile,String images,String latitude,String longitude,String views,String create_at,String update_at,String published){
        //membuat contentValue untuk memasangkan data dengan nama nama pada kolom database
        ContentValues values =  new ContentValues();
        String[] param= {String.valueOf(id), nama, alamat,no_telepon,profile,images,latitude,longitude, views,create_at,update_at,published};

        values.put(allColoumn_restoran[0],id);


        int i;


        for(i = 1;i< allColoumn_restoran.length;i++){
            values.put(allColoumn_restoran[i],param[i]);
        }
        long insertResto = 0;
        try {
            open();
            insertResto =     database.insert(DBHelper.TABLE_RESTORAN, null,values);
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return insertResto;
    }

    private static final String db_create_restoran = "create table " +
            DBHelper.TABLE_RESTORAN + "(" +
            DBHelper.KOLOM_ID_RESTORAN + " integer primary key, " +
            DBHelper.KOLOM_NAMA_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_ALAMAT_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_NO_TELEPON_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_PROFILE_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_IMAGES_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_LATITUDE_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_LONGITUDE_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_VIEWS_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_CREATE_AT_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_UPDATE_AT_RESTORAN + " varchar(50) not null, " +
            DBHelper.KOLOM_PUBLISHED_RESTORAN + " varchar(50) not null);";


    public void updateRestoran(){

        ServiceHelper SH = new ServiceHelper();
        ArrayList<Restoran> resto = null;
        resto = SH.getAllResto();

        try {
            open();
            database.execSQL("DROP TABLE "+DBHelper.TABLE_RESTORAN+";");
            database.execSQL(db_create_restoran);
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int i;

        for (i = 0; i < resto.size(); i++) {
            insertRestoran(resto.get(i).getId(), resto.get(i).getNama(), resto.get(i).getAlamat(), resto.get(i).getNo_telepon(), resto.get(i).getProfile(), resto.get(i).getProfile(), resto.get(i).getLatitude(), resto.get(i).getLongitude(), resto.get(i).getViews(), resto.get(i).getCreate_at(), resto.get(i).getUpdate_at(), resto.get(i).getPublished());
        }


    }

    public Restoran cursorToRestoran(Cursor cursor){
        Restoran restoran = new Restoran();

        restoran.setId(cursor.getInt(0));
        restoran.setNama(cursor.getString(1));
        restoran.setAlamat(cursor.getString(2));
        restoran.setNo_telepon(cursor.getString(3));
        restoran.setProfile(cursor.getString(4));
        restoran.setImages(cursor.getString(5));
        restoran.setLatitude(cursor.getString(6));
        restoran.setLongitude(cursor.getString(7));
        restoran.setViews(cursor.getString(8));
        restoran.setCreate_at(cursor.getString(9));
        restoran.setUpdate_at(cursor.getString(10));
        restoran.setPublished(cursor.getString(11));

        return restoran;
    }

    public ArrayList<Restoran> getAllRestoran(){
        ArrayList<Restoran> daftarResto = new ArrayList<Restoran>();

        //Select All SQL Query

        Cursor cursor = database.query(DBHelper.TABLE_RESTORAN,allColoumn_restoran,null,null,null,null,null);
        cursor.moveToFirst();

        Restoran resto;

        while(!cursor.isAfterLast()){
            resto = cursorToRestoran(cursor);
            daftarResto.add(resto);
            cursor.moveToNext();
        }

        cursor.close();

        return  daftarResto;
    }

    public ArrayList<Restoran> findRestoran(String Keyword){
        ArrayList<Restoran> daftarResto = new ArrayList<Restoran>();


        String query = "SELECT * FROM ms_restoran r " +
                " WHERE r.id IN (SELECT id_restoran FROM ms_menu WHERE nama LIKE '%"+Keyword+ "%') " +
                "OR r.nama LIKE  '%" + Keyword + "%' or r.alamat LIKE  '%" + Keyword + "%'" ;


        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        Restoran resto;

        while(!cursor.isAfterLast()){
            resto = cursorToRestoran(cursor);
            daftarResto.add(resto);
            cursor.moveToNext();
        }

        cursor.close();

        return  daftarResto;
    }



    // ------------------------------------------ RESTORAN -------------------------


    // ---------------------------------------------- VERSION ------------------------
    public void insertVersion(String version){
        //membuat contentValue untuk memasangkan data dengan nama nama pada kolom database
        ContentValues values =  new ContentValues();

        values.put(DBHelper.KOLOM_VERSION,version);


        database.insert(DBHelper.TABLE_DB_VERSION, null,values);
    }

    // ---------------------------------------------- VERSION ------------------------



    public int getJumlahResults() {
        return this.Results;
    }
}
