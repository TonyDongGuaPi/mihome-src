package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.message.MessageSnapshot;

interface IFileDownloadMessenger {
    void a(BaseDownloadTask.IRunningTask iRunningTask, BaseDownloadTask.LifeCycleCallback lifeCycleCallback);

    void a(MessageSnapshot messageSnapshot);

    boolean a();

    void b();

    void b(MessageSnapshot messageSnapshot);

    void c(MessageSnapshot messageSnapshot);

    boolean c();

    void d(MessageSnapshot messageSnapshot);

    boolean d();

    void e();

    void e(MessageSnapshot messageSnapshot);

    void f(MessageSnapshot messageSnapshot);

    void g(MessageSnapshot messageSnapshot);

    void h(MessageSnapshot messageSnapshot);

    void i(MessageSnapshot messageSnapshot);

    void j(MessageSnapshot messageSnapshot);
}
