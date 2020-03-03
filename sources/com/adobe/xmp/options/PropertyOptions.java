package com.adobe.xmp.options;

import com.adobe.xmp.XMPException;

public final class PropertyOptions extends Options {

    /* renamed from: a  reason: collision with root package name */
    public static final int f707a = 0;
    public static final int b = 2;
    public static final int c = 16;
    public static final int d = 32;
    public static final int e = 64;
    public static final int f = 128;
    public static final int g = 256;
    public static final int h = 512;
    public static final int i = 1024;
    public static final int j = 2048;
    public static final int k = 4096;
    public static final int l = Integer.MIN_VALUE;
    public static final int m = 536870912;

    public PropertyOptions() {
    }

    public PropertyOptions(int i2) throws XMPException {
        super(i2);
    }

    public PropertyOptions a(boolean z) {
        a(2, z);
        return this;
    }

    /* access modifiers changed from: protected */
    public String a(int i2) {
        switch (i2) {
            case Integer.MIN_VALUE:
                return "SCHEMA_NODE";
            case 2:
                return Constants.o;
            case 16:
                return "HAS_QUALIFIER";
            case 32:
                return "QUALIFIER";
            case 64:
                return "HAS_LANGUAGE";
            case 128:
                return "HAS_TYPE";
            case 256:
                return "STRUCT";
            case 512:
                return "ARRAY";
            case 1024:
                return "ARRAY_ORDERED";
            case 2048:
                return "ARRAY_ALTERNATE";
            case 4096:
                return "ARRAY_ALT_TEXT";
            default:
                return null;
        }
    }

    public boolean a() {
        return e(2);
    }

    public boolean a(PropertyOptions propertyOptions) {
        return k() == propertyOptions.k() && l() == propertyOptions.l() && m() == propertyOptions.m() && n() == propertyOptions.n();
    }

    public PropertyOptions b(boolean z) {
        a(16, z);
        return this;
    }

    public void b(PropertyOptions propertyOptions) throws XMPException {
        if (propertyOptions != null) {
            f(propertyOptions.i() | i());
        }
    }

    public boolean b() {
        return e(16);
    }

    public PropertyOptions c(boolean z) {
        a(32, z);
        return this;
    }

    public boolean c() {
        return e(32);
    }

    public PropertyOptions d(boolean z) {
        a(64, z);
        return this;
    }

    public boolean d() {
        return e(64);
    }

    public PropertyOptions e(boolean z) {
        a(128, z);
        return this;
    }

    public boolean e() {
        return e(128);
    }

    public PropertyOptions f(boolean z) {
        a(256, z);
        return this;
    }

    public boolean f() {
        return e(256);
    }

    /* access modifiers changed from: protected */
    public int g() {
        return -2147475470;
    }

    public PropertyOptions g(boolean z) {
        a(512, z);
        return this;
    }

    public void g(int i2) throws XMPException {
        if ((i2 & 256) > 0 && (i2 & 512) > 0) {
            throw new XMPException("IsStruct and IsArray options are mutually exclusive", 103);
        } else if ((i2 & 2) > 0 && (i2 & 768) > 0) {
            throw new XMPException("Structs and arrays can't have \"value\" options", 103);
        }
    }

    public PropertyOptions h(boolean z) {
        a(1024, z);
        return this;
    }

    public PropertyOptions i(boolean z) {
        a(2048, z);
        return this;
    }

    public PropertyOptions j(boolean z) {
        a(4096, z);
        return this;
    }

    public PropertyOptions k(boolean z) {
        a(Integer.MIN_VALUE, z);
        return this;
    }

    public boolean k() {
        return e(512);
    }

    public boolean l() {
        return e(1024);
    }

    public boolean m() {
        return e(2048);
    }

    public boolean n() {
        return e(4096);
    }

    public boolean o() {
        return e(Integer.MIN_VALUE);
    }

    public boolean p() {
        return (i() & 768) > 0;
    }

    public boolean q() {
        return (i() & 768) == 0;
    }

    public boolean r() {
        return (i() & -7681) == 0;
    }
}
