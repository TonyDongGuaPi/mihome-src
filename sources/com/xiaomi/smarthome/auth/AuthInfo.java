package com.xiaomi.smarthome.auth;

import com.facebook.internal.ServerProtocol;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthInfo {

    /* renamed from: a  reason: collision with root package name */
    String f13800a;
    String b;
    String c;
    String d;
    String e;
    List<AuthDetail> f = new ArrayList();
    boolean g;
    long h;

    public static class AuthDetail {

        /* renamed from: a  reason: collision with root package name */
        int f13801a;
        List<String> b;

        public String toString() {
            return "AuthDetail{authType='" + this.f13801a + Operators.SINGLE_QUOTE + ", authValues=" + this.b + Operators.BLOCK_END;
        }
    }

    public static AuthInfo a(JSONObject jSONObject) {
        AuthInfo authInfo = new AuthInfo();
        if (jSONObject != null) {
            authInfo.f13800a = jSONObject.optString("app_title", "");
            authInfo.b = jSONObject.optString("app_intro", "");
            authInfo.c = jSONObject.optString("app_icon", "");
            authInfo.d = jSONObject.optString("app_public_key", "");
            authInfo.g = jSONObject.optBoolean("default_enable", false);
            authInfo.h = jSONObject.optLong("default_expire");
            authInfo.e = jSONObject.optString("app_button_info", SHApplication.getAppContext().getResources().getString(R.string.auth_check_config));
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray(Tags.MiPhoneDetails.DETAILS);
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        AuthDetail authDetail = new AuthDetail();
                        authDetail.f13801a = optJSONObject.optInt(ServerProtocol.DIALOG_PARAM_AUTH_TYPE);
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("auth_value");
                        if (optJSONArray2 != null) {
                            authDetail.b = new ArrayList();
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                authDetail.b.add(optJSONArray2.get(i2).toString());
                            }
                        }
                        authInfo.f.add(authDetail);
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return authInfo;
    }

    public String toString() {
        return "AuthInfo{appName='" + this.f13800a + Operators.SINGLE_QUOTE + ", appDesc='" + this.b + Operators.SINGLE_QUOTE + ", appIcon='" + this.c + Operators.SINGLE_QUOTE + ", appPublicKey='" + this.d + Operators.SINGLE_QUOTE + ", authDetails=" + this.f + ", defaultEnable=" + this.g + ", defaultExpire=" + this.h + Operators.BLOCK_END;
    }

    public String a() {
        return this.f13800a;
    }
}
