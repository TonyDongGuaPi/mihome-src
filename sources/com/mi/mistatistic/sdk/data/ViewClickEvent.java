package com.mi.mistatistic.sdk.data;

import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.miui.tsmclient.net.TSMAuthContants;
import org.json.JSONObject;

public class ViewClickEvent extends CustomDataEvent {
    private String c;
    private long d;
    private String e;
    private String f;
    private String g;

    public String a() {
        return RemoteDataUploadManager.h;
    }

    public ViewClickEvent() {
    }

    public ViewClickEvent(String str, String str2, String str3) {
        this.e = str;
        this.f = str3;
        this.g = str2;
    }

    public String g() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String h() {
        return this.f;
    }

    public void e(String str) {
        this.f = str;
    }

    public String i() {
        return this.g;
    }

    public void f(String str) {
        this.g = str;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public long d() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TSMAuthContants.PARAM_SESSION_ID, this.c);
            jSONObject.put("timestamp", this.d);
            jSONObject.put("viewId", this.e);
            jSONObject.put("pageId", this.g);
            jSONObject.put("label", this.f);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
