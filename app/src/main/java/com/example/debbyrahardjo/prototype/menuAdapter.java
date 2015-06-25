package com.example.debbyrahardjo.prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya Bintang P on 22/04/2015.
 */
public class menuAdapter extends BaseAdapter {
    List<menuAttribute> placesList = getDataForListView();

    static LayoutInflater inflater;

    static ArrayList<String> nama;
    static ArrayList<String> harga;
    static ArrayList<String> dekripsi;


    public menuAdapter(){

    }

    public static void setNama(ArrayList<String> nam) {
        nama = nam;
    }

    public static void setHarga(ArrayList<String> harg) {
        harga = harg;
    }

    public static void setDekripsi(ArrayList<String> dekrips) {
        dekripsi = dekrips;
    }

    public static void setInflater(LayoutInflater inf){
        inflater = inf;
    }

    public int getCount(){
        return placesList.size();
    }

    public menuAttribute getItem(int arg0){
        return placesList.get(arg0);
    }

    public long getItemId(int arg0){
        return arg0;
    }

    //nampilin activity_placelist + isi
    public View getView(int arg0, View arg1, ViewGroup arg2){
        if(arg1==null){
            arg1 = inflater.inflate(R.layout.menu_view,arg2,false);
        }

        TextView name = (TextView)  arg1.findViewById(R.id.makanan);
        TextView price = (TextView)  arg1.findViewById(R.id.price);
        TextView desc = (TextView)  arg1.findViewById(R.id.desc);
        menuAttribute places = placesList.get(arg0);

        name.setText(places.name);
        price.setText(places.price);
        desc.setText(places.desc);

        return arg1;
    }

    public menuAttribute getPlacesAttribute(int pos){
        return placesList.get(pos);
    }

    public List<menuAttribute> getDataForListView(){
        List<menuAttribute> placesListAdd = new ArrayList<menuAttribute>();




        for(int i=0; i < nama.size(); i++){
            menuAttribute place = new menuAttribute();
            place.setName(nama.get(i));
            place.setPrice(harga.get(i));
            place.setDesc(dekripsi.get(i));
            placesListAdd.add(place);
        }

        return placesListAdd;
    }

}
