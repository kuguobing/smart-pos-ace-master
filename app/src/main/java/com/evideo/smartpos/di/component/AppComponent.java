package com.evideo.smartpos.di.component;

import android.content.Context;

import com.evideo.smartpos.di.module.ApiModule;
import com.evideo.smartpos.di.module.AppModule;
import com.evideo.smartpos.net.helper.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    Context getContext();
    RetrofitHelper getRetrofitHelper();
}
