package cc.zimo.adplugs.manager;

import java.util.HashMap;
import java.util.Map;

import cc.zimo.adplugs.model.AdType;
import cc.zimo.adplugs.utils.AdLog;
import cc.zimo.adplugs.utils.Constant;

/**
 * @author yanli
 * @date 2017/11/8
 */

public class BaseManager {

    protected Map<String, Map<String, Integer>> showNumsMap = new HashMap<>();
    protected int gap = -1;
    private Map<String, Integer> levelMap;
    public static long lastShowTime = 0;

    /**
     * 是否受gap控制
     *
     * @param page
     * @return
     */
    public boolean hasGapCtrl(String adType, String page) {
        //判断上一次打开时间与当前时间差
        gap = AppConfig.getInstance().getGapByType(AdType.TYPE_INTERSTITIAL) * 1000;
        if (lastShowTime == 0) {
            lastShowTime = Constant.appStartTime;
        }
        long diff = System.currentTimeMillis() - lastShowTime;
        AdLog.d("gap of " + page + " = " + gap / 1000 + "  current Intervals =  " + diff / 1000);
        if (diff > 0 && diff < gap) {
            return true;
        }
        Map levelMap = new HashMap();
        levelMap.put(page, 1);
        showNumsMap.put(adType, levelMap);
        return false;
    }
}
