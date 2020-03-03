package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.ITaskHunter;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

class FileDownloadTaskLauncher {

    /* renamed from: a  reason: collision with root package name */
    private final LaunchTaskPool f6401a = new LaunchTaskPool();

    FileDownloadTaskLauncher() {
    }

    private static class HolderClass {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final FileDownloadTaskLauncher f6402a = new FileDownloadTaskLauncher();

        private HolderClass() {
        }

        static {
            MessageSnapshotFlow.a().a((MessageSnapshotFlow.MessageReceiver) new MessageSnapshotGate());
        }
    }

    public static FileDownloadTaskLauncher a() {
        return HolderClass.f6402a;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(ITaskHunter.IStarter iStarter) {
        this.f6401a.a(iStarter);
    }

    /* access modifiers changed from: package-private */
    public synchronized void b() {
        this.f6401a.a();
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(ITaskHunter.IStarter iStarter) {
        this.f6401a.b(iStarter);
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(FileDownloadListener fileDownloadListener) {
        this.f6401a.a(fileDownloadListener);
    }

    private static class LaunchTaskPool {

        /* renamed from: a  reason: collision with root package name */
        private ThreadPoolExecutor f6403a;
        private LinkedBlockingQueue<Runnable> b;

        public LaunchTaskPool() {
            b();
        }

        public void a(ITaskHunter.IStarter iStarter) {
            this.f6403a.execute(new LaunchTaskRunnable(iStarter));
        }

        public void b(ITaskHunter.IStarter iStarter) {
            this.b.remove(iStarter);
        }

        public void a(FileDownloadListener fileDownloadListener) {
            if (fileDownloadListener == null) {
                FileDownloadLog.d(this, "want to expire by listener, but the listener provided is null", new Object[0]);
                return;
            }
            ArrayList<Runnable> arrayList = new ArrayList<>();
            Iterator<Runnable> it = this.b.iterator();
            while (it.hasNext()) {
                Runnable next = it.next();
                LaunchTaskRunnable launchTaskRunnable = (LaunchTaskRunnable) next;
                if (launchTaskRunnable.a(fileDownloadListener)) {
                    launchTaskRunnable.a();
                    arrayList.add(next);
                }
            }
            if (!arrayList.isEmpty()) {
                if (FileDownloadLog.f6465a) {
                    FileDownloadLog.c(this, "expire %d tasks with listener[%s]", Integer.valueOf(arrayList.size()), fileDownloadListener);
                }
                for (Runnable remove : arrayList) {
                    this.f6403a.remove(remove);
                }
            }
        }

        public void a() {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "expire %d tasks", Integer.valueOf(this.b.size()));
            }
            this.f6403a.shutdownNow();
            b();
        }

        private void b() {
            this.b = new LinkedBlockingQueue<>();
            this.f6403a = FileDownloadExecutors.a(3, this.b, "LauncherTask");
        }
    }

    private static class LaunchTaskRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final ITaskHunter.IStarter f6404a;
        private boolean b = false;

        LaunchTaskRunnable(ITaskHunter.IStarter iStarter) {
            this.f6404a = iStarter;
        }

        public void run() {
            if (!this.b) {
                this.f6404a.r();
            }
        }

        public boolean a(FileDownloadListener fileDownloadListener) {
            return this.f6404a != null && this.f6404a.a(fileDownloadListener);
        }

        public boolean equals(Object obj) {
            return super.equals(obj) || obj == this.f6404a;
        }

        public void a() {
            this.b = true;
        }
    }
}
