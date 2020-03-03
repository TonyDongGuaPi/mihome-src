package com.liulishuo.filedownloader;

import android.text.TextUtils;
import android.util.SparseArray;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.DownloadTaskHunter;
import com.liulishuo.filedownloader.ITaskHunter;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.util.ArrayList;

public class DownloadTask implements BaseDownloadTask, BaseDownloadTask.IRunningTask, DownloadTaskHunter.ICaptureTask {
    public static final int b = 10;
    volatile int c = 0;
    private final ITaskHunter d;
    private final ITaskHunter.IMessageHandler e;
    private int f;
    private ArrayList<BaseDownloadTask.FinishListener> g;
    private final String h;
    private String i;
    private String j;
    private boolean k;
    private FileDownloadHeader l;
    private FileDownloadListener m;
    private SparseArray<Object> n;
    private Object o;
    private int p = 0;
    private boolean q = false;
    private boolean r = false;
    private int s = 100;
    private int t = 10;
    private boolean u = false;
    /* access modifiers changed from: private */
    public boolean v = false;
    private final Object w;
    private final Object x = new Object();
    private volatile boolean y = false;

    public BaseDownloadTask P() {
        return this;
    }

    public BaseDownloadTask.IRunningTask ac() {
        return this;
    }

    DownloadTask(String str) {
        this.h = str;
        this.w = new Object();
        DownloadTaskHunter downloadTaskHunter = new DownloadTaskHunter(this, this.w);
        this.d = downloadTaskHunter;
        this.e = downloadTaskHunter;
    }

    public BaseDownloadTask a(int i2) {
        this.d.a(i2);
        return this;
    }

    public BaseDownloadTask a(String str) {
        return a(str, false);
    }

