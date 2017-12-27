package cc.yujie.basicplugs;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.AppUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import java.util.LinkedList;

import cc.yujie.basicplugs.utils.ActivityManger;
import cc.yujie.basicplugs.utils.Utils;

/**
 * Created by xwc on 2017/12/11.
 */
@ReportsCrashes(
        formUri = "http://www.backendofyourchoice.com/reportpath"
)
public class YuJieBaseApplication extends Application {

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
        ActivityManger.manageActivity(this);
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
