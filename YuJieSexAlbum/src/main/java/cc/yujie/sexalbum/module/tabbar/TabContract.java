package cc.yujie.sexalbum.module.tabbar;

import java.util.List;

import cc.yujie.dataplugs.mvp.IPresenter;
import cc.yujie.dataplugs.mvp.IView;
import cc.yujie.sexalbum.bean.Tab;

/**
 * Created by xwc on 2017/12/29.
 */

public interface TabContract {

    interface View extends IView<Presenter> {
        void updateTabBar(List<Tab> tabs);
        void getTabFail(int code, String msg);
    }

    interface Presenter extends IPresenter {

    }
}
