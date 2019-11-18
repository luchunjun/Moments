package com.lcj.moments.model.tweet;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

/**
 * @author Lu Chunjun
 * @date 2019/11/9
 * @decs user's tweet in moments project
 */
public class Tweet {
    private String content;
    private Map<String, Object> sender;
    private JSONArray comments;
    private JSONArray images;

    public String getContent() {
        return content;
    }

    public Map<String, Object> getSender() {
        return sender;
    }

    public JSONArray getComments() {
        return comments;
    }

    public JSONArray getImages() {
        return images;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(Map<String, Object> sender) {
        this.sender = sender;
    }

    public void setComments(JSONArray comments) {
        this.comments = comments;
    }

    public void setImages(JSONArray images) {
        this.images = images;
    }
}
