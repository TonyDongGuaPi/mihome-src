package com.adobe.xmp.options;

public final class ParseOptions extends Options {

    /* renamed from: a  reason: collision with root package name */
    public static final int f706a = 1;
    public static final int b = 4;
    public static final int c = 8;
    public static final int d = 16;
    public static final int e = 32;
    public static final int f = 64;

    public ParseOptions() {
        a(88, true);
    }

    public ParseOptions a(boolean z) {
        a(1, z);
        return this;
    }

    /* access modifiers changed from: protected */
    public String a(int i) {
        if (i == 1) {
            return "REQUIRE_XMP_META";
        }
        if (i == 4) {
            return "STRICT_ALIASING";
        }
        if (i == 8) {
            return "FIX_CONTROL_CHARS";
        }
        if (i == 16) {
            return "ACCEPT_LATIN_1";
        }
        if (i == 32) {
            return "OMIT_NORMALIZATION";
        }
        if (i != 64) {
            return null;
        }
        return "DISALLOW_DOCTYPE";
    }

    public boolean a() {
        return e(1);
    }

    public ParseOptions b(boolean z) {
        a(4, z);
        return this;
    }

    public boolean b() {
        return e(4);
    }

    public ParseOptions c(boolean z) {
        a(8, z);
        return this;
    }

    public boolean c() {
        return e(8);
    }

    public ParseOptions d(boolean z) {
        a(32, z);
        return this;
    }

    public boolean d() {
        return e(16);
    }

    public ParseOptions e(boolean z) {
        a(64, z);
        return this;
    }

    public boolean e() {
        return e(32);
    }

    public ParseOptions f(boolean z) {
        a(16, z);
        return this;
    }

    public boolean f() {
        return e(64);
    }

    /* access modifiers changed from: protected */
    public int g() {
        return 125;
    }
}
