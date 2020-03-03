package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.download.CustomComponentHolder;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.List;

class FileDownloadManager implements IThreadPoolMonitor {

    /* renamed from: a  reason: collision with root package name */
    private final FileDownloadDatabase f6454a;
    private final FileDownloadThreadPool b;

    public FileDownloadManager() {
        CustomComponentHolder a2 = CustomComponentHolder.a();
        this.f6454a = a2.c();
        this.b = new FileDownloadThreadPool(a2.d());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0088, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00af, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f5, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0182  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.String r19, java.lang.String r20, boolean r21, int r22, int r23, int r24, boolean r25, com.liulishuo.filedownloader.model.FileDownloadHeader r26, boolean r27) {
        /*
            r18 = this;
            r7 = r18
            r0 = r19
            r8 = r20
            r9 = r21
            monitor-enter(r18)
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x01cd }
            r10 = 2
            r11 = 0
            r12 = 1
            if (r1 == 0) goto L_0x0022
            java.lang.String r1 = "request start the task with url(%s) path(%s) isDirectory(%B)"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x01cd }
            r2[r11] = r0     // Catch:{ all -> 0x01cd }
            r2[r12] = r8     // Catch:{ all -> 0x01cd }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r21)     // Catch:{ all -> 0x01cd }
            r2[r10] = r3     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r7, r1, r2)     // Catch:{ all -> 0x01cd }
        L_0x0022:
            int r13 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r19, (java.lang.String) r20, (boolean) r21)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.services.FileDownloadDatabase r1 = r7.f6454a     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.model.FileDownloadModel r1 = r1.a((int) r13)     // Catch:{ all -> 0x01cd }
            r2 = 0
            if (r9 != 0) goto L_0x006e
            if (r1 != 0) goto L_0x006e
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.j(r20)     // Catch:{ all -> 0x01cd }
            int r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r0, (java.lang.String) r1, (boolean) r12)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.services.FileDownloadDatabase r3 = r7.f6454a     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.model.FileDownloadModel r3 = r3.a((int) r1)     // Catch:{ all -> 0x01cd }
            if (r3 == 0) goto L_0x006b
            java.lang.String r4 = r3.d()     // Catch:{ all -> 0x01cd }
            boolean r4 = r8.equals(r4)     // Catch:{ all -> 0x01cd }
            if (r4 == 0) goto L_0x006b
            boolean r4 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x01cd }
            if (r4 == 0) goto L_0x0063
            java.lang.String r4 = "task[%d] find model by dirCaseId[%d]"
            java.lang.Object[] r5 = new java.lang.Object[r10]     // Catch:{ all -> 0x01cd }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x01cd }
            r5[r11] = r6     // Catch:{ all -> 0x01cd }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x01cd }
            r5[r12] = r6     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r7, r4, r5)     // Catch:{ all -> 0x01cd }
        L_0x0063:
            com.liulishuo.filedownloader.services.FileDownloadDatabase r4 = r7.f6454a     // Catch:{ all -> 0x01cd }
            java.util.List r1 = r4.b((int) r1)     // Catch:{ all -> 0x01cd }
            r15 = r1
            goto L_0x006c
        L_0x006b:
            r15 = r2
        L_0x006c:
            r14 = r3
            goto L_0x0070
        L_0x006e:
            r14 = r1
            r15 = r2
        L_0x0070:
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadHelper.a((int) r13, (com.liulishuo.filedownloader.model.FileDownloadModel) r14, (com.liulishuo.filedownloader.IThreadPoolMonitor) r7, (boolean) r12)     // Catch:{ all -> 0x01cd }
            if (r1 == 0) goto L_0x0089
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x01cd }
            if (r0 == 0) goto L_0x0087
            java.lang.String r0 = "has already started download %d"
            java.lang.Object[] r1 = new java.lang.Object[r12]     // Catch:{ all -> 0x01cd }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x01cd }
            r1[r11] = r2     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r7, r0, r1)     // Catch:{ all -> 0x01cd }
        L_0x0087:
            monitor-exit(r18)
            return
        L_0x0089:
            if (r14 == 0) goto L_0x0090
            java.lang.String r1 = r14.d()     // Catch:{ all -> 0x01cd }
            goto L_0x0094
        L_0x0090:
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r8, (boolean) r9, (java.lang.String) r2)     // Catch:{ all -> 0x01cd }
        L_0x0094:
            r6 = r25
            r5 = r1
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadHelper.a((int) r13, (java.lang.String) r5, (boolean) r6, (boolean) r12)     // Catch:{ all -> 0x01cd }
            if (r1 == 0) goto L_0x00b0
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x01cd }
            if (r0 == 0) goto L_0x00ae
            java.lang.String r0 = "has already completed downloading %d"
            java.lang.Object[] r1 = new java.lang.Object[r12]     // Catch:{ all -> 0x01cd }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x01cd }
            r1[r11] = r2     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r7, r0, r1)     // Catch:{ all -> 0x01cd }
        L_0x00ae:
            monitor-exit(r18)
            return
        L_0x00b0:
            r2 = 0
            if (r14 == 0) goto L_0x00b9
            long r16 = r14.g()     // Catch:{ all -> 0x01cd }
            goto L_0x00bb
        L_0x00b9:
            r16 = r2
        L_0x00bb:
            if (r14 == 0) goto L_0x00c2
            java.lang.String r1 = r14.e()     // Catch:{ all -> 0x01cd }
            goto L_0x00c6
        L_0x00c2:
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.e(r5)     // Catch:{ all -> 0x01cd }
        L_0x00c6:
            r4 = r1
            r1 = r13
            r2 = r16
            r16 = r5
            r6 = r18
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadHelper.a(r1, r2, r4, r5, r6)     // Catch:{ all -> 0x01cd }
            if (r1 == 0) goto L_0x00f6
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x01cd }
            if (r0 == 0) goto L_0x00f4
            java.lang.String r0 = "there is an another task with the same target-file-path %d %s"
            java.lang.Object[] r1 = new java.lang.Object[r10]     // Catch:{ all -> 0x01cd }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x01cd }
            r1[r11] = r2     // Catch:{ all -> 0x01cd }
            r1[r12] = r16     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r7, r0, r1)     // Catch:{ all -> 0x01cd }
            if (r14 == 0) goto L_0x00f4
            com.liulishuo.filedownloader.services.FileDownloadDatabase r0 = r7.f6454a     // Catch:{ all -> 0x01cd }
            r0.d(r13)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.services.FileDownloadDatabase r0 = r7.f6454a     // Catch:{ all -> 0x01cd }
            r0.c(r13)     // Catch:{ all -> 0x01cd }
        L_0x00f4:
            monitor-exit(r18)
            return
        L_0x00f6:
            if (r14 == 0) goto L_0x0162
            byte r1 = r14.f()     // Catch:{ all -> 0x01cd }
            r2 = -2
            if (r1 == r2) goto L_0x0119
            byte r1 = r14.f()     // Catch:{ all -> 0x01cd }
            r2 = -1
            if (r1 == r2) goto L_0x0119
            byte r1 = r14.f()     // Catch:{ all -> 0x01cd }
            if (r1 == r12) goto L_0x0119
            byte r1 = r14.f()     // Catch:{ all -> 0x01cd }
            r2 = 6
            if (r1 == r2) goto L_0x0119
            byte r1 = r14.f()     // Catch:{ all -> 0x01cd }
            if (r1 != r10) goto L_0x0162
        L_0x0119:
            int r1 = r14.a()     // Catch:{ all -> 0x01cd }
            if (r1 == r13) goto L_0x0152
            com.liulishuo.filedownloader.services.FileDownloadDatabase r0 = r7.f6454a     // Catch:{ all -> 0x01cd }
            int r1 = r14.a()     // Catch:{ all -> 0x01cd }
            r0.d(r1)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.services.FileDownloadDatabase r0 = r7.f6454a     // Catch:{ all -> 0x01cd }
            int r1 = r14.a()     // Catch:{ all -> 0x01cd }
            r0.c(r1)     // Catch:{ all -> 0x01cd }
            r14.a((int) r13)     // Catch:{ all -> 0x01cd }
            r14.a(r8, r9)     // Catch:{ all -> 0x01cd }
            if (r15 == 0) goto L_0x0180
            java.util.Iterator r0 = r15.iterator()     // Catch:{ all -> 0x01cd }
        L_0x013d:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x01cd }
            if (r1 == 0) goto L_0x0180
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.model.ConnectionModel r1 = (com.liulishuo.filedownloader.model.ConnectionModel) r1     // Catch:{ all -> 0x01cd }
            r1.a((int) r13)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.services.FileDownloadDatabase r2 = r7.f6454a     // Catch:{ all -> 0x01cd }
            r2.a((com.liulishuo.filedownloader.model.ConnectionModel) r1)     // Catch:{ all -> 0x01cd }
            goto L_0x013d
        L_0x0152:
            java.lang.String r1 = r14.b()     // Catch:{ all -> 0x01cd }
            boolean r1 = android.text.TextUtils.equals(r0, r1)     // Catch:{ all -> 0x01cd }
            if (r1 != 0) goto L_0x0160
            r14.a((java.lang.String) r0)     // Catch:{ all -> 0x01cd }
            goto L_0x0180
        L_0x0160:
            r12 = 0
            goto L_0x0180
        L_0x0162:
            if (r14 != 0) goto L_0x0169
            com.liulishuo.filedownloader.model.FileDownloadModel r14 = new com.liulishuo.filedownloader.model.FileDownloadModel     // Catch:{ all -> 0x01cd }
            r14.<init>()     // Catch:{ all -> 0x01cd }
        L_0x0169:
            r14.a((java.lang.String) r0)     // Catch:{ all -> 0x01cd }
            r14.a(r8, r9)     // Catch:{ all -> 0x01cd }
            r14.a((int) r13)     // Catch:{ all -> 0x01cd }
            r0 = 0
            r14.a((long) r0)     // Catch:{ all -> 0x01cd }
            r14.c((long) r0)     // Catch:{ all -> 0x01cd }
            r14.a((byte) r12)     // Catch:{ all -> 0x01cd }
            r14.b((int) r12)     // Catch:{ all -> 0x01cd }
        L_0x0180:
            if (r12 == 0) goto L_0x0187
            com.liulishuo.filedownloader.services.FileDownloadDatabase r0 = r7.f6454a     // Catch:{ all -> 0x01cd }
            r0.b((com.liulishuo.filedownloader.model.FileDownloadModel) r14)     // Catch:{ all -> 0x01cd }
        L_0x0187:
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = new com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder     // Catch:{ all -> 0x01cd }
            r0.<init>()     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.a((com.liulishuo.filedownloader.model.FileDownloadModel) r14)     // Catch:{ all -> 0x01cd }
            r1 = r26
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.a((com.liulishuo.filedownloader.model.FileDownloadHeader) r1)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.a((com.liulishuo.filedownloader.IThreadPoolMonitor) r7)     // Catch:{ all -> 0x01cd }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r23)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.a((java.lang.Integer) r1)     // Catch:{ all -> 0x01cd }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r22)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.b((java.lang.Integer) r1)     // Catch:{ all -> 0x01cd }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r25)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.a((java.lang.Boolean) r1)     // Catch:{ all -> 0x01cd }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r27)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.b((java.lang.Boolean) r1)     // Catch:{ all -> 0x01cd }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r24)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.c(r1)     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable r0 = r0.a()     // Catch:{ all -> 0x01cd }
            com.liulishuo.filedownloader.services.FileDownloadThreadPool r1 = r7.b     // Catch:{ all -> 0x01cd }
            r1.a((com.liulishuo.filedownloader.download.DownloadLaunchRunnable) r0)     // Catch:{ all -> 0x01cd }
            monitor-exit(r18)
            return
        L_0x01cd:
            r0 = move-exception
            monitor-exit(r18)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.services.FileDownloadManager.a(java.lang.String, java.lang.String, boolean, int, int, int, boolean, com.liulishuo.filedownloader.model.FileDownloadHeader, boolean):void");
    }

    public boolean a(String str, String str2) {
        return a(FileDownloadUtils.b(str, str2));
    }

    public boolean a(int i) {
        return a(this.f6454a.a(i));
    }

    public boolean b(int i) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "request pause the task %d", Integer.valueOf(i));
        }
        if (this.f6454a.a(i) == null) {
            return false;
        }
        this.b.b(i);
        return true;
    }

    public void a() {
        List<Integer> b2 = this.b.b();
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "pause all tasks %d", Integer.valueOf(b2.size()));
        }
        for (Integer intValue : b2) {
            b(intValue.intValue());
        }
    }

    public long c(int i) {
        FileDownloadModel a2 = this.f6454a.a(i);
        if (a2 == null) {
            return 0;
        }
        int n = a2.n();
        if (n <= 1) {
            return a2.g();
        }
        List<ConnectionModel> b2 = this.f6454a.b(i);
        if (b2 == null || b2.size() != n) {
            return 0;
        }
        return ConnectionModel.a(b2);
    }

    public long d(int i) {
        FileDownloadModel a2 = this.f6454a.a(i);
        if (a2 == null) {
            return 0;
        }
        return a2.h();
    }

    public byte e(int i) {
        FileDownloadModel a2 = this.f6454a.a(i);
        if (a2 == null) {
            return 0;
        }
        return a2.f();
    }

    public boolean b() {
        return this.b.a() <= 0;
    }

    public synchronized boolean f(int i) {
        return this.b.a(i);
    }

    public boolean a(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            return false;
        }
        boolean c = this.b.c(fileDownloadModel.a());
        if (FileDownloadStatus.a((int) fileDownloadModel.f())) {
            if (c) {
                return true;
            }
            return false;
        } else if (!c) {
            FileDownloadLog.a(this, "%d status is[%s](not finish) & but not in the pool", Integer.valueOf(fileDownloadModel.a()), Byte.valueOf(fileDownloadModel.f()));
            return false;
        }
        return true;
    }

    public int a(String str, int i) {
        return this.b.a(str, i);
    }

    public boolean g(int i) {
        if (i == 0) {
            FileDownloadLog.d(this, "The task[%d] id is invalid, can't clear it.", Integer.valueOf(i));
            return false;
        } else if (a(i)) {
            FileDownloadLog.d(this, "The task[%d] is downloading, can't clear it.", Integer.valueOf(i));
            return false;
        } else {
            this.f6454a.d(i);
            this.f6454a.c(i);
            return true;
        }
    }

    public void c() {
        this.f6454a.a();
    }
}
