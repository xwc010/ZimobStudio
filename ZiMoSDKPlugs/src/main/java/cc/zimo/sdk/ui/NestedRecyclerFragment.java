package cc.zimo.sdk.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

import cc.zimo.sdk.R;
import cc.zimo.sdk.ZMBaseFragment;

/**
 * Created by xwc on 2018/1/4.
 */

public class NestedRecyclerFragment extends ZMBaseFragment {

    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nested_recyclerview, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_frag_body);
        // 解决NestedScrollView 嵌套RecyclerView滑动时有些粘连的感觉
//        mRecyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
    }

    @Override
    protected boolean reloadToVisible() {
        return super.reloadToVisible();
    }
}
