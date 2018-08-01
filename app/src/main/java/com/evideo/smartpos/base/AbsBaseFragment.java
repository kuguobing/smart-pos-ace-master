package com.evideo.smartpos.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evideo.smartpos.VideoSmartPosApplication;
import com.evideo.smartpos.di.component.DaggerFragmentComponent;
import com.evideo.smartpos.di.component.FragmentComponent;
import com.evideo.smartpos.di.module.FragmentModule;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class AbsBaseFragment<T extends BaseContract.BasePresenter>
        extends RxFragment implements BaseContract.BaseView {

    @Inject
    protected T mPresenter;
    protected View mRootView;
    protected Activity mActivity;
    protected Context mContext;
    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;
    //标志位 fragment是否可见
    protected boolean isVisible;
    private Unbinder mUnbinder;


    @Override
    public void onAttach(Context context) {
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
        mContext = context;
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        if (null == mRootView) {
            mRootView = inflater.inflate(getFragmentLayoutID(), container, false);
            mActivity = getSupportActivity();
            mContext = mActivity;
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        initInject();
        initPresenter();
        initVariables();
        initWidget();
        finishCreateView(savedInstanceState);
        initDatas();
    }

    protected void lazyLoadData() {

    }

    protected void initDatas() {
        loadData();
    }

    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
        mUnbinder.unbind();
    }

    /**
     * 分离
     */
    @Override
    public void onDetach() {
        this.mActivity = null;
        super.onDetach();
    }


    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(VideoSmartPosApplication.getInstance().getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }


    /**
     * 初始化RV
     */
    protected void initRecyclerView() {


    }

    /**
     * 初始化刷新
     */
    @SuppressLint("CheckResult")
    protected void initRefreshLayout() {

    }

    /**
     * 清除数据
     */
    protected void clearData() {

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
    public void initVariables() {

    }

    /**
     * 懒加载
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) return;
        lazyLoadData();
        isPrepared = false;
    }

    protected void onInvisible() {

    }

    /**
     * 加载数据
     */
    protected void loadData() {

    }

    /**
     * 注入dagger2依赖
     */
    protected void initInject() {

    }

    /**
     * 显示错误信息
     *
     * @param msg msg
     */
    @Override
    public void showError(String msg) {


    }

    /**
     * 完成加载
     */
    @Override
    public void complete() {

    }


    /**
     * 布局
     *
     * @return int
     */
    public abstract @LayoutRes int getFragmentLayoutID();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public void initWidget() {

    }


    /**
     * 获取Activity
     *
     * @return FragmentActivity
     */
    public FragmentActivity getSupportActivity() {
        return (FragmentActivity) super.getActivity();
    }

    /**
     * 获取ApplicationContext 信息
     *
     * @return Context
     */
    public Context getApplicationContext() {
        return (null == this.mContext) ?
                ((null == getActivity()) ? null : getActivity().getApplicationContext()) : this.mContext.getApplicationContext();
    }

}
