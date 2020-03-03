package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.ITaskHunter;

public interface BaseDownloadTask {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6377a = 10;

    public interface FinishListener {
        void a(BaseDownloadTask baseDownloadTask);
    }

    public interface IRunningTask {
        BaseDownloadTask P();

        ITaskHunter.IMessageHandler Q();

        boolean R();

        int S();

        void T();

        boolean U();

        void V();

        void W();

        void X();

        void Y();

        Object Z();

        boolean aa();

        boolean b(FileDownloadListener fileDownloadListener);

        boolean f(int i);

        void g(int i);
    }

    public interface InQueueTask {
        int a();
    }

    public interface LifeCycleCallback {
        void a();

        void c();

        void f_();
    }

    int A();

    byte B();

    boolean C();

    Throwable D();

    Throwable E();

    boolean F();

    Object G();

    boolean H();

    boolean I();

    String J();

    int K();

    int L();

    boolean M();

    boolean N();

    boolean O();

    BaseDownloadTask a();

    BaseDownloadTask a(int i);

    BaseDownloadTask a(int i, Object obj);

    BaseDownloadTask a(FinishListener finishListener);

    BaseDownloadTask a(FileDownloadListener fileDownloadListener);

    BaseDownloadTask a(Object obj);

    BaseDownloadTask a(String str);

    BaseDownloadTask a(String str, String str2);

    BaseDownloadTask a(String str, boolean z);

    BaseDownloadTask a(boolean z);

    int b();

    BaseDownloadTask b(int i);

    BaseDownloadTask b(FinishListener finishListener);

    BaseDownloadTask b(String str);

    BaseDownloadTask b(boolean z);

    InQueueTask c();

    BaseDownloadTask c(int i);

    BaseDownloadTask c(String str);

    BaseDownloadTask c(boolean z);

    boolean c(FinishListener finishListener);

    BaseDownloadTask d(int i);

    boolean d();

    Object e(int i);

    boolean e();

    boolean f();

    boolean g();

    int h();

    boolean i();

    boolean j();

    int k();

    int l();

    String m();

    int n();

    int o();

    String p();

    boolean q();

    String r();

    String s();

    FileDownloadListener t();

    int u();

    int v();

    long w();

    int x();

    int y();

    long z();
}
