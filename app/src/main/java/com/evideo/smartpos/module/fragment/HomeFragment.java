package com.evideo.smartpos.module.fragment;

import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseFragment;


public class HomeFragment extends AbsBaseFragment {

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_main_home;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        //initViewPager();
    }

}
