package com.lcj.commonlib.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lcj.commonlib.presenter.BasePresenter;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
    }

    protected abstract int getContentViewId();
}
