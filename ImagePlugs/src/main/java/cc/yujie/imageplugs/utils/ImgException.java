package cc.yujie.imageplugs.utils;


/**
 * Created by xwc on 2018/1/2.
 */

public class ImgException extends RuntimeException {

    public ImgException(String message) {
        super(message);
    }

    public ImgException(String message, Throwable cause) {
        super(message, cause);
    }
}
