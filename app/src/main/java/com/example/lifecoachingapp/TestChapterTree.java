package com.example.lifecoachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TestChapterTree extends AppCompatActivity {

    TextView chapterReportText;

    //UI elements
    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chapter_tree);

        //Grab the elements form this activity UI
        chapterReportText = findViewById(R.id.chapterReportText);

        //Grab the variables sent through the intent
        String chapterId = getIntent().getStringExtra("ChapterId");
        String chapterName = getIntent().getStringExtra("ChapterName");
        int chapterSize = getIntent().getIntExtra("ChapterSize", 0);
        int chapterProgress = getIntent().getIntExtra("ChapterProgress", 0);

        //Set the title of this activity to be the chapter name
        setTitle(chapterName);

        //Update the text of this element to display the chapter report
        String chapterReport = "You are in the " +  chapterId + ". " + chapterProgress + "/" + chapterSize;
        chapterReportText.setText(chapterReport);

        //Initialize the bottom nav and set an event listener to the the bottom nav menu
        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setSelectedItemId(R.id.home_page);

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