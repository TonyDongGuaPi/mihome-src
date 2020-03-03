package com.xiaomi.smarthome.miniprogram.model;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.miio.Miio;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class SupportWechatAppInfos {

    /* renamed from: a  reason: collision with root package name */
    public List<DetailInfo> f20052a = new ArrayList();

    public static class DetailInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f20053a;
        public int b;
        public boolean c;

        public String toString() {
            return "Item{model='" + this.f20053a + Operators.SINGLE_QUOTE + ", pdid='" + this.b + Operators.SINGLE_QUOTE + ", supportWechatApp='" + this.c + Operators.BLOCK_END;
        }
    }

    public static SupportWechatAppInfos a(JSONObject jSONObject) {
        SupportWechatAppInfos supportWechatAppInfos = new SupportWechatAppInfos();
        if (jSONObject == null) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("items");
        if (optJSONArray != null) {
            Miio.b("SupportWechatAppInfos", "SupportWechatAppInfos:" + supportWechatAppInfos.toString());
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                DetailInfo detailInfo = new DetailInfo();
                detailInfo.f20053a = optJSONObject.optString("model");
                detailInfo.b = optJSONObject.optInt("pdid");
                detailInfo.c = optJSONObject.optBoolean("supportWechatApp");
                supportWechatAppInfos.f20052a.add(detailInfo);
            }
        } else {
            Miio.b("SupportWechatAppInfos", "SupportWechatAppInfos is null");
        }
        return supportWechatAppInfos;
    }
}
