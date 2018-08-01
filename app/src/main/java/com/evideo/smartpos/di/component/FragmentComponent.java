package com.evideo.smartpos.di.component;

import android.app.Activity;

import com.evideo.smartpos.di.module.FragmentModule;
import com.evideo.smartpos.di.scope.FragmentScope;

import dagger.Component;


@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    //void inject(RegionTypeFragment regionTypeFragment);


}
