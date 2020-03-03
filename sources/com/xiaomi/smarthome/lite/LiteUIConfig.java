package com.xiaomi.smarthome.lite;

import android.text.TextUtils;
import com.miui.tsmclient.net.TSMAuthContants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiteUIConfig {

    /* renamed from: a  reason: collision with root package name */
    public String f19366a;
    public String b;
    public ArrayList<LiteComConfig> c;

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f19366a = jSONObject.optString("model");
                this.b = jSONObject.optString(TSMAuthContants.PARAM_LANGUAGE);
                JSONArray optJSONArray = jSONObject.optJSONArray("config");
                if (optJSONArray != null) {
                    this.c = new ArrayList<>();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        String optString = optJSONArray.optString(i);
                        LiteComConfig liteComConfig = new LiteComConfig();
                        liteComConfig.a(optString);
                        this.c.add(liteComConfig);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }
}
