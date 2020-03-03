package com.libra.sinvoice;

public class BufferQueue {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6242a = "BufferQueue";
    private static final int b = 1;
    private static final int c = 2;
    private volatile int d;
    private volatile IQueue e;
    private volatile IQueue f;
    private volatile BufferData[] g;

    public BufferQueue(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            LogHelper.c(f6242a, "BufferQueue param error, bufferCount:" + i + "  bufferSize:" + i2);
            return;
        }
        this.g = new BufferData[i];
        for (int i3 = 0; i3 < i; i3++) {
            this.g[i3] = new BufferData(i2);
        }
        this.e = new SafeQueue(i);
        this.f = new SafeQueue(i);
        this.d = 2;
    }

    public void a() {
        if (2 != this.d) {
            return;
        }
        if (this.e == null || this.f == null) {
            LogHelper.c(f6242a, "set queue is null");
            return;
        }
        for (BufferData a2 : this.g) {
            a2.a();
        }
        this.e.a(this.g);
        this.f.a((BufferData[]) null);
        this.d = 1;
    }

    public void b() {
        if (1 != this.d) {
            return;
        }
        if (this.e == null || this.f == null) {
            LogHelper.c(f6242a, "reset queue is null");
            return;
        }
        this.d = 2;
        this.e.a();
        this.f.a();
    }

    public BufferData c() {
        if (1 != this.d) {
            return null;
        }
        if (this.e != null) {
            return this.e.b();
        }
        LogHelper.c(f6242a, "getEmpty queue is null");
        return null;
    }

    public boolean a(BufferData bufferData) {
        if (1 != this.d) {
            return false;
        }
        if (this.e != null) {
            return this.e.a(bufferData);
        }
        LogHelper.c(f6242a, "putEmpty queue is null");
        return false;
    }

    public BufferData d() {
        if (1 != this.d) {
            return null;
        }
        if (this.f != null) {
            return this.f.b();
        }
        LogHelper.c(f6242a, "getFull queue is null");
        return null;
    }

    public boolean b(BufferData bufferData) {
        if (this.f != null) {
            return this.f.a(bufferData);
        }
        LogHelper.c(f6242a, "putFull queue is null");
        return false;
    }
}
