package com.liulishuo.filedownloader.event;

import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import junit.framework.Assert;

public class DownloadEventPoolImpl implements IDownloadEventPool {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f6430a = FileDownloadExecutors.a(10, "EventPool");
    private final HashMap<String, LinkedList<IDownloadListener>> b = new HashMap<>();

    public boolean a(String str, IDownloadListener iDownloadListener) {
        boolean add;
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.e(this, "setListener %s", str);
        }
        Assert.assertNotNull("EventPoolImpl.add", iDownloadListener);
        LinkedList linkedList = this.b.get(str);
        if (linkedList == null) {
            synchronized (str.intern()) {
                linkedList = this.b.get(str);
                if (linkedList == null) {
                    HashMap<String, LinkedList<IDownloadListener>> hashMap = this.b;
                    LinkedList linkedList2 = new LinkedList();
                    hashMap.put(str, linkedList2);
                    linkedList = linkedList2;
                }
            }
        }
        synchronized (str.intern()) {
            add = linkedList.add(iDownloadListener);
        }
        return add;
    }

    public boolean b(String str, IDownloadListener iDownloadListener) {
        boolean remove;
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.e(this, "removeListener %s", str);
        }
        LinkedList linkedList = this.b.get(str);
        if (linkedList == null) {
            synchronized (str.intern()) {
                linkedList = this.b.get(str);
            }
        }
        if (linkedList == null || iDownloadListener == null) {
            return false;
        }
        synchronized (str.intern()) {
            remove = linkedList.remove(iDownloadListener);
            if (linkedList.size() <= 0) {
                this.b.remove(str);
            }
        }
        return remove;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.liulishuo.filedownloader.event.IDownloadEvent r6) {
        /*
            r5 = this;
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0013
            java.lang.String r0 = "publish %s"
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = r6.b()
            r3[r1] = r4
            com.liulishuo.filedownloader.util.FileDownloadLog.e(r5, r0, r3)
        L_0x0013:
            java.lang.String r0 = "EventPoolImpl.publish"
            junit.framework.Assert.assertNotNull(r0, r6)
            java.lang.String r0 = r6.b()
            java.util.HashMap<java.lang.String, java.util.LinkedList<com.liulishuo.filedownloader.event.IDownloadListener>> r3 = r5.b
            java.lang.Object r3 = r3.get(r0)
            java.util.LinkedList r3 = (java.util.LinkedList) r3
            if (r3 != 0) goto L_0x0049
            java.lang.String r4 = r0.intern()
            monitor-enter(r4)
            java.util.HashMap<java.lang.String, java.util.LinkedList<com.liulishuo.filedownloader.event.IDownloadListener>> r3 = r5.b     // Catch:{ all -> 0x0046 }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ all -> 0x0046 }
            java.util.LinkedList r3 = (java.util.LinkedList) r3     // Catch:{ all -> 0x0046 }
            if (r3 != 0) goto L_0x0044
            boolean r6 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x0042
            java.lang.String r6 = "No listener for this event %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0046 }
            r2[r1] = r0     // Catch:{ all -> 0x0046 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r5, r6, r2)     // Catch:{ all -> 0x0046 }
        L_0x0042:
            monitor-exit(r4)     // Catch:{ all -> 0x0046 }
            return r1
        L_0x0044:
            monitor-exit(r4)     // Catch:{ all -> 0x0046 }
            goto L_0x0049
        L_0x0046:
            r6 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0046 }
            throw r6
        L_0x0049:
            r5.a((java.util.LinkedList<com.liulishuo.filedownloader.event.IDownloadListener>) r3, (com.liulishuo.filedownloader.event.IDownloadEvent) r6)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.event.DownloadEventPoolImpl.a(com.liulishuo.filedownloader.event.IDownloadEvent):boolean");
    }

    public void b(final IDownloadEvent iDownloadEvent) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.e(this, "asyncPublishInNewThread %s", iDownloadEvent.b());
        }
        Assert.assertNotNull("EventPoolImpl.asyncPublish event", iDownloadEvent);
        this.f6430a.execute(new Runnable() {
            public void run() {
                DownloadEventPoolImpl.this.a(iDownloadEvent);
            }
        });
    }

    private void a(LinkedList<IDownloadListener> linkedList, IDownloadEvent iDownloadEvent) {
        for (Object obj : linkedList.toArray()) {
            if (obj != null && ((IDownloadListener) obj).a(iDownloadEvent)) {
                break;
            }
        }
        if (iDownloadEvent.b != null) {
            iDownloadEvent.b.run();
        }
    }
}
