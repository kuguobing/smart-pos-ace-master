package com.evideo.smartpos.mvp.contract;


import com.evideo.smartpos.base.BaseContract;
import com.evideo.smartpos.bean.Splash;


public interface SplashContract {

    interface View extends BaseContract.BaseView {

        void showSplash(Splash splash);

        void showCountDown(int count);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void getSplashData();

        void setCountDown();

    }
}
