package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.IDownloadSpeed;
import com.liulishuo.filedownloader.ITaskHunter;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class DownloadTaskHunter implements BaseDownloadTask.LifeCycleCallback, ITaskHunter, ITaskHunter.IMessageHandler, ITaskHunter.IStarter {

    /* renamed from: a  reason: collision with root package name */
    private IFileDownloadMessenger f6381a;
    private final Object b;
    private final ICaptureTask c;
    private volatile byte d = 0;
    private Throwable e = null;
    private final IDownloadSpeed.Monitor f;
    private final IDownloadSpeed.Lookup g;
    private long h;
    private long i;
    private int j;
    private boolean k;
    private boolean l;
    private String m;
    private boolean n = false;

    interface ICaptureTask {
        FileDownloadHeader ab();

        BaseDownloadTask.IRunningTask ac();

        ArrayList<BaseDownloadTask.FinishListener> ad();

        void d(String str);
    }

    public boolean a(MessageSnapshot messageSnapshot) {
        if (!FileDownloadStatus.a(g(), messageSnapshot.b())) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "can't update mStatus change by keep ahead, %d, but the current mStatus is %d, %d", Byte.valueOf(this.d), Byte.valueOf(g()), Integer.valueOf(t()));
            }
            return false;
        }
        e(messageSnapshot);
        return true;
    }

    public boolean b(MessageSnapshot messageSnapshot) {
        byte g2 = g();
        byte b2 = messageSnapshot.b();
        if (-2 == g2 && FileDownloadStatus.b(b2)) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "High concurrent cause, callback pending, but has already be paused %d", Integer.valueOf(t()));
            }
            return true;
        } else if (!FileDownloadStatus.b(g2, b2)) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "can't update mStatus change by keep flow, %d, but the current mStatus is %d, %d", Byte.valueOf(this.d), Byte.valueOf(g()), Integer.valueOf(t()));
            }
            return false;
        } else {
            e(messageSnapshot);
            return true;
        }
    }

    public boolean c(MessageSnapshot messageSnapshot) {
        if (!FileDownloadStatus.a(this.c.ac().P())) {
            return false;
        }
        e(messageSnapshot);
        return true;
    }

    public boolean d(MessageSnapshot messageSnapshot) {
        if (!this.c.ac().P().q() || messageSnapshot.b() != -4 || g() != 2) {
            return false;
        }
        e(messageSnapshot);
        return true;
    }

    public IFileDownloadMessenger d() {
        return this.f6381a;
    }

    public MessageSnapshot a(Throwable th) {
        this.d = -1;
        this.e = th;
        return MessageSnapshotTaker.a(t(), i(), th);
    }

    private void e(MessageSnapshot messageSnapshot) {
        int i2;
        BaseDownloadTask P = this.c.ac().P();
        byte b2 = messageSnapshot.b();
        this.d = b2;
        this.k = messageSnapshot.m();
        switch (b2) {
            case -4:
                this.f.a();
                int a2 = FileDownloadList.a().a(P.k());
                if (a2 > 1 || !P.q()) {
                    i2 = 0;
                } else {
                    i2 = FileDownloadList.a().a(FileDownloadUtils.b(P.m(), P.s()));
                }
                if (a2 + i2 <= 1) {
                    byte d2 = FileDownloadServiceProxy.a().d(P.k());
                    FileDownloadLog.d(this, "warn, but no mListener to receive, switch to pending %d %d", Integer.valueOf(P.k()), Integer.valueOf(d2));
                    if (FileDownloadStatus.b(d2)) {
                        this.d = 1;
                        this.i = messageSnapshot.i();
                        this.h = messageSnapshot.h();
                        this.f.a(this.h);
                        this.f6381a.a(((MessageSnapshot.IWarnMessageSnapshot) messageSnapshot).a());
                        return;
                    }
                }
                FileDownloadList.a().a(this.c.ac(), messageSnapshot);
                return;
            case -3:
                this.n = messageSnapshot.l();
                this.h = messageSnapshot.i();
                this.i = messageSnapshot.i();
                FileDownloadList.a().a(this.c.ac(), messageSnapshot);
                return;
            case -1:
                this.e = messageSnapshot.d();
                this.h = messageSnapshot.h();
                FileDownloadList.a().a(this.c.ac(), messageSnapshot);
                return;
            case 1:
                this.h = messageSnapshot.h();
                this.i = messageSnapshot.i();
                this.f6381a.a(messageSnapshot);
                return;
            case 2:
                this.i = messageSnapshot.i();
                this.l = messageSnapshot.f();
                this.m = messageSnapshot.g();
                String n2 = messageSnapshot.n();
                if (n2 != null) {
                    if (P.r() != null) {
                        FileDownloadLog.d(this, "already has mFilename[%s], but assign mFilename[%s] again", P.r(), n2);
                    }
                    this.c.d(n2);
                }
                this.f.a(this.h);
                this.f6381a.c(messageSnapshot);
                return;
            case 3:
                this.h = messageSnapshot.h();
                this.f.c(messageSnapshot.h());
                this.f6381a.d(messageSnapshot);
                return;
            case 5:
                this.h = messageSnapshot.h();
                this.e = messageSnapshot.d();
                this.j = messageSnapshot.e();
                this.f.a();
                this.f6381a.f(messageSnapshot);
                return;
            case 6:
                this.f6381a.b(messageSnapshot);
                return;
            default:
                return;
        }
    }

    public void a() {
        if (FileDownloadMonitor.c()) {
            FileDownloadMonitor.b().b(this.c.ac().P());
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.e(this, "filedownloader:lifecycle:start %s by %d ", toString(), Byte.valueOf(g()));
        }
    }

    public void f_() {
        if (FileDownloadMonitor.c() && g() == 6) {
            FileDownloadMonitor.b().c(this.c.ac().P());
        }
    }

    public void c() {
        BaseDownloadTask P = this.c.ac().P();
        if (FileDownloadMonitor.c()) {
            FileDownloadMonitor.b().d(P);
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.e(this, "filedownloader:lifecycle:over %s by %d ", toString(), Byte.valueOf(g()));
        }
        this.f.b(this.h);
        if (this.c.ad() != null) {
            ArrayList arrayList = (ArrayList) this.c.ad().clone();
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((BaseDownloadTask.FinishListener) arrayList.get(i2)).a(P);
            }
        }
        FileDownloader.a().n().b(this.c.ac());
    }

    DownloadTaskHunter(ICaptureTask iCaptureTask, Object obj) {
        this.b = obj;
        this.c = iCaptureTask;
        DownloadSpeedMonitor downloadSpeedMonitor = new DownloadSpeedMonitor();
        this.f = downloadSpeedMonitor;
        this.g = downloadSpeedMonitor;
        this.f6381a = new FileDownloadMessenger(iCaptureTask.ac(), this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        r0 = r8.c.ac();
        r1 = r0.P();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.liulishuo.filedownloader.FileDownloadMonitor.c() == false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        com.liulishuo.filedownloader.FileDownloadMonitor.b().a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        if (com.liulishuo.filedownloader.util.FileDownloadLog.f6465a == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        com.liulishuo.filedownloader.util.FileDownloadLog.e(r8, "call start Url[%s], Path[%s] Listener[%s], Tag[%s]", r1.m(), r1.p(), r1.t(), r1.G());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        s();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0069, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006c, code lost:
        com.liulishuo.filedownloader.FileDownloadList.a().b(r0);
        com.liulishuo.filedownloader.FileDownloadList.a().a(r0, a(r1));
        r0 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
            r8 = this;
            java.lang.Object r0 = r8.b
            monitor-enter(r0)
            byte r1 = r8.d     // Catch:{ all -> 0x009f }
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0025
            java.lang.String r1 = "High concurrent cause, this task %d will not input to launch pool, because of the status isn't idle : %d"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x009f }
            int r5 = r8.t()     // Catch:{ all -> 0x009f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x009f }
            r2[r4] = r5     // Catch:{ all -> 0x009f }
            byte r4 = r8.d     // Catch:{ all -> 0x009f }
            java.lang.Byte r4 = java.lang.Byte.valueOf(r4)     // Catch:{ all -> 0x009f }
            r2[r3] = r4     // Catch:{ all -> 0x009f }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r8, r1, r2)     // Catch:{ all -> 0x009f }
            monitor-exit(r0)     // Catch:{ all -> 0x009f }
            return
        L_0x0025:
            r1 = 10
            r8.d = r1     // Catch:{ all -> 0x009f }
            monitor-exit(r0)     // Catch:{ all -> 0x009f }
            com.liulishuo.filedownloader.DownloadTaskHunter$ICaptureTask r0 = r8.c
            com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask r0 = r0.ac()
            com.liulishuo.filedownloader.BaseDownloadTask r1 = r0.P()
            boolean r5 = com.liulishuo.filedownloader.FileDownloadMonitor.c()
            if (r5 == 0) goto L_0x0041
            com.liulishuo.filedownloader.FileDownloadMonitor$IMonitor r5 = com.liulishuo.filedownloader.FileDownloadMonitor.b()
            r5.a(r1)
        L_0x0041:
            boolean r5 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = "call start Url[%s], Path[%s] Listener[%s], Tag[%s]"
            r6 = 4
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String r7 = r1.m()
            r6[r4] = r7
            java.lang.String r7 = r1.p()
            r6[r3] = r7
            com.liulishuo.filedownloader.FileDownloadListener r7 = r1.t()
            r6[r2] = r7
            r2 = 3
            java.lang.Object r1 = r1.G()
            r6[r2] = r1
            com.liulishuo.filedownloader.util.FileDownloadLog.e(r8, r5, r6)
        L_0x0066:
            r8.s()     // Catch:{ Throwable -> 0x006b }
            r0 = 1
            goto L_0x007f
        L_0x006b:
            r1 = move-exception
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.a()
            r2.b((com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask) r0)
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.a()
            com.liulishuo.filedownloader.message.MessageSnapshot r1 = r8.a((java.lang.Throwable) r1)
            r2.a((com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask) r0, (com.liulishuo.filedownloader.message.MessageSnapshot) r1)
            r0 = 0
        L_0x007f:
            if (r0 == 0) goto L_0x0088
            com.liulishuo.filedownloader.FileDownloadTaskLauncher r0 = com.liulishuo.filedownloader.FileDownloadTaskLauncher.a()
            r0.a((com.liulishuo.filedownloader.ITaskHunter.IStarter) r8)
        L_0x0088:
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a
            if (r0 == 0) goto L_0x009e
            java.lang.String r0 = "the task[%d] has been into the launch pool."
            java.lang.Object[] r1 = new java.lang.Object[r3]
            int r2 = r8.t()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r4] = r2
            com.liulishuo.filedownloader.util.FileDownloadLog.e(r8, r0, r1)
        L_0x009e:
            return
        L_0x009f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.DownloadTaskHunter.e():void");
    }

    public boolean f() {
        if (FileDownloadStatus.a((int) g())) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "High concurrent cause, Already is over, can't pause again, %d %d", Byte.valueOf(g()), Integer.valueOf(this.c.ac().P().k()));
            }
            return false;
        }
        this.d = -2;
        BaseDownloadTask.IRunningTask ac = this.c.ac();
        BaseDownloadTask P = ac.P();
        FileDownloadTaskLauncher.a().b(this);
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.e(this, "the task[%d] has been expired from the launch pool.", Integer.valueOf(t()));
        }
        if (FileDownloader.a().j()) {
            FileDownloadServiceProxy.a().a(P.k());
        } else if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "request pause the task[%d] to the download service, but the download service isn't connected yet.", Integer.valueOf(P.k()));
        }
        FileDownloadList.a().b(ac);
        FileDownloadList.a().a(ac, MessageSnapshotTaker.a(P));
        FileDownloader.a().n().b(ac);
        return true;
    }

    public byte g() {
        return this.d;
    }

    public void h() {
        this.e = null;
        this.m = null;
        this.l = false;
        this.j = 0;
        this.n = false;
        this.k = false;
        this.h = 0;
        this.i = 0;
        this.f.a();
        if (FileDownloadStatus.a((int) this.d)) {
            this.f6381a.e();
            this.f6381a = new FileDownloadMessenger(this.c.ac(), this);
        } else {
            this.f6381a.a(this.c.ac(), this);
        }
        this.d = 0;
    }

    public void a(int i2) {
        this.g.a(i2);
    }

    public int b() {
        return this.g.b();
    }

    public long i() {
        return this.h;
    }

    public long j() {
        return this.i;
    }

    public Throwable k() {
        return this.e;
    }

    public int l() {
        return this.j;
    }

    public boolean m() {
        return this.n;
    }

    public boolean n() {
        return this.l;
    }

    public String o() {
        return this.m;
    }

    public boolean p() {
        return this.k;
    }

    public void q() {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "free the task %d, when the status is %d", Integer.valueOf(t()), Byte.valueOf(this.d));
        }
        this.d = 0;
    }

    private void s() throws IOException {
        File file;
        BaseDownloadTask P = this.c.ac().P();
        if (P.p() == null) {
            P.a(FileDownloadUtils.b(P.m()));
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "save Path is null to %s", P.p());
            }
        }
        if (P.q()) {
            file = new File(P.p());
        } else {
            String j2 = FileDownloadUtils.j(P.p());
            if (j2 != null) {
                file = new File(j2);
            } else {
                throw new InvalidParameterException(FileDownloadUtils.a("the provided mPath[%s] is invalid, can't find its directory", P.p()));
            }
        }
        if (!file.exists() && !file.mkdirs() && !file.exists()) {
            throw new IOException(FileDownloadUtils.a("Create parent directory failed, please make sure you have permission to create file or directory on the path: %s", file.getAbsolutePath()));
        }
    }

    private int t() {
        return this.c.ac().P().k();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        com.liulishuo.filedownloader.FileDownloadList.a().b(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007c, code lost:
        if (com.liulishuo.filedownloader.util.FileDownloadHelper.a(r0.k(), r0.s(), r0.C(), true) == false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007f, code lost:
        r0 = com.liulishuo.filedownloader.FileDownloadServiceProxy.a().a(r0.m(), r0.p(), r0.q(), r0.n(), r0.o(), r0.K(), r0.C(), r1.c.ab(), r0.O());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b0, code lost:
        if (r1.d != -2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b2, code lost:
        com.liulishuo.filedownloader.util.FileDownloadLog.d(r1, "High concurrent cause, this task %d will be paused,because of the status is paused, so the pause action must be applied", java.lang.Integer.valueOf(t()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c3, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c5, code lost:
        com.liulishuo.filedownloader.FileDownloadServiceProxy.a().a(t());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d1, code lost:
        if (r0 != false) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d7, code lost:
        if (r7.c(r6) != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d9, code lost:
        r0 = a((java.lang.Throwable) new java.lang.RuntimeException("Occur Unknown Error, when request to start maybe some problem in binder, maybe the process was killed in unexpected."));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ec, code lost:
        if (com.liulishuo.filedownloader.FileDownloadList.a().a(r6) == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ee, code lost:
        r7.b(r6);
        com.liulishuo.filedownloader.FileDownloadList.a().b(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00f8, code lost:
        com.liulishuo.filedownloader.FileDownloadList.a().a(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0100, code lost:
        r7.b(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void r() {
        /*
            r18 = this;
            r1 = r18
            byte r0 = r1.d
            r2 = 2
            r3 = 10
            r4 = 0
            r5 = 1
            if (r0 == r3) goto L_0x0025
            java.lang.String r0 = "High concurrent cause, this task %d will not start, because the of status isn't toLaunchPool: %d"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            int r3 = r18.t()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r4] = r3
            byte r3 = r1.d
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)
            r2[r5] = r3
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r1, r0, r2)
            return
        L_0x0025:
            com.liulishuo.filedownloader.DownloadTaskHunter$ICaptureTask r0 = r1.c
            com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask r6 = r0.ac()
            com.liulishuo.filedownloader.BaseDownloadTask r0 = r6.P()
            com.liulishuo.filedownloader.FileDownloader r7 = com.liulishuo.filedownloader.FileDownloader.a()
            com.liulishuo.filedownloader.ILostServiceConnectedHandler r7 = r7.n()
            boolean r8 = r7.c(r6)     // Catch:{ Throwable -> 0x0107 }
            if (r8 == 0) goto L_0x003e
            return
        L_0x003e:
            java.lang.Object r8 = r1.b     // Catch:{ Throwable -> 0x0107 }
            monitor-enter(r8)     // Catch:{ Throwable -> 0x0107 }
            byte r9 = r1.d     // Catch:{ all -> 0x0104 }
            if (r9 == r3) goto L_0x0060
            java.lang.String r0 = "High concurrent cause, this task %d will not start, the status can't assign to toFileDownloadService, because the status isn't toLaunchPool: %d"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0104 }
            int r3 = r18.t()     // Catch:{ all -> 0x0104 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0104 }
            r2[r4] = r3     // Catch:{ all -> 0x0104 }
            byte r3 = r1.d     // Catch:{ all -> 0x0104 }
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)     // Catch:{ all -> 0x0104 }
            r2[r5] = r3     // Catch:{ all -> 0x0104 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r1, r0, r2)     // Catch:{ all -> 0x0104 }
            monitor-exit(r8)     // Catch:{ all -> 0x0104 }
            return
        L_0x0060:
            r2 = 11
            r1.d = r2     // Catch:{ all -> 0x0104 }
            monitor-exit(r8)     // Catch:{ all -> 0x0104 }
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.a()     // Catch:{ Throwable -> 0x0107 }
            r2.b((com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask) r6)     // Catch:{ Throwable -> 0x0107 }
            int r2 = r0.k()     // Catch:{ Throwable -> 0x0107 }
            java.lang.String r3 = r0.s()     // Catch:{ Throwable -> 0x0107 }
            boolean r8 = r0.C()     // Catch:{ Throwable -> 0x0107 }
            boolean r2 = com.liulishuo.filedownloader.util.FileDownloadHelper.a((int) r2, (java.lang.String) r3, (boolean) r8, (boolean) r5)     // Catch:{ Throwable -> 0x0107 }
            if (r2 == 0) goto L_0x007f
            return
        L_0x007f:
            com.liulishuo.filedownloader.FileDownloadServiceProxy r8 = com.liulishuo.filedownloader.FileDownloadServiceProxy.a()     // Catch:{ Throwable -> 0x0107 }
            java.lang.String r9 = r0.m()     // Catch:{ Throwable -> 0x0107 }
            java.lang.String r10 = r0.p()     // Catch:{ Throwable -> 0x0107 }
            boolean r11 = r0.q()     // Catch:{ Throwable -> 0x0107 }
            int r12 = r0.n()     // Catch:{ Throwable -> 0x0107 }
            int r13 = r0.o()     // Catch:{ Throwable -> 0x0107 }
            int r14 = r0.K()     // Catch:{ Throwable -> 0x0107 }
            boolean r15 = r0.C()     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.DownloadTaskHunter$ICaptureTask r2 = r1.c     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.model.FileDownloadHeader r16 = r2.ab()     // Catch:{ Throwable -> 0x0107 }
            boolean r17 = r0.O()     // Catch:{ Throwable -> 0x0107 }
            boolean r0 = r8.a(r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ Throwable -> 0x0107 }
            byte r2 = r1.d     // Catch:{ Throwable -> 0x0107 }
            r3 = -2
            if (r2 != r3) goto L_0x00d1
            java.lang.String r2 = "High concurrent cause, this task %d will be paused,because of the status is paused, so the pause action must be applied"
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0107 }
            int r5 = r18.t()     // Catch:{ Throwable -> 0x0107 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x0107 }
            r3[r4] = r5     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r1, r2, r3)     // Catch:{ Throwable -> 0x0107 }
            if (r0 == 0) goto L_0x00d0
            com.liulishuo.filedownloader.FileDownloadServiceProxy r0 = com.liulishuo.filedownloader.FileDownloadServiceProxy.a()     // Catch:{ Throwable -> 0x0107 }
            int r2 = r18.t()     // Catch:{ Throwable -> 0x0107 }
            r0.a((int) r2)     // Catch:{ Throwable -> 0x0107 }
        L_0x00d0:
            return
        L_0x00d1:
            if (r0 != 0) goto L_0x0100
            boolean r0 = r7.c(r6)     // Catch:{ Throwable -> 0x0107 }
            if (r0 != 0) goto L_0x0116
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ Throwable -> 0x0107 }
            java.lang.String r2 = "Occur Unknown Error, when request to start maybe some problem in binder, maybe the process was killed in unexpected."
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.message.MessageSnapshot r0 = r1.a((java.lang.Throwable) r0)     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.a()     // Catch:{ Throwable -> 0x0107 }
            boolean r2 = r2.a((com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask) r6)     // Catch:{ Throwable -> 0x0107 }
            if (r2 == 0) goto L_0x00f8
            r7.b(r6)     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.a()     // Catch:{ Throwable -> 0x0107 }
            r2.b((com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask) r6)     // Catch:{ Throwable -> 0x0107 }
        L_0x00f8:
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.a()     // Catch:{ Throwable -> 0x0107 }
            r2.a((com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask) r6, (com.liulishuo.filedownloader.message.MessageSnapshot) r0)     // Catch:{ Throwable -> 0x0107 }
            goto L_0x0116
        L_0x0100:
            r7.b(r6)     // Catch:{ Throwable -> 0x0107 }
            goto L_0x0116
        L_0x0104:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0104 }
            throw r0     // Catch:{ Throwable -> 0x0107 }
        L_0x0107:
            r0 = move-exception
            r0.printStackTrace()
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.a()
            com.liulishuo.filedownloader.message.MessageSnapshot r0 = r1.a((java.lang.Throwable) r0)
            r2.a((com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask) r6, (com.liulishuo.filedownloader.message.MessageSnapshot) r0)
        L_0x0116:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.DownloadTaskHunter.r():void");
    }

    public boolean a(FileDownloadListener fileDownloadListener) {
        return this.c.ac().P().t() == fileDownloadListener;
    }
}
