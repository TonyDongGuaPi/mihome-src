package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LostServiceConnectedHandler extends FileDownloadConnectListener implements ILostServiceConnectedHandler {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<BaseDownloadTask.IRunningTask> f6408a = new ArrayList<>();

    public void a() {
        IQueuesHandler m = FileDownloader.a().m();
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "The downloader service is connected.", new Object[0]);
        }
        synchronized (this.f6408a) {
            this.f6408a.clear();
            ArrayList arrayList = new ArrayList(m.b());
            for (BaseDownloadTask.IRunningTask iRunningTask : (List) this.f6408a.clone()) {
                int S = iRunningTask.S();
                if (m.a(S)) {
                    iRunningTask.P().c().a();
                    if (!arrayList.contains(Integer.valueOf(S))) {
                        arrayList.add(Integer.valueOf(S));
                    }
                } else {
                    iRunningTask.Y();
                }
            }
            m.a((List<Integer>) arrayList);
        }
    }

    public void b() {
        if (c() == DownloadServiceConnectChangedEvent.ConnectStatus.lost) {
            IQueuesHandler m = FileDownloader.a().m();
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "lost the connection to the file download service, and current active task size is %d", Integer.valueOf(FileDownloadList.a().c()));
            }
            if (FileDownloadList.a().c() > 0) {
                synchronized (this.f6408a) {
                    FileDownloadList.a().a((List<BaseDownloadTask.IRunningTask>) this.f6408a);
                    Iterator<BaseDownloadTask.IRunningTask> it = this.f6408a.iterator();
                    while (it.hasNext()) {
                        it.next().W();
                    }
                    m.a();
                }
                FileDownloader.a().g();
            }
        } else if (FileDownloadList.a().c() > 0) {
            FileDownloadLog.d(this, "file download service has be unbound but the size of active tasks are not empty %d ", Integer.valueOf(FileDownloadList.a().c()));
        }
    }

    public boolean a(BaseDownloadTask.IRunningTask iRunningTask) {
        return !this.f6408a.isEmpty() && this.f6408a.contains(iRunningTask);
    }

    public void b(BaseDownloadTask.IRunningTask iRunningTask) {
        if (!this.f6408a.isEmpty()) {
            synchronized (this.f6408a) {
                this.f6408a.remove(iRunningTask);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004e, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c(com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask r7) {
        /*
            r6 = this;
            com.liulishuo.filedownloader.FileDownloader r0 = com.liulishuo.filedownloader.FileDownloader.a()
            boolean r0 = r0.j()
            r1 = 0
            if (r0 != 0) goto L_0x0054
            java.util.ArrayList<com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask> r0 = r6.f6408a
            monitor-enter(r0)
            com.liulishuo.filedownloader.FileDownloader r2 = com.liulishuo.filedownloader.FileDownloader.a()     // Catch:{ all -> 0x0051 }
            boolean r2 = r2.j()     // Catch:{ all -> 0x0051 }
            if (r2 != 0) goto L_0x004f
            boolean r2 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x0051 }
            r3 = 1
            if (r2 == 0) goto L_0x0032
            java.lang.String r2 = "Waiting for connecting with the downloader service... %d"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0051 }
            com.liulishuo.filedownloader.BaseDownloadTask r5 = r7.P()     // Catch:{ all -> 0x0051 }
            int r5 = r5.k()     // Catch:{ all -> 0x0051 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0051 }
            r4[r1] = r5     // Catch:{ all -> 0x0051 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r6, r2, r4)     // Catch:{ all -> 0x0051 }
        L_0x0032:
            com.liulishuo.filedownloader.FileDownloadServiceProxy r1 = com.liulishuo.filedownloader.FileDownloadServiceProxy.a()     // Catch:{ all -> 0x0051 }
            android.content.Context r2 = com.liulishuo.filedownloader.util.FileDownloadHelper.a()     // Catch:{ all -> 0x0051 }
            r1.a((android.content.Context) r2)     // Catch:{ all -> 0x0051 }
            java.util.ArrayList<com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask> r1 = r6.f6408a     // Catch:{ all -> 0x0051 }
            boolean r1 = r1.contains(r7)     // Catch:{ all -> 0x0051 }
            if (r1 != 0) goto L_0x004d
            r7.W()     // Catch:{ all -> 0x0051 }
            java.util.ArrayList<com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask> r1 = r6.f6408a     // Catch:{ all -> 0x0051 }
            r1.add(r7)     // Catch:{ all -> 0x0051 }
        L_0x004d:
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return r3
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            goto L_0x0054
        L_0x0051:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            throw r7
        L_0x0054:
            r6.b(r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.LostServiceConnectedHandler.c(com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask):boolean");
    }
}
