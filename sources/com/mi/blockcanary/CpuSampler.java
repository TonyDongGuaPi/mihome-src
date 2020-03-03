package com.mi.blockcanary;

import com.mi.blockcanary.internal.BlockInfo;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

class CpuSampler extends AbstractSampler {
    private static final String c = "CpuSampler";
    private static final int d = 1000;
    private static final int f = 10;
    private final int e = ((int) (((float) this.b) * 1.2f));
    private final LinkedHashMap<Long, String> g = new LinkedHashMap<>();
    private int h = 0;
    private long i = 0;
    private long j = 0;
    private long k = 0;
    private long l = 0;
    private long m = 0;
    private long n = 0;

    public CpuSampler(long j2) {
        super(j2);
    }

    public void a() {
        super.a();
        e();
    }

    public String d() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.g) {
            for (Map.Entry next : this.g.entrySet()) {
                sb.append(BlockInfo.f6753a.format(Long.valueOf(((Long) next.getKey()).longValue())));
                sb.append(' ');
                sb.append((String) next.getValue());
                sb.append("\r\n");
            }
        }
        return sb.toString();
    }

    public boolean a(long j2, long j3) {
        if (j3 - j2 <= this.b) {
            return false;
        }
        long j4 = j2 - this.b;
        long j5 = j2 + this.b;
        synchronized (this.g) {
            long j6 = 0;
            for (Map.Entry<Long, String> key : this.g.entrySet()) {
                long longValue = ((Long) key.getKey()).longValue();
                if (j4 < longValue && longValue < j5) {
                    if (j6 != 0 && longValue - j6 > ((long) this.e)) {
                        return true;
                    }
                    j6 = longValue;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0077 A[SYNTHETIC, Splitter:B:32:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007f A[Catch:{ IOException -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0090 A[SYNTHETIC, Splitter:B:43:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0098 A[Catch:{ IOException -> 0x0094 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r9 = this;
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x006c, all -> 0x0069 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x006c, all -> 0x0069 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x006c, all -> 0x0069 }
            java.lang.String r4 = "/proc/stat"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x006c, all -> 0x0069 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x006c, all -> 0x0069 }
            r3 = 1000(0x3e8, float:1.401E-42)
            r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x006c, all -> 0x0069 }
            java.lang.String r2 = r1.readLine()     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            if (r2 != 0) goto L_0x001c
            java.lang.String r2 = ""
        L_0x001c:
            int r4 = r9.h     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            if (r4 != 0) goto L_0x0026
            int r4 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            r9.h = r4     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
        L_0x0026:
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            r7.<init>()     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            java.lang.String r8 = "/proc/"
            r7.append(r8)     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            int r8 = r9.h     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            r7.append(r8)     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            java.lang.String r8 = "/stat"
            r7.append(r8)     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            r6.<init>(r7)     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            r5.<init>(r6)     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            r4.<init>(r5, r3)     // Catch:{ Throwable -> 0x0065, all -> 0x0063 }
            java.lang.String r0 = r4.readLine()     // Catch:{ Throwable -> 0x0061, all -> 0x005f }
            if (r0 != 0) goto L_0x0055
            java.lang.String r0 = ""
        L_0x0055:
            r9.a((java.lang.String) r2, (java.lang.String) r0)     // Catch:{ Throwable -> 0x0061, all -> 0x005f }
            r1.close()     // Catch:{ IOException -> 0x007b }
            r4.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x008a
        L_0x005f:
            r2 = move-exception
            goto L_0x008d
        L_0x0061:
            r2 = move-exception
            goto L_0x0067
        L_0x0063:
            r2 = move-exception
            goto L_0x008e
        L_0x0065:
            r2 = move-exception
            r4 = r0
        L_0x0067:
            r0 = r1
            goto L_0x006e
        L_0x0069:
            r2 = move-exception
            r1 = r0
            goto L_0x008e
        L_0x006c:
            r2 = move-exception
            r4 = r0
        L_0x006e:
            java.lang.String r1 = "CpuSampler"
            java.lang.String r3 = "doSample: "
            android.util.Log.e(r1, r3, r2)     // Catch:{ all -> 0x008b }
            if (r0 == 0) goto L_0x007d
            r0.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x007d
        L_0x007b:
            r0 = move-exception
            goto L_0x0083
        L_0x007d:
            if (r4 == 0) goto L_0x008a
            r4.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x008a
        L_0x0083:
            java.lang.String r1 = "CpuSampler"
            java.lang.String r2 = "doSample: "
            android.util.Log.e(r1, r2, r0)
        L_0x008a:
            return
        L_0x008b:
            r2 = move-exception
            r1 = r0
        L_0x008d:
            r0 = r4
        L_0x008e:
            if (r1 == 0) goto L_0x0096
            r1.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x0096
        L_0x0094:
            r0 = move-exception
            goto L_0x009c
        L_0x0096:
            if (r0 == 0) goto L_0x00a3
            r0.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x00a3
        L_0x009c:
            java.lang.String r1 = "CpuSampler"
            java.lang.String r3 = "doSample: "
            android.util.Log.e(r1, r3, r0)
        L_0x00a3:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.blockcanary.CpuSampler.c():void");
    }

    private void e() {
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
    }

    private void a(String str, String str2) {
        long j2;
        long j3;
        String[] split = str.split(" ");
        if (split.length >= 9) {
            long parseLong = Long.parseLong(split[2]);
            long parseLong2 = Long.parseLong(split[3]);
            long parseLong3 = Long.parseLong(split[4]);
            long parseLong4 = Long.parseLong(split[5]);
            long parseLong5 = Long.parseLong(split[6]);
            long parseLong6 = parseLong2 + parseLong + parseLong3 + parseLong4 + parseLong5 + Long.parseLong(split[7]) + Long.parseLong(split[8]);
            String[] split2 = str2.split(" ");
            if (split2.length >= 17) {
                long parseLong7 = Long.parseLong(split2[13]) + Long.parseLong(split2[14]) + Long.parseLong(split2[15]) + Long.parseLong(split2[16]);
                if (this.m != 0) {
                    StringBuilder sb = new StringBuilder();
                    long j4 = parseLong4 - this.k;
                    j3 = parseLong4;
                    long j5 = parseLong6 - this.m;
                    j2 = parseLong6;
                    sb.append("cpu:");
                    sb.append(((j5 - j4) * 100) / j5);
                    sb.append("% ");
                    sb.append("app:");
                    sb.append(((parseLong7 - this.n) * 100) / j5);
                    sb.append("% ");
                    sb.append(Operators.ARRAY_START_STR);
                    sb.append("user:");
                    sb.append(((parseLong - this.i) * 100) / j5);
                    sb.append("% ");
                    sb.append("system:");
                    sb.append(((parseLong3 - this.j) * 100) / j5);
                    sb.append("% ");
                    sb.append("ioWait:");
                    sb.append(((parseLong5 - this.l) * 100) / j5);
                    sb.append("% ]");
                    synchronized (this.g) {
                        this.g.put(Long.valueOf(System.currentTimeMillis()), sb.toString());
                        if (this.g.size() > 10) {
                            Iterator<Map.Entry<Long, String>> it = this.g.entrySet().iterator();
                            if (it.hasNext()) {
                                this.g.remove((Long) it.next().getKey());
                            }
                        }
                    }
                } else {
                    j2 = parseLong6;
                    j3 = parseLong4;
                }
                this.i = parseLong;
                this.j = parseLong3;
                this.k = j3;
                this.l = parseLong5;
                this.m = j2;
                this.n = parseLong7;
            }
        }
    }
}
