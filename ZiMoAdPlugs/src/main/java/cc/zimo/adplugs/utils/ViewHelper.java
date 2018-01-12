package cc.zimo.adplugs.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/**
 * Created by xwc on 2018/01/12.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public final class ViewHelper {
    private ViewHelper() {
    }

    public static void setScaleX(View view, float scaleX) {
        if (AnimatorProxy.NEEDS_PROXY) {
            AnimatorProxy.wrap(view).setScaleX(scaleX);
        } else {
            view.setScaleX(scaleX);
        }
    }

    public static void setScaleY(View view, float scaleY) {
        if (AnimatorProxy.NEEDS_PROXY) {
            AnimatorProxy.wrap(view).setScaleY(scaleY);
        } else {
            view.setScaleY(scaleY);
        }

    }

    public static void setTranslationX(View view, float translationX) {
        if (AnimatorProxy.NEEDS_PROXY) {
            AnimatorProxy.wrap(view).setTranslationX(translationX);
        } else {
            view.setTranslationX(translationX);
        }

    }

    public static void setTranslationY(View view, float translationY) {
        if (AnimatorProxy.NEEDS_PROXY) {
            AnimatorProxy.wrap(view).setTranslationY(translationY);
        } else {
            view.setTranslationY(translationY);
        }

    }

    public static void setRotation(View view, float rotation) {
        view.setRotation(rotation);
    }
}

