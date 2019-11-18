package com.lcj.moments.view.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lcj.commonlib.activity.BaseActivity;
import com.lcj.moments.R;
import com.lcj.moments.contract.SplashContract;
import com.lcj.moments.presenter.SplashPresenter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * SplashActivity
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {
    private TextView sloganTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sloganTextView = findViewById(R.id.sloganTextView);
        Logger.addLogAdapter(new AndroidLogAdapter());
        printSlogan();
        setPresenter(new SplashPresenter(this));
        mPresenter.start();
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void printSlogan() {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(sloganTextView, "translationX", -300f, 0f);
        translationX.setDuration(2000);
        translationX.start();
    }
}
