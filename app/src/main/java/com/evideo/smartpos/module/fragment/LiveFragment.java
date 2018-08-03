package com.evideo.smartpos.module.fragment;

import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseFragment;

public class LiveFragment extends AbsBaseFragment{

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home_live;
    }

    public static LiveFragment newInstance() {
        return new LiveFragment();
    }

}
