package cc.zimo.imageplugs.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

import cc.zimo.imageplugs.R;


/**
 * Created by xwc on 2018/1/2.
 */

public class PicassoAdapter implements IImgAdapter {

    private Picasso mPicasso;

    @Override
    public void init(Context context) {
        mPicasso = Picasso.with(context);
    }

    @Override
    public void loadImg(String imgURL, ImageView imageView, int placeholderRes, int errorRes) {
        if(mPicasso != null){
            mPicasso.load(imgURL)
                    .placeholder(placeholderRes)
                    .error(errorRes)
                    .into(imageView);
        }
    }

    @Override
    public void loadImg(String imgURL, ImageView imageView, int placeholderRes, int errorRes, boolean centerCrop) {
        if(mPicasso != null){
            mPicasso.load(imgURL)
                    .placeholder(placeholderRes)
                    .error(errorRes)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void loadImg(int imgRes, ImageView imageView, int placeholderRes, int errorRes) {
        if(mPicasso != null){
            mPicasso.load(imgRes)
                    .placeholder(placeholderRes)
                    .error(errorRes)
                    .into(imageView);
        }
    }

    @Override
    public void loadImg(File imgFile, ImageView imageView, int placeholderRes, int errorRes) {
        if(mPicasso != null){
            mPicasso.load(imgFile)
                    .placeholder(placeholderRes)
                    .error(errorRes)
                    .into(imageView);
        }
    }

    @Override
    public void loadImg(File imgFile, ImageView imageView, int placeholderRes, int errorRes, boolean centerCrop) {
        if(mPicasso != null){
            mPicasso.load(imgFile)
                    .placeholder(placeholderRes)
                    .error(errorRes)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void loadImg(String imgURL, ImageView imageView) {
        if(mPicasso != null){
            mPicasso.load(imgURL)
                    .placeholder(R.mipmap.img_loading_default)
                    .error(R.mipmap.img_loading_default)
                    .into(imageView);
        }
    }

    @Override
    public void loadImg(int imgRes, ImageView imageView) {
        if(mPicasso != null){
            mPicasso.load(imgRes)
                    .placeholder(R.mipmap.img_loading_default)
                    .error(R.mipmap.img_loading_default)
                    .into(imageView);
        }
    }

    @Override
    public void loadImg(File imgFile, ImageView imageView) {
        if(mPicasso != null){
            mPicasso.load(imgFile)
                    .placeholder(R.mipmap.img_loading_default)
                    .error(R.mipmap.img_loading_default)
                    .into(imageView);
        }
    }

    @Override
    public void load(final String imgURL, final CallBack callBack) {
        if(mPicasso != null){
            mPicasso.load(imgURL).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    callBack.onSuccess(imgURL, bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    callBack.onFail();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }
    }
}
