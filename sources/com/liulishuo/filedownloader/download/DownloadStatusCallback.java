package com.liulishuo.filedownloader.download;

import android.database.sqlite.SQLiteFullException;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import com.liulishuo.filedownloader.services.FileDownloadDatabase;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

public class DownloadStatusCallback implements Handler.Callback {
    private static final int e = 1;
    private static final int f = 5;
    private static final int g = -1;
    private static final String q = "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread.";

    /* renamed from: a  reason: collision with root package name */
    private final FileDownloadModel f6426a;
    private final FileDownloadDatabase b;
    private final ProcessParams c;
    private final int d;
    private final int h;
    private final int i;
    private long j;
    private Handler k;
    private HandlerThread l;
    private volatile boolean m = false;
    private volatile Thread n;
    private volatile long o = 0;
    private final AtomicLong p = new AtomicLong();
    private volatile boolean r;
    private boolean s = true;

    DownloadStatusCallback(FileDownloadModel fileDownloadModel, int i2, int i3, int i4) {
        this.f6426a = fileDownloadModel;
        this.b = CustomComponentHolder.a().c();
        this.h = i3 >= 5 ? i3 : 5;
        this.i = i4;
        this.c = new ProcessParams();
        this.d = i2;
    }

    public boolean a() {
        return this.l != null && this.l.isAlive();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (this.k != null) {
            this.k.removeCallbacksAndMessages((Object) null);
            this.l.quit();
            this.n = Thread.currentThread();
            while (this.m) {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
            }
            this.n = null;
        }
    }

    public void c() {
        this.f6426a.a((byte) 1);
        this.b.e(this.f6426a.a());
        a((byte) 1);
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.f6426a.a((byte) 6);
        a((byte) 6);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, long j2, String str, String str2) throws IllegalArgumentException {
        String j3 = this.f6426a.j();
        if (j3 == null || j3.equals(str)) {
            this.c.a(z);
            this.f6426a.a((byte) 2);
            this.f6426a.c(j2);
            this.f6426a.b(str);
            this.f6426a.d(str2);
            this.b.a(this.f6426a.a(), j2, str, str2);
            a((byte) 2);
            this.j = a(j2, (long) this.i);
            this.r = true;
            return;
        }
        throw new IllegalArgumentException(FileDownloadUtils.a("callback onConnected must with precondition succeed, but the etag is changes(%s != %s)", str, j3));
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.l = new HandlerThread("source-status-callback");
        this.l.start();
        this.k = new Handler(this.l.getLooper(), this);
    }

    /* access modifiers changed from: package-private */
    public void a(long j2) {
        this.p.addAndGet(j2);
        this.f6426a.b(j2);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean b2 = b(elapsedRealtime);
        if (this.k == null) {
            a(elapsedRealtime, b2);
        } else if (b2) {
            a(this.k.obtainMessage(3));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Exception exc, int i2, long j2) {
        this.p.set(0);
        this.f6426a.b(-j2);
        if (this.k == null) {
            a(exc, i2);
        } else {
            a(this.k.obtainMessage(5, i2, 0, exc));
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        k();
    }

    /* access modifiers changed from: package-private */
    public void a(Exception exc) {
        c(exc);
    }

    /* access modifiers changed from: package-private */
    public void g() throws IOException {
        if (!j()) {
            i();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(android.os.Message r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            android.os.HandlerThread r0 = r4.l     // Catch:{ all -> 0x0045 }
            boolean r0 = r0.isAlive()     // Catch:{ all -> 0x0045 }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0020
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread."
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0045 }
            int r5 = r5.what     // Catch:{ all -> 0x0045 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0045 }
            r2[r1] = r5     // Catch:{ all -> 0x0045 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r4, r0, r2)     // Catch:{ all -> 0x0045 }
        L_0x001e:
            monitor-exit(r4)
            return
        L_0x0020:
            android.os.Handler r0 = r4.k     // Catch:{ IllegalStateException -> 0x0026 }
            r0.sendMessage(r5)     // Catch:{ IllegalStateException -> 0x0026 }
            goto L_0x0042
        L_0x0026:
            r0 = move-exception
            android.os.HandlerThread r3 = r4.l     // Catch:{ all -> 0x0045 }
            boolean r3 = r3.isAlive()     // Catch:{ all -> 0x0045 }
            if (r3 != 0) goto L_0x0044
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x0042
            java.lang.String r0 = "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread."
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0045 }
            int r5 = r5.what     // Catch:{ all -> 0x0045 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0045 }
            r2[r1] = r5     // Catch:{ all -> 0x0045 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r4, r0, r2)     // Catch:{ all -> 0x0045 }
        L_0x0042:
            monitor-exit(r4)
            return
        L_0x0044:
            throw r0     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadStatusCallback.a(android.os.Message):void");
    }

    private static long a(long j2, long j3) {
        if (j3 <= 0) {
            return -1;
        }
        if (j2 == -1) {
            return 1;
        }
        long j4 = j2 / (j3 + 1);
        if (j4 <= 0) {
            return 1;
        }
        return j4;
    }

    private Exception b(Exception exc) {
        long j2;
        String e2 = this.f6426a.e();
        if ((!this.f6426a.i() && !FileDownloadProperties.a().f) || !(exc instanceof IOException) || !new File(e2).exists()) {
            return exc;
        }
        long h2 = FileDownloadUtils.h(e2);
        if (h2 > 4096) {
            return exc;
        }
        File file = new File(e2);
        if (!file.exists()) {
            FileDownloadLog.a((Object) this, (Throwable) exc, "Exception with: free space isn't enough, and the target file not exist.", new Object[0]);
            j2 = 0;
        } else {
            j2 = file.length();
        }
        if (Build.VERSION.SDK_INT >= 9) {
            return new FileDownloadOutOfSpaceException(h2, 4096, j2, exc);
        }
        return new FileDownloadOutOfSpaceException(h2, 4096, j2);
    }

    private void a(SQLiteFullException sQLiteFullException) {
        int a2 = this.f6426a.a();
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "the data of the task[%d] is dirty, because the SQLite full exception[%s], so remove it from the database directly.", Integer.valueOf(a2), sQLiteFullException.toString());
        }
        this.f6426a.c(sQLiteFullException.toString());
        this.f6426a.a((byte) -1);
        this.b.d(a2);
        this.b.c(a2);
    }

