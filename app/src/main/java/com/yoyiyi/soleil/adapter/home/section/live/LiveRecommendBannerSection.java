package com.yoyiyi.soleil.adapter.home.section.live;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yoyiyi.soleil.R;
import com.yoyiyi.soleil.bean.live.LiveRecommend;
import com.yoyiyi.soleil.utils.AppUtils;
import com.yoyiyi.soleil.utils.SpanUtils;
import com.yoyiyi.soleil.widget.section.StatelessSection;
import com.yoyiyi.soleil.widget.section.ViewHolder;

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/5/21 11:57
 * 描述:首页直播推荐主播section（音乐台）
 */
public class LiveRecommendBannerSection extends StatelessSection {
    private final LiveRecommend.RecommendDataBean.BannerDataBean mData;

    public LiveRecommendBannerSection(LiveRecommend.RecommendDataBean.BannerDataBean data) {
        super(R.layout.layout_item_home_live_body, R.layout.layout_empty);
        this.mData = data;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        Glide.with(mContext)
                .load(mData.cover.src)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.bili_default_image_tv)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                )
                .into((ImageView) holder.getView(R.id.iv_video_preview));
       // holder.setText(R.id.tv_video_live_up, TextUtils.isEmpty(mData.owner.name) ? "未知" : mData.owner.name)//up
        holder.setText(R.id.tv_video_live_up, TextUtils.isEmpty(mData.title) ? "未知" : mData.title)
                .setText(R.id.tv_video_online, mData.online + "");//在线人数;
        holder.setText(R.id.tv_video_title, new SpanUtils()
                .append("#" + mData.area + "#")
                .append(mData.title)
                .setForegroundColor(AppUtils.getColor(R.color.pink_text_color))
                .create());
        setMargins(holder.itemView, (int) AppUtils.getDimension(R.dimen.dp10),
                (int) AppUtils.getDimension(R.dimen.dp5),
                (int) AppUtils.getDimension(R.dimen.dp10),
                (int) AppUtils.getDimension(R.dimen.dp5));
    }

}
