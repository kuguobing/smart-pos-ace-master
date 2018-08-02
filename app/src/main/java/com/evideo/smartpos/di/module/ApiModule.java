package com.evideo.smartpos.di.module;

import com.evideo.smartpos.di.qualifier.ApiUrl;
import com.evideo.smartpos.net.api.ApiService;
import com.evideo.smartpos.net.helper.OkHttpHelper;
import com.evideo.smartpos.net.helper.RetrofitHelper;
import com.evideo.smartpos.net.support.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return OkHttpHelper.getInstance().getOkHttpClient();
    }

    @Singleton
    @Provides
    public Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    public RetrofitHelper provideRetrofitHelper(ApiService apiService) {
        return new RetrofitHelper(apiService);
    }


    @Singleton
    @Provides
    @ApiUrl
    public Retrofit provideApiRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ApiConstants.APP_BASE_URL);
        //return createRetrofit(builder, client, ApiConstants.API_LOCAL_HOST_URL);
        //return createRetrofit(builder, client, TYDaggerApplication.ServerBaseURl());
    }

    @Singleton
    @Provides
    public ApiService provideApiService(@ApiUrl Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
