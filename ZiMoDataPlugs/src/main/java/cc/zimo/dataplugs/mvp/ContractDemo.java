package cc.zimo.dataplugs.mvp;

/**
 * Created by xwc on 2017/12/25.
 */

public interface ContractDemo {

    interface View extends IView<Presenter> {
        void showLoading();
        void loginSuccess();
        void loginFaile(int code);
    }

    interface Presenter extends IPresenter {
        void login(String name, String pwd);
    }
}
