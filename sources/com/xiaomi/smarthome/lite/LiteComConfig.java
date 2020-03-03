package com.xiaomi.smarthome.lite;

import android.text.TextUtils;
import com.taobao.weex.bridge.WXBridgeManager;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthomedevice.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiteComConfig {

    /* renamed from: a  reason: collision with root package name */
    public String f19352a;
    public String b;
    public String c;
    public String d;
    public String e;
    public ArrayList<LiteOptConfig> f;
    public String g;
    public String h;

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f19352a = jSONObject.optString(WXBridgeManager.COMPONENT);
                this.b = jSONObject.optString("component_name");
                this.g = jSONObject.optString("icon_offline");
                String optString = jSONObject.optString("prop_name");
                if (TextUtils.isEmpty(optString)) {
                    this.c = null;
                } else if (!optString.contains(".")) {
                    this.c = "prop." + optString;
                } else {
                    this.c = optString;
                }
                this.d = jSONObject.optString("prop_desc");
                if (TextUtils.isEmpty(this.d)) {
                    this.d = CommonApplication.getAppContext().getString(R.string.lite_mode_device_desc_default);
                }
                this.e = jSONObject.optString("icon");
                this.h = jSONObject.optString("grid_idx");
                if (TextUtils.isEmpty(this.h)) {
                    this.h = "0";
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("operations");
                if (optJSONArray != null) {
                    this.f = new ArrayList<>(optJSONArray.length());
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        String optString2 = optJSONArray.optString(i);
                        LiteOptConfig liteOptConfig = new LiteOptConfig();
                        liteOptConfig.a(optString2);
                        this.f.add(liteOptConfig);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }
}
