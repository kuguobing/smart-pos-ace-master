package com.evideo.smartpos.module.fragment;

import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseFragment;


public class RegionFragment extends AbsBaseFragment {

    public static RegionFragment newInstance() {
        return new RegionFragment();
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home_region;
    }
}
