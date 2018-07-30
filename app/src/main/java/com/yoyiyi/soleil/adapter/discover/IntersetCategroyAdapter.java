package com.yoyiyi.soleil.adapter.discover;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoyiyi.soleil.R;
import com.yoyiyi.soleil.bean.discover.InterestCategrory;

import java.util.List;

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/6/6 12:57
 * 描述:
 */

public class IntersetCategroyAdapter extends BaseQuickAdapter<InterestCategrory.ResultBean, BaseViewHolder> {
    public IntersetCategroyAdapter(@Nullable List<InterestCategrory.ResultBean> data) {
        super(R.layout.item_interest_category, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, InterestCategrory.ResultBean interestCategrory) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.bili_default_image_tv)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

        Glide.with(mContext)
                .load(interestCategrory.avatar)
                .apply(requestOptions)
                .into((ImageView) holder.getView(R.id.iv_icon));
        holder.setText(R.id.tv_title, interestCategrory.name);
    }
}
