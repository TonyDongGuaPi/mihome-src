package org.apache.commons.compress.archivers.zip;

import com.xiaomi.stat.d;

public class ScatterStatistics {

    /* renamed from: a  reason: collision with root package name */
    private final long f3260a;
    private final long b;

    ScatterStatistics(long j, long j2) {
        this.f3260a = j;
        this.b = j2;
    }

    public long a() {
        return this.f3260a;
    }

    public long b() {
        return this.b;
    }

    public String toString() {
        return "compressionElapsed=" + this.f3260a + "ms, mergingElapsed=" + this.b + d.H;
    }
}
