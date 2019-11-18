package com.lcj.moments.utils;

import com.lcj.moments.model.tweet.Tweet;
import com.lcj.moments.model.user.UserInfo;

import java.util.ArrayList;

public class GlobalSingletonKit {
    private volatile static GlobalSingletonKit globalSingletonKit;
    private UserInfo currentUserInfo;
    private final ArrayList<Tweet>tweetArrayList =new ArrayList<>();

    private GlobalSingletonKit() {
    }

    public static GlobalSingletonKit getInstance() {
        if (globalSingletonKit == null) {
            synchronized (GlobalSingletonKit.class) {
                if (globalSingletonKit == null) {
                    globalSingletonKit = new GlobalSingletonKit();
                }
            }
        }
        return globalSingletonKit;
    }

    public UserInfo getCurrentUserInfo() {
        return currentUserInfo;
    }

    public void setCurrentUserInfo(UserInfo currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    public ArrayList<Tweet> getTweetArrayList() {
        return tweetArrayList;
    }
    public void addTweetToTweetArrayList(Tweet tweet) {
        tweetArrayList.add(tweet);
    }
    public void clearTweetArrayList() {
        tweetArrayList.clear();
    }
}
