package cc.yujie.sexalbum;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import cc.zimo.adplugs.view.ExitAdDialog;
import cc.zimo.basicplugs.ZiMoBaseHomeActivity;
import cc.zimo.basicplugs.adapter.BaseFragmentPagerAdapter;
import cc.yujie.libs.model.Tab;
import cc.yujie.sexalbum.module.tabbar.TabContract;
import cc.yujie.sexalbum.module.tabbar.TabPresenter;
import cc.yujie.sexalbum.module.tabpage.PageFragment;

public class HomeActivity extends ZiMoBaseHomeActivity implements TabContract.View{

    private Toolbar mToolbar;
    private TabLayout topTabLayout;
    private ViewPager mViewPager;
    private List<Tab> mTabRes;
    private BaseFragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TabPresenter tabPresenter = new TabPresenter(this);
        tabPresenter.start();

    }

    @Override
    public void initUI() {
        super.initUI();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        topTabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        pagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doBackgroundToForeground() {
        ToastUtils.showLong("从后台唤醒到前台, 检查此时是否需要出广告");
        if(mViewPager != null){
            mViewPager.setCurrentItem(0, false);
        }
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
        mTabRes = tabs;

        for (Tab tab : mTabRes) {
//            topTabLayout.addTab(topTabLayout.newTab().setText(tab.getName()).setTag(tab));
            PageFragment pageFragment = new PageFragment();
            pageFragment.setTab(tab);
            pagerAdapter.addFragment(pageFragment, tab.getName());
        }

        mViewPager.setAdapter(pagerAdapter);
        topTabLayout.setupWithViewPager(mViewPager, false);
    }

    @Override
    public void getTabFail(int code, String msg) {
        ToastUtils.showShort("code = " + code + "; msg: " + msg);
    }
}
