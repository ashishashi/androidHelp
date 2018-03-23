package com.example.nitin.help;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile_activity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private DatabaseReference databaseReference;
    private EditText editTextName, editTextAge, editTextPhone;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login_mail.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextAge = findViewById(R.id.editTextAge);
        editTextName = findViewById(R.id.editTextName);
        buttonSave = findViewById(R.id.buttonSave);
        editTextPhone = findViewById(R.id.editTextPhone);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonSave.setOnClickListener(this);
        textViewUserEmail = findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());

        buttonLogout.setOnClickListener(this);
    }

    private void saveUserInfo(){
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        UserInformation userInfo =new UserInformation(name, age, phone);
        SharedPreferences sharedPreferences  =  getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone",phone);
        editor.apply();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInfo);
        Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),DashBoard.class));
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            firebaseAuth.signOut();
            SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLogin",false);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
//            startActivity(new Intent(this, Login_mail.class));
        }
        if(view == buttonSave){saveUserInfo();}
    }
}
