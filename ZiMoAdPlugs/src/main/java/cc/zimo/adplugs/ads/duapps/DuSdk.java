package cc.zimo.adplugs.ads.duapps;

import android.text.TextUtils;

import com.blankj.utilcode.util.Utils;
import com.duapps.ad.base.DuAdNetwork;
import com.duapps.ad.video.DuVideoAdSDK;

import cc.zimo.adplugs.utils.AdLog;
import cc.zimo.adplugs.utils.AdPlatform;
import cc.zimo.dataplugs.ZiMoDataManager;

/**
 * 根据duapps的SDK的要求对数据进行整合
 *
 * @author yanli
 */
public class DuSdk {

    public static void init() {
        StringBuffer nativeStr = new StringBuffer("");
        StringBuffer videoStr = new StringBuffer("");
        String duInterstitialId = null;
        String duNativeIds = null;
        String duVideoIds = null;
        if (ZiMoDataManager.sdCache != null) {
            duInterstitialId = ZiMoDataManager.sdCache.getAsString("duInterstitialId");
            duNativeIds = ZiMoDataManager.sdCache.getAsString("duNativeIds");
            duVideoIds = ZiMoDataManager.sdCache.getAsString("duVideoIds");
        }

        if (TextUtils.isEmpty(duInterstitialId) && TextUtils.isEmpty(duNativeIds)) {
            AdLog.d(AdPlatform.NAME_DUAPPS, "all", null, "duInterstitialId and duNativeIds is null");
            return;
        }

        try {
            if (!TextUtils.isEmpty(duInterstitialId)) {
                nativeStr.append("{\"pid\":\"" + duInterstitialId + "\"}");
            }
            if (!TextUtils.isEmpty(duNativeIds)) {
                String[] ids = duNativeIds.split(",");
                int i = 0;
                for (String id : ids) {
                    if (!TextUtils.isEmpty(id)) {
                        if (TextUtils.isEmpty(nativeStr)) {
                            nativeStr.append("{\"pid\":\"" + id + "\"}");
                        } else {
                            nativeStr.append("," + "{\"pid\":\"" + id + "\"}");
                        }
                        i++;
                    }
                }
            }

            //处理duapps video json
            if (!TextUtils.isEmpty(duVideoIds)) {
                String[] ids = duVideoIds.split(",");
                int i = 0;
                for (String id : ids) {
                    if (!TextUtils.isEmpty(id)) {
                        if (TextUtils.isEmpty(videoStr)) {
                            videoStr.append("{\"pid\":\"" + id + "\"}");
                        }
                        i++;
                    }
                }
            }


            StringBuffer jsonStr = new StringBuffer();
            if (!TextUtils.isEmpty(nativeStr)) {
                jsonStr.append("{\"native\":[" + nativeStr + "]");
            } else {
                jsonStr.append("{");
            }
            if (!TextUtils.isEmpty(videoStr)) {
                jsonStr.append(",\"video\":[" + videoStr + "]}");
            } else {
                jsonStr.append("}");
            }

            if (jsonStr.equals("{}")) {
                AdLog.d("duapps id is not found!");
                return;
            }
            AdLog.d("duapps init json:" + jsonStr);

            if (!TextUtils.isEmpty(jsonStr)) {
                DuAdNetwork.init(Utils.getApp(), jsonStr.toString());
                DuVideoAdSDK.init(Utils.getApp(), jsonStr.toString());
            }
        } catch (Exception e) {
            AdLog.e(e);
        }
    }

}
