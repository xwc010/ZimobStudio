package cc.yujie.adplugs;

import android.content.Context;

import cc.yujie.imageplugs.ImgLoader;

/**
 * Created by xwc on 2018/1/2.
 */

public class AdsAdgent {

    public static void init(Context context){
        ImgLoader.getInstance().init(context);
    }
}
