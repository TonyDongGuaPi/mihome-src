package com.sina.weibo.sdk.statistic;

import java.util.Map;

class EventLog extends PageLog {
    private String d;
    private Map<String, String> e;

    public EventLog(String str, String str2, Map<String, String> map) {
        super(str);
        this.d = str2;
        this.e = map;
    }

    public EventLog() {
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public Map<String, String> g() {
        return this.e;
    }
}
