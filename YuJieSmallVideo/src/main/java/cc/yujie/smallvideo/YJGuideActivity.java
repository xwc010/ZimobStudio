package cc.yujie.smallvideo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import cc.yujie.basicplugs.YuJieBaseGuideActivity;

/**
 * Created by xwc on 2017/12/11.
 */

public class YJGuideActivity extends YuJieBaseGuideActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(YJGuideActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
