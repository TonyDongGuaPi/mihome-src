package com.xiaomi.smarthome.shop.mishop;

import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.Parser;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.shop.mishop.pojo.GidMap;
import com.xiaomi.smarthome.shop.mishop.pojo.PidMap;
import com.xiaomi.smarthome.shop.mishop.pojo.ProductIdMap;
import java.lang.reflect.Type;
import java.util.Map;

public class MiShopConfigApi {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final Type f22174a = new TypeToken<GidMap>() {
    }.getType();
    /* access modifiers changed from: private */
    public static final Type b = new TypeToken<PidMap>() {
    }.getType();
    /* access modifiers changed from: private */
    public static final JsonDeserializer<GidMap> c = new JsonDeserializer<GidMap>() {
        /* renamed from: a */
        public GidMap deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            int i;
            GidMap gidMap = new GidMap();
            if (jsonElement.isJsonObject()) {
                SparseArray sparseArray = new SparseArray();
                for (Map.Entry next : jsonElement.getAsJsonObject().entrySet()) {
                    String str = (String) next.getKey();
                    JsonElement jsonElement2 = (JsonElement) next.getValue();
                    if (!TextUtils.isEmpty(str) && jsonElement2 != null) {
                        try {
                            i = Integer.parseInt(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                            i = -1;
                        }
                        if (i != -1) {
                            String asString = jsonElement2.getAsString();
                            if (!TextUtils.isEmpty(asString)) {
                                sparseArray.put(i, asString);
                            }
                        }
                    }
                }
                gidMap.setGidMap(sparseArray);
            }
            return gidMap;
        }
    };
    /* access modifiers changed from: private */
    public static final JsonDeserializer<PidMap> d = new JsonDeserializer<PidMap>() {
        /* renamed from: a */
        public PidMap deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            int i;
            PidMap pidMap = new PidMap();
            if (jsonElement.isJsonObject()) {
                SparseArray sparseArray = new SparseArray();
                for (Map.Entry next : jsonElement.getAsJsonObject().entrySet()) {
                    String str = (String) next.getKey();
                    JsonElement jsonElement2 = (JsonElement) next.getValue();
                    if (!TextUtils.isEmpty(str) && jsonElement2 != null) {
                        try {
                            i = Integer.parseInt(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                            i = -1;
                        }
                        if (i != -1) {
                            String asString = jsonElement2.getAsString();
                            if (!TextUtils.isEmpty(asString)) {
                                sparseArray.put(i, asString);
                            }
                        }
                    }
                }
                pidMap.setPidMap(sparseArray);
            }
            return pidMap;
        }
    };

    public static void a(Callback<ProductIdMap> callback) {
        if (XmPluginHostApi.instance() != null) {
            XmPluginHostApi.instance().sendRawMijiaShopRequest("GET", "/shop/productidmap", "", callback, new Parser<ProductIdMap>() {
                /* renamed from: a */
                public ProductIdMap parse(JsonElement jsonElement) {
                    try {
                        return (ProductIdMap) new GsonBuilder().registerTypeAdapter(MiShopConfigApi.f22174a, MiShopConfigApi.c).registerTypeAdapter(MiShopConfigApi.b, MiShopConfigApi.d).create().fromJson(jsonElement, ProductIdMap.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }, true);
        } else if (callback != null) {
            callback.onFailure(ErrorCode.INVALID.getCode(), "XmPluginHostApi.instance not initial");
        }
    }
}
