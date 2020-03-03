package com.xiaomi.smarthome.library.common.widget.crop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.google.common.net.HttpHeaders;
import com.taobao.weex.ui.module.WXModalUIModule;
import java.io.FileDescriptor;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class BitmapManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18992a = "BitmapManager";
    private static BitmapManager c;
    private final WeakHashMap<Thread, ThreadStatus> b = new WeakHashMap<>();

    private enum State {
        CANCEL,
        ALLOW
    }

    private static class ThreadStatus {

        /* renamed from: a  reason: collision with root package name */
        public State f18994a;
        public BitmapFactory.Options b;

        private ThreadStatus() {
            this.f18994a = State.ALLOW;
        }

        public String toString() {
            String str;
            if (this.f18994a == State.CANCEL) {
                str = WXModalUIModule.CANCEL;
            } else {
                str = this.f18994a == State.ALLOW ? HttpHeaders.ALLOW : "?";
            }
            return "thread state = " + str + ", options = " + this.b;
        }
    }

    public static class ThreadSet implements Iterable<Thread> {

        /* renamed from: a  reason: collision with root package name */
        private final WeakHashMap<Thread, Object> f18993a = new WeakHashMap<>();

        public void a(Thread thread) {
            this.f18993a.put(thread, (Object) null);
        }

        public void b(Thread thread) {
            this.f18993a.remove(thread);
        }

        public Iterator<Thread> iterator() {
            return this.f18993a.keySet().iterator();
        }
    }

    private BitmapManager() {
    }

    private synchronized ThreadStatus f(Thread thread) {
        ThreadStatus threadStatus;
        threadStatus = this.b.get(thread);
        if (threadStatus == null) {
            threadStatus = new ThreadStatus();
            this.b.put(thread, threadStatus);
        }
        return threadStatus;
    }

    private synchronized void a(Thread thread, BitmapFactory.Options options) {
        f(thread).b = options;
    }

    /* access modifiers changed from: package-private */
    public synchronized BitmapFactory.Options a(Thread thread) {
        ThreadStatus threadStatus;
        threadStatus = this.b.get(thread);
        return threadStatus != null ? threadStatus.b : null;
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(Thread thread) {
        this.b.get(thread).b = null;
    }

    public synchronized void a(ThreadSet threadSet) {
        Iterator<Thread> it = threadSet.iterator();
        while (it.hasNext()) {
            d(it.next());
        }
    }

    public synchronized void b(ThreadSet threadSet) {
        Iterator<Thread> it = threadSet.iterator();
        while (it.hasNext()) {
            e(it.next());
        }
    }

    public synchronized boolean c(Thread thread) {
        ThreadStatus threadStatus;
        threadStatus = this.b.get(thread);
        return threadStatus == null || threadStatus.f18994a != State.CANCEL;
    }

    public synchronized void d(Thread thread) {
        f(thread).f18994a = State.ALLOW;
    }

    public synchronized void e(Thread thread) {
        ThreadStatus f = f(thread);
        f.f18994a = State.CANCEL;
        if (f.b != null) {
            f.b.requestCancelDecode();
        }
        notifyAll();
    }

    public synchronized void a() {
        for (Map.Entry next : this.b.entrySet()) {
        }
    }

    public static synchronized BitmapManager b() {
        BitmapManager bitmapManager;
        synchronized (BitmapManager.class) {
            if (c == null) {
                c = new BitmapManager();
            }
            bitmapManager = c;
        }
        return bitmapManager;
    }

    public Bitmap a(FileDescriptor fileDescriptor, BitmapFactory.Options options) {
        if (options.mCancel) {
            return null;
        }
        Thread currentThread = Thread.currentThread();
        if (!c(currentThread)) {
            return null;
        }
        a(currentThread, options);
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
        b(currentThread);
        return decodeFileDescriptor;
    }
}
