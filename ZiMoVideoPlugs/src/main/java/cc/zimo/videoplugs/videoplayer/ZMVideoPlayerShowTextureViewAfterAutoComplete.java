package cc.zimo.videoplugs.videoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.jzvd.JZVideoPlayerStandard;

public class ZMVideoPlayerShowTextureViewAfterAutoComplete extends JZVideoPlayerStandard {
    public ZMVideoPlayerShowTextureViewAfterAutoComplete(Context context) {
        super(context);
    }

    public ZMVideoPlayerShowTextureViewAfterAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        thumbImageView.setVisibility(View.GONE);
    }

}
