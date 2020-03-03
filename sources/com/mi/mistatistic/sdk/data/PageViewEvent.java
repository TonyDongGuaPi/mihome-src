package com.mi.mistatistic.sdk.data;

import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.miui.tsmclient.net.TSMAuthContants;
import org.json.JSONObject;

public class PageViewEvent extends CustomDataEvent {
    private String c;
    private long d;
    private long e;
    private long f;
    private String g;
    private String h;

    public String a() {
        return RemoteDataUploadManager.g;
    }

    public PageViewEvent() {
    }

    public PageViewEvent(String str, long j, long j2, long j3, String str2, String str3) {
        this.c = str;
        this.d = j;
        this.e = j2;
        this.f = j3;
        this.g = str2;
        this.h = str3;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public long g() {
        return this.e;
    }

    public void b(long j) {
        this.e = j;
    }

    public long h() {
        return this.f;
    }

    public void c(long j) {
        this.f = j;
    }

    public String i() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    public String j() {
        return this.h;
    }

    public void e(String str) {
        this.h = str;
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
            jSONObject.put("startTime", this.e);
            jSONObject.put("endTime", this.f);
            jSONObject.put("pageId", this.g);
            jSONObject.put("pageRef", this.h);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
