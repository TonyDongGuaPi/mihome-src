package com.adobe.xmp.options;

public final class IteratorOptions extends Options {

    /* renamed from: a  reason: collision with root package name */
    public static final int f704a = 256;
    public static final int b = 512;
    public static final int c = 1024;
    public static final int d = 4096;

    public IteratorOptions a(boolean z) {
        a(256, z);
        return this;
    }

    /* access modifiers changed from: protected */
    public String a(int i) {
        if (i == 256) {
            return "JUST_CHILDREN";
        }
        if (i == 512) {
            return "JUST_LEAFNODES";
        }
        if (i == 1024) {
            return "JUST_LEAFNAME";
        }
        if (i != 4096) {
            return null;
        }
        return "OMIT_QUALIFIERS";
    }

    public boolean a() {
        return e(256);
    }

    public IteratorOptions b(boolean z) {
        a(1024, z);
        return this;
    }

    public boolean b() {
        return e(1024);
    }

    public IteratorOptions c(boolean z) {
        a(512, z);
        return this;
    }

    public boolean c() {
        return e(512);
    }

    public IteratorOptions d(boolean z) {
        a(4096, z);
        return this;
    }

    public boolean d() {
        return e(4096);
    }

    /* access modifiers changed from: protected */
    public int g() {
        return 5888;
    }
}
