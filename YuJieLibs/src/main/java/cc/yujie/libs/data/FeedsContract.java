package cc.yujie.libs.data;

import java.util.List;

import cc.yujie.libs.model.Feed;
import cc.yujie.libs.model.Tab;
import cc.zimo.dataplugs.mvp.IPresenter;
import cc.zimo.dataplugs.mvp.IView;

/**
 * Created by xwc on 2017/12/29.
 */

public interface FeedsContract {

    interface View extends IView<Presenter> {
        void onFirstSucc(List<Feed> datas);
        void onFirstFail(int code, String msg);
        void showLoading();
        void closeLoading();
        void onNextSucc(List<Feed> datas);
        void onNextFail(int code, String msg);
        void showLoadingNext();
        void closeLoadingNext();
    }

    interface Presenter extends IPresenter {
        void loadNextPage(Feed culastFeed);
    }
}
