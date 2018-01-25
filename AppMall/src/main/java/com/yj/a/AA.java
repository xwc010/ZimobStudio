package com.yj.a;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.yj.m.A;
import com.yj.see.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xwc on 2018/1/23.
 */

public class AA extends BaseQuickAdapter<A, BaseViewHolder> {
    SimpleDateFormat format = new SimpleDateFormat("MMddhh");
    Picasso mPicasso;
    public AA(@Nullable List<A> data) {
        super(R.layout.lay_ap, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, A item) {
        helper.setText(R.id.tv_app_title, item.getName());
        helper.setText(R.id.tv_app_describe, item.getDec());
        int num = item.getDownLoad().length()/item.getSize().length();
        helper.setText(R.id.tv_app_size, "下载："+ num +"万  "+item.getSize());
        ImageView img_icon = helper.getView(R.id.img_app_cion);

        if(mPicasso == null){
            mPicasso = Picasso.with(img_icon.getContext());
        }

        mPicasso.load(item.getLogo())
                .placeholder(R.drawable.img_loading_default)
                .error(R.drawable.img_loading_default)
                .into(img_icon);
    }
}
