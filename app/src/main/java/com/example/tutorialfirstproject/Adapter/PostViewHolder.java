package com.example.tutorialfirstproject.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialfirstproject.R;

public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView userIdTV, titleTV, bodyTV;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        userIdTV = (TextView)itemView.findViewById(R.id.userIdTV);
        titleTV = (TextView)itemView.findViewById(R.id.titleTV);
        bodyTV = (TextView)itemView.findViewById(R.id.bodyTV);
    }
}