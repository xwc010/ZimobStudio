package cc.zimo.sdk;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.AppUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import cc.zimo.adplugs.ZiMoAdsAdgent;
import cc.zimo.sdk.utils.ActivityManager;
import cc.zimo.sdk.utils.Utils;

/**
 * Created by xwc on 2017/12/11.
 */
@ReportsCrashes(
        formUri = "http://www.backendofyourchoice.com/reportpath"
)
public class ZMBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActivityManager.manageActivity(this);
        ZiMoAdsAdgent.initAppliction(this);

        Utils.init(this);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
        // Crach 上报
        ACRA.init(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
