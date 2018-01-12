package cc.zimo.adplugs;

import cc.zimo.adplugs.ads.AdAdapter;
import cc.zimo.adplugs.model.AdData;

/**
 * @author Wowser
 * @date 2017/5/10
 */

public abstract class AdListener {


    /**
     * 广告开始初始化
     * @param ad
     */
    public void onAdInit(AdData ad, String adsid) {
    }


    /**
     * 广告开始加载
     * @param ad
     */
    public void onAdStartLoad(AdData ad) {
    }

    /**
     * 广告加载缓存完成
     *
     * @param ad
     */
    public abstract void onAdLoadSucceeded(AdData ad);

    /**
     * 无广告填充
     * @param ad
     */
    public abstract void onAdNoFound(AdData ad);

    /**
     * 广告处理出现异常
     * @param ad
     * @param message
     * @param e
     */
    public abstract void onAdError(AdData ad, String message, Exception e);

    /**
     * 广告加载缓存完成,内部调用
     * @param ad
     */
    public void onAdLoadSucceeded(AdData ad, AdAdapter adAdapter) {
    }

    /**
     * 广告被打开，开始展示，用于插屏，banenr与原生等
     * 静态广告
     * @param ad
     */
    public void onAdShow(AdData ad) {
    }

    /**
     * 广告在展示过程中，用于视频的播放过程
     * @param ad
     */
    public void onAdView(AdData ad) {
    }

    /**
     * 广告被点击
     * @param ad
     */
    public void onAdClicked(AdData ad) {
    }

    /**
     * 广告展示结束，主要用于视频展示结束
     * @param ad
     */
    public void onAdViewEnd(AdData ad) {
    }

    /**
     * 广告被关闭
     *
     * @param ad
     */
    public void onAdClosed(AdData ad) {
    }

    /**
     * 激励性广告的奖励
     *
     * @param ad
     */
    public void onRewarded(AdData ad) {
    }

}
