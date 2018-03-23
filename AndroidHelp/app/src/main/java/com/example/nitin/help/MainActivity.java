package com.example.nitin.help;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import static android.content.ContentValues.TAG;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {
private static int SPLASH_TIME_OUT = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                boolean isLogin  = sharedPreferences.getBoolean("isLogin",false);
                Log.e("TAG", "onCreate: application is called" );
                if(isLogin)
                {
                    Log.e(TAG, "onCreate: Starting the Main3Activity");
                    startActivity(new Intent(getApplicationContext(),DashBoard.class).setFlags(FLAG_ACTIVITY_NEW_TASK));
                }else{
                    Log.e(TAG, "onCreate: MainActivity is called by the myapplication" );
                    startActivity(new Intent(getApplicationContext(),Main2Activity.class).setFlags(FLAG_ACTIVITY_NEW_TASK));
                }
            }
        }, SPLASH_TIME_OUT);

    }
}
