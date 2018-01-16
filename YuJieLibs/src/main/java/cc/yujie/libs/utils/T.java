package cc.yujie.libs.utils;

import com.blankj.utilcode.util.ConvertUtils;

/**
 * Created by xwc on 2018/1/16.
 */

public class T {

    public static void main(String... args){
        String bb = ConvertUtils.bytes2HexString("http://zimob.cc/mutil/app/".getBytes());
        System.out.println("app hex: " + bb);
        String bb0 = new String(ConvertUtils.hexString2Bytes(bb));
        System.out.println("app real: " + bb0);

        String imgs = ConvertUtils.bytes2HexString("http://zimob.cc/mutil/imgs/".getBytes());
        System.out.println("imgs hex: " + imgs);
        String imgs0 = new String(ConvertUtils.hexString2Bytes(imgs));
        System.out.println("imgs real: " + imgs0);

        String videos = ConvertUtils.bytes2HexString("http://zimob.cc/mutil/videos/".getBytes());
        System.out.println("videos hex: " + videos);
        String videos0 = new String(ConvertUtils.hexString2Bytes(videos));
        System.out.println("videos real: " + videos0);
    }
}
