package cc.zimo.adplugs.manager;

import com.blankj.utilcode.util.ActivityUtils;

import cc.zimo.adplugs.ZiMoAdsAdgent;
import cc.zimo.adplugs.ads.InterstitialAdAdapter;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.model.AdType;
import cc.zimo.adplugs.utils.AdLog;
import cc.zimo.adplugs.utils.AdPlatform;

/**
 * @author wowser
 * @date 2016/5/26
 */

public final class InterstitialManager extends BaseManager {

    private static InterstitialManager manager = new InterstitialManager();

    public static InterstitialManager getInstance() {
        return manager;
    }

    private AdData adData;
    private boolean isShow;
    public static boolean isShowInterstitial;

    InterstitialAdAdapter interstitial = null;

    /**
     * 插屏
     */
    public void showInterstitial() {

        //根据策略获取数据对象
        adData = AdStrategy.getInstance().getAdData(AdType.TYPE_INTERSTITIAL);

        if (adData != null) {
            //获取能够展示的插屏对象
            interstitial = AdManager.interstitialMap.get(adData.name);
            if (interstitial != null) {
                adData.name = interstitial.getName();
                ZiMoAdsAdgent.adsListener.onAdWillShow(adData);
                //显示插屏广告
                try {
                    ZiMoAdsAdgent.Main_Handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                interstitial.showInterstitial();
                            } catch (Exception e) {
                                AdLog.e("showInterstitial e", e);
                            }
                        }
                    });
                } catch (Exception e) {
                    AdLog.e("showInterstitial e", e);
                }
            }
        }
    }

    /**
     * 是否有插屏
     *
     * @param page
     * @return
     */
    public boolean hasInterstitial(String page) {
        try {
            return AdManager.hasInterstitial(page);
        } catch (Exception e) {
            AdLog.e("hasInterstitial e", e);
        }
        return false;
    }

    public void removeInterstitial() {
        try {
            if (interstitial != null) {
                interstitial.onDestroy(ActivityUtils.getTopActivity());
                interstitial = null;
            }
        } catch (Exception e) {
            AdLog.e(e);
        }
    }

}
