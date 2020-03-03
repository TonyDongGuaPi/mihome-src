package com.lidroid.xutils.bitmap.download;

public class DefaultDownloader extends Downloader {
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bd A[Catch:{ Throwable -> 0x0105 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(java.lang.String r18, java.io.OutputStream r19, com.lidroid.xutils.BitmapUtils.BitmapLoadTask<?> r20) {
        /*
            r17 = this;
            r0 = r18
            r1 = r20
            r2 = -1
            if (r1 == 0) goto L_0x0115
            boolean r4 = r20.m()
            if (r4 != 0) goto L_0x0115
            android.view.View r4 = r20.a()
            if (r4 != 0) goto L_0x0016
            goto L_0x0115
        L_0x0016:
            r4 = 0
            com.lidroid.xutils.util.OtherUtils.c()
            r5 = 0
            java.lang.String r7 = "/"
            boolean r7 = r0.startsWith(r7)     // Catch:{ Throwable -> 0x0105 }
            if (r7 == 0) goto L_0x004b
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0105 }
            r7.<init>(r0)     // Catch:{ Throwable -> 0x0105 }
            int r0 = r7.available()     // Catch:{ Throwable -> 0x0105 }
            long r8 = (long) r0     // Catch:{ Throwable -> 0x0105 }
            java.io.BufferedInputStream r10 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0105 }
            r10.<init>(r7)     // Catch:{ Throwable -> 0x0105 }
            long r11 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0047, all -> 0x0043 }
            long r13 = r17.b()     // Catch:{ Throwable -> 0x0047, all -> 0x0043 }
            r0 = 0
            long r11 = r11 + r13
            r4 = r10
            r15 = r8
            r8 = r11
        L_0x0040:
            r10 = r15
            goto L_0x00b7
        L_0x0043:
            r0 = move-exception
            r4 = r10
            goto L_0x0111
        L_0x0047:
            r0 = move-exception
            r4 = r10
            goto L_0x0106
        L_0x004b:
            java.lang.String r7 = "assets/"
            boolean r7 = r0.startsWith(r7)     // Catch:{ Throwable -> 0x0105 }
            if (r7 == 0) goto L_0x007b
            android.content.Context r7 = r17.a()     // Catch:{ Throwable -> 0x0105 }
            android.content.res.AssetManager r7 = r7.getAssets()     // Catch:{ Throwable -> 0x0105 }
            r8 = 7
            int r9 = r18.length()     // Catch:{ Throwable -> 0x0105 }
            java.lang.String r0 = r0.substring(r8, r9)     // Catch:{ Throwable -> 0x0105 }
            java.io.InputStream r0 = r7.open(r0)     // Catch:{ Throwable -> 0x0105 }
            int r7 = r0.available()     // Catch:{ Throwable -> 0x0105 }
            long r8 = (long) r7     // Catch:{ Throwable -> 0x0105 }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0105 }
            r7.<init>(r0)     // Catch:{ Throwable -> 0x0105 }
            r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r4 = r7
            r15 = r8
            r8 = r10
            goto L_0x0040
        L_0x007b:
            java.net.URL r7 = new java.net.URL     // Catch:{ Throwable -> 0x0105 }
            r7.<init>(r0)     // Catch:{ Throwable -> 0x0105 }
            java.net.URLConnection r0 = r7.openConnection()     // Catch:{ Throwable -> 0x0105 }
            int r7 = r17.c()     // Catch:{ Throwable -> 0x0105 }
            r0.setConnectTimeout(r7)     // Catch:{ Throwable -> 0x0105 }
            int r7 = r17.d()     // Catch:{ Throwable -> 0x0105 }
            r0.setReadTimeout(r7)     // Catch:{ Throwable -> 0x0105 }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0105 }
            java.io.InputStream r8 = r0.getInputStream()     // Catch:{ Throwable -> 0x0105 }
            r7.<init>(r8)     // Catch:{ Throwable -> 0x0105 }
            long r8 = r0.getExpiration()     // Catch:{ Throwable -> 0x0100, all -> 0x00fd }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0100, all -> 0x00fd }
            int r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r4 >= 0) goto L_0x00b1
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0100, all -> 0x00fd }
            long r10 = r17.b()     // Catch:{ Throwable -> 0x0100, all -> 0x00fd }
            r4 = 0
            long r8 = r8 + r10
        L_0x00b1:
            int r0 = r0.getContentLength()     // Catch:{ Throwable -> 0x0100, all -> 0x00fd }
            long r10 = (long) r0
            r4 = r7
        L_0x00b7:
            boolean r0 = r20.m()     // Catch:{ Throwable -> 0x0105 }
            if (r0 != 0) goto L_0x00f9
            android.view.View r0 = r20.a()     // Catch:{ Throwable -> 0x0105 }
            if (r0 != 0) goto L_0x00c4
            goto L_0x00f9
        L_0x00c4:
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch:{ Throwable -> 0x0105 }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x0105 }
            r12 = r19
            r7.<init>(r12)     // Catch:{ Throwable -> 0x0105 }
        L_0x00cf:
            int r12 = r4.read(r0)     // Catch:{ Throwable -> 0x0105 }
            r13 = -1
            if (r12 != r13) goto L_0x00de
            r7.flush()     // Catch:{ Throwable -> 0x0105 }
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r4)
            r2 = r8
            goto L_0x0110
        L_0x00de:
            r13 = 0
            r7.write(r0, r13, r12)     // Catch:{ Throwable -> 0x0105 }
            long r12 = (long) r12     // Catch:{ Throwable -> 0x0105 }
            long r5 = r5 + r12
            boolean r12 = r20.m()     // Catch:{ Throwable -> 0x0105 }
            if (r12 != 0) goto L_0x00f5
            android.view.View r12 = r20.a()     // Catch:{ Throwable -> 0x0105 }
            if (r12 != 0) goto L_0x00f1
            goto L_0x00f5
        L_0x00f1:
            r1.a(r10, r5)     // Catch:{ Throwable -> 0x0105 }
            goto L_0x00cf
        L_0x00f5:
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r4)
            return r2
        L_0x00f9:
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r4)
            return r2
        L_0x00fd:
            r0 = move-exception
            r4 = r7
            goto L_0x0111
        L_0x0100:
            r0 = move-exception
            r4 = r7
            goto L_0x0106
        L_0x0103:
            r0 = move-exception
            goto L_0x0111
        L_0x0105:
            r0 = move-exception
        L_0x0106:
            java.lang.String r1 = r0.getMessage()     // Catch:{ all -> 0x0103 }
            com.lidroid.xutils.util.LogUtils.b(r1, r0)     // Catch:{ all -> 0x0103 }
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r4)
        L_0x0110:
            return r2
        L_0x0111:
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r4)
            throw r0
        L_0x0115:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.bitmap.download.DefaultDownloader.a(java.lang.String, java.io.OutputStream, com.lidroid.xutils.BitmapUtils$BitmapLoadTask):long");
    }
}
