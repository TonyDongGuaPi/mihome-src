package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;

public class dn {

    /* renamed from: a  reason: collision with root package name */
    private Context f4410a;
    private String b;
    private String c;
    private String d;
    private String e;

    public dn(Context context, String str, String str2, String str3) throws bo {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new bo(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
        }
        this.f4410a = context.getApplicationContext();
        this.c = str;
        this.d = str2;
        this.b = str3;
    }

    public void a(String str) throws bo {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new bo(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
        }
        this.e = str;
    }

    public byte[] a(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[]{0, 0};
        }
        byte[] a2 = bz.a(this.e);
        if (a2 == null) {
            return new byte[]{0, 0};
        }
        int length = a2.length;
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0053, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0055, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006b, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0076, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0077, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x002d */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0053 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0009] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066 A[SYNTHETIC, Splitter:B:24:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0072 A[SYNTHETIC, Splitter:B:30:0x0072] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a() {
        /*
            r8 = this;
            r0 = 0
            byte[] r1 = new byte[r0]
            r2 = 0
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x005b }
            r3.<init>()     // Catch:{ Throwable -> 0x005b }
            java.lang.String r2 = r8.c     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            com.amap.api.services.a.bz.a((java.io.ByteArrayOutputStream) r3, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            java.lang.String r2 = r8.d     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            com.amap.api.services.a.bz.a((java.io.ByteArrayOutputStream) r3, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            java.lang.String r2 = r8.b     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            com.amap.api.services.a.bz.a((java.io.ByteArrayOutputStream) r3, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            android.content.Context r2 = r8.f4410a     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            int r2 = com.amap.api.services.a.bt.q(r2)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            com.amap.api.services.a.bz.a((java.io.ByteArrayOutputStream) r3, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x002d, all -> 0x0053 }
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 / r6
            int r0 = (int) r4
        L_0x002d:
            byte[] r0 = r8.a((int) r0)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            r3.write(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            java.lang.String r0 = r8.e     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            byte[] r0 = r8.b(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            r3.write(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            java.lang.String r0 = r8.e     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            byte[] r0 = com.amap.api.services.a.bz.a((java.lang.String) r0)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            r3.write(r0)     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            byte[] r0 = r3.toByteArray()     // Catch:{ Throwable -> 0x0055, all -> 0x0053 }
            r3.close()     // Catch:{ Throwable -> 0x004e }
            goto L_0x006f
        L_0x004e:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x006f
        L_0x0053:
            r0 = move-exception
            goto L_0x0070
        L_0x0055:
            r0 = move-exception
            r2 = r3
            goto L_0x005c
        L_0x0058:
            r0 = move-exception
            r3 = r2
            goto L_0x0070
        L_0x005b:
            r0 = move-exception
        L_0x005c:
            java.lang.String r3 = "se"
            java.lang.String r4 = "tds"
            com.amap.api.services.a.cl.c(r0, r3, r4)     // Catch:{ all -> 0x0058 }
            if (r2 == 0) goto L_0x006e
            r2.close()     // Catch:{ Throwable -> 0x006a }
            goto L_0x006e
        L_0x006a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006e:
            r0 = r1
        L_0x006f:
            return r0
        L_0x0070:
            if (r3 == 0) goto L_0x007a
            r3.close()     // Catch:{ Throwable -> 0x0076 }
            goto L_0x007a
        L_0x0076:
            r1 = move-exception
            r1.printStackTrace()
        L_0x007a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.dn.a():byte[]");
    }
}
