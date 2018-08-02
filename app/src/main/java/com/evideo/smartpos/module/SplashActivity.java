package com.evideo.smartpos.module;

import android.content.Intent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseActivity;
import com.evideo.smartpos.bean.Splash;
import com.evideo.smartpos.mvp.contract.SplashContract;
import com.evideo.smartpos.mvp.presenter.SplashPresenter;
import com.evideo.smartpos.utils.StatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;


public class SplashActivity extends AbsBaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    @BindView(R.id.tv_count_down)
    TextView mTvCountDown;
    @BindView(R.id.ll_count_down)
    LinearLayout mLlCountDown;

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
    protected void initWidget() {
        RxView.clicks(mLlCountDown)
                .throttleFirst(3, TimeUnit.SECONDS)//3秒内响应第一次发射数据
                .compose(bindToLifecycle())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) {
                        startActivity(new Intent(mContext, MainActivity.class));
                        finish();
                    }
                });
    }

    @Override
    protected void loadData() {
        mPresenter.getSplashData();
        mPresenter.setCountDown();
    }

    @Override
    public void showCountDown(int count) {
        mTvCountDown.setText(String.valueOf(count));
        if (count == 0) {
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
        }
    }

    @Override
    public void showSplash(Splash splash) {
        if (splash.getData().isEmpty()) {
            mIvSplash.setImageResource(R.mipmap.ic_default_bg);
        } else {
            int pos = new Random().nextInt(splash.getData().size());
            Glide.with(this)
                    .load(splash.getData().get(pos).getThumb())
                    .apply(new RequestOptions()
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                    )
                    .into(mIvSplash);
        }
    }

}
