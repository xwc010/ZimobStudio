package cc.zimo.dataplugs.http;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by xwc on 2017/12/29.
 */

public class HttpParam {

    public static final int TIME_OUT = 20;
    public static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public static final int REQ_PARAMS_EMPTY = 444;
    public static final int RESPONSE_EMPTY = 434;
    public static final int NETWORK_ERROR = 400;
    public static final int RESPONSE_ERROR = 401;
    public static final int DATA_PARSING_ERROR = 402;
}
