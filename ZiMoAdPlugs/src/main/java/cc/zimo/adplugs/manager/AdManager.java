package cc.zimo.adplugs.manager;

import android.app.Activity;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.zimo.adplugs.ZiMoAdsAdgent;
import cc.zimo.adplugs.ads.AdAdapter;
import cc.zimo.adplugs.ads.BannerAdAdapter;
import cc.zimo.adplugs.ads.InterstitialAdAdapter;
import cc.zimo.adplugs.ads.VideoAdAdapter;
import cc.zimo.adplugs.ads.duapps.DuInterstitial;
import cc.zimo.adplugs.ads.duapps.DuSdk;
import cc.zimo.adplugs.ads.duapps.DuVideo;
import cc.zimo.adplugs.ads.youmi.AdXmiInterstitial;
import cc.zimo.adplugs.ads.youmi.AdXmiSdk;
import cc.zimo.adplugs.ads.youmi.AdXmiVideo;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.model.AdType;
import cc.zimo.adplugs.utils.AdLog;
import cc.zimo.adplugs.utils.AdPlatform;
import cc.zimo.adplugs.utils.Constant;

/**
 * Created by Wowser on 2017/5/8.
 *
 * @author yanli
 */
public final class AdManager {

    //以下平台SDK初始化特殊，需要单独控制
    /**
     * ironSource 是否初始化标志
     */
    public static boolean ironSourceInitialized = false;
    /*mnt 是否初始化标志*/
    public static boolean batmobiInitialized = false;

    public static Map<String, InterstitialAdAdapter> interstitialMap = new HashMap<>();
    public static Map<String, BannerAdAdapter> bannerMap = new HashMap<>();
    public static Map<String, VideoAdAdapter> videoMap = new HashMap<>();

    static {

        if (AdPlatform.isPlatformSdkIn(AdPlatform.NAME_DUAPPS)) {
            interstitialMap.put(AdPlatform.NAME_DUAPPS, DuInterstitial.getInstance());
        }

        if (AdPlatform.isPlatformSdkIn(AdPlatform.NAME_ADXMI)) {
            interstitialMap.put(AdPlatform.NAME_ADXMI, AdXmiInterstitial.getInstance());
        }
    }

    static {

//        if (AdPlatform.isPlatformSdkIn(AdPlatform.NAME_APPLOVIN)) {
//            bannerMap.put(AdPlatform.NAME_APPLOVIN, AppLovinBanner.getInstance());
//        }
    }

    static {
        if (AdPlatform.isPlatformSdkIn(AdPlatform.NAME_ADXMI)) {
            videoMap.put(AdPlatform.NAME_ADXMI, AdXmiVideo.getInstance());
        }
        if (AdPlatform.isPlatformSdkIn(AdPlatform.NAME_DUAPPS)) {
            videoMap.put(AdPlatform.NAME_DUAPPS, DuVideo.getInstance());
        }
    }

