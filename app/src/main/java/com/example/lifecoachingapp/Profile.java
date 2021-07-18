package com.example.lifecoachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class Profile extends AppCompatActivity {

    //UI Elements
    BottomNavigationView bottom_nav;
    TextInputEditText username_input;
    ImageButton saveUsername;

    //Necessary data for the UI
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Grab UI elements
        username_input = findViewById(R.id.username_input);
        saveUsername = findViewById(R.id.user_name_edit_btn);

        //Initialize the shared preferences
        //A list of chapters with its respective progress saved in the shared preferences
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if(sp.contains("username")){
            String username = sp.getString("username", "");
            username_input.setText(username);
        }

        //Add an event listener to the save btn
        saveUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Grab the current value of the input
                String newUsername = username_input.getText().toString().trim();
                SharedPreferences.Editor editor = sp.edit();
                //Increment the value saved in this preference
                editor.putString("username",  newUsername);
                editor.apply();
                //Show a toast confirming the username update
                Toast.makeText(getApplicationContext(), "Saved username as: " + newUsername, Toast.LENGTH_LONG).show();
            }
        });




        //Initialize the bottom nav and set an event listener to the the bottom nav menu
        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setSelectedItemId(R.id.profile_page);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_page:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile_page:
                        return true;
                    case R.id.discovery_page:
                        startActivity(new Intent(getApplicationContext(), Discovery.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}