package com.xiaomi.clientreport.data;

import com.miui.tsmclient.util.Constants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.bl;
import com.xiaomi.push.l;
import org.json.JSONException;
import org.json.JSONObject;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private String f10079a = bl.a();
    private String b = l.d();
    private String c;
    private String d;
    public int e;
    public String f;
    public int g;

    public void a(String str) {
        this.c = str;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("production", this.e);
            jSONObject.put("reportType", this.g);
            jSONObject.put("clientInterfaceId", this.f);
            jSONObject.put("os", this.f10079a);
            jSONObject.put("miuiVersion", this.b);
            jSONObject.put(Constants.KEY_PACKAGE_NAME, this.c);
            jSONObject.put("sdkVersion", this.d);
            return jSONObject;
        } catch (JSONException e2) {
            b.a((Throwable) e2);
            return null;
        }
    }

    public void b(String str) {
        this.d = str;
    }

    public String c() {
        JSONObject b2 = b();
        return b2 == null ? "" : b2.toString();
    }

    public String d() {
        return this.c;
    }
}
