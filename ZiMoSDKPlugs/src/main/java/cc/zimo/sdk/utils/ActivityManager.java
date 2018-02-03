package cc.zimo.sdk.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;

import cc.zimo.dataplugs.http.IPUnit;

/**
 * Created by xwc on 2017/12/22.
 */

public class ActivityManager {

    public static final LinkedList<Activity> ACTIVITY_LINKED_LIST = new LinkedList<>();
    public static final LinkedList<Activity> ACTIVITY_PAUSE_LIST = new LinkedList<>();
    private static boolean doStart = false;

    public static void manageActivity(Application application){
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ACTIVITY_LINKED_LIST.add(activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ACTIVITY_LINKED_LIST.remove(activity);
                ACTIVITY_PAUSE_LIST.remove(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                doStart = true;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                IPUnit.initNetIp();
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                doStart = false;
                if(!ACTIVITY_PAUSE_LIST.contains(activity)){
                    ACTIVITY_PAUSE_LIST.add(activity);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

        });
    }

    public static boolean isBackgroundToForeground(){
        return !doStart && ACTIVITY_LINKED_LIST.size() <= ACTIVITY_PAUSE_LIST.size();
    }

    public static void exitApp() {
        for (Activity activity : ACTIVITY_LINKED_LIST) {
            activity.finish();
        }

        for (Activity activity : ACTIVITY_PAUSE_LIST) {
            activity.finish();
        }

        System.exit(0);
    }
}
