package com.mipay.sdk;

import android.accounts.Account;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.mipay.sdk.IMipayResponse;
import com.mipay.sdk.IMipayService;
import com.mipay.sdk.exception.MipayException;
import java.security.InvalidParameterException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class Mipay {
    public static final int CAPABILITY = 0;
    public static final int ERROR_CODE_ACCOUNT_ERROR = 4;
    public static final int ERROR_CODE_CALL_TOO_FAST = 3;
    public static final int ERROR_CODE_CANCELED = 2;
    public static final int ERROR_CODE_DUPLICATE_PURCHASE = 9;
    public static final int ERROR_CODE_EXCEPTION = 1;
    public static final int ERROR_CODE_INVALID_CALLER = 8;
    public static final int ERROR_CODE_INVALID_DATA = 7;
    public static final int ERROR_CODE_NETWORK_ERROR = 5;
    public static final int ERROR_CODE_OK = 0;
    public static final int ERROR_CODE_SERVER_ERROR = 6;
    public static final String KEY_CODE = "code";
    public static final String KEY_EXTRA = "extra";
    public static final String KEY_FULL_RESULT = "fullResult";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_ORDER = "order";
    public static final String KEY_RESULT = "result";
    public static final int REQUEST_MIPAY = 424;
    public static final String XIAOMI_ACCOUNT_TYPE = "com.xiaomi";

    /* renamed from: a  reason: collision with root package name */
    private static final String f8144a = "Mipay";
    private static final String b = "com.xiaomi.action.MIPAY";
    private static final String c = "com.xiaomi.action.MIPAY_PAY_ORDER";
    private static final String d = "com.xiaomi.action.VIEW_MIPAY_WALLET";
    private static final String e = "com.mipay.wallet";
    private static final String f = "com.mipay.wallet.tv";
    private static final String g = "com.mipay.counter";
    private static boolean h = true;
    /* access modifiers changed from: private */
    public final Context i;
    /* access modifiers changed from: private */
    public final Handler j = new Handler(this.i.getMainLooper());
    /* access modifiers changed from: private */
    public boolean k;
    private boolean l;

    private interface a<V> {
        void a(c<V> cVar);
    }

    private class b implements a<Bundle> {
        private int b;
        private Handler c;

        public b(int i, Handler handler) {
            this.b = i;
            this.c = handler;
        }

        public void a(c<Bundle> cVar) {
            if (this.c != null) {
                try {
                    Bundle b2 = cVar.b();
                    if (b2 != null) {
                        this.c.sendMessage(Mipay.this.a(this.b, 0, (String) null, b2.getString("result")));
                    } else {
                        this.c.sendMessage(Mipay.this.a(this.b, 1, "error"));
                    }
                } catch (MipayException e) {
                    this.c.sendMessage(Mipay.this.a(this.b, e.getError(), e.getMessage()));
                } catch (Throwable th) {
                    this.c = null;
                    throw th;
                }
                this.c = null;
            }
        }
    }

    private interface c<V> {
        V a(long j, TimeUnit timeUnit) throws MipayException;

        V b() throws MipayException;

        boolean cancel(boolean z);

        boolean isCancelled();

        boolean isDone();
    }

    private abstract class d extends FutureTask<Bundle> implements ServiceConnection, c<Bundle> {

        /* renamed from: a  reason: collision with root package name */
        private boolean f8147a = false;
        private IMipayResponse b;
        private IMipayService c;
        /* access modifiers changed from: private */
        public a<Bundle> e;
        /* access modifiers changed from: private */
        public Activity f;
        private final int g = 5000;
        private Runnable h = new Runnable() {
            public void run() {
                Activity a2 = d.this.f;
                if (!d.this.isDone() && a2 != null) {
                    if (a2.isFinishing()) {
                        d.this.setException(new MipayException(2, "Operation has been cancelled because host activity has finished."));
                    } else {
                        Mipay.this.j.postDelayed(this, 5000);
                    }
                }
            }
        };

        class a extends IMipayResponse.Stub {
            a() {
            }

            public void onError(int i, String str, Bundle bundle) throws RemoteException {
                if (i == 2) {
                    d.this.cancel(true);
                    d.this.g();
                    return;
                }
                d.this.setException(d.this.a(i, str, bundle));
            }

            public void onResult(Bundle bundle) throws RemoteException {
                Intent intent = (Intent) bundle.getParcelable("intent");
                if (intent == null) {
                    d.this.set(bundle);
                } else if (d.this.f != null) {
                    d.this.f.startActivity(intent);
                } else {
                    d.this.setException(new MipayException(7, "activity cannot be null"));
                }
            }
        }

        protected d(Activity activity, a<Bundle> aVar) {
            super(new Callable<Bundle>() {
                /* renamed from: a */
                public Bundle call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
            this.f = activity;
            this.e = aVar;
            this.b = new a();
        }

        private Bundle a(Long l, TimeUnit timeUnit) throws MipayException {
            if (!isDone()) {
                j();
            }
            if (l == null) {
                try {
                    Bundle bundle = (Bundle) get();
                    cancel(true);
                    return bundle;
                } catch (CancellationException unused) {
                    throw new MipayException(2, "user cancelled");
                } catch (TimeoutException unused2) {
                    throw new MipayException(2, "time out");
                } catch (InterruptedException e2) {
                    throw new MipayException(1, (Throwable) e2);
                } catch (ExecutionException e3) {
                    Throwable cause = e3.getCause();
                    if (cause instanceof MipayException) {
                        throw ((MipayException) cause);
                    }
                    throw new MipayException(1, cause);
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

        /* access modifiers changed from: private */
        public Exception a(int i, String str, Bundle bundle) {
            if (TextUtils.isEmpty(str)) {
                str = "Unknown failure";
            }
            return new MipayException(i, str, bundle);
        }

        private void j() {
            Looper myLooper = Looper.myLooper();
            if (myLooper != null && myLooper == Mipay.this.i.getMainLooper()) {
                IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
                Log.e(Mipay.f8144a, "calling this from your main thread can lead to deadlock and/or ANRs", illegalStateException);
                throw illegalStateException;
            }
        }

        /* access modifiers changed from: protected */
        public abstract void a() throws RemoteException;

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void set(Bundle bundle) {
            super.set(bundle);
            g();
        }

        /* renamed from: b */
        public Bundle a(long j, TimeUnit timeUnit) throws MipayException {
            return a(Long.valueOf(j), timeUnit);
        }

        /* access modifiers changed from: protected */
        public IMipayResponse c() {
            return this.b;
        }

        /* access modifiers changed from: protected */
        public IMipayService d() {
            return this.c;
        }

        /* access modifiers changed from: protected */
        public void done() {
            if (this.e != null) {
                Mipay.this.j.post(new Runnable() {
                    public void run() {
                        d.this.e.a(d.this);
                        a unused = d.this.e = null;
                    }
                });
            }
            Mipay.this.j.removeCallbacks(this.h);
            this.f = null;
        }

        public final c<Bundle> e() {
            f();
            return this;
        }

        /* access modifiers changed from: protected */
        public void f() {
            if (!h()) {
                setException(new MipayException(1, "bind to service failed"));
                return;
            }
            this.f8147a = true;
            Log.d(Mipay.f8144a, "service bound");
        }

        /* access modifiers changed from: protected */
        public void g() {
            if (this.f8147a) {
                Mipay.this.i.unbindService(this);
                this.f8147a = false;
                Log.d(Mipay.f8144a, "service unbinded");
            }
        }

        /* access modifiers changed from: protected */
        public boolean h() {
            Intent intent = new Intent(Mipay.b);
            intent.setPackage(Mipay.this.k ? "com.mipay.wallet" : Mipay.g);
            return Mipay.this.i.bindService(intent, this, 1);
        }

        /* renamed from: i */
        public Bundle b() throws MipayException {
            return a((Long) null, (TimeUnit) null);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(Mipay.f8144a, "service connected, component:" + componentName);
            this.c = IMipayService.Stub.asInterface(iBinder);
            try {
                a();
                Mipay.this.j.postDelayed(this.h, 5000);
            } catch (RemoteException e2) {
                setException(e2);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(Mipay.f8144a, "service disconnected");
            if (!isDone()) {
                Log.e(Mipay.f8144a, "task is not completed");
                setException(new MipayException(1, "active service exits unexpectedly"));
            }
            this.c = null;
        }

        /* access modifiers changed from: protected */
        public void setException(Throwable th) {
            super.setException(th);
            g();
        }
    }

    private Mipay(Context context) {
        this.i = context.getApplicationContext();
        this.k = a(context);
        this.l = b(context);
    }

    /* access modifiers changed from: private */
    public Message a(int i2, int i3, String str) {
        return a(i2, i3, str, (String) null);
    }

    /* access modifiers changed from: private */
    public Message a(int i2, int i3, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i3);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("message", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("result", str2);
            }
        } catch (JSONException unused) {
        }
        Message message = new Message();
        message.what = i2;
        message.obj = jSONObject.toString();
        return message;
    }

    private c<Bundle> a(Activity activity, String str, Bundle bundle, a<Bundle> aVar) {
        final Bundle bundle2 = bundle;
        final String str2 = str;
        return new d(activity, aVar) {
            /* access modifiers changed from: protected */
            public void a() throws RemoteException {
                IMipayService d = d();
                Bundle bundle = new Bundle();
                if (bundle2 != null) {
                    bundle.putAll(bundle2);
                }
                d.pay(c(), (Account) null, str2, bundle);
            }
        }.e();
    }

    private void a(Activity activity) {
        if (this.k) {
            Intent intent = new Intent("com.xiaomi.action.VIEW_MIPAY_WALLET");
            intent.setPackage("com.mipay.wallet");
            activity.startActivity(intent);
        }
    }

    private static boolean a() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return ((int) ((((float) Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)) / displayMetrics.density) + 0.5f)) >= 600;
    }

    private static boolean a(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.mipay.wallet", 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private static boolean b(Context context) {
        try {
            context.getPackageManager().getPackageInfo(f, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private static boolean c(Context context) {
        try {
            context.getPackageManager().getPackageInfo(g, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static Mipay get(Context context) {
        return new Mipay(context);
    }

    public static boolean isMipayInstalled(Context context) {
        if (a()) {
            return false;
        }
        return a(context) || c(context) || b(context);
    }

    public void pay(Activity activity, String str, Bundle bundle) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(c);
            intent.setPackage(this.l ? f : "com.mipay.wallet");
            intent.putExtra("order", str);
            intent.putExtra("extra", bundle);
            activity.startActivityForResult(intent, 424);
        } else {
            throw new InvalidParameterException("order cannot be empty");
        }
    }

    @Deprecated
    public void pay(Activity activity, String str, Bundle bundle, int i2, Handler handler) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (!TextUtils.isEmpty(str)) {
            a(activity, str, bundle, (a<Bundle>) new b(i2, handler));
        } else {
            throw new InvalidParameterException("order cannot be empty");
        }
    }

    public void pay(Fragment fragment, String str, Bundle bundle) {
        if (fragment == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(c);
            intent.setPackage(this.l ? f : "com.mipay.wallet");
            intent.putExtra("order", str);
            intent.putExtra("extra", bundle);
            fragment.startActivityForResult(intent, 424);
        } else {
            throw new InvalidParameterException("order cannot be empty");
        }
    }

    public void showMipayCenter(Activity activity) {
        a(activity);
    }
}
