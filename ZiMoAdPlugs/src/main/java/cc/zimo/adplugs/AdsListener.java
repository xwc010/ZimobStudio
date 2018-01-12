package cc.zimo.adplugs;

import android.text.TextUtils;

import cc.zimo.adplugs.ads.AdAdapter;
import cc.zimo.adplugs.manager.AdManager;
import cc.zimo.adplugs.manager.BannerManager;
import cc.zimo.adplugs.manager.BaseManager;
import cc.zimo.adplugs.manager.InterstitialManager;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.model.AdType;
import cc.zimo.adplugs.utils.AdLog;

/**
 * @author yeahyf
 * @date 2017/5/22
 * SDK内部广告统一的监听器，负责输出广告流程控制日志
 * 同时处理开发者提供的监听器回调
 */


public class AdsListener extends AdListener {

    //供开发者使用的广告监听器
    public AdListener mAdListener;


    /**
     * 广告开始初始化
     *
     * @param ad
     */
    @Override
    public void onAdInit(AdData ad, String adsid) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        AdLog.d(ad.name, ad.type, null, "init ad, id==>" + adsid);
        if (mAdListener != null) {
            mAdListener.onAdInit(ad, adsid);
        }

    }

    /**
     * 广告开始加载
     *
     * @param ad
     */
    @Override
    public void onAdStartLoad(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        if (ad instanceof AdData) {
            AdLog.d(ad.name, ad.type, null, "load ad, id = " + ((AdData) ad).adId);
        } else {
            AdLog.d(ad.name, ad.type, null, "load ad");
        }

        if (mAdListener != null) {
            mAdListener.onAdStartLoad(ad);
        }
    }


    /**
     * 本方法作为计算预备展示广告的提示
     *
     * @param ad
     */
    public void onAdWillShow(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        AdLog.d(ad.name, ad.type, null, "will show!");
    }

    /**
     * 广告加载缓存完成
     *
     * @param ad
     */
    @Override
    public void onAdLoadSucceeded(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        AdLog.d(ad.name, ad.type, null, "cache success!");

        if (mAdListener != null) {
            mAdListener.onAdLoadSucceeded(ad);
        }
    }

    /**
     * 广告加载缓存完成,内部调用
     *
     * @param ad
     */
    @Override
    public void onAdLoadSucceeded(AdData ad, AdAdapter adAdapter) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        adAdapter.adLoadedTime = System.currentTimeMillis();
        AdLog.d(ad.name, ad.type, null, "cache success!");

        if (mAdListener != null) {
            mAdListener.onAdLoadSucceeded(ad);
        }
    }

    /**
     * 无广告填充
     *
     * @param ad
     */
    @Override
    public void onAdNoFound(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        AdLog.d(ad.name, ad.type, null, "no filled!");

        if (mAdListener != null) {
            mAdListener.onAdNoFound(ad);
        }
    }

    /**
     * 广告处理出现异常
     *
     * @param ad
     */
    @Override
    public void onAdError(AdData ad, String message, Exception e) {
        if (e != null) {
            AdLog.e(message, e);
        } else {
            if (ad != null) {
                AdLog.d(ad.name, ad.type, null, "error:" + message);
                if (mAdListener != null) {
                    mAdListener.onAdError(ad, message, e);
                }
            } else {
                AdLog.e("ad is null!");
            }
        }
    }

    /**
     * 广告被打开，开始展示，用于插屏，banenr与原生等
     * 静态广告
     *
     * @param ad
     */
    @Override
    public void onAdShow(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        AdLog.d(ad.name, ad.type, null, "ad show!");

        if (AdType.TYPE_INTERSTITIAL.equals(ad.type)) {
            InterstitialManager.isShowInterstitial = true;
            BaseManager.lastShowTime = System.currentTimeMillis();
        }

        if (mAdListener != null) {
            mAdListener.onAdShow(ad);
        }
    }

    /**
     * 广告在展示过程中，用于视频的播放过程
     *
     * @param ad
     */
    @Override
    public void onAdView(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        AdLog.d(ad.name, ad.type, null, "ad view!");

        if (mAdListener != null) {
            mAdListener.onAdView(ad);
        }
    }

    /**
     * 广告被点击
     *
     * @param ad
     */
    @Override
    public void onAdClicked(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        AdLog.d(ad.name, ad.type, null, "ad clicked!");

        if (mAdListener != null) {
            mAdListener.onAdClicked(ad);
        }
    }

    /**
     * 广告展示结束，主要用于视频展示结束
     *
     * @param ad
     */
    @Override
    public void onAdViewEnd(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        AdLog.d(ad.name, ad.type, null, "view end!");

        if (mAdListener != null) {
            mAdListener.onAdViewEnd(ad);
        }
    }

    /**
     * 广告被关闭
     *
     * @param ad
     */
    @Override
    public void onAdClosed(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }
        AdLog.d(ad.name, ad.type, null, "ad closed!");

        if (AdType.TYPE_INTERSTITIAL.equals(ad.type)) {
            InterstitialManager.getInstance().removeInterstitial();
            InterstitialManager.isShowInterstitial = false;
            ZiMoAdsAdgent.Main_Handler.post(new Runnable() {
                @Override
                public void run() {
                    AdManager.cacheAd(AdType.TYPE_INTERSTITIAL);
                }
            });
        }

        if (AdType.TYPE_VIDEO.equals(ad.type)) {
            ZiMoAdsAdgent.Main_Handler.post(new Runnable() {
                @Override
                public void run() {
                    AdManager.cacheAd(AdType.TYPE_VIDEO);
                }
            });
        }

        if (mAdListener != null) {
            mAdListener.onAdClosed(ad);
        }
    }

    /**
     * 激励性广告的奖励
     *
     * @param ad
     */
    @Override
    public void onRewarded(AdData ad) {
        if (ad == null) {
            AdLog.e("ad is null!");
            return;
        }

        AdLog.d(ad.name, ad.type, null, "ad rewarded!");

        if (mAdListener != null) {
            mAdListener.onRewarded(ad);
        }
    }
}
