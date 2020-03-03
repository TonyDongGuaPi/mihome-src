package com.alipay.sdk.authjs;

import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

class f extends TimerTask {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f1100a;
    final /* synthetic */ d b;

    f(d dVar, a aVar) {
        this.b = dVar;
        this.f1100a = aVar;
    }

    public void run() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("toastCallBack", "true");
        } catch (JSONException unused) {
        }
        a aVar = new a("callback");
        aVar.a(this.f1100a.b());
        aVar.a(jSONObject);
        this.b.f1098a.a(aVar);
    }
}
