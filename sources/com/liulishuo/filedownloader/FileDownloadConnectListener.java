package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.IDownloadEvent;
import com.liulishuo.filedownloader.event.IDownloadListener;

public abstract class FileDownloadConnectListener extends IDownloadListener {

    /* renamed from: a  reason: collision with root package name */
    private DownloadServiceConnectChangedEvent.ConnectStatus f6382a;

    public abstract void a();

    public abstract void b();

    public boolean a(IDownloadEvent iDownloadEvent) {
        if (!(iDownloadEvent instanceof DownloadServiceConnectChangedEvent)) {
            return false;
        }
        this.f6382a = ((DownloadServiceConnectChangedEvent) iDownloadEvent).a();
        if (this.f6382a == DownloadServiceConnectChangedEvent.ConnectStatus.connected) {
            a();
            return false;
        }
        b();
        return false;
    }

    public DownloadServiceConnectChangedEvent.ConnectStatus c() {
        return this.f6382a;
    }
}
