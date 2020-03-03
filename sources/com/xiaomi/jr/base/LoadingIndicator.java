package com.xiaomi.jr.base;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.jr.http.NetworkStatusReceiver;
import com.xiaomi.jr.http.netopt.NetworkDiagnosis;

public class LoadingIndicator {

    /* renamed from: a  reason: collision with root package name */
    private static final long f10329a = 200;
    private static final int b = 1;
    private static final int c = 1;
    private static final int d = 0;
    private Context e;
    /* access modifiers changed from: private */
    public Callback f;
    private Handler g;
    private boolean h;
    /* access modifiers changed from: private */
    public int i;
    private Handler.Callback j = new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                LoadingIndicator.this.f.a(message.arg1 == 1);
            }
            return true;
        }
    };
    private Runnable k = new Runnable() {
        public void run() {
            LoadingIndicator.b(LoadingIndicator.this);
            LoadingIndicator.this.f.a(LoadingIndicator.this.i);
        }
    };
    private NetworkStatusReceiver.NetworkStatusListener l = new NetworkStatusReceiver.NetworkStatusListener() {
        public void a(Context context, NetworkInfo networkInfo) {
            LoadingIndicator.this.f.a(networkInfo);
        }
    };

    public interface Callback {
        void a(int i);

        void a(NetworkInfo networkInfo);

        void a(boolean z);

        void b(boolean z);
    }

    static /* synthetic */ int b(LoadingIndicator loadingIndicator) {
        int i2 = loadingIndicator.i;
        loadingIndicator.i = i2 + 1;
        return i2;
    }

    public LoadingIndicator(Context context, Callback callback) {
        this.e = context.getApplicationContext();
        this.f = callback;
    }

    public void a() {
        this.g = new Handler(Looper.getMainLooper(), this.j);
    }

    public void b() {
        this.g.removeCallbacksAndMessages((Object) null);
    }

    public void c() {
        NetworkStatusReceiver.addNetworkStatusListener(this.e, this.l, true);
    }

    public void d() {
        NetworkStatusReceiver.removeNetworkStatusListener(this.l);
    }

    public void a(boolean z, boolean z2, boolean z3) {
        this.g.post(new Runnable(z, z2, z3) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ boolean f$2;
            private final /* synthetic */ boolean f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                LoadingIndicator.this.b(this.f$1, this.f$2, this.f$3);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(boolean z, boolean z2, boolean z3) {
        if (z) {
            e();
        }
        if (!z2) {
            a(z3, 0);
            this.h = true;
        } else if (!this.h) {
            a(z3, 200);
            this.h = true;
        }
    }

    private void a(boolean z, long j2) {
        this.g.removeMessages(1);
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.arg1 = z ? 1 : 0;
        this.g.sendMessageDelayed(obtain, j2);
    }

    public void a(boolean z) {
        this.g.post(new Runnable(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                LoadingIndicator.this.b(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(boolean z) {
        if (this.h) {
            f();
            this.i = 0;
            this.g.removeMessages(1);
            this.f.b(z);
            this.h = false;
        }
    }

    private void e() {
        this.g.removeCallbacks(this.k);
        this.g.postDelayed(this.k, (long) NetworkDiagnosis.a(this.e));
    }

    private void f() {
        this.g.removeCallbacks(this.k);
    }
}
