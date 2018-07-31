package com.evideo.smartpos.base;


import com.evideo.smartpos.rx.RxBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxPresenter<T extends BaseContract.IBaseView>
        implements BaseContract.IBasePresenter<T>  {

    protected T mView;
    private CompositeDisposable mCompositeDisposable;
    private void unSubscribe() {
        if (null == mCompositeDisposable) {
            return;
        }
        mCompositeDisposable.dispose();
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

    protected void addSubscribe(Disposable disposable) {
        if (null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected <K> void addRxBusSubscribe(Class<K> eventType, Consumer<K> act) {
        if (null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxBus.INSTANCE.toDefaultFlowable(eventType, act));
    }
}
