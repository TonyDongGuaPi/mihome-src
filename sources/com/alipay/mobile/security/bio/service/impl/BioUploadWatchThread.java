package com.alipay.mobile.security.bio.service.impl;

import android.os.Handler;
import android.os.Looper;
import com.alipay.mobile.security.bio.exception.BioIllegalArgumentException;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.BioUploadCallBack;
import com.alipay.mobile.security.bio.service.BioUploadItem;
import com.alipay.mobile.security.bio.service.BioUploadResult;
import com.alipay.mobile.security.bio.thread.WatchThread;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.workspace.Env;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BioUploadWatchThread extends WatchThread {

    /* renamed from: a  reason: collision with root package name */
    private BlockingQueue<BioUploadItem> f1010a = new LinkedBlockingQueue(5);
    private BioUploadGW b;
    private List<BioUploadCallBack> c = new ArrayList();
    private AtomicBoolean d;
    public Handler mMainHandle;

    public synchronized void addBioUploadCallBack(BioUploadCallBack bioUploadCallBack) {
        BioLog.d("BioUploadWatchThread.addBioUploadCallBack(): callBack=" + bioUploadCallBack);
        if (!this.c.contains(bioUploadCallBack)) {
            this.c.add(bioUploadCallBack);
        }
    }

    public BioUploadWatchThread(String str, BioServiceManager bioServiceManager) {
        super(str);
        if (bioServiceManager != null) {
            try {
                Constructor<?> constructor = Class.forName(Env.getProtocolFormat(bioServiceManager.getBioApplicationContext()) != 2 ? "com.alipay.mobile.security.bio.service.impl.BioUploadJsonGWImpl" : "com.alipay.mobile.security.bio.service.impl.BioUploadPBGWImpl").getConstructor(new Class[]{BioServiceManager.class});
                constructor.setAccessible(true);
                this.b = (BioUploadGW) constructor.newInstance(new Object[]{bioServiceManager});
            } catch (Throwable th) {
                BioLog.e(th);
            }
            this.mMainHandle = new Handler(Looper.getMainLooper());
            return;
        }
        throw new BioIllegalArgumentException("BioServiceManager can't be null.");
    }

    /* access modifiers changed from: protected */
    public void a() {
        try {
            BioUploadItem poll = this.f1010a.poll(50, TimeUnit.SECONDS);
            BioLog.e("BioUploadWatchThread.task(1), mClearUpFlag=" + this.d + ", request=" + poll);
            if (poll != null) {
                this.d.set(false);
                BioUploadResult upload = this.b.upload(poll);
                BioLog.e("BioUploadWatchThread.task(2), mClearUpFlag=" + this.d);
                if (this.d.getAndSet(false)) {
                    BioLog.e("BioUploadWatchThread.task(2.5), mClearUpFlag=" + this.d + ", return.");
                } else if (!this.c.isEmpty() && upload != null && this.mMainHandle != null && poll.isNeedSendResponse) {
                    BioLog.e("BioUploadWatchThread.task(3)");
                    this.mMainHandle.post(new c(this, upload));
                }
            }
        } catch (Exception e) {
            BioLog.e((Throwable) e);
        }
    }

    public boolean isFulled() {
        return this.f1010a.size() >= 5;
    }

    public void addBioUploadItem(BioUploadItem bioUploadItem) {
        try {
            boolean add = this.f1010a.add(bioUploadItem);
            BioLog.e("BioUploadWatchThread.addBioUploadItem(), isAddSuc=" + add + ", item=" + bioUploadItem);
            if (!add) {
                this.mMainHandle.post(new d(this));
            }
        } catch (IllegalStateException e) {
            BioLog.e((Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(BioUploadResult bioUploadResult) {
        boolean z = false;
        Iterator<BioUploadCallBack> it = this.c.iterator();
        while (it.hasNext() && !z) {
            z = it.next().onResponse(bioUploadResult);
        }
    }

    public void release() {
        this.c.clear();
        BioLog.d("BioUploadWatchThread.release() => clearBioUploadCallBacks()");
        this.f1010a.clear();
        this.b = null;
        kill();
    }

    public boolean isEmpty() {
        return this.f1010a == null || this.f1010a.isEmpty();
    }

    public synchronized void clearBioUploadCallBacks() {
        if (this.d == null) {
            this.d = new AtomicBoolean(false);
        } else {
            this.d.set(true);
        }
        BioLog.w("BioUploadWatchThread.clearBioUploadCallBacks(), mClearUpFlag=" + this.d);
        this.c.clear();
    }

    public void clearUploadItems() {
        BioLog.w("BioUploadWatchThread.clearUploadItems()");
        this.f1010a.clear();
    }
}
