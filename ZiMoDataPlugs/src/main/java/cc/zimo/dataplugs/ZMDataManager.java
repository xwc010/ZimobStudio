package cc.zimo.dataplugs;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.blankj.utilcode.util.Utils;

import cc.zimo.dataplugs.cache.ZMSDCache;
import cc.zimo.dataplugs.log.ZMLog;

/**
 * Created by xwc on 2018/1/12.
 */

public class ZMDataManager {

    public static ZMSDCache sdCache;
    private static Handler MainHandler;

    public static void init(Application application){
        Utils.init(application);
        sdCache = ZMSDCache.get(application.getApplicationContext());
        ZMLog.init();
        MainHandler = new Handler(Looper.getMainLooper());
    }

    public static Handler getMainHandler(){
        if (MainHandler == null){
            MainHandler = new Handler(Looper.getMainLooper());
        }

        return MainHandler;
    }
}
