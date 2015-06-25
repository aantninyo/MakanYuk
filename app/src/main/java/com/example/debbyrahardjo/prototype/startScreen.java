package com.example.debbyrahardjo.prototype;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class startScreen extends ActionBarActivity {

    Button btn;
    DBDataSource DB;
    EditText Keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        btn = (Button) findViewById(R.id.button1);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Keyword = (EditText) findViewById(R.id.KeywordPencarian);

        DB = new DBDataSource(this);
        DB.updateRestoran();

        addListenerOnButton();

    }

    public void addListenerOnButton(){
        final Context context = this;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent in = new Intent(context,placesActivity.class);
            in.putExtra("KW",Keyword.getText().toString());
            startActivity(in);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_screen, menu);
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
            Intent st = new Intent(this,settings.class);
            startActivity(st);
        }
        else if (id == R.id.action_about){
            Intent ab = new Intent (this,about_us.class);
            startActivity(ab);
        }else if (id == R.id.action_profile){
            Intent ab = new Intent(this, usrProfile.class);
            startActivity(ab);
        }
        return super.onOptionsItemSelected(item);


    }
}
