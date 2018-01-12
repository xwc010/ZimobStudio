package cc.zimo.adplugs.model;

/**
 * @author admin
 * @date 2016/5/9
 */
public class AdData {
    /**
     * 广告类型
     * 例如：banner/interstitial/video/native
     */
    public String type;

    /**
     * 广告名称
     * 例如：admob/facebook/vungle/unity/self
     */
    public String name;

    public String adId;//广告id
    public Integer weight;//相同广告类型下的权重

    public AdData(String name, String type) {
        this.type = type;
        this.name = name;
    }
}