    /* JADX INFO: finally extract failed */
    private void h() throws IOException {
        String e2 = this.f6426a.e();
        String d2 = this.f6426a.d();
        File file = new File(e2);
        try {
            File file2 = new File(d2);
            if (file2.exists()) {
                long length = file2.length();
                if (file2.delete()) {
                    FileDownloadLog.d(this, "The target file([%s], [%d]) will be replaced with the new downloaded file[%d]", d2, Long.valueOf(length), Long.valueOf(file.length()));
                } else {
                    throw new IOException(FileDownloadUtils.a("Can't delete the old file([%s], [%d]), so can't replace it with the new downloaded one.", d2, Long.valueOf(length)));
                }
            }
            if (!file.renameTo(file2)) {
                throw new IOException(FileDownloadUtils.a("Can't rename the  temp downloaded file(%s) to the target file(%s)", e2, d2));
            } else if (file.exists() && !file.delete()) {
                FileDownloadLog.d(this, "delete the temp file(%s) failed, on completed downloading.", e2);
            }
        } catch (Throwable th) {
            if (file.exists() && !file.delete()) {
                FileDownloadLog.d(this, "delete the temp file(%s) failed, on completed downloading.", e2);
            }
            throw th;
        }
    }

    public boolean handleMessage(Message message) {
        this.m = true;
        int i2 = message.what;
        if (i2 == 3) {
            a(SystemClock.elapsedRealtime(), true);
        } else if (i2 == 5) {
            try {
                a((Exception) message.obj, message.arg1);
            } catch (Throwable th) {
                this.m = false;
                if (this.n != null) {
                    LockSupport.unpark(this.n);
                }
                throw th;
            }
        }
        this.m = false;
        if (this.n != null) {
            LockSupport.unpark(this.n);
        }
        return true;
    }

    private void a(long j2, boolean z) {
        if (this.f6426a.g() == this.f6426a.h()) {
            this.b.a(this.f6426a.a(), this.f6426a.g());
            return;
        }
        if (this.r) {
            this.r = false;
            this.f6426a.a((byte) 3);
        }
        if (z) {
            this.o = j2;
            a((byte) 3);
            this.p.set(0);
        }
    }

    private void i() throws IOException {
        h();
        this.f6426a.a((byte) -3);
        this.b.b(this.f6426a.a(), this.f6426a.h());
        this.b.c(this.f6426a.a());
        a((byte) -3);
        if (FileDownloadProperties.a().g) {
            FileDownloadBroadcastHandler.a(this.f6426a);
        }
    }

    private boolean j() {
        if (this.f6426a.i()) {
            this.f6426a.c(this.f6426a.g());
        } else if (this.f6426a.g() != this.f6426a.h()) {
            a((Exception) new FileDownloadGiveUpRetryException(FileDownloadUtils.a("sofar[%d] not equal total[%d]", Long.valueOf(this.f6426a.g()), Long.valueOf(this.f6426a.h()))));
            return true;
        }
        return false;
    }

    private void a(Exception exc, int i2) {
        Exception b2 = b(exc);
        this.c.a(b2);
        this.c.a(this.d - i2);
        this.f6426a.a((byte) 5);
        this.f6426a.c(b2.toString());
        this.b.a(this.f6426a.a(), (Throwable) b2);
        a((byte) 5);
    }

    private void k() {
        this.f6426a.a((byte) -2);
        this.b.c(this.f6426a.a(), this.f6426a.g());
        a((byte) -2);
    }

    private void c(Exception exc) {
        Exception b2 = b(exc);
        if (b2 instanceof SQLiteFullException) {
            a((SQLiteFullException) b2);
        } else {
            try {
                this.f6426a.a((byte) -1);
                this.f6426a.c(exc.toString());
                this.b.a(this.f6426a.a(), (Throwable) b2, this.f6426a.g());
            } catch (SQLiteFullException e2) {
                b2 = e2;
                a((SQLiteFullException) b2);
            }
        }
        this.c.a(b2);
        a((byte) -1);
    }

    private boolean b(long j2) {
        if (this.s) {
            this.s = false;
            return true;
        }
        long j3 = j2 - this.o;
        if (this.j == -1 || this.p.get() < this.j || j3 < ((long) this.h)) {
            return false;
        }
        return true;
    }

    private void a(byte b2) {
        if (b2 != -2) {
            MessageSnapshotFlow.a().a(MessageSnapshotTaker.a(b2, this.f6426a, this.c));
        } else if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "High concurrent cause, Already paused and we don't need to call-back to Task in here, %d", Integer.valueOf(this.f6426a.a()));
        }
    }

    public static class ProcessParams {

        /* renamed from: a  reason: collision with root package name */
        private boolean f6427a;
        private Exception b;
        private int c;

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            this.f6427a = z;
        }

        public boolean a() {
            return this.f6427a;
        }

        /* access modifiers changed from: package-private */
        public void a(Exception exc) {
            this.b = exc;
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            this.c = i;
        }

        public Exception b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }
    }
}