    /**
     * 广告统一初始化
     */
    public static void initAd() {
        ZiMoAdsAdgent.Main_Handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    cacheAd(AdType.TYPE_INTERSTITIAL);
                    cacheAd(AdType.TYPE_BANNER);
                    cacheAd(AdType.TYPE_VIDEO);
                    //以上执行完成之后，则必然会初始化，还有一种情况是他们没有权重，
                } catch (Exception e) {
                    AdLog.e("cacheAd error!", e);
                }
            }
        });
    }

    /**
     * onResume 生命周期 统一管理
     *
     * @param activity
     */
    public static void onResume(Activity activity) {
        for (InterstitialAdAdapter interstitial : interstitialMap.values()) {
            interstitial.onResume(activity);
        }

        for (BannerAdAdapter banner : bannerMap.values()) {
            banner.onResume(activity);
        }

        for (VideoAdAdapter video : videoMap.values()) {
            video.onResume(activity);
        }


        //防止YOUMI不做初始化，或初始化过期
        if (AdPlatform.isPlatformSdkIn(AdPlatform.NAME_ADXMI)) {
            AdXmiSdk.initAd();
        }

        if (AdPlatform.isPlatformSdkIn(AdPlatform.NAME_AXNATIVE)) {
            AdXmiSdk.initAd();
        }
    }

    /**
     * onPause 生命周期 统一管理
     *
     * @param activity
     */
    public static void onPause(Activity activity) {
        for (InterstitialAdAdapter interstitial : interstitialMap.values()) {
            interstitial.onPause(activity);
        }

        for (BannerAdAdapter banner : bannerMap.values()) {
            banner.onPause(activity);
        }

        for (VideoAdAdapter video : videoMap.values()) {
            video.onPause(activity);
        }

    }

    /**
     * onDestroy 生命周期 统一管理
     *
     * @param activity
     */
    public static void onDestroy(Activity activity) {
        for (InterstitialAdAdapter interstitial : interstitialMap.values()) {
            interstitial.onDestroy(activity);
        }

        for (BannerAdAdapter banner : bannerMap.values()) {
            banner.onDestroy(activity);
        }

        for (VideoAdAdapter video : videoMap.values()) {
            video.onDestroy(activity);
        }
    }

    /**
     * 重置广告加载状态 重新缓存广告
     */
    public static void resetAd() {
        for (InterstitialAdAdapter interstitial : interstitialMap.values()) {
            interstitial.reset();
        }

        for (BannerAdAdapter banner : bannerMap.values()) {
            banner.reset();
        }

        for (VideoAdAdapter video : videoMap.values()) {
            video.reset();
        }

        cacheAd(AdType.TYPE_INTERSTITIAL);
        cacheAd(AdType.TYPE_BANNER);
        cacheAd(AdType.TYPE_VIDEO);
    }

    /**
     * 对部分需要特殊初始化的SDK进行处理
     * 制作一次初始化即可
     *
     * @param adapter
     */
    private static void initialize(AdAdapter adapter) {

        //adxmi 有米广告
        if (AdPlatform.NAME_ADXMI.equals(adapter.getName())) {
            AdXmiSdk.initAd();
        }

        if (AdPlatform.NAME_DUAPPS.equals(adapter.getName())) {
            DuSdk.init();
        }
    }

    /**
     * 缓存广告
     *
     * @param adType 广告类型
     */

    public static void cacheAd(final String adType) {
        if (TextUtils.isEmpty(adType)) {
            return;
        }
        ZiMoAdsAdgent.Main_Handler.post(new Runnable() {
            @Override
            public void run() {
                try {

                    //获取所有权重大于零的广告数据
                    List<AdData> adDatas = AdStrategy.getInstance().adDateMap.get(adType);

                    if (adDatas == null || adDatas.size() == 0) {
                        return;
                    }

                    //缓存平台广告
                    for (final AdData adData : adDatas) {
                        //缓存interstitial广告
                        if (AdType.TYPE_INTERSTITIAL.equals(adType)) {
                            if (interstitialMap.containsKey(adData.name)) {
                                final InterstitialAdAdapter interstitial = interstitialMap.get(adData.name);
                                interstitial.adData = adData;
                                initialize(interstitial);
                                boolean ready = isAdEffective(interstitial);
                                AdLog.d(adData.name, AdType.TYPE_INTERSTITIAL, null, "ready=" + ready);
                                if (!ready) {
                                    AdLog.d(adData.name, AdType.TYPE_INTERSTITIAL, null, "start cache");
                                    interstitial.loadAd(adData);
                                }
                            }
                        }

                        //缓存banner广告
                        if (AdType.TYPE_BANNER.equals(adType)) {
                            if (bannerMap.containsKey(adData.name)) {
                                final BannerAdAdapter banner = bannerMap.get(adData.name);
                                banner.adData = adData;
                                initialize(banner);
                                boolean ready = isAdEffective(banner);
                                AdLog.d(adData.name, AdType.TYPE_BANNER, null, "ready=" + ready);
                                if (!ready) {
                                    AdLog.d(adData.name, AdType.TYPE_BANNER, null, "start cache");
                                    banner.loadAd(adData);
                                }
                            }
                        }
                        //缓存video广告
                        if (AdType.TYPE_VIDEO.equals(adType)) {
                            if (videoMap.containsKey(adData.name)) {
                                final VideoAdAdapter video = videoMap.get(adData.name);
                                video.adData = adData;
                                initialize(video);
                                boolean ready = isAdEffective(video);
                                AdLog.d(adData.name, AdType.TYPE_VIDEO, null, "ready=" + ready);
                                if (!ready) {
                                    AdLog.d(adData.name, AdType.TYPE_VIDEO, null, "start cache");
                                    video.loadAd(adData);
                                }
                            }
                        }
                    }

                    if (AdType.TYPE_BANNER.equals(adType)) {
                        Constant.isLoadBanner = true;
                    }
                } catch (Exception e) {
                    AdLog.e("cacheAd e", e);
                }
            }
        });
    }


    /***
     * 是否有interstitial广告
     * @param page
     * @return
     */
    public static boolean hasInterstitial(String page) {
        try {
            List<AdData> adDatas = AdStrategy.getInstance().getItem(AdType.TYPE_INTERSTITIAL);
            for (final AdData adData : adDatas) {
                if (interstitialMap.containsKey(adData.name)) {
                    InterstitialAdAdapter interstitial = interstitialMap.get(adData.name);
                    boolean ready = isAdEffective(interstitial) && interstitial.isReady();
                    AdLog.d(adData.name, AdType.TYPE_INTERSTITIAL, null, "ready=" + ready);
                    if (ready) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            AdLog.e("hasInterstitial e", e);
        }

        cacheAd(AdType.TYPE_INTERSTITIAL);

        return false;
    }

    /**
     * 是否有banner广告
     *
     * @return
     */
    public static boolean hasBanner() {
        try {
            List<AdData> adDatas = AdStrategy.getInstance().getItem(AdType.TYPE_BANNER);
            if (adDatas != null) {

                for (final AdData adData : adDatas) {
                    if (bannerMap.containsKey(adData.name)) {
                        BannerAdAdapter banner = bannerMap.get(adData.name);
                        boolean ready = banner.isReady();
                        AdLog.d(adData.name, AdType.TYPE_BANNER, null, "ready=" + ready);
                        if (ready) {
                            return true;
                        }
                    }
                }
            }

        } catch (Exception e) {
            AdLog.e("hasBanner e", e);
        }

        cacheAd(AdType.TYPE_BANNER);

        return false;
    }


    /**
     * 是否有video广告
     *
     * @return
     */
    public static boolean hasVideo() {
        try {
            List<AdData> adDatas = AdStrategy.getInstance().getItem(AdType.TYPE_VIDEO);
            for (final AdData adData : adDatas) {
                if (videoMap.containsKey(adData.name)) {
                    VideoAdAdapter video = videoMap.get(adData.name);
                    boolean ready = isAdEffective(video);
                    AdLog.d(adData.name, AdType.TYPE_VIDEO, null, "ready=" + ready);
                    if (ready) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            AdLog.e("hasVideo error", e);
        }
        return false;
    }


    /**
     * 广告是否过期
     *
     * @return
     */
    public static boolean isAdEffective(AdAdapter adAdapter) {

        if (adAdapter == null) {
            AdLog.d("Ad is invalid because object is null");
            return false;
        }
        boolean isReady = false;
        long intervals;//间隔时间
        if (adAdapter.adLoadedTime == 0) {
            intervals = 0;
        } else {
            intervals = (System.currentTimeMillis() - adAdapter.adLoadedTime) / 1000;
        }

        String adTimeout;
        if (AppConfig.getInstance().adTimeoutMap.containsKey(adAdapter.getName())) {
            adTimeout = AppConfig.getInstance().adTimeoutMap.get(adAdapter.getName());
        } else {
            adTimeout = AppConfig.getInstance().adTimeoutMap.get("default");
        }
        int result;
        if (TextUtils.isEmpty(adTimeout)) {
            result = 3600;
        } else {
            result = Integer.parseInt(adTimeout);
            if (result <= 0) {
                result = 3600;
            }
        }

        return isReady;
    }

    public static List<AdAdapter> getEffectiveAdList(String adType) {
        List<AdAdapter> effectiveAdList = new ArrayList<>();
        if (AdType.TYPE_INTERSTITIAL.equals(adType)) {
            for (Map.Entry ad : interstitialMap.entrySet()) {
                InterstitialAdAdapter interstitial = (InterstitialAdAdapter) ad.getValue();
                boolean ready = isAdEffective(interstitial) && interstitial.isReady();
                AdLog.d(interstitial.getName(), AdType.TYPE_INTERSTITIAL, null, "ready=" + ready);
                if (ready) {
                    effectiveAdList.add(interstitial);
                }
            }
        }
        if (AdType.TYPE_VIDEO.equals(adType)) {
            for (Map.Entry ad : videoMap.entrySet()) {
                VideoAdAdapter video = (VideoAdAdapter) ad.getValue();
                boolean ready = isAdEffective(video);
                AdLog.d(video.getName(), AdType.TYPE_VIDEO, null, "ready=" + ready);
                if (ready) {
                    effectiveAdList.add(video);
                }
            }
        }
        if (AdType.TYPE_BANNER.equals(adType)) {
            for (Map.Entry ad : bannerMap.entrySet()) {
                BannerAdAdapter banner = (BannerAdAdapter) ad.getValue();
                boolean ready = banner.isReady();
                AdLog.d(banner.getName(), AdType.TYPE_BANNER, null, "ready=" + ready);
                if (ready) {
                    effectiveAdList.add(banner);
                }
            }
        }
        return effectiveAdList;
    }
}