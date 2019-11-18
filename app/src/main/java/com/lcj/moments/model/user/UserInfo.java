package com.lcj.moments.model.user;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Lu Chunjun
 * @date 2019/11/9 11:59
 * @decs user's information in moments project
 */

public class UserInfo {
    //url for profile image in moments.
    @JSONField(name = "profile-image")
    private String profileImage;
    //url for profile image in moments.
    @JSONField(name = "avatar")
    private String avatar;
    @JSONField(name = "nick")
    private String nickName;
    @JSONField(name = "username")
    private String userName;

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getProfileImage() {
        return profileImage;
    }


    public String getAvatar() {
        return avatar;
    }


    public String getNickName() {
        return nickName;
    }

    public String getUserName() {
        return userName;
    }

}
