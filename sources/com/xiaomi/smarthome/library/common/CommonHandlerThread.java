package com.xiaomi.smarthome.library.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CommonHandlerThread extends HandlerThread {

    /* renamed from: a  reason: collision with root package name */
    private Handler f18557a;
    private Handler b;
    private final List<Pair<Message, Long>> c = new ArrayList();
    /* access modifiers changed from: private */
    public WeakReference<Handler> d;

    public CommonHandlerThread(String str) {
        super(str);
        b((Handler) null);
    }

    public CommonHandlerThread(String str, Handler handler) {
        super(str);
        b(handler);
    }

    public void a(Handler handler) {
        this.d = new WeakReference<>(handler);
    }

    private void b(Handler handler) {
        a(handler);
        this.b = new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: protected */
    public void onLooperPrepared() {
        this.f18557a = new Handler(getLooper()) {
            public void handleMessage(Message message) {
                Handler handler;
                if (CommonHandlerThread.this.d != null && (handler = (Handler) CommonHandlerThread.this.d.get()) != null) {
                    handler.handleMessage(message);
                }
            }
        };
        synchronized (this.c) {
            for (Pair next : this.c) {
                this.f18557a.sendMessageDelayed((Message) next.first, ((Long) next.second).longValue());
            }
            this.c.clear();
        }
    }

    public void a(Runnable runnable) {
        this.b.post(runnable);
    }

    public void a(Runnable runnable, long j) {
        this.b.postDelayed(runnable, j);
    }

    public void a(Message message, long j) {
        if (this.f18557a != null) {
            this.f18557a.sendMessageDelayed(message, j);
            return;
        }
        synchronized (this.c) {
            this.c.add(new Pair(message, Long.valueOf(j)));
        }
    }

    public void a(Message message) {
        a(message, 0);
    }

    public void a(int i, Object obj) {
        a(b(i, obj));
    }

    public Message b(int i, Object obj) {
        return Message.obtain((Handler) null, i, obj);
    }

    public void b(Runnable runnable, long j) {
        a(Message.obtain((Handler) null, runnable), j);
    }

    public void b(Runnable runnable) {
        b(runnable, 0);
    }

    public void a(int i, long j) {
        a(Message.obtain((Handler) null, i), j);
    }

    public void a(int i) {
        a(i, 0);
    }

    public void destroy() {
        this.f18557a.removeCallbacksAndMessages((Object) null);
        super.quit();
    }

    public boolean b(int i) {
        if (this.f18557a != null) {
            return this.f18557a.hasMessages(i);
        }
        synchronized (this.c) {
            for (Pair<Message, Long> pair : this.c) {
                if (((Message) pair.first).what == i) {
                    return true;
                }
            }
            return false;
        }
    }

    public void c(int i) {
        if (this.f18557a != null) {
            this.f18557a.removeMessages(i);
            return;
        }
        synchronized (this.c) {
            ArrayList arrayList = new ArrayList();
            for (Pair next : this.c) {
                if (((Message) next.first).what == i) {
                    arrayList.add(next);
                }
            }
            this.c.removeAll(arrayList);
        }
    }
}
