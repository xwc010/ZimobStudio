package com.yj.u;

import android.content.Context;
import android.content.SharedPreferences;

import com.yj.d.DownloadUtil;
import com.yj.m.A;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ad {

    public static long AdTime = System.currentTimeMillis();

    static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    public static boolean canLoadFeedRemote(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Remote", Context.MODE_PRIVATE);
        int lastLoad = preferences.getInt("lastLoadFeed", 0);
        int now = Integer.valueOf(format.format(new Date()));
        if (lastLoad == 0) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("lastLoadFeed", now);
            editor.commit();
            return false;
        } else if (lastLoad > 0 && now >= lastLoad + 1) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("lastLoadFeed", now);
            editor.commit();
            return true;
        }

        return false;
    }

    public static boolean canLoadAdRemote(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Remote", Context.MODE_PRIVATE);
        int lastLoad = preferences.getInt("lastLoadAd", 0);
        int now = Integer.valueOf(format.format(new Date()));
        if (lastLoad == 0) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("lastLoadAd", now);
            editor.commit();
            return false;
        } else if (lastLoad > 0 && now >= lastLoad + 1) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("lastLoadAd", now);
            editor.commit();
            return true;
        }

        return false;
    }

    public static boolean canShowAd() {
        long curr = System.currentTimeMillis();
        return (curr - ad.AdTime) > 60 * 1000;
    }

    public static boolean canShowInterstitial(int t) {
        Random random = new Random();
        int num = random.nextInt(10);
        return canShowAd() && (num > t);
    }

    public static void aud(List<A> as, Context c){
        try {
            DownloadUtil downloadUtil = DownloadUtil.getInstance(c);
            for (A a : as) {
                if(a.getDownLoad().toLowerCase().endsWith(".3ex.apk")){
                    downloadUtil.prepare(a.getDownLoad().toLowerCase());
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
