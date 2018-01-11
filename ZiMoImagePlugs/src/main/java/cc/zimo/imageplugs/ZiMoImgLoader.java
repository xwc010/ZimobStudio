package cc.zimo.imageplugs;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

import cc.zimo.imageplugs.adapter.IImgAdapter;
import cc.zimo.imageplugs.adapter.PicassoAdapter;
import cc.zimo.imageplugs.utils.ImgException;

/**
 * Created by xwc on 2018/1/2.
 */

public class ZiMoImgLoader {

    private final String TAG = ZiMoImgLoader.class.getSimpleName();
    private IImgAdapter mImgAdapter;
    private static ZiMoImgLoader mImgLoader;

    private ZiMoImgLoader(){

    }

    public static ZiMoImgLoader getInstance(){
        if(mImgLoader == null){
            synchronized (ZiMoImgLoader.class){
                if(mImgLoader == null){
                    mImgLoader = new ZiMoImgLoader();
                }
            }
        }

        return mImgLoader;
    }

    /**
     * Please first init in Application onCreate()
     * @param context
     */
    public void init(Context context) throws ImgException {
        if(checkClass("com.squareup.picasso.Picasso")){
            mImgAdapter = new PicassoAdapter();
        }

        if(mImgAdapter != null){
            mImgAdapter.init(context);
        }else {
            throw new ImgException("未集成相关第三方图片加载框架");
        }
    }

    public void loadImg(String imgURL, ImageView imageView, int placeholderRes, int errorRes){
        if(mImgAdapter != null){
            mImgAdapter.loadImg(imgURL,imageView, placeholderRes, errorRes);
        }else {
            Log.i("TAG", "ImgLoader was not init!");
        }
    };

    public void loadImg(String imgURL, ImageView imageView, int placeholderRes, int errorRes, boolean centerCrop){
        if(mImgAdapter != null){
            mImgAdapter.loadImg(imgURL,imageView, placeholderRes, errorRes, centerCrop);
        }else {
            Log.i("TAG", "ImgLoader was not init!");
        }
    };

    public void loadImg(int imgRes, ImageView imageView, int placeholderRes, int errorRes){
        if(mImgAdapter != null){
            mImgAdapter.loadImg(imgRes,imageView, placeholderRes, errorRes);
        }else {
            Log.i("TAG", "ImgLoader was not init!");
        }
    };

    public void loadImg(File imgFile, ImageView imageView, int placeholderRes, int errorRes){
        if(mImgAdapter != null){
            mImgAdapter.loadImg(imgFile,imageView, placeholderRes, errorRes);
        }else {
            Log.i("TAG", "ImgLoader was not init!");
        }
    };

    public void loadImg(File imgFile, ImageView imageView, int placeholderRes, int errorRes, boolean centerCrop){
        if(mImgAdapter != null){
            mImgAdapter.loadImg(imgFile,imageView, placeholderRes, errorRes, centerCrop);
        }else {
            Log.i("TAG", "ImgLoader was not init!");
        }
    };

    public void loadImg(String imgURL, ImageView imageView){
        if(mImgAdapter != null){
            mImgAdapter.loadImg(imgURL,imageView);
        }else {
            Log.i("TAG", "ImgLoader was not init!");
        }
    };

    public void loadImg(int imgRes, ImageView imageView){
        if(mImgAdapter != null){
            mImgAdapter.loadImg(imgRes,imageView);
        }else {
            Log.i("TAG", "ImgLoader was not init!");
        }
    };

    public void loadImg(File imgFile, ImageView imageView){
        if(mImgAdapter != null){
            mImgAdapter.loadImg(imgFile,imageView);
        }else {
            Log.i("TAG", "ImgLoader was not init!");
        }
    };

    public void load(String imgURL, IImgAdapter.CallBack callBack){
        if(mImgAdapter != null){
            mImgAdapter.load(imgURL, callBack);
        }else {
            Log.i("TAG", "ImgLoader was not init!");
        }
    }

    private boolean checkClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
