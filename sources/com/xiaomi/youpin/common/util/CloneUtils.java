package com.xiaomi.youpin.common.util;

import java.io.Serializable;

public final class CloneUtils {
    public static <T> T a(Serializable serializable) {
        if (serializable == null) {
            return null;
        }
        return a(b(serializable));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0029 A[SYNTHETIC, Splitter:B:19:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0036 A[SYNTHETIC, Splitter:B:27:0x0036] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] b(java.io.Serializable r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            r2.<init>()     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            r1.writeObject(r3)     // Catch:{ Exception -> 0x001e }
            byte[] r3 = r2.toByteArray()     // Catch:{ Exception -> 0x001e }
            r1.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r0 = move-exception
            r0.printStackTrace()
        L_0x001d:
            return r3
        L_0x001e:
            r3 = move-exception
            goto L_0x0024
        L_0x0020:
            r3 = move-exception
            goto L_0x0034
        L_0x0022:
            r3 = move-exception
            r1 = r0
        L_0x0024:
            r3.printStackTrace()     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0031
            r1.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0031:
            return r0
        L_0x0032:
            r3 = move-exception
            r0 = r1
        L_0x0034:
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.CloneUtils.b(java.io.Serializable):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0026 A[SYNTHETIC, Splitter:B:19:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0033 A[SYNTHETIC, Splitter:B:27:0x0033] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object a(byte[] r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x001f, all -> 0x001d }
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x001f, all -> 0x001d }
            r2.<init>(r3)     // Catch:{ Exception -> 0x001f, all -> 0x001d }
            r1.<init>(r2)     // Catch:{ Exception -> 0x001f, all -> 0x001d }
            java.lang.Object r3 = r1.readObject()     // Catch:{ Exception -> 0x001b }
            r1.close()     // Catch:{ IOException -> 0x0016 }
            goto L_0x001a
        L_0x0016:
            r0 = move-exception
            r0.printStackTrace()
        L_0x001a:
            return r3
        L_0x001b:
            r3 = move-exception
            goto L_0x0021
        L_0x001d:
            r3 = move-exception
            goto L_0x0031
        L_0x001f:
            r3 = move-exception
            r1 = r0
        L_0x0021:
            r3.printStackTrace()     // Catch:{ all -> 0x002f }
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r3 = move-exception
            r3.printStackTrace()
        L_0x002e:
            return r0
        L_0x002f:
            r3 = move-exception
            r0 = r1
        L_0x0031:
            if (r0 == 0) goto L_0x003b
            r0.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.CloneUtils.a(byte[]):java.lang.Object");
    }
}
