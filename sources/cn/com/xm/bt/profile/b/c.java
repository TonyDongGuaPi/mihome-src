package cn.com.xm.bt.profile.b;

import com.taobao.weex.el.parse.Operators;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private byte f581a = 16;
    private byte b = -1;
    private byte c = 0;
    private byte[] d = null;
    private boolean e = false;
    private byte[] f = null;

    public static c a(byte[] bArr) {
        return a(bArr, 1);
    }

    private static c a(byte[] bArr, int i) {
        c cVar = new c();
        cVar.b(bArr, i);
        return cVar;
    }

    public synchronized byte[] a() {
        return this.f;
    }

    public synchronized byte[] b() {
        return this.d;
    }

    public synchronized byte c() {
        return this.c;
    }

    public synchronized boolean a(byte b2) {
        return this.b == b2 && this.c == a.SUCCESS.a();
    }

    public synchronized byte d() {
        return this.b;
    }

    public synchronized void b(byte[] bArr) {
        b(bArr, 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void b(byte[] r5, int r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "HMNotifyResponse"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            r1.<init>()     // Catch:{ all -> 0x0062 }
            java.lang.String r2 = "from value:"
            r1.append(r2)     // Catch:{ all -> 0x0062 }
            java.lang.String r2 = cn.com.xm.bt.c.c.a((byte[]) r5)     // Catch:{ all -> 0x0062 }
            r1.append(r2)     // Catch:{ all -> 0x0062 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0062 }
            cn.com.xm.bt.a.a.b(r0, r1)     // Catch:{ all -> 0x0062 }
            r0 = 1
            r4.e = r0     // Catch:{ all -> 0x0062 }
            r1 = 0
            if (r5 == 0) goto L_0x002f
            int r2 = r5.length     // Catch:{ all -> 0x0062 }
            if (r2 <= 0) goto L_0x002f
            int r2 = r5.length     // Catch:{ all -> 0x0062 }
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x0062 }
            r4.f = r2     // Catch:{ all -> 0x0062 }
            byte[] r2 = r4.f     // Catch:{ all -> 0x0062 }
            int r3 = r5.length     // Catch:{ all -> 0x0062 }
            java.lang.System.arraycopy(r5, r1, r2, r1, r3)     // Catch:{ all -> 0x0062 }
        L_0x002f:
            int r6 = r6 + 2
            if (r5 == 0) goto L_0x0060
            int r2 = r5.length     // Catch:{ all -> 0x0062 }
            if (r2 >= r6) goto L_0x0037
            goto L_0x0060
        L_0x0037:
            byte r2 = r5[r1]     // Catch:{ all -> 0x0062 }
            r4.f581a = r2     // Catch:{ all -> 0x0062 }
            byte r0 = r5[r0]     // Catch:{ all -> 0x0062 }
            r4.b = r0     // Catch:{ all -> 0x0062 }
            int r0 = r6 + -1
            byte r0 = r5[r0]     // Catch:{ all -> 0x0062 }
            r4.c = r0     // Catch:{ all -> 0x0062 }
            r0 = 0
            r4.d = r0     // Catch:{ all -> 0x0062 }
            int r0 = r5.length     // Catch:{ all -> 0x0062 }
            int r0 = r0 - r6
            if (r0 <= 0) goto L_0x0055
            byte[] r2 = new byte[r0]     // Catch:{ all -> 0x0062 }
            r4.d = r2     // Catch:{ all -> 0x0062 }
            byte[] r2 = r4.d     // Catch:{ all -> 0x0062 }
            java.lang.System.arraycopy(r5, r6, r2, r1, r0)     // Catch:{ all -> 0x0062 }
        L_0x0055:
            java.lang.String r5 = "HMNotifyResponse"
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x0062 }
            cn.com.xm.bt.a.a.b(r5, r6)     // Catch:{ all -> 0x0062 }
            monitor-exit(r4)
            return
        L_0x0060:
            monitor-exit(r4)
            return
        L_0x0062:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.xm.bt.profile.b.c.b(byte[], int):void");
    }

    public synchronized String toString() {
        return "HMNotifyResponse{origin=" + cn.com.xm.bt.c.c.a(this.f) + ", flag=" + String.format("%02x ", new Object[]{Byte.valueOf(this.f581a)}) + ", cmd=" + String.format("%02x ", new Object[]{Byte.valueOf(this.b)}) + ", code=" + String.format("%02x ", new Object[]{Byte.valueOf(this.c)}) + ", data=" + cn.com.xm.bt.c.c.a(this.d) + Operators.BLOCK_END;
    }

    enum a {
        RESERVED((byte) 0),
        SUCCESS((byte) 1),
        INVALID_STATE((byte) 2),
        UNKNOW_COMMAND((byte) 3),
        OPERATION_FAILED((byte) 4);
        
        private final byte f;

        private a(byte b) {
            this.f = b;
        }

        public byte a() {
            return this.f;
        }
    }
}
