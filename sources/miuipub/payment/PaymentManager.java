package miuipub.payment;

import android.accounts.Account;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import miuipub.payment.IPaymentManagerResponse;
import miuipub.payment.IPaymentManagerService;
import miuipub.payment.exception.AuthenticationFailureException;
import miuipub.payment.exception.OperationCancelledException;
import miuipub.payment.exception.PaymentServiceFailureException;

public class PaymentManager {
    private static final boolean A = true;
    private static final String B = "PaymentManager";
    private static final String C = "miuipub.intent.action.PAYMENT";

    /* renamed from: a  reason: collision with root package name */
    public static final int f2989a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;
    public static final int j = 10;
    public static final int k = 11;
    public static final int l = 12;
    public static final int m = 13;
    public static final int n = 14;
    public static final int o = 15;
    public static final int p = 16;
    public static final String q = "payment_is_no_account";
    public static final String r = "payment_skip_view";
    public static final String s = "deduct_channel";
    public static final String t = "sign_deduct_channel";
    public static final String u = "payment_payment_result";
    public static final String v = "payment_trade_balance";
    public static final String w = "intent";
    public static final String x = "account";
    public static final String y = "com.xiaomi";
    public static final int z = 3;
    /* access modifiers changed from: private */
    public final Context D;
    /* access modifiers changed from: private */
    public final Handler E = new Handler(this.D.getMainLooper());

    public interface PaymentListener {
        void a(String str, int i, String str2, Bundle bundle);

        void a(String str, Bundle bundle);
    }

    private interface PaymentManagerCallback<V> {
        void a(PaymentManagerFuture<V> paymentManagerFuture);
    }

    private interface PaymentManagerFuture<V> {
        V a(long j, TimeUnit timeUnit) throws IOException, OperationCancelledException, AuthenticationFailureException, PaymentServiceFailureException;

        V b() throws IOException, OperationCancelledException, AuthenticationFailureException, PaymentServiceFailureException;

        boolean cancel(boolean z);

        boolean isCancelled();

        boolean isDone();
    }

    public boolean a() {
        return false;
    }

    private PaymentManager(Context context) {
        this.D = context.getApplicationContext();
    }

    public static PaymentManager a(Context context) {
        return new PaymentManager(context);
    }

