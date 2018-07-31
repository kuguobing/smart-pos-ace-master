package com.evideo.smartpos.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.evideo.smartpos.VideoSmartPosApplication;
import com.yoyiyi.soleil.event.Event;
import com.yoyiyi.soleil.rx.RxBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class AbsBaseActivity<T extends BaseContract.IBasePresenter>
        extends RxAppCompatActivity implements BaseContract.IBaseView{
    @Inject
    protected T mPresenter;
    protected Context mContext;//上下文环境
    private Disposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutID());
        mContext = this;
        ButterKnife.bind(this);
        initInject();
        initPresenter();
        initVariables();
        VideoSmartPosApplication.getInstance().addActivity(this);
        initExit();
    }

    protected abstract @LayoutRes int getActivityLayoutID();

    /**
     * 注入依赖
     */
    protected void initInject() {

    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        if (null == mPresenter) {
            return;
        }
        mPresenter.attachView(this);
    }

    /**
     * 初始化变量
     */
    protected void initVariables() {

    }

    /**
     * 退出应用
     */
    private void initExit() {
        mDisposable = RxBus.INSTANCE.toDefaultFlowable(Event.ExitEvent.class, new Consumer<Event.ExitEvent>() {
            @Override
            public void accept(Event.ExitEvent exitEvent) throws Exception {
                if (exitEvent.exit == -1) {
                    finish();
                }
            }
        });
    }


}
