package com.evideo.smartpos.net.api;

import com.evideo.smartpos.bean.GuestInfo;
import com.evideo.smartpos.bean.Splash;
import com.evideo.smartpos.net.request.HttpRequest;
import com.evideo.smartpos.net.response.HttpResponse;
import com.evideo.smartpos.utils.TYProtocolPath;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    /**
     * Get示例
     */

    /*@GET("event/getlist?device=phone&mobi_app=iphone")
    Flowable<ActivityCenter> getActivityCenter(@Query("page") int page, @Query("pagesize") int pageSize);
*/
    @GET
    Flowable<Splash> getTopicCenter();

    @POST(TYProtocolPath.getCustomerInfo)
    Flowable<HttpResponse<GuestInfo>> getCustomerInfo(@Body HttpRequest httpRequest);


}
