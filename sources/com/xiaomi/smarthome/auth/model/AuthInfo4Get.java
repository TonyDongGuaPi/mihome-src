package com.xiaomi.smarthome.auth.model;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AuthInfo4Get {

    /* renamed from: a  reason: collision with root package name */
    public List<AuthDetail> f13921a = new ArrayList();

    public static class AuthDetail {

        /* renamed from: a  reason: collision with root package name */
        public int f13922a;
        public String b;
        public String c;
        public long d;

        public String toString() {
            return "auth_list{scope='" + this.f13922a + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public static AuthInfo4Get a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        AuthInfo4Get authInfo4Get = new AuthInfo4Get();
        if (!(jSONObject == null || (optJSONArray = jSONObject.optJSONArray("auth_list")) == null)) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                AuthDetail authDetail = new AuthDetail();
                authDetail.f13922a = optJSONObject.optInt("scope");
                authDetail.b = optJSONObject.optString("did");
                authDetail.c = optJSONObject.optString("access_token");
                authDetail.d = optJSONObject.optLong("expire_at");
                authInfo4Get.f13921a.add(authDetail);
            }
        }
        return authInfo4Get;
    }
}
