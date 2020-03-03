package miui.payment;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
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
import miui.cloud.exception.AuthenticationFailureException;
import miui.cloud.exception.OperationCancelledException;
import miui.os.Build;
import miui.payment.IPaymentManagerResponse;
import miui.payment.IPaymentManagerService;
import miui.payment.exception.PaymentServiceFailureException;
import miuipub.payment.XmsfPaymentManager;

public class PaymentManager {
    public static final int CAPABILITY = 3;
    private static final boolean DEBUG = true;
    public static final int ERROR_CODE_ACCOUNT_CHANGED = 10;
    public static final int ERROR_CODE_ACCOUNT_FROZEN = 9;
    public static final int ERROR_CODE_AUTHENTICATION_ERROR = 5;
    public static final int ERROR_CODE_CALLER_INVALID = 12;
    public static final int ERROR_CODE_CALL_TOO_FAST = 14;
    public static final int ERROR_CODE_CANCELED = 4;
    public static final int ERROR_CODE_DUPLICATE_PURCHASE = 7;
    public static final int ERROR_CODE_EXCEPTION = 1;
    public static final int ERROR_CODE_INVALID_PARAMS = 2;
    public static final int ERROR_CODE_NETWORK_ERROR = 3;
    public static final int ERROR_CODE_ORDER_ERROR = 13;
    public static final int ERROR_CODE_SERVER_ERROR = 6;
    public static final int ERROR_CODE_THIRD_PARTY = 11;
    public static final int ERROR_CODE_USER_ID_MISMATCH = 8;
    public static final String KEY_ACCOUNT = "account";
    public static final String KEY_INTENT = "intent";
    public static final String PAYMENT_KEY_PAYMENT_RESULT = "payment_payment_result";
    public static final String PAYMENT_KEY_QUICK = "payment_quick";
    public static final String PAYMENT_KEY_TRADE_BALANCE = "payment_trade_balance";
    private static final String TAG = "PaymentManager";
    public static final String XIAOMI_ACCOUNT_TYPE = "com.xiaomi";
    private static final String XIAOMI_PAYMENT_AUTH_TOKEN_TYPE = "billcenter";
    /* access modifiers changed from: private */
    public final AccountManager mAccountManager = AccountManager.get(this.mContext);
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mMainHandler = new Handler(this.mContext.getMainLooper());

    private interface LoginCallback {
        void onFailed(int i, String str);

        void onSuccess(Account account);
    }

    public interface PaymentListener {
        void onFailed(String str, int i, String str2, Bundle bundle);

        void onSuccess(String str, Bundle bundle);
    }

    private interface PaymentManagerCallback<V> {
        void run(PaymentManagerFuture<V> paymentManagerFuture);
    }

    private interface PaymentManagerFuture<V> {
        boolean cancel(boolean z);

        V getResult() throws IOException, OperationCancelledException, AuthenticationFailureException, PaymentServiceFailureException;

        V getResult(long j, TimeUnit timeUnit) throws IOException, OperationCancelledException, AuthenticationFailureException, PaymentServiceFailureException;

        boolean isCancelled();

        boolean isDone();
    }

    private PaymentManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static PaymentManager get(Context context) {
        return new PaymentManager(context);
    }

    public boolean isMibiServiceDisabled() {
        return Build.IS_INTERNATIONAL_BUILD;
    }

