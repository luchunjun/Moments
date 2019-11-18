package com.lcj.moments.adapter.tweet;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcj.commonlib.adapter.CommonAdapter;
import com.lcj.commonlib.utils.image.ImageUtil;
import com.lcj.moments.R;
import com.lcj.moments.components.CommentPopWindow;
import com.lcj.moments.model.tweet.Tweet;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Map;

public class TweetListAdapter extends CommonAdapter {
    public TweetListAdapter(Context context, ArrayList<Tweet> dataList) {
        super(context, dataList);
        mContext = context;
        mData = dataList;
        headerViewsCount =1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            return new UserInfoViewHolder(mHeaderView);
        } else {
            View item = LayoutInflater.from(mContext).inflate(R.layout.tweet_layout, parent, false);
            return new TweetViewHolder(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TweetViewHolder) {
            TweetViewHolder mTweetViewHolder = (TweetViewHolder) holder;
            Tweet tweet = (Tweet) mData.get(position);
            mTweetViewHolder.commentsButton.setOnClickListener(v -> {
                CommentPopWindow commentPopWindow = new CommentPopWindow(mContext);
                PopupWindowCompat.showAsDropDown(commentPopWindow, v, -100, -50, Gravity.TOP);
            });
            Logger.d(tweet);
            mTweetViewHolder.content.setText(tweet.getContent());
            Map<String, Object> senderMap = tweet.getSender();
            if (senderMap != null) {
                ImageUtil.loadImage((Activity) mContext, ((TweetViewHolder) holder).contentSenderAvatar, (String) senderMap.get("avatar"), R.drawable.ic_avatar);
                mTweetViewHolder.contentSenderNickName.setText((String) senderMap.get("nick"));
                mTweetViewHolder.contentSenderNickName.getPaint().setUnderlineText(true);
            }
            JSONArray images = tweet.getImages();
            Logger.d(images);
            ArrayList<String> picturesURLs = new ArrayList<>();
            if (images != null) {
                for (int i = 0; i < 9 && i < images.size(); i++) {
                    picturesURLs.add(images.getString(i));
                }
            }
            JSONArray commentArray = tweet.getComments();
            if (commentArray != null) {
                StringBuilder commentsStr = new StringBuilder();
                for (int i = 0; i < commentArray.size(); i++) {
                    Logger.d(commentArray.getString(i));
                    JSONObject json = JSON.parseObject(commentArray.getString(i));
                    String commentContent = json.getString("content");
                    String commentSenderNick = json.getJSONObject("sender").getString("nick");
                    commentsStr.append("<B><font color='#4169E1'>").append(commentSenderNick).append(" :</font></B>");
                    commentsStr.append(commentContent);
                    commentsStr.append("<br/>");
                }
                Logger.e("commentsStr=====>" + commentsStr.toString());
                if (commentsStr.length() > 0) {
                    mTweetViewHolder.comments.setText(Html.fromHtml(commentsStr.toString()));
                } else {
                    mTweetViewHolder.comments.setVisibility(View.INVISIBLE);
                }

            } else {
                mTweetViewHolder.comments.setVisibility(View.INVISIBLE);
            }
            mTweetViewHolder.multiPicturesView.setImageUrls(picturesURLs);
        }
    }


}
