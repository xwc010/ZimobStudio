package cc.zimo.adplugs;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import cc.zimo.adplugs.utils.Constant;
import cc.zimo.dataplugs.ZMDataManager;
import cc.zimo.dataplugs.log.ZMLog;
import cc.zimo.imageplugs.ZMImgLoader;

/**
 * Created by xwc on 2018/1/2.
 */

public class ZiMoAdsAdgent {

    //广告全局
    public static final AdsListener adsListener = new AdsListener();
    public static final Handler Main_Handler = new Handler(Looper.getMainLooper());

    public static void initAppliction(Application application) {
        ZMDataManager.init(application);
        ZMImgLoader.getInstance().init(application);
        Constant.appStartTime = System.currentTimeMillis();
    }

    public static void onCreate() {

    }

    public static void onResume() {

    }

    public static void onPause() {

    }

    public static void onDestory() {

    }

    /**
     * 设置是否支持透明navBar,默认为FALSE
     * 设置为false,表示不支持透明navBar，布局在容器的最顶层
     */
    public static void setTransparentNavBar(boolean transparentNavBar) {
        ZMLog.fc("setTransparentNavBar");
        ZMLog.d("setTransparentNavBar transparentNavBar==>" + transparentNavBar);
        Constant.transparentNavBar = transparentNavBar;
    }

    /**
     * 设置屏幕方向,用作横屏竖写
     * @param gravity
     */
    public static void setScreenDirection(int gravity) {
        Constant.screenDirection = gravity;
    }

    public static boolean hasNative(String adType){
        return false;
    }
}
