package com.example.nitin.help;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.net.URL;
import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
    int i=1;
    private Location lastLocation;
    private TextView detailsText;
    Button btnShowLocation, btnLogout;
    double latitude;
    double longitude;
    String latitudeestring;
    String longitudestring;
    URL url;
    GPSTracker gps;
    FirebaseAuth firebaseAuth;
    MediaPlayer mediaPlayer;

    boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnLogout = findViewById(R.id.buttonLogout);
        firebaseAuth = FirebaseAuth.getInstance();
        Button a = (Button) findViewById(R.id.button);
        Button b = (Button) findViewById(R.id.button9);
        Button c = (Button) findViewById(R.id.button5);
        Button d = (Button) findViewById(R.id.button11);
        Button e = (Button) findViewById(R.id.button14);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLogin",false);
                editor.apply();
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent call1Intent = new Intent(Intent.ACTION_CALL);
                call1Intent.setData(Uri.parse("tel: 1234 "));
                startActivity(call1Intent);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent call1Intent = new Intent(Intent.ACTION_CALL);
                call1Intent.setData(Uri.parse("tel: 1234 "));
                startActivity(call1Intent);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent call2Intent = new Intent(Intent.ACTION_CALL);
                call2Intent.setData(Uri.parse("tel: 1235 "));
                startActivity(call2Intent);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent call3Intent = new Intent(Intent.ACTION_CALL);
                call3Intent.setData(Uri.parse("tel: 1236 "));
                startActivity(call3Intent);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent call3Intent = new Intent(Intent.ACTION_CALL);
                call3Intent.setData(Uri.parse("tel: 1236 "));
                startActivity(call3Intent);
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        Button button10 = (Button) findViewById(R.id.button10);
        Button button12 = (Button) findViewById(R.id.button12);
        Button button15 = (Button) findViewById(R.id.button15);
        Button seven=(Button) this.findViewById(R.id.button7);

        double ar[] = Sharings();
        final String link = "http://maps.google.com/maps?q=loc:" + String.format("%f,%f", ar[0], ar[1]);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mess("I am in emergency. Please Help."+link,"100");
            }
        });
        final SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mess("I am in emergency. Please Help"+link, sharedPreferences.getString("phone","100"));
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mess("I am in a medical Emergency. Please Help."+link,"102");
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mess("I am an in emergency. Please Help."+link,"110");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sharing();
            }
        });
        Button one = (Button) this.findViewById(R.id.siren);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.siren);
        mediaPlayer.setLooping(true);
        one.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Log.e("TAG", "onClick: playing condition is "+isPlaying );
                if(isPlaying)
                {
                    mediaPlayer.stop();
                    isPlaying = false;

                }else {
                    mediaPlayer.start();
//                    mediaPlayer.setLooping(true);
                    isPlaying = true;
                }
                /**mediaPlayer.setLooping(true);
                while(i>0)
                {if (i%2==0)
                {mediaPlayer.stop();}
                    i=i+1;
                }
            }*/
        }});

    }
    public void Sharing() {

        gps = new GPSTracker(this);
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();


            latitudeestring = Double.toString(latitude);
            longitudestring = Double.toString(longitude);
            shareLocation();
        } else {
            gps.showSettingsAlert();
            //shareLocation();


        }
    }
    public double[] Sharings() {

        gps = new GPSTracker(this);
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();


            latitudeestring = Double.toString(latitude);
            longitudestring = Double.toString(longitude);
        } else {
            gps.showSettingsAlert();
            //shareLocation();


        }
        return new double[]{latitude,longitude};
    }
    public void shareLocation(){
        /** String newline = System.getProperty("line.separator");
         detailsText.setText(String.format("%s: %s%s%s: %s%s%s: %s",
         getString(R.string.accuracy), getAccuracy(location), newline,
         getString(R.string.latitude), getLatitude(location), newline,
         getString(R.string.longitude), getLongitude(location)));

         lastLocation = location;*/

        //String link = formatLocation(lastLocation, PreferenceManager.getDefaultSharedPreferences(this).getString("prefLinkType", ""));
        //URL link ="https://maps.google.com?q="+latitudeestring+","+longitudestring;
        //  Uri myUri = Uri.parse("https://maps.google.com?q="+latitudeestring+","+longitudestring);
        //url =link;
        String link = "http://maps.google.com/maps?q=loc:" + String.format("%f,%f", latitude, longitude);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,link);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    public void mess(String X,String Y){
        //Intent callIntent = new Intent(Intent.ACTION_CALL);
        //callIntent.setData(Uri.parse("tel: 123 "));
        //startActivity(callIntent);
        String messageToSend = X;
        String number = Y;
        SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}