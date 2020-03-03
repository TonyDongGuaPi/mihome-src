package com.loc;

import android.content.Context;
import java.lang.ref.WeakReference;

public final class bs {
    public static bl a(WeakReference<bl> weakReference) {
        if (weakReference == null || weakReference.get() == null) {
            weakReference = new WeakReference<>(new bl());
        }
        return (bl) weakReference.get();
    }

    public static String a(Context context, ac acVar) {
        StringBuilder sb = new StringBuilder();
        try {
            String f = x.f(context);
            sb.append("\"sim\":\"");
            sb.append(f);
            sb.append("\",\"sdkversion\":\"");
            sb.append(acVar.c());
            sb.append("\",\"product\":\"");
            sb.append(acVar.a());
            sb.append("\",\"ed\":\"");
            sb.append(acVar.d());
            sb.append("\",\"nt\":\"");
            sb.append(x.d(context));
            sb.append("\",\"np\":\"");
            sb.append(x.b(context));
            sb.append("\",\"mnc\":\"");
            sb.append(x.c(context));
            sb.append("\",\"ant\":\"");
            sb.append(x.e(context));
            sb.append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    public static void a(Context context, bl blVar, String str, int i, int i2, String str2) {
        blVar.f6514a = ao.c(context, str);
        blVar.d = i;
        blVar.b = (long) i2;
        blVar.c = str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x006f A[SYNTHETIC, Splitter:B:54:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0079 A[SYNTHETIC, Splitter:B:59:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0085 A[SYNTHETIC, Splitter:B:66:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x008f A[SYNTHETIC, Splitter:B:71:0x008f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static byte[] a(com.loc.bc r5, java.lang.String r6) {
        /*
            r0 = 0
            byte[] r0 = new byte[r0]
            r1 = 0
            com.loc.bc$b r5 = r5.a((java.lang.String) r6)     // Catch:{ Throwable -> 0x0061, all -> 0x005d }
            if (r5 != 0) goto L_0x0015
            if (r5 == 0) goto L_0x0014
            r5.close()     // Catch:{ Throwable -> 0x0010 }
            goto L_0x0014
        L_0x0010:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0014:
            return r0
        L_0x0015:
            java.io.InputStream r6 = r5.a()     // Catch:{ Throwable -> 0x0058, all -> 0x0055 }
            if (r6 != 0) goto L_0x0030
            if (r6 == 0) goto L_0x0025
            r6.close()     // Catch:{ Throwable -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0025:
            if (r5 == 0) goto L_0x002f
            r5.close()     // Catch:{ Throwable -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r5 = move-exception
            r5.printStackTrace()
        L_0x002f:
            return r0
        L_0x0030:
            int r1 = r6.available()     // Catch:{ Throwable -> 0x0053 }
            byte[] r1 = new byte[r1]     // Catch:{ Throwable -> 0x0053 }
            r6.read(r1)     // Catch:{ Throwable -> 0x004e }
            if (r6 == 0) goto L_0x0043
            r6.close()     // Catch:{ Throwable -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0043:
            if (r5 == 0) goto L_0x004d
            r5.close()     // Catch:{ Throwable -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r5 = move-exception
            r5.printStackTrace()
        L_0x004d:
            return r1
        L_0x004e:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0065
        L_0x0053:
            r1 = move-exception
            goto L_0x0065
        L_0x0055:
            r0 = move-exception
            r6 = r1
            goto L_0x0083
        L_0x0058:
            r6 = move-exception
            r4 = r1
            r1 = r6
            r6 = r4
            goto L_0x0065
        L_0x005d:
            r0 = move-exception
            r5 = r1
            r6 = r5
            goto L_0x0083
        L_0x0061:
            r5 = move-exception
            r6 = r1
            r1 = r5
            r5 = r6
        L_0x0065:
            java.lang.String r2 = "sui"
            java.lang.String r3 = "rdS"
            com.loc.aq.b((java.lang.Throwable) r1, (java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x0082 }
            if (r6 == 0) goto L_0x0077
            r6.close()     // Catch:{ Throwable -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0077:
            if (r5 == 0) goto L_0x0081
            r5.close()     // Catch:{ Throwable -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0081:
            return r0
        L_0x0082:
            r0 = move-exception
        L_0x0083:
            if (r6 == 0) goto L_0x008d
            r6.close()     // Catch:{ Throwable -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r6 = move-exception
            r6.printStackTrace()
        L_0x008d:
            if (r5 == 0) goto L_0x0097
            r5.close()     // Catch:{ Throwable -> 0x0093 }
            goto L_0x0097
        L_0x0093:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0097:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bs.a(com.loc.bc, java.lang.String):byte[]");
    }
}
