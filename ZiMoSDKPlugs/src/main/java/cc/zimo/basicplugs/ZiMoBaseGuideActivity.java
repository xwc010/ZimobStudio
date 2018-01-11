package cc.zimo.basicplugs;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 启动&导航界面（插屏/视频广告展示）
 * Created by xwc on 2017/12/11.
 */

public class ZiMoBaseGuideActivity extends ZiMoBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cc.zimo.basicplugs.R.layout.activity_basic_guide);
    }

    @Override
    public boolean canSlideDismiss() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
