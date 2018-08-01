package com.evideo.smartpos.base;

import android.text.TextUtils;

import com.evideo.smartpos.net.exception.ApiException;

import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;


public abstract class BaseSubscriber<T> extends ResourceSubscriber<T> {
    private BaseContract.IBaseView mBaseView;
    private String mMsg;

    public BaseSubscriber(BaseContract.IBaseView baseView) {
        this.mBaseView = baseView;
    }


    public abstract void onSuccess(T t);

    public void onFailure(int code, String message) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        /*if (!NetworkUtils.isConnected(AppUtils.getAppContext())) {
            // Logger.d("没有网络");
        } else {

        }*/
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T response) {
        if (null == mBaseView) {
            return;
        }
        mBaseView.complete();
        onSuccess(response);
    }


    @Override
    public void onError(Throwable e) {
        if (null == mBaseView) {
            return;
        }
        mBaseView.complete();//完成操作
        if (null != mMsg && !TextUtils.isEmpty(mMsg)) {
            mBaseView.showError(mMsg);
        } else if (e instanceof ApiException) {
            mBaseView.showError(e.toString());
        } else if (e instanceof SocketTimeoutException) {
            mBaseView.showError("服务器响应超时ヽ(≧Д≦)ノ");
        } else if (e instanceof HttpException) {
            mBaseView.showError("数据加载失败ヽ(≧Д≦)ノ");
        } else {
            mBaseView.showError("未知错误ヽ(≧Д≦)ノ");
        }
    }
}
