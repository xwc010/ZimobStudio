package cc.yujie.sexalbum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;

import cc.yujie.basicplugs.YuJieBaseHomeActivity;
import cc.yujie.basicplugs.YuJieBaseWebActivity;
import cc.yujie.dataplugs.http.CallBack;
import cc.yujie.dataplugs.http.YuHttpClient;

public class HomeActivity extends YuJieBaseHomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, YuJieBaseWebActivity.class);
                startActivity(intent);
            }
        });

        Log.i("HomeActivity", "doGet -------");
        YuHttpClient.getInstance().doGet("dd", "http://zimob.cc/mutil/app/cc.yujie.sexalbum/tab.json", new CallBack() {
            @Override
            public void onSuccess(String reqTag, int resultCode, String response) {
                Log.i("HomeActivity", resultCode + response);
            }

            @Override
            public void onError(String reqTag, Throwable erro) {
                Log.i("HomeActivity", "onError");
                erro.printStackTrace();
            }
        });
    }
}
