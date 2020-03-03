package com.unionpay.mobile.android.utils;

public final class k {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f9742a = false;
    private static int b = Integer.MAX_VALUE;

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        r0 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(int r2, java.lang.String r3, java.lang.String r4) {
        /*
            r0 = 0
            if (r3 == 0) goto L_0x008e
            if (r4 == 0) goto L_0x008e
            switch(r2) {
                case 2: goto L_0x001d;
                case 3: goto L_0x0018;
                case 4: goto L_0x0013;
                case 5: goto L_0x000e;
                case 6: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0022
        L_0x0009:
            int r2 = android.util.Log.e(r3, r4)
            goto L_0x0021
        L_0x000e:
            int r2 = android.util.Log.w(r3, r4)
            goto L_0x0021
        L_0x0013:
            int r2 = android.util.Log.i(r3, r4)
            goto L_0x0021
        L_0x0018:
            int r2 = android.util.Log.d(r3, r4)
            goto L_0x0021
        L_0x001d:
            int r2 = android.util.Log.v(r3, r4)
        L_0x0021:
            r0 = r2
        L_0x0022:
            boolean r2 = f9742a
            if (r2 == 0) goto L_0x008e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "[ ERROR ] "
            r2.<init>(r1)
            r2.append(r3)
            java.lang.String r3 = ":"
            r2.append(r3)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ IOException -> 0x008a }
            java.io.File r4 = new java.io.File     // Catch:{ IOException -> 0x008a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008a }
            r1.<init>()     // Catch:{ IOException -> 0x008a }
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ IOException -> 0x008a }
            r1.append(r3)     // Catch:{ IOException -> 0x008a }
            java.lang.String r3 = java.io.File.separator     // Catch:{ IOException -> 0x008a }
            r1.append(r3)     // Catch:{ IOException -> 0x008a }
            java.lang.String r3 = "upmp_log.txt"
            r1.append(r3)     // Catch:{ IOException -> 0x008a }
            java.lang.String r3 = r1.toString()     // Catch:{ IOException -> 0x008a }
            r4.<init>(r3)     // Catch:{ IOException -> 0x008a }
            boolean r3 = r4.exists()     // Catch:{ IOException -> 0x008a }
            if (r3 != 0) goto L_0x0068
            r4.createNewFile()     // Catch:{ IOException -> 0x008a }
        L_0x0068:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008a }
            r3.<init>()     // Catch:{ IOException -> 0x008a }
            r3.append(r2)     // Catch:{ IOException -> 0x008a }
            java.lang.String r2 = "\n"
            r3.append(r2)     // Catch:{ IOException -> 0x008a }
            java.lang.String r2 = r3.toString()     // Catch:{ IOException -> 0x008a }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x008a }
            r1 = 1
            r3.<init>(r4, r1)     // Catch:{ IOException -> 0x008a }
            byte[] r2 = r2.getBytes()     // Catch:{ IOException -> 0x008a }
            r3.write(r2)     // Catch:{ IOException -> 0x008a }
            r3.close()     // Catch:{ IOException -> 0x008a }
            goto L_0x008e
        L_0x008a:
            r2 = move-exception
            r2.printStackTrace()
        L_0x008e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.utils.k.a(int, java.lang.String, java.lang.String):int");
    }

    public static int a(String str, String str2) {
        if (b > 3) {
            return 0;
        }
        a(3, str, str2);
        return 0;
    }

    public static int b(String str, String str2) {
        if (b > 4) {
            return 0;
        }
        a(4, str, str2);
        return 0;
    }

    public static int c(String str, String str2) {
        if (b <= 6) {
            return a(6, str, str2);
        }
        return 0;
    }
}
