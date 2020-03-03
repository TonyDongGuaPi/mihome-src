package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.download.DownloadRunnable;
import com.liulishuo.filedownloader.download.FetchDataTask;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadHttpException;
import com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.services.FileDownloadDatabase;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class DownloadLaunchRunnable implements ProcessCallback, Runnable {
    private static final int k = 416;
    private static final int l = -1;
    private static final ThreadPoolExecutor q = FileDownloadExecutors.a(Integer.MAX_VALUE, "download-executor");
    private long A;
    private long B;
    private long C;

    /* renamed from: a  reason: collision with root package name */
    private final DownloadStatusCallback f6422a;
    private final int b;
    private final FileDownloadModel c;
    private final FileDownloadHeader d;
    private final boolean e;
    private final boolean f;
    private final FileDownloadDatabase g;
    private final IThreadPoolMonitor h;
    private boolean i;
    private int j;
    private final boolean m;
    private final ArrayList<DownloadRunnable> n;
    private FetchDataTask o;
    private boolean p;
    private boolean r;
    private boolean s;
    private boolean t;
    private final AtomicBoolean u;
    private volatile boolean v;
    private volatile boolean w;
    private volatile Exception x;
    private String y;
    private long z;

    private int h() {
        return 5;
    }

    private DownloadLaunchRunnable(FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor iThreadPoolMonitor, int i2, int i3, boolean z2, boolean z3, int i4) {
        this.b = 5;
        this.n = new ArrayList<>(5);
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.u = new AtomicBoolean(true);
        this.v = false;
        this.i = false;
        this.c = fileDownloadModel;
        this.d = fileDownloadHeader;
        this.e = z2;
        this.f = z3;
        this.g = CustomComponentHolder.a().c();
        this.m = CustomComponentHolder.a().e();
        this.h = iThreadPoolMonitor;
        this.j = i4;
        this.f6422a = new DownloadStatusCallback(fileDownloadModel, i4, i2, i3);
    }

    private DownloadLaunchRunnable(DownloadStatusCallback downloadStatusCallback, FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor iThreadPoolMonitor, int i2, int i3, boolean z2, boolean z3, int i4) {
        this.b = 5;
        this.n = new ArrayList<>(5);
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.u = new AtomicBoolean(true);
        this.v = false;
        this.i = false;
        this.c = fileDownloadModel;
        this.d = fileDownloadHeader;
        this.e = z2;
        this.f = z3;
        this.g = CustomComponentHolder.a().c();
        this.m = CustomComponentHolder.a().e();
        this.h = iThreadPoolMonitor;
        this.j = i4;
        this.f6422a = downloadStatusCallback;
    }

    static DownloadLaunchRunnable a(DownloadStatusCallback downloadStatusCallback, FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor iThreadPoolMonitor, int i2, int i3, boolean z2, boolean z3, int i4) {
        return new DownloadLaunchRunnable(downloadStatusCallback, fileDownloadModel, fileDownloadHeader, iThreadPoolMonitor, i2, i3, z2, z3, i4);
    }

    public void a() {
        this.v = true;
        if (this.o != null) {
            this.o.a();
        }
        Iterator it = ((ArrayList) this.n.clone()).iterator();
        while (it.hasNext()) {
            DownloadRunnable downloadRunnable = (DownloadRunnable) it.next();
            if (downloadRunnable != null) {
                downloadRunnable.a();
            }
        }
    }

    public void b() {
        if (this.c.n() > 1) {
            List<ConnectionModel> b2 = this.g.b(this.c.a());
            if (this.c.n() == b2.size()) {
                this.c.a(ConnectionModel.a(b2));
            } else {
                this.c.a(0);
                this.g.c(this.c.a());
            }
        }
        this.f6422a.c();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0216, code lost:
        if (r5 != null) goto L_0x0218;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        r5.f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x028b, code lost:
        if (r5 != null) goto L_0x0218;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x028e, code lost:
        r1.f6422a.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0295, code lost:
        if (r1.v == false) goto L_0x029d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0297, code lost:
        r1.f6422a.f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x029f, code lost:
        if (r1.w == false) goto L_0x02a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x02a1, code lost:
        r1.f6422a.a(r1.x);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:?, code lost:
        r1.f6422a.g();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x02af, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x02b0, code lost:
        r1.f6422a.a((java.lang.Exception) r0);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:120:0x0239 */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0249 A[Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239, all -> 0x02c1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x025b  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0280 A[Catch:{ all -> 0x0235 }] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x02bd A[SYNTHETIC, Splitter:B:164:0x02bd] */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0288 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r17 = this;
            r1 = r17
            r0 = 10
            r2 = 0
            android.os.Process.setThreadPriority(r0)     // Catch:{ all -> 0x02c1 }
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r1.c     // Catch:{ all -> 0x02c1 }
            byte r0 = r0.f()     // Catch:{ all -> 0x02c1 }
            r3 = -2
            r4 = 1
            if (r0 == r4) goto L_0x008f
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r1.c     // Catch:{ all -> 0x02c1 }
            byte r0 = r0.f()     // Catch:{ all -> 0x02c1 }
            if (r0 != r3) goto L_0x0032
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x02c1 }
            if (r0 == 0) goto L_0x0062
            java.lang.String r0 = "High concurrent cause, start runnable but already paused %d"
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x02c1 }
            com.liulishuo.filedownloader.model.FileDownloadModel r4 = r1.c     // Catch:{ all -> 0x02c1 }
            int r4 = r4.a()     // Catch:{ all -> 0x02c1 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x02c1 }
            r3[r2] = r4     // Catch:{ all -> 0x02c1 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r1, r0, r3)     // Catch:{ all -> 0x02c1 }
            goto L_0x0062
        L_0x0032:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x02c1 }
            java.lang.String r3 = "Task[%d] can't start the download runnable, because its status is %d not %d"
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x02c1 }
            com.liulishuo.filedownloader.model.FileDownloadModel r6 = r1.c     // Catch:{ all -> 0x02c1 }
            int r6 = r6.a()     // Catch:{ all -> 0x02c1 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x02c1 }
            r5[r2] = r6     // Catch:{ all -> 0x02c1 }
            com.liulishuo.filedownloader.model.FileDownloadModel r6 = r1.c     // Catch:{ all -> 0x02c1 }
            byte r6 = r6.f()     // Catch:{ all -> 0x02c1 }
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)     // Catch:{ all -> 0x02c1 }
            r5[r4] = r6     // Catch:{ all -> 0x02c1 }
            r6 = 2
            java.lang.Byte r4 = java.lang.Byte.valueOf(r4)     // Catch:{ all -> 0x02c1 }
            r5[r6] = r4     // Catch:{ all -> 0x02c1 }
            java.lang.String r3 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r3, (java.lang.Object[]) r5)     // Catch:{ all -> 0x02c1 }
            r0.<init>(r3)     // Catch:{ all -> 0x02c1 }
            r1.b(r0)     // Catch:{ all -> 0x02c1 }
        L_0x0062:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.b()
            boolean r0 = r1.v
            if (r0 == 0) goto L_0x0071
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.f()
            goto L_0x0089
        L_0x0071:
            boolean r0 = r1.w
            if (r0 == 0) goto L_0x007d
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            java.lang.Exception r3 = r1.x
            r0.a((java.lang.Exception) r3)
            goto L_0x0089
        L_0x007d:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a     // Catch:{ IOException -> 0x0083 }
            r0.g()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0089
        L_0x0083:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r3 = r1.f6422a
            r3.a((java.lang.Exception) r0)
        L_0x0089:
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.u
            r0.set(r2)
            return
        L_0x008f:
            boolean r0 = r1.v     // Catch:{ all -> 0x02c1 }
            if (r0 != 0) goto L_0x0098
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a     // Catch:{ all -> 0x02c1 }
            r0.d()     // Catch:{ all -> 0x02c1 }
        L_0x0098:
            boolean r0 = r1.v     // Catch:{ all -> 0x02c1 }
            if (r0 == 0) goto L_0x00e0
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x02c1 }
            if (r0 == 0) goto L_0x00b3
            java.lang.String r0 = "High concurrent cause, start runnable but already paused %d"
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x02c1 }
            com.liulishuo.filedownloader.model.FileDownloadModel r4 = r1.c     // Catch:{ all -> 0x02c1 }
            int r4 = r4.a()     // Catch:{ all -> 0x02c1 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x02c1 }
            r3[r2] = r4     // Catch:{ all -> 0x02c1 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r1, r0, r3)     // Catch:{ all -> 0x02c1 }
        L_0x00b3:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.b()
            boolean r0 = r1.v
            if (r0 == 0) goto L_0x00c2
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.f()
            goto L_0x00da
        L_0x00c2:
            boolean r0 = r1.w
            if (r0 == 0) goto L_0x00ce
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            java.lang.Exception r3 = r1.x
            r0.a((java.lang.Exception) r3)
            goto L_0x00da
        L_0x00ce:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a     // Catch:{ IOException -> 0x00d4 }
            r0.g()     // Catch:{ IOException -> 0x00d4 }
            goto L_0x00da
        L_0x00d4:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r3 = r1.f6422a
            r3.a((java.lang.Exception) r0)
        L_0x00da:
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.u
            r0.set(r2)
            return
        L_0x00e0:
            r5 = 0
            r17.i()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.services.FileDownloadDatabase r0 = r1.g     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.model.FileDownloadModel r6 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            int r6 = r6.a()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            java.util.List r0 = r0.b((int) r6)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.download.ConnectionProfile r6 = r1.a((java.util.List<com.liulishuo.filedownloader.model.ConnectionModel>) r0)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.download.ConnectTask$Builder r7 = new com.liulishuo.filedownloader.download.ConnectTask$Builder     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            r7.<init>()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.model.FileDownloadModel r8 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            int r8 = r8.a()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.download.ConnectTask$Builder r7 = r7.a((int) r8)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.model.FileDownloadModel r8 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            java.lang.String r8 = r8.b()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.download.ConnectTask$Builder r7 = r7.a((java.lang.String) r8)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.model.FileDownloadModel r8 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            java.lang.String r8 = r8.j()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.download.ConnectTask$Builder r7 = r7.b(r8)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.model.FileDownloadHeader r8 = r1.d     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.download.ConnectTask$Builder r7 = r7.a((com.liulishuo.filedownloader.model.FileDownloadHeader) r8)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.download.ConnectTask$Builder r6 = r7.a((com.liulishuo.filedownloader.download.ConnectionProfile) r6)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.download.ConnectTask r6 = r6.a()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            com.liulishuo.filedownloader.connection.FileDownloadConnection r7 = r6.a()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            java.util.Map r8 = r6.d()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r1.a((java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r8, (com.liulishuo.filedownloader.download.ConnectTask) r6, (com.liulishuo.filedownloader.connection.FileDownloadConnection) r7)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            boolean r8 = r1.v     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            if (r8 == 0) goto L_0x016b
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r0.a((byte) r3)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            if (r7 == 0) goto L_0x013e
            r7.f()     // Catch:{ all -> 0x02c1 }
        L_0x013e:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.b()
            boolean r0 = r1.v
            if (r0 == 0) goto L_0x014d
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.f()
            goto L_0x0165
        L_0x014d:
            boolean r0 = r1.w
            if (r0 == 0) goto L_0x0159
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            java.lang.Exception r3 = r1.x
            r0.a((java.lang.Exception) r3)
            goto L_0x0165
        L_0x0159:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a     // Catch:{ IOException -> 0x015f }
            r0.g()     // Catch:{ IOException -> 0x015f }
            goto L_0x0165
        L_0x015f:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r3 = r1.f6422a
            r3.a((java.lang.Exception) r0)
        L_0x0165:
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.u
            r0.set(r2)
            return
        L_0x016b:
            r17.j()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            com.liulishuo.filedownloader.model.FileDownloadModel r8 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            long r13 = r8.h()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            com.liulishuo.filedownloader.model.FileDownloadModel r8 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            java.lang.String r8 = r8.e()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r1.a((long) r13, (java.lang.String) r8)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            boolean r8 = r17.g()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            if (r8 == 0) goto L_0x01ab
            boolean r8 = r1.r     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            if (r8 == 0) goto L_0x018f
            com.liulishuo.filedownloader.model.FileDownloadModel r8 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            int r8 = r8.n()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r15 = r13
            goto L_0x01ad
        L_0x018f:
            com.liulishuo.filedownloader.download.CustomComponentHolder r9 = com.liulishuo.filedownloader.download.CustomComponentHolder.a()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            com.liulishuo.filedownloader.model.FileDownloadModel r8 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            int r10 = r8.a()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            com.liulishuo.filedownloader.model.FileDownloadModel r8 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            java.lang.String r11 = r8.b()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            com.liulishuo.filedownloader.model.FileDownloadModel r8 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            java.lang.String r12 = r8.c()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r15 = r13
            int r8 = r9.a(r10, r11, r12, r13)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            goto L_0x01ad
        L_0x01ab:
            r15 = r13
            r8 = 1
        L_0x01ad:
            if (r8 <= 0) goto L_0x021d
            boolean r9 = r1.v     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            if (r9 == 0) goto L_0x01ea
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r1.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r0.a((byte) r3)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            if (r7 == 0) goto L_0x01bd
            r7.f()     // Catch:{ all -> 0x02c1 }
        L_0x01bd:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.b()
            boolean r0 = r1.v
            if (r0 == 0) goto L_0x01cc
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.f()
            goto L_0x01e4
        L_0x01cc:
            boolean r0 = r1.w
            if (r0 == 0) goto L_0x01d8
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            java.lang.Exception r3 = r1.x
            r0.a((java.lang.Exception) r3)
            goto L_0x01e4
        L_0x01d8:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a     // Catch:{ IOException -> 0x01de }
            r0.g()     // Catch:{ IOException -> 0x01de }
            goto L_0x01e4
        L_0x01de:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r3 = r1.f6422a
            r3.a((java.lang.Exception) r0)
        L_0x01e4:
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.u
            r0.set(r2)
            return
        L_0x01ea:
            if (r8 != r4) goto L_0x01ee
            r9 = 1
            goto L_0x01ef
        L_0x01ee:
            r9 = 0
        L_0x01ef:
            r1.p = r9     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            boolean r9 = r1.p     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            if (r9 == 0) goto L_0x01fe
            com.liulishuo.filedownloader.download.ConnectionProfile r0 = r6.e()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r1.a((com.liulishuo.filedownloader.download.ConnectionProfile) r0, (com.liulishuo.filedownloader.connection.FileDownloadConnection) r7)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r5 = r7
            goto L_0x0216
        L_0x01fe:
            if (r7 == 0) goto L_0x0204
            r7.f()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            goto L_0x0205
        L_0x0204:
            r5 = r7
        L_0x0205:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r6 = r1.f6422a     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            r6.e()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            boolean r6 = r1.r     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            if (r6 == 0) goto L_0x0212
            r1.a((int) r8, (java.util.List<com.liulishuo.filedownloader.model.ConnectionModel>) r0)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
            goto L_0x0216
        L_0x0212:
            r6 = r15
            r1.a((long) r6, (int) r8)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0279, DiscardSafely -> 0x0246, RetryDirectly -> 0x0239 }
        L_0x0216:
            if (r5 == 0) goto L_0x028e
        L_0x0218:
            r5.f()     // Catch:{ all -> 0x02c1 }
            goto L_0x028e
        L_0x021d:
            java.lang.IllegalAccessException r0 = new java.lang.IllegalAccessException     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            java.lang.String r5 = "invalid connection count %d, the connection count must be larger than 0"
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r6[r2] = r7     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            java.lang.String r5 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r5, (java.lang.Object[]) r6)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            r0.<init>(r5)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
            throw r0     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException | InterruptedException -> 0x0232, DiscardSafely -> 0x0247, RetryDirectly -> 0x0230, all -> 0x022d }
        L_0x022d:
            r0 = move-exception
            goto L_0x02bb
        L_0x0230:
            r5 = r7
            goto L_0x0239
        L_0x0232:
            r0 = move-exception
            r5 = r7
            goto L_0x027a
        L_0x0235:
            r0 = move-exception
            r7 = r5
            goto L_0x02bb
        L_0x0239:
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r1.c     // Catch:{ all -> 0x0235 }
            r6 = 5
            r0.a((byte) r6)     // Catch:{ all -> 0x0235 }
            if (r5 == 0) goto L_0x0098
        L_0x0241:
            r5.f()     // Catch:{ all -> 0x02c1 }
            goto L_0x0098
        L_0x0246:
            r7 = r5
        L_0x0247:
            if (r7 == 0) goto L_0x024c
            r7.f()     // Catch:{ all -> 0x02c1 }
        L_0x024c:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.b()
            boolean r0 = r1.v
            if (r0 == 0) goto L_0x025b
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.f()
            goto L_0x0273
        L_0x025b:
            boolean r0 = r1.w
            if (r0 == 0) goto L_0x0267
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            java.lang.Exception r3 = r1.x
            r0.a((java.lang.Exception) r3)
            goto L_0x0273
        L_0x0267:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a     // Catch:{ IOException -> 0x026d }
            r0.g()     // Catch:{ IOException -> 0x026d }
            goto L_0x0273
        L_0x026d:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r3 = r1.f6422a
            r3.a((java.lang.Exception) r0)
        L_0x0273:
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.u
            r0.set(r2)
            return
        L_0x0279:
            r0 = move-exception
        L_0x027a:
            boolean r6 = r1.a((java.lang.Exception) r0)     // Catch:{ all -> 0x0235 }
            if (r6 == 0) goto L_0x0288
            r6 = 0
            r1.a((java.lang.Exception) r0, (long) r6)     // Catch:{ all -> 0x0235 }
            if (r5 == 0) goto L_0x0098
            goto L_0x0241
        L_0x0288:
            r1.b(r0)     // Catch:{ all -> 0x0235 }
            if (r5 == 0) goto L_0x028e
            goto L_0x0218
        L_0x028e:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.b()
            boolean r0 = r1.v
            if (r0 == 0) goto L_0x029d
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.f()
            goto L_0x02b5
        L_0x029d:
            boolean r0 = r1.w
            if (r0 == 0) goto L_0x02a9
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            java.lang.Exception r3 = r1.x
            r0.a((java.lang.Exception) r3)
            goto L_0x02b5
        L_0x02a9:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a     // Catch:{ IOException -> 0x02af }
            r0.g()     // Catch:{ IOException -> 0x02af }
            goto L_0x02b5
        L_0x02af:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r3 = r1.f6422a
            r3.a((java.lang.Exception) r0)
        L_0x02b5:
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.u
            r0.set(r2)
            return
        L_0x02bb:
            if (r7 == 0) goto L_0x02c0
            r7.f()     // Catch:{ all -> 0x02c1 }
        L_0x02c0:
            throw r0     // Catch:{ all -> 0x02c1 }
        L_0x02c1:
            r0 = move-exception
            r3 = r0
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.b()
            boolean r0 = r1.v
            if (r0 != 0) goto L_0x02e5
            boolean r0 = r1.w
            if (r0 == 0) goto L_0x02d8
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            java.lang.Exception r4 = r1.x
            r0.a((java.lang.Exception) r4)
            goto L_0x02ea
        L_0x02d8:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a     // Catch:{ IOException -> 0x02de }
            r0.g()     // Catch:{ IOException -> 0x02de }
            goto L_0x02ea
        L_0x02de:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r4 = r1.f6422a
            r4.a((java.lang.Exception) r0)
            goto L_0x02ea
        L_0x02e5:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r1.f6422a
            r0.f()
        L_0x02ea:
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.u
            r0.set(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadLaunchRunnable.run():void");
    }

    private boolean g() {
        if ((!this.r || this.c.n() > 1) && this.s && this.m && !this.t) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.liulishuo.filedownloader.download.ConnectionProfile a(java.util.List<com.liulishuo.filedownloader.model.ConnectionModel> r21) {
        /*
            r20 = this;
            r0 = r20
            com.liulishuo.filedownloader.model.FileDownloadModel r1 = r0.c
            int r1 = r1.n()
            com.liulishuo.filedownloader.model.FileDownloadModel r2 = r0.c
            java.lang.String r2 = r2.e()
            com.liulishuo.filedownloader.model.FileDownloadModel r3 = r0.c
            java.lang.String r3 = r3.d()
            r4 = 0
            r5 = 1
            if (r1 <= r5) goto L_0x001a
            r6 = 1
            goto L_0x001b
        L_0x001a:
            r6 = 0
        L_0x001b:
            r7 = 0
            if (r6 == 0) goto L_0x0025
            boolean r9 = r0.m
            if (r9 != 0) goto L_0x0025
        L_0x0023:
            r14 = r7
            goto L_0x0057
        L_0x0025:
            com.liulishuo.filedownloader.model.FileDownloadModel r9 = r0.c
            int r9 = r9.a()
            com.liulishuo.filedownloader.model.FileDownloadModel r10 = r0.c
            boolean r9 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((int) r9, (com.liulishuo.filedownloader.model.FileDownloadModel) r10)
            if (r9 == 0) goto L_0x0023
            boolean r9 = r0.m
            if (r9 != 0) goto L_0x0042
            java.io.File r1 = new java.io.File
            r1.<init>(r2)
            long r9 = r1.length()
        L_0x0040:
            r14 = r9
            goto L_0x0057
        L_0x0042:
            if (r6 == 0) goto L_0x0050
            int r6 = r21.size()
            if (r1 == r6) goto L_0x004b
            goto L_0x0023
        L_0x004b:
            long r9 = com.liulishuo.filedownloader.model.ConnectionModel.a((java.util.List<com.liulishuo.filedownloader.model.ConnectionModel>) r21)
            goto L_0x0040
        L_0x0050:
            com.liulishuo.filedownloader.model.FileDownloadModel r1 = r0.c
            long r9 = r1.g()
            goto L_0x0040
        L_0x0057:
            com.liulishuo.filedownloader.model.FileDownloadModel r1 = r0.c
            r1.a((long) r14)
            int r1 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
            if (r1 <= 0) goto L_0x0061
            r4 = 1
        L_0x0061:
            r0.r = r4
            boolean r1 = r0.r
            if (r1 != 0) goto L_0x0075
            com.liulishuo.filedownloader.services.FileDownloadDatabase r1 = r0.g
            com.liulishuo.filedownloader.model.FileDownloadModel r4 = r0.c
            int r4 = r4.a()
            r1.c(r4)
            com.liulishuo.filedownloader.util.FileDownloadUtils.c(r3, r2)
        L_0x0075:
            com.liulishuo.filedownloader.download.ConnectionProfile r1 = new com.liulishuo.filedownloader.download.ConnectionProfile
            r12 = 0
            r16 = 0
            com.liulishuo.filedownloader.model.FileDownloadModel r2 = r0.c
            long r2 = r2.h()
            long r18 = r2 - r14
            r11 = r1
            r11.<init>(r12, r14, r16, r18)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadLaunchRunnable.a(java.util.List):com.liulishuo.filedownloader.download.ConnectionProfile");
    }

    private void a(Map<String, List<String>> map, ConnectTask connectTask, FileDownloadConnection fileDownloadConnection) throws IOException, RetryDirectly, IllegalArgumentException {
        boolean z2;
        FileDownloadConnection fileDownloadConnection2 = fileDownloadConnection;
        int a2 = this.c.a();
        int e2 = fileDownloadConnection.e();
        this.s = e2 == 206 || e2 == 1;
        boolean z3 = e2 == 200 || e2 == 201 || e2 == 0;
        String j2 = this.c.j();
        String a3 = FileDownloadUtils.a(a2, fileDownloadConnection2);
        if (e2 != 412 && ((j2 == null || j2.equals(a3) || (!z3 && !this.s)) && ((e2 != 201 || !connectTask.b()) && (e2 != 416 || this.c.g() <= 0)))) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2) {
            if (this.r) {
                FileDownloadLog.d(this, "there is precondition failed on this request[%d] with old etag[%s]、new etag[%s]、response code is %d", Integer.valueOf(a2), j2, a3, Integer.valueOf(e2));
            }
            this.g.c(this.c.a());
            FileDownloadUtils.c(this.c.d(), this.c.e());
            this.r = false;
            if (j2 != null && j2.equals(a3)) {
                FileDownloadLog.d(this, "the old etag[%s] is the same to the new etag[%s], but the response status code is %d not Partial(206), so wo have to start this task from very beginning for task[%d]!", j2, a3, Integer.valueOf(e2), Integer.valueOf(a2));
                a3 = null;
            }
            this.c.a(0);
            this.c.c(0);
            this.c.b(a3);
            this.c.o();
            this.g.a(a2, this.c.j(), this.c.g(), this.c.h(), this.c.n());
            throw new RetryDirectly();
        }
        this.y = connectTask.c();
        if (this.s || z3) {
            long b2 = FileDownloadUtils.b(a2, fileDownloadConnection2);
            String a4 = this.c.l() ? FileDownloadUtils.a(fileDownloadConnection2, this.c.b()) : null;
            this.t = b2 == -1;
            this.f6422a.a(this.r && this.s, !this.t ? this.c.g() + b2 : b2, a3, a4);
            return;
        }
        throw new FileDownloadHttpException(e2, map, fileDownloadConnection.c());
    }

    private void a(ConnectionProfile connectionProfile, FileDownloadConnection fileDownloadConnection) throws IOException, IllegalAccessException {
        if (!this.s) {
            this.c.a(0);
            connectionProfile = new ConnectionProfile(0, 0, connectionProfile.c, connectionProfile.d);
        }
        FetchDataTask.Builder builder = new FetchDataTask.Builder();
        builder.a((ProcessCallback) this).b(this.c.a()).a(-1).a(this.f).a(fileDownloadConnection).a(connectionProfile).a(this.c.e());
        this.c.b(1);
        this.g.a(this.c.a(), 1);
        this.o = builder.a();
        if (this.v) {
            this.c.a((byte) -2);
            this.o.a();
            return;
        }
        this.o.b();
    }

    private void a(int i2, List<ConnectionModel> list) throws InterruptedException {
        if (i2 <= 1 || list.size() != i2) {
            throw new IllegalArgumentException();
        }
        a(list, this.c.h());
    }

    private void a(long j2, int i2) throws InterruptedException {
        long j3 = j2;
        int i3 = i2;
        long j4 = j3 / ((long) i3);
        int a2 = this.c.a();
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        long j5 = 0;
        while (i4 < i3) {
            long j6 = i4 == i3 + -1 ? 0 : (j5 + j4) - 1;
            ConnectionModel connectionModel = new ConnectionModel();
            connectionModel.a(a2);
            connectionModel.b(i4);
            connectionModel.a(j5);
            connectionModel.b(j5);
            connectionModel.c(j6);
            arrayList.add(connectionModel);
            this.g.a(connectionModel);
            j5 += j4;
            i4++;
        }
        this.c.b(i3);
        this.g.a(a2, i3);
        a((List<ConnectionModel>) arrayList, j3);
    }

    private void a(List<ConnectionModel> list, long j2) throws InterruptedException {
        long e2;
        int a2 = this.c.a();
        String j3 = this.c.j();
        String b2 = this.y != null ? this.y : this.c.b();
        String e3 = this.c.e();
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "fetch data with multiple connection(count: [%d]) for task[%d] totalLength[%d]", Integer.valueOf(list.size()), Integer.valueOf(a2), Long.valueOf(j2));
        }
        boolean z2 = this.r;
        long j4 = 0;
        long j5 = 0;
        for (ConnectionModel next : list) {
            if (next.e() == j4) {
                e2 = j2 - next.d();
            } else {
                e2 = (next.e() - next.d()) + 1;
            }
            long j6 = e2;
            j5 += next.d() - next.c();
            if (j6 != j4) {
                DownloadRunnable a3 = new DownloadRunnable.Builder().a(a2).a(Integer.valueOf(next.b())).a((ProcessCallback) this).a(b2).b(z2 ? j3 : null).a(this.d).a(this.f).a(new ConnectionProfile(next.c(), next.d(), next.e(), j6)).c(e3).a();
                if (FileDownloadLog.f6465a) {
                    FileDownloadLog.c(this, "enable multiple connection: %s", next);
                }
                if (a3 != null) {
                    this.n.add(a3);
                } else {
                    throw new IllegalArgumentException("the download runnable must not be null!");
                }
            } else if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "pass connection[%d-%d], because it has been completed", Integer.valueOf(next.a()), Integer.valueOf(next.b()));
            }
            j4 = 0;
        }
        if (j5 != this.c.g()) {
            FileDownloadLog.d(this, "correct the sofar[%d] from connection table[%d]", Long.valueOf(this.c.g()), Long.valueOf(j5));
            this.c.a(j5);
        }
        ArrayList arrayList = new ArrayList(this.n.size());
        Iterator<DownloadRunnable> it = this.n.iterator();
        while (it.hasNext()) {
            DownloadRunnable next2 = it.next();
            if (this.v) {
                next2.a();
            } else {
                arrayList.add(Executors.callable(next2));
            }
        }
        if (this.v) {
            this.c.a((byte) -2);
            return;
        }
        List<Future> invokeAll = q.invokeAll(arrayList);
        if (FileDownloadLog.f6465a) {
            for (Future future : invokeAll) {
                FileDownloadLog.c(this, "finish sub-task for [%d] %B %B", Integer.valueOf(a2), Boolean.valueOf(future.isDone()), Boolean.valueOf(future.isCancelled()));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(long r10, java.lang.String r12) throws java.io.IOException, java.lang.IllegalAccessException {
        /*
            r9 = this;
            r0 = -1
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            r0 = 0
            if (r2 == 0) goto L_0x0043
            com.liulishuo.filedownloader.model.FileDownloadModel r1 = r9.c     // Catch:{ all -> 0x003b }
            java.lang.String r1 = r1.e()     // Catch:{ all -> 0x003b }
            com.liulishuo.filedownloader.stream.FileDownloadOutputStream r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.n(r1)     // Catch:{ all -> 0x003b }
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0039 }
            r0.<init>(r12)     // Catch:{ all -> 0x0039 }
            long r7 = r0.length()     // Catch:{ all -> 0x0039 }
            r0 = 0
            long r5 = r10 - r7
            long r3 = com.liulishuo.filedownloader.util.FileDownloadUtils.h(r12)     // Catch:{ all -> 0x0039 }
            int r12 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x0032
            com.liulishuo.filedownloader.util.FileDownloadProperties r12 = com.liulishuo.filedownloader.util.FileDownloadProperties.a()     // Catch:{ all -> 0x0039 }
            boolean r12 = r12.f     // Catch:{ all -> 0x0039 }
            if (r12 != 0) goto L_0x0030
            r1.b(r10)     // Catch:{ all -> 0x0039 }
        L_0x0030:
            r0 = r1
            goto L_0x0043
        L_0x0032:
            com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException r10 = new com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException     // Catch:{ all -> 0x0039 }
            r2 = r10
            r2.<init>(r3, r5, r7)     // Catch:{ all -> 0x0039 }
            throw r10     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r10 = move-exception
            goto L_0x003d
        L_0x003b:
            r10 = move-exception
            r1 = r0
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            r1.b()
        L_0x0042:
            throw r10
        L_0x0043:
            if (r0 == 0) goto L_0x0048
            r0.b()
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadLaunchRunnable.a(long, java.lang.String):void");
    }

    public void a(long j2) {
        if (!this.v) {
            this.f6422a.a(j2);
        }
    }

    public void a(DownloadRunnable downloadRunnable, long j2, long j3) {
        int i2;
        if (!this.v) {
            if (downloadRunnable == null) {
                i2 = -1;
            } else {
                i2 = downloadRunnable.f6424a;
            }
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "the connection has been completed(%d): [%d, %d)  %d", Integer.valueOf(i2), Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(this.c.h()));
            }
            if (!this.p) {
                synchronized (this.n) {
                    this.n.remove(downloadRunnable);
                }
            } else if (j2 != 0 && j3 != this.c.h()) {
                FileDownloadLog.a(this, "the single task not completed corrected(%d, %d != %d) for task(%d)", Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(this.c.h()), Integer.valueOf(this.c.a()));
            }
        } else if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "the task[%d] has already been paused, so pass the completed callback", Integer.valueOf(this.c.a()));
        }
    }

    public boolean a(Exception exc) {
        if (exc instanceof FileDownloadHttpException) {
            int code = ((FileDownloadHttpException) exc).getCode();
            if (this.p && code == 416 && !this.i) {
                FileDownloadUtils.c(this.c.d(), this.c.e());
                this.i = true;
                return true;
            }
        }
        if (this.j <= 0 || (exc instanceof FileDownloadGiveUpRetryException)) {
            return false;
        }
        return true;
    }

    public void b(Exception exc) {
        this.w = true;
        this.x = exc;
        if (!this.v) {
            Iterator it = ((ArrayList) this.n.clone()).iterator();
            while (it.hasNext()) {
                DownloadRunnable downloadRunnable = (DownloadRunnable) it.next();
                if (downloadRunnable != null) {
                    downloadRunnable.b();
                }
            }
        } else if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "the task[%d] has already been paused, so pass the error callback", Integer.valueOf(this.c.a()));
        }
    }

    public void a(Exception exc, long j2) {
        if (!this.v) {
            int i2 = this.j;
            this.j = i2 - 1;
            if (i2 < 0) {
                FileDownloadLog.a(this, "valid retry times is less than 0(%d) for download task(%d)", Integer.valueOf(this.j), Integer.valueOf(this.c.a()));
            }
            DownloadStatusCallback downloadStatusCallback = this.f6422a;
            int i3 = this.j;
            this.j = i3 - 1;
            downloadStatusCallback.a(exc, i3, j2);
        } else if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "the task[%d] has already been paused, so pass the retry callback", Integer.valueOf(this.c.a()));
        }
    }

    public void c() {
        this.g.a(this.c.a(), this.c.g());
    }

    private void i() throws FileDownloadGiveUpRetryException {
        if (this.f && !FileDownloadUtils.l("android.permission.ACCESS_NETWORK_STATE")) {
            throw new FileDownloadGiveUpRetryException(FileDownloadUtils.a("Task[%d] can't start the download runnable, because this task require wifi, but user application nor current process has %s, so we can't check whether the network type connection.", Integer.valueOf(this.c.a()), "android.permission.ACCESS_NETWORK_STATE"));
        } else if (this.f && FileDownloadUtils.e()) {
            throw new FileDownloadNetworkPolicyException();
        }
    }

    private void j() throws RetryDirectly, DiscardSafely {
        int a2 = this.c.a();
        if (this.c.l()) {
            String d2 = this.c.d();
            int b2 = FileDownloadUtils.b(this.c.b(), d2);
            if (!FileDownloadHelper.a(a2, d2, this.e, false)) {
                FileDownloadModel a3 = this.g.a(b2);
                if (a3 != null) {
                    if (!FileDownloadHelper.a(a2, a3, this.h, false)) {
                        List<ConnectionModel> b3 = this.g.b(b2);
                        this.g.d(b2);
                        this.g.c(b2);
                        FileDownloadUtils.p(this.c.d());
                        if (FileDownloadUtils.a(b2, a3)) {
                            this.c.a(a3.g());
                            this.c.c(a3.h());
                            this.c.b(a3.j());
                            this.c.b(a3.n());
                            this.g.b(this.c);
                            if (b3 != null) {
                                for (ConnectionModel next : b3) {
                                    next.a(a2);
                                    this.g.a(next);
                                }
                            }
                            throw new RetryDirectly();
                        }
                    } else {
                        this.g.d(a2);
                        this.g.c(a2);
                        throw new DiscardSafely();
                    }
                }
                if (FileDownloadHelper.a(a2, this.c.g(), this.c.e(), d2, this.h)) {
                    this.g.d(a2);
                    this.g.c(a2);
                    throw new DiscardSafely();
                }
                return;
            }
            this.g.d(a2);
            this.g.c(a2);
            throw new DiscardSafely();
        }
    }

    public int d() {
        return this.c.a();
    }

    public boolean e() {
        return this.u.get() || this.f6422a.a();
    }

    public String f() {
        return this.c.e();
    }

    class RetryDirectly extends Throwable {
        RetryDirectly() {
        }
    }

    class DiscardSafely extends Throwable {
        DiscardSafely() {
        }
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private FileDownloadModel f6423a;
        private FileDownloadHeader b;
        private IThreadPoolMonitor c;
        private Integer d;
        private Integer e;
        private Boolean f;
        private Boolean g;
        private Integer h;

        public Builder a(FileDownloadModel fileDownloadModel) {
            this.f6423a = fileDownloadModel;
            return this;
        }

        public Builder a(FileDownloadHeader fileDownloadHeader) {
            this.b = fileDownloadHeader;
            return this;
        }

        public Builder a(IThreadPoolMonitor iThreadPoolMonitor) {
            this.c = iThreadPoolMonitor;
            return this;
        }

        public Builder a(Integer num) {
            this.d = num;
            return this;
        }

        public Builder b(Integer num) {
            this.e = num;
            return this;
        }

        public Builder a(Boolean bool) {
            this.f = bool;
            return this;
        }

        public Builder b(Boolean bool) {
            this.g = bool;
            return this;
        }

        public Builder c(Integer num) {
            this.h = num;
            return this;
        }

        public DownloadLaunchRunnable a() {
            if (this.f6423a != null && this.c != null && this.d != null && this.e != null && this.f != null && this.g != null && this.h != null) {
                return new DownloadLaunchRunnable(this.f6423a, this.b, this.c, this.d.intValue(), this.e.intValue(), this.f.booleanValue(), this.g.booleanValue(), this.h.intValue());
            }
            throw new IllegalArgumentException();
        }
    }
}
