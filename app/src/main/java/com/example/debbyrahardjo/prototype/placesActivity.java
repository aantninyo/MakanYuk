package com.example.debbyrahardjo.prototype;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by debbyrahardjo on 21/04/2015.
 */
public class placesActivity extends ActionBarActivity {


    //attribute tiap item list (restaurant/tempat makan)
    public class placesAttribute{
        int id;
        String name;
        String address;
        String distance;
        String time;
        String ImagesResto;
    }

    TextView Result;
    TextView KeyPen;

    ArrayList<Restoran> Array;
    DBDataSource DB;
    placesAdapter placesListAdapter;
    String KeyWord;

    private int getIdfromPosition(int positon){
        return Array.get(positon).getId();
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);
        DB = new DBDataSource(this);
        KeyPen = (TextView) findViewById(R.id.CKeyword);
        Result = (TextView) findViewById(R.id.CResults);
        try {
            Intent intent = getIntent();
            KeyWord = intent.getStringExtra("KW");
            DB.open();
            Array = DB.findRestoran(KeyWord);
            KeyPen.setText("Keyword : "+ KeyWord);
            //if(DB.getJumlahResults()==0){
            //    Result.setText("Hasil Pencarian : Tidak Ada Data");
            //}
            //else {
            //    Result.setText("Hasil Pencarian : " + String.valueOf(DB.getJumlahResults()) + " Data Ditemukan ");
            //}
            //
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        //adapter listview di place_list
        placesListAdapter = new placesAdapter();
        ListView place = (ListView) findViewById(R.id.listOfPlace);
        place.setAdapter(placesListAdapter);

        final Context context = this;
        place.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick (AdapterView<?> parent,View view,int position,long id){
                Intent it = new Intent(context,popupDialog.class);
                it.putExtra("id",getIdfromPosition(position));
                startActivity(it);
            }
        });


    }


    public class placesAdapter extends BaseAdapter{
        List<placesAttribute> placesList = getDataForListView();
        public int getCount(){
            return placesList.size();
        }

        public placesAttribute getItem(int arg0){
            return placesList.get(arg0);
        }

        public long getItemId(int arg0){
            return arg0;
        }

        //nampilin activity_placelist + isi
        public View getView(int arg0, View arg1, ViewGroup arg2){
            if(arg1==null){
                LayoutInflater inflater = (LayoutInflater) placesActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                arg1 = inflater.inflate(R.layout.activity_placelist,arg2,false);
            }

            int loader = R.drawable.user_profile;

           // ImageView image = (ImageView) findViewById(R.id.usr_prof);
           // String imageURL = "http://makanyuk.sevenmediatech.com/assets/images/";
            //ImageLoader imgLoader = new ImageLoader(getApplicationContext());

            TextView name = (TextView)  arg1.findViewById(R.id.restaurant);
            TextView address = (TextView) arg1.findViewById(R.id.address);
            TextView distance = (TextView) arg1.findViewById(R.id.distance);
            TextView time = (TextView) arg1.findViewById(R.id.time);

            placesAttribute places = placesList.get(arg0);
            if(places.ImagesResto!=null){
            //    imgLoader.DisplayImage(imageURL,loader,image,"cantik.jpg");
            }
            name.setText(places.name);
            address.setText(places.address);
            distance.setText(places.distance);
            time.setText(places.time);

            return arg1;
        }

        public placesAttribute getPlacesAttribute(int pos){
            return placesList.get(pos);
        }

    }

    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_start_screen,menu);
        return true;
    }

    //buat ngisi list
    public List<placesAttribute> getDataForListView(){
        List<placesAttribute> placesList = new ArrayList<placesAttribute>();
        Restoran resto;
        if(Array.size()!=0){
            for(int i=0; i < Array.size() ; i++){
                placesAttribute place = new placesAttribute();
                resto = Array.get(i);
                place.name = resto.getNama();
                place.address =resto.getAlamat();
                place.distance = "2,"+i+" KM ";
                place.time = "2 Minutes";
                place.ImagesResto = resto.getImages();
                place.id = resto.getId();

                placesList.add(place);
            }
        }

        return placesList;
    }

}

