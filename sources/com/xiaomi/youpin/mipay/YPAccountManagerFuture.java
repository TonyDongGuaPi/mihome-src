package com.xiaomi.youpin.mipay;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.OperationCanceledException;
import com.xiaomi.passport.servicetoken.AMAuthTokenConverter;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.mipay.ServiceTokenConverter;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

abstract class YPAccountManagerFuture extends FutureTask<Bundle> implements AccountManagerFuture<Bundle> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23649a = "YPAccountManagerFuture";
    private final Handler b;
    private final AccountManagerCallback<Bundle> c;
    final IYPAccountManagerResponse d = new Response();
    /* access modifiers changed from: private */
    public final Activity e;
    private final Context f;
    private final Handler g;
    private final String h;
    private final String i;

    public abstract void a();

    public YPAccountManagerFuture(Activity activity, Handler handler, Handler handler2, AccountManagerCallback<Bundle> accountManagerCallback, String str, String str2) {
        super(new Callable<Bundle>() {
            /* renamed from: a */
            public Bundle call() throws Exception {
                throw new IllegalStateException("this should never be called");
            }
        });
        this.b = handler2;
        this.c = accountManagerCallback;
        this.e = activity;
        this.f = activity;
        this.g = handler;
        this.h = str;
        this.i = str2;
    }

    public final AccountManagerFuture<Bundle> b() {
        a();
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void set(Bundle bundle) {
        if (bundle == null) {
            LogUtils.d(f23649a, "the bundle must not be null");
        }
        super.set(bundle);
    }

    private void d() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == this.f.getMainLooper()) {
            IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
            LogUtils.d(f23649a, "calling this from your main thread can lead to deadlock and/or ANRs");
            if (this.f.getApplicationInfo().targetSdkVersion >= 8) {
                throw illegalStateException;
            }
        }
    }

    private Bundle a(Long l, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
        try {
            Bundle a2 = ServiceTokenConverter.a(AMAuthTokenConverter.fromAMBundle(b(l, timeUnit), this.h));
            a2.putString("cUserId", this.i);
            return a2;
        } catch (ServiceTokenConverter.ConvertException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private Bundle b(Long l, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
        if (!isDone()) {
            d();
        }
        LogUtils.d(f23649a, "internalGetResult(): " + l + ", " + timeUnit);
        if (l == null) {
            try {
                Bundle bundle = (Bundle) get();
                cancel(true);
                return bundle;
            } catch (InterruptedException | CancellationException | TimeoutException unused) {
                cancel(true);
                throw new RuntimeException();
            } catch (ExecutionException e2) {
                Throwable cause = e2.getCause();
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                } else if (cause instanceof UnsupportedOperationException) {
                    throw new AuthenticatorException(cause);
                } else if (cause instanceof AuthenticatorException) {
                    throw ((AuthenticatorException) cause);
                } else if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                } else if (cause instanceof Error) {
                    throw ((Error) cause);
                } else {
                    throw new IllegalStateException(cause);
                }
            } catch (Throwable th) {
                cancel(true);
                throw th;
            }
        } else {
            Bundle bundle2 = (Bundle) get(l.longValue(), timeUnit);
            cancel(true);
            return bundle2;
        }
    }

    /* renamed from: c */
    public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException {
        return a((Long) null, (TimeUnit) null);
    }

    /* renamed from: a */
    public Bundle getResult(long j, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
        return a(Long.valueOf(j), timeUnit);
    }

    /* access modifiers changed from: protected */
    public void done() {
        LogUtils.d(f23649a, "done()");
        if (this.c != null) {
            a(this.b, this.c, (AccountManagerFuture<Bundle>) this);
        }
    }

    private void a(Handler handler, final AccountManagerCallback<Bundle> accountManagerCallback, final AccountManagerFuture<Bundle> accountManagerFuture) {
        if (handler == null) {
            handler = this.g;
        }
        handler.post(new Runnable() {
            public void run() {
                accountManagerCallback.run(accountManagerFuture);
            }
        });
    }

    private class Response implements IYPAccountManagerResponse {
        private Response() {
        }

        public void a(Bundle bundle) {
            Intent intent = (Intent) bundle.getParcelable("intent");
            if (intent != null && YPAccountManagerFuture.this.e != null) {
                YPAccountManagerFuture.this.e.startActivity(intent);
            } else if (bundle.getBoolean("retry")) {
                YPAccountManagerFuture.this.a();
            } else {
                YPAccountManagerFuture.this.set(bundle);
            }
        }

        public void a(int i, String str) {
            LogUtils.d(YPAccountManagerFuture.f23649a, "onError(): " + i + ", " + str);
            if (i == 4) {
                YPAccountManagerFuture.this.cancel(true);
            } else {
                YPAccountManagerFuture.this.setException(YPAccountManagerFuture.this.a(i, str));
            }
        }

        public void a() {
            LogUtils.d(YPAccountManagerFuture.f23649a, "onRequestContinued()");
        }
    }

    /* access modifiers changed from: private */
    public Exception a(int i2, String str) {
        if (i2 == 3) {
            return new IOException(str);
        }
        if (i2 == 6) {
            return new UnsupportedOperationException(str);
        }
        if (i2 == 5) {
            return new AuthenticatorException(str);
        }
        if (i2 == 7) {
            return new IllegalArgumentException(str);
        }
        return new AuthenticatorException(str);
    }
}
