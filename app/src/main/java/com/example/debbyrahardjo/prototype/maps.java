package com.example.debbyrahardjo.prototype;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class maps extends ActionBarActivity implements RoutingListener {

    protected GoogleMap googleMap;
    protected LatLng start;
    protected LatLng end;
    GetMyLocation GML;
    Restoran restor;

    int id;
    DBDataSource DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        try {
            DB = new DBDataSource(this);

            id = getIntent().getIntExtra("id",0);

            DB.updateMenuFromResto(id);

            restor = DB.getRestobyId(id);
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }

            //1 SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            GML = new GetMyLocation(maps.this);
            //1 googleMap = fm.getMap();
            googleMap.setMyLocationEnabled(true);

            if(GML.canGetLocation) {
                CameraUpdate MyLocation = CameraUpdateFactory.newLatLngZoom(new LatLng(GML.getLatitude(), GML.getLongitude()), 15);
                googleMap.animateCamera(MyLocation);


                start = new LatLng(GML.getLatitude(),GML.getLongitude());
                end = new LatLng(Double.parseDouble(restor.getLatitude()),Double.parseDouble(restor.getLongitude()));

                Routing routing = new Routing(Routing.TravelMode.DRIVING);
                routing.registerListener(this);
                routing.execute(start, end);


            }
            else {
                GML.showSettingAlert();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_screen, menu);
        return true;
    }

    @Override
    public void onRoutingFailure() {
        // The Routing request failed
    }

    @Override
    public void onRoutingStart() {
        // The Routing Request starts
    }

    @Override
    public void onRoutingSuccess(PolylineOptions mPolyOptions, Route route) {
        PolylineOptions polyOptions = new PolylineOptions();
        polyOptions.color(Color.BLUE);
        polyOptions.width(10);
        polyOptions.addAll(mPolyOptions.getPoints());
        googleMap.addPolyline(polyOptions);

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(start);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue));
        googleMap.addMarker(options);


        // End marker
        options = new MarkerOptions();
        options.position(end);
        options.title(restor.getNama()+"/n"+restor.getAlamat());
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green));
        googleMap.addMarker(options);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent st = new Intent(this,settings.class);
            startActivity(st);
        }
        else if (id == R.id.action_about){
            Intent ab = new Intent (this,about_us.class);
            startActivity(ab);
        }

        return super.onOptionsItemSelected(item);
    }
}
