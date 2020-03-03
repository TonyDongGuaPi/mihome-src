package com.tmall.wireless.vaf.expr.engine.executor;

import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;

public abstract class BinExecutor extends ArithExecutor {
    private static final String j = "BinExecutor_TMTEST";

    /* access modifiers changed from: protected */
    public int a(Data data, float f, float f2) {
        return 2;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, int i) {
        return 2;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, String str) {
        return 2;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, int i, float f) {
        return 2;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, int i, int i2) {
        return 2;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, int i, String str) {
        return 2;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, String str, float f) {
        return 2;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, String str, int i) {
        return 2;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, String str, String str2) {
        return 2;
    }

    public int a(Object obj) {
        Data data;
        int a2 = super.a(obj);
        Data data2 = null;
        switch (this.r.d()) {
            case 0:
                data2 = a(0);
                data = a(0);
                this.h = this.r.d();
                break;
            case 1:
                data2 = a(0);
                data = a(1);
                this.h = this.r.d();
                break;
            case 2:
                data2 = a(0);
                data = a(2);
                this.h = this.r.d();
                break;
            case 3:
                data2 = a(0);
                data = a(3);
                this.h = this.r.d();
                break;
            case 4:
                data2 = a(0);
                data = a(4);
                break;
            case 5:
                data2 = a(1);
                data = a(0);
                this.h = this.r.d();
                break;
            case 6:
                data2 = a(1);
                data = a(4);
                break;
            case 7:
                data2 = a(2);
                data = a(0);
                this.h = this.r.d();
                break;
            case 8:
                data2 = a(2);
                data = a(4);
                break;
            case 9:
                data2 = a(3);
                data = a(0);
                this.h = this.r.d();
                break;
            case 10:
                data2 = a(3);
                data = a(4);
                break;
            case 11:
                data2 = a(4);
                data = a(0);
                break;
            case 12:
                data2 = a(4);
                data = a(1);
                break;
            case 13:
                data2 = a(4);
                data = a(2);
                break;
            case 14:
                data2 = a(4);
                data = a(3);
                break;
            case 15:
                data2 = a(4);
                data = a(4);
                break;
            default:
                data = null;
                break;
        }
        if (data2 != null && data != null) {
            return a(data2, data);
        }
        Log.e(j, "read data failed");
        return a2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(com.tmall.wireless.vaf.expr.engine.data.Data r6, com.tmall.wireless.vaf.expr.engine.data.Data r7) {
        /*
            r5 = this;
            com.tmall.wireless.vaf.expr.engine.RegisterManager r0 = r5.s
            int r1 = r5.h
            com.tmall.wireless.vaf.expr.engine.data.Data r0 = r0.a(r1)
            r1 = 2
            if (r0 == 0) goto L_0x0118
            r2 = 1
            int r3 = r6.g
            switch(r3) {
                case 1: goto L_0x00b3;
                case 2: goto L_0x0070;
                case 3: goto L_0x0029;
                default: goto L_0x0011;
            }
        L_0x0011:
            java.lang.String r0 = "BinExecutor_TMTEST"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "value1 invalidate type:"
            r3.append(r4)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r0, r3)
            goto L_0x00f6
        L_0x0029:
            int r3 = r7.g
            switch(r3) {
                case 1: goto L_0x0062;
                case 2: goto L_0x0054;
                case 3: goto L_0x0046;
                default: goto L_0x002e;
            }
        L_0x002e:
            java.lang.String r0 = "BinExecutor_TMTEST"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "value2 invalidate type:"
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r0, r3)
            goto L_0x00f6
        L_0x0046:
            java.lang.String r2 = r6.d()
            java.lang.String r3 = r7.d()
            int r0 = r5.a((com.tmall.wireless.vaf.expr.engine.data.Data) r0, (java.lang.String) r2, (java.lang.String) r3)
            goto L_0x00f7
        L_0x0054:
            java.lang.String r2 = r6.d()
            float r3 = r7.c()
            int r0 = r5.a((com.tmall.wireless.vaf.expr.engine.data.Data) r0, (java.lang.String) r2, (float) r3)
            goto L_0x00f7
        L_0x0062:
            java.lang.String r2 = r6.d()
            int r3 = r7.b()
            int r0 = r5.a((com.tmall.wireless.vaf.expr.engine.data.Data) r0, (java.lang.String) r2, (int) r3)
            goto L_0x00f7
        L_0x0070:
            int r3 = r7.g
            switch(r3) {
                case 1: goto L_0x00a6;
                case 2: goto L_0x0099;
                case 3: goto L_0x008c;
                default: goto L_0x0075;
            }
        L_0x0075:
            java.lang.String r0 = "BinExecutor_TMTEST"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "value2 invalidate type:"
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r0, r3)
            goto L_0x00f6
        L_0x008c:
            float r2 = r6.c()
            java.lang.String r3 = r7.d()
            int r0 = r5.a((com.tmall.wireless.vaf.expr.engine.data.Data) r0, (float) r2, (java.lang.String) r3)
            goto L_0x00f7
        L_0x0099:
            float r2 = r6.c()
            float r3 = r7.c()
            int r0 = r5.a((com.tmall.wireless.vaf.expr.engine.data.Data) r0, (float) r2, (float) r3)
            goto L_0x00f7
        L_0x00a6:
            float r2 = r6.c()
            int r3 = r7.b()
            int r0 = r5.a((com.tmall.wireless.vaf.expr.engine.data.Data) r0, (float) r2, (int) r3)
            goto L_0x00f7
        L_0x00b3:
            int r3 = r7.g
            switch(r3) {
                case 1: goto L_0x00e9;
                case 2: goto L_0x00dc;
                case 3: goto L_0x00cf;
                default: goto L_0x00b8;
            }
        L_0x00b8:
            java.lang.String r0 = "BinExecutor_TMTEST"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "value2 invalidate type:"
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r0, r3)
            goto L_0x00f6
        L_0x00cf:
            int r2 = r6.b()
            java.lang.String r3 = r7.d()
            int r0 = r5.a((com.tmall.wireless.vaf.expr.engine.data.Data) r0, (int) r2, (java.lang.String) r3)
            goto L_0x00f7
        L_0x00dc:
            int r2 = r6.b()
            float r3 = r7.c()
            int r0 = r5.a((com.tmall.wireless.vaf.expr.engine.data.Data) r0, (int) r2, (float) r3)
            goto L_0x00f7
        L_0x00e9:
            int r2 = r6.b()
            int r3 = r7.b()
            int r0 = r5.a((com.tmall.wireless.vaf.expr.engine.data.Data) r0, (int) r2, (int) r3)
            goto L_0x00f7
        L_0x00f6:
            r0 = 1
        L_0x00f7:
            if (r1 != r0) goto L_0x0119
            java.lang.String r1 = "BinExecutor_TMTEST"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "type invalidate data1:"
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = "  data2:"
            r2.append(r6)
            r2.append(r7)
            java.lang.String r6 = r2.toString()
            android.util.Log.e(r1, r6)
            goto L_0x0119
        L_0x0118:
            r0 = 2
        L_0x0119:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tmall.wireless.vaf.expr.engine.executor.BinExecutor.a(com.tmall.wireless.vaf.expr.engine.data.Data, com.tmall.wireless.vaf.expr.engine.data.Data):int");
    }
}
