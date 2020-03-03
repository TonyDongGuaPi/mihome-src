package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.liulishuo.filedownloader.services.FileDownloadDatabase;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import java.io.File;
import java.io.IOException;

public class CustomComponentHolder {

    /* renamed from: a  reason: collision with root package name */
    private DownloadMgrInitialParams f6420a;
    private FileDownloadHelper.ConnectionCountAdapter b;
    private FileDownloadHelper.ConnectionCreator c;
    private FileDownloadHelper.OutputStreamCreator d;
    private FileDownloadDatabase e;
    private FileDownloadHelper.IdGenerator f;

    private static final class LazyLoader {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final CustomComponentHolder f6421a = new CustomComponentHolder();

        private LazyLoader() {
        }
    }

    public static CustomComponentHolder a() {
        return LazyLoader.f6421a;
    }

    public void a(DownloadMgrInitialParams.InitCustomMaker initCustomMaker) {
        synchronized (this) {
            this.f6420a = new DownloadMgrInitialParams(initCustomMaker);
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
        }
    }

    public FileDownloadConnection a(String str) throws IOException {
        return g().a(str);
    }

    public FileDownloadOutputStream a(File file) throws IOException {
        return h().a(file);
    }

    public FileDownloadHelper.IdGenerator b() {
        if (this.f != null) {
            return this.f;
        }
        synchronized (this) {
            if (this.f == null) {
                this.f = i().f();
            }
        }
        return this.f;
    }

