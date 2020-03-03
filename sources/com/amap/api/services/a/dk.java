package com.amap.api.services.a;

import java.io.File;
import java.util.List;

public class dk {
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005c A[SYNTHETIC, Splitter:B:29:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0066 A[SYNTHETIC, Splitter:B:34:0x0066] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r5, byte[] r6, com.amap.api.services.a.dj r7) throws java.io.IOException, java.security.cert.CertificateException, java.security.NoSuchAlgorithmException, javax.crypto.IllegalBlockSizeException, javax.crypto.BadPaddingException, javax.crypto.NoSuchPaddingException, java.security.InvalidKeyException, java.security.spec.InvalidKeySpecException {
        /*
            r0 = 0
            java.lang.String r1 = r7.f4405a     // Catch:{ all -> 0x0058 }
            boolean r1 = a((java.lang.String) r1, (java.lang.String) r5)     // Catch:{ all -> 0x0058 }
            if (r1 == 0) goto L_0x000a
            return
        L_0x000a:
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0058 }
            java.lang.String r2 = r7.f4405a     // Catch:{ all -> 0x0058 }
            r1.<init>(r2)     // Catch:{ all -> 0x0058 }
            boolean r2 = r1.exists()     // Catch:{ all -> 0x0058 }
            if (r2 != 0) goto L_0x001a
            r1.mkdirs()     // Catch:{ all -> 0x0058 }
        L_0x001a:
            long r2 = r7.b     // Catch:{ all -> 0x0058 }
            r4 = 1
            com.amap.api.services.a.cw r1 = com.amap.api.services.a.cw.a(r1, r4, r4, r2)     // Catch:{ all -> 0x0058 }
            int r2 = r7.d     // Catch:{ all -> 0x0056 }
            r1.a((int) r2)     // Catch:{ all -> 0x0056 }
            com.amap.api.services.a.cc r7 = r7.e     // Catch:{ all -> 0x0056 }
            byte[] r6 = r7.b(r6)     // Catch:{ all -> 0x0056 }
            com.amap.api.services.a.cw$a r5 = r1.b((java.lang.String) r5)     // Catch:{ all -> 0x0056 }
            r7 = 0
            java.io.OutputStream r7 = r5.a((int) r7)     // Catch:{ all -> 0x0056 }
            r7.write(r6)     // Catch:{ all -> 0x0053 }
            r5.a()     // Catch:{ all -> 0x0053 }
            r1.c()     // Catch:{ all -> 0x0053 }
            if (r7 == 0) goto L_0x0048
            r7.close()     // Catch:{ Throwable -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0048:
            if (r1 == 0) goto L_0x0052
            r1.close()     // Catch:{ Throwable -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0052:
            return
        L_0x0053:
            r5 = move-exception
            r0 = r7
            goto L_0x005a
        L_0x0056:
            r5 = move-exception
            goto L_0x005a
        L_0x0058:
            r5 = move-exception
            r1 = r0
        L_0x005a:
            if (r0 == 0) goto L_0x0064
            r0.close()     // Catch:{ Throwable -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0064:
            if (r1 == 0) goto L_0x006e
            r1.close()     // Catch:{ Throwable -> 0x006a }
            goto L_0x006e
        L_0x006a:
            r6 = move-exception
            r6.printStackTrace()
        L_0x006e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.dk.a(java.lang.String, byte[], com.amap.api.services.a.dj):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x009e A[SYNTHETIC, Splitter:B:44:0x009e] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a4 A[SYNTHETIC, Splitter:B:47:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(com.amap.api.services.a.dj r8) {
        /*
            r0 = 0
            com.amap.api.services.a.ec r1 = r8.f     // Catch:{ Throwable -> 0x0093 }
            boolean r1 = r1.c()     // Catch:{ Throwable -> 0x0093 }
            if (r1 == 0) goto L_0x0085
            com.amap.api.services.a.ec r1 = r8.f     // Catch:{ Throwable -> 0x0093 }
            r2 = 1
            r1.a((boolean) r2)     // Catch:{ Throwable -> 0x0093 }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x0093 }
            java.lang.String r3 = r8.f4405a     // Catch:{ Throwable -> 0x0093 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x0093 }
            long r3 = r8.b     // Catch:{ Throwable -> 0x0093 }
            com.amap.api.services.a.cw r1 = com.amap.api.services.a.cw.a(r1, r2, r2, r3)     // Catch:{ Throwable -> 0x0093 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r3.<init>()     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            byte[] r4 = a((com.amap.api.services.a.cw) r1, (com.amap.api.services.a.dj) r8, (java.util.List<java.lang.String>) r3)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            if (r4 == 0) goto L_0x0075
            int r5 = r4.length     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            if (r5 != 0) goto L_0x002b
            goto L_0x0075
        L_0x002b:
            com.amap.api.services.a.ck r5 = new com.amap.api.services.a.ck     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r6 = r8.c     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r5.<init>(r4, r6)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            com.amap.api.services.a.db r6 = com.amap.api.services.a.db.a()     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            byte[] r5 = r6.b(r5)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r7 = new java.lang.String     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r7.<init>(r5)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r6.<init>(r7)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r5 = "code"
            boolean r5 = r6.has(r5)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            if (r5 == 0) goto L_0x0073
            java.lang.String r5 = "code"
            int r5 = r6.getInt(r5)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            if (r5 != r2) goto L_0x0073
            com.amap.api.services.a.ec r2 = r8.f     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            if (r2 == 0) goto L_0x0060
            if (r4 == 0) goto L_0x0060
            com.amap.api.services.a.ec r2 = r8.f     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            int r4 = r4.length     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r2.a((int) r4)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
        L_0x0060:
            com.amap.api.services.a.ec r8 = r8.f     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            int r8 = r8.b()     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r8 >= r2) goto L_0x006f
            a((com.amap.api.services.a.cw) r1, (java.util.List<java.lang.String>) r3)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            goto L_0x0085
        L_0x006f:
            a((com.amap.api.services.a.cw) r1)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            goto L_0x0085
        L_0x0073:
            r0 = r1
            goto L_0x0085
        L_0x0075:
            if (r1 == 0) goto L_0x007f
            r1.close()     // Catch:{ Throwable -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r8 = move-exception
            r8.printStackTrace()
        L_0x007f:
            return
        L_0x0080:
            r8 = move-exception
            goto L_0x00a2
        L_0x0082:
            r8 = move-exception
            r0 = r1
            goto L_0x0094
        L_0x0085:
            if (r0 == 0) goto L_0x00a1
            r0.close()     // Catch:{ Throwable -> 0x008b }
            goto L_0x00a1
        L_0x008b:
            r8 = move-exception
            r8.printStackTrace()
            goto L_0x00a1
        L_0x0090:
            r8 = move-exception
            r1 = r0
            goto L_0x00a2
        L_0x0093:
            r8 = move-exception
        L_0x0094:
            java.lang.String r1 = "leg"
            java.lang.String r2 = "uts"
            com.amap.api.services.a.cl.c(r8, r1, r2)     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x00a1
            r0.close()     // Catch:{ Throwable -> 0x008b }
        L_0x00a1:
            return
        L_0x00a2:
            if (r1 == 0) goto L_0x00ac
            r1.close()     // Catch:{ Throwable -> 0x00a8 }
            goto L_0x00ac
        L_0x00a8:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00ac:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.dk.a(com.amap.api.services.a.dj):void");
    }

    private static byte[] a(cw cwVar, dj djVar, List<String> list) {
        try {
            File b = cwVar.b();
            if (b != null && b.exists()) {
                String[] list2 = b.list();
                int length = list2.length;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String str = list2[i];
                    if (str.contains(".0")) {
                        String str2 = str.split("\\.")[0];
                        byte[] a2 = dp.a(cwVar, str2, false);
                        i2 += a2.length;
                        list.add(str2);
                        if (i2 > djVar.f.b()) {
                            break;
                        }
                        djVar.g.b(a2);
                    }
                    i++;
                }
                return djVar.g.a();
            }
        } catch (Throwable th) {
            cl.c(th, "leg", "gCo");
        }
        return new byte[0];
    }

    private static void a(cw cwVar) {
        if (cwVar != null) {
            try {
                cwVar.d();
            } catch (Throwable th) {
                cl.c(th, "ofm", "dlo");
            }
        }
    }

    private static void a(cw cwVar, List<String> list) {
        if (cwVar != null) {
            try {
                for (String c : list) {
                    cwVar.c(c);
                }
                cwVar.close();
            } catch (Throwable th) {
                cl.c(th, "ofm", "dlo");
            }
        }
    }

    private static boolean a(String str, String str2) {
        try {
            return new File(str, str2 + ".0").exists();
        } catch (Throwable th) {
            cl.c(th, "leg", "fet");
            return false;
        }
    }
}
