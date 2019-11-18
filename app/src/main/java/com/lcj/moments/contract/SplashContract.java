package com.lcj.moments.contract;

import com.lcj.commonlib.presenter.BasePresenter;
import com.lcj.commonlib.view.BaseView;


public class SplashContract {
    public interface View extends BaseView<Presenter> {
        void startMainActivity();
    }

    public interface Presenter extends BasePresenter {

    }
}
