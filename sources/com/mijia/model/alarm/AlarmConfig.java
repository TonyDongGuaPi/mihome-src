package com.mijia.model.alarm;

import org.json.JSONArray;

public class AlarmConfig {

    /* renamed from: a  reason: collision with root package name */
    public int f7965a = 0;
    public int b = 10;
    public int c = 0;
    public int d = 17;
    public int e = 0;
    public int f = 1;
    public int g = 5;

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AlarmConfig)) {
            return false;
        }
        AlarmConfig alarmConfig = (AlarmConfig) obj;
        if (this.f7965a != alarmConfig.f7965a || this.f != alarmConfig.f || this.g != alarmConfig.g) {
            return false;
        }
        if (this.f7965a == 0 || this.f7965a == 1) {
            return true;
        }
        if (this.b == alarmConfig.b && this.c == alarmConfig.c && this.d == alarmConfig.d && this.e == alarmConfig.e) {
            return true;
        }
        return false;
    }

    public void a(AlarmConfig alarmConfig) {
        this.f7965a = alarmConfig.f7965a;
        this.b = alarmConfig.b;
        this.c = alarmConfig.c;
        this.d = alarmConfig.d;
        this.e = alarmConfig.e;
        this.f = alarmConfig.f;
        this.g = alarmConfig.g;
    }

    static AlarmConfig a(JSONArray jSONArray) {
        AlarmConfig alarmConfig = new AlarmConfig();
        if (jSONArray == null || jSONArray.length() < 6) {
            return alarmConfig;
        }
        alarmConfig.f7965a = jSONArray.optInt(0);
        if (alarmConfig.f7965a == 2) {
            alarmConfig.b = jSONArray.optInt(1);
            alarmConfig.c = jSONArray.optInt(2);
            alarmConfig.d = jSONArray.optInt(3);
            alarmConfig.e = jSONArray.optInt(4);
        }
        if (jSONArray.length() > 5) {
            alarmConfig.f = jSONArray.optInt(5);
        }
        if (jSONArray.length() > 6) {
            alarmConfig.g = jSONArray.optInt(6, 5);
        }
        return alarmConfig;
    }

    /* access modifiers changed from: package-private */
    public JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.f7965a);
        jSONArray.put(this.b);
        jSONArray.put(this.c);
        jSONArray.put(this.d);
        jSONArray.put(this.e);
        jSONArray.put(this.f);
        jSONArray.put(this.g);
        return jSONArray;
    }
}
