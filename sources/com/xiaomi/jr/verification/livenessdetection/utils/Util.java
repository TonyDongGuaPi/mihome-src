package com.xiaomi.jr.verification.livenessdetection.utils;

public class Util {
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0024 A[SYNTHETIC, Splitter:B:19:0x0024] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0030 A[SYNTHETIC, Splitter:B:26:0x0030] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(android.content.Context r1, java.lang.String r2) {
        /*
            r0 = 0
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ IOException -> 0x001d, all -> 0x001a }
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ IOException -> 0x001d, all -> 0x001a }
            byte[] r2 = com.xiaomi.jr.common.utils.FileUtils.a((java.io.InputStream) r1)     // Catch:{ IOException -> 0x0018 }
            if (r1 == 0) goto L_0x0017
            r1.close()     // Catch:{ IOException -> 0x0013 }
            goto L_0x0017
        L_0x0013:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0017:
            return r2
        L_0x0018:
            r2 = move-exception
            goto L_0x001f
        L_0x001a:
            r2 = move-exception
            r1 = r0
            goto L_0x002e
        L_0x001d:
            r2 = move-exception
            r1 = r0
        L_0x001f:
            r2.printStackTrace()     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x002c
            r1.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x002c
        L_0x0028:
            r1 = move-exception
            r1.printStackTrace()
        L_0x002c:
            return r0
        L_0x002d:
            r2 = move-exception
        L_0x002e:
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0038:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.verification.livenessdetection.utils.Util.a(android.content.Context, java.lang.String):byte[]");
    }
}
