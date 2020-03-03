package com.mi.mistatistic.sdk.data;

import com.mi.mistatistic.sdk.controller.Logger;
import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import org.json.JSONObject;

public class NewUserEvent extends CustomDataEvent {
    private EventData c;
    private String d;

    public String a() {
        return RemoteDataUploadManager.e;
    }

    public EventData g() {
        return this.c;
    }

    public void a(EventData eventData) {
        this.c = eventData;
    }

    public String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventId", this.d);
            jSONObject.put("data", h());
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public String h() {
        try {
            JSONObject jSONObject = new JSONObject();
            if (this.c != null) {
                jSONObject.put("key", this.c.a());
                jSONObject.put("value", this.c.b());
                jSONObject.put("type", this.c.c());
            }
            return jSONObject.toString();
        } catch (Exception e) {
            Logger.a("dataToJsonArrayString  Exception: ", (Throwable) e);
            return "";
        }
    }

    public String i() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }
}
