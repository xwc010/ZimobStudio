package cc.zimo.adplugs.ads.youmi;


import android.text.TextUtils;

import com.adxmi.android.AdError;
import com.adxmi.android.AdxmiInterstitialListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.Utils;

import java.util.HashMap;
import java.util.Map;

import cc.zimo.adplugs.ads.InterstitialAdAdapter;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.utils.AdPlatform;

/**
 * 作者：Liuhai on 2017/8/16 17:41
 * 邮箱：13164719919@163.com
 *
 * @author yanli
 */

public final class AdXmiInterstitial extends InterstitialAdAdapter {
    private Map<Integer, Interstitial> interstitialMap = new HashMap<>();

    private static AdXmiInterstitial sYMInterstitial = new AdXmiInterstitial();

    private AdXmiInterstitial() {
    }

    public static AdXmiInterstitial getInstance() {
        return sYMInterstitial;
    }

    @Override
    public void loadAd(AdData data) {
        super.loadAd(data);

        if (!checkId()) {
            return;
        }
        try {
            int hashCode = -1;
            if (ActivityUtils.getTopActivity() != null) {
                hashCode = ActivityUtils.getTopActivity().hashCode();
            } else if (Utils.getApp() != null) {
                hashCode = Utils.getApp().hashCode();
            }

            if (!interstitialMap.containsKey(hashCode)) {
                Interstitial interstitial = new Interstitial();
                interstitial.initAd(adData);
                interstitialMap.put(hashCode, interstitial);
                adsListener.onAdInit(adData, adData.adId);
            }

            if (interstitialMap.containsKey(hashCode)) {
                AdXmiInterstitial.Interstitial interstitial = interstitialMap.get(hashCode);
                interstitial.loadAd();
            }
        } catch (Exception e) {
            adsListener.onAdError(adData, "AdXmiInterstitial  loadAd  error!", e);
        }

    }

    @Override
    public void showInterstitial() {
        try {
            int hashCode = -1;
            if (ActivityUtils.getTopActivity() != null) {
                hashCode = ActivityUtils.getTopActivity().hashCode();
            } else if (Utils.getApp() != null) {
                hashCode = Utils.getApp().hashCode();
            }
            if (interstitialMap.containsKey(hashCode)) {
                Interstitial interstitial = interstitialMap.get(hashCode);
                interstitial.showInterstitial();
            }
        } catch (Exception e) {
            adsListener.onAdError(adData, "showInterstitial error!", e);
        }
    }

    @Override
    public boolean isReady() {
        try {
            int hashCode = -1;
            if (ActivityUtils.getTopActivity() != null) {
                hashCode = ActivityUtils.getTopActivity().hashCode();
            } else if (Utils.getApp() != null) {
                hashCode = Utils.getApp().hashCode();
            }
            if (interstitialMap.containsKey(hashCode)) {
                Interstitial interstitial = interstitialMap.get(hashCode);
                return interstitial.isReady();
            }
        } catch (Exception e) {
            adsListener.onAdError(adData, "ready error!", e);
        }
        return false;
    }

    @Override
    public String getName() {
        return AdPlatform.NAME_ADXMI;
    }

    class Interstitial {

        private com.adxmi.android.AdxmiInterstitial interstitial;
        private boolean loading;
        private AdData adData;
        private boolean ready;

        public void initAd(AdData adData) {
            this.adData = adData;
            try {
                String adId = adData.adId;
                if (!TextUtils.isEmpty(adData.adId)) {
                    String[] idArrays = adData.adId.split("_");
                    if (idArrays.length >= 3) {
                        adId = idArrays[2];
                    }
                }
                interstitial = new com.adxmi.android.AdxmiInterstitial(Utils.getApp(), adId);
                interstitial.setListener(createListener());
            } catch (Exception e) {
                adsListener.onAdError(adData, "initAd error!", e);
            }
        }

        public boolean isReady() {
            return ready;
        }

        private void loadAd() {
            if (loading) {
                return;
            }
            loading = true;
            adsListener.onAdStartLoad(adData);
            interstitial.load();
        }

        private AdxmiInterstitialListener createListener() {
            return new AdxmiInterstitialListener() {

                @Override
                public void onLoadSuccess(com.adxmi.android.AdxmiInterstitial adxmiInterstitial) {
                    ready = true;
                    loading = false;
                    adsListener.onAdLoadSucceeded(adData, AdXmiInterstitial.this);
                }

                @Override
                public void onLoadFail(com.adxmi.android.AdxmiInterstitial adxmiInterstitial, AdError adError) {
                    ready = false;
                    loading = false;
                    adsListener.onAdError(adData, String.valueOf(adError.getCode()), null);
                    reloadAd();
                }

                @Override
                public void onShowSuccess(com.adxmi.android.AdxmiInterstitial adxmiInterstitial) {
                    ready = false;
                    loading = false;
                    adsListener.onAdShow(adData);
                }

                @Override
                public void onClick(com.adxmi.android.AdxmiInterstitial adxmiInterstitial) {
                    adsListener.onAdClicked(adData);
                }

                @Override
                public void onClose(com.adxmi.android.AdxmiInterstitial adxmiInterstitial) {
                    ready = false;
                    loadAd();
                    adsListener.onAdClosed(adData);
                }
            };
        }


        public void showInterstitial() {
            try {
                if (isReady()) {
                    interstitial.show();
                }
            } catch (Exception e) {
                adsListener.onAdError(adData, "showInterstitial error!", e);
            }
        }

        public void destroy() {
            try {
                interstitial.destroy();
            } catch (Exception e) {
                adsListener.onAdError(adData, "destroy error!", e);
            }
        }
    }
}
