package com.xiaomi.smarthome.scenenew.model;

import com.google.gson.JsonObject;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.framework.openapi.ApiConst;

public class GoodInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f21983a;
    public float b;
    public String c;
    public String d;
    public String e;

    public static GoodInfo a(JsonObject jsonObject) {
        GoodInfo goodInfo = new GoodInfo();
        if (!jsonObject.get("data").isJsonObject()) {
            return null;
        }
        JsonObject asJsonObject = jsonObject.getAsJsonObject("data");
        if (!asJsonObject.has("good")) {
            return null;
        }
        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("good");
        goodInfo.e = asJsonObject2.get(ApiConst.j).getAsString();
        goodInfo.f21983a = asJsonObject2.get("name").getAsString();
        goodInfo.b = asJsonObject2.get("price_min").getAsFloat();
        goodInfo.d = asJsonObject2.get("pic_url").getAsString();
        goodInfo.c = "https://home.mi.com/detail?gid=" + goodInfo.e;
        return goodInfo;
    }

    public String toString() {
        return "GoodInfo{goodName='" + this.f21983a + Operators.SINGLE_QUOTE + ", goodPrice=" + this.b + ", goodJumpUrl='" + this.c + Operators.SINGLE_QUOTE + ", goodImg='" + this.d + Operators.SINGLE_QUOTE + ", gid='" + this.e + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
