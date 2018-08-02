package com.evideo.smartpos.net.api;

import com.evideo.smartpos.bean.GuestInfo;
import com.evideo.smartpos.net.request.HttpRequest;
import com.evideo.smartpos.net.response.HttpResponse;
import com.evideo.smartpos.utils.TYProtocolPath;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {


    /**
     * splash界面
     *
     * @return
     */
    /*@GET("/x/v2/splash?mobi_app=android&build=505000&channel=360&width=1080&height=1920&ver=4344558841496142006")
    Flowable<Splash> getSplash();*/


    @POST(TYProtocolPath.getCustomerInfo)
    Flowable<HttpResponse<GuestInfo>> getCustomerInfo(@Body HttpRequest httpRequest);


}
