package cc.yujie.libs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;

import cc.yujie.libs.model.Feed;
import cc.zimo.sdk.ZMBaseActivity;

/**
 * 多图大图浏览
 */
public class YJLAblumActivity extends ZMBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libs_ablum_detail);
    }

    @Override
    public void initUI() {
        super.initUI();
        Feed feed = (Feed) getIntent().getSerializableExtra("feed");
    }

    @Override
    public void updateStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.color_libs_black), 20);
    }

    public static void showAblum(Context context, Feed feed) {
        Intent intent = new Intent(context, YJLAblumActivity.class);
        intent.putExtra("feed", feed);

        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        context.startActivity(intent);
    }

    @Override
    public void doBackgroundToForeground() {
        super.doBackgroundToForeground();
    }
}
