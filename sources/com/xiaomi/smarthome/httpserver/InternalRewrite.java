package com.xiaomi.smarthome.httpserver;

import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import java.util.Map;

public class InternalRewrite extends NanoHTTPD.Response {

    /* renamed from: a  reason: collision with root package name */
    private final String f18379a;
    private final Map<String, String> b;

    public InternalRewrite(Map<String, String> map, String str) {
        super((String) null);
        this.b = map;
        this.f18379a = str;
    }

    public String a() {
        return this.f18379a;
    }

    public Map<String, String> b() {
        return this.b;
    }
}
