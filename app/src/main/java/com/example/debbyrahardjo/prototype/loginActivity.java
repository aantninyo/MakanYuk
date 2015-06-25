package com.example.debbyrahardjo.prototype;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;


public class loginActivity extends ActionBarActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {


    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "Login";
    public static final int PROFILE_PIC_SIZE = 400;
    private GoogleApiClient mGoogleApiClient;

    private boolean mSignInClicked;
    private boolean mIntentInProgress;
    private ConnectionResult mConnectionResult;

    private SignInButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);

        // Button click listeners
        btnSignIn.setOnClickListener(this);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();

        //findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
}

    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    /*public void onConnectionFailed(ConnectionResult result){
        if(!mIntentInProgress && result.hasResolution()){
            try {
                mIntentInProgress = true;
                result.startResolutionForResult(this,RC_SIGN_IN);
            }catch (IntentSender.SendIntentException e){
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }*/

    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
            return;
        }

        if (!mIntentInProgress) {
            mConnectionResult = result;

            if (mSignInClicked) {
                resolveSignInError();
            }
        }
    }

    public void onConnected(Bundle connectionHint) {
        mSignInClicked = false;
        Toast.makeText(this, "User Connected :D", Toast.LENGTH_LONG).show();

        Intent in = new Intent(this,startScreen.class);
        startActivity(in);

    }

    /*public void onActivityResult(int requestCode, int responseCode, Intent intent){
        if(requestCode == RC_SIGN_IN){
            mIntentInProgress = false;
            if(!mGoogleApiClient.isConnected()){
                mGoogleApiClient.reconnect();
            }
        }
    }*/

    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    /*public void onClick(View view){
        if(view.getId() == R.id.sign_in_button && !mGoogleApiClient.isConnecting()){
            mSignInClicked = true;
            mGoogleApiClient.connect();
        }else if (view.getId() == R.id.sign_out_button){
            if (mGoogleApiClient.isConnected()){
                mGoogleApiClient.clearDefaultAccountAndReconnect();
            }
        }
    }*/



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                // Signin button clicked
                signInWithGplus();
                break;
        }
    }

    /**
     * Sign-in into google
     * */
    public void signInWithGplus() {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }
    }

    /**
     * Method to resolve any signin errors
     * */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }




}


