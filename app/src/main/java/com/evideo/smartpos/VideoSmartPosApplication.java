package com.evideo.smartpos;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.evideo.smartpos.utils.LogUtils;
import com.facebook.stetho.Stetho;
import com.yoyiyi.soleil.di.component.AppComponent;
import com.yoyiyi.soleil.di.component.DaggerAppComponent;
import com.yoyiyi.soleil.di.module.ApiModule;
import com.yoyiyi.soleil.di.module.AppModule;
import com.yoyiyi.soleil.utils.AppUtils;
import com.yoyiyi.soleil.utils.CrashHandler;
import com.yoyiyi.soleil.utils.NetworkUtils;
import com.yoyiyi.soleil.utils.PrefsUtils;

import java.util.HashSet;
import java.util.Set;

public final class VideoSmartPosApplication extends Application {
    private static VideoSmartPosApplication mContext;
    private Set<Activity> allActivities;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
        mContext = this;
        initNetwork();
        initStetho();
        initCrashHandler();
        initLog();
        initPrefs();
        initComponent();
    }


    /**
     * 增加Activity
     *
     * @param act act
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除Activity
     *
     * @param act act
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 初始化网络模块组件
     */
    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    /**
     * 初始化sp
     */
    private void initPrefs() {
        PrefsUtils.init(this, getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    /**
     * 初始化调试
     */
    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    /**
     * 开启网络监听
     */
    private void initNetwork() {
        NetworkUtils.startNetService(this);
    }

    /**
     * 获取Application
     *
     * @return BiliCopyApplication
     */
    public static VideoSmartPosApplication getInstance() {
        return mContext;
    }


    /**
     * 初始化崩溃日志
     */
    private void initCrashHandler() {
        CrashHandler.getInstance().init(this);
    }


    /**
     * 初始化log
     */
    private void initLog() {
        LogUtils.init(this);
    }
}
