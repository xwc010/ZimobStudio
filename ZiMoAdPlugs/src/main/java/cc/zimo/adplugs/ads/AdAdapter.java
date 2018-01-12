package cc.zimo.adplugs.ads;

import android.app.Activity;
import android.text.TextUtils;

import cc.zimo.adplugs.AdListener;
import cc.zimo.adplugs.ZiMoAdsAdgent;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.model.AdType;

/**
 * 所有广告聚合基类
 */
public abstract class AdAdapter {

    protected boolean ready = false;

    protected boolean isReadyPrice;

    public AdData adData;

    protected int layerIndex = 0;

    protected int layerSize = 0;

    protected int timeoutIndex = 0;

    protected int timeoutCount = 3;

    public long adLoadedTime;

    protected double adMaxPrice;

    protected AdListener adsListener = ZiMoAdsAdgent.adsListener;

    /**
     * 检查ID是否有效
     *
     * @return true为有效  fasle为无效
     */
    public boolean checkId() {

        if (adData == null || TextUtils.isEmpty(adData.adId)) {
            String type = null;
            if (this instanceof BannerAdAdapter) {
                type = AdType.TYPE_BANNER;
            } else if (this instanceof InterstitialAdAdapter) {
                type = AdType.TYPE_INTERSTITIAL;
            } else if (this instanceof VideoAdAdapter) {
                type = AdType.TYPE_VIDEO;
            }
            adsListener.onAdError(new AdData(getName(), type), "id is null!", null);
            return false;
        }
        return true;
    }

    String type = null;

    public void loadAd(AdData data) {
        if (data != null) {
            if (this instanceof BannerAdAdapter) {
                type = AdType.TYPE_BANNER;
            } else if (this instanceof InterstitialAdAdapter) {
                type = AdType.TYPE_INTERSTITIAL;
            } else if (this instanceof VideoAdAdapter) {
                type = AdType.TYPE_VIDEO;
            }
        }
    }

    public void reloadAd() {
        if (timeoutCount > timeoutIndex) {
            timeoutIndex++;
        } else {
            timeoutIndex = 0;
        }
    }

    public void onResume(Activity activity) {
    }

    public void onPause(Activity activity) {
    }

    public void onDestroy(Activity activity) {
    }

    public void loadAd() {

    }

    public void resetAd() {

    }

    public void reset() {
        ready = false;
    }

    /**
     * 判断广告是否加载成功
     */
    public abstract boolean isReady();

    public abstract String getName();

    /**
     * 检查SDK的版本是否符合要求
     *
     * @param version
     * @param targetVersion
     * @param adPlatform
     */
//    public static void checkVersion(String version, final String targetVersion, final String adPlatform) {
//        if (!targetVersion.equals(version)) {
//            BaseAgent.HANDLER.post(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(Utils.getApp().getApplicationContext(),
//                            adPlatform + " Ad SDK Version Error! You should use!" + targetVersion,
//                            Toast.LENGTH_LONG).show();
//                }
//            });
//            DLog.e(adPlatform + " Ad SDK Version=[" + version + "] is Error Version, You should use " + targetVersion);
//        }
//    }
//
//    public static void checkVersion(String className, String fieldName, final String targetVersion, final String adPlatform) {
//        //读取集成的版本，注意此处不能直接读取
//        String version = MiscUtils.getStringFieldValue(className, fieldName);
//        checkVersion(version, targetVersion, adPlatform);
//    }

}
