package cc.zimo.adplugs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;

public final class AdSize {
    private static final String TAG = AdSize.class.getSimpleName();
    /**
     * Banner的规格
     */
    public enum AdBannerSize {
        ADSIZE_UNIT_320, ADSIZE_UNIT_468, ADSIZE_UNIT_728,
    }

    //public static Context sContext;
    public static float density = 0;
    public static int screenWidth = 0;
    public static int screenHeight = 0;
    public static int bannerHeight = 0;
    public static int navigationBarHeight = 0;
    public static int statusBarHeight = 0;

    public static AdBannerSize adSize = AdBannerSize.ADSIZE_UNIT_320;

    public static void init(Activity context) {
        //sContext = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            display.getRealMetrics(displayMetrics);
        } else {
            display.getMetrics(displayMetrics);
        }

        density = displayMetrics.density;
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        bannerHeight = (int) ((screenHeight / density <= 720 ? 50 : 90) * density);
        navigationBarHeight =  BarUtils.getNavBarHeight();
        statusBarHeight = BarUtils.getStatusBarHeight();

//        int orientation = DeviceUtil.getOrientation(context);
        WindowManager.LayoutParams attrs = context.getWindow().getAttributes();
        if ((attrs.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
            AdSize.statusBarHeight = 0;
        }

        //如果是持透明navar，需要设置为零
        if (Constant.transparentNavBar) {
            AdSize.navigationBarHeight = 0;
        }

        //当前设备横屏并且是手机时计算宽度包含虚拟菜单
        if (ScreenUtils.isLandscape() && ! ScreenUtils.isTablet()) {
            screenWidth = screenWidth - AdSize.navigationBarHeight;
            screenHeight = screenHeight - AdSize.statusBarHeight;
        } else {
            screenHeight = screenHeight - AdSize.navigationBarHeight - AdSize.statusBarHeight;
        }

        int width = (int) (screenWidth / density);
        if (width >= 320 && width < 468) {
            setAdSize(AdBannerSize.ADSIZE_UNIT_320);
        } else if (width >= 468 && width < 728) {
            setAdSize(AdBannerSize.ADSIZE_UNIT_468);
        } else if (width >= 728) {
            setAdSize(AdBannerSize.ADSIZE_UNIT_728);
        } else {
            setAdSize(AdBannerSize.ADSIZE_UNIT_320);
        }

        Log.d(TAG,"navigationBarHeight = " + navigationBarHeight + " statusBarHeight = " + statusBarHeight);
        Log.d(TAG, "adSize==>" + adSize + ",density==>" + density + ",screenHeight==>" + screenHeight + ",screenWidth==>" + screenWidth);

    }

    private static void setAdSize(AdBannerSize adSize) {
        AdSize.adSize = adSize;
    }

    public static int getWidthPixels() {
        return screenWidth;
    }

    public static int getHeightPixels() {
        return screenHeight;
    }

    public static int getOrientation() {
        int height = getHeightPixels();
        int width = getWidthPixels();
        if (height > width) {
            return Configuration.ORIENTATION_PORTRAIT;
        } else {
            return Configuration.ORIENTATION_LANDSCAPE;
        }
    }
}
