package com.xiaomi.ai;

import org.json.JSONObject;

public class EventRequest {

    /* renamed from: a  reason: collision with root package name */
    String f9897a;
    String b;
    String c;
    String d;
    String e;
    String f;
    boolean g = true;
    boolean h = false;
    JSONObject i;
    TtsRequest j;

    public EventRequest(String str, String str2, String str3) {
        this.f9897a = str;
        this.b = str2;
        this.c = str3;
    }

    public TtsRequest a() {
        return this.j;
    }

    public void a(TtsRequest ttsRequest) {
        this.j = ttsRequest;
    }

    public void a(String str, String str2, String str3) {
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    public void a(JSONObject jSONObject) {
        this.i = jSONObject;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void b() {
        this.h = true;
    }
}
