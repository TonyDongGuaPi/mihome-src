package com.xiaomi.smarthome.operation.js_sdk.network;

import android.util.Log;
import com.xiaomi.smarthome.framework.log.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class NetState {

    /* renamed from: a  reason: collision with root package name */
    public static final NetState f21095a = new NetState(false, "get network type failed") {
        public JSONObject c() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", "error");
                jSONObject.put("msg", this.c);
            } catch (JSONException e) {
                LogUtil.a(NetState.d, "toJson: " + Log.getStackTraceString(e));
            }
            LogUtil.a(NetState.d, "toJson: networkAvailable: " + this.b + " ;networkType: " + this.c);
            return jSONObject;
        }
    };
    private static final String d = "NetState";
    protected boolean b;
    protected String c;

    public NetState(boolean z, String str) {
        this.b = z;
        this.c = str;
    }

    public boolean a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", "ok");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("networkAvailable", this.b);
            jSONObject2.put("networkType", this.c);
            jSONObject.put("data", jSONObject2);
        } catch (Exception e) {
            LogUtil.a(d, "toJson: " + Log.getStackTraceString(e));
        }
        LogUtil.a(d, "toJson: networkAvailable: " + this.b + " ;networkType: " + this.c);
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NetState netState = (NetState) obj;
        if (this.b != netState.b) {
            return false;
        }
        if (this.c != null) {
            return this.c.equals(netState.c);
        }
        if (netState.c == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.b ? 1 : 0) * true) + (this.c != null ? this.c.hashCode() : 0);
    }
}
