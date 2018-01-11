package cc.zimo.adplugs;

import android.content.Context;

import cc.zimo.imageplugs.ZiMoImgLoader;

/**
 * Created by xwc on 2018/1/2.
 */

public class ZiMoAdsAdgent {

    public static void init(Context context){
        ZiMoImgLoader.getInstance().init(context);
    }
}
