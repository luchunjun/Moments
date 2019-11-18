package com.lcj.moments.contract;

import com.lcj.commonlib.presenter.BasePresenter;
import com.lcj.commonlib.view.BaseView;
import com.lcj.moments.model.tweet.Tweet;

import java.util.ArrayList;


public class MainContract {
    public interface Presenter extends BasePresenter {
        void loadData();
        void initTweetsFirstPage();
    }
    public interface View extends BaseView<BasePresenter> {
        void setProfileImage(String url);

        void setAvatar(String url);

        void setNickName(String nickName);

        void setTweetList(ArrayList<Tweet> tweetList);
    }


}
