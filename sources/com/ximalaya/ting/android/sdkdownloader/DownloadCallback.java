package com.ximalaya.ting.android.sdkdownloader;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState;
import com.ximalaya.ting.android.sdkdownloader.exception.DbException;
import com.ximalaya.ting.android.sdkdownloader.task.Callback;
import java.io.File;

class DownloadCallback implements Callback.Cancelable, Callback.CommonCallback<File>, Callback.ProgressCallback<File> {

    /* renamed from: a  reason: collision with root package name */
    static final InternalHandler f2308a = new InternalHandler();
    private static final String b = "DownloadCallBack";
    private static final int h = 1000000000;
    private static final int i = 1000000001;
    private static final int j = 1000000002;
    private static final int k = 1000000003;
    private static final int l = 1000000004;
    private static final int m = 1000000005;
    private static final int n = 1000000006;
    private static final int o = 1000000007;
    /* access modifiers changed from: private */
    public Track c;
    /* access modifiers changed from: private */
    public XmDownloadManager d;
    private boolean e = false;
    private boolean f = false;
    private Callback.Cancelable g;

    public DownloadCallback(Track track) {
        this.c = track;
    }

    public void a(XmDownloadManager xmDownloadManager) {
        this.d = xmDownloadManager;
    }

    public void a(Callback.Cancelable cancelable) {
        this.g = cancelable;
    }

    public void a() {
        synchronized (DownloadCallback.class) {
            try {
                this.c.z(DownloadState.WAITING.value());
                this.d.c(this.c);
            } catch (DbException e2) {
                Log.e(b, "onWaiting : " + e2.getMessage());
            }
            f2308a.obtainMessage(i, this).sendToTarget();
        }
    }

    public void b() {
        synchronized (DownloadCallback.class) {
            try {
                this.c.z(DownloadState.STARTED.value());
                this.d.c(this.c);
            } catch (DbException e2) {
                Log.e(b, "onStarted: " + e2.getMessage());
            }
        }
        f2308a.obtainMessage(j, this).sendToTarget();
    }

    public void a(long j2, long j3, boolean z) {
        synchronized (DownloadCallback.class) {
            if (z) {
                try {
                    this.c.z(DownloadState.STARTED.value());
                    this.c.j(j2);
                    this.c.n(j3);
                    this.d.c(this.c);
                } catch (DbException e2) {
                    Log.e(b, "onLoading: " + e2.getMessage());
                }
                f2308a.obtainMessage(o, new ArgsObj(this, Long.valueOf(j2), Long.valueOf(j3))).sendToTarget();
            }
        }
    }

    public void a(File file) {
        synchronized (DownloadCallback.class) {
            try {
                this.c.z(DownloadState.FINISHED.value());
                this.d.c(this.c);
            } catch (DbException e2) {
                Log.e(b, "onSuccess: " + e2.getMessage());
            }
            f2308a.obtainMessage(k, this).sendToTarget();
        }
    }

    public void a(Throwable th, boolean z) {
        synchronized (DownloadCallback.class) {
            try {
                this.c.z(DownloadState.ERROR.value());
                this.d.c(this.c);
            } catch (DbException e2) {
                Log.e(b, "onError: " + e2.getMessage());
            }
            if (!z) {
                f2308a.obtainMessage(l, new ArgsObj(this, th)).sendToTarget();
            }
        }
    }

    public void a(Callback.CancelledException cancelledException) {
        synchronized (DownloadCallback.class) {
            try {
                this.c.z(DownloadState.STOPPED.value());
                this.d.c(this.c);
            } catch (DbException e2) {
                Log.e(b, "onCancelled: " + e2.getMessage());
            }
            f2308a.obtainMessage(m, new ArgsObj(this, cancelledException)).sendToTarget();
        }
    }

    public void a(Callback.RemovedException removedException) {
        synchronized (DownloadCallback.class) {
            f2308a.obtainMessage(n, new ArgsObj(this, removedException)).sendToTarget();
        }
    }

    public void c() {
        this.e = false;
        this.f = false;
    }

    private boolean k() {
        return f() || DownloadState.valueOf(this.c.at()).value() > DownloadState.STARTED.value();
    }

    public void d() {
        this.e = true;
        if (this.g != null) {
            this.g.d();
        }
    }

    public void e() {
        this.e = true;
    }

    public boolean f() {
        return this.e;
    }

    public boolean g() {
        return this.f;
    }

    public void h() {
        this.f = true;
        if (this.g != null) {
            this.g.h();
        }
    }

    public void i() {
        if (this.g != null) {
            this.g.i();
        }
    }

    public boolean j() {
        return this.c.at() == DownloadState.STARTED.value();
    }

    private static class ArgsObj {

        /* renamed from: a  reason: collision with root package name */
        final DownloadCallback f2309a;
        final Object[] b;

        public ArgsObj(DownloadCallback downloadCallback, Object... objArr) {
            this.f2309a = downloadCallback;
            this.b = objArr;
        }
    }

    static final class InternalHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ boolean f2310a = (!DownloadCallback.class.desiredAssertionStatus());

        private InternalHandler() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            Object[] objArr;
            if (message.obj != null) {
                DownloadCallback downloadCallback = null;
                if (message.obj instanceof DownloadCallback) {
                    downloadCallback = (DownloadCallback) message.obj;
                    objArr = null;
                } else if (message.obj instanceof ArgsObj) {
                    ArgsObj argsObj = (ArgsObj) message.obj;
                    downloadCallback = argsObj.f2309a;
                    objArr = argsObj.b;
                } else {
                    objArr = null;
                }
                if (downloadCallback != null) {
                    Track a2 = downloadCallback.c;
                    XmDownloadManager b = downloadCallback.d;
                    if (downloadCallback != null && b != null && a2 != null) {
                        try {
                            switch (message.what) {
                                case DownloadCallback.i /*1000000001*/:
                                    b.d(a2);
                                    return;
                                case DownloadCallback.j /*1000000002*/:
                                    b.e(a2);
                                    return;
                                case DownloadCallback.k /*1000000003*/:
                                    b.f(a2);
                                    return;
                                case DownloadCallback.l /*1000000004*/:
                                    if (!f2310a) {
                                        if (objArr == null) {
                                            throw new AssertionError();
                                        }
                                    }
                                    b.a(a2, (Throwable) objArr[0]);
                                    return;
                                case DownloadCallback.m /*1000000005*/:
                                    if (!f2310a) {
                                        if (objArr == null) {
                                            throw new AssertionError();
                                        }
                                    }
                                    b.a(a2, (Callback.CancelledException) objArr[0]);
                                    return;
                                case DownloadCallback.n /*1000000006*/:
                                    b.a(a2, (Callback.RemovedException) objArr[0]);
                                    return;
                                case DownloadCallback.o /*1000000007*/:
                                    b.a(a2, ((Long) objArr[0]).longValue(), ((Long) objArr[1]).longValue());
                                    return;
                                default:
                                    return;
                            }
                        } catch (Throwable th) {
                            if (message.what != DownloadCallback.l) {
                                downloadCallback.a(th, true);
                            }
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("msg must not be null");
            }
        }
    }
}
