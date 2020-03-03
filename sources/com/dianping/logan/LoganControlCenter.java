package com.dianping.logan;

import android.os.Looper;
import android.text.TextUtils;
import com.dianping.logan.LoganModel;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentLinkedQueue;

class LoganControlCenter {

    /* renamed from: a  reason: collision with root package name */
    private static LoganControlCenter f5164a;
    private ConcurrentLinkedQueue<LoganModel> b = new ConcurrentLinkedQueue<>();
    private String c;
    private String d;
    private long e;
    private long f;
    private long g;
    private long h;
    private String i;
    private String j;
    private LoganThread k;
    private SimpleDateFormat l = new SimpleDateFormat("yyyy-MM-dd");

    private LoganControlCenter(LoganConfig loganConfig) {
        if (loganConfig.a()) {
            this.d = loganConfig.b;
            this.c = loganConfig.f5162a;
            this.e = loganConfig.d;
            this.g = loganConfig.f;
            this.f = loganConfig.c;
            this.h = loganConfig.e;
            this.i = loganConfig.g == null ? "" : new String(loganConfig.g);
            this.j = loganConfig.h == null ? "" : new String(loganConfig.h);
            c();
            return;
        }
        throw new NullPointerException("config's param is invalid");
    }

    private void c() {
        if (this.k == null) {
            this.k = new LoganThread(this.b, this.c, this.d, this.e, this.f, this.g, this.i, this.j);
            this.k.setName("logan-thread");
            this.k.start();
        }
    }

    static LoganControlCenter a(LoganConfig loganConfig) {
        if (f5164a == null) {
            synchronized (LoganControlCenter.class) {
                if (f5164a == null) {
                    f5164a = new LoganControlCenter(loganConfig);
                }
            }
        }
        return f5164a;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, int i2) {
        if (!TextUtils.isEmpty(str)) {
            LoganModel loganModel = new LoganModel();
            loganModel.f5165a = LoganModel.Action.WRITE;
            WriteAction writeAction = new WriteAction();
            String name = Thread.currentThread().getName();
            long id = Thread.currentThread().getId();
            boolean z = false;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            }
            writeAction.f5175a = str;
            writeAction.e = System.currentTimeMillis();
            writeAction.f = i2;
            writeAction.b = z;
            writeAction.c = id;
            writeAction.d = name;
            loganModel.b = writeAction;
            if (((long) this.b.size()) < this.h) {
                this.b.add(loganModel);
                if (this.k != null) {
                    this.k.a();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String[] strArr, SendLogRunnable sendLogRunnable) {
        if (!TextUtils.isEmpty(this.d) && strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    LoganModel loganModel = new LoganModel();
                    SendAction sendAction = new SendAction();
                    loganModel.f5165a = LoganModel.Action.SEND;
                    sendAction.b = str;
                    sendAction.d = sendLogRunnable;
                    loganModel.c = sendAction;
                    this.b.add(loganModel);
                    if (this.k != null) {
                        this.k.a();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (!TextUtils.isEmpty(this.d)) {
            LoganModel loganModel = new LoganModel();
            loganModel.f5165a = LoganModel.Action.FLUSH;
            this.b.add(loganModel);
            if (this.k != null) {
                this.k.a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public File b() {
        return new File(this.d);
    }

    private long a(String str) {
        try {
            return this.l.parse(str).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
