package com.evideo.smartpos.net.helper;


import com.evideo.smartpos.bean.GuestInfo;
import com.evideo.smartpos.net.api.ApiService;
import com.evideo.smartpos.net.request.HttpRequest;
import com.evideo.smartpos.net.response.HttpResponse;

import io.reactivex.Flowable;


public class RetrofitHelper {

    private final ApiService mApiService;

    public RetrofitHelper(ApiService apiService) {
        this.mApiService = apiService;
    }


    /*******************************ApiApi****************************************/

    public Flowable<HttpResponse<GuestInfo>> getCustomerInfo(HttpRequest httpRequest) {
        return mApiService.getCustomerInfo(httpRequest);
    }







}
