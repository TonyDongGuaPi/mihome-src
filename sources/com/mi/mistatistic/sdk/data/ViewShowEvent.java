package com.mi.mistatistic.sdk.data;

import com.mi.mistatistic.sdk.StaticConstants;
import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.miui.tsmclient.net.TSMAuthContants;
import org.json.JSONObject;

public class ViewShowEvent extends CustomDataEvent {
    private String c;
    private long d;
    private long e = System.currentTimeMillis();
    private long f = StaticConstants.b;
    private long g;
    private String h;
    private String i;
    private String j;

    public String a() {
        return RemoteDataUploadManager.i;
    }

    public ViewShowEvent() {
    }

    public ViewShowEvent(String str, String str2, String str3) {
        this.h = str;
        this.i = str3;
        this.j = str2;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public long g() {
        return this.d;
    }

    public void b(long j2) {
        this.d = j2;
    }

    public long h() {
        return this.e;
    }

    public void c(long j2) {
        this.e = j2;
    }

    public long i() {
        return this.f;
    }

    public void d(long j2) {
        this.f = j2;
    }

    public String j() {
        return this.h;
    }

    public void d(String str) {
        this.h = str;
    }

    public String k() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public String l() {
        return this.j;
    }

    public void f(String str) {
        this.j = str;
    }

    public long d() {
        return this.g;
    }

    public void a(long j2) {
        this.g = j2;
    }

    public String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TSMAuthContants.PARAM_SESSION_ID, this.c);
            jSONObject.put("viewLeaveTime", this.c);
            jSONObject.put("viewLeaveTime", this.d);
            jSONObject.put("viewShowTime", this.e);
            jSONObject.put("pageShowTime", this.f);
            jSONObject.put("timestamp", this.g);
            jSONObject.put("viewId", this.h);
            jSONObject.put("pageId", this.j);
            jSONObject.put("label", this.i);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
