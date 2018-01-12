package cc.zimo.adplugs.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.zimo.adplugs.model.AdData;
import cc.zimo.adplugs.model.Condition;

/**
 * Created by xwc on 2018/1/12.
 */

public class AdStrategy {
    private static AdStrategy instance = new AdStrategy();

    private AdStrategy() {
    }

    public static AdStrategy getInstance() {
        return instance;
    }

    //根据广告类型保存所有weight大于零的广告策略数据,用作广告的初始化
    public Map<String, List<AdData>> adDateMap = new HashMap<>();
    private Map<String, List<Condition>> adConditionMap = new HashMap<>();

    public AdData getAdData(String adType) {
        return null;
    }

    public List<AdData> getItem(String adType) {

        List<AdData> adDataList = new ArrayList<>();
        List<Condition> conditionList;

        //根据权重对weight排序
        Collections.sort(adDataList, new Comparator<AdData>() {
            @Override
            public int compare(AdData lhs, AdData rhs) {
                return rhs.weight.compareTo(lhs.weight);
            }
        });
        return adDataList;
    }
}
