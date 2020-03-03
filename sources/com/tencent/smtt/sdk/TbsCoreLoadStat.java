package com.tencent.smtt.sdk;

import android.content.Context;
import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.utils.TbsLog;
import java.util.Arrays;

public class TbsCoreLoadStat {
    private static TbsCoreLoadStat d = null;
    public static volatile int mLoadErrorCode = -1;

    /* renamed from: a  reason: collision with root package name */
    private TbsSequenceQueue f9089a = null;
    private boolean b = false;
    private final int c = 3;

    public class TbsSequenceQueue {
        private int b;
        private int c;
        private int[] d;
        private int e;
        private int f;

        public TbsSequenceQueue() {
            this.b = 10;
            this.e = 0;
            this.f = 0;
            this.c = this.b;
            this.d = new int[this.c];
        }

        public TbsSequenceQueue(int i, int i2) {
            this.b = 10;
            this.e = 0;
            this.f = 0;
            this.c = i2;
            this.d = new int[this.c];
            this.d[0] = i;
            this.f++;
        }

        public void add(int i) {
            if (this.f <= this.c - 1) {
                int[] iArr = this.d;
                int i2 = this.f;
                this.f = i2 + 1;
                iArr[i2] = i;
                return;
            }
            throw new IndexOutOfBoundsException("sequeue is full");
        }

        public void clear() {
            Arrays.fill(this.d, 0);
            this.e = 0;
            this.f = 0;
        }

        public int element() {
            if (!empty()) {
                return this.d[this.e];
            }
            throw new IndexOutOfBoundsException("sequeue is null");
        }

        public boolean empty() {
            return this.f == this.e;
        }

        public int length() {
            return this.f - this.e;
        }

        public int remove() {
            if (!empty()) {
                int i = this.d[this.e];
                int[] iArr = this.d;
                int i2 = this.e;
                this.e = i2 + 1;
                iArr[i2] = 0;
                return i;
            }
            throw new IndexOutOfBoundsException("sequeue is null");
        }

        public String toString() {
            if (empty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder(Operators.ARRAY_START_STR);
            for (int i = this.e; i < this.f; i++) {
                sb.append(String.valueOf(this.d[i]) + ",");
            }
            int length = sb.length();
            StringBuilder delete = sb.delete(length - 1, length);
            delete.append(Operators.ARRAY_END_STR);
            return delete.toString();
        }
    }

    private TbsCoreLoadStat() {
    }

    public static TbsCoreLoadStat getInstance() {
        if (d == null) {
            d = new TbsCoreLoadStat();
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.f9089a != null) {
            this.f9089a.clear();
        }
        this.b = false;
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, int i) {
        a(context, i, (Throwable) null);
        TbsLog.e(TbsListener.tag_load_error, "" + i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r6, int r7, java.lang.Throwable r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = mLoadErrorCode     // Catch:{ all -> 0x006b }
            r1 = -1
            if (r0 != r1) goto L_0x0047
            mLoadErrorCode = r7     // Catch:{ all -> 0x006b }
            r0 = 998(0x3e6, float:1.398E-42)
            java.lang.String r1 = "code=%d,desc=%s"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x006b }
            r3 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x006b }
            r2[r3] = r4     // Catch:{ all -> 0x006b }
            r3 = 1
            java.lang.String r4 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x006b }
            r2[r3] = r4     // Catch:{ all -> 0x006b }
            com.tencent.smtt.utils.TbsLog.addLog(r0, r1, r2)     // Catch:{ all -> 0x006b }
            if (r8 == 0) goto L_0x002a
            com.tencent.smtt.sdk.TbsLogReport r6 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r6)     // Catch:{ all -> 0x006b }
            r6.b((int) r7, (java.lang.Throwable) r8)     // Catch:{ all -> 0x006b }
            goto L_0x0045
        L_0x002a:
            java.lang.String r6 = "TbsCoreLoadStat"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            r8.<init>()     // Catch:{ all -> 0x006b }
            java.lang.String r0 = "setLoadErrorCode :: error is null with errorCode: "
            r8.append(r0)     // Catch:{ all -> 0x006b }
            r8.append(r7)     // Catch:{ all -> 0x006b }
            java.lang.String r7 = "; Check & correct it!"
            r8.append(r7)     // Catch:{ all -> 0x006b }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x006b }
            com.tencent.smtt.utils.TbsLog.e(r6, r7)     // Catch:{ all -> 0x006b }
        L_0x0045:
            monitor-exit(r5)
            return
        L_0x0047:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            java.lang.String r8 = "setLoadErrorCode :: error("
            r6.<init>(r8)     // Catch:{ all -> 0x006b }
            int r8 = mLoadErrorCode     // Catch:{ all -> 0x006b }
            r6.append(r8)     // Catch:{ all -> 0x006b }
            java.lang.String r8 = ") was already reported; "
            r6.append(r8)     // Catch:{ all -> 0x006b }
            r6.append(r7)     // Catch:{ all -> 0x006b }
            java.lang.String r7 = " is duplicated. Try to remove it!"
            r6.append(r7)     // Catch:{ all -> 0x006b }
            java.lang.String r7 = "TbsCoreLoadStat"
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x006b }
            com.tencent.smtt.utils.TbsLog.w(r7, r6)     // Catch:{ all -> 0x006b }
            monitor-exit(r5)
            return
        L_0x006b:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsCoreLoadStat.a(android.content.Context, int, java.lang.Throwable):void");
    }
}
