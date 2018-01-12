package cc.zimo.adplugs.ads;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ScreenUtils;

import cc.zimo.adplugs.R;
import cc.zimo.adplugs.utils.AdSize;

public abstract class InterstitialAdAdapter extends AdAdapter {

    public boolean isReady() {
        return true;
    }

    public abstract void showInterstitial();

    /**
     * 对原生广告中的图片进行合理的宽度设置
     *
     * @param adView
     */
    public static void setImageWidth(ViewGroup adView) {
        ImageView imageView = (ImageView) adView.findViewById(R.id.yifants_nativeAdMedia);
        if (ScreenUtils.isTablet()) {
            float imageHeight = 500;
            float imageWidth = 1024;

            float layoutWidth = 480;
            float layoutHeight = 800;

            float screenWidth = AdSize.getWidthPixels() * 0.9f;
            float screenHeight = AdSize.getHeightPixels() * 0.9f;

            float wBh = layoutWidth / layoutHeight;
            float wBhN = screenWidth / screenHeight;

            float scale;

            //计算广告缩放比例
            if (wBh < wBhN) {
                scale = screenHeight / layoutHeight;
            } else {
                scale = screenWidth / layoutWidth;
            }

            layoutWidth = layoutWidth * scale;
            layoutHeight = layoutHeight * scale;

            wBh = imageWidth / imageHeight;
            wBhN = layoutWidth / layoutHeight;

            //计算广告缩放比例
            if (wBh < wBhN) {
                scale = layoutHeight / imageHeight;
            } else {
                scale = layoutWidth / imageWidth;
            }

            imageWidth = imageWidth * scale;
            imageHeight = imageHeight * scale;

            RelativeLayout layout = (RelativeLayout) adView.findViewById(R.id.yifants_rootLayout);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) layoutWidth, (int) layoutHeight);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

            layout.setLayoutParams(layoutParams);
            imageView.setMaxWidth((int) imageWidth);
        } else {
            imageView.setMaxWidth(AdSize.getWidthPixels());
        }
    }

}
