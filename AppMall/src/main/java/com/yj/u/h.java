package com.yj.u;

import android.content.Context;

import com.yj.see.R;

import net.youmi.android.AdManager;
import net.youmi.android.nm.sp.SpotManager;
import net.youmi.android.nm.sp.SpotRequestListener;

/**
 * Created by xwc on 2018/1/24.
 */

public class h {
    private static final char hd[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String bh(final byte[] b) {
        if (b == null) return null;
        int len = b.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hd[b[i] >>> 4 & 0x0f];
            ret[j++] = hd[b[i] & 0x0f];
        }
        return new String(ret);
    }

    public static byte[] hb(String h) {
        if (isSpace(h)) return null;
        int len = h.length();
        if (len % 2 != 0) {
            h = "0" + h;
            len = len + 1;
        }
        char[] hexBytes = h.toUpperCase().toCharArray();
        byte[] ret = new byte[len >> 1];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) (hd(hexBytes[i]) << 4 | hd(hexBytes[i + 1]));
        }
        return ret;
    }

    /**
     * hexChar 转 int
     *
     * @param hexChar hex 单个字节
     * @return 0..15
     */
    private static int hd(final char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 判断字符串是否为 null 或全为空白字符
     *
     * @param s 待校验字符串
     * @return {@code true}: null 或全空白字符<br> {@code false}: 不为 null 且不全空白字符
     */
    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void i(Context c){
        AdManager.getInstance(c).init(d.rs(c.getString(R.string.i_)), d.rs(c.getString(R.string.s_)), false);
        SpotManager.getInstance(c).requestSpot(new SpotRequestListener(){
            @Override
            public void onRequestSuccess() {

            }

            @Override
            public void onRequestFailed(int i) {

            }
        });
    }
}
