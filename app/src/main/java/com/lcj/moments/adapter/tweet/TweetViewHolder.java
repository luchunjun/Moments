package com.lcj.moments.adapter.tweet;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lcj.commonlib.view.components.MultiPicturesView;
import com.lcj.moments.R;

public class TweetViewHolder extends RecyclerView.ViewHolder {
    TextView content;
    TextView contentSenderNickName;
    ImageView contentSenderAvatar;
    MultiPicturesView multiPicturesView;
    TextView comments;
    Button commentsButton;

    public TweetViewHolder(View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.content);
        contentSenderNickName = itemView.findViewById(R.id.senderNickName);
        multiPicturesView = itemView.findViewById(R.id.multiPicturesView);
        contentSenderAvatar = itemView.findViewById(R.id.avatar);
        comments= itemView.findViewById(R.id.comments);
        commentsButton = itemView.findViewById(R.id.commentsButton);
    }

}
