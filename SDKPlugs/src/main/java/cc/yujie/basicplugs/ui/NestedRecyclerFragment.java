package cc.yujie.basicplugs.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.yujie.basicplugs.R;
import cc.yujie.basicplugs.YuJieBaseFragment;

/**
 * Created by xwc on 2018/1/4.
 */

public class NestedRecyclerFragment extends YuJieBaseFragment {

    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nested_recyclerview, container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_frag_body);
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