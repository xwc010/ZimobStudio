package com.yj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.yj.see.R;
import com.yj.u.ad;
import com.yj.u.lg;

import net.youmi.android.nm.bn.BannerManager;
import net.youmi.android.nm.bn.BannerViewListener;
import net.youmi.android.nm.sp.SpotListener;
import net.youmi.android.nm.sp.SpotManager;

public class IA extends AppCompatActivity {

    private ImageView img_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.a_i);

        img_show = (ImageView) findViewById(R.id.img_show);

        if (getActionBar() != null) {
            getActionBar().hide();
        }

        String imgUrl = getIntent().getStringExtra("imgUrl");
        Picasso.with(this).load(imgUrl)
                .placeholder(R.drawable.img_loading_default)
                .error(R.drawable.img_loading_default)
                .into(img_show);

        if (ad.canShowAd()) {
            showBottomBanner();
            showTopBanner();

            if (ad.canShowInterstitial(4))
                showInstersitial();
        }
    }

    public static void startShowImg(Context context, String imgUrl) {
        Intent intent = new Intent(context, IA.class);
        intent.putExtra("imgUrl", imgUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 插屏广告
        SpotManager.getInstance(getApplicationContext()).onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 插屏广告
        SpotManager.getInstance(getApplicationContext()).onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 插屏广告
        SpotManager.getInstance(getApplicationContext()).onDestroy();
        // 展示广告条窗口的 onDestroy() 回调方法中调用
        BannerManager.getInstance(getApplicationContext()).onDestroy();
    }

    @Override
    public void onBackPressed() {
        // 如果有需要，可以点击后退关闭插播广告。
        if (isShow) {
            SpotManager.getInstance(getApplicationContext()).hideSpot();
        } else {
            super.onBackPressed();
        }
    }

    private void showTopBanner() {
        // 实例化 LayoutParams（重要）
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        // 设置广告条的悬浮位置
        layoutParams.gravity = Gravity.TOP | Gravity.RIGHT; // 这里示例为右下角

        // 获取广告条
        View bannerView01 = BannerManager.getInstance(getApplicationContext())
                .getBannerView(this, new BannerViewListener() {
                    @Override
                    public void onRequestSuccess() {
                        lg.i("YJAd", "top banner onRequestSuccess");
                    }

                    @Override
                    public void onSwitchBanner() {
                        lg.i("YJAd", "top banner onSwitchBanner");
                    }

                    @Override
                    public void onRequestFailed() {
                        lg.i("YJAd", "top banner onRequestFailed");
                    }
                });

        // 调用 Activity 的 addContentView 函数
        addContentView(bannerView01, layoutParams);
    }

    private void showBottomBanner() {
        // 获取广告条
        View bannerView = BannerManager.getInstance(getApplicationContext())
                .getBannerView(getApplicationContext(), new BannerViewListener() {
                    @Override
                    public void onRequestSuccess() {
                        lg.i("YJAd", "bottom banner onRequestSuccess");
                    }

                    @Override
                    public void onSwitchBanner() {
                        lg.i("YJAd", "bottom banner onSwitchBanner");
                    }

                    @Override
                    public void onRequestFailed() {
                        lg.i("YJAd", "bottom banner onRequestFailed");
                    }
                });

        // 获取要嵌入广告条的布局
        LinearLayout bannerLayout = (LinearLayout) findViewById(R.id.ll_ads);
        // 将广告条加入到布局中
        bannerLayout.addView(bannerView);
    }

    boolean isShow = false;

    private void showInstersitial() {
        SpotManager.getInstance(getApplicationContext()).showSpot(this,
                new SpotListener() {
                    @Override
                    public void onShowSuccess() {
                        isShow = true;
                    }

                    @Override
                    public void onShowFailed(int i) {
                        isShow = false;
                    }

                    @Override
                    public void onSpotClosed() {
                        isShow = false;
                    }

                    @Override
                    public void onSpotClicked(boolean b) {
                        isShow = false;
                    }
                });
    }
}
