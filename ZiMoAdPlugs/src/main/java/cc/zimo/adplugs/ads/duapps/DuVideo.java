package cc.zimo.adplugs.ads.duapps;

import com.blankj.utilcode.util.Utils;
import com.duapps.ad.AdError;
import com.duapps.ad.video.AdResult;
import com.duapps.ad.video.DuVideoAd;
import com.duapps.ad.video.DuVideoAdListener;
import com.duapps.ad.video.DuVideoAdsManager;

import cc.zimo.adplugs.ads.VideoAdAdapter;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.utils.AdLog;
import cc.zimo.adplugs.utils.AdPlatform;

public class DuVideo extends VideoAdAdapter {
    private static DuVideo sDuVideo = new DuVideo();

    private DuVideo() {
    }

    public static DuVideo getInstance() {
        return sDuVideo;
    }

    private DuVideoAd duRewardedVideoAd;

    private boolean loading;

    @Override
    public void loadAd(AdData data) {
        super.loadAd(data);
        if (loading) {
            return;
        }

        if (!checkId()) {
            return;
        }
        loadVideoData();
    }

    private void loadVideoData() {
        try {
            // TODO: 2017/12/1 需要修改，多ID的情况
            if (duRewardedVideoAd == null) {
                int adVideoId = Integer.parseInt(adData.adId);
                duRewardedVideoAd = DuVideoAdsManager.getVideoAd(Utils.getApp(), adVideoId);
                duRewardedVideoAd.setListener(createListener());
            }

            duRewardedVideoAd.load();
        } catch (Exception e) {
            AdLog.e(e);
        }
    }

    private DuVideoAdListener createListener() {
        return new DuVideoAdListener() {

            @Override
            public void onAdEnd(AdResult adResult) {
                ready = false;
                if (adResult.isSuccessfulView()) {
                    adsListener.onRewarded(adData);
                }
                if (adResult.isCallToActionClicked()) {
                    adsListener.onAdClicked(adData);
                }
                adsListener.onAdClosed(adData);
            }

            @Override
            public void onAdStart() {
                adsListener.onAdShow(adData);
            }

            @Override
            public void onAdError(AdError adError) {
                loading = false;
                ready = false;
                adsListener.onAdError(adData, String.valueOf(adError.getErrorCode()), null);
                reloadAd();
            }

            @Override
            public void onAdPlayable() {
                loading = false;
                ready = true;
                adsListener.onAdLoadSucceeded(adData, getInstance());

            }
        };
    }

    @Override
    public void showVideo() {
        if (duRewardedVideoAd != null && duRewardedVideoAd.isAdPlayable() && ready) {
            duRewardedVideoAd.playAd(Utils.getApp());
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
