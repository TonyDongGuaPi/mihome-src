package com.xiaomi.jr.web.webpbackport;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

public class WebpFetcher {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11088a = "WebpFetcher";
    private static WebpFetcher b = new WebpFetcher();
    private WebpCache c;

    private WebpFetcher() {
    }

    public static WebpFetcher a() {
        return b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b9, code lost:
        if (r0 == null) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cc, code lost:
        if (r0 == null) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00ce, code lost:
        r0.disconnect();
     */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b1 A[SYNTHETIC, Splitter:B:47:0x00b1] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00d7 A[SYNTHETIC, Splitter:B:67:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00e1  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:44:0x00ac=Splitter:B:44:0x00ac, B:54:0x00bf=Splitter:B:54:0x00bf} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.InputStream a(android.content.Context r6, java.lang.String r7) {
        /*
            r5 = this;
            com.xiaomi.jr.web.webpbackport.WebpCache r0 = r5.c
            if (r0 != 0) goto L_0x000b
            com.xiaomi.jr.web.webpbackport.WebpCache r0 = new com.xiaomi.jr.web.webpbackport.WebpCache
            r0.<init>(r6)
            r5.c = r0
        L_0x000b:
            r6 = 0
            java.net.URL r0 = new java.net.URL     // Catch:{ MalformedURLException -> 0x00bc, IOException -> 0x00a9, all -> 0x00a6 }
            r0.<init>(r7)     // Catch:{ MalformedURLException -> 0x00bc, IOException -> 0x00a9, all -> 0x00a6 }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ MalformedURLException -> 0x00bc, IOException -> 0x00a9, all -> 0x00a6 }
            boolean r1 = r0 instanceof java.net.HttpURLConnection     // Catch:{ MalformedURLException -> 0x00bc, IOException -> 0x00a9, all -> 0x00a6 }
            if (r1 == 0) goto L_0x009e
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ MalformedURLException -> 0x00bc, IOException -> 0x00a9, all -> 0x00a6 }
            com.xiaomi.jr.web.webpbackport.WebpCache r1 = r5.c     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            java.lang.String r1 = r1.a(r7)     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            java.lang.String r2 = "If-None-Match"
            r0.setRequestProperty(r2, r1)     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            int r1 = r0.getResponseCode()     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            java.lang.String r2 = "WebpFetcher"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            r3.<init>()     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            java.lang.String r4 = "url: "
            r3.append(r4)     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            r3.append(r7)     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            java.lang.String r4 = ", code: "
            r3.append(r4)     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            r3.append(r1)     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            java.lang.String r3 = r3.toString()     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            com.xiaomi.jr.common.utils.MifiLog.b(r2, r3)     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 != r2) goto L_0x0071
            java.lang.String r1 = "ETag"
            java.lang.String r1 = r0.getHeaderField(r1)     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            java.io.InputStream r2 = r0.getInputStream()     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            byte[] r3 = com.xiaomi.jr.common.utils.FileUtils.a((java.io.InputStream) r2)     // Catch:{ MalformedURLException -> 0x0094, IOException -> 0x0092 }
            me.everything.webp.WebPDecoder r4 = me.everything.webp.WebPDecoder.a()     // Catch:{ MalformedURLException -> 0x0094, IOException -> 0x0092 }
            android.graphics.Bitmap r3 = r4.a(r3)     // Catch:{ MalformedURLException -> 0x0094, IOException -> 0x0092 }
            byte[] r3 = a(r3)     // Catch:{ MalformedURLException -> 0x0094, IOException -> 0x0092 }
            com.xiaomi.jr.web.webpbackport.WebpCache r4 = r5.c     // Catch:{ MalformedURLException -> 0x0094, IOException -> 0x0092 }
            r4.a(r7, r3, r1)     // Catch:{ MalformedURLException -> 0x0094, IOException -> 0x0092 }
            java.io.ByteArrayInputStream r7 = new java.io.ByteArrayInputStream     // Catch:{ MalformedURLException -> 0x0094, IOException -> 0x0092 }
            r7.<init>(r3)     // Catch:{ MalformedURLException -> 0x0094, IOException -> 0x0092 }
            goto L_0x007f
        L_0x0071:
            r2 = 304(0x130, float:4.26E-43)
            if (r1 != r2) goto L_0x007d
            com.xiaomi.jr.web.webpbackport.WebpCache r1 = r5.c     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            java.io.ByteArrayInputStream r7 = r1.b(r7)     // Catch:{ MalformedURLException -> 0x009b, IOException -> 0x0098, all -> 0x0096 }
            r2 = r6
            goto L_0x007f
        L_0x007d:
            r7 = r6
            r2 = r7
        L_0x007f:
            r0.disconnect()     // Catch:{ MalformedURLException -> 0x0094, IOException -> 0x0092 }
            if (r2 == 0) goto L_0x008c
            r2.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x008c
        L_0x0088:
            r6 = move-exception
            r6.printStackTrace()
        L_0x008c:
            if (r0 == 0) goto L_0x0091
            r0.disconnect()
        L_0x0091:
            return r7
        L_0x0092:
            r7 = move-exception
            goto L_0x00ac
        L_0x0094:
            r7 = move-exception
            goto L_0x00bf
        L_0x0096:
            r7 = move-exception
            goto L_0x00d5
        L_0x0098:
            r7 = move-exception
            r2 = r6
            goto L_0x00ac
        L_0x009b:
            r7 = move-exception
            r2 = r6
            goto L_0x00bf
        L_0x009e:
            java.lang.String r7 = "WebpFetcher"
            java.lang.String r0 = "support http request only"
            com.xiaomi.jr.common.utils.MifiLog.b(r7, r0)     // Catch:{ MalformedURLException -> 0x00bc, IOException -> 0x00a9, all -> 0x00a6 }
            goto L_0x00d1
        L_0x00a6:
            r7 = move-exception
            r0 = r6
            goto L_0x00d5
        L_0x00a9:
            r7 = move-exception
            r0 = r6
            r2 = r0
        L_0x00ac:
            r7.printStackTrace()     // Catch:{ all -> 0x00d2 }
            if (r2 == 0) goto L_0x00b9
            r2.close()     // Catch:{ IOException -> 0x00b5 }
            goto L_0x00b9
        L_0x00b5:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00b9:
            if (r0 == 0) goto L_0x00d1
            goto L_0x00ce
        L_0x00bc:
            r7 = move-exception
            r0 = r6
            r2 = r0
        L_0x00bf:
            r7.printStackTrace()     // Catch:{ all -> 0x00d2 }
            if (r2 == 0) goto L_0x00cc
            r2.close()     // Catch:{ IOException -> 0x00c8 }
            goto L_0x00cc
        L_0x00c8:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00cc:
            if (r0 == 0) goto L_0x00d1
        L_0x00ce:
            r0.disconnect()
        L_0x00d1:
            return r6
        L_0x00d2:
            r6 = move-exception
            r7 = r6
            r6 = r2
        L_0x00d5:
            if (r6 == 0) goto L_0x00df
            r6.close()     // Catch:{ IOException -> 0x00db }
            goto L_0x00df
        L_0x00db:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00df:
            if (r0 == 0) goto L_0x00e4
            r0.disconnect()
        L_0x00e4:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.web.webpbackport.WebpFetcher.a(android.content.Context, java.lang.String):java.io.InputStream");
    }

    private static byte[] a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
