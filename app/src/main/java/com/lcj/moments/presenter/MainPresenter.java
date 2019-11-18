package com.lcj.moments.presenter;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.lcj.moments.contract.MainContract;
import com.lcj.moments.model.tweet.Tweet;
import com.lcj.moments.utils.GlobalSingletonKit;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import static androidx.core.util.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mMainView;
    private final static int pageItemsCount = 5;
    private final ArrayList<Tweet> tweets = new ArrayList<>();

    public MainPresenter(@NonNull MainContract.View view) {
        mMainView = checkNotNull(view, "mMainView cannot be null!");
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        initUserInformationView();
        initTweetsFirstPage();
    }

    private void initUserInformationView() {
        if (GlobalSingletonKit.getInstance().getCurrentUserInfo() != null) {
            mMainView.setAvatar(GlobalSingletonKit.getInstance().getCurrentUserInfo().getAvatar());
            mMainView.setProfileImage(GlobalSingletonKit.getInstance().getCurrentUserInfo().getProfileImage());
            mMainView.setNickName(GlobalSingletonKit.getInstance().getCurrentUserInfo().getNickName());
        }
    }

    @Override
    public void initTweetsFirstPage() {
        tweets.clear();
        new GetDataTask().execute();
    }

    public class GetDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            generateData();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mMainView.setTweetList(tweets);
        }
    }
    @Override
    public void loadData() {
        new GetDataTask().execute();
    }
    private void generateData(){
        int newSize = tweets.size() + pageItemsCount;
        int currentSize = tweets.size();
        if (tweets.size() >= GlobalSingletonKit.getInstance().getTweetArrayList().size()) {
            return;
        }
        Logger.d("newSize=====>" + newSize + "currentSize======>" + currentSize);
        for (int i = currentSize; i < newSize && i < GlobalSingletonKit.getInstance().getTweetArrayList().size(); i++) {
            tweets.add(GlobalSingletonKit.getInstance().getTweetArrayList().get(i));
        }
    }
}
