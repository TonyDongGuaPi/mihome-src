package com.liulishuo.filedownloader.event;

import com.liulishuo.filedownloader.util.FileDownloadLog;

public abstract class IDownloadEvent {
    public Runnable b = null;
    protected final String c;

    public IDownloadEvent(String str) {
        this.c = str;
    }

    public IDownloadEvent(String str, boolean z) {
        this.c = str;
        if (z) {
            FileDownloadLog.d(this, "do not handle ORDER any more, %s", str);
        }
    }

    public final String b() {
        return this.c;
    }
}
