package com.yj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yj.m.F;
import com.yj.see.R;
import com.yj.u.fesa;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;

public class FA extends AppCompatActivity {

    protected RecyclerView mRecyclerView;
    private List<F> allList = new ArrayList<>();
    private com.yj.a.FA mFa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_f);
        Toolbar tbar_f = (Toolbar) findViewById(R.id.tbar_f);
        tbar_f.setTitle("收藏夹");
        setSupportActionBar(tbar_f);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_imgs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFa = new com.yj.a.FA(this, fesa.getDatas(getApplicationContext()));
        if (mFa.getItemCount() == 0) {
            Toast.makeText(this, "赶紧去收藏试试", Toast.LENGTH_SHORT).show();
        }
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
        mFa.setOnItemClickListener(createOnItemClickListener());
    }

    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener;

    private BaseQuickAdapter.OnItemClickListener createOnItemClickListener() {

        if (mOnItemClickListener == null) {
            mOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    F f = (F) adapter.getItem(position);
                    IA.startShowImg(FA.this, f);
                }
            };
        }
        return mOnItemClickListener;
    }
}
