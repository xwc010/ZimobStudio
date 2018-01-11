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

public class EDesplayModeJsonSerializer implements JsonSerializer<EDesplayMode>,
        JsonDeserializer<EDesplayMode> {

    // 对象转为Json时调用,实现JsonSerializer<EDesplayMode>接口
    @Override
    public JsonElement serialize(EDesplayMode state, Type arg1,
                                 JsonSerializationContext arg2) {
        return new JsonPrimitive(state.ordinal());
    }

    // json转为对象时调用,实现JsonDeserializer<EDesplayMode>接口
    @Override
    public EDesplayMode deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
        if (json.getAsInt() < EDesplayMode.values().length)
            return EDesplayMode.values()[json.getAsInt()];
        return null;
    }

}
