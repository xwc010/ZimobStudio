package com.yj.u;

import android.content.Context;

import com.yj.h.CallBack;
import com.yj.h.HttpClient;
import com.yj.see.R;

/**
 * Created by xwc on 2018/1/24.
 */

public class hd {

    public static void dfg(Context context, CallBack callBack){
        String u = d.gu(context.getString(R.string.ff));
        lg.i("loadRemote", "dfg u: " + u);
        HttpClient.getInstance().doGet("0x01", u, callBack);
    }

    public static void dag(Context context, CallBack callBack){
        String u = d.gu(context.getString(R.string.aa));
        lg.i("loadRemote", "dag u: " + u);
        HttpClient.getInstance().doGet("0x02", u, callBack);
    }
}
