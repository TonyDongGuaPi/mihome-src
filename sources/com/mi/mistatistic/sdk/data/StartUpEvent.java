package com.mi.mistatistic.sdk.data;

import com.alipay.mobile.common.logging.api.LogCategory;
import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.market.sdk.Constants;
import org.json.JSONObject;

public class StartUpEvent extends CustomDataEvent {
    private String c;
    private String d;
    private long e;
    private String f;
    private long g;

    public String a() {
        return RemoteDataUploadManager.k;
    }

    public StartUpEvent() {
    }

    public StartUpEvent(String str, String str2, long j, String str3) {
        this.c = str;
        this.d = str2;
        this.e = j;
        this.f = str3;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public String g() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public long h() {
        return this.e;
    }

    public void b(long j) {
        this.e = j;
    }

    public String i() {
        return this.f;
    }

    public void e(String str) {
        this.f = str;
    }

    public long d() {
        return this.g;
    }

    public void a(long j) {
        this.g = j;
    }

    public String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TSMAuthContants.PARAM_SESSION_ID, this.c);
            jSONObject.put(Constants.x, this.d);
            jSONObject.put("startTime", this.e);
            jSONObject.put(LogCategory.CATEGORY_NETWORK, this.f);
            jSONObject.put("timestamp", this.g);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
