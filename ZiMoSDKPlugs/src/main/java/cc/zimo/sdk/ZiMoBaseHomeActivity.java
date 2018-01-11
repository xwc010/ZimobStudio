package cc.zimo.sdk;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;

public class ZiMoBaseHomeActivity extends ZiMoBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean canSlideDismiss() {
        return false;
    }

    protected AlertDialog createExitAdDialog(){
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            createExitAdDialog().show();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
