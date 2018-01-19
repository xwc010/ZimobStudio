package cc.zimo.videoplugs.videoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.jzvd.JZVideoPlayerStandard;

public class ZMVideoPlayerStandardShowTextureViewAfterAutoComplete extends JZVideoPlayerStandard {
    public ZMVideoPlayerStandardShowTextureViewAfterAutoComplete(Context context) {
        super(context);
    }

    public ZMVideoPlayerStandardShowTextureViewAfterAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        thumbImageView.setVisibility(View.GONE);
    }

}
