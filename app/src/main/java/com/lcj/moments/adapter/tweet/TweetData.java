package com.lcj.moments.adapter.tweet;

import java.util.ArrayList;

public class TweetData {
    private  String content;
    private  String contentSenderNickName;
    private  String contentSenderAvatarUrl;
    private ArrayList<String> multiPicturesUrls =new ArrayList<>();
    public ArrayList<String> getMultiPicturesUrls() {
        return multiPicturesUrls;
    }

    public void addToMultiPicturesUrls(String pictureUrl) {
        multiPicturesUrls.add(pictureUrl);
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSenderNickName() {
        return contentSenderNickName;
    }

    public void setContentSenderNickName(String contentSenderNickName) {
        this.contentSenderNickName = contentSenderNickName;
    }

    public String getContentSenderAvatarUrl() {
        return contentSenderAvatarUrl;
    }

    public void setContentSenderAvatarUrl(String contentSenderAvatarUrl) {
        this.contentSenderAvatarUrl = contentSenderAvatarUrl;
    }



}
