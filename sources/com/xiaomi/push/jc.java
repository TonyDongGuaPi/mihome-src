package com.xiaomi.push;

import java.io.ByteArrayOutputStream;

public class jc extends ByteArrayOutputStream {
    public jc() {
    }

    public jc(int i) {
        super(i);
    }

    public byte[] a() {
        return this.buf;
    }

    public int b() {
        return this.count;
    }
}
