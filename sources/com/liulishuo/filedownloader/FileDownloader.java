package com.liulishuo.filedownloader;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.download.CustomComponentHolder;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.IDownloadListener;
import com.liulishuo.filedownloader.model.FileDownloadTaskAtom;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.util.List;

public class FileDownloader {
    private static final Object b = new Object();
    private static final Object d = new Object();

    /* renamed from: a  reason: collision with root package name */
    private Runnable f6405a;
    private IQueuesHandler c;
    private ILostServiceConnectedHandler e;

    public static void a(Context context) {
        FileDownloadHelper.a(context.getApplicationContext());
    }

    public static DownloadMgrInitialParams.InitCustomMaker a(Application application) {
        FileDownloadHelper.a(application.getApplicationContext());
        DownloadMgrInitialParams.InitCustomMaker initCustomMaker = new DownloadMgrInitialParams.InitCustomMaker();
        CustomComponentHolder.a().a(initCustomMaker);
        return initCustomMaker;
    }

    public static void b(Context context) {
        if (context != null) {
            a(context);
            return;
        }
        throw new IllegalArgumentException("the provided context must not be null!");
    }

    public static void a(Context context, DownloadMgrInitialParams.InitCustomMaker initCustomMaker) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(FileDownloader.class, "init Downloader with params: %s %s", context, initCustomMaker);
        }
        if (context != null) {
            FileDownloadHelper.a(context.getApplicationContext());
            CustomComponentHolder.a().a(initCustomMaker);
            return;
        }
        throw new IllegalArgumentException("the provided context must not be null!");
    }

    private static final class HolderClass {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final FileDownloader f6407a = new FileDownloader();

        private HolderClass() {
        }
    }

    public static FileDownloader a() {
        return HolderClass.f6407a;
    }

    public static void a(int i) {
        FileDownloadMessageStation.e = i;
    }

    public static void b(int i) {
        if (i > 0) {
            FileDownloadMessageStation.f = i;
            return;
        }
        throw new IllegalArgumentException("sub package size must more than 0");
    }

    public static void b() {
        a(10);
    }

    public static void c() {
        a(-1);
    }

    public static boolean d() {
        return FileDownloadMessageStation.b();
    }

    public BaseDownloadTask a(String str) {
        return new DownloadTask(str);
    }

    public boolean a(FileDownloadListener fileDownloadListener, boolean z) {
        if (fileDownloadListener == null) {
            FileDownloadLog.d(this, "Tasks with the listener can't start, because the listener provided is null: [null, %B]", Boolean.valueOf(z));
            return false;
        } else if (z) {
            return m().b(fileDownloadListener);
        } else {
            return m().a(fileDownloadListener);
        }
    }

    public void a(FileDownloadListener fileDownloadListener) {
        FileDownloadTaskLauncher.a().a(fileDownloadListener);
        for (BaseDownloadTask.IRunningTask P : FileDownloadList.a().a(fileDownloadListener)) {
            P.P().i();
        }
    }

    public void e() {
        FileDownloadTaskLauncher.a().b();
        for (BaseDownloadTask.IRunningTask P : FileDownloadList.a().d()) {
            P.P().i();
        }
        if (FileDownloadServiceProxy.a().e()) {
            FileDownloadServiceProxy.a().c();
            return;
        }
        if (this.f6405a == null) {
            this.f6405a = new Runnable() {
                public void run() {
                    FileDownloadServiceProxy.a().c();
                }
            };
        }
        FileDownloadServiceProxy.a().a(FileDownloadHelper.a(), this.f6405a);
    }

    public int c(int i) {
        List<BaseDownloadTask.IRunningTask> d2 = FileDownloadList.a().d(i);
        if (d2 == null || d2.isEmpty()) {
            FileDownloadLog.d(this, "request pause but not exist %d", Integer.valueOf(i));
            return 0;
        }
        for (BaseDownloadTask.IRunningTask P : d2) {
            P.P().i();
        }
        return d2.size();
    }

    public boolean a(int i, String str) {
        c(i);
        if (!FileDownloadServiceProxy.a().f(i)) {
            return false;
        }
        File file = new File(FileDownloadUtils.e(str));
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            return true;
        }
        file2.delete();
        return true;
    }

    public void f() {
        e();
        FileDownloadServiceProxy.a().f();
    }

    public long d(int i) {
        BaseDownloadTask.IRunningTask b2 = FileDownloadList.a().b(i);
        if (b2 == null) {
            return FileDownloadServiceProxy.a().b(i);
        }
        return b2.P().w();
    }

    public long e(int i) {
        BaseDownloadTask.IRunningTask b2 = FileDownloadList.a().b(i);
        if (b2 == null) {
            return FileDownloadServiceProxy.a().c(i);
        }
        return b2.P().z();
    }

    public byte f(int i) {
        return b(i, (String) null);
    }

    public byte a(String str, String str2) {
        return b(FileDownloadUtils.b(str, str2), str2);
    }

    public byte b(int i, String str) {
        byte b2;
        BaseDownloadTask.IRunningTask b3 = FileDownloadList.a().b(i);
        if (b3 == null) {
            b2 = FileDownloadServiceProxy.a().d(i);
        } else {
            b2 = b3.P().B();
        }
        if (str == null || b2 != 0 || !FileDownloadUtils.c(FileDownloadHelper.a()) || !new File(str).exists()) {
            return b2;
        }
        return -3;
    }

    public int a(String str, FileDownloadListener fileDownloadListener) {
        return a(str, FileDownloadUtils.b(str), fileDownloadListener);
    }

    public int a(String str, String str2, FileDownloadListener fileDownloadListener) {
        return a(FileDownloadUtils.b(str, str2), fileDownloadListener);
    }

    public int a(int i, FileDownloadListener fileDownloadListener) {
        BaseDownloadTask.IRunningTask b2 = FileDownloadList.a().b(i);
        if (b2 == null) {
            return 0;
        }
        b2.P().a(fileDownloadListener);
        return b2.P().k();
    }

    public void g() {
        if (!j()) {
            FileDownloadServiceProxy.a().a(FileDownloadHelper.a());
        }
    }

    public void a(Runnable runnable) {
        if (j()) {
            runnable.run();
        } else {
            FileDownloadServiceProxy.a().a(FileDownloadHelper.a(), runnable);
        }
    }

    public void h() {
        if (j()) {
            FileDownloadServiceProxy.a().b(FileDownloadHelper.a());
        }
    }

    public boolean i() {
        if (!j() || !FileDownloadList.a().b() || !FileDownloadServiceProxy.a().d()) {
            return false;
        }
        h();
        return true;
    }

    public boolean j() {
        return FileDownloadServiceProxy.a().e();
    }

    public void a(FileDownloadConnectListener fileDownloadConnectListener) {
        FileDownloadEventPool.a().a(DownloadServiceConnectChangedEvent.f6433a, (IDownloadListener) fileDownloadConnectListener);
    }

    public void b(FileDownloadConnectListener fileDownloadConnectListener) {
        FileDownloadEventPool.a().b(DownloadServiceConnectChangedEvent.f6433a, fileDownloadConnectListener);
    }

    public void a(int i, Notification notification) {
        FileDownloadServiceProxy.a().a(i, notification);
    }

    public void a(boolean z) {
        FileDownloadServiceProxy.a().a(z);
    }

    public boolean a(String str, String str2, long j) {
        FileDownloadLog.d(this, "If you invoked this method, please remove it directly feel free, it doesn't need any longer", new Object[0]);
        return true;
    }

    public boolean a(List<FileDownloadTaskAtom> list) {
        FileDownloadLog.d(this, "If you invoked this method, please remove it directly feel free, it doesn't need any longer", new Object[0]);
        return true;
    }

    public boolean g(int i) {
        if (FileDownloadList.a().b()) {
            return FileDownloadServiceProxy.a().e(i);
        }
        FileDownloadLog.d(this, "Can't change the max network thread count, because there are actively executing tasks in FileDownloader, please try again after all actively executing tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
        return false;
    }

    public FileDownloadLine k() {
        return new FileDownloadLine();
    }

    public FileDownloadLineAsync l() {
        return new FileDownloadLineAsync();
    }

    /* access modifiers changed from: package-private */
    public IQueuesHandler m() {
        if (this.c == null) {
            synchronized (b) {
                if (this.c == null) {
                    this.c = new QueuesHandler();
                }
            }
        }
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public ILostServiceConnectedHandler n() {
        if (this.e == null) {
            synchronized (d) {
                if (this.e == null) {
                    this.e = new LostServiceConnectedHandler();
                    a((FileDownloadConnectListener) this.e);
                }
            }
        }
        return this.e;
    }
}
