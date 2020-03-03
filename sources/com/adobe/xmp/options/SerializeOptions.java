package com.adobe.xmp.options;

import com.adobe.xmp.XMPException;

public final class SerializeOptions extends Options {

    /* renamed from: a  reason: collision with root package name */
    public static final int f708a = 16;
    public static final int b = 32;
    public static final int c = 64;
    public static final int d = 128;
    public static final int e = 256;
    public static final int f = 512;
    public static final int g = 4096;
    public static final int h = 8192;
    public static final int i = 0;
    public static final int j = 2;
    public static final int k = 3;
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 3;
    private int o = 2048;
    private String p = "\n";
    private String q = "  ";
    private int r = 0;
    private boolean s = false;

    public SerializeOptions() {
    }

    public SerializeOptions(int i2) throws XMPException {
        super(i2);
    }

    public SerializeOptions a(String str) {
        this.q = str;
        return this;
    }

    public SerializeOptions a(boolean z) {
        a(16, z);
        return this;
    }

    /* access modifiers changed from: protected */
    public String a(int i2) {
        if (i2 == 16) {
            return "OMIT_PACKET_WRAPPER";
        }
        if (i2 == 32) {
            return "READONLY_PACKET";
        }
        if (i2 == 64) {
            return "USE_COMPACT_FORMAT";
        }
        if (i2 == 256) {
            return "INCLUDE_THUMBNAIL_PAD";
        }
        if (i2 == 512) {
            return "EXACT_PACKET_LENGTH";
        }
        if (i2 == 4096) {
            return "OMIT_XMPMETA_ELEMENT";
        }
        if (i2 != 8192) {
            return null;
        }
        return "NORMALIZED";
    }

    public boolean a() {
        return e(16);
    }

    public SerializeOptions b(String str) {
        this.p = str;
        return this;
    }

    public SerializeOptions b(boolean z) {
        a(4096, z);
        return this;
    }

    public boolean b() {
        return e(4096);
    }

    public SerializeOptions c(boolean z) {
        a(32, z);
        return this;
    }

    public boolean c() {
        return e(32);
    }

    public Object clone() throws CloneNotSupportedException {
        try {
            SerializeOptions serializeOptions = new SerializeOptions(i());
            serializeOptions.h(this.r);
            serializeOptions.a(this.q);
            serializeOptions.b(this.p);
            serializeOptions.i(this.o);
            return serializeOptions;
        } catch (XMPException unused) {
            return null;
        }
    }

    public SerializeOptions d(boolean z) {
        a(64, z);
        return this;
    }

    public boolean d() {
        return e(64);
    }

    public SerializeOptions e(boolean z) {
        a(128, z);
        return this;
    }

    public boolean e() {
        return e(128);
    }

    public SerializeOptions f(boolean z) {
        a(256, z);
        return this;
    }

    public boolean f() {
        return e(256);
    }

    /* access modifiers changed from: protected */
    public int g() {
        return 13168;
    }

    public SerializeOptions g(boolean z) {
        a(512, z);
        return this;
    }

    public SerializeOptions h(int i2) {
        this.r = i2;
        return this;
    }

    public SerializeOptions h(boolean z) {
        a(8192, z);
        return this;
    }

    public SerializeOptions i(int i2) {
        this.o = i2;
        return this;
    }

    public SerializeOptions i(boolean z) {
        a(3, false);
        a(2, z);
        return this;
    }

    public SerializeOptions j(boolean z) {
        a(3, false);
        a(3, z);
        return this;
    }

    public boolean k() {
        return e(512);
    }

    public boolean l() {
        return e(8192);
    }

    public boolean m() {
        return (i() & 3) == 2;
    }

    public boolean n() {
        return (i() & 3) == 3;
    }

    public int o() {
        return this.r;
    }

    public String p() {
        return this.q;
    }

    public String q() {
        return this.p;
    }

    public int r() {
        return this.o;
    }

    public boolean s() {
        return this.s;
    }

    public String t() {
        return m() ? "UTF-16BE" : n() ? "UTF-16LE" : "UTF-8";
    }
}
