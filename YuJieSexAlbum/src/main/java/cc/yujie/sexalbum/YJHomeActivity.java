package cc.yujie.sexalbum;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ToastUtils;

import cc.yujie.libs.YJLFeedTabFragment;
import cc.zimo.adplugs.view.ExitAdDialog;
import cc.zimo.sdk.ZMBaseHomeActivity;

public class YJHomeActivity extends ZMBaseHomeActivity {

    private YJLFeedTabFragment mFeedTabFragment;
    private FrameLayout fl_sexalbum_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initUI() {
        super.initUI();
        fl_sexalbum_home = (FrameLayout) findViewById(R.id.fl_sexalbum_home);

        mFeedTabFragment = new YJLFeedTabFragment();
        mFeedTabFragment.setFeedTagUrl("http://zimob.cc/mutil/app/cc.yujie.sexalbum/tab.json");

        FragmentTransaction fgTransactions = getSupportFragmentManager().beginTransaction();
        fgTransactions.add(R.id.fl_sexalbum_home,mFeedTabFragment);
        fgTransactions.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doBackgroundToForeground() {
        ToastUtils.showLong("从后台唤醒到前台, 检查此时是否需要出广告");
        mFeedTabFragment.setCurrentItem(0);
    }

    @Override
    protected AlertDialog createExitAdDialog() {
        return ExitAdDialog.bulide(this, "http://img02.tooopen.com/images/20160514/tooopen_sy_162511376742.jpg"
                    , "这是一条广告测试数据", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.showShort("点击广告");
                    }
                });
    }

}
