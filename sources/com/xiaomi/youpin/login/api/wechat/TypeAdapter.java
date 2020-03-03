package com.xiaomi.youpin.login.api.wechat;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.xiaomi.youpin.login.api.wechat.data.WechatAccount;
import java.lang.reflect.Type;

public class TypeAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static final Type f23507a = new TypeToken<WechatAccount>() {
    }.getType();
    public static final JsonDeserializer<WechatAccount> b = new JsonDeserializer<WechatAccount>() {
        /* renamed from: a */
        public WechatAccount deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (!jsonElement.isJsonPrimitive()) {
                return null;
            }
            try {
                return (WechatAccount) new Gson().fromJson(jsonElement.getAsString().replaceAll("\\\\", ""), WechatAccount.class);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    };
}
