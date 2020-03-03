package com.tencent.a.a.a.a;

import android.util.Log;
import com.alipay.mobile.security.bio.api.BioDetector;
import com.mobikwik.sdk.lib.Constants;
import com.paytm.pgsdk.PaytmConstants;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    String f8974a = null;
    String b = null;
    String c = "0";
    long d = 0;

    static c a(String str) {
        c cVar = new c();
        if (h.a(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull(BioDetector.EXT_KEY_UI)) {
                    cVar.f8974a = jSONObject.getString(BioDetector.EXT_KEY_UI);
                }
                if (!jSONObject.isNull("mc")) {
                    cVar.b = jSONObject.getString("mc");
                }
                if (!jSONObject.isNull(Constants.MID)) {
                    cVar.c = jSONObject.getString(Constants.MID);
                }
                if (!jSONObject.isNull("ts")) {
                    cVar.d = jSONObject.getLong("ts");
                }
            } catch (JSONException e) {
                Log.w(PaytmConstants.f8536a, e);
            }
        }
        return cVar;
    }

    private JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            h.a(jSONObject, BioDetector.EXT_KEY_UI, this.f8974a);
            h.a(jSONObject, "mc", this.b);
            h.a(jSONObject, Constants.MID, this.c);
            jSONObject.put("ts", this.d);
        } catch (JSONException e) {
            Log.w(PaytmConstants.f8536a, e);
        }
        return jSONObject;
    }

    public final String a() {
        return this.c;
    }

    public final String toString() {
        return b().toString();
    }
}
