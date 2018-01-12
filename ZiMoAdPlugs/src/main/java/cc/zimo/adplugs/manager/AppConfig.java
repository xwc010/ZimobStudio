package cc.zimo.adplugs.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.zimo.adplugs.model.AdType;
import cc.zimo.adplugs.model.Condition;
import cc.zimo.adplugs.utils.AdLog;

/**
 * Created by xwc on 2018/1/12.
 */

public class AppConfig {
    private static AppConfig instance = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return instance;
    }

    public List<Condition> weightDatas = new ArrayList<>();
    public Map<String, String> adGapMap = new HashMap<>();//广告控制打开间隔
    /**
     * 广告有效时间
     * 超过时间代表广告失效，默认为一小时
     */
    public Map<String, String> adTimeoutMap = new HashMap<>();
    /**
     * 0代表小banner，1代表大banner
     */
    public int banner_style = 1;


    /**
     * 根据广告类型获取刷新间隔
     * @param type
     * @return
     */
    public int getGapByType(String type) {
        int gap = 0;
        if (adGapMap != null && adGapMap.size() > 0) {
            if (adGapMap.containsKey(type)) {
                gap = Integer.parseInt(adGapMap.get(type));
            } else if (adGapMap.containsKey("default")) {
                gap = Integer.parseInt(adGapMap.get("default"));
            }
        }
        //不得小于1
        if (gap < 1) {
            gap = 1;
        }
        //banner最小为30秒
        if (AdType.TYPE_BANNER.equals(type)) {
            if (gap < 30) {
                gap = 30;
            }
        }
        AdLog.d("[AdType]=" + type + " [gap]=" + gap);
        return gap;
    }

}
