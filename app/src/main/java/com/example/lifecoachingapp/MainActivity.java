package com.example.lifecoachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Data needed to display the activity
    String[] chapters;
    String[] chapterColors;
    String[] chapterSizes;
    List<Integer> chapterProgress = new ArrayList<>();

    int[] images = {
            R.drawable.ic_intro,
            R.drawable.ic_motivation,
            R.drawable.ic_measure_ss,
            R.drawable.ic_mission,
            R.drawable.ic_vision,
            R.drawable.ic_goals,
            R.drawable.ic_growth
    };

    SharedPreferences sp;


    //UI elements
    BottomNavigationView bottom_nav;
    RecyclerView chapters_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the data needed for the activity
        chapters = getResources().getStringArray(R.array.chapters);
        chapterColors = getResources().getStringArray(R.array.chapterColors);
        chapterSizes = getResources().getStringArray(R.array.chapterSizes);

        boolean taskCompleted = getIntent().getBooleanExtra("TaskCompleted", false);
        if(taskCompleted){
            //Show a toast congratulating the user
            Toast.makeText(getApplicationContext(), "Congratulations for finishing a task! keep up on you path to success", Toast.LENGTH_LONG).show();
        }


        //A list of chapters with its respective progress saved in the shared preferences
        sp = getSharedPreferences("chaptersProgress", Context.MODE_PRIVATE);
        //Check if this file has been initialized
        if(!sp.contains("Chapter_1_progress")){
            initializeSharedPreferenceToTrackChapterProgress();
        }
        loadChapterProgressArray();

        //Load the list of chapters in the UI
        chapters_recycler_view = findViewById(R.id.chapters_recycler_view);
        ChaptersRecyclerViewAdapter chaptersRecyclerViewAdapter = new ChaptersRecyclerViewAdapter(this, chapters, chapterColors, chapterSizes, chapterProgress, images );
        chapters_recycler_view.setAdapter(chaptersRecyclerViewAdapter);
        chapters_recycler_view.setLayoutManager(new LinearLayoutManager(this));


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
    }

    void initializeSharedPreferenceToTrackChapterProgress(){
        SharedPreferences.Editor editor = sp.edit();
        for(int i = 1; i <= chapters.length; i++){
            String chapterProgress = "Chapter_" + i + "_progress";
            //Initialize to zero
            editor.putInt(chapterProgress, 0);
        }
        editor.apply();
    }

    void loadChapterProgressArray(){
        for(int i = 1; i <= chapters.length; i++){
            String thisChapterProgress = "Chapter_" + i + "_progress";
            //Push this value in the array of chapterProgress
            int progress = sp.getInt(thisChapterProgress, 0);
            chapterProgress.add(progress);
        }
    }
}