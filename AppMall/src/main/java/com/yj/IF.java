package com.yj;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yj.a.FA;
import com.yj.h.CallBack;
import com.yj.m.F;
import com.yj.see.R;
import com.yj.u.ad;
import com.yj.u.d;
import com.yj.u.h;
import com.yj.u.hd;
import com.yj.u.lg;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;

public class IF extends Fragment {

    protected RecyclerView mRecyclerView;
    private List<F> allList = new ArrayList<>();
    private List<F> cuList = new ArrayList<>();
    private final int NUM = 15;
    private FA mFa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_i, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_imgs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initFirst(getDatas());
        loadRemote();
        // 设置adapter
        mRecyclerView.setAdapter(mFa);
        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                JZVideoPlayer.onChildViewAttachedToWindow(view, R.id.videoplayer);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                JZVideoPlayer.onChildViewDetachedFromWindow(view);
            }
        });

        return view;
    }

    private int cuPage = 1;

    Handler handler = new Handler();

    private void showPage(final int p) {
        int showNum = NUM * p;
        if (showNum >= allList.size() && showNum < allList.size() + NUM) {
            cuList.clear();
            cuList.addAll(allList);
            cuPage = p;
            if (mFa != null) {
                mFa.setEnableLoadMore(true);
                mFa.loadMoreComplete();
            }
        } else if (showNum < allList.size()) {
            cuList.clear();
            cuList.addAll(allList.subList(0, showNum));
            cuPage = p;
            if (mFa != null) {
                mFa.setEnableLoadMore(true);
                mFa.loadMoreComplete();
            }
        } else {
            if (mFa != null) {
                mFa.setEnableLoadMore(false);
                mFa.loadMoreComplete();
            }
            Toast.makeText(getContext(), "人家是有原则底线的嘛！", Toast.LENGTH_SHORT).show();
        }
    }


    private List<F> getDatas() {
        List<F> datas = new ArrayList<>();

        F f = new F();
        datas.add(f);
        f.setTitle("归属感大厦的gas贡嘎山");
        f.setImgUrl("http://img.ivsky.com/img/tupian/co/201710/31/kaixin_nvhai.jpg");

        F f01 = new F();
        datas.add(f01);
        f01.setTitle("各个国家法规的三国杀");
        f01.setImgUrl("http://img.ivsky.com/img/tupian/co/201710/31/meili_de_xinjiang-008.jpg");
        f01.setVideoUrl("http://zimob.cc/mutil/videos/1111.vdat");

        F f02 = new F();
        datas.add(f02);
        f02.setTitle("我深V的三个傻瓜");
        f02.setImgUrl("http://img.ivsky.com/img/bizhi/co/201801/08/riben_chengshi_jiedao-004.jpg");

        F f04 = new F();
        datas.add(f04);
        f04.setTitle("归属感大厦的gas贡嘎山");
        f04.setImgUrl("http://img.ivsky.com/img/tupian/co/201710/31/kaixin_nvhai.jpg");

        F f05 = new F();
        datas.add(f05);
        f05.setTitle("各个国家法规的三国杀");
        f05.setImgUrl("http://img.ivsky.com/img/tupian/co/201710/31/meili_de_xinjiang-008.jpg");

        F f06 = new F();
        datas.add(f06);
        f06.setTitle("我深V的三个傻瓜");
        f06.setImgUrl("http://img.ivsky.com/img/bizhi/co/201801/08/riben_chengshi_jiedao-004.jpg");

        return datas;
    }

    private void loadRemote() {
        if (ad.canLoadFeedRemote(getContext())) {
            hd.dfg(getContext(), new CallBack() {
                @Override
                public void onSuccess(String reqTag, int resultCode, String response) {
                    lg.i("loadRemote", "IF onSuccess: " + resultCode + " - " + response);
                    par(response);
                }

                @Override
                public void onError(String reqTag, int resultCode, Throwable erro) {

                }
            });
        } else {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("IFDATAS", Context.MODE_PRIVATE);
            String jo = sharedPreferences.getString("jo", "");
            lg.i("loadRemote", "IF load cache data" + jo);
            if (!TextUtils.isEmpty(jo)) {
                par(jo);
            }
        }
    }

    private void par(String jo) {
        try {
            String jjo = jo;
            if (!jjo.contains(":") || !jjo.contains("http")) {
                jjo = new String(h.hb(jo));
            }
            JSONArray jsonArray = new JSONArray(jjo);
            if (jsonArray != null && jsonArray.length() > 0) {
                List<F> as = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    F a = F.parseF(jsonArray.getJSONObject(i));
                    if (!TextUtils.isEmpty(a.getImgUrl())) {
                        as.add(a);
                    }
                }

                if (as.size() > 0) {
                    as.addAll(0, getDatas());
                    initFirst(as);
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("IFDATAS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    lg.i("loadRemote", "IF save data to cache " + jo);
                    editor.putString("jo", jo);
                    editor.commit();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initFirst(List<F> as) {
        allList = d.df(as);
        showPage(1);
        mFa = new FA(getActivity(), cuList);
        // 设置adapter
        mRecyclerView.setAdapter(mFa);
        mFa.setOnItemClickListener(createOnItemClickListener());
        //        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
//            @Override
//            public void onChildViewAttachedToWindow(View view) {
//            }
//
//            @Override
//            public void onChildViewDetachedFromWindow(View view) {
//                JZVideoPlayer jzvd = view.findViewById(R.id.videoplayer);
//                if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
//                    JZVideoPlayer.releaseAllVideos();
//                }
//            }
//        });
//        feedAdapter.setOnItemChildClickListener(createOnItemChildClickListener());
        mFa.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
    }

    private void loadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showPage(cuPage + 1);
                mFa.notifyDataSetChanged();
            }
        }, 3000);
    }

    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener;

    private BaseQuickAdapter.OnItemClickListener createOnItemClickListener() {

        if (mOnItemClickListener == null) {
            mOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    F f = (F) adapter.getItem(position);
                    IA.startShowImg(getContext(), f);
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

    @Override
    public void onPause() {
        super.onPause();
        releaseAllVideos();
    }

    public void releaseAllVideos() {
        JZVideoPlayer.releaseAllVideos();
    }
}
