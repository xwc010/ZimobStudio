package cc.yujie.libs.constant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by xwc on 2018/1/11.
 */

public class FeedGson {

    public static Gson getGson(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(EFeedAction.class,
                new EFeedActionJsonSerializer());
        gsonBuilder.registerTypeAdapter(EFeedFlag.class,
                new EFeedFlagJsonSerializer());
        gsonBuilder.registerTypeAdapter(EFeedType.class,
                new EFeedTypeJsonSerializer());
        Gson gson = gsonBuilder.create();

        return gson;

    }
}
