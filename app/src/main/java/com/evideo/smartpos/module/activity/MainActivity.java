package com.evideo.smartpos.module.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseActivity;
import com.evideo.smartpos.event.Event;
import com.evideo.smartpos.module.fragment.HomeFragment;
import com.evideo.smartpos.rx.RxBus;
import com.evideo.smartpos.utils.AppUtils;
import com.evideo.smartpos.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;


public class MainActivity extends AbsBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private Long mExitTime = 0L;
    private int mCurrentPosition = -1;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mFragments = new ArrayList<>();
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        disableNavigationViewScrollbars(mNavigationView);
        mNavigationView.setNavigationItemSelectedListener(this);
        switchFragmentIndex(0);
    }

    @Override
    protected void initVariables() {
        initFragment();
        //监听事件
        RxBus.INSTANCE.toFlowable(Event.StartNavigationEvent.class)
                .compose(bindToLifecycle())
                .subscribe(new Consumer<Event.StartNavigationEvent>() {
                    @Override
                    public void accept(Event.StartNavigationEvent startNavigationEvent) throws Exception {
                        if (startNavigationEvent.start) {
                            toggleDrawer();
                        }
                    }
                });
    }

    private void initFragment() {
        mFragments = Arrays.asList(HomeFragment.newInstance());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        AppUtils.runOnUIDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();
                switch (id) {
                    case R.id.item_vip:
                        //startActivity(new Intent(MainActivity.this, VipActivity.class));
                        ToastUtils.showToast("大会员");
                        break;
                }
            }
        }, 230);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (null == navigationView) {
            return;
        }
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (null == navigationMenuView) {
            return;
        }
        navigationMenuView.setVerticalScrollBarEnabled(false);
    }

    private void switchFragmentIndex(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentPosition != -1) {
            transaction.hide(mFragments.get(mCurrentPosition));
        }
        if (!mFragments.get(position).isAdded()) {
            transaction.add(R.id.fl_content, mFragments.get(position));
        }
        transaction.show(mFragments.get(position)).commit();
        mCurrentPosition = position;
    }

    public void toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(mDrawerLayout.getChildAt(1))) {
                mDrawerLayout.closeDrawers();
            } else {
                exitApp();
            }
        }
        return true;
    }

    private void exitApp() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtils.showToast("再按一次退出");
            mExitTime = System.currentTimeMillis();
        } else {
            Event.ExitEvent event = new Event.ExitEvent();
            event.exit = -1;
            RxBus.INSTANCE.post(event);
        }
    }

}
