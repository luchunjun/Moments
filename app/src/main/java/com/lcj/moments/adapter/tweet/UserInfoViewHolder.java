package com.lcj.moments.adapter.tweet;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lcj.moments.R;

public class UserInfoViewHolder extends RecyclerView.ViewHolder {
    private TextView nickName;
    private ImageView avatar;
    private ImageView profileImage;

    public UserInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        nickName = itemView.findViewById(R.id.nickName);
        avatar = itemView.findViewById(R.id.avatar);
        profileImage = itemView.findViewById(R.id.profile_image);
    }
}