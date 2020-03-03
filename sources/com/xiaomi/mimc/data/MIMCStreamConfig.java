package com.xiaomi.mimc.data;

public class MIMCStreamConfig {

    /* renamed from: a  reason: collision with root package name */
    public static final int f11189a = 0;
    public static final int b = 1;
    private int c;
    private int d;
    private boolean e;

    public MIMCStreamConfig(int i, int i2, boolean z) {
        this.c = i;
        this.d = i2;
        this.e = z;
    }

    public MIMCStreamConfig(int i, boolean z) {
        this.c = i;
        this.d = 0;
        this.e = z;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public boolean c() {
        return this.e;
    }
}
