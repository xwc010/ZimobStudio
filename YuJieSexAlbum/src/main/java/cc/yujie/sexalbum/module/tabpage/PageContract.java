package cc.yujie.sexalbum.module.tabpage;

import java.util.List;

import cc.yujie.dataplugs.mvp.IPresenter;
import cc.yujie.dataplugs.mvp.IView;
import cc.yujie.sexalbum.bean.IData;

/**
 * Created by xwc on 2017/12/29.
 */

public interface PageContract {

    interface View extends IView<Presenter> {
        void onSuccess(List<IData> datas);
        void onFail(int code);
    }

    interface Presenter extends IPresenter {
        void loadDatas(String reqTag);
    }
}
