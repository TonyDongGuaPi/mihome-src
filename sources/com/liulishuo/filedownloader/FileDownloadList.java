package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileDownloadList {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<BaseDownloadTask.IRunningTask> f6390a;

    private static final class HolderClass {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final FileDownloadList f6391a = new FileDownloadList();

        private HolderClass() {
        }
    }

    public static FileDownloadList a() {
        return HolderClass.f6391a;
    }

    private FileDownloadList() {
        this.f6390a = new ArrayList<>();
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.f6390a.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.f6390a.size();
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        int i2;
        synchronized (this.f6390a) {
            Iterator<BaseDownloadTask.IRunningTask> it = this.f6390a.iterator();
            i2 = 0;
            while (it.hasNext()) {
                if (it.next().f(i)) {
                    i2++;
                }
            }
        }
        return i2;
    }

    public BaseDownloadTask.IRunningTask b(int i) {
        synchronized (this.f6390a) {
            Iterator<BaseDownloadTask.IRunningTask> it = this.f6390a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.IRunningTask next = it.next();
                if (next.f(i)) {
                    return next;
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public List<BaseDownloadTask.IRunningTask> c(int i) {
        byte B;
        ArrayList arrayList = new ArrayList();
        synchronized (this.f6390a) {
            Iterator<BaseDownloadTask.IRunningTask> it = this.f6390a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.IRunningTask next = it.next();
                if (next.f(i) && !next.R() && (B = next.P().B()) != 0 && B != 10) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<BaseDownloadTask.IRunningTask> d(int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f6390a) {
            Iterator<BaseDownloadTask.IRunningTask> it = this.f6390a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.IRunningTask next = it.next();
                if (next.f(i) && !next.R()) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public boolean a(BaseDownloadTask.IRunningTask iRunningTask) {
        return this.f6390a.isEmpty() || !this.f6390a.contains(iRunningTask);
    }

    /* access modifiers changed from: package-private */
    public List<BaseDownloadTask.IRunningTask> a(FileDownloadListener fileDownloadListener) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f6390a) {
            Iterator<BaseDownloadTask.IRunningTask> it = this.f6390a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.IRunningTask next = it.next();
                if (next.b(fileDownloadListener)) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<BaseDownloadTask.IRunningTask> a(int i, FileDownloadListener fileDownloadListener) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f6390a) {
            Iterator<BaseDownloadTask.IRunningTask> it = this.f6390a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.IRunningTask next = it.next();
                if (next.P().t() == fileDownloadListener && !next.P().g()) {
                    next.g(i);
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public BaseDownloadTask.IRunningTask[] d() {
        BaseDownloadTask.IRunningTask[] iRunningTaskArr;
        synchronized (this.f6390a) {
            iRunningTaskArr = (BaseDownloadTask.IRunningTask[]) this.f6390a.toArray(new BaseDownloadTask.IRunningTask[this.f6390a.size()]);
        }
        return iRunningTaskArr;
    }

    /* access modifiers changed from: package-private */
    public void a(List<BaseDownloadTask.IRunningTask> list) {
        synchronized (this.f6390a) {
            Iterator<BaseDownloadTask.IRunningTask> it = this.f6390a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.IRunningTask next = it.next();
                if (!list.contains(next)) {
                    list.add(next);
                }
            }
            this.f6390a.clear();
        }
    }

    public boolean a(BaseDownloadTask.IRunningTask iRunningTask, MessageSnapshot messageSnapshot) {
        boolean remove;
        byte b = messageSnapshot.b();
        synchronized (this.f6390a) {
            remove = this.f6390a.remove(iRunningTask);
        }
        if (FileDownloadLog.f6465a && this.f6390a.size() == 0) {
            FileDownloadLog.e(this, "remove %s left %d %d", iRunningTask, Byte.valueOf(b), Integer.valueOf(this.f6390a.size()));
        }
        if (remove) {
            IFileDownloadMessenger d = iRunningTask.Q().d();
            switch (b) {
                case -4:
                    d.g(messageSnapshot);
                    break;
                case -3:
                    d.e(MessageSnapshotTaker.a(messageSnapshot));
                    break;
                case -2:
                    d.i(messageSnapshot);
                    break;
                case -1:
                    d.h(messageSnapshot);
                    break;
            }
        } else {
            FileDownloadLog.a(this, "remove error, not exist: %s %d", iRunningTask, Byte.valueOf(b));
        }
        return remove;
    }

    /* access modifiers changed from: package-private */
    public void b(BaseDownloadTask.IRunningTask iRunningTask) {
        if (!iRunningTask.P().g()) {
            iRunningTask.T();
        }
        if (iRunningTask.Q().d().a()) {
            c(iRunningTask);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(BaseDownloadTask.IRunningTask iRunningTask) {
        if (!iRunningTask.U()) {
            synchronized (this.f6390a) {
                if (this.f6390a.contains(iRunningTask)) {
                    FileDownloadLog.d(this, "already has %s", iRunningTask);
                } else {
                    iRunningTask.V();
                    this.f6390a.add(iRunningTask);
                    if (FileDownloadLog.f6465a) {
                        FileDownloadLog.e(this, "add list in all %s %d %d", iRunningTask, Byte.valueOf(iRunningTask.P().B()), Integer.valueOf(this.f6390a.size()));
                    }
                }
            }
        }
    }
}
