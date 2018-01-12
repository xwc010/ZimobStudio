package cc.zimo.adplugs.utils;

/**
 * @author xwc
 * 检测每个第三方平台是否被集成到应用中
 */
public class AdPlatform {

    //ad platform
    public final static String NAME_ADMOB = "admob"; //admob 插屏，banner，原生（实际为大banner）
    public final static String NAME_AVOCARROT = "avocarrot"; //avocarrot
    public final static String NAME_AVONATIVE = "avonative"; //avocarrot 的类型
    public final static String NAME_FACEBOOK = "facebook"; //facebook 插屏，banner，原生
    public final static String NAME_APPNEXT = "appnext";  //appnext的插屏，原生
    public final static String NAME_ANNATIVE = "annative"; //appnext的原生组成的类型
    public final static String NAME_APPLOVIN = "applovin"; //applovin
    public final static String NAME_VUNGLE = "nvungle"; //vungle 包括视频和插屏
    public final static String NAME_UNITY = "unity"; //unity 视频
    public final static String NAME_DUAPPS = "duapps";//baidu原生，插屏
    public final static String NAME_ADCOLONY = "adcolony";
    public final static String NAME_IRONSOURCE = "ironsource";
    public final static String NAME_MOBVISTA = "mobvista";
    public static final String NAME_BATMOBI = "batmobi";
    public final static String NAME_ADXMI = "adxmi";
    public final static String NAME_AXNATIVE = "axnative"; //adxmi的原生组成的类型
    public final static String NAME_TAPJOY = "tapjoy";
    public final static String NAME_INNERACTIVE = "inneractive";

    //默认为未检测，1为有  -1为没有

    private static int hasAdmob = 0;
    private static int hasFacebook = 0;
    private static int hasVungle = 0;
    private static int hasUnity = 0;
    private static int hasAppLovin = 0;
    private static int hasAppNext = 0;
    private static int hasAdColony = 0;
    private static int hasvocarrot = 0;
    private static int hasIronSource = 0;
    private static int hasDuapps = 0;
    private static int hasMobvista = 0;
    private static int hasBatMobi = 0;
    private static int hasAdmxi = 0;
    private static int hasTapjoy = 0;
    public static int hasInnerActive = 0;


