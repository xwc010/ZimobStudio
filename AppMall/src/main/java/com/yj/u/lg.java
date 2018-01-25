package com.yj.u;

import android.util.Log;

import com.yj.see.BuildConfig;

/**
 * Created by xwc on 2018/1/24.
 */

public class lg {

    public static void i(String t, String m){
        if(BuildConfig.DEBUG){
            Log.i(t, m);
        }
    }
}
