package cc.yujie.sexalbum.module.tabbar;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cc.zimo.dataplugs.http.CallBack;
import cc.zimo.dataplugs.http.HttpParam;
import cc.zimo.dataplugs.http.ZiMoHttpClient;
import cc.yujie.libs.model.Tab;

/**
 * Created by xwc on 2017/12/29.
 */

public class TabPresenter implements TabContract.Presenter {

    private final String TAG = TabPresenter.class.getSimpleName();
    private final TabContract.View mTabsView;

    public TabPresenter(@NonNull TabContract.View mTabsView) {
        this.mTabsView = mTabsView;
    }

    @Override
    public void start() {
        ZiMoHttpClient.getInstance().doGet("dd", "http://zimob.cc/mutil/app/cc.yujie.sexalbum/tab.json", new CallBack() {
            @Override
            public void onSuccess(String reqTag, int resultCode, String response) {
                Log.i(TAG, resultCode + " - " + response);
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<Tab>>() {}.getType();
                    final List<Tab> tabs = gson.fromJson(response, type);
                    mTabsView.updateTabBar(tabs);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    mTabsView.getTabFail(HttpParam.DATA_PARSING_ERROR, "Data parsing error!");
                }
            }

            @Override
            public void onFaile(String reqTag, int resultCode, String msg) {
                super.onFaile(reqTag, resultCode, msg);
                mTabsView.getTabFail(resultCode, msg);
            }

            @Override
            public void onError(String reqTag, int resultCode, Throwable erro) {
                erro.printStackTrace();
                mTabsView.getTabFail(resultCode, "NetWork Error!");
            }
        });
    }
}
