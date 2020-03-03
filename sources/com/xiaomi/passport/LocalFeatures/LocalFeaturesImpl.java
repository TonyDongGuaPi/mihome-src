package com.xiaomi.passport.LocalFeatures;

import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.xiaomi.accounts.ILocalFeatureManagerResponse;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.account.ServerErrorCode;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.exception.IllegalDeviceException;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.account.exception.NeedNotificationException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;

public class LocalFeaturesImpl implements MiLocalFeaturesManager {
    private static final String TAG = "LocalFeaturesImpl";
    /* access modifiers changed from: private */
    public static final ExecutorService THREAD_POOL_EXECUTOR = Executors.newFixedThreadPool(5);
    private static LocalFeaturesImpl sInstance = null;
    /* access modifiers changed from: private */
    public Context mContext;
    private Handler mMainHandler = new Handler(this.mContext.getMainLooper());

    LocalFeaturesImpl(Context context) {
        this.mContext = context;
    }

    public static synchronized LocalFeaturesImpl get(Context context) {
        LocalFeaturesImpl localFeaturesImpl;
        synchronized (LocalFeaturesImpl.class) {
            if (sInstance == null) {
                sInstance = new LocalFeaturesImpl(context);
            }
            localFeaturesImpl = sInstance;
        }
        return localFeaturesImpl;
    }

