package com.liulishuo.filedownloader.services;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.liulishuo.filedownloader.i.IFileDownloadIPCCallback;
import com.liulishuo.filedownloader.i.IFileDownloadIPCService;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.lang.ref.WeakReference;

public class FDServiceSeparateHandler extends IFileDownloadIPCService.Stub implements MessageSnapshotFlow.MessageReceiver, IFileDownloadServiceHandler {
    private final RemoteCallbackList<IFileDownloadIPCCallback> callbackList = new RemoteCallbackList<>();
    private final FileDownloadManager downloadManager;
    private final WeakReference<FileDownloadService> wService;

    public IBinder onBind(Intent intent) {
        return this;
    }

    public void onStartCommand(Intent intent, int i, int i2) {
    }

    private synchronized int callback(MessageSnapshot messageSnapshot) {
        int beginBroadcast;
        RemoteCallbackList<IFileDownloadIPCCallback> remoteCallbackList;
        beginBroadcast = this.callbackList.beginBroadcast();
        int i = 0;
        while (i < beginBroadcast) {
            try {
                this.callbackList.getBroadcastItem(i).callback(messageSnapshot);
                i++;
            } catch (RemoteException e) {
                try {
                    FileDownloadLog.a((Object) this, (Throwable) e, "callback error", new Object[0]);
                    remoteCallbackList = this.callbackList;
                } catch (Throwable th) {
                    this.callbackList.finishBroadcast();
                    throw th;
                }
            }
        }
        remoteCallbackList = this.callbackList;
        remoteCallbackList.finishBroadcast();
        return beginBroadcast;
    }

    FDServiceSeparateHandler(WeakReference<FileDownloadService> weakReference, FileDownloadManager fileDownloadManager) {
        this.wService = weakReference;
        this.downloadManager = fileDownloadManager;
        MessageSnapshotFlow.a().a((MessageSnapshotFlow.MessageReceiver) this);
    }

    public void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
        this.callbackList.register(iFileDownloadIPCCallback);
    }

    public void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
        this.callbackList.unregister(iFileDownloadIPCCallback);
    }

    public boolean checkDownloading(String str, String str2) throws RemoteException {
        return this.downloadManager.a(str, str2);
    }

    public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException {
        this.downloadManager.a(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    public boolean pause(int i) throws RemoteException {
        return this.downloadManager.b(i);
    }

    public void pauseAllTasks() throws RemoteException {
        this.downloadManager.a();
    }

    public boolean setMaxNetworkThreadCount(int i) throws RemoteException {
        return this.downloadManager.f(i);
    }

    public long getSofar(int i) throws RemoteException {
        return this.downloadManager.c(i);
    }

    public long getTotal(int i) throws RemoteException {
        return this.downloadManager.d(i);
    }

    public byte getStatus(int i) throws RemoteException {
        return this.downloadManager.e(i);
    }

    public boolean isIdle() throws RemoteException {
        return this.downloadManager.b();
    }

    public void startForeground(int i, Notification notification) throws RemoteException {
        if (this.wService != null && this.wService.get() != null) {
            ((FileDownloadService) this.wService.get()).startForeground(i, notification);
        }
    }

    public void stopForeground(boolean z) throws RemoteException {
        if (this.wService != null && this.wService.get() != null) {
            ((FileDownloadService) this.wService.get()).stopForeground(z);
        }
    }

    public boolean clearTaskData(int i) throws RemoteException {
        return this.downloadManager.g(i);
    }

    public void clearAllTaskData() throws RemoteException {
        this.downloadManager.c();
    }

    public void onDestroy() {
        MessageSnapshotFlow.a().a((MessageSnapshotFlow.MessageReceiver) null);
    }

    public void receive(MessageSnapshot messageSnapshot) {
        callback(messageSnapshot);
    }
}
