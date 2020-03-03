package com.xiaomi.zxing.common;

import java.util.List;

public final class DecoderResult {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f1648a;
    private final String b;
    private final List<byte[]> c;
    private final String d;
    private Integer e;
    private Integer f;
    private Object g;
    private final int h;
    private final int i;

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2, int i2, int i3) {
        this.f1648a = bArr;
        this.b = str;
        this.c = list;
        this.d = str2;
        this.h = i3;
        this.i = i2;
    }

    public byte[] a() {
        return this.f1648a;
    }

    public String b() {
        return this.b;
    }

    public List<byte[]> c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public Integer e() {
        return this.e;
    }

    public void a(Integer num) {
        this.e = num;
    }

    public Integer f() {
        return this.f;
    }

    public void b(Integer num) {
        this.f = num;
    }

    public Object g() {
        return this.g;
    }

    public void a(Object obj) {
        this.g = obj;
    }

    public boolean h() {
        return this.h >= 0 && this.i >= 0;
    }

    public int i() {
        return this.h;
    }

    public int j() {
        return this.i;
    }
}
