package com.tencent.smtt.sdk;

public class a {

    /* renamed from: a  reason: collision with root package name */
    public static int f9118a = 600;
    private static int b;

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075 A[SYNTHETIC, Splitter:B:33:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A[SYNTHETIC, Splitter:B:40:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008d A[SYNTHETIC, Splitter:B:46:0x008d] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:30:0x0070=Splitter:B:30:0x0070, B:37:0x007d=Splitter:B:37:0x007d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a() {
        /*
            int r0 = b
            if (r0 <= 0) goto L_0x0007
            int r0 = b
            return r0
        L_0x0007:
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ IOException -> 0x0079, Throwable -> 0x006c, all -> 0x0067 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0079, Throwable -> 0x006c, all -> 0x0067 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0079, Throwable -> 0x006c, all -> 0x0067 }
            r4 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r3, r4)     // Catch:{ IOException -> 0x0079, Throwable -> 0x006c, all -> 0x0067 }
        L_0x0017:
            java.lang.String r1 = r0.readLine()     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            if (r1 == 0) goto L_0x005a
            java.lang.String r3 = "MemTotal:"
            int r3 = r1.indexOf(r3)     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            r4 = -1
            if (r4 == r3) goto L_0x0017
            java.lang.String r4 = "MemTotal:"
            int r4 = r4.length()     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            int r3 = r3 + r4
            java.lang.String r1 = r1.substring(r3)     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            java.lang.String r1 = r1.trim()     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            if (r1 == 0) goto L_0x005a
            int r3 = r1.length()     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            if (r3 == 0) goto L_0x005a
            java.lang.String r3 = "k"
            boolean r3 = r1.contains(r3)     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            if (r3 == 0) goto L_0x005a
            java.lang.String r3 = "k"
            int r3 = r1.indexOf(r3)     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            java.lang.String r1 = r1.substring(r2, r3)     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            java.lang.String r1 = r1.trim()     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ IOException -> 0x0065, Throwable -> 0x0063 }
            int r1 = r1 / 1024
            r2 = r1
        L_0x005a:
            r0.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0085
        L_0x005e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0085
        L_0x0063:
            r1 = move-exception
            goto L_0x0070
        L_0x0065:
            r1 = move-exception
            goto L_0x007d
        L_0x0067:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x008b
        L_0x006c:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0070:
            r1.printStackTrace()     // Catch:{ all -> 0x008a }
            if (r0 == 0) goto L_0x0085
            r0.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0085
        L_0x0079:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x007d:
            r1.printStackTrace()     // Catch:{ all -> 0x008a }
            if (r0 == 0) goto L_0x0085
            r0.close()     // Catch:{ IOException -> 0x005e }
        L_0x0085:
            b = r2
            int r0 = b
            return r0
        L_0x008a:
            r1 = move-exception
        L_0x008b:
            if (r0 == 0) goto L_0x0095
            r0.close()     // Catch:{ IOException -> 0x0091 }
            goto L_0x0095
        L_0x0091:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0095:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.a.a():int");
    }
}
