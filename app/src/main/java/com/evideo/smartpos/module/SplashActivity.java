package com.evideo.smartpos.module;

import android.view.WindowManager;

import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseActivity;
import com.evideo.smartpos.bean.Splash;
import com.evideo.smartpos.mvp.contract.SplashContract;
import com.evideo.smartpos.mvp.presenter.SplashPresenter;
import com.evideo.smartpos.utils.StatusBarUtil;

import javax.inject.Inject;


public class SplashActivity extends AbsBaseActivity<SplashPresenter> implements SplashContract.View {

    @Inject
    SplashPresenter mPresenter;

    @Override
    protected int getActivityLayoutID() {
        // 隐藏标题栏
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //继承AppCompatActivity，导致无效
        if (null != getSupportActionBar()) {
            getSupportActionBar().hide();
        }
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 返回界面
        return R.layout.activity_splash;
    }


    @Override
    protected void initInject() {
        //设置透明
        StatusBarUtil.setTransparent(this);
        getActivityComponent().inject(this);
    }

    @Override
    public void showCountDown(int count) {

    }

    @Override
    public void showSplash(Splash splash) {

    }


}
