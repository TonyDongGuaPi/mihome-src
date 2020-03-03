package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.util.Map;

public final class eo extends bh {
    Map<String, String> f = null;
    String g = "";
    byte[] h = null;
    byte[] i = null;
    boolean j = false;
    String k = null;
    Map<String, String> l = null;
    boolean m = false;
    private String n = "";

    public eo(Context context, ac acVar) {
        super(context, acVar);
    }

    public final Map<String, String> a() {
        return this.f;
    }

    public final void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.n = str;
        } else {
            this.n = "";
        }
    }

    public final Map<String, String> b() {
        return this.l;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0030 A[SYNTHETIC, Splitter:B:23:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x003b A[SYNTHETIC, Splitter:B:28:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(byte[] r3) {
        /*
            r2 = this;
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x002a }
            r1.<init>()     // Catch:{ Throwable -> 0x002a }
            if (r3 == 0) goto L_0x0018
            byte[] r0 = a(r3)     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            r1.write(r0)     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            r1.write(r3)     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            goto L_0x0018
        L_0x0013:
            r3 = move-exception
            goto L_0x0039
        L_0x0015:
            r3 = move-exception
            r0 = r1
            goto L_0x002b
        L_0x0018:
            byte[] r3 = r1.toByteArray()     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            r2.i = r3     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            r1.close()     // Catch:{ IOException -> 0x0022 }
            return
        L_0x0022:
            r3 = move-exception
            r3.printStackTrace()
            return
        L_0x0027:
            r3 = move-exception
            r1 = r0
            goto L_0x0039
        L_0x002a:
            r3 = move-exception
        L_0x002b:
            r3.printStackTrace()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x0038
            r0.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0038:
            return
        L_0x0039:
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0043:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eo.b(byte[]):void");
    }

    public final String c() {
        return this.g;
    }

    public final boolean e() {
        return this.j;
    }

    public final String f() {
        return this.k;
    }

    public final byte[] h() {
        return this.h;
    }

    public final byte[] i() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public final boolean j() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public final String k() {
        return this.n;
    }
}
