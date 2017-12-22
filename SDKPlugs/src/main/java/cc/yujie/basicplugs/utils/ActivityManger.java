package cc.yujie.basicplugs.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;

/**
 * Created by xwc on 2017/12/22.
 */

public class ActivityManger {

    public static final LinkedList<Activity> ACTIVITY_LINKED_LIST = new LinkedList<>();

    public static void manageActivity(Application application){
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ACTIVITY_LINKED_LIST.add(activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ACTIVITY_LINKED_LIST.remove(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

        });
    }

    public static void exitApp() {
        for (Activity activity : ACTIVITY_LINKED_LIST) {
            activity.finish();
        }

        System.exit(0);
    }
}
