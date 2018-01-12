package cc.zimo.adplugs.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cc.zimo.adplugs.ZiMoAdsAdgent;
import cc.zimo.adplugs.ads.BannerAdAdapter;
import cc.zimo.adplugs.manager.AdManager;
import cc.zimo.adplugs.manager.AdStrategy;
import cc.zimo.adplugs.manager.AppConfig;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.model.AdType;
import cc.zimo.adplugs.utils.AdLog;
import cc.zimo.adplugs.utils.AdSize;
import cc.zimo.adplugs.utils.Constant;
import cc.zimo.adplugs.utils.ViewHelper;


public class AdView extends RelativeLayout {
    private BannerAdAdapter banner;
    private AdData adData;
    private int refreshTime = 5000;

    public AdView(Context context) {
        this(context, null);
    }

    public AdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
    }

    public void reSize(int width, int height) {
        ViewGroup.LayoutParams lParams = getLayoutParams();
        int designHeight = lParams.height;
        int designWidth = lParams.width;

        //缩放广告大小
        float wBh = (designWidth * 1.0f) / (designHeight * 1.0f);
        float wBhN = (width * 1.0f) / (height * 1.0f);
        float scale, tranX = 0, tranY = 0;

        //计算广告缩放比例
        if (wBh < wBhN) {
            scale = height * 1.0f / designHeight;
        } else {
            scale = width * 1.0f / designWidth;
        }

        tranY = (designHeight - height) / 2;
        tranX = (designWidth - width) / 2;

        ViewHelper.setTranslationX(this, -tranX);
        ViewHelper.setTranslationY(this, -tranY);

        ViewHelper.setScaleX(this, scale);
        ViewHelper.setScaleY(this, scale);

        ViewHelper.setRotation(this, 0);
    }

    public BannerAdAdapter getBanner() {
        return banner;
    }

    /**
     * 加载下一条广告
     */
    public void loadNextAd() {
        ZiMoAdsAdgent.Main_Handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    int designHeight;
                    int designWidth;

                    if (AppConfig.getInstance().banner_style == 0) {
                        designWidth = (int) (320 * AdSize.density);
                        designHeight = (int) (50 * AdSize.density);
                    } else {
                        if (Constant.screenDirection == Gravity.NO_GRAVITY) {
                            designWidth = AdSize.getWidthPixels();
                        } else {
                            designWidth = AdSize.getHeightPixels();
                        }
                        designHeight = AdSize.bannerHeight;
                    }

                    ViewGroup.LayoutParams layoutParams = getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new ViewGroup.LayoutParams(designWidth, designHeight);
                    }
                    layoutParams.width = designWidth;
                    layoutParams.height = designHeight;
                    setLayoutParams(layoutParams);

                    //根据全局广告权重判断是展示广告
                    adData = AdStrategy.getInstance().getAdData(AdType.TYPE_BANNER);
                    if (adData != null) {
                        banner = AdManager.bannerMap.get(adData.name);
                        if (banner != null && banner.isReady()) {
                            //获取banner的视图
                            View view = banner.getBannerView();
                            if (view == null) {
                                AdManager.cacheAd(AdType.TYPE_BANNER);
                                return;
                            }
                            adData.name = banner.getName();
                            ZiMoAdsAdgent.adsListener.onAdWillShow(adData);
                            //父视图不包含当前要展示的子视图
                            if (getChildCount() == 0 || getChildAt(0) != view) {
                                removeAllViews();
                                addView(view);
                            }
                            ZiMoAdsAdgent.adsListener.onAdShow(adData);
                        }
                    }
                    AdManager.cacheAd(AdType.TYPE_BANNER);
                } catch (Exception e) {
                    AdLog.e("AdView loadNextAd e", e);
                }
            }
        });
    }

    private boolean hasBanner() {
        return AdManager.hasBanner();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            start();
        } else {
            stop();
        }
    }

    private Handler mHandler;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (!hasBanner()) {
                removeAllViews();
                mHandler.postDelayed(this, 3000);
                return;
            }

            loadNextAd();

            refreshTime = 60 * 1000;
            mHandler.postDelayed(this, refreshTime);
        }
    };

    private void stop() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
            mHandler = null;
        }
    }

    private void start() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.myLooper());
            mHandler.post(mRunnable);
        }
    }

}
