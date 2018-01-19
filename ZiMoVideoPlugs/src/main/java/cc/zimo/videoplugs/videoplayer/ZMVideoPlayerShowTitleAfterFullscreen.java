package cc.zimo.videoplugs.videoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.jzvd.JZVideoPlayerStandard;

public class ZMVideoPlayerShowTitleAfterFullscreen extends JZVideoPlayerStandard {
    public ZMVideoPlayerShowTitleAfterFullscreen(Context context) {
        super(context);
    }

    public ZMVideoPlayerShowTitleAfterFullscreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setUp(String url, int screen, Object... objects) {
        super.setUp(url, screen, objects);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            titleTextView.setVisibility(View.VISIBLE);
        } else {
            titleTextView.setVisibility(View.INVISIBLE);
        }
    }
}
