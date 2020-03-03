package com.miuipub.internal.hybrid;

import java.util.HashMap;
import java.util.Map;

public class Feature {

    /* renamed from: a  reason: collision with root package name */
    private String f8254a;
    private Map<String, String> b = new HashMap();

    public String a() {
        return this.f8254a;
    }

    public void a(String str) {
        this.f8254a = str;
    }

    public Map<String, String> b() {
        return this.b;
    }

    public void a(Map<String, String> map) {
        this.b = map;
    }

    public void c() {
        this.b.clear();
    }

    public String b(String str) {
        return this.b.get(str);
    }

    public void a(String str, String str2) {
        this.b.put(str, str2);
    }
}
