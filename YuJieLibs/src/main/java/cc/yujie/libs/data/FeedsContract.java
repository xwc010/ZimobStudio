package cc.yujie.libs.data;

import java.util.List;

import cc.yujie.libs.model.Feed;
import cc.zimo.dataplugs.mvp.IPresenter;
import cc.zimo.dataplugs.mvp.IView;

/**
 * Created by xwc on 2017/12/29.
 */

public interface FeedsContract {

    interface View extends IView<Presenter> {
        void onSuccess(List<Feed> datas);
        void onFail(int code);
    }

    interface Presenter extends IPresenter {
        void loadDatas(String reqTag);
    }
}
