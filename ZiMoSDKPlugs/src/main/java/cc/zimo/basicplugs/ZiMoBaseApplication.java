package cc.zimo.basicplugs;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.AppUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import cc.zimo.basicplugs.BuildConfig;
import cc.zimo.adplugs.ZiMoAdsAdgent;
import cc.zimo.basicplugs.utils.ActivityManager;
import cc.zimo.basicplugs.utils.Utils;

/**
 * Created by xwc on 2017/12/11.
 */
@ReportsCrashes(
        formUri = "http://www.backendofyourchoice.com/reportpath"
)
public class ZiMoBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        com.blankj.utilcode.util.Utils.init(this);
        Logger.t(AppUtils.getAppName());
        Utils.init(this);
        ActivityManager.manageActivity(this);

        ZiMoAdsAdgent.init(this);
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
