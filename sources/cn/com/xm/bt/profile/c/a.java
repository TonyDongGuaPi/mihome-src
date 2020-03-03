package cn.com.xm.bt.profile.c;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import cn.com.xm.bt.c.b;
import cn.com.xm.bt.c.c;
import cn.com.xm.bt.c.d;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final UUID f583a = c.a(32);
    private static BluetoothGattCharacteristic b;
    private static b c = null;
    private static int d = 0;
    private static d.b e = null;
    private static d.b f = $$Lambda$a$FqhIrO5szbEoB8Ty6ueRFmUyM.INSTANCE;

    /* access modifiers changed from: private */
    public static /* synthetic */ void c(byte[] bArr) {
        cn.com.xm.bt.a.a.a("HMPieceProfile", "patchWrite notify:" + c.a(bArr));
        if (e != null) {
            e.notify(bArr);
        }
    }

    public static synchronized boolean a(b bVar, boolean z) {
        synchronized (a.class) {
            c = bVar;
            BluetoothGattService a2 = bVar.a(cn.com.xm.bt.profile.b.b.f580a);
            if (a2 == null) {
                cn.com.xm.bt.a.a.a("HMPieceProfile", cn.com.xm.bt.profile.b.b.f580a + " is null!!!");
                return false;
            }
            b = a2.getCharacteristic(f583a);
            if (b == null) {
                cn.com.xm.bt.a.a.a("HMPieceProfile", f583a + " is null!!!");
                return false;
            }
            byte[] g = bVar.g(b);
            cn.com.xm.bt.a.a.b("HMPieceProfile", "data:" + c.a(g));
            if (g != null && g.length == 1) {
                d = g[0] & 255;
                cn.com.xm.bt.a.a.a("HMPieceProfile", "piece profile version:" + d);
            }
            if (!z) {
                return true;
            }
            boolean a3 = bVar.a(b, f);
            return a3;
        }
    }

    public static synchronized boolean a() {
        boolean z;
        synchronized (a.class) {
            z = b != null;
        }
        return z;
    }

    public static boolean a(byte[] bArr, int i) {
        b b2 = b(bArr, i);
        cn.com.xm.bt.a.a.a("HMPieceProfile", "PieceResult:" + b2);
        if (b2 == null || !b2.b() || !b2.c() || b2.a() != 0) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007a, code lost:
        if (r1 != 0) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007c, code lost:
        r1 = 17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r6 = new byte[(r1 + 3)];
        r6[0] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0084, code lost:
        if (r3 <= 1) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        r3 = -128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0089, code lost:
        r3 = -64;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
        r6[1] = (byte) (r13 | r3);
        r6[2] = (byte) (r7 % 255);
        java.lang.System.arraycopy(r12, r7 * 17, r6, 3, r1);
        cn.com.xm.bt.a.a.b("HMPieceProfile", "patchWrite:" + cn.com.xm.bt.c.c.a(r6));
        r12 = b(r6);
        cn.com.xm.bt.a.a.b("HMPieceProfile", "response:" + r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00cd, code lost:
        if (r12 != null) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d0, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r12 = r12.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00d5, code lost:
        if (r12 == null) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d9, code lost:
        if (r12.length < 4) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00dd, code lost:
        if (r12[1] != 0) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e3, code lost:
        if (r12[2] == r6[1]) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e6, code lost:
        r13 = r12[3] & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ec, code lost:
        if (r12.length < 5) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ee, code lost:
        r5 = r12[4] & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f4, code lost:
        if (r12.length < 7) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f6, code lost:
        r4 = (r12[5] & 255) | ((r12[6] & 255) << 8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0103, code lost:
        r12 = new cn.com.xm.bt.profile.c.b(r13, r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0109, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x010b, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized cn.com.xm.bt.profile.c.b b(byte[] r12, int r13) {
        /*
            java.lang.Class<cn.com.xm.bt.profile.c.a> r0 = cn.com.xm.bt.profile.c.a.class
            monitor-enter(r0)
            java.lang.String r1 = "HMPieceProfile"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x010c }
            r2.<init>()     // Catch:{ all -> 0x010c }
            java.lang.String r3 = "patchWrite origin:"
            r2.append(r3)     // Catch:{ all -> 0x010c }
            java.lang.String r3 = cn.com.xm.bt.c.c.a((byte[]) r12)     // Catch:{ all -> 0x010c }
            r2.append(r3)     // Catch:{ all -> 0x010c }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x010c }
            cn.com.xm.bt.a.a.b(r1, r2)     // Catch:{ all -> 0x010c }
            int r1 = r12.length     // Catch:{ all -> 0x010c }
            r2 = 17
            int r3 = r1 / 17
            int r1 = r1 % r2
            r4 = 0
            r5 = 1
            if (r1 != 0) goto L_0x0029
            r6 = 0
            goto L_0x002a
        L_0x0029:
            r6 = 1
        L_0x002a:
            int r3 = r3 + r6
            r6 = 0
        L_0x002c:
            int r7 = r3 + -1
            r8 = 0
            r9 = 2
            r10 = 3
            if (r6 >= r7) goto L_0x007a
            r7 = 20
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x010c }
            r7[r4] = r4     // Catch:{ all -> 0x010c }
            if (r6 != 0) goto L_0x003d
            r11 = 0
            goto L_0x003f
        L_0x003d:
            r11 = 64
        L_0x003f:
            r11 = r11 | r13
            byte r11 = (byte) r11     // Catch:{ all -> 0x010c }
            r7[r5] = r11     // Catch:{ all -> 0x010c }
            int r11 = r6 % 255
            byte r11 = (byte) r11     // Catch:{ all -> 0x010c }
            r7[r9] = r11     // Catch:{ all -> 0x010c }
            int r9 = r6 * 17
            java.lang.System.arraycopy(r12, r9, r7, r10, r2)     // Catch:{ all -> 0x010c }
            java.lang.String r9 = "HMPieceProfile"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x010c }
            r10.<init>()     // Catch:{ all -> 0x010c }
            java.lang.String r11 = "patchWrite:"
            r10.append(r11)     // Catch:{ all -> 0x010c }
            java.lang.String r11 = cn.com.xm.bt.c.c.a((byte[]) r7)     // Catch:{ all -> 0x010c }
            r10.append(r11)     // Catch:{ all -> 0x010c }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x010c }
            cn.com.xm.bt.a.a.b(r9, r10)     // Catch:{ all -> 0x010c }
            boolean r7 = a(r7)     // Catch:{ all -> 0x010c }
            if (r7 != 0) goto L_0x0077
            java.lang.String r12 = "HMPieceProfile"
            java.lang.String r13 = "writeNoResponse failed!!!"
            cn.com.xm.bt.a.a.b(r12, r13)     // Catch:{ all -> 0x010c }
            monitor-exit(r0)
            return r8
        L_0x0077:
            int r6 = r6 + 1
            goto L_0x002c
        L_0x007a:
            if (r1 != 0) goto L_0x007e
            r1 = 17
        L_0x007e:
            int r6 = r1 + 3
            byte[] r6 = new byte[r6]     // Catch:{ all -> 0x010c }
            r6[r4] = r4     // Catch:{ all -> 0x010c }
            if (r3 <= r5) goto L_0x0089
            r3 = -128(0xffffffffffffff80, float:NaN)
            goto L_0x008b
        L_0x0089:
            r3 = -64
        L_0x008b:
            r13 = r13 | r3
            byte r13 = (byte) r13     // Catch:{ all -> 0x010c }
            r6[r5] = r13     // Catch:{ all -> 0x010c }
            int r13 = r7 % 255
            byte r13 = (byte) r13     // Catch:{ all -> 0x010c }
            r6[r9] = r13     // Catch:{ all -> 0x010c }
            int r7 = r7 * 17
            java.lang.System.arraycopy(r12, r7, r6, r10, r1)     // Catch:{ all -> 0x010c }
            java.lang.String r12 = "HMPieceProfile"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x010c }
            r13.<init>()     // Catch:{ all -> 0x010c }
            java.lang.String r1 = "patchWrite:"
            r13.append(r1)     // Catch:{ all -> 0x010c }
            java.lang.String r1 = cn.com.xm.bt.c.c.a((byte[]) r6)     // Catch:{ all -> 0x010c }
            r13.append(r1)     // Catch:{ all -> 0x010c }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x010c }
            cn.com.xm.bt.a.a.b(r12, r13)     // Catch:{ all -> 0x010c }
            cn.com.xm.bt.profile.b.c r12 = b(r6)     // Catch:{ all -> 0x010c }
            java.lang.String r13 = "HMPieceProfile"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x010c }
            r1.<init>()     // Catch:{ all -> 0x010c }
            java.lang.String r2 = "response:"
            r1.append(r2)     // Catch:{ all -> 0x010c }
            r1.append(r12)     // Catch:{ all -> 0x010c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x010c }
            cn.com.xm.bt.a.a.b(r13, r1)     // Catch:{ all -> 0x010c }
            if (r12 != 0) goto L_0x00d1
            monitor-exit(r0)
            return r8
        L_0x00d1:
            byte[] r12 = r12.a()     // Catch:{ all -> 0x010c }
            if (r12 == 0) goto L_0x010a
            int r13 = r12.length     // Catch:{ all -> 0x010c }
            r1 = 4
            if (r13 < r1) goto L_0x010a
            byte r13 = r12[r5]     // Catch:{ all -> 0x010c }
            if (r13 != 0) goto L_0x010a
            byte r13 = r12[r9]     // Catch:{ all -> 0x010c }
            byte r2 = r6[r5]     // Catch:{ all -> 0x010c }
            if (r13 == r2) goto L_0x00e6
            goto L_0x010a
        L_0x00e6:
            byte r13 = r12[r10]     // Catch:{ all -> 0x010c }
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r2 = r12.length     // Catch:{ all -> 0x010c }
            r3 = 5
            if (r2 < r3) goto L_0x00f2
            byte r1 = r12[r1]     // Catch:{ all -> 0x010c }
            r5 = r1 & 255(0xff, float:3.57E-43)
        L_0x00f2:
            int r1 = r12.length     // Catch:{ all -> 0x010c }
            r2 = 7
            if (r1 < r2) goto L_0x0103
            byte r1 = r12[r3]     // Catch:{ all -> 0x010c }
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 6
            byte r12 = r12[r2]     // Catch:{ all -> 0x010c }
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << 8
            r4 = r1 | r12
        L_0x0103:
            cn.com.xm.bt.profile.c.b r12 = new cn.com.xm.bt.profile.c.b     // Catch:{ all -> 0x010c }
            r12.<init>(r13, r5, r4)     // Catch:{ all -> 0x010c }
            monitor-exit(r0)
            return r12
        L_0x010a:
            monitor-exit(r0)
            return r8
        L_0x010c:
            r12 = move-exception
            monitor-exit(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.xm.bt.profile.c.a.b(byte[], int):cn.com.xm.bt.profile.c.b");
    }

    private static boolean a(byte[] bArr) {
        if (c == null || b == null) {
            return false;
        }
        return c.c(b, bArr);
    }

    private static cn.com.xm.bt.profile.b.c b(byte[] bArr) {
        if (c == null || b == null) {
            return null;
        }
        if (!c.f(b)) {
            return c.a(b, bArr, 6000);
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        cn.com.xm.bt.profile.b.c cVar = new cn.com.xm.bt.profile.b.c();
        e = new d.b(countDownLatch) {
            private final /* synthetic */ CountDownLatch f$1;

            {
                this.f$1 = r2;
            }

            public final void notify(byte[] bArr) {
                a.a(cn.com.xm.bt.profile.b.c.this, this.f$1, bArr);
            }
        };
        c.c(b, bArr);
        try {
            countDownLatch.await(6000, TimeUnit.MILLISECONDS);
        } catch (Exception unused) {
        }
        return cVar;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(cn.com.xm.bt.profile.b.c cVar, CountDownLatch countDownLatch, byte[] bArr) {
        cVar.b(bArr);
        countDownLatch.countDown();
    }
}
