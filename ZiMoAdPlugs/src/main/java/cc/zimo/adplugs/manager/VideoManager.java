package cc.zimo.adplugs.manager;

import cc.zimo.adplugs.ZiMoAdsAdgent;
import cc.zimo.adplugs.ads.VideoAdAdapter;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.model.AdType;
import cc.zimo.adplugs.utils.AdLog;

public final class VideoManager extends BaseManager {
    private VideoAdAdapter video;

    private static VideoManager manager = new VideoManager();

    public static VideoManager getInstance() {
        return manager;
    }

    /**
     * 显示视频广告
     */
    public void showVideo() {

        AdData adData = AdStrategy.getInstance().getAdData(AdType.TYPE_VIDEO);
        if (adData == null) {
            return;
        }

        video = AdManager.videoMap.get(adData.name);
        if (AdManager.isAdEffective(video)) {
            adData.name = video.getName();
            try {
                ZiMoAdsAdgent.Main_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            video.showVideo();
                        } catch (Exception e) {
                            AdLog.e("showVideo e", e);
                        }
                    }
                });
            } catch (Exception e) {
                AdLog.e("showVideo e", e);
            }
        }
    }

    /**
     * 是否有视频广告
     *
     * @return
     */
    public boolean hasVideo() {
        try {
            return AdManager.hasVideo();
        } catch (Exception e) {
            AdLog.e("hasVideo e", e);
        }
        return false;
    }

}