package cc.zimo.adplugs.utils;

import android.text.TextUtils;

import cc.zimo.adplugs.BuildConfig;
import cc.zimo.dataplugs.log.ZiMoLog;

/**
 * Created by xwc on 2018/1/12.
 */

public class AdLog {
    public final static String FLAG = "AdLog";

    public static void d(String adPlatform, String adType, String page, String adAction) {
        if (BuildConfig.DEBUG) {
            String msg;
            if (TextUtils.isEmpty(page)) {
                msg = adPlatform + "__" + adType + "==>" + adAction;
            } else {
                msg = adPlatform + "__" + adType + "__" + page + "==>" + adAction;
            }
            ZiMoLog.i(FLAG, msg);
        }
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            ZiMoLog.i(FLAG, msg);
        }
    }

    public static void e(String errorInfo, Throwable exp) {
        try {
            if (!TextUtils.isEmpty(errorInfo)) {
                ZiMoLog.e(FLAG, "{Thread:" + Thread.currentThread().getName() + "} " + errorInfo, exp);
            } else {
                ZiMoLog.e(FLAG, "error:", exp);
            }
        } catch (Exception e) {
        }
    }

    public static void e(Throwable exp) {
        try {
            ZiMoLog.e("error:", exp);
        } catch (Exception e) {
        }
    }

    public static void e(String message) {
        try {
            ZiMoLog.e(message);
        } catch (Exception e) {
        }
    }
}
