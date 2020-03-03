package com.xiaomi.smarthome.newui.wallpaper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Call<R> {

    public interface CloseableRunnable extends Closeable, Runnable {
    }

    public static abstract class SafeCall<R> extends Call<R> implements Closeable {
    }

    public abstract void a(R r);

    public static class Ref {

        /* renamed from: a  reason: collision with root package name */
        private Map f20785a = new ConcurrentHashMap();

        public void a(Object obj, Object obj2) {
            this.f20785a.put(obj, obj2);
        }

        public <V> V a(Object obj) {
            return this.f20785a.get(obj);
        }

        public boolean b(Object obj) {
            return a(obj) != null;
        }
    }

    public Runnable b(final R r) {
        return new Runnable() {
            public void run() {
                Call.this.a(r);
            }
        };
    }

    public Runnable a(final Call<Call<R>> call) {
        return new Runnable() {
            public void run() {
                call.a(Call.this);
            }
        };
    }

    public CloseableRunnable a(R r, final boolean z) {
        final WeakReference weakReference = new WeakReference(r);
        return new CloseableRunnable() {
            public void close() throws IOException {
                weakReference.clear();
            }

            public void run() {
                Object obj = weakReference.get();
                if (!z || obj != null) {
                    Call.this.a(obj);
                }
            }
        };
    }

    public Runnable c(R r) {
        return a(r, true);
    }

    public Runnable d(R r) {
        return a(r, false);
    }

    public final SafeCall<R> a() {
        if (this instanceof SafeCall) {
            return (SafeCall) this;
        }
        return new WeakCall(this, false);
    }

    private static class WeakCall<R> extends SafeCall<R> {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<Call<R>> f20786a;
        /* access modifiers changed from: private */
        public boolean b;

        public WeakCall(Call<R> call, boolean z) {
            this.f20786a = new WeakReference<>(call);
            this.b = z;
        }

        /* access modifiers changed from: private */
        public void e(R r) {
            Call call = (Call) this.f20786a.get();
            if (call != null) {
                call.a(r);
            }
        }

        public void a(final R r) {
            if (!this.b || b()) {
                e(r);
            } else {
                new Handler(Looper.getMainLooper()) {
                    public void handleMessage(Message message) {
                        WeakCall.this.e(r);
                    }
                }.sendEmptyMessage(1);
            }
        }

        public void close() throws IOException {
            this.f20786a.clear();
        }
    }

    public static final boolean b() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public Call<R> c() {
        return new Call<R>() {
            public void a(final R r) {
                if (b()) {
                    this.a(r);
                } else {
                    new Handler(Looper.getMainLooper()) {
                        public void handleMessage(Message message) {
                            this.a(r);
                        }
                    }.sendEmptyMessage(1);
                }
            }
        };
    }

    public SafeCall<R> d() {
        if (!(this instanceof WeakCall)) {
            return new WeakCall(this, true);
        }
        boolean unused = ((WeakCall) this).b = true;
        return (SafeCall) this;
    }
}
