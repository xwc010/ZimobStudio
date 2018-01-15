package cc.yujie.libs.data;

import java.util.List;

import cc.yujie.libs.model.Tab;
import cc.zimo.dataplugs.mvp.IPresenter;
import cc.zimo.dataplugs.mvp.IView;

/**
 * Created by xwc on 2017/12/29.
 */

public interface TabContract {

    interface View extends IView<Presenter> {
        void updateTabBar(List<Tab> tabs);
        void getTabFail(int code, String msg);
        void showLoading();
        void closeLoading();
    }

    interface Presenter extends IPresenter {

    }
}
