package cc.zimo.sdk.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;

import cc.zimo.dataplugs.log.ZMLog;

/**
 * 应用前后台状态管理
 * 适用与Android SDK >= 14
 * Created by xwc on 2017/12/22.
 */

public class ForeBackgroundManager {

    private ArrayList<ToBackgroundObserver> mBackgroundObservers;
    private ArrayList<ToForegroundObserver> mForegroundObservers;
    private int aliveNum = 0;
    private int aliveState = 0; // 0 切换到后台模式，1 从后台切换到前台，2 保持前台模式

    private boolean isLaucher = true;

    private static ForeBackgroundManager mManager;

    private ForeBackgroundManager() {
        mBackgroundObservers = new ArrayList<>();
        mForegroundObservers = new ArrayList<>();
    }

    public static ForeBackgroundManager getInstance() {
        ForeBackgroundManager manager = mManager;
        if (manager == null) {
            synchronized (ForeBackgroundManager.class) {
                manager = mManager;
                if (manager == null) {
                    manager = mManager = new ForeBackgroundManager();
                }
            }
        }

        return manager;
    }

    /**
     * 请先在 {@link android.app.Application#onCreate() Application.onCreate()} 中进行初始化
     *
     * @param application
     */
    public void initInApplication(Application application) {
        ZMLog.d("ForeBackground [- firstInit -]");
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
//                ZMLog.d("ForeBackground - onActivityStarted:  - start -");
                aliveNum++;
                if (aliveNum == 1) {
                    //说明从后台回到了前台
                    aliveState = 1;
                    if (!isLaucher) {
                        dispatchToForegroundEvent();
                    }
                    isLaucher = false;
                } else {
                    // 保持前台模式
                    aliveState = 2;
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
//                ZMLog.d("ForeBackground - onActivityStopped: - stop -");
                aliveNum--;
                //如果aliveNum ==0，说明是前台到后台
                if (aliveNum == 0) {
                    //说明从前台回到了后台
                    aliveState = 0;
                    dispatchToBackgroundEvent();
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }
        });
    }


    public boolean isBackground() {
        return aliveState == 0;
    }

    public void registerBackgroundObserver(ToBackgroundObserver observer) {
        synchronized (mBackgroundObservers) {
            mBackgroundObservers.add(observer);
        }
    }

    public void unregisterBackgroundObserver(ToBackgroundObserver observer) {
        synchronized (mBackgroundObservers) {
            mBackgroundObservers.remove(observer);
        }
    }

    private void dispatchToBackgroundEvent() {
        ZMLog.d("ForeBackground [foreToBackground]");
        for (ToBackgroundObserver observer : mBackgroundObservers) {
            observer.foreToBackground();
        }
    }

    public void registerForegroundObserver(ToForegroundObserver observer) {
        synchronized (mForegroundObservers) {
            mForegroundObservers.add(observer);
        }
    }

    public void unregisterForegroundObserver(ToForegroundObserver observer) {
        synchronized (mForegroundObservers) {
            mForegroundObservers.remove(observer);
        }
    }

    private void dispatchToForegroundEvent() {
        ZMLog.d("ForeBackground [backToForeground]");
        for (ToForegroundObserver observer : mForegroundObservers) {
            observer.backToForeground();
        }
    }

    public interface ToBackgroundObserver {
        void foreToBackground();
    }

    public interface ToForegroundObserver {
        void backToForeground();
    }
}
