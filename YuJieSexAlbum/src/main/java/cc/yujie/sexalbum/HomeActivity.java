package cc.yujie.sexalbum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

import cc.yujie.basicplugs.YuJieBaseHomeActivity;
import cc.yujie.basicplugs.YuJieBaseWebActivity;

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
    }
}
