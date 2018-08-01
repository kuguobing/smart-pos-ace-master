package com.evideo.smartpos.mvp.presenter;


import com.evideo.smartpos.base.BaseSubscriber;
import com.evideo.smartpos.base.RxPresenter;
import com.evideo.smartpos.bean.Splash;
import com.evideo.smartpos.mvp.contract.SplashContract;
import com.evideo.smartpos.net.helper.RetrofitHelper;
import com.evideo.smartpos.rx.RxUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;


public class SplashPresenter extends RxPresenter<SplashContract.View>
        implements SplashContract.Presenter<SplashContract.View> {

    private RetrofitHelper mRetrofitHelper;
    @Inject
    public SplashPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getSplashData() {
        BaseSubscriber<Splash> subscriber = mRetrofitHelper.getTopicCenter()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<Splash>(mView) {
                    @Override
                    public void onSuccess(Splash splash) {
                        if (splash.code == 0)
                            mView.showSplash(splash);
                    }
                    @Override
                    public void onFailure(int code, String message) {
                            mView.showError(message);
                    }
                });
        addSubscribe(subscriber);


    }

    /**
     * 5s 倒计时
     */
    @Override
    public void setCountDown() {
        final Long count = 5L;
        Disposable subscribe = Flowable.interval(0, 1, TimeUnit.SECONDS)
                .map(aLong -> count - aLong)
                .take(count + 1)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(aLong -> mView.showCountDown(aLong.intValue()));
        addSubscribe(subscribe);
    }

}
