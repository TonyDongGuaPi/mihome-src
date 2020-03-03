package com.xiaomi.smarthome.library.http;

public class KeyValuePair {

    /* renamed from: a  reason: collision with root package name */
    private final String f19105a;
    private final String b;

    public KeyValuePair(String str, String str2) {
        if (str != null) {
            this.f19105a = str;
            this.b = str2;
            return;
        }
        throw new IllegalArgumentException("Key may not be null");
    }

    public String a() {
        return this.f19105a;
    }

    public String b() {
        return this.b;
    }
}
