package cc.zimo.videoplugs.videoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.jzvd.JZVideoPlayerStandard;

public class ZMVideoPlayerStandardShowTitleAfterFullscreen extends JZVideoPlayerStandard {
    public ZMVideoPlayerStandardShowTitleAfterFullscreen(Context context) {
        super(context);
    }

    public ZMVideoPlayerStandardShowTitleAfterFullscreen(Context context, AttributeSet attrs) {
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
