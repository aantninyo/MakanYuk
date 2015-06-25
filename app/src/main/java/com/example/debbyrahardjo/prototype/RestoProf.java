package com.example.debbyrahardjo.prototype;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class RestoProf extends ActionBarActivity {

    int id;
    DBDataSource DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto_prof);

        DB = new DBDataSource(this);

        id = getIntent().getIntExtra("id",0);

        DB.updateMenuFromResto(id);

        Restoran restor;
        restor = DB.getRestobyId(id);
        Bundle bundle = new Bundle();
        bundle.putString("name",restor.getNama());
        bundle.putString("alamat",restor.getAlamat());
        bundle.putStringArrayList("nama_makanan",DB.getAttMenu_nama(id));
        bundle.putStringArrayList("harga_makanan",DB.getAttMenu_harga(id));
        bundle.putStringArrayList("dekripsi_makanan",DB.getAttMenu_dekripsi(id));

        if (savedInstanceState == null) {
            RestaurantProfile resto = new RestaurantProfile();
            resto.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.header ,resto).commit();
            ListOfMenu list = new ListOfMenu();
            list.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.footer ,list)
                    .commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_screen, menu);
        return true;
    }

}
