package com.lcj.moments.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.lcj.commonlib.activity.BaseActivity;
import com.lcj.commonlib.presenter.BasePresenter;
import com.lcj.commonlib.utils.image.ImageUtil;
import com.lcj.commonlib.view.components.SupportRefreshRecyclerView;
import com.lcj.moments.R;
import com.lcj.moments.adapter.tweet.TweetListAdapter;
import com.lcj.moments.contract.MainContract;
import com.lcj.moments.model.tweet.Tweet;
import com.lcj.moments.presenter.MainPresenter;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainContract.View {
    private SupportRefreshRecyclerView tweetsList;
    private FrameLayout headerLayout;
    private SHSwipeRefreshLayout swipeRefreshLayout;
    private static final int WAITING_TIME_BY_MILLISECOND = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweetsList = findViewById(R.id.tweetsList);
        tweetsList.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        headerLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.tweet_list_header, null);
        setPresenter(new MainPresenter(this));
        mPresenter.start();
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        final View view = inflater.inflate(R.layout.refresh_view, null);
        final TextView textView = view.findViewById(R.id.title);
        swipeRefreshLayout.setFooterView(view);
        swipeRefreshLayout.setOnRefreshListener(new SHSwipeRefreshLayout.SHSOnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(() -> {
                    swipeRefreshLayout.finishRefresh();
                    ((MainPresenter) mPresenter).initTweetsFirstPage();
                }, WAITING_TIME_BY_MILLISECOND);
            }

            @Override
            public void onLoading() {
                swipeRefreshLayout.postDelayed(() -> {
                    swipeRefreshLayout.finishLoadmore();
                    ((MainPresenter) mPresenter).loadData();
                }, WAITING_TIME_BY_MILLISECOND);
            }


            @Override
            public void onRefreshPulStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setRefreshViewText(getString(R.string.pull_down_to_refresh));
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setRefreshViewText(getString((R.string.release_to_refresh)));
                        break;
                    case SHSwipeRefreshLayout.START:
                        swipeRefreshLayout.setRefreshViewText(getString(R.string.refreshing));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onLoadmorePullStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        textView.setText(R.string.pull_up_to_refresh);
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        textView.setText(R.string.release_to_load);
                        break;
                    case SHSwipeRefreshLayout.START:
                        textView.setText(R.string.loading);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void setProfileImage(final String url) {
        ImageUtil.loadImage(this, headerLayout.findViewById(R.id.profile_image), url, R.mipmap.bg404);
    }

    @Override
    public void setAvatar(final String url) {
        ImageUtil.loadImage(this, headerLayout.findViewById(R.id.avatar), url, R.drawable.ic_avatar);
    }

    @Override
    public void setNickName(final String nickName) {
        final TextView nickNameTV = headerLayout.findViewById(R.id.nickName);
        runOnUiThread(() -> nickNameTV.setText(nickName));
    }

    @Override
    public void setTweetList(final ArrayList<Tweet> tweetList) {
        runOnUiThread(() -> {
            TweetListAdapter tweetListAdapter = new TweetListAdapter(MainActivity.this, tweetList);
            tweetListAdapter.setHeaderView(headerLayout);
            tweetsList.setAdapter(tweetListAdapter);
        });
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }

}
