package com.evideo.smartpos.module.fragment;

import com.evideo.smartpos.R;
import com.evideo.smartpos.base.AbsBaseFragment;


public class RecommendFragment extends AbsBaseFragment {

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home_recommend;
    }
}
