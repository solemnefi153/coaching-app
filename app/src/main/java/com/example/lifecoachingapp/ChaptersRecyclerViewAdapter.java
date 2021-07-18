package com.example.lifecoachingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ChaptersRecyclerViewAdapter extends RecyclerView.Adapter<ChaptersRecyclerViewAdapter.ThisViewHolder> {
    Context context;
    String[] chapterNames;
    String[] chapterColors;
    String[] chapterSizes;
    List<Integer> chapterProgress;

    int[] images;

    public ChaptersRecyclerViewAdapter(Context ct, String[] chn, String[] chc, String[] chs, List<Integer> chp, int[] img){
        context = ct;
        chapterNames = chn;
        chapterColors= chc;
        chapterSizes= chs;
        chapterProgress = chp;
        images = img;
    }

    @NonNull
    @Override
    public ThisViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chapter_row, parent, false);
        return new ThisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChaptersRecyclerViewAdapter.ThisViewHolder holder, int position) {
        //Check if the previous chapter has been finished by the user in order to unlock this chapter
        boolean chapterUnlocked = false;
        //Check if this is the first chapter
        if(position == 0){
            chapterUnlocked = true;
        }
        //Check if the previous chapter has been completed
        else if (chapterProgress.get(position - 1)  == Integer.parseInt(chapterSizes[position - 1])){
            chapterUnlocked = true;
        }

        //Fill out the chapter row
        holder.chapter_name_text.setText(chapterNames[position]);
        String progressTxt = chapterProgress.get(position) + "/" + chapterSizes[position];
        holder.chapter_status_text.setText(progressTxt);
        holder.chapter_progress_bar.setProgress(chapterProgress.get(position) * 100 / Integer.parseInt(chapterSizes[position]));

        //Default gray color
        int chapterColor;

        //If this chapter is unlocked do the following
        if(chapterUnlocked){
            chapterColor =  Color.parseColor(chapterColors[position]);
            holder.chapter_image.setImageResource(images[position]);
            holder.chapter_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChapterTree.class);
                    String chapterId = "Chapter_" + (position + 1);
                    intent.putExtra("ChapterId", chapterId);
                    intent.putExtra("ChapterSize", Integer.parseInt(chapterSizes[position]));
                    intent.putExtra("ChapterProgress", chapterProgress.get(position));
                    intent.putExtra("ChapterName", chapterNames[position]);
                    intent.putExtra("ChapterColor", chapterColor);
                    intent.putExtra("ChapterIcon", images[position]);
                    context.startActivity(intent);
                }
            });
        }
        else{
            //This is a gray color for locked items
            chapterColor =  Color.parseColor("#D3D3D3");
            //Change this to a lock
            holder.chapter_image.setImageResource(R.drawable.ic_lock);
            //Add an event listener to trigger a toast prompting the user to finish the previous chapter
            holder.chapter_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Locked chapter. Finish previous chapters to unlock", Toast.LENGTH_LONG).show();
                }
            });
        }
        holder.chapter_image.setBackgroundColor(chapterColor);
        holder.chapter_progress_bar.getProgressDrawable().setColorFilter(chapterColor, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Override
    public int getItemCount() {
        return chapterNames.length;
    }

    public class ThisViewHolder extends RecyclerView.ViewHolder{

        CardView chapter_row;
        TextView chapter_name_text;
        TextView chapter_status_text;
        ImageView chapter_image;
        ProgressBar chapter_progress_bar;


        public ThisViewHolder(@NonNull View itemView) {
            super(itemView);
            chapter_row = itemView.findViewById(R.id.chapter_row);
            chapter_name_text = itemView.findViewById(R.id.chapter_name_text);
            chapter_status_text = itemView.findViewById(R.id.chapter_status_text);
            chapter_image = itemView.findViewById(R.id.chapter_image);
            chapter_progress_bar = itemView.findViewById(R.id.chapter_progress_bar);
        }
    }

}
