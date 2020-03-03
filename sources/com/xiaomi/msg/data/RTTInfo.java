package com.xiaomi.msg.data;

import com.xiaomi.msg.common.Constants;

public class RTTInfo implements Comparable<RTTInfo> {

    /* renamed from: a  reason: collision with root package name */
    private Long f12084a;
    private Long b;
    private Long c = Long.MAX_VALUE;

    public RTTInfo(long j, long j2) {
        this.f12084a = Long.valueOf(j);
        this.b = Long.valueOf(j2);
    }

    public void a(long j) {
        synchronized (this.c) {
            this.c = Long.valueOf(j);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        return Long.MAX_VALUE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a() {
        /*
            r6 = this;
            java.lang.Long r0 = r6.c
            monitor-enter(r0)
            java.lang.Long r1 = r6.b     // Catch:{ all -> 0x002f }
            long r1 = r1.longValue()     // Catch:{ all -> 0x002f }
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x002d
            java.lang.Long r1 = r6.c     // Catch:{ all -> 0x002f }
            long r1 = r1.longValue()     // Catch:{ all -> 0x002f }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x001d
            goto L_0x002d
        L_0x001d:
            java.lang.Long r1 = r6.c     // Catch:{ all -> 0x002f }
            long r1 = r1.longValue()     // Catch:{ all -> 0x002f }
            java.lang.Long r3 = r6.b     // Catch:{ all -> 0x002f }
            long r3 = r3.longValue()     // Catch:{ all -> 0x002f }
            r5 = 0
            long r1 = r1 - r3
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r1
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r3
        L_0x002f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.msg.data.RTTInfo.a():long");
    }

    public boolean b(long j) {
        return j - this.b.longValue() >= ((long) Constants.t);
    }

    public long b() {
        return this.b.longValue();
    }

    public long c() {
        return this.f12084a.longValue();
    }

    /* renamed from: a */
    public int compareTo(RTTInfo rTTInfo) {
        return rTTInfo.b.compareTo(this.b);
    }
}
