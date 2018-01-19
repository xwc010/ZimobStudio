package cc.yujie.libs.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cc.yujie.libs.R;
import cc.yujie.libs.constant.DesplayMode;
import cc.yujie.libs.constant.EFeedType;
import cc.yujie.libs.model.Feed;
import cc.yujie.libs.utils.YJLUrls;
import cc.zimo.imageplugs.ZMImgLoader;

/**
 * Created by xwc on 2018/1/11.
 */

public class YJLFeedAdapter extends BaseMultiItemQuickAdapter<Feed, BaseViewHolder> {

    public YJLFeedAdapter(Context context, List data) {
        super(data);
        addItemType(DesplayMode.LARGE, R.layout.item_libs_feed_large);
        addItemType(DesplayMode.LEFT, R.layout.item_libs_feed_left);
        addItemType(DesplayMode.RIGHT, R.layout.item_libs_feed_right);
        addItemType(DesplayMode.GRIDS, R.layout.item_libs_feed_grids);
        addItemType(DesplayMode.TEXT, R.layout.item_libs_feed_txt);
    }

    @Override
    protected void convert(BaseViewHolder helper, Feed feed) {
        setFlagBottom(helper, feed);
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
                ZMImgLoader.getInstance().loadImg(getThumbnail(feed), img_icon);

                if (feed.getType() == EFeedType.VIDEO) {
                    helper.setVisible(R.id.img_libs_feed_large_video_tag, true);
                    // 显示时长
                    helper.setVisible(R.id.tv_libs_comm_feed_video_imgt_num, false);
                } else {
                    helper.setVisible(R.id.img_libs_feed_large_video_tag, false);
                    if(feed.getAlbum() != null && feed.getAlbum().size() > 1){
                        helper.setText(R.id.tv_libs_comm_feed_video_imgt_num, "多图");
                        helper.setVisible(R.id.tv_libs_comm_feed_video_imgt_num, true);
                    }else {
                        helper.setVisible(R.id.tv_libs_comm_feed_video_imgt_num, false);
                    }
                }

                break;
            }
            case DesplayMode.LEFT: {
                helper.setText(R.id.tv_libs_feed_left_title, feed.getTitle());
                ImageView img_icon = helper.getView(R.id.img_libs_feed_left_cion);
                ZMImgLoader.getInstance().loadImg(getThumbnail(feed), img_icon);

                if (feed.getType() == EFeedType.VIDEO) {
                    helper.setVisible(R.id.img_libs_feed_left_video_tag, true);
                    // 显示时长
                    helper.setVisible(R.id.tv_libs_comm_feed_video_imgt_num, false);
                } else {
                    helper.setVisible(R.id.img_libs_feed_left_video_tag, false);
                    if(feed.getAlbum() != null && feed.getAlbum().size() > 1){
                        helper.setText(R.id.tv_libs_comm_feed_video_imgt_num, "多图");
                        helper.setVisible(R.id.tv_libs_comm_feed_video_imgt_num, true);
                    }else {
                        helper.setVisible(R.id.tv_libs_comm_feed_video_imgt_num, false);
                    }
                }
                break;
            }
            case DesplayMode.RIGHT: {
                helper.setText(R.id.tv_libs_feed_right_title, feed.getTitle());
                ImageView img_icon = helper.getView(R.id.img_libs_feed_right_cion);
                ZMImgLoader.getInstance().loadImg(getThumbnail(feed), img_icon);

                if (feed.getType() == EFeedType.VIDEO) {
                    helper.setVisible(R.id.img_libs_feed_right_video_tag, true);
                    // 显示时长
                    helper.setVisible(R.id.tv_libs_comm_feed_video_imgt_num, false);
                } else {
                    helper.setVisible(R.id.img_libs_feed_right_video_tag, false);
                    if(feed.getAlbum() != null && feed.getAlbum().size() > 1){
                        helper.setText(R.id.tv_libs_comm_feed_video_imgt_num, "多图");
                        helper.setVisible(R.id.tv_libs_comm_feed_video_imgt_num, true);
                    }else {
                        helper.setVisible(R.id.tv_libs_comm_feed_video_imgt_num, false);
                    }
                }
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
                if (!TextUtils.isEmpty(feed.getDescribe())) {
                    helper.setVisible(R.id.tv_libs_feed_txt_describe, true);
                    helper.setText(R.id.tv_libs_feed_txt_describe, feed.getDescribe());
                } else {
                    helper.getView(R.id.tv_libs_feed_txt_describe).setVisibility(View.GONE);
                }
                break;
            }
        }
    }

    private String getThumbnail(Feed feed) {
        return YJLUrls.getThumbnailUrl(feed.getId() + "/" + feed.getThumbnail());
    }

    private void setFlagBottom(BaseViewHolder helper, Feed feed) {
        TextView tv_flag = helper.getView(R.id.tv_libs_feed_item_flag);
        tv_flag.setVisibility(View.VISIBLE);
        switch (feed.getFlag()) {
            case AD: {
                tv_flag.setTextColor(tv_flag.getContext().getResources().getColor(R.color.color_libs_blue_b));
                tv_flag.setText("广告");
                tv_flag.setCompoundDrawables(null, null, null, null);
                break;
            }
            case HOT: {
                tv_flag.setTextColor(tv_flag.getContext().getResources().getColor(R.color.color_libs_red));
                tv_flag.setText("热");
                Drawable drawable = tv_flag.getContext().getResources().getDrawable(R.drawable.ic_libs_hot);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth() * 3 / 4, drawable.getMinimumHeight() * 3 / 4);
                tv_flag.setCompoundDrawables(drawable, null, null, null);
                break;
            }
            case NEW: {
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
        helper.setVisible(R.id.tv_libs_feed_item_time, false);
        if (feed.getPublisher() == null) {
            helper.setText(R.id.tv_libs_feed_item_poster, publishers[(int) feed.getId() % 15]);
        } else {
            helper.setText(R.id.tv_libs_feed_item_poster, feed.getPublisher().getName());
        }

        helper.setText(R.id.tv_libs_feed_item_brow_num, String.valueOf(feed.getBrowse_num()));
        helper.getView(R.id.img_libs_feed_item_more_icon).setVisibility(View.GONE);
    }

    private String[] publishers = {"火星人播报", "YaYa喵萌", "Angler MM", "八卦达人", "圈外评价人"
            , "黑话大叔", "暴走橙子", "胡来", "Cona Da", "BiSai"
            , "8号当铺", "小春看世界", "都是那点事", "积木火车", "CoolSee"};
}
