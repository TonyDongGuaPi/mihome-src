package com.amap.api.services.a;

import android.content.Context;
import java.lang.ref.WeakReference;

public class dp {
    public static void a(Context context, dj djVar, String str, int i, int i2, String str2) {
        djVar.f4405a = cj.c(context, str);
        djVar.d = i;
        djVar.b = (long) i2;
        djVar.c = str2;
    }

    public static dj a(WeakReference<dj> weakReference) {
        if (weakReference == null || weakReference.get() == null) {
            weakReference = new WeakReference<>(new dj());
        }
        return (dj) weakReference.get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0053, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0054, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0056, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0057, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0056 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:24:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x006d A[SYNTHETIC, Splitter:B:56:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0077 A[SYNTHETIC, Splitter:B:61:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0083 A[SYNTHETIC, Splitter:B:68:0x0083] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x008d A[SYNTHETIC, Splitter:B:73:0x008d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static byte[] a(com.amap.api.services.a.cw r4, java.lang.String r5, boolean r6) {
        /*
            r0 = 0
            byte[] r1 = new byte[r0]
            r2 = 0
            com.amap.api.services.a.cw$b r3 = r4.a((java.lang.String) r5)     // Catch:{ Throwable -> 0x0061, all -> 0x005e }
            if (r3 != 0) goto L_0x0015
            if (r3 == 0) goto L_0x0014
            r3.close()     // Catch:{ Throwable -> 0x0010 }
            goto L_0x0014
        L_0x0010:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0014:
            return r1
        L_0x0015:
            java.io.InputStream r0 = r3.a(r0)     // Catch:{ Throwable -> 0x005c }
            if (r0 != 0) goto L_0x0030
            if (r0 == 0) goto L_0x0025
            r0.close()     // Catch:{ Throwable -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0025:
            if (r3 == 0) goto L_0x002f
            r3.close()     // Catch:{ Throwable -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x002f:
            return r1
        L_0x0030:
            int r2 = r0.available()     // Catch:{ Throwable -> 0x0059, all -> 0x0056 }
            byte[] r2 = new byte[r2]     // Catch:{ Throwable -> 0x0059, all -> 0x0056 }
            r0.read(r2)     // Catch:{ Throwable -> 0x0053, all -> 0x0056 }
            if (r6 == 0) goto L_0x003e
            r4.c((java.lang.String) r5)     // Catch:{ Throwable -> 0x0053, all -> 0x0056 }
        L_0x003e:
            if (r0 == 0) goto L_0x0048
            r0.close()     // Catch:{ Throwable -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0048:
            if (r3 == 0) goto L_0x0052
            r3.close()     // Catch:{ Throwable -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0052:
            return r2
        L_0x0053:
            r4 = move-exception
            r1 = r2
            goto L_0x005a
        L_0x0056:
            r4 = move-exception
            r2 = r0
            goto L_0x0081
        L_0x0059:
            r4 = move-exception
        L_0x005a:
            r2 = r0
            goto L_0x0063
        L_0x005c:
            r4 = move-exception
            goto L_0x0063
        L_0x005e:
            r4 = move-exception
            r3 = r2
            goto L_0x0081
        L_0x0061:
            r4 = move-exception
            r3 = r2
        L_0x0063:
            java.lang.String r5 = "sui"
            java.lang.String r6 = "rdS"
            com.amap.api.services.a.cl.c(r4, r5, r6)     // Catch:{ all -> 0x0080 }
            if (r2 == 0) goto L_0x0075
            r2.close()     // Catch:{ Throwable -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0075:
            if (r3 == 0) goto L_0x007f
            r3.close()     // Catch:{ Throwable -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x007f:
            return r1
        L_0x0080:
            r4 = move-exception
        L_0x0081:
            if (r2 == 0) goto L_0x008b
            r2.close()     // Catch:{ Throwable -> 0x0087 }
            goto L_0x008b
        L_0x0087:
            r5 = move-exception
            r5.printStackTrace()
        L_0x008b:
            if (r3 == 0) goto L_0x0095
            r3.close()     // Catch:{ Throwable -> 0x0091 }
            goto L_0x0095
        L_0x0091:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0095:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.dp.a(com.amap.api.services.a.cw, java.lang.String, boolean):byte[]");
    }

    public static String a() {
        return bz.a(System.currentTimeMillis());
    }

    public static String a(Context context, by byVar) {
        StringBuilder sb = new StringBuilder();
        try {
            String g = bt.g(context);
            sb.append("\"sim\":\"");
            sb.append(g);
            sb.append("\",\"sdkversion\":\"");
            sb.append(byVar.c());
            sb.append("\",\"product\":\"");
            sb.append(byVar.a());
            sb.append("\",\"ed\":\"");
            sb.append(byVar.d());
            sb.append("\",\"nt\":\"");
            sb.append(bt.e(context));
            sb.append("\",\"np\":\"");
            sb.append(bt.c(context));
            sb.append("\",\"mnc\":\"");
            sb.append(bt.d(context));
            sb.append("\",\"ant\":\"");
            sb.append(bt.f(context));
            sb.append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    public static String a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append(",");
        stringBuffer.append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }
}
