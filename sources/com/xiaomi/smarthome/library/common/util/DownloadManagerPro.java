package com.xiaomi.smarthome.library.common.util;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Build;
import java.lang.reflect.Method;

public class DownloadManagerPro {

    /* renamed from: a  reason: collision with root package name */
    public static final Uri f18670a = Uri.parse("content://downloads/my_downloads");
    public static final String b = "local_filename";
    public static final String c = "local_uri";
    public static final String d = "pauseDownload";
    public static final String e = "resumeDownload";
    private static boolean f = false;
    private static boolean g = false;
    private static Method h = null;
    private static Method i = null;
    private DownloadManager j;

    public DownloadManagerPro(DownloadManager downloadManager) {
        this.j = downloadManager;
    }

    public DownloadManager a() {
        return this.j;
    }

    public int a(long j2) {
        return b(j2, "status");
    }

    public int[] b(long j2) {
        int[] c2 = c(j2);
        return new int[]{c2[0], c2[1]};
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int[] c(long r7) {
        /*
            r6 = this;
            r0 = 3
            int[] r0 = new int[r0]
            r0 = {-1, -1, 0} // fill-array
            android.app.DownloadManager$Query r1 = new android.app.DownloadManager$Query
            r1.<init>()
            r2 = 1
            long[] r3 = new long[r2]
            r4 = 0
            r3[r4] = r7
            android.app.DownloadManager$Query r7 = r1.setFilterById(r3)
            r8 = 0
            android.app.DownloadManager r1 = r6.j     // Catch:{ all -> 0x0052 }
            android.database.Cursor r7 = r1.query(r7)     // Catch:{ all -> 0x0052 }
            if (r7 == 0) goto L_0x004c
            boolean r8 = r7.moveToFirst()     // Catch:{ all -> 0x004a }
            if (r8 == 0) goto L_0x004c
            java.lang.String r8 = "bytes_so_far"
            int r8 = r7.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x004a }
            int r8 = r7.getInt(r8)     // Catch:{ all -> 0x004a }
            r0[r4] = r8     // Catch:{ all -> 0x004a }
            java.lang.String r8 = "total_size"
            int r8 = r7.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x004a }
            int r8 = r7.getInt(r8)     // Catch:{ all -> 0x004a }
            r0[r2] = r8     // Catch:{ all -> 0x004a }
            r8 = 2
            java.lang.String r1 = "status"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ all -> 0x004a }
            int r1 = r7.getInt(r1)     // Catch:{ all -> 0x004a }
            r0[r8] = r1     // Catch:{ all -> 0x004a }
            goto L_0x004c
        L_0x004a:
            r8 = move-exception
            goto L_0x0056
        L_0x004c:
            if (r7 == 0) goto L_0x0051
            r7.close()
        L_0x0051:
            return r0
        L_0x0052:
            r7 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
        L_0x0056:
            if (r7 == 0) goto L_0x005b
            r7.close()
        L_0x005b:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.DownloadManagerPro.c(long):int[]");
    }

    public int a(long... jArr) {
        c();
        if (h == null) {
            return -1;
        }
        try {
            return ((Integer) h.invoke(this.j, new Object[]{jArr})).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int b(long... jArr) {
        d();
        if (i == null) {
            return -1;
        }
        try {
            return ((Integer) i.invoke(this.j, new Object[]{jArr})).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static boolean b() {
        c();
        d();
        return (h == null || i == null) ? false : true;
    }

    private static void c() {
        if (!f) {
            f = true;
            try {
                h = DownloadManager.class.getMethod(d, new Class[]{long[].class});
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void d() {
        if (!g) {
            g = true;
            try {
                i = DownloadManager.class.getMethod(e, new Class[]{long[].class});
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public String d(long j2) {
        return a(j2, Build.VERSION.SDK_INT < 11 ? "local_uri" : b);
    }

    public String e(long j2) {
        return a(j2, "uri");
    }

    public int f(long j2) {
        return b(j2, "reason");
    }

    public int g(long j2) {
        return b(j2, "reason");
    }

    public int h(long j2) {
        return b(j2, "reason");
    }

    public static class RequestPro extends DownloadManager.Request {

        /* renamed from: a  reason: collision with root package name */
        public static final String f18671a = "setNotiClass";
        public static final String b = "setNotiExtras";
        private static boolean c = false;
        private static boolean d = false;
        private static Method e;
        private static Method f;

        public RequestPro(Uri uri) {
            super(uri);
        }

        public void a(String str) {
            synchronized (this) {
                if (!c) {
                    c = true;
                    try {
                        e = DownloadManager.Request.class.getMethod(f18671a, new Class[]{CharSequence.class});
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (e != null) {
                try {
                    e.invoke(this, new Object[]{str});
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }

        public void b(String str) {
            synchronized (this) {
                if (!d) {
                    d = true;
                    try {
                        f = DownloadManager.Request.class.getMethod(b, new Class[]{CharSequence.class});
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (f != null) {
                try {
                    f.invoke(this, new Object[]{str});
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(long r5, java.lang.String r7) {
        /*
            r4 = this;
            android.app.DownloadManager$Query r0 = new android.app.DownloadManager$Query
            r0.<init>()
            r1 = 1
            long[] r1 = new long[r1]
            r2 = 0
            r1[r2] = r5
            android.app.DownloadManager$Query r5 = r0.setFilterById(r1)
            r6 = 0
            android.app.DownloadManager r0 = r4.j     // Catch:{ all -> 0x002f }
            android.database.Cursor r5 = r0.query(r5)     // Catch:{ all -> 0x002f }
            if (r5 == 0) goto L_0x0029
            boolean r0 = r5.moveToFirst()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x0029
            int r6 = r5.getColumnIndex(r7)     // Catch:{ all -> 0x0027 }
            java.lang.String r6 = r5.getString(r6)     // Catch:{ all -> 0x0027 }
            goto L_0x0029
        L_0x0027:
            r6 = move-exception
            goto L_0x0033
        L_0x0029:
            if (r5 == 0) goto L_0x002e
            r5.close()
        L_0x002e:
            return r6
        L_0x002f:
            r5 = move-exception
            r3 = r6
            r6 = r5
            r5 = r3
        L_0x0033:
            if (r5 == 0) goto L_0x0038
            r5.close()
        L_0x0038:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.DownloadManagerPro.a(long, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int b(long r5, java.lang.String r7) {
        /*
            r4 = this;
            android.app.DownloadManager$Query r0 = new android.app.DownloadManager$Query
            r0.<init>()
            r1 = 1
            long[] r1 = new long[r1]
            r2 = 0
            r1[r2] = r5
            android.app.DownloadManager$Query r5 = r0.setFilterById(r1)
            r6 = 0
            android.app.DownloadManager r0 = r4.j     // Catch:{ all -> 0x0030 }
            android.database.Cursor r5 = r0.query(r5)     // Catch:{ all -> 0x0030 }
            if (r5 == 0) goto L_0x0029
            boolean r6 = r5.moveToFirst()     // Catch:{ all -> 0x0027 }
            if (r6 == 0) goto L_0x0029
            int r6 = r5.getColumnIndex(r7)     // Catch:{ all -> 0x0027 }
            int r6 = r5.getInt(r6)     // Catch:{ all -> 0x0027 }
            goto L_0x002a
        L_0x0027:
            r6 = move-exception
            goto L_0x0034
        L_0x0029:
            r6 = -1
        L_0x002a:
            if (r5 == 0) goto L_0x002f
            r5.close()
        L_0x002f:
            return r6
        L_0x0030:
            r5 = move-exception
            r3 = r6
            r6 = r5
            r5 = r3
        L_0x0034:
            if (r5 == 0) goto L_0x0039
            r5.close()
        L_0x0039:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.DownloadManagerPro.b(long, java.lang.String):int");
    }
}
