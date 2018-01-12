package cc.zimo.adplugs.utils;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * Created by yeahyf on 2016/11/2.
 */

public final class AnimatorProxy extends Animation {
    public static final boolean NEEDS_PROXY;
    private static final WeakHashMap<View, AnimatorProxy> PROXIES;
    private final WeakReference<View> mView;
    private float mAlpha = 1.0F;
    private float mScaleX = 1.0F;
    private float mScaleY = 1.0F;
    private float mTranslationX;
    private float mTranslationY;
    private final RectF mBefore = new RectF();
    private final RectF mAfter = new RectF();
    private final Matrix mTempMatrix = new Matrix();

    public static AnimatorProxy wrap(View view) {
        AnimatorProxy proxy = PROXIES.get(view);
        if (proxy == null || proxy != view.getAnimation()) {
            proxy = new AnimatorProxy(view);
            PROXIES.put(view, proxy);
        }

        return proxy;
    }

    private AnimatorProxy(View view) {
        this.setDuration(0L);
        this.setFillAfter(true);
        view.setAnimation(this);
        this.mView = new WeakReference<>(view);
    }

    public void setScaleX(float scaleX) {
        if (this.mScaleX != scaleX) {
            this.prepareForUpdate();
            this.mScaleX = scaleX;
            this.invalidateAfterUpdate();
        }

    }

    public void setScaleY(float scaleY) {
        if (this.mScaleY != scaleY) {
            this.prepareForUpdate();
            this.mScaleY = scaleY;
            this.invalidateAfterUpdate();
        }

    }

    public void setTranslationX(float translationX) {
        if (this.mTranslationX != translationX) {
            this.prepareForUpdate();
            this.mTranslationX = translationX;
            this.invalidateAfterUpdate();
        }

    }

    public void setTranslationY(float translationY) {
        if (this.mTranslationY != translationY) {
            this.prepareForUpdate();
            this.mTranslationY = translationY;
            this.invalidateAfterUpdate();
        }
    }

    private void prepareForUpdate() {
        View view = this.mView.get();
        if (view != null) {
            this.computeRect(this.mBefore, view);
        }

    }

    private void invalidateAfterUpdate() {
        View view = this.mView.get();
        if (view != null && view.getParent() != null) {
            RectF after = this.mAfter;
            this.computeRect(after, view);
            after.union(this.mBefore);
            ((View) view.getParent()).invalidate((int) Math.floor((double) after.left), (int) Math.floor((double) after.top), (int) Math.ceil((double) after.right), (int) Math.ceil((double) after.bottom));
        }
    }

    private void computeRect(RectF r, View view) {
        float w = (float) view.getWidth();
        float h = (float) view.getHeight();
        r.set(0.0F, 0.0F, w, h);
        Matrix m = this.mTempMatrix;
        m.reset();
        this.transformMatrix(m, view);
        this.mTempMatrix.mapRect(r);
        r.offset((float) view.getLeft(), (float) view.getTop());
        float f;
        if (r.right < r.left) {
            f = r.right;
            r.right = r.left;
            r.left = f;
        }

        if (r.bottom < r.top) {
            f = r.top;
            r.top = r.bottom;
            r.bottom = f;
        }

    }

    private void transformMatrix(Matrix m, View view) {
        float w = (float) view.getWidth();
        float h = (float) view.getHeight();
        float pX = w / 2.0F;
        float pY = h / 2.0F;

        float sX1 = this.mScaleX;
        float sY = this.mScaleY;
        if (sX1 != 1.0F || sY != 1.0F) {
            m.postScale(sX1, sY);
            float sPX = -(pX / w) * (sX1 * w - w);
            float sPY = -(pY / h) * (sY * h - h);
            m.postTranslate(sPX, sPY);
        }

        m.postTranslate(this.mTranslationX, this.mTranslationY);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        View view = this.mView.get();
        if (view != null) {
            t.setAlpha(this.mAlpha);
            this.transformMatrix(t.getMatrix(), view);
        }
    }

    static {
        NEEDS_PROXY = Integer.valueOf(Build.VERSION.SDK_INT).intValue() < 11;
        PROXIES = new WeakHashMap<>();
    }
}

