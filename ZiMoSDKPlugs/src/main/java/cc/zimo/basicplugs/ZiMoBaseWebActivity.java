package cc.zimo.basicplugs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.just.agentweb.DefaultWebClient;
import com.orhanobut.logger.Logger;

import cc.zimo.basicplugs.widget.WebLayout;

public class ZiMoBaseWebActivity extends ZiMoBaseActivity {

    protected AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;
    private Toolbar mToolbar;
    private TextView mTitleTextView;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cc.zimo.basicplugs.R.layout.activity_base_web);

        mLinearLayout = (LinearLayout) this.findViewById(cc.zimo.basicplugs.R.id.container);
        mToolbar = (Toolbar) this.findViewById(cc.zimo.basicplugs.R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        mTitleTextView = (TextView) this.findViewById(cc.zimo.basicplugs.R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        long p = System.currentTimeMillis();

        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(cc.zimo.basicplugs.R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .setWebLayout(new WebLayout(this))
                .openParallelDownload()//打开并行下载 , 默认串行下载
                .setNotifyIcon(cc.zimo.basicplugs.R.mipmap.download) //下载图标
                .setOpenOtherAppWays(DefaultWebClient.OpenOtherAppWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownScheme() //拦截找不到相关页面的Scheme
                .createAgentWeb()//
                .ready()
                .go(getUrl());

        //mAgentWeb.getLoader().loadUrl(getUrl());

        long n = System.currentTimeMillis();
        Logger.d("BaseWebActivity init used time:" + (n - p));
    }

    @Override
    public boolean canSlideDismiss() {
        return true;
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Logger.d("BaseWebActivity onPageStarted");
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
//            Log.i("Info","progress:"+newProgress);
        }
    };

    public String getUrl() {
        return "http://zimob.cc/";
    }

    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (mTitleTextView != null)
                mTitleTextView.setText(title);
        }
    };


    private void showDialog() {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(this)
                    .setMessage("您确定要关闭该页面吗?")
                    .setNegativeButton("再逛逛", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null) mAlertDialog.dismiss();
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null) mAlertDialog.dismiss();
                            finish();
                        }
                    }).create();
        }
        mAlertDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.d("BaseWebActivity onActivityResult requestCode:" + requestCode + " resultCode:" + resultCode);
        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    public static void GO(Activity context){
        Intent intent = new Intent(context, ZiMoBaseWebActivity.class);
        context.startActivity(intent);
    }
}
