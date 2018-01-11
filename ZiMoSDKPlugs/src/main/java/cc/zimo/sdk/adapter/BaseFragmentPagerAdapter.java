package cc.zimo.sdk.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwc on 2018/1/4.
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();

    public BaseFragmentPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (mFragmentTitleList.size() == mFragmentList.size()) {
            return mFragmentTitleList.get(position);
        }
        return "";
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        return mFragmentList.get(position);
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return super.isViewFromObject(view, object);
//    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public boolean setFragments(@NonNull List<Fragment> fragmentList, @NonNull List<String> pageTitleList) {
        if (fragmentList == null || pageTitleList == null) {
            return false;
        }

        if (fragmentList.size() != pageTitleList.size()) {
            return false;
        }

        mFragmentList = fragmentList;
        mFragmentTitleList = pageTitleList;
        return true;
    }

    public boolean setFragments(@NonNull List<Fragment> fragmentList) {
        if (fragmentList == null) {
            return false;
        }

        mFragmentList = fragmentList;
        return true;
    }
}
