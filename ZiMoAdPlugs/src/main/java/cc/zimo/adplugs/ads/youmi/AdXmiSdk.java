package cc.zimo.adplugs.ads.youmi;

import android.text.TextUtils;

import com.adxmi.android.AdxmiSdk;
import com.blankj.utilcode.util.Utils;

import cc.zimo.adplugs.ZiMoAdsAdgent;
import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.utils.AdLog;
import cc.zimo.adplugs.utils.AdPlatform;
import cc.zimo.dataplugs.ZiMoDataManager;

/**
 * @author Liuhai on 2017/8/17 11:48
 * @email 13164719919@163.com
 * @desc 有米广告的初始化及配置整合
 */

public class AdXmiSdk {

    public static void initAd() {
        String adxmiAppId = null;
        String adxmiAppSecret = null;
        if (Utils.getApp() != null) {
            adxmiAppId = ZiMoDataManager.sdCache.getAsString("adxmiAppID");
            adxmiAppSecret = ZiMoDataManager.sdCache.getAsString("adxmiAppSecret");
        }

        if (TextUtils.isEmpty(adxmiAppId) || TextUtils.isEmpty(adxmiAppSecret)) {
            AdLog.d(AdPlatform.NAME_ADXMI, "all", null, "adxmiAppId or adxmiAppSecret is null");
            return;
        }
        try {
            AdxmiSdk.init(Utils.getApp().getApplicationContext(), adxmiAppId, adxmiAppSecret);
        } catch (Exception e) {
            ZiMoAdsAdgent.adsListener.onAdError(new AdData(AdPlatform.NAME_ADXMI, "app_id"),
                    "Adxmi sdk not found,if not use Adxmi, please ignore!", e);
        }
    }
}
