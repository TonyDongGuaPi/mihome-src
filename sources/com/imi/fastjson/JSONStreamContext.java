package com.imi.fastjson;

class JSONStreamContext {

    /* renamed from: a  reason: collision with root package name */
    static final int f6082a = 1001;
    static final int b = 1002;
    static final int c = 1003;
    static final int d = 1004;
    static final int e = 1005;
    private final JSONStreamContext f;
    private int g;

    public JSONStreamContext(JSONStreamContext jSONStreamContext, int i) {
        this.f = jSONStreamContext;
        this.g = i;
    }

    public JSONStreamContext a() {
        return this.f;
    }

    public int b() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }
}
