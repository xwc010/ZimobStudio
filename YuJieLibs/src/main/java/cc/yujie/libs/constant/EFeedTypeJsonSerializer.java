package cc.yujie.libs.constant;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by xwc on 2018/1/11.
 */

public class EFeedTypeJsonSerializer implements JsonSerializer<EFeedType>,
        JsonDeserializer<EFeedType> {

    // 对象转为Json时调用,实现JsonSerializer<EFeedType>接口
    @Override
    public JsonElement serialize(EFeedType state, Type arg1,
                                 JsonSerializationContext arg2) {
        return new JsonPrimitive(state.ordinal());
    }

    // json转为对象时调用,实现JsonDeserializer<EFeedType>接口
    @Override
    public EFeedType deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
        if (json.getAsInt() < EFeedType.values().length)
            return EFeedType.values()[json.getAsInt()];
        return null;
    }

}
