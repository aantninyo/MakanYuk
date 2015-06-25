package com.example.debbyrahardjo.prototype;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aditya Bintang P on 21/06/2015.
 * INI BELUM SELESAI YA BROOOOO OJO LALIIIII
 */
public class ServiceHelper {

    private String urlJenisMenu = "http://makanyuk.sevenmediatech.com/index.php/api/jenis";
    private String urlResto = "http://makanyuk.sevenmediatech.com/index.php/api/restoran";

    public ServiceHelper(){}


    public String getRequest(String url){
        String req ="";
        HttpClient client = new DefaultHttpClient();
        HttpGet requestUrl = new HttpGet(url);


        BufferedReader reader;
        try {
            HttpResponse response = client.execute(requestUrl);
            req = request(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return req;




/*
        HttpResponse response = null;
        try {
            response = client.execute(requestUrl);
            req = request(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

*/

    }

    public String request(HttpResponse response){
        String result ="";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));

            StringBuilder str = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null){
                str.append(line + "\n");
            }

            result = str.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }


    public ArrayList<Restoran> getAllResto()
    {
        JSONArray jArray;
        String jsonResult;


        ArrayList<Restoran> resto = new ArrayList<Restoran>();

        int id;
        String nama;
        String alamat;
        String no_telepon;
        String profile;
        String images;
        String latitude;
        String longitude;
        String views = "1";
        String create_at;
        String update_at;
        String published;


        try {
            jsonResult = getRequest(urlResto);
            jArray = new JSONArray(jsonResult);


            int i;
            for(i = 0;i < jArray.length() ;i++){
                Restoran RestoAdd = new Restoran();
                id = jArray.getJSONObject(i).getInt("id_restoran");
                nama = jArray.getJSONObject(i).getString("nama_restoran");
                alamat = jArray.getJSONObject(i).getString("alamat_restoran");
                no_telepon = jArray.getJSONObject(i).getString("no_telepon_restoran");
                profile = jArray.getJSONObject(i).getString("profile_restoran");
                images = jArray.getJSONObject(i).getString("images_restoran");
                latitude = jArray.getJSONObject(i).getString("latitude_restoran");
                longitude = jArray.getJSONObject(i).getString("longitude_restoran");
                create_at = jArray.getJSONObject(i).getString("create_at_restoran");
                update_at = jArray.getJSONObject(i).getString("update_at_restoran");
                published = jArray.getJSONObject(i).getString("published_restoran");
                RestoAdd.setId(id);
                RestoAdd.setNama(nama);
                RestoAdd.setViews(views);
                RestoAdd.setAlamat(alamat);
                RestoAdd.setNo_telepon(no_telepon);
                RestoAdd.setProfile(profile);
                RestoAdd.setImages(images);
                RestoAdd.setLatitude(latitude);
                RestoAdd.setLongitude(longitude);
                RestoAdd.setCreate_at(create_at);
                RestoAdd.setUpdate_at(update_at);
                RestoAdd.setPublished(published);

                resto.add(RestoAdd);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return resto;
    }

    public ArrayList<JenisMenu> getAllJenis()
    {
        JSONArray jArray;
        String jsonResult;


        ArrayList<JenisMenu> jenis = null;

        int id;
        String nama;
        String create_at;
        String update_at;
        String published;


        try {
            jsonResult = getRequest(urlJenisMenu);
            jArray = new JSONArray(jsonResult);

            JenisMenu JenisAdd = null;
            int panjang = jArray.length();
            int i;
            for(i = 0;i < panjang;i++){
                id = jArray.getJSONObject(i).getInt("id_restoran");JenisAdd.setId(id);
                nama = jArray.getJSONObject(i).getString("nama_restoran");JenisAdd.setNama(nama);
                create_at = jArray.getJSONObject(i).getString("create_at_restoran");JenisAdd.setCreate_at(create_at);
                update_at = jArray.getJSONObject(i).getString("update_at_restoran");JenisAdd.setUpdate_at(update_at);
                published = jArray.getJSONObject(i).getString("published_restoran");JenisAdd.setPublished(published);


                jenis.add(JenisAdd);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jenis;
    }


    public ArrayList<MenuKu> getMenuFromResto(int id_resto){
        JSONObject jObject;
        String jsonResult;


        ArrayList<MenuKu> menu = new ArrayList<MenuKu>();

        int id;
        int id_restoran = id_resto;
        int id_jenis;
        String nama;
        String dekripsi;
        String harga;
        String images;
        String create_at;
        String update_at;
        int published;

        try {
            jsonResult = getRequest(urlResto + "/" +id_resto );
            jObject = new JSONObject(jsonResult);
            JSONArray ArrayOfMenu = jObject.getJSONArray("menu");


            int i;
            for(i = 0;i < ArrayOfMenu.length() ;i++){
                MenuKu menuAdd = new MenuKu();
                id = ArrayOfMenu.getJSONObject(i).getInt("id_menu");
                id_jenis = ArrayOfMenu.getJSONObject(i).getInt("id_jenis");
                nama = ArrayOfMenu.getJSONObject(i).getString("nama_menu");
                dekripsi = ArrayOfMenu.getJSONObject(i).getString("deskripsi_menu");
                harga = ArrayOfMenu.getJSONObject(i).getString("harga_menu");
                images = "Masih Ksong";
                create_at = ArrayOfMenu.getJSONObject(i).getString("create_at_menu");
                update_at = ArrayOfMenu.getJSONObject(i).getString("update_at_menu");
                published = ArrayOfMenu.getJSONObject(i).getInt("published_menu");
                menuAdd.setId(id);
                menuAdd.setId_jenis(id_jenis);
                menuAdd.setId_restoran(id_restoran);
                menuAdd.setNama(nama);
                menuAdd.setDekripsi(dekripsi);
                menuAdd.setHarga(harga);
                menuAdd.setCreate_at(create_at);
                menuAdd.setUpdate_at(update_at);
                menuAdd.setPublished(published);
                menuAdd.setImages(images);

                menu.add(menuAdd);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return menu;
    }

}
