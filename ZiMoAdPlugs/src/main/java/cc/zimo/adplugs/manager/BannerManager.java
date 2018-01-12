package cc.zimo.adplugs.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

import cc.zimo.adplugs.ZiMoAdsAdgent;
import cc.zimo.adplugs.utils.AdLog;
import cc.zimo.adplugs.utils.AdSize;
import cc.zimo.adplugs.utils.Constant;
import cc.zimo.adplugs.utils.ViewHelper;
import cc.zimo.adplugs.view.AdView;

/**
 * @author wowser
 * @date 2016/5/31
 */

public class BannerManager {
    private static Map<Integer, FrameLayout> bannerLayoutMap = new HashMap<>();

    private static BannerManager manager = new BannerManager();

    public static BannerManager getInstance() {
        return manager;
    }

    private AdView adBannerView;

    /**
     * 生命周期调用方法 用来初始化banner父容器布局
     *
     * @param activity
     */
    public static void onCreate(Activity activity) {
        int hashCode = activity.hashCode();
        if (!bannerLayoutMap.containsKey(hashCode)) {
            FrameLayout layout = new FrameLayout(activity);
            layout.setBackgroundColor(Color.TRANSPARENT);

            ViewGroup viewGroup;
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Constant.transparentNavBar) {
                viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
            } else {
                viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            }

            viewGroup.addView(layout, layoutParams);

            bannerLayoutMap.put(hashCode, layout);
        }
    }

    /**
     * 生命周期调用方法 用来销毁banner父容器布局
     *
     * @param activity
     */
    public static void onDestroy(Activity activity) {
        int hashCode = activity.hashCode();
        if (bannerLayoutMap.containsKey(hashCode)) {
            FrameLayout layout = bannerLayoutMap.get(hashCode);
            layout.removeAllViews();
            if (layout.getParent() != null) {
                ((ViewGroup) layout.getParent()).removeView(layout);
            }
            bannerLayoutMap.remove(hashCode);
        }
    }

    /**
     * 检测是否有banner广告
     *
     * @return
     */
    public boolean hasBanner() {
        return AdManager.hasBanner();
    }

    /**
     * 展示banner广告
     *
     * @param activity
     * @param gravity
     */
    public void showBanner(Activity activity, int gravity) {
        if (activity != null) {
            try {
                if (adBannerView == null) {
                    adBannerView = loadBannerView(activity);
                }
                if (adBannerView.getParent() != null) {
                    ((ViewGroup) adBannerView.getParent()).removeView(adBannerView);
                }


                //remove all child views from the ViewGroup
                adBannerView.removeAllViews();

                ViewHelper.setScaleY(adBannerView, 1);
                ViewHelper.setScaleX(adBannerView, 1);

                ViewHelper.setTranslationY(adBannerView, 0);
                ViewHelper.setTranslationX(adBannerView, 0);

                ViewHelper.setRotation(adBannerView, 0);

                FrameLayout adLayout = bannerLayoutMap.get(activity.hashCode());
                adLayout.addView(adBannerView);

                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) adBannerView.getLayoutParams();
                int bannerHeight = layoutParams.height;

                if (Constant.screenDirection == Gravity.NO_GRAVITY) {
                    layoutParams.gravity = gravity | Gravity.CENTER_HORIZONTAL;
                } else {
                    layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                    if (Constant.screenDirection == Gravity.LEFT) {
                        if (gravity == Gravity.BOTTOM) {
                            ViewHelper.setTranslationY(adBannerView, (AdSize.getHeightPixels() / 2) - (bannerHeight / 2));
                            ViewHelper.setRotation(adBannerView, 90);
                            ViewHelper.setTranslationX(adBannerView, -((AdSize.getWidthPixels() / 2) - (bannerHeight / 2)));
                        } else if (gravity == Gravity.TOP) {
                            ViewHelper.setTranslationY(adBannerView, (AdSize.getHeightPixels() / 2) - (bannerHeight / 2));
                            ViewHelper.setRotation(adBannerView, 90);
                            ViewHelper.setTranslationX(adBannerView, ((AdSize.getWidthPixels() / 2) - (bannerHeight / 2)));
                        }
                    } else if (Constant.screenDirection == Gravity.RIGHT) {
                        if (gravity == Gravity.BOTTOM) {
                            ViewHelper.setTranslationY(adBannerView, (AdSize.getHeightPixels() / 2) - (bannerHeight / 2));
                            ViewHelper.setRotation(adBannerView, -90);
                            ViewHelper.setTranslationX(adBannerView, ((AdSize.getWidthPixels() / 2) - (bannerHeight / 2)));
                        } else if (gravity == Gravity.TOP) {
                            ViewHelper.setTranslationY(adBannerView, (AdSize.getHeightPixels() / 2) - (bannerHeight / 2));
                            ViewHelper.setRotation(adBannerView, -90);
                            ViewHelper.setTranslationX(adBannerView, -((AdSize.getWidthPixels() / 2) - (bannerHeight / 2)));
                        }
                    }
                }
            } catch (Exception e) {
                AdLog.e("add banner error!", e);
            }
        }

        if (adBannerView != null && adBannerView.getParent() != null) {
            adBannerView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取banner条视图
     *
     * @param context
     * @return
     */
    public AdView getBannerView(Context context) {
        if (adBannerView == null) {
            adBannerView = loadBannerView(context);
        }

        ViewGroup.LayoutParams layoutParams = adBannerView.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams1.gravity = Gravity.NO_GRAVITY;
        }

        if (adBannerView.getParent() != null) {
            ((ViewGroup) adBannerView.getParent()).removeView(adBannerView);
        }
        return adBannerView;
    }

    /**
     * 加载动态布局
     *
     * @param context
     * @return
     */
    private static AdView loadBannerView(Context context) {
        AdView adView = new AdView(context);
        adView.setTag(100);
        adView.setBackgroundColor(Color.TRANSPARENT);
        adView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        return adView;
    }

    /**
     * 隐藏banner条
     */
    public void hideBanner() {
        try {
            if (adBannerView != null) {
                ZiMoAdsAdgent.Main_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            adBannerView.setVisibility(View.INVISIBLE);
                        } catch (Exception e) {
                            AdLog.e(e);
                        }
                    }
                });
            }
        } catch (Exception e) {
            AdLog.e("hide banner error!", e);
        }
    }

    /**
     * 将广告窗从屏幕上移除。
     *
     * @param context
     */
    public void removeBanner(Context context) {
        try {
            if (context != null) {
                if (adBannerView != null && adBannerView.getParent() != null) {
                    ((ViewGroup) adBannerView.getParent()).removeView(adBannerView);
                }
            }
        } catch (Exception e) {
            AdLog.e("remove banner error!", e);
        }
    }

}