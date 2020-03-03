package com.mi.mistatistic.sdk.data;

import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.miui.tsmclient.net.TSMAuthContants;
import org.json.JSONObject;

public class RNLoadActivityEvent extends CustomDataEvent {
    private String c;

    public String a() {
        return RemoteDataUploadManager.n;
    }

    public RNLoadActivityEvent() {
    }

    public RNLoadActivityEvent(String str) {
        this.c = str;
    }

    public String g() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TSMAuthContants.PARAM_SESSION_ID, this.f7357a);
            jSONObject.put("timestamp", this.b);
            jSONObject.put("data", this.c);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
