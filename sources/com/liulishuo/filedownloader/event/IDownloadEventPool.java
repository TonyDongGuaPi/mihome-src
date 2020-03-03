package com.liulishuo.filedownloader.event;

interface IDownloadEventPool {
    boolean a(IDownloadEvent iDownloadEvent);

    boolean a(String str, IDownloadListener iDownloadListener);

    void b(IDownloadEvent iDownloadEvent);

    boolean b(String str, IDownloadListener iDownloadListener);
}
