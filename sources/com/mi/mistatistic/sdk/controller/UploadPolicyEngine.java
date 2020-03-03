package com.mi.mistatistic.sdk.controller;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.mi.mistatistic.sdk.controller.AsyncJobDispatcher;

public class UploadPolicyEngine {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7347a = "upload_policy";
    private static final String b = "upload_interval";
    private static final int c = 1;
    private static UploadPolicyEngine d;
    private boolean e = false;
    private int f;
    private long g;
    private long h;
    private Handler i = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                AsyncJobDispatcher.a().a((AsyncJobDispatcher.AsyncJob) new AsyncJobDispatcher.AsyncJob() {
                    public void a() {
                        if (UploadPolicyEngine.this.d()) {
                            new RemoteDataUploadManager().a();
                        }
                    }
                });
            }
        }
    };

    public static synchronized UploadPolicyEngine a() {
        UploadPolicyEngine uploadPolicyEngine;
        synchronized (UploadPolicyEngine.class) {
            if (d == null) {
                d = new UploadPolicyEngine();
            }
            uploadPolicyEngine = d;
        }
        return uploadPolicyEngine;
    }

    private UploadPolicyEngine() {
    }

    public void b() {
        this.f = PrefPersistUtils.a(ApplicationContextHolder.a(), f7347a, 0);
        if (this.f == 4) {
            this.h = PrefPersistUtils.a(ApplicationContextHolder.a(), b, 60000);
        } else {
            this.h = PrefPersistUtils.a(ApplicationContextHolder.a(), RemoteDataUploadManager.o, 60000);
        }
    }

    public void a(int i2, long j) {
        this.f = i2;
        if (this.f == 4) {
            this.h = j;
        } else {
            this.h = PrefPersistUtils.a(ApplicationContextHolder.a(), RemoteDataUploadManager.o, 60000);
        }
        PrefPersistUtils.b(ApplicationContextHolder.a(), f7347a, this.f);
        if (this.f == 4) {
            PrefPersistUtils.b(ApplicationContextHolder.a(), b, this.h);
            AsyncJobDispatcher.a().a((AsyncJobDispatcher.AsyncJob) new AsyncJobDispatcher.AsyncJob() {
                public void a() {
                    if (UploadPolicyEngine.this.d()) {
                        new RemoteDataUploadManager().a();
                    }
                }
            }, this.h);
        }
    }

    public void c() {
        try {
            if (this.f == 4) {
                if (!this.i.hasMessages(1)) {
                    this.i.sendEmptyMessageDelayed(1, this.h);
                }
            } else if (!this.i.hasMessages(1)) {
                if (this.f != 0) {
                    if (this.f != 1) {
                        this.i.sendEmptyMessage(1);
                        return;
                    }
                }
                this.i.sendEmptyMessageDelayed(1, RemoteDataUploadManager.d());
            }
        } catch (Exception e2) {
            Logger.a("onEventRecorded exception: ", (Throwable) e2);
        }
    }

    public boolean d() {
        if (RemoteDataUploadManager.b()) {
            Logger.a("RemoteDataUploadManager isUploading, should NOT upload now");
            return false;
        }
        int i2 = this.f;
        if (i2 != 4) {
            switch (i2) {
                case 0:
                    return true;
                case 1:
                    if (NetworkUtils.a(ApplicationContextHolder.a())) {
                        return true;
                    }
                    break;
            }
            return false;
        }
        long b2 = TimeUtil.a().b();
        if (!this.e && b2 - this.g <= this.h) {
            return false;
        }
        this.e = false;
        this.g = b2;
        return true;
    }

    public long e() {
        return this.h;
    }

    public int f() {
        return this.f;
    }
}
