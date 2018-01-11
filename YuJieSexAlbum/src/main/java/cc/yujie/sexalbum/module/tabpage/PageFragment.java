package cc.yujie.sexalbum.module.tabpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cc.zimo.sdk.ui.NestedRecyclerFragment;
import cc.yujie.sexalbum.R;
import cc.yujie.libs.model.Tab;

/**
 * Created by xwc on 2018/1/4.
 */

public class PageFragment extends NestedRecyclerFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private Tab mTab;
    public void setTab(Tab tab){
        mTab = tab;
    }

    private String[] city = {"广州","深圳","北京","上海","香港","澳门","天津","广州","深圳","北京","上海","香港","澳门","天津"} ;

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        RVAdapter rvAdapter = new RVAdapter(getDatas());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置Item之间间隔样式
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL));
        // 设置adapter
        mRecyclerView.setAdapter(rvAdapter);
    }

    @Override
    protected boolean reloadToVisible() {
        return super.reloadToVisible();
    }

    private ArrayList<Map<String, String>> getDatas(){
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        for (int i = 0; i < city.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", city[i]);
            map.put("value", city[i] + "ddd");
            maps.add(map);
        }

        return maps;
    }

    class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{

        private ArrayList<Map<String, String>> appKeyList;

        public RVAdapter(ArrayList<Map<String, String>> appKeyList) {
            this.appKeyList = appKeyList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 实例化展示的view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_key, parent, false);
            // 实例化viewholder
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // 绑定数据
            String gameName = appKeyList.get(position).get("name");
            String gameKey = appKeyList.get(position).get("value");
            holder.mTvName.setText(gameName);
            holder.mTvKey.setText(gameKey);
            //将数据保存在itemView的Tag中，以便点击时进行获取
            holder.itemView.setTag(appKeyList.get(position));
        }

        @Override
        public int getItemCount() {
            return appKeyList == null ? 0 : appKeyList.size();
        }

        public void addItem(Map<String, String> content, int position) {
            appKeyList.add(position, content);
            notifyItemInserted(position); //Attention!
        }

        public void removeItem(Map<String, String> model) {
            int position = appKeyList.indexOf(model);
            appKeyList.remove(position);
            notifyItemRemoved(position);//Attention!
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView mTvName;
            TextView mTvKey;

            public ViewHolder(View itemView) {
                super(itemView);
                mTvName = (TextView) itemView.findViewById(R.id.item_tv_name);
                mTvName.setTextSize(19);
                mTvKey = (TextView) itemView.findViewById(R.id.item_tv_key);
            }
        }
    }
}
