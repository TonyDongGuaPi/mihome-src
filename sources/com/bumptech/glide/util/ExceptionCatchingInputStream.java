package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public class ExceptionCatchingInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final Queue<ExceptionCatchingInputStream> f5097a = Util.a(0);
    private InputStream b;
    private IOException c;

    @NonNull
    public static ExceptionCatchingInputStream a(@NonNull InputStream inputStream) {
        ExceptionCatchingInputStream poll;
        synchronized (f5097a) {
            poll = f5097a.poll();
        }
        if (poll == null) {
            poll = new ExceptionCatchingInputStream();
        }
        poll.b(inputStream);
        return poll;
    }

    static void a() {
        while (!f5097a.isEmpty()) {
            f5097a.remove();
        }
    }

    ExceptionCatchingInputStream() {
    }

    /* access modifiers changed from: package-private */
    public void b(@NonNull InputStream inputStream) {
        this.b = inputStream;
    }

    public int available() throws IOException {
        return this.b.available();
    }

    public void close() throws IOException {
        this.b.close();
    }

    public void mark(int i) {
        this.b.mark(i);
    }

    public boolean markSupported() {
        return this.b.markSupported();
    }

    public int read(byte[] bArr) {
        try {
            return this.b.read(bArr);
        } catch (IOException e) {
            this.c = e;
            return -1;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        try {
            return this.b.read(bArr, i, i2);
        } catch (IOException e) {
            this.c = e;
            return -1;
        }
    }

    public synchronized void reset() throws IOException {
        this.b.reset();
    }

    public long skip(long j) {
        try {
            return this.b.skip(j);
        } catch (IOException e) {
            this.c = e;
            return 0;
        }
    }

    public int read() {
        try {
            return this.b.read();
        } catch (IOException e) {
            this.c = e;
            return -1;
        }
    }

    @Nullable
    public IOException b() {
        return this.c;
    }

    public void c() {
        this.c = null;
        this.b = null;
        synchronized (f5097a) {
            f5097a.offer(this);
        }
    }
}
