package com.evideo.smartpos.mvp.presenter;


import com.evideo.smartpos.base.BaseSubscriber;
import com.evideo.smartpos.base.RxPresenter;
import com.evideo.smartpos.bean.Splash;
import com.evideo.smartpos.mvp.contract.SplashContract;
import com.evideo.smartpos.net.helper.RetrofitHelper;
import com.evideo.smartpos.rx.RxUtils;
import com.evideo.smartpos.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class SplashPresenter extends RxPresenter<SplashContract.View>
        implements SplashContract.Presenter<SplashContract.View> {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public SplashPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getSplashData() {
        BaseSubscriber<Splash> subscriber = Flowable.just(JsonUtils.readJson("splash.json"))
                .map(new Function<String, Splash>() {
                    @Override
                    public Splash apply(String s) {
                        Gson gson = new Gson();
                        JsonObject object = new JsonParser().parse(s).getAsJsonObject();
                        Splash splash = gson.fromJson(object, Splash.class);
                        return splash;
                    }
                })
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<Splash>(mView) {
                    @Override
                    public void onSuccess(Splash splash) {
                        if (0 == splash.getCode()) {
                            mView.showSplash(splash);
                        }
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
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) {
                        return count - aLong;
                    }
                })
                .take(count + 1)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mView.showCountDown(aLong.intValue());
                    }
                });
        addSubscribe(subscribe);
    }

}
