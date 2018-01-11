package cc.zimo.dataplugs.mvp;

/**
 * Created by xwc on 2017/12/22.
 */

public interface IView <T extends IPresenter> {
    public boolean isActive();
}
