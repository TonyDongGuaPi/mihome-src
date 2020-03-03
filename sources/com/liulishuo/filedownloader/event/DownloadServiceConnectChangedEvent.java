package com.liulishuo.filedownloader.event;

public class DownloadServiceConnectChangedEvent extends IDownloadEvent {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6433a = "event.service.connect.changed";
    private final ConnectStatus d;
    private final Class<?> e;

    public enum ConnectStatus {
        connected,
        disconnected,
        lost
    }

    public DownloadServiceConnectChangedEvent(ConnectStatus connectStatus, Class<?> cls) {
        super(f6433a);
        this.d = connectStatus;
        this.e = cls;
    }

    public ConnectStatus a() {
        return this.d;
    }

    public boolean a(Class<?> cls) {
        return this.e != null && this.e.getName().equals(cls.getName());
    }
}
