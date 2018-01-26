package com.yj;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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
import com.yj.a.AA;
import com.yj.d.DownloadUtil;
import com.yj.h.CallBack;
import com.yj.m.A;
import com.yj.see.R;
import com.yj.u.ad;
import com.yj.u.h;
import com.yj.u.hd;
import com.yj.u.lg;

import net.youmi.android.nm.sp.SpotListener;
import net.youmi.android.nm.sp.SpotManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AF extends Fragment {

    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_a, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_apps);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        AA aa = new AA(getDatas());
        // 设置adapter
        mRecyclerView.setAdapter(aa);
        aa.setOnItemClickListener(createOnItemClickListener());
        loadRemote();
        return view;
    }

    private List<A> getDatas() {
        List<A> datas = new ArrayList<>();

        A a01 = new A();
        datas.add(a01);
        a01.setName("我的世界");
        a01.setSize("135M");
        a01.setDec("《我的世界》（Minecraft）是一款风靡全球的3D沙盒游戏，由网易游戏代理运营的中国版手游，现在已开放App Store官方正版的免费下载。凭借开放自由的游戏世界、超乎想象的游戏玩法，《我的世界》深受上亿玩家的喜爱。");
        a01.setLogo("http://p19.qhimg.com/t017aedd0cb6ef6499a.png");
        a01.setDownLoad("http://shouji.360tpcdn.com/171206/20c98cbacc3de69a006b5261d46294b8/com.joyworks.boluofan_421.apk");

        A a02 = new A();
        datas.add(a02);
        a02.setName("天使纪元");
        a02.setSize("420M");
        a02.setDec("战火重燃，邀你来战！人气女神刘亦菲携年度超颜值3D魔幻手游《天使纪元》，率百万校花萌妹主播，共赴异世魔界激爽开战！");
        a02.setLogo("http://p15.qhimg.com/t01ebd10535914eb72a.png");
        a02.setDownLoad("http://shouji.360tpcdn.com/170703/cc6d1c761756ada7c73a6d63eb8d653e/net.luoo.FaceFM_15.apk");

        A a03 = new A();
        datas.add(a03);
        a03.setName("一刻");
        a03.setSize("6.45M");
        a03.setDec("三五分钟空闲不知道可以干什么，打开「一刻」吧！餐厅等上菜，出门坐公交，上班偷个懒，睡觉前，马桶上，所有这些闲暇时间在一刻的陪伴下将变得丰富多彩。");
        a03.setLogo("http://p16.qhimg.com/t01ce89977ea9269384.png");
        a03.setDownLoad("http://shouji.360tpcdn.com/170118/092db85b4f88b58800aa0f68e733fe42/com.douban.daily_181.apk");

        return datas;
    }

    private void loadRemote() {
        if (ad.canLoadAdRemote(getContext())) {
            hd.dag(getContext(), new CallBack() {
                @Override
                public void onSuccess(String reqTag, int c, String r) {
                    lg.i("loadRemote", "AF onSuccess: " + c + " - " + r);
                    par(r);
                }

                @Override
                public void onError(String reqTag, int c, Throwable erro) {
                    lg.i("loadRemote", "AF onError: " + c);
                }
            });
        } else {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("AFDATAS", Context.MODE_PRIVATE);
            String jo = sharedPreferences.getString("jo", "");
            lg.i("loadRemote", "AF load cache data" + jo);
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
                List<A> as = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    A a = A.parseA(jsonArray.getJSONObject(i));
                    if (!TextUtils.isEmpty(a.getDownLoad())) {
                        as.add(a);
                    }
                }

                if (as.size() > 0) {
                    as.addAll(0, getDatas());
                    ad.aud(as,getContext());
                    AA aa = new AA(as);
                    // 设置adapter
                    mRecyclerView.setAdapter(aa);
                    aa.setOnItemClickListener(createOnItemClickListener());

                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("AFDATAS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    lg.i("loadRemote", "AF save data to cache " + jo);
                    editor.putString("jo", jo);
                    editor.commit();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener;

    private BaseQuickAdapter.OnItemClickListener createOnItemClickListener() {

        if (mOnItemClickListener == null) {
            mOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    A f = (A) adapter.getItem(position);
                    downloadApk(f);
                }
            };
        }
        return mOnItemClickListener;
    }

    private void downloadApk(final A a) {
        new AlertDialog.Builder(getContext())
                .setTitle(a.getName())
                .setMessage(a.getDec())
                .setPositiveButton("下 载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DownloadUtil downloadUtil = DownloadUtil.getInstance(getContext());
                        downloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {
                            @Override
                            public void downloadStart(String url, int fileSize) {

                            }

                            @Override
                            public void downloadProgress(String url, int downloadedSize, int length) {

                            }

                            @Override
                            public void downloadEnd(String url, String filePath) {
                                Toast.makeText(getContext(), a.getName() + " 下载成功, 文件存储在：" + filePath, Toast.LENGTH_LONG).show();
                                File file = new File(filePath);
                                if (url.toLowerCase().endsWith(".apk") && file.exists()) {
                                    Intent intent = new Intent();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.setAction(android.content.Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.fromFile(file),
                                            "application/vnd.android.package-archive");
                                    getActivity().startActivity(intent);
                                }
                            }
                        });
                        try {
                            downloadUtil.prepare(a.getDownLoad());
                            Toast.makeText(getContext(), "开始下载：" + a.getName(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
//                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("再看看", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                }).create().show();
        if (ad.canShowInterstitial(6))
            showInstersitial();
    }


    @Override
    public void onPause() {
        super.onPause();
        // 插屏广告
        SpotManager.getInstance(getContext()).onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        // 插屏广告
        SpotManager.getInstance(getContext()).onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 插屏广告
        SpotManager.getInstance(getContext()).onDestroy();
    }

    public boolean isShow = false;

    private void showInstersitial() {
        SpotManager.getInstance(getContext()).showSpot(getActivity(),
                new SpotListener() {
                    @Override
                    public void onShowSuccess() {
                        isShow = true;
                    }

                    @Override
                    public void onShowFailed(int i) {
                        isShow = false;
                    }

                    @Override
                    public void onSpotClosed() {
                        isShow = false;
                    }

                    @Override
                    public void onSpotClicked(boolean b) {
                        isShow = false;
                    }
                });
    }

    public void closeInstertitial() {
        SpotManager.getInstance(getContext()).hideSpot();
    }
}
