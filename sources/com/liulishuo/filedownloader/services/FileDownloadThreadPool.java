package com.liulishuo.filedownloader.services;

import android.util.SparseArray;
import com.liulishuo.filedownloader.download.DownloadLaunchRunnable;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

class FileDownloadThreadPool {

    /* renamed from: a  reason: collision with root package name */
    private SparseArray<DownloadLaunchRunnable> f6456a = new SparseArray<>();
    private ThreadPoolExecutor b;
    private final String c = "Network";
    private int d;
    private int e = 0;

    FileDownloadThreadPool(int i) {
        this.b = FileDownloadExecutors.a(i, "Network");
        this.d = i;
    }

    public synchronized boolean a(int i) {
        if (a() > 0) {
            FileDownloadLog.d(this, "Can't change the max network thread count, because the  network thread pool isn't in IDLE, please try again after all running tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
            return false;
        }
        int a2 = FileDownloadProperties.a(i);
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "change the max network thread count, from %d to %d", Integer.valueOf(this.d), Integer.valueOf(a2));
        }
        List<Runnable> shutdownNow = this.b.shutdownNow();
        this.b = FileDownloadExecutors.a(a2, "Network");
        if (shutdownNow.size() > 0) {
            FileDownloadLog.d(this, "recreate the network thread pool and discard %d tasks", Integer.valueOf(shutdownNow.size()));
        }
        this.d = a2;
        return true;
    }

    public void a(DownloadLaunchRunnable downloadLaunchRunnable) {
        downloadLaunchRunnable.b();
        synchronized (this) {
            this.f6456a.put(downloadLaunchRunnable.d(), downloadLaunchRunnable);
        }
        this.b.execute(downloadLaunchRunnable);
        if (this.e >= 600) {
            c();
            this.e = 0;
            return;
        }
        this.e++;
    }

    public void b(int i) {
        c();
        synchronized (this) {
            DownloadLaunchRunnable downloadLaunchRunnable = this.f6456a.get(i);
            if (downloadLaunchRunnable != null) {
                downloadLaunchRunnable.a();
                boolean remove = this.b.remove(downloadLaunchRunnable);
                if (FileDownloadLog.f6465a) {
                    FileDownloadLog.c(this, "successful cancel %d %B", Integer.valueOf(i), Boolean.valueOf(remove));
                }
            }
            this.f6456a.remove(i);
        }
    }

    private synchronized void c() {
        SparseArray<DownloadLaunchRunnable> sparseArray = new SparseArray<>();
        int size = this.f6456a.size();
        for (int i = 0; i < size; i++) {
            int keyAt = this.f6456a.keyAt(i);
            DownloadLaunchRunnable downloadLaunchRunnable = this.f6456a.get(keyAt);
            if (downloadLaunchRunnable.e()) {
                sparseArray.put(keyAt, downloadLaunchRunnable);
            }
        }
        this.f6456a = sparseArray;
    }

    public boolean c(int i) {
        DownloadLaunchRunnable downloadLaunchRunnable = this.f6456a.get(i);
        return downloadLaunchRunnable != null && downloadLaunchRunnable.e();
    }

    public int a(String str, int i) {
        if (str == null) {
            return 0;
        }
        int size = this.f6456a.size();
        for (int i2 = 0; i2 < size; i2++) {
            DownloadLaunchRunnable valueAt = this.f6456a.valueAt(i2);
            if (valueAt != null && valueAt.e() && valueAt.d() != i && str.equals(valueAt.f())) {
                return valueAt.d();
            }
        }
        return 0;
    }

    public synchronized int a() {
        c();
        return this.f6456a.size();
    }

    public synchronized List<Integer> b() {
        ArrayList arrayList;
        c();
        arrayList = new ArrayList();
        for (int i = 0; i < this.f6456a.size(); i++) {
            arrayList.add(Integer.valueOf(this.f6456a.get(this.f6456a.keyAt(i)).d()));
        }
        return arrayList;
    }
}
