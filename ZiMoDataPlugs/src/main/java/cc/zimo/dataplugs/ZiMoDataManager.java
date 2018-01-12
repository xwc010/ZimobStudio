package cc.zimo.dataplugs;

import android.app.Application;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cc.zimo.dataplugs.cache.ZiMoSDCache;
import cc.zimo.dataplugs.log.ZiMoLog;

/**
 * Created by xwc on 2018/1/12.
 */

public class ZiMoDataManager {

    public static ZiMoSDCache sdCache;

    public static void init(Application application){
        Utils.init(application);
        sdCache = ZiMoSDCache.get(application.getApplicationContext());
        ZiMoLog.init();
    }
}
