package cc.yujie.sfilm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.yujie.basicplugs.YuJieBaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends YuJieBaseFragment {

    public HomeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
