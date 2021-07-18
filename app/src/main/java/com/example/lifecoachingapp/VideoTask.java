package com.example.lifecoachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoTask extends AppCompatActivity {


    //UI elements
    BottomNavigationView bottom_nav;
    YouTubePlayerView task_video;
    CheckBox task_check_box;
    TextView task_completed_text;

    //Data elements
    SharedPreferences sp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_task);


        //Grab the variables from the intent
        String taskTitle = getIntent().getStringExtra("TaskTitle");
        String chapterVideoId = getIntent().getStringExtra("ChapterVideoId");
        String chapterId = getIntent().getStringExtra("ChapterId");
        boolean taskCompleted = getIntent().getBooleanExtra("TaskCompleted", false);

        //Set the title of this activity to be the task name
        setTitle(taskTitle);



        //Get UI elements
        task_check_box = findViewById(R.id.task_check_box);
        task_completed_text = findViewById(R.id.task_completed_text);
        task_video = findViewById(R.id.task_video);

        //Initialize YouTubePlayerView
        //Add a listener to the video
        getLifecycle().addObserver(task_video);
        task_video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(chapterVideoId, 0);
            }
        });
        //Check if this task has been marked as completed
        if(taskCompleted){
            //Hide the  checkbox and show the completed task text
            task_check_box.setVisibility(View.GONE);
            task_completed_text.setText("Completed task");
        }
        else{
            //Display the checkbox and add an event listener
            task_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                      if(isChecked){
                          //Hide the  checkbox and show the completed task text
                          task_check_box.setVisibility(View.GONE);
                          task_completed_text.setText("Completed task");
                          //Grab from the user preferences the current progress on this chapter
                          //and increase it
                          //A list of chapters with its respective progress saved in the shared preferences
                          sp = getSharedPreferences("chaptersProgress", Context.MODE_PRIVATE);
                          SharedPreferences.Editor editor = sp.edit();
                          //Check if this variable has been initialized
                          String thisChapterProgress = chapterId + "_progress";
                          if(sp.contains(thisChapterProgress)){
                              int currentProgress = sp.getInt(thisChapterProgress, 0);
                              //Increment the value saved in this preference
                              editor.putInt(thisChapterProgress, currentProgress + 1);
                              editor.apply();
                          }
                          //Create an intent to go to the home page
                          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                          intent.putExtra("TaskCompleted", true);
                          startActivity(intent);
                      }
                  }
              }
            );
        }


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