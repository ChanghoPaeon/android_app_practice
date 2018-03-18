package com.example.paeon.practice;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
   // String TAG = getLocalClassName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.actionbar_name);


        IsOndeviceMode();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_menu, menu) ;
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings :
                Log.d("onOptionsItemSelected", "setting button clicked");
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean IsOndeviceMode(){

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isOnDevice = pref.getBoolean("ondevice_mode", false);
        return isOnDevice;
    }


    public void onResume() {

        super.onResume();  // Always call the superclass method first
        Log.d("onResume",  "ondevice val :  "+ IsOndeviceMode());
        // Get the Camera instance as the activity achieves full user focus


        //fist two 'FF' was not used..
        if (IsOndeviceMode() == true) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFFF0000));
            getSupportActionBar().setTitle(getResources().getString(R.string.actionbar_name) + " with ondevice");
        }
        else{
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF3F51B5));
            getSupportActionBar().setTitle(getResources().getString(R.string.actionbar_name) + " with server");
        }
    }
}