    public void payForOrder(Activity activity, String str, String str2, Bundle bundle, PaymentListener paymentListener) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (!TextUtils.isEmpty(str2)) {
            final String str3 = str;
            final PaymentListener paymentListener2 = paymentListener;
            final Activity activity2 = activity;
            final String str4 = str2;
            final Bundle bundle2 = bundle;
            login(activity, new LoginCallback() {
                public void onSuccess(Account account) {
                    Account account2 = account;
                    PaymentManagerFuture unused = PaymentManager.this.internalPayForOrder(activity2, account2, str4, bundle2, new PaymentCallback("thd", str3, paymentListener2));
                }

                public void onFailed(int i, String str) {
                    paymentListener2.onFailed(str3, i, str, new Bundle());
                }
            });
        } else {
            throw new InvalidParameterException("order cannot be empty");
        }
    }

    public void recharge(final Activity activity, String str, final String str2, final String str3) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(str2)) {
            throw new InvalidParameterException("serviceId cannot be empty");
        } else if (!TextUtils.isEmpty(str3)) {
            login(activity, new LoginCallback() {
                public void onFailed(int i, String str) {
                }

                public void onSuccess(Account account) {
                    PaymentManagerFuture unused = PaymentManager.this.internalRecharge(activity, account, str2, str3);
                }
            });
        } else {
            throw new InvalidParameterException("verify cannot be empty");
        }
    }

    public void getMiliBalance(Activity activity, String str, String str2, String str3, PaymentListener paymentListener) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(str2)) {
            throw new InvalidParameterException("serviceId cannot be empty");
        } else if (!TextUtils.isEmpty(str3)) {
            final String str4 = str2;
            final String str5 = str;
            final PaymentListener paymentListener2 = paymentListener;
            final Activity activity2 = activity;
            final String str6 = str3;
            login(activity, new LoginCallback() {
                public void onSuccess(Account account) {
                    Account account2 = account;
                    PaymentManagerFuture unused = PaymentManager.this.internalGetMiliBalance(activity2, account2, str4, str6, new PaymentCallback(str4, str5, paymentListener2));
                }

                public void onFailed(int i, String str) {
                    paymentListener2.onFailed(str5, i, str, new Bundle());
                }
            });
        } else {
            throw new InvalidParameterException("verify cannot be empty");
        }
    }

    public void gotoMiliCenter(final Activity activity) {
        login(activity, new LoginCallback() {
            public void onFailed(int i, String str) {
            }

            public void onSuccess(Account account) {
                PaymentManager.this.internalGotoMiliCenter(activity, account);
            }
        });
    }

    public void gotoRechargeRecord(final Activity activity, final String str, final String str2) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(str)) {
            throw new InvalidParameterException("serviceId cannot be empty");
        } else if (!TextUtils.isEmpty(str2)) {
            login(activity, new LoginCallback() {
                public void onFailed(int i, String str) {
                }

                public void onSuccess(Account account) {
                    PaymentManager.this.internalGotoRechargeRecord(activity, account, str, str2);
                }
            });
        } else {
            throw new InvalidParameterException("verify cannot be empty");
        }
    }

    public void gotoPayRecord(final Activity activity, final String str, final String str2) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(str)) {
            throw new InvalidParameterException("serviceId cannot be empty");
        } else if (!TextUtils.isEmpty(str2)) {
            login(activity, new LoginCallback() {
                public void onFailed(int i, String str) {
                }

                public void onSuccess(Account account) {
                    PaymentManager.this.internalGotoPayRecord(activity, account, str, str2);
                }
            });
        } else {
            throw new InvalidParameterException("verify cannot be empty");
        }
    }

    private void login(Activity activity, final LoginCallback loginCallback) {
        Account[] accountsByType = this.mAccountManager.getAccountsByType("com.xiaomi");
        if (accountsByType.length == 0) {
            this.mAccountManager.addAccount("com.xiaomi", "billcenter", (String[]) null, (Bundle) null, activity, new AddAccountCallback() {
                public void onSuccess(Account account) {
                    loginCallback.onSuccess(account);
                }

                public void onFailed(int i, String str) {
                    loginCallback.onFailed(i, str);
                }
            }, (Handler) null);
            return;
        }
        loginCallback.onSuccess(accountsByType[0]);
    }

    private abstract class AddAccountCallback implements AccountManagerCallback<Bundle>, LoginCallback {
        private AddAccountCallback() {
        }

        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            if (accountManagerFuture.isDone()) {
                try {
                    if (accountManagerFuture.getResult() == null) {
                        Log.d(PaymentManager.TAG, "login failed : authentication failed");
                        onFailed(5, "authentication failed");
                        return;
                    }
                    Account[] accountsByType = PaymentManager.this.mAccountManager.getAccountsByType("com.xiaomi");
                    if (accountsByType.length == 0) {
                        Log.d(PaymentManager.TAG, "login failed : authentication failed");
                        onFailed(5, "authentication failed");
                        return;
                    }
                    onSuccess(accountsByType[0]);
                } catch (OperationCanceledException e) {
                    Log.d(PaymentManager.TAG, "login failed : user canceled " + e);
                    onFailed(4, e.getMessage());
                } catch (AuthenticatorException e2) {
                    Log.d(PaymentManager.TAG, "login failed : authenticator exception " + e2);
                    onFailed(5, e2.getMessage());
                } catch (IOException e3) {
                    Log.d(PaymentManager.TAG, "login failed : io exception " + e3);
                    onFailed(3, e3.getMessage());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public PaymentManagerFuture<Bundle> internalPayForOrder(Activity activity, Account account, String str, Bundle bundle, PaymentManagerCallback<Bundle> paymentManagerCallback) {
        final Bundle bundle2 = bundle;
        final Account account2 = account;
        final String str2 = str;
        return new PmsTask(activity, paymentManagerCallback) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                IPaymentManagerService service = getService();
                Bundle bundle = new Bundle();
                if (bundle2 != null) {
                    bundle.putAll(bundle2);
                }
                service.payForOrder(getResponse(), account2, str2, bundle);
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public PaymentManagerFuture<Bundle> internalRecharge(Activity activity, Account account, String str, String str2) {
        final Account account2 = account;
        final String str3 = str;
        final String str4 = str2;
        return new PmsTask(activity) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                getService().recharge(getResponse(), account2, str3, str4);
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public PaymentManagerFuture<Bundle> internalGetMiliBalance(Activity activity, Account account, String str, String str2, PaymentManagerCallback<Bundle> paymentManagerCallback) {
        final Account account2 = account;
        final String str3 = str;
        final String str4 = str2;
        return new PmsTask(activity, paymentManagerCallback) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                getService().getMiliBalance(getResponse(), account2, str3, str4);
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public void internalGotoMiliCenter(Activity activity, final Account account) {
        new PmsTask(activity) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                getService().showMiliCenter(getResponse(), account);
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public void internalGotoRechargeRecord(Activity activity, Account account, String str, String str2) {
        final Account account2 = account;
        final String str3 = str;
        final String str4 = str2;
        new PmsTask(activity) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                getService().showRechargeRecord(getResponse(), account2, str3, str4);
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public void internalGotoPayRecord(Activity activity, Account account, String str, String str2) {
        final Account account2 = account;
        final String str3 = str;
        final String str4 = str2;
        new PmsTask(activity) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                getService().showPayRecord(getResponse(), account2, str3, str4);
            }
        }.start();
    }

    private class PaymentCallback implements PaymentManagerCallback<Bundle> {
        private String mPaymentId;
        private PaymentListener mPaymentListener;
        private String mServiceId;

        public PaymentCallback(String str, String str2, PaymentListener paymentListener) {
            this.mServiceId = str;
            this.mPaymentId = str2;
            this.mPaymentListener = paymentListener;
        }

        public void run(PaymentManagerFuture<Bundle> paymentManagerFuture) {
            if (this.mPaymentListener != null) {
                try {
                    Bundle result = paymentManagerFuture.getResult();
                    if (result != null) {
                        this.mPaymentListener.onSuccess(this.mPaymentId, result);
                    } else {
                        this.mPaymentListener.onFailed(this.mPaymentId, 1, "error", new Bundle());
                    }
                } catch (IOException e) {
                    this.mPaymentListener.onFailed(this.mPaymentId, 3, e.getMessage(), new Bundle());
                } catch (OperationCancelledException e2) {
                    this.mPaymentListener.onFailed(this.mPaymentId, 4, e2.getMessage(), new Bundle());
                } catch (AuthenticationFailureException e3) {
                    this.mPaymentListener.onFailed(this.mPaymentId, 5, e3.getMessage(), new Bundle());
                } catch (PaymentServiceFailureException e4) {
                    this.mPaymentListener.onFailed(this.mPaymentId, e4.getError(), e4.getMessage(), e4.getErrorResult());
                } catch (Throwable th) {
                    this.mPaymentListener = null;
                    throw th;
                }
                this.mPaymentListener = null;
            }
        }
    }

    private abstract class PmsTask extends FutureTask<Bundle> implements ServiceConnection, PaymentManagerFuture<Bundle> {
        private final int HOST_MONITOR_HEART_INTERNAL;
        /* access modifiers changed from: private */
        public Activity mActivity;
        /* access modifiers changed from: private */
        public PaymentManagerCallback<Bundle> mCallback;
        private Handler mHandler;
        private Runnable mHostActivityMonitor;
        private boolean mIsBound;
        private IPaymentManagerResponse mResponse;
        private IPaymentManagerService mService;

        /* access modifiers changed from: protected */
        public abstract void doWork() throws RemoteException;

        protected PmsTask(PaymentManager paymentManager, Activity activity) {
            this(paymentManager, activity, (PaymentManagerCallback<Bundle>) null);
        }

        protected PmsTask(PaymentManager paymentManager, Activity activity, PaymentManagerCallback<Bundle> paymentManagerCallback) {
            this(activity, (Handler) null, paymentManagerCallback);
        }

        protected PmsTask(Activity activity, Handler handler, PaymentManagerCallback<Bundle> paymentManagerCallback) {
            super(new Callable<Bundle>() {
                public Bundle call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
            this.mIsBound = false;
            this.HOST_MONITOR_HEART_INTERNAL = 5000;
            this.mHostActivityMonitor = new Runnable() {
                public void run() {
                    Activity access$800 = PmsTask.this.mActivity;
                    if (!PmsTask.this.isDone() && access$800 != null) {
                        if (access$800.isFinishing()) {
                            PmsTask.this.setException(new OperationCancelledException("Operation has been cancelled because host activity has finished."));
                        } else {
                            PaymentManager.this.mMainHandler.postDelayed(this, 5000);
                        }
                    }
                }
            };
            this.mActivity = activity;
            this.mHandler = handler;
            this.mCallback = paymentManagerCallback;
            this.mResponse = new IPaymentManagerResponseImpl();
        }

        /* access modifiers changed from: protected */
        public IPaymentManagerResponse getResponse() {
            return this.mResponse;
        }

        /* access modifiers changed from: protected */
        public IPaymentManagerService getService() {
            return this.mService;
        }

        public final PaymentManagerFuture<Bundle> start() {
            bind();
            return this;
        }

        /* access modifiers changed from: protected */
        public void bind() {
            if (this.mIsBound) {
                return;
            }
            if (!bindToPaymentService()) {
                setException(new PaymentServiceFailureException(1, "bind to service failed"));
                return;
            }
            this.mIsBound = true;
            Log.d(PaymentManager.TAG, "service bound");
        }

        /* access modifiers changed from: protected */
        public void unBind() {
            if (this.mIsBound) {
                PaymentManager.this.mContext.unbindService(this);
                this.mIsBound = false;
                Log.d(PaymentManager.TAG, "service unbinded");
            }
        }

        /* access modifiers changed from: protected */
        public boolean bindToPaymentService() {
            return PaymentManager.this.mContext.bindService(new Intent(XmsfPaymentManager.c), this, 1);
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x0067 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.os.Bundle internalGetResult(java.lang.Long r4, java.util.concurrent.TimeUnit r5) throws java.io.IOException, miui.cloud.exception.OperationCancelledException, miui.cloud.exception.AuthenticationFailureException, miui.payment.exception.PaymentServiceFailureException {
            /*
                r3 = this;
                boolean r0 = r3.isDone()
                if (r0 != 0) goto L_0x0009
                r3.ensureNotOnMainThread()
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
                boolean r5 = r4 instanceof miui.payment.exception.PaymentServiceFailureException     // Catch:{ all -> 0x0016 }
                if (r5 != 0) goto L_0x0056
                boolean r5 = r4 instanceof miui.cloud.exception.AuthenticationFailureException     // Catch:{ all -> 0x0016 }
                if (r5 != 0) goto L_0x0053
                boolean r5 = r4 instanceof miui.cloud.exception.OperationCancelledException     // Catch:{ all -> 0x0016 }
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
                miui.cloud.exception.OperationCancelledException r4 = (miui.cloud.exception.OperationCancelledException) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x0053:
                miui.cloud.exception.AuthenticationFailureException r4 = (miui.cloud.exception.AuthenticationFailureException) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x0056:
                miui.payment.exception.PaymentServiceFailureException r4 = (miui.payment.exception.PaymentServiceFailureException) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x0059:
                java.io.IOException r4 = (java.io.IOException) r4     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x005c:
                r3.cancel(r0)
                miui.cloud.exception.OperationCancelledException r4 = new miui.cloud.exception.OperationCancelledException
                java.lang.String r5 = "cancelled by exception"
                r4.<init>(r5)
                throw r4
            L_0x0067:
                miui.cloud.exception.OperationCancelledException r4 = new miui.cloud.exception.OperationCancelledException     // Catch:{ all -> 0x0016 }
                java.lang.String r5 = "cancelled by user"
                r4.<init>(r5)     // Catch:{ all -> 0x0016 }
                throw r4     // Catch:{ all -> 0x0016 }
            L_0x006f:
                r3.cancel(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.payment.PaymentManager.PmsTask.internalGetResult(java.lang.Long, java.util.concurrent.TimeUnit):android.os.Bundle");
        }

        /* access modifiers changed from: protected */
        public void set(Bundle bundle) {
            super.set(bundle);
            unBind();
        }

        /* access modifiers changed from: protected */
        public void setException(Throwable th) {
            super.setException(th);
            unBind();
        }

        public Bundle getResult(long j, TimeUnit timeUnit) throws IOException, OperationCancelledException, AuthenticationFailureException, PaymentServiceFailureException {
            return internalGetResult(Long.valueOf(j), timeUnit);
        }

        public Bundle getResult() throws IOException, OperationCancelledException, AuthenticationFailureException, PaymentServiceFailureException {
            return internalGetResult((Long) null, (TimeUnit) null);
        }

        /* access modifiers changed from: protected */
        public void done() {
            if (this.mCallback != null) {
                (this.mHandler == null ? PaymentManager.this.mMainHandler : this.mHandler).post(new Runnable() {
                    public void run() {
                        PmsTask.this.mCallback.run(PmsTask.this);
                        PaymentManagerCallback unused = PmsTask.this.mCallback = null;
                    }
                });
            }
            PaymentManager.this.mMainHandler.removeCallbacks(this.mHostActivityMonitor);
            this.mHandler = null;
            this.mActivity = null;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(PaymentManager.TAG, "onServiceConnected, component:" + componentName);
            this.mService = IPaymentManagerService.Stub.asInterface(iBinder);
            try {
                doWork();
                PaymentManager.this.mMainHandler.postDelayed(this.mHostActivityMonitor, 5000);
            } catch (RemoteException e) {
                setException(e);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (!isDone()) {
                Log.e(PaymentManager.TAG, "payment service disconnected, but task is not completed");
                setException(new PaymentServiceFailureException(1, "active service exits unexpectedly"));
            }
            this.mService = null;
        }

        class IPaymentManagerResponseImpl extends IPaymentManagerResponse.Stub {
            IPaymentManagerResponseImpl() {
            }

            public void onResult(Bundle bundle) throws RemoteException {
                Intent intent = (Intent) bundle.getParcelable("intent");
                if (intent == null) {
                    PmsTask.this.set(bundle);
                } else if (PmsTask.this.mActivity != null) {
                    PmsTask.this.mActivity.startActivity(intent);
                } else {
                    PmsTask.this.setException(new PaymentServiceFailureException(2, "activity cannot be null"));
                }
            }

            public void onError(int i, String str, Bundle bundle) throws RemoteException {
                if (i == 4) {
                    PmsTask.this.cancel(true);
                    PmsTask.this.unBind();
                    return;
                }
                PmsTask.this.setException(PmsTask.this.convertErrorCodeToException(i, str, bundle));
            }
        }

        /* access modifiers changed from: private */
        public Exception convertErrorCodeToException(int i, String str, Bundle bundle) {
            if (i == 3) {
                return new IOException(str);
            }
            if (i == 5) {
                return new AuthenticationFailureException(str);
            }
            if (TextUtils.isEmpty(str)) {
                str = "Unknown payment failure";
            }
            return new PaymentServiceFailureException(i, str, bundle);
        }

        private void ensureNotOnMainThread() {
            Looper myLooper = Looper.myLooper();
            if (myLooper != null && myLooper == PaymentManager.this.mContext.getMainLooper()) {
                IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
                Log.e(PaymentManager.TAG, "calling this from your main thread can lead to deadlock and/or ANRs", illegalStateException);
                throw illegalStateException;
            }
        }
    }
}
