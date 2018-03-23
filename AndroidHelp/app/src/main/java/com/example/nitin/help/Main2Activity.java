package com.example.nitin.help;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.VideoView;
public class Main2Activity extends AppCompatActivity {
    Button button4;
    Button button5;
    PopupMenu.OnMenuItemClickListener menuItemClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        VideoView simpleview = (VideoView) findViewById(R.id.video1);
        simpleview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid));
        simpleview.start();
        simpleview.getCurrentPosition();
        menuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.maill:
                        startActivity(new Intent(getApplicationContext(), Login_mail.class));
                        return true;
                    case R.id.phonel:
                        startActivity(new Intent(getApplicationContext(), Login_mail.class));
                        return true;
                    case R.id.mailr:
                        startActivity(new Intent(getApplicationContext(), Main3Activity.class));
                        return true;
                    case R.id.phoner:
                        startActivity(new Intent(getApplicationContext(), Main3Activity.class));
                        return true;
                }
                return false;
            }
        };
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu1 = new PopupMenu(Main2Activity.this, button4);
                popupMenu1.getMenuInflater().inflate(R.menu.popup_menu_1, popupMenu1.getMenu());
                popupMenu1.show();
                popupMenu1.setOnMenuItemClickListener(menuItemClickListener);
            }
        });
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu2 = new PopupMenu(Main2Activity.this, button5);
                popupMenu2.getMenuInflater().inflate(R.menu.popup_menu_2, popupMenu2.getMenu());
                popupMenu2.show();
                popupMenu2.setOnMenuItemClickListener(menuItemClickListener);
            }
        });
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