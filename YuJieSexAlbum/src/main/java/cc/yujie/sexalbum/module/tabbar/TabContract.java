package cc.yujie.sexalbum.module.tabbar;

import java.util.List;

import cc.zimo.dataplugs.mvp.IPresenter;
import cc.zimo.dataplugs.mvp.IView;
import cc.yujie.libs.model.Tab;

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
