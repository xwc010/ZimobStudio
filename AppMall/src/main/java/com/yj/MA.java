package com.yj;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yj.see.R;
import com.yj.u.h;

import net.youmi.android.nm.sp.SpotManager;

import cn.jzvd.JZVideoPlayer;

public class MA extends AppCompatActivity {

    private IF IF;
    private AF AF;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    showFragment(1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_m);

        Toolbar tbar_m = (Toolbar) findViewById(R.id.tbar_m);
        setSupportActionBar(tbar_m);
        tbar_m.setTitle(getAppName());
//        tbar_m.setMenu();

        tbar_m.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_favorite:
                        Intent intent = new Intent(MA.this, FA.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        IF = new IF();
        AF = new AF();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_body, IF);
        fragmentTransaction.add(R.id.frame_body, AF);
        fragmentTransaction.commit();
        showFragment(0);
        h.i(getApplicationContext());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void showFragment(int tag){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(tag == 0){
            fragmentTransaction.show(IF);
            fragmentTransaction.hide(AF);
        }else {
            fragmentTransaction.hide(IF);
            IF.releaseAllVideos();
            fragmentTransaction.show(AF);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }

        if(AF != null && AF.isShow){
            AF.closeInstertitial();
            return;
        }

//        super.onBackPressed();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    private String getAppName() {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        SpotManager.getInstance(getApplicationContext()).onAppExit();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return true;
    }

}
