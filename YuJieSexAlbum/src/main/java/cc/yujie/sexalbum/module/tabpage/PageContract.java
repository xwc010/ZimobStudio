package cc.yujie.sexalbum.module.tabpage;

import java.util.List;

import cc.zimo.dataplugs.mvp.IPresenter;
import cc.zimo.dataplugs.mvp.IView;
import cc.yujie.libs.model.Feed;

/**
 * Created by xwc on 2017/12/29.
 */

public interface PageContract {

    interface View extends IView<Presenter> {
        void onSuccess(List<Feed> datas);
        void onFail(int code);
    }

    interface Presenter extends IPresenter {
        void loadDatas(String reqTag);
    }
}
