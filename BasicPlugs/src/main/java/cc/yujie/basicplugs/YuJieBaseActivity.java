package cc.yujie.basicplugs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrListener;
import com.r0adkll.slidr.model.SlidrPosition;

/**
 * Created by xwc on 2017/12/11.
 */

public class YuJieBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInit();
        if (canSlideDismiss()) {
            SlidrConfig config = new SlidrConfig.Builder()
//                    .primaryColor(getResources().getColor(R.color.primary)
//                    .secondaryColor(getResources().getColor(R.color.secondary)
                     .position(SlidrPosition.LEFT) // LEFT|RIGHT|TOP|BOTTOM|VERTICAL|HORIZONTAL
                     .sensitivity(1f)
                     .scrimColor(Color.BLACK)
                     .scrimStartAlpha(0.8f)
                     .scrimEndAlpha(0f)
                     .velocityThreshold(2400)
                     .distanceThreshold(0.25f)
                     .edge(true) // true | false
                     .edgeSize(0.18f) // The % of the screen that counts as the edge, default 18%
//                     .listener(new SlidrListener(){...})
                     .build();

            Slidr.attach(this, config);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        updateStatusBar();
    }

    public boolean canSlideDismiss() {
        return true;
    }

    public void onInit() {

    }

    public void updateStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.basePrimary), 20); // StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatusBar();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
