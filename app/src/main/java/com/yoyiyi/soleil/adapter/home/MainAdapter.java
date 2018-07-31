package com.yoyiyi.soleil.adapter.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/5/23 18:25
 * 描述:主页Tag
 */

public class MainAdapter extends FragmentPagerAdapter {
    private String[] mTitle;
    private Fragment[] mFragment;

    public MainAdapter(FragmentManager fm) {
        super(fm);
        init();
    }


    private void init() {
        //mTitle = AppUtils.getStringArray(R.array.main_title);
        mFragment = new Fragment[mTitle.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragment[position] == null) {
            switch (position) {
                default:
                    break;
            }
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
