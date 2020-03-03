package com.liulishuo.filedownloader;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class FileDownloadMessageStation {

    /* renamed from: a  reason: collision with root package name */
    static final int f6392a = 1;
    static final int b = 2;
    public static final int c = 10;
    public static final int d = 5;
    static int e = 10;
    static int f = 5;
    private final Executor g;
    private final Handler h;
    private final LinkedBlockingQueue<IFileDownloadMessenger> i;
    private final Object j;
    private final ArrayList<IFileDownloadMessenger> k;

    private static final class HolderClass {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final FileDownloadMessageStation f6394a = new FileDownloadMessageStation();

        private HolderClass() {
        }
    }

    public static FileDownloadMessageStation a() {
        return HolderClass.f6394a;
    }

    private FileDownloadMessageStation() {
        this.g = FileDownloadExecutors.a(5, "BlockCompleted");
        this.j = new Object();
        this.k = new ArrayList<>();
        this.h = new Handler(Looper.getMainLooper(), new UIHandlerCallback());
        this.i = new LinkedBlockingQueue<>();
    }

    /* access modifiers changed from: package-private */
    public void a(IFileDownloadMessenger iFileDownloadMessenger) {
        a(iFileDownloadMessenger, false);
    }

    /* access modifiers changed from: package-private */
    public void a(final IFileDownloadMessenger iFileDownloadMessenger, boolean z) {
        if (iFileDownloadMessenger.c()) {
            iFileDownloadMessenger.b();
        } else if (iFileDownloadMessenger.d()) {
            this.g.execute(new Runnable() {
                public void run() {
                    iFileDownloadMessenger.b();
                }
            });
        } else {
            if (!b() && !this.i.isEmpty()) {
                synchronized (this.j) {
                    if (!this.i.isEmpty()) {
                        Iterator<IFileDownloadMessenger> it = this.i.iterator();
                        while (it.hasNext()) {
                            b(it.next());
                        }
                    }
                    this.i.clear();
                }
            }
            if (!b() || z) {
                b(iFileDownloadMessenger);
            } else {
                c(iFileDownloadMessenger);
            }
        }
    }

    private void b(IFileDownloadMessenger iFileDownloadMessenger) {
        this.h.sendMessage(this.h.obtainMessage(1, iFileDownloadMessenger));
    }

    private void c(IFileDownloadMessenger iFileDownloadMessenger) {
        synchronized (this.j) {
            this.i.offer(iFileDownloadMessenger);
        }
        c();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        r6.h.sendMessageDelayed(r6.h.obtainMessage(2, r6.k), (long) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0055, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.j
            monitor-enter(r0)
            java.util.ArrayList<com.liulishuo.filedownloader.IFileDownloadMessenger> r1 = r6.k     // Catch:{ all -> 0x0056 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0056 }
            if (r1 != 0) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            return
        L_0x000d:
            java.util.concurrent.LinkedBlockingQueue<com.liulishuo.filedownloader.IFileDownloadMessenger> r1 = r6.i     // Catch:{ all -> 0x0056 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x0017
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            return
        L_0x0017:
            boolean r1 = b()     // Catch:{ all -> 0x0056 }
            r2 = 0
            if (r1 != 0) goto L_0x0027
            java.util.concurrent.LinkedBlockingQueue<com.liulishuo.filedownloader.IFileDownloadMessenger> r1 = r6.i     // Catch:{ all -> 0x0056 }
            java.util.ArrayList<com.liulishuo.filedownloader.IFileDownloadMessenger> r3 = r6.k     // Catch:{ all -> 0x0056 }
            r1.drainTo(r3)     // Catch:{ all -> 0x0056 }
            r1 = 0
            goto L_0x0045
        L_0x0027:
            int r1 = e     // Catch:{ all -> 0x0056 }
            java.util.concurrent.LinkedBlockingQueue<com.liulishuo.filedownloader.IFileDownloadMessenger> r3 = r6.i     // Catch:{ all -> 0x0056 }
            int r3 = r3.size()     // Catch:{ all -> 0x0056 }
            int r4 = f     // Catch:{ all -> 0x0056 }
            int r3 = java.lang.Math.min(r3, r4)     // Catch:{ all -> 0x0056 }
        L_0x0035:
            if (r2 >= r3) goto L_0x0045
            java.util.ArrayList<com.liulishuo.filedownloader.IFileDownloadMessenger> r4 = r6.k     // Catch:{ all -> 0x0056 }
            java.util.concurrent.LinkedBlockingQueue<com.liulishuo.filedownloader.IFileDownloadMessenger> r5 = r6.i     // Catch:{ all -> 0x0056 }
            java.lang.Object r5 = r5.remove()     // Catch:{ all -> 0x0056 }
            r4.add(r5)     // Catch:{ all -> 0x0056 }
            int r2 = r2 + 1
            goto L_0x0035
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            android.os.Handler r0 = r6.h
            android.os.Handler r2 = r6.h
            r3 = 2
            java.util.ArrayList<com.liulishuo.filedownloader.IFileDownloadMessenger> r4 = r6.k
            android.os.Message r2 = r2.obtainMessage(r3, r4)
            long r3 = (long) r1
            r0.sendMessageDelayed(r2, r3)
            return
        L_0x0056:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.FileDownloadMessageStation.c():void");
    }

    private static class UIHandlerCallback implements Handler.Callback {
        private UIHandlerCallback() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((IFileDownloadMessenger) message.obj).b();
            } else if (message.what == 2) {
                a((ArrayList) message.obj);
                FileDownloadMessageStation.a().c();
            }
            return true;
        }

        private void a(ArrayList<IFileDownloadMessenger> arrayList) {
            Iterator<IFileDownloadMessenger> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            arrayList.clear();
        }
    }

    public static boolean b() {
        return e > 0;
    }
}
