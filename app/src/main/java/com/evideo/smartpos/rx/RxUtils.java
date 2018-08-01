package com.evideo.smartpos.rx;

import com.evideo.smartpos.net.response.HttpResponse;
import com.evideo.smartpos.net.exception.ApiException;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RxUtils {
    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程 统一处理线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> flowableEmitter) throws Exception {
                try {
                    flowableEmitter.onNext(t);
                    flowableEmitter.onComplete();
                } catch (Exception e) {
                    flowableEmitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<List<T>> createData(final List<T> t) {
        return Flowable.create(new FlowableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(FlowableEmitter<List<T>> flowableEmitter) throws Exception {
                try {
                    flowableEmitter.onNext(t);
                    flowableEmitter.onComplete();
                } catch (Exception e) {
                    flowableEmitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResponse<T>, T> handleResult() {
        return new FlowableTransformer<HttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<HttpResponse<T>> flowable) {
                return flowable.flatMap(new Function<HttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(HttpResponse<T> tHttpResponse) throws Exception {
                        if ("0".equals(tHttpResponse.getErrorCode())) {
                            if (null != tHttpResponse.getResponseInfo()) {
                                return createData(tHttpResponse.getResponseInfo());
                            }
                            return Flowable.error(new ApiException("服务器返回error"));
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResponse<List<T>>, List<T>> handleListResult() {
        return new FlowableTransformer<HttpResponse<List<T>>, List<T>>() {
            @Override
            public Flowable<List<T>> apply(Flowable<HttpResponse<List<T>>> flowable) {
                return flowable.flatMap(new Function<HttpResponse<List<T>>, Flowable<List<T>>>() {
                    @Override
                    public Flowable<List<T>> apply(HttpResponse<List<T>> listHttpResponse) throws Exception {
                        if ("0".equals(listHttpResponse.getErrorCode())) {
                            if (null != listHttpResponse.getResponseInfo()) {
                                return createData(listHttpResponse.getResponseInfo());
                            }
                            return Flowable.error(new ApiException("服务器返回error"));
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
           }
        };
    }
}
