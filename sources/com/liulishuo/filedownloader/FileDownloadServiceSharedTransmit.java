package com.liulishuo.filedownloader;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler;
import com.liulishuo.filedownloader.services.FileDownloadService;
import com.liulishuo.filedownloader.util.DownloadServiceNotConnectedHelper;
import java.util.ArrayList;
import java.util.List;

class FileDownloadServiceSharedTransmit implements IFileDownloadServiceProxy, FDServiceSharedHandler.FileDownloadServiceSharedConnection {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?> f6400a = FileDownloadService.SharedMainProcessService.class;
    private final ArrayList<Runnable> b = new ArrayList<>();
    private FDServiceSharedHandler c;

    FileDownloadServiceSharedTransmit() {
    }

    public boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.a(str, str2, z);
        }
        this.c.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
        return true;
    }

    public boolean a(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.a(i);
        }
        return this.c.pause(i);
    }

    public boolean a(String str, String str2) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.a(str, str2);
        }
        return this.c.checkDownloading(str, str2);
    }

    public long b(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.b(i);
        }
        return this.c.getSofar(i);
    }

    public long c(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.c(i);
        }
        return this.c.getTotal(i);
    }

    public byte d(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.d(i);
        }
        return this.c.getStatus(i);
    }

    public void c() {
        if (!e()) {
            DownloadServiceNotConnectedHelper.a();
        } else {
            this.c.pauseAllTasks();
        }
    }

    public boolean d() {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.b();
        }
        return this.c.isIdle();
    }

    public boolean e() {
        return this.c != null;
    }

    public void a(Context context) {
        a(context, (Runnable) null);
    }

    public void a(Context context, Runnable runnable) {
        if (runnable != null && !this.b.contains(runnable)) {
            this.b.add(runnable);
        }
        context.startService(new Intent(context, f6400a));
    }

    public void b(Context context) {
        context.stopService(new Intent(context, f6400a));
        this.c = null;
    }

    public void a(int i, Notification notification) {
        if (!e()) {
            DownloadServiceNotConnectedHelper.a(i, notification);
        } else {
            this.c.startForeground(i, notification);
        }
    }

    public void a(boolean z) {
        if (!e()) {
            DownloadServiceNotConnectedHelper.a(z);
        } else {
            this.c.stopForeground(z);
        }
    }

    public boolean e(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.e(i);
        }
        return this.c.setMaxNetworkThreadCount(i);
    }

    public boolean f(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.f(i);
        }
        return this.c.clearTaskData(i);
    }

    public void f() {
        if (!e()) {
            DownloadServiceNotConnectedHelper.c();
        } else {
            this.c.clearAllTaskData();
        }
    }

    public void a(FDServiceSharedHandler fDServiceSharedHandler) {
        this.c = fDServiceSharedHandler;
        this.b.clear();
        for (Runnable run : (List) this.b.clone()) {
            run.run();
        }
        FileDownloadEventPool.a().b(new DownloadServiceConnectChangedEvent(DownloadServiceConnectChangedEvent.ConnectStatus.connected, f6400a));
    }

    public void a() {
        this.c = null;
        FileDownloadEventPool.a().b(new DownloadServiceConnectChangedEvent(DownloadServiceConnectChangedEvent.ConnectStatus.disconnected, f6400a));
    }
}
