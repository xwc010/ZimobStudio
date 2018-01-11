package cc.zimo.imageplugs.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by xwc on 2018/1/2.
 */

public interface IImgAdapter {

    public void init(Context context);

    /**
     * @param imgURL
     *          "http://img.baidu.com/ddd.jpg"
     *          "file:///android_asset/DvpvklR.png"
     * @param imageView
     * @param placeholderRes
     * @param errorRes
     */
    public void loadImg(String imgURL, ImageView imageView, int placeholderRes, int errorRes);
    public void loadImg(String imgURL, ImageView imageView, int placeholderRes, int errorRes, boolean centerCrop);
    public void loadImg(int imgRes, ImageView imageView, int placeholderRes, int errorRes);
    public void loadImg(File imgFile, ImageView imageView, int placeholderRes, int errorRes);
    public void loadImg(File imgFile, ImageView imageView, int placeholderRes, int errorRes, boolean centerCrop);
    public void loadImg(String imgURL, ImageView imageView);
    public void loadImg(int imgRes, ImageView imageView);
    public void loadImg(File imgFile, ImageView imageView);
    public void load(String imgURL, CallBack callBack);

    interface CallBack{
        void onSuccess(String imgURL, Bitmap bitmap);
        void onFail();
    }
}
