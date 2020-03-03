package com.orhanobut.logger;

public final class Settings {

    /* renamed from: a  reason: collision with root package name */
    private int f8503a = 2;
    private boolean b = true;
    private int c = 0;
    private LogAdapter d;
    private LogLevel e = LogLevel.FULL;

    public Settings a() {
        this.b = false;
        return this;
    }

    public Settings a(int i) {
        if (i < 0) {
            i = 0;
        }
        this.f8503a = i;
        return this;
    }

    public Settings a(LogLevel logLevel) {
        this.e = logLevel;
        return this;
    }

    public Settings b(int i) {
        this.c = i;
        return this;
    }

    public Settings a(LogAdapter logAdapter) {
        this.d = logAdapter;
        return this;
    }

    public int b() {
        return this.f8503a;
    }

    public boolean c() {
        return this.b;
    }

    public LogLevel d() {
        return this.e;
    }

    public int e() {
        return this.c;
    }

    public LogAdapter f() {
        if (this.d == null) {
            this.d = new AndroidLogAdapter();
        }
        return this.d;
    }

    public void g() {
        this.f8503a = 2;
        this.c = 0;
        this.b = true;
        this.e = LogLevel.FULL;
    }
}
