package com.xiaomi.jr.web.staticresource;

import com.xiaomi.jr.http.download.PrivateFileDownloader;

public class Fetcher {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1456a = "Fetcher";
    /* access modifiers changed from: private */
    public final Object b = new Object();
    private PrivateFileDownloader.DownloadListener c;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.content.Context r13, java.lang.String r14, java.lang.String r15) {
        /*
            r12 = this;
            r0 = 1
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0023 }
            r2.<init>(r14)     // Catch:{ MalformedURLException -> 0x0023 }
            java.lang.String r2 = r2.getPath()     // Catch:{ MalformedURLException -> 0x0023 }
            if (r2 == 0) goto L_0x0027
            java.lang.String r3 = "/"
            int r3 = r2.lastIndexOf(r3)     // Catch:{ MalformedURLException -> 0x0023 }
            r4 = -1
            if (r3 == r4) goto L_0x0027
            int r4 = r2.length()     // Catch:{ MalformedURLException -> 0x0023 }
            int r4 = r4 - r0
            if (r3 >= r4) goto L_0x0027
            int r3 = r3 + r0
            java.lang.String r2 = r2.substring(r3)     // Catch:{ MalformedURLException -> 0x0023 }
            goto L_0x0028
        L_0x0023:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0027:
            r2 = r1
        L_0x0028:
            r3 = 0
            if (r2 != 0) goto L_0x002c
            return r3
        L_0x002c:
            r2 = 2
            boolean[] r2 = new boolean[r2]
            r2 = {0, 0} // fill-array
            com.xiaomi.jr.http.download.PrivateFileDownloader r10 = new com.xiaomi.jr.http.download.PrivateFileDownloader
            r10.<init>(r13)
            com.xiaomi.jr.web.staticresource.Fetcher$1 r11 = new com.xiaomi.jr.web.staticresource.Fetcher$1
            r4 = r11
            r5 = r12
            r6 = r2
            r7 = r13
            r8 = r15
            r9 = r10
            r4.<init>(r6, r7, r8, r9)
            r12.c = r11
            java.lang.String r13 = "Fetcher"
            java.lang.String r15 = "start downloading..."
            com.xiaomi.jr.common.utils.MifiLog.b(r13, r15)
            com.xiaomi.jr.http.download.PrivateFileDownloader$DownloadListener r13 = r12.c
            boolean r13 = r10.a(r14, r13, r1, r3)
            if (r13 != 0) goto L_0x0055
            return r3
        L_0x0055:
            java.lang.Object r13 = r12.b
            monitor-enter(r13)
        L_0x0058:
            boolean r14 = r2[r0]     // Catch:{ InterruptedException -> 0x006f }
            if (r14 != 0) goto L_0x0062
            java.lang.Object r14 = r12.b     // Catch:{ InterruptedException -> 0x006f }
            r14.wait()     // Catch:{ InterruptedException -> 0x006f }
            goto L_0x0058
        L_0x0062:
            java.lang.String r14 = "Fetcher"
            java.lang.String r15 = "download complete!"
            com.xiaomi.jr.common.utils.MifiLog.b(r14, r15)     // Catch:{ InterruptedException -> 0x006f }
            boolean r14 = r2[r3]     // Catch:{ InterruptedException -> 0x006f }
            monitor-exit(r13)     // Catch:{ all -> 0x006d }
            return r14
        L_0x006d:
            r14 = move-exception
            goto L_0x0075
        L_0x006f:
            r14 = move-exception
            r14.printStackTrace()     // Catch:{ all -> 0x006d }
            monitor-exit(r13)     // Catch:{ all -> 0x006d }
            return r3
        L_0x0075:
            monitor-exit(r13)     // Catch:{ all -> 0x006d }
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.web.staticresource.Fetcher.a(android.content.Context, java.lang.String, java.lang.String):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.io.InputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            r1.<init>(r7)     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            boolean r2 = r1 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            if (r2 == 0) goto L_0x0077
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            int r2 = r1.getResponseCode()     // Catch:{ IOException -> 0x0075 }
            java.lang.String r3 = "Fetcher"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0075 }
            r4.<init>()     // Catch:{ IOException -> 0x0075 }
            java.lang.String r5 = "fetch file url: "
            r4.append(r5)     // Catch:{ IOException -> 0x0075 }
            r4.append(r7)     // Catch:{ IOException -> 0x0075 }
            java.lang.String r7 = ", code: "
            r4.append(r7)     // Catch:{ IOException -> 0x0075 }
            r4.append(r2)     // Catch:{ IOException -> 0x0075 }
            java.lang.String r7 = r4.toString()     // Catch:{ IOException -> 0x0075 }
            com.xiaomi.jr.common.utils.MifiLog.b(r3, r7)     // Catch:{ IOException -> 0x0075 }
            r7 = 200(0xc8, float:2.8E-43)
            if (r2 != r7) goto L_0x0080
            java.io.InputStream r7 = r1.getInputStream()     // Catch:{ IOException -> 0x0075 }
            if (r7 == 0) goto L_0x0047
            byte[] r2 = com.xiaomi.jr.common.utils.FileUtils.a((java.io.InputStream) r7)     // Catch:{ IOException -> 0x0044, all -> 0x0041 }
            goto L_0x0048
        L_0x0041:
            r8 = move-exception
            r0 = r7
            goto L_0x0099
        L_0x0044:
            r8 = move-exception
            r0 = r7
            goto L_0x008b
        L_0x0047:
            r2 = r0
        L_0x0048:
            if (r2 == 0) goto L_0x0073
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0067 }
            r3.<init>(r8)     // Catch:{ IOException -> 0x0067 }
            r3.write(r2)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            r8 = 1
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r3)     // Catch:{ IOException -> 0x0044, all -> 0x0041 }
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r7)
            if (r1 == 0) goto L_0x005e
            r1.disconnect()
        L_0x005e:
            return r8
        L_0x005f:
            r8 = move-exception
            r0 = r3
            goto L_0x006f
        L_0x0062:
            r8 = move-exception
            r0 = r3
            goto L_0x0068
        L_0x0065:
            r8 = move-exception
            goto L_0x006f
        L_0x0067:
            r8 = move-exception
        L_0x0068:
            r8.printStackTrace()     // Catch:{ all -> 0x0065 }
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r0)     // Catch:{ IOException -> 0x0044, all -> 0x0041 }
            goto L_0x0073
        L_0x006f:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r0)     // Catch:{ IOException -> 0x0044, all -> 0x0041 }
            throw r8     // Catch:{ IOException -> 0x0044, all -> 0x0041 }
        L_0x0073:
            r0 = r7
            goto L_0x0080
        L_0x0075:
            r8 = move-exception
            goto L_0x008b
        L_0x0077:
            java.lang.String r7 = "Fetcher"
            java.lang.String r8 = "support http request only"
            com.xiaomi.jr.common.utils.MifiLog.b(r7, r8)     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            r1 = r0
        L_0x0080:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r0)
            if (r1 == 0) goto L_0x0096
            goto L_0x0093
        L_0x0086:
            r8 = move-exception
            r1 = r0
            goto L_0x0099
        L_0x0089:
            r8 = move-exception
            r1 = r0
        L_0x008b:
            r8.printStackTrace()     // Catch:{ all -> 0x0098 }
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r0)
            if (r1 == 0) goto L_0x0096
        L_0x0093:
            r1.disconnect()
        L_0x0096:
            r7 = 0
            return r7
        L_0x0098:
            r8 = move-exception
        L_0x0099:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r0)
            if (r1 == 0) goto L_0x00a1
            r1.disconnect()
        L_0x00a1:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.web.staticresource.Fetcher.a(java.lang.String, java.lang.String):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.net.HttpURLConnection} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x0031 }
            r1.<init>(r5)     // Catch:{ IOException -> 0x0031 }
            java.net.URLConnection r5 = r1.openConnection()     // Catch:{ IOException -> 0x0031 }
            boolean r1 = r5 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0031 }
            if (r1 == 0) goto L_0x002c
            r1 = r5
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException -> 0x0031 }
            int r0 = r1.getResponseCode()     // Catch:{ IOException -> 0x0029, all -> 0x0026 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 != r2) goto L_0x0024
            int r5 = r5.getContentLength()     // Catch:{ IOException -> 0x0029, all -> 0x0026 }
            long r2 = (long) r5
            if (r1 == 0) goto L_0x0023
            r1.disconnect()
        L_0x0023:
            return r2
        L_0x0024:
            r0 = r1
            goto L_0x002c
        L_0x0026:
            r5 = move-exception
            r0 = r1
            goto L_0x003d
        L_0x0029:
            r5 = move-exception
            r0 = r1
            goto L_0x0032
        L_0x002c:
            if (r0 == 0) goto L_0x003a
            goto L_0x0037
        L_0x002f:
            r5 = move-exception
            goto L_0x003d
        L_0x0031:
            r5 = move-exception
        L_0x0032:
            r5.printStackTrace()     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x003a
        L_0x0037:
            r0.disconnect()
        L_0x003a:
            r0 = -1
            return r0
        L_0x003d:
            if (r0 == 0) goto L_0x0042
            r0.disconnect()
        L_0x0042:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.web.staticresource.Fetcher.a(java.lang.String):long");
    }
}