    public void a(Activity activity, String str, String str2, Bundle bundle, PaymentListener paymentListener) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (!TextUtils.isEmpty(str2)) {
            a(activity, str2, bundle, (PaymentManagerCallback<Bundle>) new PaymentCallback("thd", str, paymentListener));
        } else {
            throw new InvalidParameterException("order cannot be empty");
        }
    }

    public void a(Activity activity, String str, String str2, String str3, boolean z2, boolean z3, boolean z4, Bundle bundle, PaymentListener paymentListener) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (!TextUtils.isEmpty(str2)) {
            String str4 = str;
            a(activity, str2, str3, z2, z3, z4, bundle, new PaymentCallback("thd", str, paymentListener));
        } else {
            throw new InvalidParameterException("order cannot be empty");
        }
    }

    public void a(Activity activity, String str, String str2, String str3) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(str2)) {
            throw new InvalidParameterException("marketType cannot be empty");
        } else if (!TextUtils.isEmpty(str3)) {
            c(activity, str2, str3);
        } else {
            throw new InvalidParameterException("verify cannot be empty");
        }
    }

    public void a(Activity activity, String str, String str2, String str3, PaymentListener paymentListener) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(str2)) {
            throw new InvalidParameterException("marketType cannot be empty");
        } else if (!TextUtils.isEmpty(str3)) {
            a(activity, str2, str3, (PaymentManagerCallback<Bundle>) new PaymentCallback(str2, str, paymentListener));
        } else {
            throw new InvalidParameterException("verify cannot be empty");
        }
    }

    public void a(Activity activity) {
        b(activity);
    }

    public void a(Activity activity, String str, String str2) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(str)) {
            throw new InvalidParameterException("marketType cannot be empty");
        } else if (!TextUtils.isEmpty(str2)) {
            d(activity, str, str2);
        } else {
            throw new InvalidParameterException("verify cannot be empty");
        }
    }

    public void b(Activity activity, String str, String str2) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(str)) {
            throw new InvalidParameterException("marketType cannot be empty");
        } else if (!TextUtils.isEmpty(str2)) {
            e(activity, str, str2);
        } else {
            throw new InvalidParameterException("verify cannot be empty");
        }
    }

    private PaymentManagerFuture<Bundle> a(Activity activity, String str, String str2, boolean z2, boolean z3, boolean z4, Bundle bundle, PaymentManagerCallback<Bundle> paymentManagerCallback) {
        final Bundle bundle2 = bundle;
        final String str3 = str;
        final String str4 = str2;
        final boolean z5 = z2;
        final boolean z6 = z3;
        final boolean z7 = z4;
        return new PmsTask(activity, paymentManagerCallback) {
            /* access modifiers changed from: protected */
            public void a() throws RemoteException {
                IPaymentManagerService d2 = d();
                Bundle bundle = new Bundle();
                if (bundle2 != null) {
                    bundle.putAll(bundle2);
                }
                d2.payOrder(c(), (Account) null, str3, str4, z5, z6, z7, bundle);
            }
        }.e();
    }

    private PaymentManagerFuture<Bundle> a(Activity activity, String str, Bundle bundle, PaymentManagerCallback<Bundle> paymentManagerCallback) {
        final Bundle bundle2 = bundle;
        final String str2 = str;
        return new PmsTask(activity, paymentManagerCallback) {
            /* access modifiers changed from: protected */
            public void a() throws RemoteException {
                IPaymentManagerService d = d();
                Bundle bundle = new Bundle();
                if (bundle2 != null) {
                    bundle.putAll(bundle2);
                }
                d.payForOrder(c(), (Account) null, str2, bundle);
            }
        }.e();
    }

    private PaymentManagerFuture<Bundle> c(Activity activity, final String str, final String str2) {
        return new PmsTask(activity) {
            /* access modifiers changed from: protected */
            public void a() throws RemoteException {
                d().recharge(c(), (Account) null, str, str2);
            }
        }.e();
    }

    private PaymentManagerFuture<Bundle> a(Activity activity, String str, String str2, PaymentManagerCallback<Bundle> paymentManagerCallback) {
        final String str3 = str;
        final String str4 = str2;
        return new PmsTask(activity, paymentManagerCallback) {
            /* access modifiers changed from: protected */
            public void a() throws RemoteException {
                d().getMiliBalance(c(), (Account) null, str3, str4);
            }
        }.e();
    }

    private void b(Activity activity) {
        new PmsTask(activity) {
            /* access modifiers changed from: protected */
            public void a() throws RemoteException {
                d().showMiliCenter(c(), (Account) null);
            }
        }.e();
    }

    private void d(Activity activity, final String str, final String str2) {
        new PmsTask(activity) {
            /* access modifiers changed from: protected */
            public void a() throws RemoteException {
                d().showRechargeRecord(c(), (Account) null, str, str2);
            }
        }.e();
    }

    private void e(Activity activity, final String str, final String str2) {
        new PmsTask(activity) {
            /* access modifiers changed from: protected */
            public void a() throws RemoteException {
                d().showPayRecord(c(), (Account) null, str, str2);
            }
        }.e();
    }

    private class PaymentCallback implements PaymentManagerCallback<Bundle> {
        private String b;
        private String c;
        private PaymentListener d;

        public PaymentCallback(String str, String str2, PaymentListener paymentListener) {
            this.b = str;
            this.c = str2;
            this.d = paymentListener;
        }

        public void a(PaymentManagerFuture<Bundle> paymentManagerFuture) {
            if (this.d != null) {
                try {
                    Bundle b2 = paymentManagerFuture.b();
                    if (b2 != null) {
                        this.d.a(this.c, b2);
                    } else {
                        this.d.a(this.c, 1, "error", new Bundle());
                    }
                } catch (IOException e) {
                    this.d.a(this.c, 3, e.getMessage(), new Bundle());
                } catch (OperationCancelledException e2) {
                    this.d.a(this.c, 4, e2.getMessage(), new Bundle());
                } catch (AuthenticationFailureException e3) {
                    this.d.a(this.c, 5, e3.getMessage(), new Bundle());
                } catch (PaymentServiceFailureException e4) {
                    this.d.a(this.c, e4.getError(), e4.getMessage(), e4.getErrorResult());
                } catch (Throwable th) {
                    this.d = null;
                    throw th;
                }
                this.d = null;
            }
        }
    }

    private abstract class PmsTask extends FutureTask<Bundle> implements ServiceConnection, PaymentManagerFuture<Bundle> {

        /* renamed from: a  reason: collision with root package name */
        private boolean f2998a;
        private IPaymentManagerResponse b;
        private IPaymentManagerService c;
        /* access modifiers changed from: private */
        public PaymentManagerCallback<Bundle> d;
        private Handler e;
        /* access modifiers changed from: private */
        public Activity f;
        private final int g;
        private Runnable i;

        /* access modifiers changed from: protected */
        public abstract void a() throws RemoteException;

        protected PmsTask(PaymentManager paymentManager, Activity activity) {
            this(paymentManager, activity, (PaymentManagerCallback<Bundle>) null);
        }

        protected PmsTask(PaymentManager paymentManager, Activity activity, PaymentManagerCallback<Bundle> paymentManagerCallback) {
            this(activity, (Handler) null, paymentManagerCallback);
        }

        protected PmsTask(Activity activity, Handler handler, PaymentManagerCallback<Bundle> paymentManagerCallback) {
            super(new Callable<Bundle>() {
                /* renamed from: a */
                public Bundle call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
            this.f2998a = false;
            this.g = 5000;
            this.i = new Runnable() {
                public void run() {
                    Activity a2 = PmsTask.this.f;
                    if (!PmsTask.this.isDone() && a2 != null) {
                        if (a2.isFinishing()) {
                            PmsTask.this.setException(new OperationCancelledException("Operation has been cancelled because host activity has finished."));
                        } else {
                            PaymentManager.this.E.postDelayed(this, 5000);
                        }
                    }
                }
            };
            this.f = activity;
            this.e = handler;
            this.d = paymentManagerCallback;
            this.b = new IPaymentManagerResponseImpl();
        }

        /* access modifiers changed from: protected */
        public IPaymentManagerResponse c() {
            return this.b;
        }

        /* access modifiers changed from: protected */
        public IPaymentManagerService d() {
            return this.c;
        }

        public final PaymentManagerFuture<Bundle> e() {
            f();
            return this;
        }

        /* access modifiers changed from: protected */
        public void f() {
            if (this.f2998a) {
                return;
            }
            if (!h()) {
                setException(new PaymentServiceFailureException(1, "bind to service failed"));
                return;
            }
            this.f2998a = true;
            Log.d(PaymentManager.B, "service bound");
        }

        /* access modifiers changed from: protected */
        public void g() {
            if (this.f2998a) {
                PaymentManager.this.D.unbindService(this);
                this.f2998a = false;
                Log.d(PaymentManager.B, "service unbinded");
            }
        }

        /* access modifiers changed from: protected */
        public boolean h() {
            Intent intent = new Intent("miuipub.intent.action.PAYMENT");
            intent.setPackage(PaymentManager.this.D.getPackageName());
            return PaymentManager.this.D.bindService(intent, this, 1);
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x0067 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.os.Bundle a(java.lang.Long r4, java.util.concurrent.TimeUnit r5) throws java.io.IOException, miuipub.payment.exception.OperationCancelledException, miuipub.payment.exception.AuthenticationFailureException, miuipub.payment.exception.PaymentServiceFailureException {
            /*
                r3 = this;
                boolean r0 = r3.isDone()
                if (r0 != 0) goto L_0x0009
                r3.j()
            L_0x0009:
                r0 = 1
                if (r4 != 0) goto L_0x001a
                java.lang.Object r4 = r3.get()     // Catch:{ CancellationException -> 0x0067, InterruptedException | TimeoutException -> 0x005c, ExecutionException -> 0x0018 }
                android.os.Bundle r4 = (android.os.Bundle) r4     // Catch:{ CancellationException -> 0x0067, InterruptedException | TimeoutException -> 0x005c, ExecutionException -> 0x0018 }
                r3.cancel(r0)
                return r4
            L_0x0016:
                r4 = move-exception
                goto L_0x006f
            L_0x0018:
                r4 = move-exception
                goto L_0x0028
            L_0x001a:
                long r1 = r4.longValue()     // Catch:{ CancellationException -> 0x0067, InterruptedException | TimeoutException -> 0x005c, ExecutionException -> 0x0018 }
                java.lang.Object r4 = r3.get(r1, r5)     // Catch:{ CancellationException -> 0x0067, InterruptedException | TimeoutException -> 0x005c, ExecutionException -> 0x0018 }
                android.os.Bundle r4 = (android.os.Bundle) r4     // Catch:{ CancellationException -> 0x0067, InterruptedException | TimeoutException -> 0x005c, ExecutionException -> 0x0018 }
                r3.cancel(r0)
                return r4
            L_0x0028:
                java.lang.Throwable r4 = r4.getCause()     // Catch:{ all -> 0x0016 }
                boolean r5 = r4 instanceof java.io.IOException     // Catch:{ all -> 0x0016 }
                if (r5 != 0) goto L_0x0059
                boolean r5 = r4 instanceof miuipub.payment.exception.PaymentServiceFailureException     // Catch:{ all -> 0x0016 }
                if (r5 != 0) goto L_0x0056
                boolean r5 = r4 instanceof miuipub.payment.exception.AuthenticationFailureException     // Catch:{ all -> 0x0016 }
                if (r5 != 0) goto L_0x0053
                boolean r5 = r4 instanceof miuipub.payment.exception.OperationCancelledException     // Catch:{ all -> 0x0016 }
                if (r5 != 0) goto L_0x0050
                boolean r5 = r4 instanceof java.lang.RuntimeException     // Catch:{ all -> 0x0016 }
                if (r5 != 0) goto L_0x004d
                boolean r5 = r4 instanceof java.lang.Error     // Catch:{ all -> 0x0016 }
                if (r5 == 0) goto L_0x0047
                java.lang.Error r4 = (java.lang.Error) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x0047:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0016 }
                r5.<init>(r4)     // Catch:{ all -> 0x0016 }
                throw r5     // Catch:{ all -> 0x0016 }
            L_0x004d:
                java.lang.RuntimeException r4 = (java.lang.RuntimeException) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x0050:
                miuipub.payment.exception.OperationCancelledException r4 = (miuipub.payment.exception.OperationCancelledException) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x0053:
                miuipub.payment.exception.AuthenticationFailureException r4 = (miuipub.payment.exception.AuthenticationFailureException) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x0056:
                miuipub.payment.exception.PaymentServiceFailureException r4 = (miuipub.payment.exception.PaymentServiceFailureException) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x0059:
                java.io.IOException r4 = (java.io.IOException) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x005c:
                r3.cancel(r0)
                miuipub.payment.exception.OperationCancelledException r4 = new miuipub.payment.exception.OperationCancelledException
                java.lang.String r5 = "cancelled by exception"
                r4.<init>(r5)
                throw r4
            L_0x0067:
                miuipub.payment.exception.OperationCancelledException r4 = new miuipub.payment.exception.OperationCancelledException     // Catch:{ all -> 0x0016 }
                java.lang.String r5 = "cancelled by user"
                r4.<init>(r5)     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x006f:
                r3.cancel(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: miuipub.payment.PaymentManager.PmsTask.a(java.lang.Long, java.util.concurrent.TimeUnit):android.os.Bundle");
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void set(Bundle bundle) {
            super.set(bundle);
            g();
        }

        /* access modifiers changed from: protected */
        public void setException(Throwable th) {
            super.setException(th);
            g();
        }

        /* renamed from: b */
        public Bundle a(long j, TimeUnit timeUnit) throws IOException, OperationCancelledException, AuthenticationFailureException, PaymentServiceFailureException {
            return a(Long.valueOf(j), timeUnit);
        }

        /* renamed from: i */
        public Bundle b() throws IOException, OperationCancelledException, AuthenticationFailureException, PaymentServiceFailureException {
            return a((Long) null, (TimeUnit) null);
        }

        /* access modifiers changed from: protected */
        public void done() {
            if (this.d != null) {
                (this.e == null ? PaymentManager.this.E : this.e).post(new Runnable() {
                    public void run() {
                        PmsTask.this.d.a(PmsTask.this);
                        PaymentManagerCallback unused = PmsTask.this.d = null;
                    }
                });
            }
            PaymentManager.this.E.removeCallbacks(this.i);
            this.e = null;
            this.f = null;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(PaymentManager.B, "onServiceConnected, component:" + componentName);
            this.c = IPaymentManagerService.Stub.asInterface(iBinder);
            try {
                a();
                PaymentManager.this.E.postDelayed(this.i, 5000);
            } catch (RemoteException e2) {
                setException(e2);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (!isDone()) {
                Log.e(PaymentManager.B, "payment service disconnected, but task is not completed");
                setException(new PaymentServiceFailureException(1, "active service exits unexpectedly"));
            }
            this.c = null;
        }

        class IPaymentManagerResponseImpl extends IPaymentManagerResponse.Stub {
            IPaymentManagerResponseImpl() {
            }

            public void onResult(Bundle bundle) throws RemoteException {
                Intent intent = (Intent) bundle.getParcelable("intent");
                if (intent == null) {
                    PmsTask.this.set(bundle);
                } else if (PmsTask.this.f != null) {
                    PmsTask.this.f.startActivity(intent);
                } else {
                    PmsTask.this.setException(new PaymentServiceFailureException(2, "activity cannot be null"));
                }
            }

            public void onError(int i, String str, Bundle bundle) throws RemoteException {
                if (i == 4) {
                    PmsTask.this.cancel(true);
                    PmsTask.this.g();
                    return;
                }
                PmsTask.this.setException(PmsTask.this.a(i, str, bundle));
            }
        }

        /* access modifiers changed from: private */
        public Exception a(int i2, String str, Bundle bundle) {
            if (i2 == 3) {
                return new IOException(str);
            }
            if (i2 == 5) {
                return new AuthenticationFailureException(str);
            }
            if (TextUtils.isEmpty(str)) {
                str = "Unknown payment failure";
            }
            return new PaymentServiceFailureException(i2, str, bundle);
        }

        private void j() {
            Looper myLooper = Looper.myLooper();
            if (myLooper != null && myLooper == PaymentManager.this.D.getMainLooper()) {
                IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
                Log.e(PaymentManager.B, "calling this from your main thread can lead to deadlock and/or ANRs", illegalStateException);
                throw illegalStateException;
            }
        }
    }
}
