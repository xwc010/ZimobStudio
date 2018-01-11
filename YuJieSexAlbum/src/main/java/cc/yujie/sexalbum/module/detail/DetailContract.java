package cc.yujie.sexalbum.module.detail;

import cc.zimo.dataplugs.mvp.IPresenter;
import cc.zimo.dataplugs.mvp.IView;

/**
 * Created by xwc on 2017/12/29.
 */

public interface DetailContract {

    interface View extends IView<Presenter> {
        void updateLikeNum(int num);
        void likeFail(String id, int code);
        void updateUnLikeNum(int num);
        void unLikeFail(String id, int code);
        void sendCommentSucc();
        void sendCommentFail(int code);
    }

    interface Presenter extends IPresenter {
        void like(String id);
        void unLike(String id);
        void sendComment(String id, String msg);
    }
}
