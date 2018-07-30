package com.yoyiyi.soleil.adapter.region.sectiton;

import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yoyiyi.soleil.R;
import com.yoyiyi.soleil.bean.region.RegionRecommend;
import com.yoyiyi.soleil.module.region.AllRegionRankActivity;
import com.yoyiyi.soleil.module.region.RegionTypeActivity;
import com.yoyiyi.soleil.utils.AppUtils;
import com.yoyiyi.soleil.utils.NumberUtils;
import com.yoyiyi.soleil.widget.section.StatelessSection;
import com.yoyiyi.soleil.widget.section.ViewHolder;

import java.util.List;

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/6/4 17:07
 * 描述:
 */
public class RegionRecommendRecommendSection extends StatelessSection<RegionRecommend.RecommendBean> {

    public RegionRecommendRecommendSection(List<RegionRecommend.RecommendBean> recommendBeanList) {
        super(R.layout.layout_item_region_head, R.layout.layout_item_region_body, recommendBeanList);
    }

    @Override
    public void convert(ViewHolder holder, RegionRecommend.RecommendBean recommendBean, int position) {
        Glide.with(mContext)
                .load(recommendBean.cover)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.bili_default_image_tv)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                )
                .into((ImageView) holder.getView(R.id.iv_video_preview));
        holder.setText(R.id.tv_video_title, recommendBean.title)
                .setText(R.id.tv_video_play_num, NumberUtils.format(recommendBean.play + ""))
                .setText(R.id.tv_video_favourite, NumberUtils.format(recommendBean.danmaku + ""));
        if (position % 2 == 0) {
            setMargins(holder.itemView, (int) AppUtils.getDimension(R.dimen.dp10),
                    (int) AppUtils.getDimension(R.dimen.dp5),
                    (int) AppUtils.getDimension(R.dimen.dp5),
                    (int) AppUtils.getDimension(R.dimen.dp5));
        } else {
            setMargins(holder.itemView, (int) AppUtils.getDimension(R.dimen.dp5),
                    (int) AppUtils.getDimension(R.dimen.dp5),
                    (int) AppUtils.getDimension(R.dimen.dp10),
                    (int) AppUtils.getDimension(R.dimen.dp5));
        }
        holder.itemView.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, VideoDetailActivity.class)));


    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_title, "热门推荐")
                .setImageResource(R.id.iv_icon, R.mipmap.ic_category_promo)
                .setVisible(R.id.tv_rank, true)
                .setVisible(R.id.tv_look_up, false);
        holder.getView(R.id.tv_rank).setOnClickListener(view -> {
            if (mContext instanceof RegionTypeActivity) {
                RegionTypeActivity activity = (RegionTypeActivity) mContext;
                AllRegionRankActivity.startActivity(mContext, activity.mTitle);
            }
        });
    }
}
