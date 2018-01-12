package cc.zimo.dataplugs.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cc.zimo.dataplugs.BuildConfig;

/**
 * Created by xwc on 2018/1/12.
 */

public class ZiMoLog {

    public static void init(){
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        Logger.t("ZIMO_LOG");
    }

    public static void fc(String method, Object... args){
        Logger.i("[- " + method + " -]", args);
    }

    public static void i(String msg, Object... args){
        Logger.i(msg, args);
    }

    public static void d(String msg, Object... args){
        Logger.d(msg, args);
    }

    public static void w(String msg, Object... args){
        Logger.w(msg, args);
    }

    public static void e(String msg, Object... args){
        Logger.e(msg, args);
    }

    public static void e(Throwable throwable, String msg, Object... args){
        Logger.e(throwable, msg, args);
    }
}
