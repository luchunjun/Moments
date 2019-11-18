package com.lcj.moments.presenter;


import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.lcj.commonlib.utils.http.HttpUtil;
import com.lcj.commonlib.utils.http.NetUtil;
import com.lcj.moments.contract.SplashContract;
import com.lcj.moments.model.tweet.Tweet;
import com.lcj.moments.model.tweet.TweetsModel;
import com.lcj.moments.model.user.UserInfo;
import com.lcj.moments.model.user.UserInfoModel;
import com.lcj.moments.utils.GlobalSingletonKit;
import com.orhanobut.logger.Logger;

import static androidx.core.util.Preconditions.checkNotNull;

public class SplashPresenter implements SplashContract.Presenter {
    private final SplashContract.View mView;

    public SplashPresenter(SplashContract.View view) {
        mView = checkNotNull(view, "mMainView cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (NetUtil.isNetworkConnected((Activity) mView)) {
            fetchUserInformation();
            fetchTweets();
        } else {
            NetUtil.openNetworkSetting((Activity) mView);
        }
    }


    private void fetchUserInformation() {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.sendGetRequest(new HttpUtil.HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    GlobalSingletonKit.getInstance().setCurrentUserInfo(JSON.parseObject(result, UserInfo.class));
                } catch (JSONException e) {
                    Logger.e("==========>" + result);
                }
            }

            @Override
            public void onFail() {
                Logger.e("==========>fail");
            }
        });
    }

    private void fetchTweets() {
        TweetsModel tweetsModel = new TweetsModel();
        tweetsModel.sendGetRequest(new HttpUtil.HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                GlobalSingletonKit.getInstance().clearTweetArrayList();
                Logger.e(result);
                JSONArray jsonElements = JSONArray.parseArray(result);
                for (int i = 0; i < jsonElements.size(); i++) {
                    String jsonStr = jsonElements.get(i).toString();
                    try {
                        if (jsonStr.startsWith("{\"error\":") || jsonStr.startsWith("{\"unknown error\"")) {
                            Logger.d(jsonStr);
                        } else {
                            Tweet tweet = JSON.parseObject(jsonStr, Tweet.class);
                            if ((tweet.getImages() == null || tweet.getImages().size() == 0) && tweet.getContent() == null) {
                                Logger.e("No Images or content");
                            } else {
                                GlobalSingletonKit.getInstance().addTweetToTweetArrayList(tweet);
                            }
                        }
                    } catch (JSONException e) {
                        Logger.e("JSONException=========>" + jsonStr);
                        e.printStackTrace();
                    }
                }
                mView.startMainActivity();
            }

            @Override
            public void onFail() {

            }
        });
    }

}
