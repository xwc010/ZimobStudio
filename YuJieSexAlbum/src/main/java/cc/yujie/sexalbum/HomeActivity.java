package cc.yujie.sexalbum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import cc.yujie.basicplugs.YuJieBaseHomeActivity;
import cc.yujie.basicplugs.YuJieBaseWebActivity;
import cc.yujie.dataplugs.http.CallBack;
import cc.yujie.dataplugs.http.YuHttpClient;
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
