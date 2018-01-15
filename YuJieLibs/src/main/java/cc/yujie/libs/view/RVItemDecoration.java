package cc.yujie.libs.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xwc on 2018/1/15.
 */

public class RVItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //设定底部边距为1px
        outRect.set(0, 0, 0, 1);
    }
}
