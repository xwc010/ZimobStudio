package cc.yujie.libs.data;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cc.yujie.libs.constant.DesplayMode;
import cc.yujie.libs.constant.FeedGson;
import cc.yujie.libs.model.Feed;
import cc.yujie.libs.model.Tab;
import cc.zimo.dataplugs.http.CallBack;
import cc.zimo.dataplugs.http.HttpParam;
import cc.zimo.dataplugs.http.ZiMoHttpClient;
import cc.zimo.dataplugs.log.ZiMoLog;

/**
 * Created by xwc on 2018/1/15.
 */

public class FeedsPresenter implements FeedsContract.Presenter {

    private final int ONE_PAGE_NUM = 20; // 一页feed数
    private Tab mTab;
    private FeedsContract.View mFeedsView;

    public FeedsPresenter(FeedsContract.View view, Tab tab) {
        this.mTab = tab;
        this.mFeedsView = view;
    }

    @Override
    public void start() {
        if(mTab == null || TextUtils.isEmpty(mTab.getDatasUrl())){
            ZiMoLog.e("FeedsPresenter url is empty");
            return;
        }

        ZiMoLog.d("FeedsPresenter start() url is: " + mTab.getDatasUrl());

        if(getRomte().size()> 0){
            mFeedsView.onFirstSucc(getRomte());
            return;
        }

        ZiMoHttpClient.getInstance().doGet("dd", mTab.getDatasUrl(), new CallBack() {
            @Override
            public void onSuccess(String reqTag, int resultCode, String response) {
                ZiMoLog.i("FeedsPresenter start() "+ resultCode + " - " + response);
                try {
                    Gson gson = FeedGson.getGson();
                    Type type = new TypeToken<ArrayList<Feed>>() {}.getType();
                    final List<Feed> feeds = gson.fromJson(response, type);

                    mFeedsView.onFirstSucc(feeds);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    mFeedsView.onFirstFail(HttpParam.DATA_PARSING_ERROR, "Data parsing error!");
                }
            }

            @Override
            public void onFaile(String reqTag, int resultCode, String msg) {
                super.onFaile(reqTag, resultCode, msg);
                mFeedsView.onFirstFail(resultCode, msg);
            }

            @Override
            public void onError(String reqTag, int resultCode, Throwable erro) {
                erro.printStackTrace();
                mFeedsView.onFirstFail(resultCode, "NetWork Error!");
            }
        });
    }

    /**
     * 加载下一页
     * @param cuLastFeed 当前最后一条Feed
     */
    @Override
    public void loadNextPage(Feed cuLastFeed) {
        if(mTab == null || TextUtils.isEmpty(mTab.getDatasUrl())){
            ZiMoLog.e("FeedsPresenter url is empty");
            return;
        }

        ZiMoLog.d("FeedsPresenter start() url is: " + mTab.getDatasUrl());

        if(getRomte().size()> 0){
            mFeedsView.onFirstSucc(getRomte());
            return;
        }

        ZiMoHttpClient.getInstance().doGet("dd", mTab.getDatasUrl(), new CallBack() {
            @Override
            public void onSuccess(String reqTag, int resultCode, String response) {
                ZiMoLog.i("FeedsPresenter start() "+ resultCode + " - " + response);
                try {
                    Gson gson = FeedGson.getGson();
                    Type type = new TypeToken<ArrayList<Feed>>() {}.getType();
                    final List<Feed> feeds = gson.fromJson(response, type);

                    mFeedsView.onNextSucc(feeds);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    mFeedsView.onNextFail(HttpParam.DATA_PARSING_ERROR, "Data parsing error!");
                }
            }

            @Override
            public void onFaile(String reqTag, int resultCode, String msg) {
                super.onFaile(reqTag, resultCode, msg);
                mFeedsView.onNextFail(resultCode, msg);
            }

            @Override
            public void onError(String reqTag, int resultCode, Throwable erro) {
                erro.printStackTrace();
                mFeedsView.onNextFail(resultCode, "NetWork Error!");
            }
        });
    }

    private List<Feed> getRomte(){
        ArrayList<Feed> maps = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Feed feed = new Feed();
            int mode = i%5;
            feed.setDisplay_mode(mode);

            switch (mode){
                case DesplayMode.LARGE:{
                    feed.setTitle("这是大图模式 " + i);
                    feed.setThumbnail("https://img2.tuicool.com/BvaYfuY.jpg!web");
                    break;
                }
                case DesplayMode.LEFT: {
                    feed.setTitle("这是卓图模式 " + i);
                    feed.setThumbnail("https://aimg0.tuicool.com/AvAJfyF.jpg!index");
                    break;
                }
                case DesplayMode.RIGHT: {
                    feed.setTitle("这是右图模式 " + i);
                    feed.setThumbnail("https://aimg1.tuicool.com/y2AVJvV.jpg!index");
                    break;
                }
                case DesplayMode.GRIDS: {
                    feed.setTitle("这是九宫格模式 " + i);
                    break;
                }
                case DesplayMode.TEXT: {
                    feed.setTitle("这是文本模式 " + i);
                    break;
                }
            }

            maps.add(feed);
        }

        return maps;
    }
}
