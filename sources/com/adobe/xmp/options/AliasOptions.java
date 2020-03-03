package com.adobe.xmp.options;

import com.adobe.xmp.XMPException;

public final class AliasOptions extends Options {

    /* renamed from: a  reason: collision with root package name */
    public static final int f703a = 0;
    public static final int b = 512;
    public static final int c = 1024;
    public static final int d = 2048;
    public static final int e = 4096;

    public AliasOptions() {
    }

    public AliasOptions(int i) throws XMPException {
        super(i);
    }

    public AliasOptions a(boolean z) {
        a(512, z);
        return this;
    }

    /* access modifiers changed from: protected */
    public String a(int i) {
        if (i == 0) {
            return "PROP_DIRECT";
        }
        if (i == 512) {
            return "ARRAY";
        }
        if (i == 1024) {
            return "ARRAY_ORDERED";
        }
        if (i == 2048) {
            return "ARRAY_ALTERNATE";
        }
        if (i != 4096) {
            return null;
        }
        return "ARRAY_ALT_TEXT";
    }

    public boolean a() {
        return i() == 0;
    }

    public AliasOptions b(boolean z) {
        a(1536, z);
        return this;
    }

    public boolean b() {
        return e(512);
    }

    public AliasOptions c(boolean z) {
        a(3584, z);
        return this;
    }

    public boolean c() {
        return e(1024);
    }

    public AliasOptions d(boolean z) {
        a(7680, z);
        return this;
    }

    public boolean d() {
        return e(2048);
    }

    public boolean e() {
        return e(4096);
    }

    public PropertyOptions f() throws XMPException {
        return new PropertyOptions(i());
    }

    /* access modifiers changed from: protected */
    public int g() {
        return 7680;
    }
}
