package com.xiaomi.smarthome.miniprogram.model;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.miio.Miio;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class TokenCountInfo {

    /* renamed from: a  reason: collision with root package name */
    public List<DetailInfo> f20054a = new ArrayList();

    public static class DetailInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f20055a;
        public int b;

        public String toString() {
            return "Item{did='" + this.f20055a + Operators.SINGLE_QUOTE + ", times='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public static TokenCountInfo a(JSONObject jSONObject) {
        TokenCountInfo tokenCountInfo = new TokenCountInfo();
        if (jSONObject == null) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("result");
        if (optJSONArray != null) {
            Miio.b("TokenCountInfo", "TokenCountInfo:" + tokenCountInfo.toString());
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                DetailInfo detailInfo = new DetailInfo();
                detailInfo.f20055a = optJSONObject.optString("did");
                detailInfo.b = optJSONObject.optInt("times");
                tokenCountInfo.f20054a.add(detailInfo);
            }
        } else {
            Miio.b("TokenCountInfo", "TokenCountInfo is null");
        }
        return tokenCountInfo;
    }
}
