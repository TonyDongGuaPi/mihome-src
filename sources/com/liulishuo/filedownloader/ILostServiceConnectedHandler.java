package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;

public interface ILostServiceConnectedHandler {
    boolean a(BaseDownloadTask.IRunningTask iRunningTask);

    void b(BaseDownloadTask.IRunningTask iRunningTask);

    boolean c(BaseDownloadTask.IRunningTask iRunningTask);
}
