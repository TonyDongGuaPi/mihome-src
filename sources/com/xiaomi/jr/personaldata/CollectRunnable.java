package com.xiaomi.jr.personaldata;

import android.content.Context;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.common.utils.PreferenceUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class CollectRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    protected static final long f11001a = 2592000000L;
    protected static final long b = 10000;
    private static final String c = "CollectRunnable";
    private static long f = Long.MIN_VALUE;
    private final String d = (c() + "EndTime");
    private final String e = (c() + "Progress");
    private Context g;
    private boolean h;
    private SimpleDateFormat i = new SimpleDateFormat("MM-dd hh:mm:ss", Locale.getDefault());

    public static class CollectResult {

        /* renamed from: a  reason: collision with root package name */
        public String f11002a;
        public long b;
    }

    /* access modifiers changed from: package-private */
    public abstract CollectResult a(long j, long j2) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract String[] a();

    /* access modifiers changed from: package-private */
    public abstract int b();

    /* access modifiers changed from: package-private */
    public abstract String c();

    /* access modifiers changed from: package-private */
    public abstract long d();

    /* access modifiers changed from: package-private */
    public abstract long e();

    public CollectRunnable(Context context) {
        this.g = context;
    }

    public Context f() {
        return this.g;
    }

    public boolean g() {
        return this.h;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009a, code lost:
        if (r5 <= r7) goto L_0x009d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01d5 A[EDGE_INSN: B:58:0x01d5->B:52:0x01d5 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r15 = this;
            r0 = 1
            r15.h = r0
            long r1 = r15.e()
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r1 = 0
            if (r5 != 0) goto L_0x0013
            r2 = 1
            goto L_0x0014
        L_0x0013:
            r2 = 0
        L_0x0014:
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r15.i()
            long r7 = f
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0031
            com.xiaomi.jr.personaldata.ApiHolder$IApi r5 = com.xiaomi.jr.personaldata.ApiHolder.a()
            int r6 = r15.b()
            long r5 = r5.a((int) r6)
            r15.c(r5)
        L_0x0031:
            long r7 = r15.h()
            long r9 = f
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x003f
            r15.b(r5)
            r7 = r5
        L_0x003f:
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x0045
            r9 = 1
            goto L_0x0046
        L_0x0045:
            r9 = 0
        L_0x0046:
            r10 = 0
            long r10 = r3 - r7
            long r12 = r15.d()
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 < 0) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r0 = 0
        L_0x0053:
            if (r0 == 0) goto L_0x005e
            r15.c(r7)
            r15.b(r3)
            r5 = r7
            r9 = 0
            goto L_0x005f
        L_0x005e:
            r3 = r7
        L_0x005f:
            if (r9 != 0) goto L_0x008b
            if (r0 != 0) goto L_0x008b
            java.lang.String r0 = "TestData"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "DONT collect "
            r2.append(r3)
            java.lang.String r3 = r15.c()
            r2.append(r3)
            java.lang.String r3 = " due to no break point data and not reaching new period. progress="
            r2.append(r3)
            java.lang.String r3 = r15.a(r5)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.xiaomi.jr.common.utils.MifiLog.c(r0, r2)
            goto L_0x01ef
        L_0x008b:
            if (r9 == 0) goto L_0x008f
        L_0x008d:
            r7 = r5
            goto L_0x009d
        L_0x008f:
            long r7 = r15.e()
            long r7 = r3 - r7
            if (r2 == 0) goto L_0x0098
            goto L_0x009d
        L_0x0098:
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x009d
            goto L_0x008d
        L_0x009d:
            java.lang.String r0 = "TestData"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r10 = "START collect "
            r2.append(r10)
            java.lang.String r10 = r15.c()
            r2.append(r10)
            java.lang.String r10 = "... progress="
            r2.append(r10)
            java.lang.String r5 = r15.a(r5)
            r2.append(r5)
            java.lang.String r5 = ", startTime="
            r2.append(r5)
            java.lang.String r5 = r15.a(r7)
            r2.append(r5)
            java.lang.String r5 = ", endTime="
            r2.append(r5)
            java.lang.String r5 = r15.a(r3)
            r2.append(r5)
            java.lang.String r5 = ". type="
            r2.append(r5)
            if (r9 == 0) goto L_0x00de
            java.lang.String r5 = "break point resume"
            goto L_0x00e0
        L_0x00de:
            java.lang.String r5 = "start new period"
        L_0x00e0:
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            com.xiaomi.jr.common.utils.MifiLog.c(r0, r2)
        L_0x00ea:
            int r0 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x01d5
            java.lang.String r0 = "TestData"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ae }
            r2.<init>()     // Catch:{ Exception -> 0x01ae }
            java.lang.String r5 = "collect "
            r2.append(r5)     // Catch:{ Exception -> 0x01ae }
            java.lang.String r5 = r15.c()     // Catch:{ Exception -> 0x01ae }
            r2.append(r5)     // Catch:{ Exception -> 0x01ae }
            java.lang.String r5 = ", startTime="
            r2.append(r5)     // Catch:{ Exception -> 0x01ae }
            java.lang.String r5 = r15.a(r7)     // Catch:{ Exception -> 0x01ae }
            r2.append(r5)     // Catch:{ Exception -> 0x01ae }
            java.lang.String r5 = ", endTime="
            r2.append(r5)     // Catch:{ Exception -> 0x01ae }
            java.lang.String r5 = r15.a(r3)     // Catch:{ Exception -> 0x01ae }
            r2.append(r5)     // Catch:{ Exception -> 0x01ae }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x01ae }
            com.xiaomi.jr.common.utils.MifiLog.c(r0, r2)     // Catch:{ Exception -> 0x01ae }
            com.xiaomi.jr.personaldata.CollectRunnable$CollectResult r0 = r15.a(r7, r3)     // Catch:{ Exception -> 0x01ae }
            if (r0 == 0) goto L_0x018e
            long r5 = r0.b
            r7 = 0
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x012f
            goto L_0x018e
        L_0x012f:
            com.xiaomi.jr.personaldata.ApiHolder$IApi r2 = com.xiaomi.jr.personaldata.ApiHolder.a()
            int r5 = r15.b()
            java.lang.String r6 = r0.f11002a
            long r7 = r0.b
            boolean r2 = r2.a(r5, r6, r7)
            java.lang.String r5 = "TestData"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "upload "
            r6.append(r7)
            java.lang.String r7 = r15.c()
            r6.append(r7)
            java.lang.String r7 = ", progress="
            r6.append(r7)
            long r7 = r0.b
            java.lang.String r7 = r15.a(r7)
            r6.append(r7)
            java.lang.String r7 = ", dataSize="
            r6.append(r7)
            java.lang.String r7 = r0.f11002a
            if (r7 == 0) goto L_0x0170
            java.lang.String r7 = r0.f11002a
            int r7 = r7.length()
            goto L_0x0171
        L_0x0170:
            r7 = 0
        L_0x0171:
            r6.append(r7)
            java.lang.String r7 = ". suc="
            r6.append(r7)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            com.xiaomi.jr.common.utils.MifiLog.c(r5, r6)
            if (r2 == 0) goto L_0x01d5
            long r5 = r0.b
            r15.c(r5)
            long r7 = r0.b
            goto L_0x00ea
        L_0x018e:
            java.lang.String r0 = "TestData"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "no more "
            r2.append(r3)
            java.lang.String r3 = r15.c()
            r2.append(r3)
            java.lang.String r3 = " to upload."
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.xiaomi.jr.common.utils.MifiLog.d(r0, r2)
            goto L_0x01d5
        L_0x01ae:
            r0 = move-exception
            java.lang.String r2 = "TestData"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "collect "
            r3.append(r4)
            java.lang.String r4 = r15.c()
            r3.append(r4)
            java.lang.String r4 = " exception: "
            r3.append(r4)
            java.lang.String r0 = r0.getMessage()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.xiaomi.jr.common.utils.MifiLog.e(r2, r0)
        L_0x01d5:
            java.lang.String r0 = "TestData"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "END collect "
            r2.append(r3)
            java.lang.String r3 = r15.c()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.xiaomi.jr.common.utils.MifiLog.c(r0, r2)
        L_0x01ef:
            r15.h = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.personaldata.CollectRunnable.run():void");
    }

    private String a(long j) {
        if (j <= 0) {
            return String.valueOf(j);
        }
        return String.valueOf(j) + Operators.BRACKET_START_STR + this.i.format(new Date(j)) + Operators.BRACKET_END_STR;
    }

    private long h() {
        return PreferenceUtils.b(this.g, Constants.e, this.d, f);
    }

    private void b(long j) {
        PreferenceUtils.a(this.g, Constants.e, this.d, j);
    }

    private long i() {
        return PreferenceUtils.b(this.g, Constants.e, this.e, f);
    }

    private void c(long j) {
        PreferenceUtils.a(this.g, Constants.e, this.e, j);
    }
}
