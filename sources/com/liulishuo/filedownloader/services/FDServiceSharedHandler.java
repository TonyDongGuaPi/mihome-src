package com.liulishuo.filedownloader.services;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import com.liulishuo.filedownloader.FileDownloadServiceProxy;
import com.liulishuo.filedownloader.i.IFileDownloadIPCCallback;
import com.liulishuo.filedownloader.i.IFileDownloadIPCService;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import java.lang.ref.WeakReference;

public class FDServiceSharedHandler extends IFileDownloadIPCService.Stub implements IFileDownloadServiceHandler {
    private final FileDownloadManager downloadManager;
    private final WeakReference<FileDownloadService> wService;

    public interface FileDownloadServiceSharedConnection {
        void a();

        void a(FDServiceSharedHandler fDServiceSharedHandler);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) {
    }

    public void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) {
    }

    FDServiceSharedHandler(WeakReference<FileDownloadService> weakReference, FileDownloadManager fileDownloadManager) {
        this.wService = weakReference;
        this.downloadManager = fileDownloadManager;
    }

    public boolean checkDownloading(String str, String str2) {
        return this.downloadManager.a(str, str2);
    }

    public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        this.downloadManager.a(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    public boolean pause(int i) {
        return this.downloadManager.b(i);
    }

    public void pauseAllTasks() {
        this.downloadManager.a();
    }

    public boolean setMaxNetworkThreadCount(int i) {
        return this.downloadManager.f(i);
    }

    public long getSofar(int i) {
        return this.downloadManager.c(i);
    }

    public long getTotal(int i) {
        return this.downloadManager.d(i);
    }

    public byte getStatus(int i) {
        return this.downloadManager.e(i);
    }

    public boolean isIdle() {
        return this.downloadManager.b();
    }

    public void startForeground(int i, Notification notification) {
        if (this.wService != null && this.wService.get() != null) {
            ((FileDownloadService) this.wService.get()).startForeground(i, notification);
        }
    }

    public void stopForeground(boolean z) {
        if (this.wService != null && this.wService.get() != null) {
            ((FileDownloadService) this.wService.get()).stopForeground(z);
        }
    }

    public boolean clearTaskData(int i) {
        return this.downloadManager.g(i);
    }

    public void clearAllTaskData() {
        this.downloadManager.c();
    }

    public void onStartCommand(Intent intent, int i, int i2) {
        FileDownloadServiceProxy.b().a(this);
    }

    public void onDestroy() {
        FileDownloadServiceProxy.b().a();
    }
}
