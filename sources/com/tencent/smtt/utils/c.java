package com.tencent.smtt.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class c implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final RandomAccessFile f9202a;
    private final File b;
    private final byte[] c;
    private boolean d;

    public c(File file) {
        this.c = new byte[8];
        this.b = file;
        this.f9202a = new RandomAccessFile(this.b, "r");
    }

    public c(String str) {
        this(new File(str));
    }

    public final int a() {
        int readInt = this.f9202a.readInt();
        if (!this.d) {
            return readInt;
        }
        return ((readInt & -16777216) >>> 24) | ((readInt & 255) << 24) | ((65280 & readInt) << 8) | ((16711680 & readInt) >>> 8);
    }

    public final int a(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        int read = this.f9202a.read(bArr);
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = (char) bArr[i];
        }
        return read;
    }

    public void a(long j) {
        this.f9202a.seek(j);
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void close() {
        try {
            this.f9202a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
