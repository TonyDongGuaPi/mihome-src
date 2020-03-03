package com.xiaomi.mistatistic.sdk.data;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.xiaomi.iot.spec.api.Constants;
import com.xiaomi.mistatistic.sdk.controller.c;
import com.xiaomi.mistatistic.sdk.controller.j;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpEvent {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12064a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private long d;
    private long e;
    private int f;
    private String g;
    private String h;
    private long i;
    private String j;
    private long k;
    private String l;
    private String m;
    private String n;
    private int o;

    public HttpEvent(String str, long j2) {
        this(str, j2, -1, (String) null);
    }

    public HttpEvent(String str, long j2, long j3) {
        this(str, j2, j3, -1, (String) null);
    }

    public HttpEvent(String str, long j2, long j3, int i2) {
        this(str, j2, j3, i2, (String) null);
    }

    public HttpEvent(String str, long j2, String str2) {
        this(str, j2, -1, str2);
    }

    public HttpEvent(String str, long j2, int i2, String str2) {
        this(str, j2, 0, i2, str2);
    }

    public HttpEvent(String str, long j2, long j3, int i2, String str2) {
        this.i = System.currentTimeMillis();
        this.k = 0;
        this.o = 0;
        this.j = str;
        this.d = j2;
        this.f = i2;
        this.g = str2;
        this.k = j3;
        c();
    }

    public HttpEvent(String str, String str2) {
        this(str, -1, -1, str2);
    }

    public void a(String str) {
        this.h = str;
    }

    public void b(String str) {
        this.j = str;
    }

    public String a() {
        return this.j;
    }

    public void a(long j2) {
        this.e = j2;
    }

    public long b() {
        return this.e;
    }

    public void c() {
        if (c.a() == null) {
            this.h = "NULL";
            return;
        }
        String b2 = j.b(c.a());
        if (TextUtils.isEmpty(b2)) {
            this.h = "NULL";
            return;
        }
        this.h = b2;
        if (!"WIFI".equalsIgnoreCase(b2)) {
            this.l = ((TelephonyManager) c.a().getSystemService("phone")).getSimOperator();
        }
    }

    public void a(int i2) {
        this.o = i2;
    }

    public int d() {
        return this.o;
    }

    public JSONObject e() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("net", this.h);
        if (this.d > 0) {
            jSONObject.put("cost", this.d);
        }
        if (this.e > 0) {
            jSONObject.put("first_byte_t", this.e);
        }
        if (this.f != -1) {
            jSONObject.put("code", this.f);
        }
        if (!TextUtils.isEmpty(this.g)) {
            jSONObject.put(LogCategory.CATEGORY_EXCEPTION, this.g);
        }
        if (!TextUtils.isEmpty(this.l)) {
            jSONObject.put("op", this.l);
        }
        if (this.k > 0) {
            jSONObject.put("flow", this.k);
        }
        if (this.o == 1 || this.o == 2) {
            jSONObject.put("flow_status", this.o);
        }
        if (!TextUtils.isEmpty(this.m)) {
            jSONObject.put(Constants.Device.e, this.m);
        }
        if (!TextUtils.isEmpty(this.n)) {
            jSONObject.put("dns", this.n);
        }
        jSONObject.put("t", this.i);
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HttpEvent)) {
            return false;
        }
        HttpEvent httpEvent = (HttpEvent) obj;
        if (TextUtils.equals(this.j, httpEvent.j) && TextUtils.equals(this.h, httpEvent.h) && TextUtils.equals(this.g, httpEvent.g) && TextUtils.equals(this.n, httpEvent.n) && this.f == httpEvent.f && this.d == httpEvent.d && this.i == httpEvent.i && this.k == httpEvent.k && this.o == httpEvent.o && TextUtils.equals(this.m, httpEvent.m) && this.e == httpEvent.e) {
            return true;
        }
        return false;
    }

    public String f() {
        return this.m;
    }

    public void c(String str) {
        this.m = str;
    }

    public String g() {
        return this.n;
    }

    public void d(String str) {
        this.n = str;
    }

    public long h() {
        return this.k;
    }
}
