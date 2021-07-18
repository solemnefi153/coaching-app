package com.example.lifecoachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChapterTree extends AppCompatActivity {


    //UI elements
    BottomNavigationView bottom_nav;
    List<CardView> tasks = new ArrayList<>();
    List<ImageView> tasks_images = new ArrayList<>();
    List<TextView> tasks_titles = new ArrayList<>();


    //Data
    String titles_text [];
    String task_video_ids [];





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_tree);

        //Grab the variables sent through the intent
        String chapterId = getIntent().getStringExtra("ChapterId");
        String chapterName = getIntent().getStringExtra("ChapterName");
        int chapterSize = getIntent().getIntExtra("ChapterSize", 0);
        int chapterProgress = getIntent().getIntExtra("ChapterProgress", 0);
        int chapterColor = getIntent().getIntExtra("ChapterColor", 0);
        int chapterIcon = getIntent().getIntExtra("ChapterIcon", 0);


        //Set the title of this activity to be the chapter name
        setTitle(chapterName);

        //Initialize the tasks
        tasks.add(findViewById(R.id.task_1));
        tasks.add(findViewById(R.id.task_2));
        tasks.add(findViewById(R.id.task_3));
        tasks.add(findViewById(R.id.task_4));

        //Initialize the tasks_images
        tasks_images.add(findViewById(R.id.task_1_image));
        tasks_images.add(findViewById(R.id.task_2_image));
        tasks_images.add(findViewById(R.id.task_3_image));
        tasks_images.add(findViewById(R.id.task_4_image));

        //Initialize the tasks_titles
        tasks_titles.add(findViewById(R.id.task_1_title));
        tasks_titles.add(findViewById(R.id.task_2_title));
        tasks_titles.add(findViewById(R.id.task_3_title));
        tasks_titles.add(findViewById(R.id.task_4_title));

        //Initialize the tasks_titles
        int chapterArrayId = getResources().getIdentifier(chapterId, "array", getPackageName());
        titles_text = getResources().getStringArray(chapterArrayId);

        //Initialize videos list for for this chapter
        String videosListId = chapterId + "_videos";
        int chapterVideosArrayId = getResources().getIdentifier(videosListId, "array", getPackageName());
        task_video_ids = getResources().getStringArray(chapterVideosArrayId);






        //Display the tasks based on the size of the chapter
        for(int i = 0; i < chapterSize & i < tasks.size(); i++){
            //Check if the task is locked
            if(i > chapterProgress){
                //Default gray color with a lock image
                tasks.get(i).setCardBackgroundColor(Color.parseColor("#D3D3D3"));
                tasks_images.get(i).setImageResource(R.drawable.ic_lock);
                //Add an event listener to trigger a toast prompting the user to finish the previous tasks
                tasks.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ChapterTree.this, "Locked task. Finish previous tasks to unlock", Toast.LENGTH_LONG).show();
                    }
                });
            }
            //Else dynamically display the tasks
            else {
                //Default gray color with a lock image
                tasks.get(i).setCardBackgroundColor(chapterColor);
                tasks_images.get(i).setImageResource(chapterIcon);
                //Add an event listener
                int finalI = i;
                tasks.get(i).setOnClickListener(new View.OnClickListener() {
                    String taskTitle = titles_text[finalI];
                    String taskVideo = task_video_ids[finalI];
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ChapterTree.this, VideoTask.class);
                        intent.putExtra("TaskTitle", taskTitle);
                        intent.putExtra("ChapterId", chapterId);
                        intent.putExtra("ChapterVideoId", task_video_ids[finalI]);
                        intent.putExtra("TaskCompleted", chapterProgress > finalI);
                        startActivity(intent);
                    }
                });
            }
            //Display the task with the name of the task
            tasks.get(i).setVisibility(View.VISIBLE);
            tasks_titles.get(i).setText(titles_text[i]);
        }


        //Initialize the bottom nav and set an event listener to the the bottom nav menu
        bottom_nav = findViewById(R.id.bottom_nav);
        //bottom_nav.setSelectedItemId(R.id.home_page);

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