package cc.yujie.sexalbum.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import cc.yujie.sexalbum.bean.IData;

/**
 * Created by xwc on 2017/12/22.
 */

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<IData> mDataList;

    public RVAdapter(List<IData> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
