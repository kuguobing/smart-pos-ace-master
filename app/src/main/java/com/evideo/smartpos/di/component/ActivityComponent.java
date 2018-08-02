package com.evideo.smartpos.di.component;

import android.app.Activity;

import com.evideo.smartpos.di.module.ActivityModule;
import com.evideo.smartpos.di.scope.ActivityScope;
import com.evideo.smartpos.module.activity.SplashActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(SplashActivity splashActivity);


}
