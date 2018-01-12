package cc.zimo.adplugs.ads.youmi;


import android.app.Activity;
import android.text.TextUtils;

import com.adxmi.android.AdError;
import com.adxmi.android.AdxmiVideoAd;
import com.adxmi.android.AdxmiVideoAdListener;
import com.adxmi.android.VideoReward;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.Utils;

import java.util.HashMap;
import java.util.Map;

import cc.zimo.adplugs.ads.VideoAdAdapter;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.model.AdType;
import cc.zimo.adplugs.utils.AdLog;
import cc.zimo.adplugs.utils.AdPlatform;

/**
 * @author ：Liuhai
 * @date 2017/8/17 16:58
 * @see 13164719919@163.com
 */

public class AdXmiVideo extends VideoAdAdapter {
    private static AdXmiVideo sAdXmiVideo = new AdXmiVideo();

    private Map<Integer, Video> videoMap = new HashMap<>();

    private AdXmiVideo() {
    }

    public static AdXmiVideo getInstance() {
        return sAdXmiVideo;
    }


    @Override
    public void loadAd(AdData data) {
        super.loadAd(data);
        if (!checkId()) {
            return;
        }
        int hashCode = -1;
        try {
            if (ActivityUtils.getTopActivity() != null) {
                hashCode = ActivityUtils.getTopActivity().hashCode();
            } else if (Utils.getApp() != null) {
                hashCode = Utils.getApp().hashCode();
            }

            Video video;
            if (!videoMap.containsKey(hashCode)) {
                video = new Video();
                videoMap.put(hashCode, video);
            } else {
                video = videoMap.get(hashCode);
            }
            video.loadAd(adData);
        } catch (Exception e) {
            adsListener.onAdError(adData, "loadAd error!", e);
        }
    }


    @Override
    public boolean isReady() {
        int hashCode = -1;
        try {
            if (ActivityUtils.getTopActivity() != null) {
                hashCode = ActivityUtils.getTopActivity().hashCode();
            } else if (Utils.getApp() != null) {
                hashCode = Utils.getApp().hashCode();
            }
            if (videoMap.containsKey(hashCode)) {
                Video video = videoMap.get(hashCode);
                return video.isReady();
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

    @Override
    public void showVideo() {
        int hashCode = -1;
        try {
            if (ActivityUtils.getTopActivity() != null) {
                hashCode = ActivityUtils.getTopActivity().hashCode();
            } else if (Utils.getApp() != null) {
                hashCode = Utils.getApp().hashCode();
            }
            if (videoMap.containsKey(hashCode)) {
                Video video = videoMap.get(hashCode);
                video.showVideo();
            }
        } catch (Exception e) {
            adsListener.onAdError(adData, "showVideo error!", e);
        }
    }

    @Override
    public void onDestroy(Activity activity) {
        super.onDestroy(activity);
        int hashCode = activity.hashCode();
        if (videoMap.containsKey(hashCode)) {
            Video video = videoMap.get(hashCode);
            video.destroy();
            videoMap.remove(hashCode);
        }
    }

    class Video {
        private AdxmiVideoAd mAdxmiVideoAd = null;
        private boolean loading = false;
        private AdData adData;

        public void loadAd(AdData adData) {
            if (loading) {
                return;
            }
            this.adData = adData;
            loading = true;
            String adId = adData.adId;
            if (!TextUtils.isEmpty(adData.adId)) {
                String[] idArrays = adData.adId.split("_");
                if (idArrays.length >= 3) {
                    adId = idArrays[2];

                }
            }

            if (mAdxmiVideoAd == null) {
                adsListener.onAdInit(adData, adId);
                mAdxmiVideoAd = new AdxmiVideoAd(ActivityUtils.getTopActivity(), adId);
                mAdxmiVideoAd.setListener(createListener());

            }
            loadVideo();
        }


        private void loadVideo() {
            adsListener.onAdStartLoad(adData);
            try {
                mAdxmiVideoAd.load();
            } catch (Exception e) {
                adsListener.onAdError(adData, "load video error!", e);
            }
        }

        private AdxmiVideoAdListener createListener() {
            return new AdxmiVideoAdListener() {
                @Override
                public void onVideoLoaded(AdxmiVideoAd adxmiVideoAd) {
                    loading = false;
                    adsListener.onAdLoadSucceeded(adData, AdXmiVideo.this);
                }

                @Override
                public void onVideoShow(AdxmiVideoAd adxmiVideoAd) {
                    loading = false;
                    adsListener.onAdShow(adData);
                }

                @Override
                public void onVideoStarted(AdxmiVideoAd adxmiVideoAd) {
                    AdLog.d(AdPlatform.NAME_ADXMI, AdType.TYPE_VIDEO, "adxmiVideoAd+Started ", adxmiVideoAd.toString());
                }

                @Override
                public void onVideoClosed(AdxmiVideoAd adxmiVideoAd) {
                    loading = false;
                    adsListener.onAdClosed(adData);
                }

                @Override
                public void onVideoRewarded(AdxmiVideoAd adxmiVideoAd, VideoReward videoReward) {
                    loading = false;
                    adsListener.onAdViewEnd(adData);
                    adsListener.onRewarded(adData);
                }

                @Override
                public void onVideoClick(AdxmiVideoAd adxmiVideoAd) {
                    loading = false;
                    adsListener.onAdClicked(adData);
                }

                @Override
                public void onVideoLoadFailed(AdxmiVideoAd adxmiVideoAd, AdError adError) {
                    loading = false;
                    adsListener.onAdError(adData, String.valueOf(adError.getCode()), null);
                    reloadAd();
                }
            };
        }

        public void showVideo() {
            if (mAdxmiVideoAd != null) {
                mAdxmiVideoAd.show();
            }
        }

        public boolean isReady() {
            if (mAdxmiVideoAd != null) {
                return mAdxmiVideoAd.isReady();
            }
            return false;
        }

        public String getName() {
            return AdPlatform.NAME_ADXMI;
        }

        public void destroy() {
            if (mAdxmiVideoAd != null) {
                mAdxmiVideoAd.onDestroy();
            }
        }
    }
}
