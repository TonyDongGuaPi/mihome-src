package com.mi.mistatistic.sdk.data;

import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.miui.tsmclient.net.TSMAuthContants;
import org.json.JSONObject;

public class SessionEvent extends CustomDataEvent {
    private long c;
    private long d;
    private String e;
    private String f;
    private long g;

    public String a() {
        return RemoteDataUploadManager.f;
    }

    public boolean f() {
        return true;
    }

    public SessionEvent() {
    }

    public SessionEvent(long j, long j2, String str) {
        this.c = j;
        this.d = j2;
        this.e = str;
    }

    public String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("startTime", this.c);
            jSONObject.put("endTime", this.d);
            jSONObject.put(TSMAuthContants.PARAM_SESSION_ID, this.f);
            jSONObject.put("netWork", this.e);
            jSONObject.put("timestamp", this.g);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public long g() {
        return this.c;
    }

    public void b(long j) {
        this.c = j;
    }

    public String c() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public long h() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }

    public String i() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public long d() {
        return this.g;
    }

    public void a(long j) {
        this.g = j;
    }
}
