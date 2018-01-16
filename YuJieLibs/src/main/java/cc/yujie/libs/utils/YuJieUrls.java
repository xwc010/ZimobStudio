package cc.yujie.libs.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ConvertUtils;

/**
 * Created by xwc on 2018/1/16.
 */

public class YuJieUrls {

    private static String b = "687474703A2F2F7A696D6F622E63632F6D7574696C2F6170702F";
    private static String b_imgs = "687474703A2F2F7A696D6F622E63632F6D7574696C2F696D67732F";
    private static String b_videos = "687474703A2F2F7A696D6F622E63632F6D7574696C2F766964656F732F";

    public static String getTabBarUrl(String path) {
        if (!TextUtils.isEmpty(path) && path.toLowerCase().startsWith("http")) {
            return path;
        }
        return new String(ConvertUtils.hexString2Bytes(b)) + AppUtils.getAppPackageName() + "/" + path;
    }

    public static String getTabFeedUrl(String path) {
        if (!TextUtils.isEmpty(path) && path.toLowerCase().startsWith("http")) {
            return path;
        }
        return new String(ConvertUtils.hexString2Bytes(b)) + AppUtils.getAppPackageName() + "/" + path;
    }

    public static String getThumbnailUrl(String path) {
        if (!TextUtils.isEmpty(path) && path.toLowerCase().startsWith("http")) {
            return path;
        }

        return new String(ConvertUtils.hexString2Bytes(b_imgs)) + path;
    }

    public static String getLargeImgUrl(String path) {
        if (!TextUtils.isEmpty(path) && path.toLowerCase().startsWith("http")) {
            return path;
        }

        return new String(ConvertUtils.hexString2Bytes(b_imgs)) + path;
    }

    public static String getVideoUrl(String path) {
        if (!TextUtils.isEmpty(path) && path.toLowerCase().startsWith("http")) {
            return path;
        }

        return new String(ConvertUtils.hexString2Bytes(b_videos)) + path;
    }

    public static String getAppUpdateInfoUrl() {
        return new String(ConvertUtils.hexString2Bytes(b)) + AppUtils.getAppPackageName() + "/update/index.json";
    }

    public static String getNewestApkUrl(String path) {
        if (!TextUtils.isEmpty(path) && path.toLowerCase().startsWith("http")) {
            return path;
        }
        return new String(ConvertUtils.hexString2Bytes(b)) + AppUtils.getAppPackageName() + "/update/" + path;
    }
}
