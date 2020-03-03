package com.xiaomi.msg.utils;

import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketLossCalculate {
    private static final String c = (Constants.C + "PacketLossCalculate");

    /* renamed from: a  reason: collision with root package name */
    int f12131a;
    int b;
    private XMDTransceiver d;
    private Map<Long, RecvInfo> e;

    public PacketLossCalculate(XMDTransceiver xMDTransceiver) {
        this(xMDTransceiver, Constants.t, Constants.s);
    }

    public PacketLossCalculate(XMDTransceiver xMDTransceiver, int i, int i2) {
        this.d = xMDTransceiver;
        this.f12131a = i;
        this.b = i2;
        this.e = new ConcurrentHashMap();
    }

    public void a(long j) {
        RecvInfo recvInfo = this.e.get(Long.valueOf(j));
        if (recvInfo == null) {
            MIMCLog.c(c, "The connection may have been closed.");
            return;
        }
        synchronized (recvInfo) {
            recvInfo.g = true;
            recvInfo.c = recvInfo.f12132a;
            recvInfo.d = recvInfo.b;
            recvInfo.f = 1.0f - (((float) recvInfo.d) / ((float) (recvInfo.c - recvInfo.e)));
            recvInfo.h = (int) (recvInfo.c - recvInfo.e);
            recvInfo.i = (int) recvInfo.d;
            recvInfo.e = recvInfo.c;
            recvInfo.b -= recvInfo.d;
            recvInfo.g = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0070, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(long r5, long r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.Map<java.lang.Long, com.xiaomi.msg.utils.PacketLossCalculate$RecvInfo> r0 = r4.e     // Catch:{ all -> 0x0071 }
            java.lang.Long r1 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0071 }
            boolean r0 = r0.containsKey(r1)     // Catch:{ all -> 0x0071 }
            if (r0 != 0) goto L_0x0031
            java.lang.String r0 = c     // Catch:{ all -> 0x0071 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r1.<init>()     // Catch:{ all -> 0x0071 }
            java.lang.String r2 = "Create packet loss rate computing object for the connection, connId="
            r1.append(r2)     // Catch:{ all -> 0x0071 }
            r1.append(r5)     // Catch:{ all -> 0x0071 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0071 }
            com.xiaomi.msg.logger.MIMCLog.b(r0, r1)     // Catch:{ all -> 0x0071 }
            java.util.Map<java.lang.Long, com.xiaomi.msg.utils.PacketLossCalculate$RecvInfo> r0 = r4.e     // Catch:{ all -> 0x0071 }
            java.lang.Long r1 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0071 }
            com.xiaomi.msg.utils.PacketLossCalculate$RecvInfo r2 = new com.xiaomi.msg.utils.PacketLossCalculate$RecvInfo     // Catch:{ all -> 0x0071 }
            r2.<init>()     // Catch:{ all -> 0x0071 }
            r0.put(r1, r2)     // Catch:{ all -> 0x0071 }
        L_0x0031:
            java.util.Map<java.lang.Long, com.xiaomi.msg.utils.PacketLossCalculate$RecvInfo> r0 = r4.e     // Catch:{ all -> 0x0071 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0071 }
            java.lang.Object r5 = r0.get(r5)     // Catch:{ all -> 0x0071 }
            com.xiaomi.msg.utils.PacketLossCalculate$RecvInfo r5 = (com.xiaomi.msg.utils.PacketLossCalculate.RecvInfo) r5     // Catch:{ all -> 0x0071 }
            long r0 = r5.e     // Catch:{ all -> 0x0071 }
            int r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r6 >= 0) goto L_0x0045
            monitor-exit(r4)
            return
        L_0x0045:
            long r0 = r5.c     // Catch:{ all -> 0x0071 }
            int r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            r0 = 1
            if (r6 >= 0) goto L_0x005a
            long r6 = r5.d     // Catch:{ all -> 0x0071 }
            r8 = 0
            long r6 = r6 + r0
            r5.d = r6     // Catch:{ all -> 0x0071 }
            long r6 = r5.b     // Catch:{ all -> 0x0071 }
            r8 = 0
            long r6 = r6 + r0
            r5.b = r6     // Catch:{ all -> 0x0071 }
            goto L_0x006f
        L_0x005a:
            long r2 = r5.f12132a     // Catch:{ all -> 0x0071 }
            int r6 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0067
            long r6 = r5.b     // Catch:{ all -> 0x0071 }
            r8 = 0
            long r6 = r6 + r0
            r5.b = r6     // Catch:{ all -> 0x0071 }
            goto L_0x006f
        L_0x0067:
            long r2 = r5.b     // Catch:{ all -> 0x0071 }
            r6 = 0
            long r2 = r2 + r0
            r5.b = r2     // Catch:{ all -> 0x0071 }
            r5.f12132a = r7     // Catch:{ all -> 0x0071 }
        L_0x006f:
            monitor-exit(r4)
            return
        L_0x0071:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.msg.utils.PacketLossCalculate.a(long, long):void");
    }

    public synchronized void b(long j) {
        String str = c;
        MIMCLog.a(str, "Remove packet loss rate computing object of the connection, connId=" + j);
        if (!this.e.containsKey(Long.valueOf(j))) {
            String str2 = c;
            MIMCLog.c(str2, "connId_RecvInfo don't contain the key, connId=" + j);
        }
        this.e.remove(Long.valueOf(j));
    }

    public boolean c(long j) {
        return this.e.get(Long.valueOf(j)).g;
    }

    public RecvInfo d(long j) {
        return this.e.get(Long.valueOf(j));
    }

    public class RecvInfo {

        /* renamed from: a  reason: collision with root package name */
        long f12132a = 0;
        long b = 0;
        long c = 0;
        long d = 0;
        long e = 0;
        float f = 0.0f;
        boolean g = false;
        int h = 0;
        int i = 0;

        public RecvInfo() {
        }

        public int a() {
            return this.h;
        }

        public int b() {
            return this.i;
        }
    }
}
