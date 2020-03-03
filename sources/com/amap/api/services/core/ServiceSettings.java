package com.amap.api.services.core;

import com.amap.api.services.a.as;
import com.amap.api.services.a.br;
import com.amap.api.services.a.bv;
import com.amap.api.services.a.s;

public class ServiceSettings {
    public static final String CHINESE = "zh-CN";
    public static final String ENGLISH = "en";
    public static final int HTTP = 1;
    public static final int HTTPS = 2;
    private static ServiceSettings c;

    /* renamed from: a  reason: collision with root package name */
    private String f4449a = "zh-CN";
    private int b = 1;
    private int d = 20000;
    private int e = 20000;

    public int getConnectionTimeOut() {
        return this.d;
    }

    public int getSoTimeOut() {
        return this.e;
    }

    public void setConnectionTimeOut(int i) {
        if (i < 5000) {
            this.d = 5000;
        } else if (i > 30000) {
            this.d = 30000;
        } else {
            this.d = i;
        }
    }

    public void setSoTimeOut(int i) {
        if (i < 5000) {
            this.e = 5000;
        } else if (i > 30000) {
            this.e = 30000;
        } else {
            this.e = i;
        }
    }

    private ServiceSettings() {
    }

    public static ServiceSettings getInstance() {
        if (c == null) {
            c = new ServiceSettings();
        }
        return c;
    }

    public void setLanguage(String str) {
        if ("en".equals(str) || "zh-CN".equals(str)) {
            this.f4449a = str;
        }
    }

    public void setProtocol(int i) {
        this.b = i;
        bv.a().a(this.b == 2);
    }

    public String getLanguage() {
        return this.f4449a;
    }

    public int getProtocol() {
        return this.b;
    }

    public void setApiKey(String str) {
        br.a(str);
    }

    public void destroyInnerAsynThreadPool() {
        try {
            as.b();
        } catch (Throwable th) {
            s.a(th, "ServiceSettings", "destroyInnerAsynThreadPool");
        }
    }
}
