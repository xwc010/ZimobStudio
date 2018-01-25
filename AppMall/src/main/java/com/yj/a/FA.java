package com.yj.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.yj.m.F;
import com.yj.see.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by xwc on 2018/1/11.
 */

public class FA extends BaseMultiItemQuickAdapter<F, BaseViewHolder> {

    Picasso mPicasso;

    public FA(Context context, List data) {
        super(data);
        addItemType(0, R.layout.lay_i_i);
        addItemType(1, R.layout.lay_i_v);
        mPicasso = Picasso.with(context);
    }

    @Override
    protected void convert(BaseViewHolder helper, F f) {
        setFlagBottom(helper, f);
        switch (helper.getItemViewType()) {
            case 0: {
                TextView tvTitle = (TextView) helper.getView(R.id.tv_libs_feed_large_title);
                if(TextUtils.isEmpty(f.getTitle())){
                    tvTitle.setVisibility(View.GONE);
                }else {
                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText(f.getTitle());
                }

                ImageView img_icon = helper.getView(R.id.img_libs_feed_large_cion);
                mPicasso.load(f.getImgUrl())
                        .placeholder(R.drawable.img_loading_default)
                        .error(R.drawable.img_loading_default)
                        .into(img_icon);
                break;
            }
            case 1: {
                JZVideoPlayerStandard videoPlayerStandard = helper.getView(R.id.videoplayer);
                videoPlayerStandard.setUp(f.getVideoUrl(), JZVideoPlayer.SCREEN_WINDOW_LIST,
                        f.getTitle());

                mPicasso.load(f.getImgUrl())
                        .into(videoPlayerStandard.thumbImageView);
                break;
            }
        }
    }

    private void setFlagBottom(BaseViewHolder helper, F img) {
        TextView tv_flag = helper.getView(R.id.tv_libs_feed_item_flag);
        tv_flag.setVisibility(View.VISIBLE);
        int flag = img.getImgUrl().length() % 3;
        switch (flag) {
            case 0: {
                tv_flag.setTextColor(tv_flag.getContext().getResources().getColor(R.color.color_libs_red));
                tv_flag.setText("热");
                Drawable drawable = tv_flag.getContext().getResources().getDrawable(R.drawable.ic_libs_hot);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth() * 3 / 4, drawable.getMinimumHeight() * 3 / 4);
                tv_flag.setCompoundDrawables(drawable, null, null, null);
                break;
            }
            case 1: {
                tv_flag.setTextColor(tv_flag.getContext().getResources().getColor(R.color.color_libs_blue));
                tv_flag.setText("最新");
                tv_flag.setCompoundDrawables(null, null, null, null);
                break;
            }
            default: {
                tv_flag.setVisibility(View.GONE);
                tv_flag.setCompoundDrawables(null, null, null, null);
                break;
            }
        }

//        helper.getView(R.id.tv_libs_feed_item_time).setVisibility(View.GONE);
        helper.setText(R.id.tv_libs_feed_item_poster, publishers[(int) (img.getImgUrl().length()+img.getTitle().length()) % 15]);
        int num = Integer.valueOf(format.format(new Date())) / (img.getImgUrl().length());
        helper.setText(R.id.tv_libs_feed_item_brow_num, String.valueOf(num));
    }

    SimpleDateFormat format = new SimpleDateFormat("MMddhhmm");


    private String[] publishers = {"火星人播报", "YaYa喵萌", "Angler MM", "八卦达人", "圈外评价人"
            , "黑话大叔", "暴走橙子", "胡来", "Cona Da", "BiSai"
            , "8号当铺", "小春看世界", "都是那点事", "积木火车", "CoolSee"};
}
