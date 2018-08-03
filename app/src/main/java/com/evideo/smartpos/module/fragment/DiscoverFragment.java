package com.evideo.smartpos.module.fragment;

import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseFragment;


public class DiscoverFragment extends AbsBaseFragment {

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home_discover;
    }
}