    public static boolean isPlatformSdkIn(String platformName) {
        if (NAME_ADMOB.equals(platformName)) {
            if (AdPlatform.hasAdmob == 0) {
                AdPlatform.hasAdmob = checkClass("com.google.android.gms.ads.AdActivity") ? 1 : -1;
            }
            return AdPlatform.hasAdmob == 1;
        } else if (NAME_FACEBOOK.equals(platformName)) {
            if (AdPlatform.hasFacebook == 0) {
                AdPlatform.hasFacebook = checkClass("com.facebook.ads.AdSettings") ? 1 : -1;
            }
            return AdPlatform.hasFacebook == 1;
        } else if (NAME_APPLOVIN.equals(platformName)) {
            if (AdPlatform.hasAppLovin == 0) {
                AdPlatform.hasAppLovin = checkClass("com.applovin.sdk.AppLovinSdk") ? 1 : -1;
            }
            return AdPlatform.hasAppLovin == 1;
        } else if (NAME_VUNGLE.equals(platformName)) {
            if (AdPlatform.hasVungle == 0) {
                AdPlatform.hasVungle = checkClass("com.vungle.publisher.VunglePub") ? 1 : -1;
            }
            return AdPlatform.hasVungle == 1;
        } else if (NAME_UNITY.equals(platformName)) {
            if (AdPlatform.hasUnity == 0) {
                AdPlatform.hasUnity = checkClass("com.unity3d.ads.UnityAds") ? 1 : -1;
            }
            return AdPlatform.hasUnity == 1;
        } else if (NAME_APPNEXT.equals(platformName)) {
            if (AdPlatform.hasAppNext == 0) {
                AdPlatform.hasAppNext = checkClass("com.appnext.ads.interstitial.Interstitial") ? 1 : -1;
            }
            return AdPlatform.hasAppNext == 1;
        } else if (NAME_ANNATIVE.equals(platformName)) {
            if (AdPlatform.hasAppNext == 0) {
                AdPlatform.hasAppNext = checkClass("com.appnext.appnextsdk.API.AppnextAPI") ? 1 : -1;
            }
            return AdPlatform.hasAppNext == 1;
        } else if (NAME_AVOCARROT.equals(platformName)) {
            if (AdPlatform.hasvocarrot == 0) {
                AdPlatform.hasvocarrot = checkClass("com.avocarrot.androidsdk.AvocarrotCustom") ? 1 : -1;
            }
            return AdPlatform.hasvocarrot == 1;
        } else if (NAME_AVONATIVE.equals(platformName)) {
            if (AdPlatform.hasvocarrot == 0) {
                AdPlatform.hasvocarrot = checkClass("com.avocarrot.androidsdk.AvocarrotCustom") ? 1 : -1;
            }
            return AdPlatform.hasvocarrot == 1;
        } else if (NAME_ADCOLONY.equals(platformName)) {
            if (AdPlatform.hasAdColony == 0) {
                AdPlatform.hasAdColony = checkClass("com.adcolony.sdk.AdColony") ? 1 : -1;
            }
            return AdPlatform.hasAdColony == 1;
        } else if (NAME_IRONSOURCE.equals(platformName)) {
            if (AdPlatform.hasIronSource == 0) {
                AdPlatform.hasIronSource = checkClass("com.ironsource.mediationsdk.model.Placement") ? 1 : -1;
            }
            return AdPlatform.hasIronSource == 1;
        } else if (AdPlatform.NAME_DUAPPS.equals(platformName)) {
            if (AdPlatform.hasDuapps == 0) {
                AdPlatform.hasDuapps = checkClass("com.duapps.ad.base.DuAdNetwork") ? 1 : -1;
            }
            return AdPlatform.hasDuapps == 1;
        } else if (AdPlatform.NAME_MOBVISTA.equals(platformName)) {
            if (AdPlatform.hasMobvista == 0) {
                AdPlatform.hasMobvista = checkClass("com.mobvista.msdk.out.MVRewardVideoHandler") ? 1 : -1;
            }
            return AdPlatform.hasMobvista == 1;
        } else if (AdPlatform.NAME_BATMOBI.equals(platformName)) {
            if (AdPlatform.hasBatMobi == 0) {
                AdPlatform.hasBatMobi = checkClass("com.mnt.MntLib") ? 1 : -1;
            }
            return AdPlatform.hasBatMobi == 1;
        } else if (AdPlatform.NAME_TAPJOY.equals(platformName)) {
            if (AdPlatform.hasTapjoy == 0) {
                AdPlatform.hasTapjoy = checkClass("com.tapjoy.Tapjoy") ? 1 : -1;
            }
            return AdPlatform.hasTapjoy == 1;

        } else if (AdPlatform.NAME_AXNATIVE.equals(platformName)) {
            if (AdPlatform.hasAdmxi == 0) {
                AdPlatform.hasAdmxi = checkClass("com.adxmi.android.AdxmiSdk") ? 1 : -1;
            }
            return AdPlatform.hasAdmxi == 1;

        } else if (AdPlatform.NAME_ADXMI.equals(platformName)) {
            if (AdPlatform.hasAdmxi == 0) {
                AdPlatform.hasAdmxi = checkClass("com.adxmi.android.AdxmiSdk") ? 1 : -1;
            }
            return AdPlatform.hasAdmxi == 1;

        } else if (AdPlatform.NAME_INNERACTIVE.equals(platformName)) {
            if (AdPlatform.hasInnerActive == 0) {
                AdPlatform.hasInnerActive = checkClass("com.fyber.inneractive.sdk.external.InneractiveAdManager") ? 1 : -1;
            }
            return AdPlatform.hasInnerActive == 1;

        }
        return false;
    }

    private static boolean checkClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}