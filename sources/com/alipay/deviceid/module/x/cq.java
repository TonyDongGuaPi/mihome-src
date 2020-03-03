package com.alipay.deviceid.module.x;

import android.content.Context;
import com.alipay.zoloz.toyger.ToygerService;
import com.payu.custombrowser.util.CBConstant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cq extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f918a = {"appver", "ua", "user", "appname", "sdkver", CBConstant.SDK_DETAILS, ToygerService.KEY_PUBLIC_KEY, "appkey"};
    public Map<String, Object> b;
    public ch c;

    public cq(Context context, String str, String str2, String str3, String str4, String str5) {
        this();
        this.b.put("appver", str);
        this.b.put("user", str2);
        this.b.put("appname", str3);
        this.b.put("sdkver", "1.0.7.20160517");
        this.b.put(CBConstant.SDK_DETAILS, "APRdsSdk");
        j.a();
        this.b.put(ToygerService.KEY_PUBLIC_KEY, h.a(j.a(context, str5)));
        this.b.put("appkey", e.a(str4) ? "" : str4);
    }

    private cq() {
        this.b = new HashMap();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        for (String str : f918a) {
            Object obj = this.b.get(str);
            if (obj != null && (obj instanceof String)) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException unused) {
                }
            } else if (obj != null && (obj instanceof ch)) {
                jSONObject.put(str, ((ch) obj).a());
            }
        }
        return jSONObject;
    }
}
