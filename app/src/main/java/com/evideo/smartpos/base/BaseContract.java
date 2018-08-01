package com.evideo.smartpos.base;

public interface BaseContract {

    interface IBaseView {
        /**
         * 请求出错
         */
        void showError(String msg);

        /**
         * 请求完成
         */
        void complete();
    }


    interface IBasePresenter<T> {
        /**
         * 绑定
         * @param view
         */
        void attachView(T view);

        /**
         * 解绑
         */
        void detachView();
    }

}
