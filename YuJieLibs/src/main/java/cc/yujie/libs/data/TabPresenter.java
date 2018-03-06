package cc.yujie.libs.data;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cc.yujie.libs.model.Tab;
import cc.zimo.dataplugs.http.CallBack;
import cc.zimo.dataplugs.http.HttpParam;
import cc.zimo.dataplugs.http.ZMHttpClient;
import cc.zimo.dataplugs.log.ZMLog;

/**
 * Created by xwc on 2017/12/29.
 */

public class TabPresenter implements TabContract.Presenter {

    private final TabContract.View mTabsView;
    private String url;

    public TabPresenter(@NonNull TabContract.View mTabsView, String url) {
        this.mTabsView = mTabsView;
        this.url = url;
    }

    @Override
    public void start() {
        if(TextUtils.isEmpty(this.url)){
            ZMLog.e("TabPresenter url is empty");
            return;
        }

        ZMLog.d("TabPresenter url is: " + url);

        mTabsView.showLoading();
        ZMHttpClient.getInstance().doGet("dd", this.url, new CallBack() {
            @Override
            public void onSuccess(String reqTag, int resultCode, String response) {
                ZMLog.d(response);
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<Tab>>() {}.getType();
                    final List<Tab> tabs = gson.fromJson(response, type);
                    mTabsView.updateTabBar(tabs);
                    mTabsView.closeLoading();
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    mTabsView.getTabFail(HttpParam.DATA_PARSING_ERROR, "Data parsing error!");
                    mTabsView.closeLoading();
                }
            }

            @Override
            public void onFail(String reqTag, int resultCode, String msg) {
                super.onFail(reqTag, resultCode, msg);
                mTabsView.getTabFail(resultCode, msg);
                mTabsView.closeLoading();
            }

            @Override
            public void onError(String reqTag, int resultCode, Throwable erro) {
                erro.printStackTrace();
                mTabsView.getTabFail(resultCode, "NetWork Error!");
                mTabsView.closeLoading();
            }
        });
    }
}
