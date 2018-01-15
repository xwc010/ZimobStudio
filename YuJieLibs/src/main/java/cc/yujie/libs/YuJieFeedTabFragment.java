package cc.yujie.libs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import cc.yujie.libs.data.TabContract;
import cc.yujie.libs.data.TabPresenter;
import cc.yujie.libs.model.Tab;
import cc.zimo.dataplugs.log.ZiMoLog;
import cc.zimo.sdk.ZiMoBaseFragment;
import cc.zimo.sdk.adapter.BaseFragmentPagerAdapter;

/**
 * Created by xwc on 2018/1/12.
 */

public class YuJieFeedTabFragment extends ZiMoBaseFragment implements TabContract.View {

    private Toolbar mToolbar;
    private TabLayout topTabLayout;
    private ViewPager mViewPager;
    private List<Tab> mTabRes;
    private BaseFragmentPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lib_feed_tags, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.tbar_lib);
        mToolbar.setTitle(AppUtils.getAppName());
        topTabLayout = (TabLayout) view.findViewById(R.id.tl_lib_feed_tabs);

        mViewPager = (ViewPager) view.findViewById(R.id.vp_lib_feed_pages);
        mViewPager.setOffscreenPageLimit(3);
        pagerAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager());

        TabPresenter tabPresenter = new TabPresenter(this, mFeedTagUrl);
        tabPresenter.start();
        ZiMoLog.i("ZiMoBaseFragment onCreateView");
        return view;
    }


    private String mFeedTagUrl;

    public void setFeedTagUrl(String url) {
        mFeedTagUrl = url;
    }

    public void setCurrentItem(int position) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(position, false);
        }
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        ToastUtils.showShort("lazy");
        TabPresenter tabPresenter = new TabPresenter(this, mFeedTagUrl);
        tabPresenter.start();
    }

    @Override
    protected boolean reloadToVisible() {
        return super.reloadToVisible();
    }


    @Override
    public boolean isActive() {
        return !isRemoving();
    }

    @Override
    public void updateTabBar(List<Tab> tabs) {
        mTabRes = tabs;
        for (Tab tab : mTabRes) {
//            topTabLayout.addTab(topTabLayout.newTab().setText(tab.getName()).setTag(tab));
            YuJieFeedFragment pageFragment = new YuJieFeedFragment();
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

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }
}
