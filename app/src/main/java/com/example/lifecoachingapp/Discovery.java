package com.example.lifecoachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Discovery extends AppCompatActivity {


    BottomNavigationView bottom_nav;

    //Necessary data for the UI
    SharedPreferences sp;

    //UI elements
    EditText mission_statement_input;
    ImageButton mission_statement_edit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        //Get the elements from the UI
        mission_statement_input = findViewById(R.id.mission_statement_input);
        mission_statement_edit_btn = findViewById(R.id.mission_statement_edit_btn);


        //Initialize the shared preferences
        //A list of chapters with its respective progress saved in the shared preferences
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if(sp.contains("mission")){
            String missionStatement = sp.getString("mission", "");
            mission_statement_input.setText(missionStatement);
        }

        //Add an event listener to the save btn
        mission_statement_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Grab the current value of the input
                String missionStatement = mission_statement_input.getText().toString().trim();
                SharedPreferences.Editor editor = sp.edit();
                //Increment the value saved in this preference
                editor.putString("mission",  missionStatement);
                editor.apply();
                //Show a toast confirming the username update
                Toast.makeText(getApplicationContext(), "Mission statement has been updated", Toast.LENGTH_LONG).show();
            }
        });


        //Initialize the bottom nav and set an event listener to the the bottom nav menu
        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setSelectedItemId(R.id.discovery_page);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_page:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile_page:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.discovery_page:
                        return true;
                }
                return false;
            }
        });
    }
}