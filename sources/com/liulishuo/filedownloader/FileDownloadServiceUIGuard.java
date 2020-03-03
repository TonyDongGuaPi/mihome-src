package com.liulishuo.filedownloader;

import android.app.Notification;
import android.os.IBinder;
import android.os.RemoteException;
import com.liulishuo.filedownloader.i.IFileDownloadIPCCallback;
import com.liulishuo.filedownloader.i.IFileDownloadIPCService;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.BaseFileServiceUIGuard;
import com.liulishuo.filedownloader.services.FileDownloadService;
import com.liulishuo.filedownloader.util.DownloadServiceNotConnectedHelper;

class FileDownloadServiceUIGuard extends BaseFileServiceUIGuard<FileDownloadServiceCallback, IFileDownloadIPCService> {
    FileDownloadServiceUIGuard() {
        super(FileDownloadService.SeparateProcessService.class);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public FileDownloadServiceCallback b() {
        return new FileDownloadServiceCallback();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public IFileDownloadIPCService b(IBinder iBinder) {
        return IFileDownloadIPCService.Stub.asInterface(iBinder);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(IFileDownloadIPCService iFileDownloadIPCService, FileDownloadServiceCallback fileDownloadServiceCallback) throws RemoteException {
        iFileDownloadIPCService.registerCallback(fileDownloadServiceCallback);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void a(IFileDownloadIPCService iFileDownloadIPCService, FileDownloadServiceCallback fileDownloadServiceCallback) throws RemoteException {
        iFileDownloadIPCService.unregisterCallback(fileDownloadServiceCallback);
    }

    protected static class FileDownloadServiceCallback extends IFileDownloadIPCCallback.Stub {
        protected FileDownloadServiceCallback() {
        }

        public void callback(MessageSnapshot messageSnapshot) throws RemoteException {
            MessageSnapshotFlow.a().a(messageSnapshot);
        }
    }

    public boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.a(str, str2, z);
        }
        try {
            ((IFileDownloadIPCService) h()).start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean a(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.a(i);
        }
        try {
            return ((IFileDownloadIPCService) h()).pause(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean a(String str, String str2) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.a(str, str2);
        }
        try {
            return ((IFileDownloadIPCService) h()).checkDownloading(str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long b(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.b(i);
        }
        try {
            return ((IFileDownloadIPCService) h()).getSofar(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public long c(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.c(i);
        }
        try {
            return ((IFileDownloadIPCService) h()).getTotal(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte d(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.d(i);
        }
        try {
            return ((IFileDownloadIPCService) h()).getStatus(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void c() {
        if (!e()) {
            DownloadServiceNotConnectedHelper.a();
            return;
        }
        try {
            ((IFileDownloadIPCService) h()).pauseAllTasks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean d() {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.b();
        }
        try {
            ((IFileDownloadIPCService) h()).isIdle();
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void a(int i, Notification notification) {
        if (!e()) {
            DownloadServiceNotConnectedHelper.a(i, notification);
            return;
        }
        try {
            ((IFileDownloadIPCService) h()).startForeground(i, notification);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (!e()) {
            DownloadServiceNotConnectedHelper.a(z);
            return;
        }
        try {
            ((IFileDownloadIPCService) h()).stopForeground(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean e(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.e(i);
        }
        try {
            return ((IFileDownloadIPCService) h()).setMaxNetworkThreadCount(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean f(int i) {
        if (!e()) {
            return DownloadServiceNotConnectedHelper.f(i);
        }
        try {
            return ((IFileDownloadIPCService) h()).clearTaskData(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void f() {
        if (!e()) {
            DownloadServiceNotConnectedHelper.c();
            return;
        }
        try {
            ((IFileDownloadIPCService) h()).clearAllTaskData();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
