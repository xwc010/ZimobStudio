package com.yj.u;

import android.content.Context;

import com.yj.m.F;

import java.util.ArrayList;

/**
 * Created by xwc on 2018/2/3.
 */

public class fesa {

    public static boolean save(Context context, F f){
        try {
            ArrayList<F> fs = getDatas(context);
            if(fs == null){
                fs = new ArrayList<>();
            }

            boolean contanct = false;
            for (F f1 : fs) {
                if(f1.getImgUrl().equals(f.getImgUrl())){
                    contanct = true;
                    break;
                }
            }

            if(!contanct){
                fs.add(f);
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
}
