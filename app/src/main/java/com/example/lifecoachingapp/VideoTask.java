package com.example.lifecoachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VideoTask extends AppCompatActivity {


    //UI elements
    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_task);

        //Initialize the bottom nav and set an event listener to the the bottom nav menu
        bottom_nav = findViewById(R.id.bottom_nav);

        //Unselect everything in the menu
        Menu menu = bottom_nav.getMenu();
        unselectBottomMenuItems(menu);

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
                        startActivity(new Intent(getApplicationContext(), Discovery.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    void unselectBottomMenuItems(Menu menu){
        menu.setGroupCheckable(0, true, false);
        for (int i = 0; i <  menu.size(); i++) {
            menu.getItem(i).setChecked(false);
        }
        menu.setGroupCheckable(0, true, true);
    }
}