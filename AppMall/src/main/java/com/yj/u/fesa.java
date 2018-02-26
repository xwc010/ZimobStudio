package com.yj.u;

import android.content.Context;

import com.yj.m.F;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by xwc on 2018/2/3.
 */

public class fesa {

    public static boolean save(Context context, F f) {
        try {
            ArrayList<F> fs = getDatas(context);
            if (fs == null) {
                fs = new ArrayList<>();
            }

            boolean contanct = false;
            for (F f1 : fs) {
                if (f1.getImgUrl().equals(f.getImgUrl())) {
                    contanct = true;
                    break;
                }
            }

            if (!contanct) {
                if (fs.size() > 0) {
                    fs.add(0, f);
                } else {
                    fs.add(f);
                }
            }

            sc.get(context).put("mf_feeds", fs);
            return true;
        } catch (Exception e) {
            lg.i("fesa", "save error");
        }

        return false;
    }

    public static ArrayList<F> getDatas(Context context) {
        return (ArrayList<F>) sc.get(context).getAsObject("mf_feeds");
    }

    public static void main(String... args) {

        for (int i = 1; i < 5; i++) {
            final int position = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    downLoad("http://img.mmjpg.com/2018/1230/" + position + ".jpg");
                }
            }).start();
        }
    }

    private static void downLoad(String destUrl) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;

        try {
            URL url = null;
            byte[] buf = new byte[1024];
            int size = 0;

            String fileName = "e:" + File.separator + "img" + File.separator+ destUrl.substring(destUrl.lastIndexOf("/") + 1);
            File file = new File(fileName);
            if(!file.getParentFile().exists()){
                file.mkdir();
            }
//建立链接
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
//连接指定的资源
            httpUrl.connect();
//获取网络输入流
            bis = new BufferedInputStream(httpUrl.getInputStream());
//建立文件
            fos = new FileOutputStream(file);

//保存文件
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
    }
}
