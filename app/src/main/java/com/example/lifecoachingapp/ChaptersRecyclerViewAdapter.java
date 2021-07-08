package com.example.lifecoachingapp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.graphics.Color.CYAN;

public class ChaptersRecyclerViewAdapter extends RecyclerView.Adapter<ChaptersRecyclerViewAdapter.ThisViewHolder> {
    Context context;
    String chapterNames [];
    int chapterColors [];
    int chapterSizes [];
    int chapterProgress [];
    int images [];

    public ChaptersRecyclerViewAdapter(Context ct, String chn[], int chc [], int chs[], int chp[], int img[]){
        context = ct;
        chapterNames = chn;
        chapterColors= chc;
        chapterSizes= chs;
        chapterProgress= chp;
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
        holder.chapter_name_text.setText(chapterNames[position]);
        String progressTxt = chapterProgress[position] + "/" + chapterSizes[position];
        holder.chapter_status_text.setText(progressTxt);
        holder.chapter_image.setImageResource(images[position]);
        holder.chapter_image.setBackgroundColor(chapterColors[position]);
        holder.chapter_progress_bar.setProgress(chapterProgress[position] * 100 / chapterSizes[position]);
        holder.chapter_progress_bar.getProgressDrawable().setColorFilter(chapterColors[position], android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Override
    public int getItemCount() {
        return chapterNames.length;
    }

    public class ThisViewHolder extends RecyclerView.ViewHolder{

        TextView chapter_name_text;
        TextView chapter_status_text;
        ImageView chapter_image;
        ProgressBar chapter_progress_bar;


        public ThisViewHolder(@NonNull View itemView) {
            super(itemView);
            chapter_name_text = itemView.findViewById(R.id.chapter_name_text);
            chapter_status_text = itemView.findViewById(R.id.chapter_status_text);
            chapter_image = itemView.findViewById(R.id.chapter_image);
            chapter_progress_bar = itemView.findViewById(R.id.chapter_progress_bar);
        }
    }

}
