package com.example.nitin.help;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Nitin on 2/7/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        boolean isLogin  = sharedPreferences.getBoolean("isLogin",true);
        Log.e("TAG", "onCreate: application is called" );
        if(isLogin)
        {
            Log.e(TAG, "onCreate: Starting the Main3Activity");
            startActivity(new Intent(getApplicationContext(),Main3Activity.class));
        }else{
            Log.e(TAG, "onCreate: MainActivity is called by the myapplication" );
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
}
