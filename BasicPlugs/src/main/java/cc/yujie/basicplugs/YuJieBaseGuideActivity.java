package cc.yujie.basicplugs;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 启动&导航界面（插屏/视频广告展示）
 * Created by xwc on 2017/12/11.
 */

public class YuJieBaseGuideActivity extends YuJieBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_guide);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
