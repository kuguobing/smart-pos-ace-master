package com.evideo.smartpos.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseActivity;
import com.evideo.smartpos.bean.Splash;
import com.evideo.smartpos.mvp.contract.SplashContract;
import com.evideo.smartpos.mvp.presenter.SplashPresenter;

import javax.inject.Inject;


public class SplashActivity extends AbsBaseActivity implements SplashContract.View {

    @Inject
    SplashPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_splash;
    }


    @Override
    public void showCountDown(int count) {

    }

    @Override
    public void showSplash(Splash splash) {

    }
}
