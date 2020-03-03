package com.xiaomi.zxing.datamatrix.encoder;

import com.xiaomi.zxing.Dimension;
import java.nio.charset.Charset;

final class EncoderContext {

    /* renamed from: a  reason: collision with root package name */
    int f1673a;
    private final String b;
    private SymbolShapeHint c;
    private Dimension d;
    private Dimension e;
    private final StringBuilder f;
    private int g;
    private SymbolInfo h;
    private int i;

    EncoderContext(String str) {
        byte[] bytes = str.getBytes(Charset.forName("ISO-8859-1"));
        StringBuilder sb = new StringBuilder(bytes.length);
        int length = bytes.length;
        int i2 = 0;
        while (i2 < length) {
            char c2 = (char) (bytes[i2] & 255);
            if (c2 != '?' || str.charAt(i2) == '?') {
                sb.append(c2);
                i2++;
            } else {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
        }
        this.b = sb.toString();
        this.c = SymbolShapeHint.FORCE_NONE;
        this.f = new StringBuilder(str.length());
        this.g = -1;
    }

    public void a(SymbolShapeHint symbolShapeHint) {
        this.c = symbolShapeHint;
    }

    public void a(Dimension dimension, Dimension dimension2) {
        this.d = dimension;
        this.e = dimension2;
    }

    public String a() {
        return this.b;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public char b() {
        return this.b.charAt(this.f1673a);
    }

    public char c() {
        return this.b.charAt(this.f1673a);
    }

    public StringBuilder d() {
        return this.f;
    }

    public void a(String str) {
        this.f.append(str);
    }

    public void a(char c2) {
        this.f.append(c2);
    }

    public int e() {
        return this.f.length();
    }

    public int f() {
        return this.g;
    }

    public void b(int i2) {
        this.g = i2;
    }

    public void g() {
        this.g = -1;
    }

    public boolean h() {
        return this.f1673a < m();
    }

    private int m() {
        return this.b.length() - this.i;
    }

    public int i() {
        return m() - this.f1673a;
    }

    public SymbolInfo j() {
        return this.h;
    }

    public void k() {
        c(e());
    }

    public void c(int i2) {
        if (this.h == null || i2 > this.h.i()) {
            this.h = SymbolInfo.a(i2, this.c, this.d, this.e, true);
        }
    }

    public void l() {
        this.h = null;
    }
}
