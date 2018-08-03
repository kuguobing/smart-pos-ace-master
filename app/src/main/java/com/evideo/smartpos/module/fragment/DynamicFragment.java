package com.evideo.smartpos.module.fragment;

import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseFragment;

public class DynamicFragment extends AbsBaseFragment {


    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home_dynamic;
    }
}
