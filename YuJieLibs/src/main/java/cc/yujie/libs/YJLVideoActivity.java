package cc.yujie.libs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jaeger.library.StatusBarUtil;

import cc.yujie.libs.model.Feed;
import cc.yujie.libs.utils.YJLUrls;
import cc.zimo.dataplugs.log.ZMLog;
import cc.zimo.imageplugs.ZMImgLoader;
import cc.zimo.sdk.ZMBaseActivity;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZUserActionStandard;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class YJLVideoActivity extends ZMBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libs_yujie_video);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    public static void startVideo(Context context, Feed feed) {
        Intent intent = new Intent(context, YJLVideoActivity.class);
        intent.putExtra("feed", feed);

        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        context.startActivity(intent);
    }

    @Override
    public void initUI() {
        super.initUI();
        Feed feed = (Feed) getIntent().getSerializableExtra("feed");
        JZVideoPlayerStandard videoPlayer = (JZVideoPlayerStandard) findViewById(R.id.video_libs_paly);

        ZMLog.d("YuJieVideo URL: " + YJLUrls.getVideoUrl(feed.getLink()));
        videoPlayer.setUp(YJLUrls.getVideoUrl("2222.mp4")
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, feed.getTitle());

        ZMImgLoader.getInstance().loadImg(YJLUrls.getThumbnailUrl(feed.getId() + "/" + feed.getThumbnail()), videoPlayer.thumbImageView);
//        videoPlayer.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
//                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "闭眼睛");
//        ZiMoImgLoader.getInstance().loadImg("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640", videoPlayer.thumbImageView);

//        videoPlayer.setJzUserAction(createUserAction());
    }

    @Override
    public void updateStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.color_libs_black), 20);
    }

    @Override
    public void doBackgroundToForeground() {
        super.doBackgroundToForeground();
    }

    private JZUserAction createUserAction(){
        return new JZUserAction() {
            @Override
            public void onEvent(int type, Object url, int screen, Object... objects) {
                switch (type) {
                    case JZUserAction.ON_CLICK_START_ICON:
                        Log.i("USER_EVENT", "ON_CLICK_START_ICON" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_CLICK_START_ERROR:
                        Log.i("USER_EVENT", "ON_CLICK_START_ERROR" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_CLICK_START_AUTO_COMPLETE:
                        Log.i("USER_EVENT", "ON_CLICK_START_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_CLICK_PAUSE:
                        Log.i("USER_EVENT", "ON_CLICK_PAUSE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_CLICK_RESUME:
                        Log.i("USER_EVENT", "ON_CLICK_RESUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_SEEK_POSITION:
                        Log.i("USER_EVENT", "ON_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_AUTO_COMPLETE:
                        Log.i("USER_EVENT", "ON_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_ENTER_FULLSCREEN:
                        Log.i("USER_EVENT", "ON_ENTER_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_QUIT_FULLSCREEN:
                        Log.i("USER_EVENT", "ON_QUIT_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_ENTER_TINYSCREEN:
                        Log.i("USER_EVENT", "ON_ENTER_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_QUIT_TINYSCREEN:
                        Log.i("USER_EVENT", "ON_QUIT_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_TOUCH_SCREEN_SEEK_VOLUME:
                        Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_VOLUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserAction.ON_TOUCH_SCREEN_SEEK_POSITION:
                        Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;

                    case JZUserActionStandard.ON_CLICK_START_THUMB:
                        Log.i("USER_EVENT", "ON_CLICK_START_THUMB" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    case JZUserActionStandard.ON_CLICK_BLANK:
                        Log.i("USER_EVENT", "ON_CLICK_BLANK" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                        break;
                    default:
                        Log.i("USER_EVENT", "unknow");
                        break;
                }
            }
        };
    }
}
