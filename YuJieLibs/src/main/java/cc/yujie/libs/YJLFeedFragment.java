package cc.yujie.libs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cc.yujie.libs.adapter.YJLFeedAdapter;
import cc.yujie.libs.constant.EFeedType;
import cc.yujie.libs.data.FeedsContract;
import cc.yujie.libs.data.FeedsPresenter;
import cc.yujie.libs.model.Feed;
import cc.yujie.libs.model.Tab;
import cc.zimo.dataplugs.log.ZMLog;
import cc.zimo.sdk.ui.NestedRecyclerFragment;

/**
 * Created by xwc on 2018/1/11.
 */

public class YJLFeedFragment extends NestedRecyclerFragment implements FeedsContract.View {

    @Override
    public void initUI() {
        super.initUI();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置Item之间间隔样式
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(
//                getActivity(), DividerItemDecoration.HORIZONTAL));
//        mRecyclerView.addItemDecoration(new RVItemDecoration());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private Tab mTab;

    public void setTab(Tab tab) {
        mTab = tab;
    }

    private FeedsPresenter mFeedsPresenter;
    private YJLFeedAdapter feedAdapter;

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getFristFeed();
    }

    @Override
    protected boolean reloadToVisible() {
        return super.reloadToVisible();
    }

    @Override
    public boolean isActive() {
        return !isRemoving();
    }


    private void getFristFeed() {
        ZMLog.d(mTab.getName() + " - YuJieFeedFragment getFristFeed: ");
        if (mFeedsPresenter == null) {
            mFeedsPresenter = new FeedsPresenter(this, mTab);
        }

        mFeedsPresenter.start();
    }

    private void getNextFeed() {
        ZMLog.d(mTab.getName() + " - YuJieFeedFragment getNextFeed: ");
        if (mFeedsPresenter == null) {
            mFeedsPresenter = new FeedsPresenter(this, mTab);
        }

        mFeedsPresenter.loadNextPage(new Feed());
    }


    @Override
    public void onFirstSucc(List<Feed> datas) {
        ZMLog.d(mTab.getName() + " - YuJieFeedFragment onFirstSucc: " + datas.size());
        feedAdapter = new YJLFeedAdapter(getActivity(), datas);
        // 设置adapter
        mRecyclerView.setAdapter(feedAdapter);
        feedAdapter.setOnItemClickListener(createOnItemClickListener());
        feedAdapter.setOnItemChildClickListener(createOnItemChildClickListener());
    }

    @Override
    public void onFirstFail(int code, String msg) {
        ToastUtils.showLong("Get First Page Failed: code - " + code + "; msg - " + msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void onNextSucc(List<Feed> datas) {
        ZMLog.d(mTab.getName() + " - YuJieFeedFragment onNextSucc: " + datas.size());
        feedAdapter.addData(datas);
    }

    @Override
    public void onNextFail(int code, String msg) {
        ToastUtils.showLong("Get Next Page Failed: code - " + code + "; msg - " + msg);
    }

    @Override
    public void showLoadingNext() {

    }

    @Override
    public void closeLoadingNext() {

    }

    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener;

    private BaseQuickAdapter.OnItemClickListener createOnItemClickListener() {

        if (mOnItemClickListener == null) {
            mOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Feed feed = (Feed) adapter.getItem(position);

                    if (feed.getType() == EFeedType.VIDEO) {
                        YJLVideoActivity.startVideo(getActivity(), feed);
                    } else {

                    }
                }
            };
        }
        return mOnItemClickListener;
    }

    private BaseQuickAdapter.OnItemChildClickListener mOnItemChildClickListener;

    private BaseQuickAdapter.OnItemChildClickListener createOnItemChildClickListener() {

        if (mOnItemChildClickListener == null) {
            mOnItemChildClickListener = new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }
            };
        }
        return mOnItemChildClickListener;
    }
}
