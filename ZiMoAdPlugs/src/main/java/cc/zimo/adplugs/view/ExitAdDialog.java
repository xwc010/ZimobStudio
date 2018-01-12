package cc.zimo.adplugs.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cc.zimo.adplugs.R;
import cc.zimo.imageplugs.ZiMoImgLoader;

/**
 * Created by xwc on 2018/1/2.
 */

public class ExitAdDialog {

    public static void show(Activity context, String adImgURL, String describe, View.OnClickListener clickListener) {
        bulide(context, adImgURL, describe, clickListener).show();
    }

    public static void show(Activity context, View view, View.OnClickListener clickListener) {
        bulide(context, view, clickListener).show();
    }

    public static AlertDialog bulide(final Activity context, String adImgURL, String describe, final View.OnClickListener clickListener) {
        View view = null;
        if(!TextUtils.isEmpty(adImgURL) && !TextUtils.isEmpty(describe)){
            view = LayoutInflater.from(context).inflate(R.layout.zimo_ads_exit_dialog, null, false);
            ImageView adImg = (ImageView) view.findViewById(R.id.img_ads_exit_diaolg);
            ZiMoImgLoader.getInstance().loadImg(adImgURL, adImg);
            TextView adDesc = (TextView) view.findViewById(R.id.tv_ads_exit_diaolg);
            adDesc.setText(describe);
        }

        return bulide(context, view, clickListener);
    }

    public static AlertDialog bulide(final Activity context, View view, final View.OnClickListener clickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("亲，是要走了吗？")
                .setPositiveButton("忍痛离去", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //finish();//返回系统登录界面
                        //使用隐式Intent返回系统home界面
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("再逛逛", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        final AlertDialog dialog = builder.create();

        if(view != null){
            if(clickListener != null){
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        clickListener.onClick(view);
                    }
                });
            }

            dialog.setView(view);
        }
        return dialog;
    }
}
