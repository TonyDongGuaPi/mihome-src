package com.loc;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class fd {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<Charset> f6607a = new ThreadLocal<Charset>() {
        /* access modifiers changed from: protected */
        public final /* synthetic */ Object initialValue() {
            return Charset.forName("UTF-8");
        }
    };
    private static final ThreadLocal<CharsetDecoder> d = new ThreadLocal<CharsetDecoder>() {
        /* access modifiers changed from: protected */
        public final /* synthetic */ Object initialValue() {
            return Charset.forName("UTF-8").newDecoder();
        }
    };
    private static final ThreadLocal<CharBuffer> e = new ThreadLocal<>();
    protected int b;
    protected ByteBuffer c;

    /* access modifiers changed from: protected */
    public int a(Integer num, Integer num2, ByteBuffer byteBuffer) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int c(int i) {
        int i2 = this.b - this.c.getInt(this.b);
        if (i < this.c.getShort(i2)) {
            return this.c.getShort(i2 + i);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int d(int i) {
        return i + this.c.getInt(i);
    }

    /* access modifiers changed from: protected */
    public int e(int i) {
        int i2 = i + this.b;
        return this.c.getInt(i2 + this.c.getInt(i2));
    }

    /* access modifiers changed from: protected */
    public int f(int i) {
        int i2 = i + this.b;
        return i2 + this.c.getInt(i2) + 4;
    }
}