    public BaseDownloadTask a(String str, boolean z) {
        this.i = str;
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "setPath %s", str);
        }
        this.k = z;
        if (z) {
            this.j = null;
        } else {
            this.j = new File(str).getName();
        }
        return this;
    }

    public BaseDownloadTask a(FileDownloadListener fileDownloadListener) {
        this.m = fileDownloadListener;
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "setListener %s", fileDownloadListener);
        }
        return this;
    }

    public BaseDownloadTask b(int i2) {
        this.s = i2;
        return this;
    }

    public BaseDownloadTask c(int i2) {
        this.t = i2;
        return this;
    }

    public BaseDownloadTask a() {
        return b(-1);
    }

    public BaseDownloadTask a(Object obj) {
        this.o = obj;
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "setTag %s", obj);
        }
        return this;
    }

    public BaseDownloadTask a(int i2, Object obj) {
        if (this.n == null) {
            this.n = new SparseArray<>(2);
        }
        this.n.put(i2, obj);
        return this;
    }

    public BaseDownloadTask a(boolean z) {
        this.u = z;
        return this;
    }

    public BaseDownloadTask a(BaseDownloadTask.FinishListener finishListener) {
        b(finishListener);
        return this;
    }

    public BaseDownloadTask b(BaseDownloadTask.FinishListener finishListener) {
        if (this.g == null) {
            this.g = new ArrayList<>();
        }
        if (!this.g.contains(finishListener)) {
            this.g.add(finishListener);
        }
        return this;
    }

    public boolean c(BaseDownloadTask.FinishListener finishListener) {
        return this.g != null && this.g.remove(finishListener);
    }

    public BaseDownloadTask d(int i2) {
        this.p = i2;
        return this;
    }

    public BaseDownloadTask a(String str, String str2) {
        af();
        this.l.a(str, str2);
        return this;
    }

    public BaseDownloadTask b(String str) {
        af();
        this.l.a(str);
        return this;
    }

    public BaseDownloadTask c(String str) {
        if (this.l == null) {
            synchronized (this.x) {
                if (this.l == null) {
                    return this;
                }
            }
        }
        this.l.b(str);
        return this;
    }

    public BaseDownloadTask b(boolean z) {
        this.q = z;
        return this;
    }

    public BaseDownloadTask c(boolean z) {
        this.r = z;
        return this;
    }

    public int b() {
        return c().a();
    }

    public BaseDownloadTask.InQueueTask c() {
        return new InQueueTaskImpl();
    }

    public boolean d() {
        if (f()) {
            FileDownloadLog.d(this, "This task[%d] is running, if you want start the same task, please create a new one by FileDownloader#create", Integer.valueOf(k()));
            return false;
        }
        this.c = 0;
        this.v = false;
        this.y = false;
        this.d.h();
        return true;
    }

    public boolean e() {
        return this.d.g() != 0;
    }

    public boolean f() {
        if (FileDownloader.a().n().a(this)) {
            return true;
        }
        return FileDownloadStatus.b(B());
    }

    public boolean g() {
        return this.c != 0;
    }

    public int h() {
        if (!this.v) {
            return ae();
        }
        throw new IllegalStateException("If you start the task manually, it means this task doesn't belong to a queue, so you must not invoke BaseDownloadTask#ready() or InQueueTask#enqueue() before you start() this method. For detail: If this task doesn't belong to a queue, what is just an isolated task, you just need to invoke BaseDownloadTask#start() to start this task, that's all. In other words, If this task doesn't belong to a queue, you must not invoke BaseDownloadTask#ready() method or InQueueTask#enqueue() method before invoke BaseDownloadTask#start(), If you do that and if there is the same listener object to start a queue in another thread, this task may be assembled by the queue, in that case, when you invoke BaseDownloadTask#start() manually to start this task or this task is started by the queue, there is an exception buried in there, because this task object is started two times without declare BaseDownloadTask#reuse() : 1. you invoke BaseDownloadTask#start() manually; 2. the queue start this task automatically.");
    }

    private int ae() {
        if (!e()) {
            if (!g()) {
                T();
            }
            this.d.e();
            return k();
        } else if (f()) {
            throw new IllegalStateException(FileDownloadUtils.a("This task is running %d, if you want to start the same task, please create a new one by FileDownloader.create", Integer.valueOf(k())));
        } else {
            throw new IllegalStateException("This task is dirty to restart, If you want to reuse this task, please invoke #reuse method manually and retry to restart again." + this.d.toString());
        }
    }

    public boolean i() {
        boolean f2;
        synchronized (this.w) {
            f2 = this.d.f();
        }
        return f2;
    }

    public boolean j() {
        return i();
    }

    public int k() {
        if (this.f != 0) {
            return this.f;
        }
        if (TextUtils.isEmpty(this.i) || TextUtils.isEmpty(this.h)) {
            return 0;
        }
        int a2 = FileDownloadUtils.a(this.h, this.i, this.k);
        this.f = a2;
        return a2;
    }

    public int l() {
        return k();
    }

    public String m() {
        return this.h;
    }

    public int n() {
        return this.s;
    }

    public int o() {
        return this.t;
    }

    public String p() {
        return this.i;
    }

    public boolean q() {
        return this.k;
    }

    public String r() {
        return this.j;
    }

    public String s() {
        return FileDownloadUtils.a(p(), q(), r());
    }

    public FileDownloadListener t() {
        return this.m;
    }

    public int u() {
        return v();
    }

    public int v() {
        if (this.d.i() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.d.i();
    }

    public long w() {
        return this.d.i();
    }

    public int x() {
        return y();
    }

    public int y() {
        if (this.d.j() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.d.j();
    }

    public long z() {
        return this.d.j();
    }

    public int A() {
        return this.d.b();
    }

    public byte B() {
        return this.d.g();
    }

    public boolean C() {
        return this.u;
    }

    public Throwable D() {
        return E();
    }

    public Throwable E() {
        return this.d.k();
    }

    public boolean F() {
        return this.d.m();
    }

    public Object G() {
        return this.o;
    }

    public Object e(int i2) {
        if (this.n == null) {
            return null;
        }
        return this.n.get(i2);
    }

    public boolean H() {
        return I();
    }

    public boolean I() {
        return this.d.n();
    }

    public String J() {
        return this.d.o();
    }

    public int K() {
        return this.p;
    }

    public int L() {
        return this.d.l();
    }

    public boolean M() {
        return this.q;
    }

    public boolean N() {
        return this.d.p();
    }

    public boolean O() {
        return this.r;
    }

    private void af() {
        if (this.l == null) {
            synchronized (this.x) {
                if (this.l == null) {
                    this.l = new FileDownloadHeader();
                }
            }
        }
    }

    public FileDownloadHeader ab() {
        return this.l;
    }

    public void V() {
        this.y = true;
    }

    public void W() {
        this.d.q();
        if (FileDownloadList.a().a((BaseDownloadTask.IRunningTask) this)) {
            this.y = false;
        }
    }

    public void X() {
        ae();
    }

    public void Y() {
        ae();
    }

    public Object Z() {
        return this.w;
    }

    public boolean aa() {
        return this.g != null && this.g.size() > 0;
    }

    public boolean U() {
        return this.y;
    }

    public void d(String str) {
        this.j = str;
    }

    public ArrayList<BaseDownloadTask.FinishListener> ad() {
        return this.g;
    }

    public ITaskHunter.IMessageHandler Q() {
        return this.e;
    }

    public boolean f(int i2) {
        return k() == i2;
    }

    public boolean b(FileDownloadListener fileDownloadListener) {
        return t() == fileDownloadListener;
    }

    public boolean R() {
        return FileDownloadStatus.a((int) B());
    }

    public int S() {
        return this.c;
    }

    public void g(int i2) {
        this.c = i2;
    }

    public void T() {
        int i2;
        if (t() != null) {
            i2 = t().hashCode();
        } else {
            i2 = hashCode();
        }
        this.c = i2;
    }

    public String toString() {
        return FileDownloadUtils.a("%d@%s", Integer.valueOf(k()), super.toString());
    }

    private static final class InQueueTaskImpl implements BaseDownloadTask.InQueueTask {

        /* renamed from: a  reason: collision with root package name */
        private final DownloadTask f6380a;

        private InQueueTaskImpl(DownloadTask downloadTask) {
            this.f6380a = downloadTask;
            boolean unused = this.f6380a.v = true;
        }

        public int a() {
            int k = this.f6380a.k();
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "add the task[%d] to the queue", Integer.valueOf(k));
            }
            FileDownloadList.a().c((BaseDownloadTask.IRunningTask) this.f6380a);
            return k;
        }
    }
}