    public FileDownloadDatabase c() {
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e == null) {
                this.e = i().b();
                a(this.e.b());
            }
        }
        return this.e;
    }

    public int d() {
        return i().a();
    }

    public boolean e() {
        return h().a();
    }

    public int a(int i, String str, String str2, long j) {
        return f().a(i, str, str2, j);
    }

    private FileDownloadHelper.ConnectionCountAdapter f() {
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = i().e();
            }
        }
        return this.b;
    }

    private FileDownloadHelper.ConnectionCreator g() {
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = i().d();
            }
        }
        return this.c;
    }

    private FileDownloadHelper.OutputStreamCreator h() {
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = i().c();
            }
        }
        return this.d;
    }

    private DownloadMgrInitialParams i() {
        if (this.f6420a != null) {
            return this.f6420a;
        }
        synchronized (this) {
            if (this.f6420a == null) {
                this.f6420a = new DownloadMgrInitialParams();
            }
        }
        return this.f6420a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d1, code lost:
        if (r5.g() <= 0) goto L_0x00d3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c9 A[Catch:{ all -> 0x00f9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d5 A[Catch:{ all -> 0x00f9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0192  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(com.liulishuo.filedownloader.services.FileDownloadDatabase.Maintainer r25) {
        /*
            r1 = r25
            java.util.Iterator r0 = r25.iterator()
            com.liulishuo.filedownloader.download.CustomComponentHolder r2 = a()
            com.liulishuo.filedownloader.util.FileDownloadHelper$IdGenerator r2 = r2.b()
            long r3 = java.lang.System.currentTimeMillis()
            r7 = 0
            r9 = 0
            r11 = 0
        L_0x0018:
            r14 = 3
            r15 = 2
            r16 = 0
            boolean r17 = r0.hasNext()     // Catch:{ all -> 0x017f }
            if (r17 == 0) goto L_0x0141
            java.lang.Object r17 = r0.next()     // Catch:{ all -> 0x017f }
            r5 = r17
            com.liulishuo.filedownloader.model.FileDownloadModel r5 = (com.liulishuo.filedownloader.model.FileDownloadModel) r5     // Catch:{ all -> 0x017f }
            byte r6 = r5.f()     // Catch:{ all -> 0x017f }
            r13 = -2
            if (r6 == r14) goto L_0x004f
            byte r6 = r5.f()     // Catch:{ all -> 0x017f }
            if (r6 == r15) goto L_0x004f
            byte r6 = r5.f()     // Catch:{ all -> 0x017f }
            r15 = -1
            if (r6 == r15) goto L_0x004f
            byte r6 = r5.f()     // Catch:{ all -> 0x017f }
            r15 = 1
            if (r6 != r15) goto L_0x0052
            long r19 = r5.g()     // Catch:{ all -> 0x017f }
            r17 = 0
            int r6 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r6 <= 0) goto L_0x0052
        L_0x004f:
            r5.a((byte) r13)     // Catch:{ all -> 0x017f }
        L_0x0052:
            java.lang.String r6 = r5.d()     // Catch:{ all -> 0x017f }
            if (r6 != 0) goto L_0x0061
            r21 = r3
            r23 = r7
            r3 = 1
            r6 = 0
            goto L_0x00ea
        L_0x0061:
            java.io.File r15 = new java.io.File     // Catch:{ all -> 0x017f }
            r15.<init>(r6)     // Catch:{ all -> 0x017f }
            byte r6 = r5.f()     // Catch:{ all -> 0x017f }
            if (r6 != r13) goto L_0x00be
            int r6 = r5.a()     // Catch:{ all -> 0x017f }
            java.lang.String r13 = r5.c()     // Catch:{ all -> 0x017f }
            r14 = 0
            boolean r6 = com.liulishuo.filedownloader.util.FileDownloadUtils.a(r6, r5, r13, r14)     // Catch:{ all -> 0x017f }
            if (r6 == 0) goto L_0x00be
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x017f }
            java.lang.String r13 = r5.e()     // Catch:{ all -> 0x017f }
            r6.<init>(r13)     // Catch:{ all -> 0x017f }
            boolean r13 = r6.exists()     // Catch:{ all -> 0x017f }
            if (r13 != 0) goto L_0x00be
            boolean r13 = r15.exists()     // Catch:{ all -> 0x017f }
            if (r13 == 0) goto L_0x00be
            boolean r13 = r15.renameTo(r6)     // Catch:{ all -> 0x017f }
            boolean r14 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x017f }
            if (r14 == 0) goto L_0x00be
            java.lang.Class<com.liulishuo.filedownloader.services.FileDownloadDatabase> r14 = com.liulishuo.filedownloader.services.FileDownloadDatabase.class
            r21 = r3
            java.lang.String r3 = "resume from the old no-temp-file architecture [%B], [%s]->[%s]"
            r23 = r7
            r4 = 3
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ all -> 0x00f9 }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r13)     // Catch:{ all -> 0x00f9 }
            r7[r16] = r4     // Catch:{ all -> 0x00f9 }
            java.lang.String r4 = r15.getPath()     // Catch:{ all -> 0x00f9 }
            r8 = 1
            r7[r8] = r4     // Catch:{ all -> 0x00f9 }
            java.lang.String r4 = r6.getPath()     // Catch:{ all -> 0x00f9 }
            r6 = 2
            r7[r6] = r4     // Catch:{ all -> 0x00f9 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r14, r3, r7)     // Catch:{ all -> 0x00f9 }
            goto L_0x00c2
        L_0x00bb:
            r0 = move-exception
            goto L_0x0182
        L_0x00be:
            r21 = r3
            r23 = r7
        L_0x00c2:
            byte r3 = r5.f()     // Catch:{ all -> 0x00f9 }
            r4 = 1
            if (r3 != r4) goto L_0x00d5
            long r3 = r5.g()     // Catch:{ all -> 0x00f9 }
            r6 = 0
            int r8 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x00d7
        L_0x00d3:
            r3 = 1
            goto L_0x00ea
        L_0x00d5:
            r6 = 0
        L_0x00d7:
            int r3 = r5.a()     // Catch:{ all -> 0x00f9 }
            boolean r3 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((int) r3, (com.liulishuo.filedownloader.model.FileDownloadModel) r5)     // Catch:{ all -> 0x00f9 }
            if (r3 != 0) goto L_0x00e2
            goto L_0x00d3
        L_0x00e2:
            boolean r3 = r15.exists()     // Catch:{ all -> 0x00f9 }
            if (r3 == 0) goto L_0x00e9
            goto L_0x00d3
        L_0x00e9:
            r3 = 0
        L_0x00ea:
            r13 = 1
            if (r3 == 0) goto L_0x00fc
            r0.remove()     // Catch:{ all -> 0x00f9 }
            r1.a(r5)     // Catch:{ all -> 0x00f9 }
            r3 = 0
            long r9 = r9 + r13
            r7 = r23
            goto L_0x013d
        L_0x00f9:
            r0 = move-exception
            goto L_0x0184
        L_0x00fc:
            int r3 = r5.a()     // Catch:{ all -> 0x00f9 }
            java.lang.String r4 = r5.b()     // Catch:{ all -> 0x00f9 }
            java.lang.String r8 = r5.c()     // Catch:{ all -> 0x00f9 }
            boolean r15 = r5.l()     // Catch:{ all -> 0x00f9 }
            int r4 = r2.a(r3, r4, r8, r15)     // Catch:{ all -> 0x00f9 }
            if (r4 == r3) goto L_0x0137
            boolean r8 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x00f9 }
            if (r8 == 0) goto L_0x012f
            java.lang.Class<com.liulishuo.filedownloader.services.FileDownloadDatabase> r8 = com.liulishuo.filedownloader.services.FileDownloadDatabase.class
            java.lang.String r15 = "the id is changed on restoring from db: old[%d] -> new[%d]"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x00f9 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00f9 }
            r7[r16] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00f9 }
            r17 = 1
            r7[r17] = r6     // Catch:{ all -> 0x00f9 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r8, r15, r7)     // Catch:{ all -> 0x00f9 }
        L_0x012f:
            r5.a((int) r4)     // Catch:{ all -> 0x00f9 }
            r1.a(r3, r5)     // Catch:{ all -> 0x00f9 }
            r3 = 0
            long r11 = r11 + r13
        L_0x0137:
            r1.b(r5)     // Catch:{ all -> 0x00f9 }
            r3 = 0
            long r7 = r23 + r13
        L_0x013d:
            r3 = r21
            goto L_0x0018
        L_0x0141:
            r21 = r3
            r23 = r7
            android.content.Context r0 = com.liulishuo.filedownloader.util.FileDownloadHelper.a()
            com.liulishuo.filedownloader.util.FileDownloadUtils.b((android.content.Context) r0)
            r25.a()
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a
            if (r0 == 0) goto L_0x017e
            java.lang.Class<com.liulishuo.filedownloader.services.FileDownloadDatabase> r0 = com.liulishuo.filedownloader.services.FileDownloadDatabase.class
            java.lang.String r1 = "refreshed data count: %d , delete data count: %d, reset id count: %d. consume %d"
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Long r3 = java.lang.Long.valueOf(r23)
            r2[r16] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r9)
            r4 = 1
            r2[r4] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r11)
            r4 = 2
            r2[r4] = r3
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r21
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r4 = 3
            r2[r4] = r3
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r0, r1, r2)
        L_0x017e:
            return
        L_0x017f:
            r0 = move-exception
            r21 = r3
        L_0x0182:
            r23 = r7
        L_0x0184:
            android.content.Context r2 = com.liulishuo.filedownloader.util.FileDownloadHelper.a()
            com.liulishuo.filedownloader.util.FileDownloadUtils.b((android.content.Context) r2)
            r25.a()
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a
            if (r1 == 0) goto L_0x01bd
            java.lang.Class<com.liulishuo.filedownloader.services.FileDownloadDatabase> r1 = com.liulishuo.filedownloader.services.FileDownloadDatabase.class
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Long r3 = java.lang.Long.valueOf(r23)
            r2[r16] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r9)
            r4 = 1
            r2[r4] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r11)
            r4 = 2
            r2[r4] = r3
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r21
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r4 = 3
            r2[r4] = r3
            java.lang.String r3 = "refreshed data count: %d , delete data count: %d, reset id count: %d. consume %d"
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r1, r3, r2)
        L_0x01bd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.CustomComponentHolder.a(com.liulishuo.filedownloader.services.FileDownloadDatabase$Maintainer):void");
    }
}