    public LocalFeaturesManagerFuture<Bundle> getStsUrl(String str, String str2, String str3, Bundle bundle, Activity activity, LocalFeaturesManagerCallback<Bundle> localFeaturesManagerCallback, Handler handler) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("userId or password options is null");
        }
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        return new AmsTask(activity, handler, localFeaturesManagerCallback) {
            public void doWork() throws RemoteException {
                LocalFeaturesImpl.THREAD_POOL_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        LocalFeaturesImpl.this.getStsUrl(AnonymousClass1.this.mResponse, str4, str5, str6);
                    }
                });
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public void getStsUrl(LocalFeaturesManagerResponse localFeaturesManagerResponse, String str, String str2, String str3) {
        if (localFeaturesManagerResponse != null) {
            String str4 = null;
            try {
                AccountInfo stsUrlByPassword = AccountHelper.getStsUrlByPassword(str, str2, str3, (String) null, (String) null);
                Bundle bundle = new Bundle();
                if (stsUrlByPassword != null) {
                    str4 = stsUrlByPassword.getAutoLoginUrl();
                }
                bundle.putString("sts_url", str4);
                localFeaturesManagerResponse.onResult(bundle);
            } catch (NeedCaptchaException e) {
                NeedCaptchaException needCaptchaException = e;
                AccountLog.w(TAG, (Throwable) needCaptchaException);
                onBackIntent(localFeaturesManagerResponse, str, str2, str3, needCaptchaException);
            } catch (NeedVerificationException e2) {
                NeedVerificationException needVerificationException = e2;
                AccountLog.w(TAG, (Throwable) needVerificationException);
                onBackIntent(localFeaturesManagerResponse, str, str2, str3, needVerificationException);
            } catch (NeedNotificationException e3) {
                NeedNotificationException needNotificationException = e3;
                AccountLog.w(TAG, (Throwable) needNotificationException);
                onBackIntent(localFeaturesManagerResponse, str, str2, str3, needNotificationException);
            } catch (InvalidCredentialException e4) {
                InvalidCredentialException invalidCredentialException = e4;
                AccountLog.w(TAG, (Throwable) invalidCredentialException);
                if (invalidCredentialException.getHasPwd()) {
                    localFeaturesManagerResponse.onError(4, invalidCredentialException.getMessage());
                } else {
                    onBackIntent(localFeaturesManagerResponse, str, str2, str3, invalidCredentialException);
                }
            } catch (IOException e5) {
                AccountLog.w(TAG, (Throwable) e5);
                localFeaturesManagerResponse.onError(5, e5.getMessage());
            } catch (InvalidResponseException e6) {
                AccountLog.w(TAG, (Throwable) e6);
                localFeaturesManagerResponse.onError(6, e6.getMessage());
            } catch (AccessDeniedException e7) {
                AccountLog.w(TAG, (Throwable) e7);
                localFeaturesManagerResponse.onError(7, e7.getMessage());
            } catch (InvalidUserNameException e8) {
                AccountLog.w(TAG, (Throwable) e8);
                localFeaturesManagerResponse.onError(8, e8.getMessage());
            } catch (AuthenticationFailureException e9) {
                AccountLog.w(TAG, (Throwable) e9);
                localFeaturesManagerResponse.onError(6, e9.getMessage());
            } catch (IllegalDeviceException e10) {
                AccountLog.w(TAG, (Throwable) e10);
                localFeaturesManagerResponse.onError(9, e10.getMessage());
            }
        } else {
            throw new IllegalArgumentException("response is null");
        }
    }

    private void onBackIntent(LocalFeaturesManagerResponse localFeaturesManagerResponse, String str, String str2, String str3, Exception exc) {
        Bundle bundle = new Bundle();
        bundle.putString("userId", str);
        bundle.putString("service_id", str3);
        bundle.putString("password", str2);
        bundle.putBoolean("need_retry_on_authenticator_response_result", true);
        Intent resultIntent = getResultIntent(localFeaturesManagerResponse, exc, bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("intent", resultIntent);
        localFeaturesManagerResponse.onResult(bundle2);
    }

    private Intent getResultIntent(LocalFeaturesManagerResponse localFeaturesManagerResponse, Exception exc, Bundle bundle) {
        if (exc instanceof NeedCaptchaException) {
            bundle.putString("captcha_url", ((NeedCaptchaException) exc).getCaptchaUrl());
            return AuthenticatorUtil.newQuickLoginIntent(this.mContext, localFeaturesManagerResponse, bundle);
        } else if (exc instanceof NeedNotificationException) {
            return AuthenticatorUtil.newNotificationIntent(this.mContext, localFeaturesManagerResponse, ((NeedNotificationException) exc).getNotificationUrl(), (String) null, true, bundle);
        } else if (!(exc instanceof NeedVerificationException)) {
            return AuthenticatorUtil.newQuickLoginIntent(this.mContext, localFeaturesManagerResponse, bundle);
        } else {
            NeedVerificationException needVerificationException = (NeedVerificationException) exc;
            MetaLoginData metaLoginData = needVerificationException.getMetaLoginData();
            bundle.putString("extra_step1_token", needVerificationException.getStep1Token());
            bundle.putString("extra_sign", metaLoginData.sign);
            bundle.putString("extra_qs", metaLoginData.qs);
            bundle.putString("extra_callback", metaLoginData.callback);
            return AuthenticatorUtil.newQuickLoginIntent(this.mContext, localFeaturesManagerResponse, bundle);
        }
    }

    public LocalFeaturesManagerFuture<Bundle> handleLoginQRCodeScanResult(String str, Activity activity, Bundle bundle, LocalFeaturesManagerCallback<Bundle> localFeaturesManagerCallback, Handler handler) {
        final String str2 = str;
        final Activity activity2 = activity;
        final Bundle bundle2 = bundle;
        return new AmsTask(activity, handler, localFeaturesManagerCallback) {
            public void doWork() throws RemoteException {
                LocalFeaturesImpl.THREAD_POOL_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        if (!AccountHelper.isMiAccountLoginQRCodeScanResult(str2)) {
                            AnonymousClass2.this.mResponse.onError(7, "invalid scan code login url");
                            return;
                        }
                        Intent intent = new Intent("android.intent.action.VIEW");
                        if (MiAccountManager.get(LocalFeaturesImpl.this.mContext).isUseSystem()) {
                            intent.setClassName(AccountIntent.PACKAGE_XIAOMI_ACCOUNT, "com.xiaomi.account.ui.AccountWebActivity");
                        } else {
                            ComponentName processScanLoginQRCodeComponentName = PassportExternal.getAuthenticatorComponentNameInterface(activity2).getProcessScanLoginQRCodeComponentName();
                            if (processScanLoginQRCodeComponentName == null) {
                                AnonymousClass2.this.mResponse.onError(8, "custom ui not implements process scan login QR Code");
                                return;
                            }
                            intent.setComponent(processScanLoginQRCodeComponentName);
                        }
                        intent.putExtra("accountAuthenticatorResponse", AnonymousClass2.this.mResponse);
                        intent.setData(Uri.parse(str2));
                        if (bundle2 != null) {
                            intent.putExtras(bundle2);
                        }
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("intent", intent);
                        AnonymousClass2.this.mResponse.onResult(bundle);
                    }
                });
            }
        }.start();
    }

    private abstract class AmsTask extends FutureTask<Bundle> implements LocalFeaturesManagerFuture<Bundle> {
        final Activity mActivity;
        final LocalFeaturesManagerCallback<Bundle> mCallback;
        final Handler mHandler;
        final LocalFeaturesManagerResponse mResponse = new LocalFeaturesManagerResponse((ILocalFeatureManagerResponse) new Response());

        public abstract void doWork() throws RemoteException;

        public AmsTask(Activity activity, Handler handler, LocalFeaturesManagerCallback<Bundle> localFeaturesManagerCallback) {
            super(new Callable<Bundle>() {
                public Bundle call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
            this.mHandler = handler;
            this.mCallback = localFeaturesManagerCallback;
            this.mActivity = activity;
        }

        public final LocalFeaturesManagerFuture<Bundle> start() {
            try {
                doWork();
            } catch (RemoteException e) {
                setException(e);
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public void set(Bundle bundle) {
            if (bundle == null) {
                AccountLog.e(LocalFeaturesImpl.TAG, "the bundle must not be null", new Exception());
            }
            super.set(bundle);
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x00c4 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.os.Bundle internalGetResult(java.lang.Long r4, java.util.concurrent.TimeUnit r5) throws android.accounts.OperationCanceledException, java.io.IOException, android.accounts.AuthenticatorException, com.xiaomi.accountsdk.account.exception.InvalidCredentialException, com.xiaomi.accountsdk.account.exception.InvalidUserNameException, com.xiaomi.accountsdk.request.InvalidResponseException, com.xiaomi.accountsdk.account.exception.IllegalDeviceException, com.xiaomi.accountsdk.request.AccessDeniedException, com.xiaomi.accountsdk.request.AuthenticationFailureException {
            /*
                r3 = this;
                boolean r0 = r3.isDone()
                if (r0 != 0) goto L_0x000b
                com.xiaomi.passport.LocalFeatures.LocalFeaturesImpl r0 = com.xiaomi.passport.LocalFeatures.LocalFeaturesImpl.this
                r0.ensureNotOnMainThread()
            L_0x000b:
                r0 = 1
                if (r4 != 0) goto L_0x001d
                java.lang.Object r4 = r3.get()     // Catch:{ CancellationException -> 0x00c4, InterruptedException | TimeoutException -> 0x00bb, ExecutionException -> 0x001b }
                android.os.Bundle r4 = (android.os.Bundle) r4     // Catch:{ CancellationException -> 0x00c4, InterruptedException | TimeoutException -> 0x00bb, ExecutionException -> 0x001b }
                r3.cancel(r0)
                return r4
            L_0x0018:
                r4 = move-exception
                goto L_0x00ca
            L_0x001b:
                r4 = move-exception
                goto L_0x002b
            L_0x001d:
                long r1 = r4.longValue()     // Catch:{ CancellationException -> 0x00c4, InterruptedException | TimeoutException -> 0x00bb, ExecutionException -> 0x001b }
                java.lang.Object r4 = r3.get(r1, r5)     // Catch:{ CancellationException -> 0x00c4, InterruptedException | TimeoutException -> 0x00bb, ExecutionException -> 0x001b }
                android.os.Bundle r4 = (android.os.Bundle) r4     // Catch:{ CancellationException -> 0x00c4, InterruptedException | TimeoutException -> 0x00bb, ExecutionException -> 0x001b }
                r3.cancel(r0)
                return r4
            L_0x002b:
                java.lang.Throwable r5 = r4.getCause()     // Catch:{ all -> 0x0018 }
                boolean r1 = r5 instanceof java.io.IOException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x00b8
                boolean r1 = r5 instanceof java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x00b2
                boolean r1 = r5 instanceof android.accounts.AuthenticatorException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x00af
                boolean r1 = r5 instanceof java.lang.RuntimeException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x00ac
                boolean r1 = r5 instanceof java.lang.Error     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x00a9
                boolean r1 = r5 instanceof com.xiaomi.accountsdk.account.exception.InvalidCredentialException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x009f
                boolean r1 = r5 instanceof com.xiaomi.accountsdk.account.exception.InvalidUserNameException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x0099
                boolean r1 = r5 instanceof com.xiaomi.accountsdk.request.InvalidResponseException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x008f
                boolean r1 = r5 instanceof com.xiaomi.accountsdk.account.exception.IllegalDeviceException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x0085
                boolean r1 = r5 instanceof javax.net.ssl.SSLException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x007b
                boolean r1 = r5 instanceof com.xiaomi.accountsdk.request.AccessDeniedException     // Catch:{ all -> 0x0018 }
                if (r1 != 0) goto L_0x006f
                boolean r1 = r5 instanceof com.xiaomi.accountsdk.request.AuthenticationFailureException     // Catch:{ all -> 0x0018 }
                if (r1 == 0) goto L_0x0069
                com.xiaomi.accountsdk.request.AuthenticationFailureException r5 = new com.xiaomi.accountsdk.request.AuthenticationFailureException     // Catch:{ all -> 0x0018 }
                java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0018 }
                r5.<init>(r4)     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x0069:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0018 }
                r4.<init>(r5)     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x006f:
                com.xiaomi.accountsdk.request.AccessDeniedException r5 = new com.xiaomi.accountsdk.request.AccessDeniedException     // Catch:{ all -> 0x0018 }
                r1 = 403(0x193, float:5.65E-43)
                java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0018 }
                r5.<init>(r1, r4)     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x007b:
                javax.net.ssl.SSLException r5 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x0018 }
                java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0018 }
                r5.<init>(r4)     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x0085:
                com.xiaomi.accountsdk.account.exception.IllegalDeviceException r5 = new com.xiaomi.accountsdk.account.exception.IllegalDeviceException     // Catch:{ all -> 0x0018 }
                java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0018 }
                r5.<init>(r4)     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x008f:
                com.xiaomi.accountsdk.request.InvalidResponseException r5 = new com.xiaomi.accountsdk.request.InvalidResponseException     // Catch:{ all -> 0x0018 }
                java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0018 }
                r5.<init>(r4)     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x0099:
                com.xiaomi.accountsdk.account.exception.InvalidUserNameException r4 = new com.xiaomi.accountsdk.account.exception.InvalidUserNameException     // Catch:{ all -> 0x0018 }
                r4.<init>()     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x009f:
                com.xiaomi.accountsdk.account.exception.InvalidCredentialException r4 = new com.xiaomi.accountsdk.account.exception.InvalidCredentialException     // Catch:{ all -> 0x0018 }
                com.xiaomi.accountsdk.account.exception.InvalidCredentialException r5 = (com.xiaomi.accountsdk.account.exception.InvalidCredentialException) r5     // Catch:{ all -> 0x0018 }
                boolean r5 = r5.hasPwd     // Catch:{ all -> 0x0018 }
                r4.<init>(r5)     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x00a9:
                java.lang.Error r5 = (java.lang.Error) r5     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x00ac:
                java.lang.RuntimeException r5 = (java.lang.RuntimeException) r5     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x00af:
                android.accounts.AuthenticatorException r5 = (android.accounts.AuthenticatorException) r5     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x00b2:
                android.accounts.AuthenticatorException r4 = new android.accounts.AuthenticatorException     // Catch:{ all -> 0x0018 }
                r4.<init>(r5)     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x00b8:
                java.io.IOException r5 = (java.io.IOException) r5     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x00bb:
                r3.cancel(r0)
                android.accounts.OperationCanceledException r4 = new android.accounts.OperationCanceledException
                r4.<init>()
                throw r4
            L_0x00c4:
                android.accounts.OperationCanceledException r4 = new android.accounts.OperationCanceledException     // Catch:{ all -> 0x0018 }
                r4.<init>()     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x00ca:
                r3.cancel(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.LocalFeatures.LocalFeaturesImpl.AmsTask.internalGetResult(java.lang.Long, java.util.concurrent.TimeUnit):android.os.Bundle");
        }

        public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException, InvalidCredentialException, InvalidUserNameException, AccessDeniedException, InvalidResponseException, IllegalDeviceException, AuthenticationFailureException {
            return internalGetResult((Long) null, (TimeUnit) null);
        }

        public Bundle getResult(long j, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException, InvalidCredentialException, InvalidUserNameException, AccessDeniedException, InvalidResponseException, IllegalDeviceException, AuthenticationFailureException {
            return internalGetResult(Long.valueOf(j), timeUnit);
        }

        /* access modifiers changed from: protected */
        public void done() {
            if (this.mCallback != null) {
                LocalFeaturesImpl.this.postToHandler(this.mHandler, this.mCallback, this);
            }
        }

        private class Response extends ILocalFeatureManagerResponse.Stub {
            public void onRequestContinued() throws RemoteException {
            }

            private Response() {
            }

            public void onResult(Bundle bundle) {
                Intent intent = (Intent) bundle.getParcelable("intent");
                if (intent != null && AmsTask.this.mActivity != null) {
                    AmsTask.this.mActivity.startActivity(intent);
                } else if (bundle.getBoolean("retry")) {
                    try {
                        AmsTask.this.doWork();
                    } catch (RemoteException unused) {
                    }
                } else {
                    AmsTask.this.set(bundle);
                }
            }

            public void onError(int i, String str) {
                if (i == 4) {
                    AmsTask.this.cancel(true);
                } else {
                    AmsTask.this.setException(LocalFeaturesImpl.this.convertErrorToException(i, str));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public Exception convertErrorToException(int i, String str) {
        if (i == 5) {
            return new IOException(str);
        }
        if (i == 7) {
            return new AccessDeniedException(403, str);
        }
        if (i == 4) {
            return new InvalidCredentialException(ServerErrorCode.ERROR_PASSWORD, str, true);
        }
        if (i == 10) {
            return new SSLException(str);
        }
        if (i == 6) {
            return new InvalidResponseException(str);
        }
        if (i == 8) {
            return new InvalidUserNameException();
        }
        if (i == 9) {
            return new IllegalDeviceException(str);
        }
        if (i == 6) {
            return new UnsupportedOperationException(str);
        }
        if (i == 5) {
            return new AuthenticatorException(str);
        }
        if (i == 7) {
            return new IllegalArgumentException(str);
        }
        return new AuthenticatorException(str);
    }

    /* access modifiers changed from: private */
    public void postToHandler(Handler handler, final LocalFeaturesManagerCallback<Bundle> localFeaturesManagerCallback, final LocalFeaturesManagerFuture<Bundle> localFeaturesManagerFuture) {
        if (handler == null) {
            handler = this.mMainHandler;
        }
        handler.post(new Runnable() {
            public void run() {
                localFeaturesManagerCallback.run(localFeaturesManagerFuture);
            }
        });
    }

    /* access modifiers changed from: private */
    public void ensureNotOnMainThread() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == this.mContext.getMainLooper()) {
            IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
            AccountLog.e(TAG, "calling this from your main thread can lead to deadlock and/or ANRs", illegalStateException);
            if (this.mContext.getApplicationInfo().targetSdkVersion >= 8) {
                throw illegalStateException;
            }
        }
    }
}
