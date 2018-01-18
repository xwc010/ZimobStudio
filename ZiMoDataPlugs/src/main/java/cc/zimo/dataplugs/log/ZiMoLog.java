package cc.zimo.dataplugs.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import cc.zimo.dataplugs.BuildConfig;

/**
 * Created by xwc on 2018/1/12.
 */

public class ZiMoLog {

    public static void init(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Skips some method invokes in stack trace. Default 5
//        .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("ZIMO_LOG")   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
//        Logger.addLogAdapter(new AndroidLogAdapter() {
//            @Override public boolean isLoggable(int priority, String tag) {
//                return true;
//            }
//        });
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

    public static void json(String json){
        Logger.json(json);
    }

    public static void xml(String xml){
        Logger.xml(xml);
    }
}
