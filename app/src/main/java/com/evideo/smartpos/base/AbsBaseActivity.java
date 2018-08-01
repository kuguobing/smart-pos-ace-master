package com.evideo.smartpos.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.evideo.smartpos.VideoSmartPosApplication;
import com.evideo.smartpos.di.component.ActivityComponent;
import com.evideo.smartpos.di.component.DaggerActivityComponent;
import com.evideo.smartpos.di.module.ActivityModule;
import com.evideo.smartpos.event.Event;
import com.evideo.smartpos.rx.RxBus;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class AbsBaseActivity<T extends BaseContract.BasePresenter>
        extends RxAppCompatActivity implements BaseContract.BaseView{

    @Inject
    protected T mPresenter;
    protected Context mContext;//上下文环境
    protected Unbinder mUnbinder;
    private Disposable mDisposable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutID());
        mContext = this;
        mUnbinder = ButterKnife.bind(this);
        initInject();
        initPresenter();
        initVariables();
        VideoSmartPosApplication.getInstance().addActivity(this);
        initExit();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(VideoSmartPosApplication.getInstance().getAppComponent())
                .activityModule(getActivityModule())
                .build();
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

    @Override
    protected void onDestroy() {
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
        if (null != mPresenter) {
            mPresenter.detachView();
        }
        VideoSmartPosApplication.getInstance().removeActivity(this);
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void complete() {

    }
}
