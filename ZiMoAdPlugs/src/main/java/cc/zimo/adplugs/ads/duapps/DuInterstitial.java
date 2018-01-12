package cc.zimo.adplugs.ads.duapps;

import com.blankj.utilcode.util.ActivityUtils;
import com.duapps.ad.InterstitialAd;

import cc.zimo.adplugs.ads.InterstitialAdAdapter;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.utils.AdPlatform;

/**
 * @author Administrator
 * @date 2017/6/7
 */

public class DuInterstitial extends InterstitialAdAdapter {

    private InterstitialAd interstitial;
    private boolean loading = false;
    private boolean ready = false;

    private static DuInterstitial duInterstitial = new DuInterstitial();

    public static DuInterstitial getInstance() {
        return duInterstitial;
    }


    @Override
    public void loadAd(AdData data) {
        super.loadAd(data);

        if (!checkId()) {
            return;
        }

        if (loading) {
            return;
        }

        loading = true;
        if (interstitial == null) {
            try {
                interstitial = new InterstitialAd(ActivityUtils.getTopActivity(), Integer.parseInt(adData.adId), InterstitialAd.Type.SCREEN);
                interstitial.setInterstitialListener(createListener());
            } catch (Exception e) {
                adsListener.onAdError(adData, "initAd error!", e);
            }
        }

        try {
            adsListener.onAdStartLoad(adData);
            interstitial.load();
        } catch (Exception e) {
            loading = false;
            adsListener.onAdError(adData, "load ad error!", e);
        }
    }

    private com.duapps.ad.InterstitialListener createListener() {
        com.duapps.ad.InterstitialListener inListener = new com.duapps.ad.InterstitialListener() {

            @Override
            public void onAdFail(int i) {
                ready = false;
                loading = false;
                adsListener.onAdNoFound(adData);
                reloadAd();
            }

            @Override
            public void onAdReceive() {
                ready = true;
                loading = false;
                adsListener.onAdLoadSucceeded(adData, getInstance());

            }

            @Override
            public void onAdDismissed() {
                //ready = false;
                adsListener.onAdClosed(adData);
            }

            @Override
            public void onAdPresent() {
                ready = false;
                adsListener.onAdShow(adData);
            }

            @Override
            public void onAdClicked() {
                adsListener.onAdClosed(adData);
            }
        };
        return inListener;
    }

    @Override
    public void showInterstitial() {
        try {
            if (isReady()) {
                interstitial.show();
            }
        } catch (Exception e) {
            adsListener.onAdError(adData, "showInterstitial error!", e);
        }
    }

    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public String getName() {
        return AdPlatform.NAME_DUAPPS;
    }

}
