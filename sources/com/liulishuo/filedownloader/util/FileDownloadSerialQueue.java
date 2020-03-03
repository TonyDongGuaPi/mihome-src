package com.liulishuo.filedownloader.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.liulishuo.filedownloader.BaseDownloadTask;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileDownloadSerialQueue {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6468a = 0;
    private static final int i = 1;
    volatile BaseDownloadTask b;
    final SerialFinishCallback c;
    volatile boolean d = false;
    private final Object e = new Object();
    /* access modifiers changed from: private */
    public final BlockingQueue<BaseDownloadTask> f = new LinkedBlockingQueue();
    private final HandlerThread g = new HandlerThread("SerialDownloadManager");
    private final Handler h;

    public FileDownloadSerialQueue() {
        this.g.start();
        this.h = new Handler(this.g.getLooper(), new SerialLoop());
        this.c = new SerialFinishCallback(new WeakReference(this));
        f();
    }

    public void a(BaseDownloadTask baseDownloadTask) {
        try {
            this.f.put(baseDownloadTask);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r5 = this;
            com.liulishuo.filedownloader.util.FileDownloadSerialQueue$SerialFinishCallback r0 = r5.c
            monitor-enter(r0)
            boolean r1 = r5.d     // Catch:{ all -> 0x0032 }
            r2 = 1
            if (r1 == 0) goto L_0x001e
            java.lang.String r1 = "require pause this queue(remain %d), but it has already been paused"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0032 }
            r3 = 0
            java.util.concurrent.BlockingQueue<com.liulishuo.filedownloader.BaseDownloadTask> r4 = r5.f     // Catch:{ all -> 0x0032 }
            int r4 = r4.size()     // Catch:{ all -> 0x0032 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0032 }
            r2[r3] = r4     // Catch:{ all -> 0x0032 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r5, r1, r2)     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x001e:
            r5.d = r2     // Catch:{ all -> 0x0032 }
            com.liulishuo.filedownloader.BaseDownloadTask r1 = r5.b     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0030
            com.liulishuo.filedownloader.BaseDownloadTask r1 = r5.b     // Catch:{ all -> 0x0032 }
            com.liulishuo.filedownloader.util.FileDownloadSerialQueue$SerialFinishCallback r2 = r5.c     // Catch:{ all -> 0x0032 }
            r1.c((com.liulishuo.filedownloader.BaseDownloadTask.FinishListener) r2)     // Catch:{ all -> 0x0032 }
            com.liulishuo.filedownloader.BaseDownloadTask r1 = r5.b     // Catch:{ all -> 0x0032 }
            r1.i()     // Catch:{ all -> 0x0032 }
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0032:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.util.FileDownloadSerialQueue.a():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r5 = this;
            com.liulishuo.filedownloader.util.FileDownloadSerialQueue$SerialFinishCallback r0 = r5.c
            monitor-enter(r0)
            boolean r1 = r5.d     // Catch:{ all -> 0x0036 }
            r2 = 0
            if (r1 != 0) goto L_0x001e
            java.lang.String r1 = "require resume this queue(remain %d), but it is still running"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0036 }
            java.util.concurrent.BlockingQueue<com.liulishuo.filedownloader.BaseDownloadTask> r4 = r5.f     // Catch:{ all -> 0x0036 }
            int r4 = r4.size()     // Catch:{ all -> 0x0036 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0036 }
            r3[r2] = r4     // Catch:{ all -> 0x0036 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r5, r1, r3)     // Catch:{ all -> 0x0036 }
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return
        L_0x001e:
            r5.d = r2     // Catch:{ all -> 0x0036 }
            com.liulishuo.filedownloader.BaseDownloadTask r1 = r5.b     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x0028
            r5.f()     // Catch:{ all -> 0x0036 }
            goto L_0x0034
        L_0x0028:
            com.liulishuo.filedownloader.BaseDownloadTask r1 = r5.b     // Catch:{ all -> 0x0036 }
            com.liulishuo.filedownloader.util.FileDownloadSerialQueue$SerialFinishCallback r2 = r5.c     // Catch:{ all -> 0x0036 }
            r1.b((com.liulishuo.filedownloader.BaseDownloadTask.FinishListener) r2)     // Catch:{ all -> 0x0036 }
            com.liulishuo.filedownloader.BaseDownloadTask r1 = r5.b     // Catch:{ all -> 0x0036 }
            r1.h()     // Catch:{ all -> 0x0036 }
        L_0x0034:
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return
        L_0x0036:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.util.FileDownloadSerialQueue.b():void");
    }

    public int c() {
        if (this.b != null) {
            return this.b.k();
        }
        return 0;
    }

    public int d() {
        return this.f.size();
    }

    public List<BaseDownloadTask> e() {
        if (this.b != null) {
            a();
        }
        ArrayList arrayList = new ArrayList();
        this.f.drainTo(arrayList);
        this.h.removeMessages(1);
        this.g.interrupt();
        this.g.quit();
        return arrayList;
    }

    private class SerialLoop implements Handler.Callback {
        private SerialLoop() {
        }

        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            try {
                synchronized (FileDownloadSerialQueue.this.c) {
                    if (FileDownloadSerialQueue.this.d) {
                        return false;
                    }
                    FileDownloadSerialQueue.this.b = (BaseDownloadTask) FileDownloadSerialQueue.this.f.take();
                    FileDownloadSerialQueue.this.b.b((BaseDownloadTask.FinishListener) FileDownloadSerialQueue.this.c).h();
                    return false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private static class SerialFinishCallback implements BaseDownloadTask.FinishListener {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<FileDownloadSerialQueue> f6469a;

        SerialFinishCallback(WeakReference<FileDownloadSerialQueue> weakReference) {
            this.f6469a = weakReference;
        }

        public synchronized void a(BaseDownloadTask baseDownloadTask) {
            baseDownloadTask.c((BaseDownloadTask.FinishListener) this);
            if (this.f6469a != null) {
                FileDownloadSerialQueue fileDownloadSerialQueue = (FileDownloadSerialQueue) this.f6469a.get();
                if (fileDownloadSerialQueue != null) {
                    fileDownloadSerialQueue.b = null;
                    if (!fileDownloadSerialQueue.d) {
                        fileDownloadSerialQueue.f();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        this.h.sendEmptyMessage(1);
    }
}
