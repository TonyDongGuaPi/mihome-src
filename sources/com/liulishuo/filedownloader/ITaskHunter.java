package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.IDownloadSpeed;
import com.liulishuo.filedownloader.message.MessageSnapshot;

public interface ITaskHunter extends IDownloadSpeed.Lookup {

    public interface IMessageHandler {
        MessageSnapshot a(Throwable th);

        boolean a(MessageSnapshot messageSnapshot);

        boolean b(MessageSnapshot messageSnapshot);

        boolean c(MessageSnapshot messageSnapshot);

        IFileDownloadMessenger d();

        boolean d(MessageSnapshot messageSnapshot);
    }

    public interface IStarter {
        boolean a(FileDownloadListener fileDownloadListener);

        void r();
    }

    void e();

    boolean f();

    byte g();

    void h();

    long i();

    long j();

    Throwable k();

    int l();

    boolean m();

    boolean n();

    String o();

    boolean p();

    void q();
}
