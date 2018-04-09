package cc.zimo.sdk.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * 应用前后台状态管理
 * 适用与Android SDK >= 14
 * Created by xwc on 2017/12/22.
 */

public class ForeBackgroundManager {

    private ArrayList<BackgroundObserver> mBackgroundObservers;
    private ArrayList<ForegroundObserver> mForegroundObservers;
    private int aliveNum = 0;
    private int aliveState = 0; // 0 切换到后台模式，1 从后台切换到前台，2 保持前台模式

    private static ForeBackgroundManager mManager;

    private ForeBackgroundManager() {
        mBackgroundObservers = new ArrayList<>();
        mForegroundObservers = new ArrayList<>();
    }

    public static ForeBackgroundManager getInstance(){
        ForeBackgroundManager manager = mManager;
        if(manager == null){
            synchronized (ForeBackgroundManager.class){
                manager = mManager;
                if(manager == null){
                    manager = mManager = new ForeBackgroundManager();
                }
            }
        }

        return manager;
    }

    /**
     * 请先在 {@link android.app.Application#onCreate() Application.onCreate()} 中进行初始化
     * @param application
     */
    public void firstInit(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                aliveNum++;
                if (aliveNum == 1) {
                    //说明从后台回到了前台
                    aliveState = 1;
                    dispatchForegroundEvent();
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
                aliveNum--;
                //如果aliveNum ==0，说明是前台到后台
                if (aliveNum == 0) {
                    //说明从前台回到了后台
                    aliveState = 0;
                    dispatchBackgroundEvent();
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

    public void registerBackgroundObserver(BackgroundObserver observer) {
        synchronized (mBackgroundObservers) {
            mBackgroundObservers.add(observer);
        }
    }

    public void unregisterBackgroundObserver(BackgroundObserver observer) {
        synchronized (mBackgroundObservers) {
            mBackgroundObservers.remove(observer);
        }
    }

    private void dispatchBackgroundEvent() {
        for (BackgroundObserver observer : mBackgroundObservers) {
            observer.foreToBackground();
        }
    }

    public void registerForegroundObserver(ForegroundObserver observer) {
        synchronized (mForegroundObservers) {
            mForegroundObservers.add(observer);
        }
    }

    public void unregisterForegroundObserver(ForegroundObserver observer) {
        synchronized (mForegroundObservers) {
            mForegroundObservers.remove(observer);
        }
    }

    private void dispatchForegroundEvent() {
        for (ForegroundObserver observer : mForegroundObservers) {
            observer.backToForeground();
        }
    }

    interface BackgroundObserver {
        void foreToBackground();
    }

    interface ForegroundObserver {
        void backToForeground();
    }
}
