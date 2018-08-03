package com.evideo.smartpos.module.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.evideo.smartpos.R;
import com.evideo.smartpos.adapter.MainAdapter;
import com.evideo.smartpos.base.AbsBaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;


public class HomeFragment extends AbsBaseFragment {

    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.stl_tabs)
    SlidingTabLayout mStlTabs;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_main_home;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void initWidget() {
        if (null != mToolbar) {
            mToolbar.setTitle("");
            ((AppCompatActivity) mActivity).setSupportActionBar(mToolbar);
            mToolbar.inflateMenu(R.menu.menu_main);
        }
        initViewPager();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initViewPager() {
        MainAdapter adapter = new MainAdapter(getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(adapter);
        mStlTabs.setViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
    }

}
