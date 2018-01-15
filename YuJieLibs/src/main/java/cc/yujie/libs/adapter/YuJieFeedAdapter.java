package cc.yujie.libs.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cc.yujie.libs.R;
import cc.yujie.libs.constant.DesplayMode;
import cc.yujie.libs.constant.EFeedType;
import cc.yujie.libs.model.Feed;
import cc.zimo.imageplugs.ZiMoImgLoader;

/**
 * Created by xwc on 2018/1/11.
 */

public class YuJieFeedAdapter extends BaseMultiItemQuickAdapter<Feed, BaseViewHolder> {

    public YuJieFeedAdapter(Context context, List data) {
        super(data);
        addItemType(DesplayMode.LARGE, R.layout.item_libs_feed_large);
        addItemType(DesplayMode.LEFT, R.layout.item_libs_feed_left);
        addItemType(DesplayMode.RIGHT, R.layout.item_libs_feed_right);
        addItemType(DesplayMode.GRIDS, R.layout.item_libs_feed_grids);
        addItemType(DesplayMode.TEXT, R.layout.item_libs_feed_txt);
    }

    @Override
    protected void convert(BaseViewHolder helper, Feed feed) {
        switch (helper.getItemViewType()) {
            case DesplayMode.LARGE: {
                helper.setText(R.id.tv_libs_feed_large_title, feed.getTitle());
                if (!TextUtils.isEmpty(feed.getDescribe())) {
                    helper.setVisible(R.id.tv_libs_feed_large_describe, true);
                    helper.setText(R.id.tv_libs_feed_large_describe, feed.getDescribe());
                } else {
                    helper.getView(R.id.tv_libs_feed_large_describe).setVisibility(View.GONE);
                }

                ImageView img_icon = helper.getView(R.id.img_libs_feed_large_cion);
                ZiMoImgLoader.getInstance().loadImg(feed.getThumbnail(), img_icon);
                if (feed.getType() == EFeedType.VIDEO) {
                    helper.setVisible(R.id.img_libs_feed_large_video_tag, true);
                } else {
                    helper.setVisible(R.id.img_libs_feed_large_video_tag, false);
                }

                if (feed.getAlbum() != null && feed.getAlbum().size() > 1) {
                    helper.setVisible(R.id.ll_libs_feed_large_time_num, true);
                } else {
                    helper.setVisible(R.id.ll_libs_feed_large_time_num, false);
                }

                break;
            }
            case DesplayMode.LEFT: {
                helper.setText(R.id.tv_libs_feed_left_title, feed.getTitle());
                ImageView img_icon = helper.getView(R.id.img_libs_feed_left_cion);
                ZiMoImgLoader.getInstance().loadImg(feed.getThumbnail(), img_icon);
                break;
            }
            case DesplayMode.RIGHT: {
                helper.setText(R.id.tv_libs_feed_right_title, feed.getTitle());
                ImageView img_icon = helper.getView(R.id.img_libs_feed_right_cion);
                ZiMoImgLoader.getInstance().loadImg(feed.getThumbnail(), img_icon);
                break;
            }
            case DesplayMode.GRIDS: {
                helper.setText(R.id.tv_libs_feed_girds_title, feed.getTitle());
                if (!TextUtils.isEmpty(feed.getDescribe())) {
                    helper.setVisible(R.id.tv_libs_feed_girds_describe, true);
                    helper.setText(R.id.tv_libs_feed_girds_describe, feed.getDescribe());
                } else {
                    helper.getView(R.id.tv_libs_feed_girds_describe).setVisibility(View.GONE);
                }
                break;
            }
            case DesplayMode.TEXT: {
                helper.setText(R.id.tv_libs_feed_txt_title, feed.getTitle());
//                if(!TextUtils.isEmpty(feed.getDescribe())){
//                    helper.setVisible(R.id.tv_libs_feed_txt_describe, true);
//                    helper.setText(R.id.tv_libs_feed_txt_describe, feed.getDescribe());
//                }else {
//                    helper.getView(R.id.tv_libs_feed_txt_describe).setVisibility(View.GONE);
//                }
                break;
            }
        }
    }

}
