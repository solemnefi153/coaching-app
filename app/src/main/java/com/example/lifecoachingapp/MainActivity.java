package com.example.lifecoachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    String  courses[];
    int chapterColors[] = {
            Color.parseColor("#F94144"),
            Color.parseColor("#F3722C"),
            Color.parseColor("#F8961E"),
            Color.parseColor("#F9C74F"),
            Color.parseColor("#90BE6D"),
            Color.parseColor("#43AA8B"),
            Color.parseColor("#577590")
    };

    
    //Here you save the id values of the course images
    //R.drawable.myImage
    int chapterSizes [] = {5, 5, 5, 5, 5, 5, 5};

    int chapterProgress [] = {1, 2, 3, 4, 5, 1, 2};

    int images[] = {
            R.drawable.monthly_goals_icon,
            R.drawable.monthly_goals_icon,
            R.drawable.monthly_goals_icon,
            R.drawable.monthly_goals_icon,
            R.drawable.monthly_goals_icon,
            R.drawable.monthly_goals_icon,
            R.drawable.monthly_goals_icon
    };

    BottomNavigationView bottom_nav;

    RecyclerView chapters_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the bottom nav and set an event listener to the the bottom nav menu
        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setSelectedItemId(R.id.home_page);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_page:
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



        courses = getResources().getStringArray(R.array.chapters);
        chapters_recycler_view = findViewById(R.id.chapters_recycler_view);
        ChaptersRecyclerViewAdapter chaptersRecyclerViewAdapter = new ChaptersRecyclerViewAdapter(this, courses, chapterColors, chapterSizes, chapterProgress, images );
        chapters_recycler_view.setAdapter(chaptersRecyclerViewAdapter);
        chapters_recycler_view.setLayoutManager(new LinearLayoutManager(this));

    }
}