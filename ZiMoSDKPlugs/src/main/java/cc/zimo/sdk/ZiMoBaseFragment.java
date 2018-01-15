package cc.zimo.sdk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import cc.zimo.dataplugs.log.ZiMoLog;

/**
 * Created by xwc on 2017/12/11.
 */

public class ZiMoBaseFragment extends Fragment {

    //Fragment的View加载完毕的标记
    private boolean isViewCreated = false;
    //Fragment对用户可见的标记
    private boolean isUIVisible = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        initUI();
        lazy();
    }

    public void initUI(){

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazy();
        } else {
            isUIVisible = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void lazy(){
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
//        ZiMoLog.i("ZiMoBaseFragment lazy isViewCreated: " + isViewCreated + "; isUIVisible: " + isUIVisible);
        if ((isViewCreated && isUIVisible) || reloadToVisible()) {
            lazyLoad();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    protected void lazyLoad(){

    }

    /**
     * 当Fragment重新可见时是否需要拉取最新数据
     * @return
     */
    protected boolean reloadToVisible(){
        return false;
    }
}
