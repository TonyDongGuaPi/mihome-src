package com.liulishuo.filedownloader.download;

public interface ProcessCallback {
    void a(long j);

    void a(DownloadRunnable downloadRunnable, long j, long j2);

    void a(Exception exc, long j);

    boolean a(Exception exc);

    void b(Exception exc);

    void c();
}
