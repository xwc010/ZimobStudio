package cc.zimo.adplugs.utils;

import android.text.TextUtils;

import cc.zimo.adplugs.BuildConfig;
import cc.zimo.dataplugs.log.ZMLog;

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
            ZMLog.i(FLAG, msg);
        }
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            ZMLog.i(FLAG, msg);
        }
    }

    public static void e(String errorInfo, Throwable exp) {
        try {
            if (!TextUtils.isEmpty(errorInfo)) {
                ZMLog.e(FLAG, "{Thread:" + Thread.currentThread().getName() + "} " + errorInfo, exp);
            } else {
                ZMLog.e(FLAG, "error:", exp);
            }
        } catch (Exception e) {
        }
    }

    public static void e(Throwable exp) {
        try {
            ZMLog.e("error:", exp);
        } catch (Exception e) {
        }
    }

    public static void e(String message) {
        try {
            ZMLog.e(message);
        } catch (Exception e) {
        }
    }
}
