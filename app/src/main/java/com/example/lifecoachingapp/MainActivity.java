package com.example.lifecoachingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

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
            R.drawable.monthly_goals_icon};



    RecyclerView chapters_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courses = getResources().getStringArray(R.array.chapters);
        chapters_recycler_view = findViewById(R.id.chapters_recycler_view);

        ChaptersRecyclerViewAdapter chaptersRecyclerViewAdapter = new ChaptersRecyclerViewAdapter(this, courses, chapterColors, chapterSizes, chapterProgress, images );
        chapters_recycler_view.setAdapter(chaptersRecyclerViewAdapter);
        chapters_recycler_view.setLayoutManager(new LinearLayoutManager(this));

    }
}