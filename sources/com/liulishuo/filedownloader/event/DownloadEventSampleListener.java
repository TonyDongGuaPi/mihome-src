package com.liulishuo.filedownloader.event;

public class DownloadEventSampleListener extends IDownloadListener {

    /* renamed from: a  reason: collision with root package name */
    private final IEventListener f6432a;

    public interface IEventListener {
        boolean a(IDownloadEvent iDownloadEvent);
    }

    public DownloadEventSampleListener(IEventListener iEventListener) {
        this.f6432a = iEventListener;
    }

    public boolean a(IDownloadEvent iDownloadEvent) {
        return this.f6432a != null && this.f6432a.a(iDownloadEvent);
    }
}
