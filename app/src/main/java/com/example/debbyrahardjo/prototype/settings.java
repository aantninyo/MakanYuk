package com.example.debbyrahardjo.prototype;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class settings extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent ab = new Intent (this,settings.class);
            startActivity(ab);
        }
        else if (id == R.id.action_about){
            Intent ab = new Intent (this,about_us.class);
            startActivity(ab);
        }else if (id == R.id.action_profile){
            Intent ab = new Intent(this,usrProfile.class);
            startActivity(ab);
        }

        return super.onOptionsItemSelected(item);
    }
}
