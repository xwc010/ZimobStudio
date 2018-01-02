package cc.yujie.sexalbum;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import cc.yujie.adplugs.view.ExitAdDialog;
import cc.yujie.basicplugs.YuJieBaseHomeActivity;
import cc.yujie.basicplugs.YuJieBaseWebActivity;
import cc.yujie.sexalbum.bean.Tab;
import cc.yujie.sexalbum.module.tabbar.TabContract;
import cc.yujie.sexalbum.module.tabbar.TabPresenter;

public class HomeActivity extends YuJieBaseHomeActivity implements TabContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, YuJieBaseWebActivity.class);
                startActivity(intent);
            }
        });

        // http://zimob.cc/mutil/app/
        TabPresenter tabPresenter = new TabPresenter(this);
        tabPresenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doBackgroundToForeground() {
        ToastUtils.showLong("从后台唤醒到前台, 检查此时是否需要出广告");
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

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @Override
    public void updateTabBar(List<Tab> tabs) {
        ToastUtils.showShort("Tab.size = " + tabs.size());
    }

    @Override
    public void getTabFail(int code, String msg) {
        ToastUtils.showShort("code = " + code + "; msg: " + msg);
    }
}
