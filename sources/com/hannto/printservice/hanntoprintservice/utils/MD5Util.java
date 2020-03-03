package com.hannto.printservice.hanntoprintservice.utils;

public class MD5Util {
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004e A[SYNTHETIC, Splitter:B:25:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0055 A[SYNTHETIC, Splitter:B:31:0x0055] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r6) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0030, all -> 0x002d }
            r1.<init>(r6)     // Catch:{ Exception -> 0x0030, all -> 0x002d }
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch:{ Exception -> 0x002b }
            java.lang.String r2 = "SHA-1"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch:{ Exception -> 0x002b }
            r3 = 0
            r4 = 0
        L_0x0012:
            r5 = -1
            if (r4 == r5) goto L_0x001f
            int r4 = r1.read(r6)     // Catch:{ Exception -> 0x002b }
            if (r4 <= 0) goto L_0x0012
            r2.update(r6, r3, r4)     // Catch:{ Exception -> 0x002b }
            goto L_0x0012
        L_0x001f:
            byte[] r6 = r2.digest()     // Catch:{ Exception -> 0x002b }
            java.lang.String r6 = a((byte[]) r6)     // Catch:{ Exception -> 0x002b }
            r1.close()     // Catch:{ Exception -> 0x002a }
        L_0x002a:
            return r6
        L_0x002b:
            r6 = move-exception
            goto L_0x0032
        L_0x002d:
            r6 = move-exception
            r1 = r0
            goto L_0x0053
        L_0x0030:
            r6 = move-exception
            r1 = r0
        L_0x0032:
            java.lang.String r2 = "HanntoPrinterService"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            r3.<init>()     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = "fileToSHA1 "
            r3.append(r4)     // Catch:{ all -> 0x0052 }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x0052 }
            r3.append(r6)     // Catch:{ all -> 0x0052 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0052 }
            android.util.Log.i(r2, r6)     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0051
            r1.close()     // Catch:{ Exception -> 0x0051 }
        L_0x0051:
            return r0
        L_0x0052:
            r6 = move-exception
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()     // Catch:{ Exception -> 0x0058 }
        L_0x0058:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hannto.printservice.hanntoprintservice.utils.MD5Util.a(java.lang.String):java.lang.String");
    }

    private static String a(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            str = str + Integer.toString((bArr[i] & 255) + 256, 16).substring(1);
        }
        return str.toLowerCase();
    }
}
