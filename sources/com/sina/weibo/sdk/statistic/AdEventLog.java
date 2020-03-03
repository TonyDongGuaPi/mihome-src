package com.sina.weibo.sdk.statistic;

import java.util.Map;

class AdEventLog extends EventLog {
    private String d;
    private String e = "";
    private String f = "";
    private Map<String, String> g;

    public String a() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public Map<String, String> b() {
        return this.g;
    }

    public void a(Map<String, String> map) {
        this.g = map;
    }

    public String c() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }
}
