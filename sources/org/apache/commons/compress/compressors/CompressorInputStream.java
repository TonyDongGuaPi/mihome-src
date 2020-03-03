package org.apache.commons.compress.compressors;

import java.io.InputStream;

public abstract class CompressorInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private long f3308a = 0;

    /* access modifiers changed from: protected */
    public void a(int i) {
        a((long) i);
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        if (j != -1) {
            this.f3308a += j;
        }
    }

    /* access modifiers changed from: protected */
    public void b(long j) {
        this.f3308a -= j;
    }

    @Deprecated
    public int b() {
        return (int) this.f3308a;
    }

    public long c() {
        return this.f3308a;
    }
}
