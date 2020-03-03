package com.xiaomi.passport;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.passport.ISecurityDeviceSignResponse;
import com.xiaomi.passport.ISecurityDeviceSignService;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class SecurityDeviceSignManager {
    private static final String ACTION_SECURITY_DEVICE_SIGN = "com.xiaomi.account.action.SECURITY_DEVICE_SIGN";
    public static final String KEY_BOOLEAN_RESULT = "booleanResult";
    public static final String KEY_ERROR_CODE = "errorCode";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_EXTRA_PARAMS_JSON_STRING = "extraParamsJsonStr";
    public static final String KEY_USER_DATA = "userData";
    private static final String PACKAGE_NAME_SERVICE = "com.xiaomi.account";
    private static final String TAG = "SecurityDeviceSignManager";
    private static final ExecutorService mThreadPool = Executors.newCachedThreadPool();

    public static FutureTask<Bundle> sign(final Context context, final String str, final Bundle bundle) {
        if (context == null) {
            throw new IllegalArgumentException("context should not be null");
        } else if (!TextUtils.isEmpty(str)) {
            FutureTask<Bundle> futureTask = new FutureTask<>(new Callable<Bundle>() {
                public Bundle call() throws Exception {
                    if (SecurityDeviceSignManager.checkHasSignService(context)) {
                        return SecurityDeviceSignManager.blockingSignImpl(context, str, bundle);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("booleanResult", false);
                    bundle.putInt("errorCode", 4);
                    bundle.putString("errorMessage", "no sign service");
                    return bundle;
                }
            });
            mThreadPool.submit(futureTask);
            return futureTask;
        } else {
            throw new IllegalArgumentException("input data should not be empty");
        }
    }

    /* access modifiers changed from: private */
    public static boolean checkHasSignService(Context context) {
        Intent intent = new Intent(ACTION_SECURITY_DEVICE_SIGN);
        intent.setPackage("com.xiaomi.account");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static Bundle blockingSignImpl(final Context context, final String str, final Bundle bundle) {
        String str2;
        try {
            return (Bundle) new SignServiceFutureTask(context) {
                /* access modifiers changed from: protected */
                public void callServiceWork(ISecurityDeviceSignService iSecurityDeviceSignService) throws RemoteException {
                    iSecurityDeviceSignService.sign(this.mResponse, context.getPackageName(), str, bundle);
                }
            }.start().get();
        } catch (InterruptedException e) {
            str2 = e.getMessage();
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("booleanResult", false);
            bundle2.putInt("errorCode", 1);
            bundle2.putString("errorMessage", str2);
            return bundle2;
        } catch (ExecutionException e2) {
            str2 = e2.getMessage();
            Bundle bundle22 = new Bundle();
            bundle22.putBoolean("booleanResult", false);
            bundle22.putInt("errorCode", 1);
            bundle22.putString("errorMessage", str2);
            return bundle22;
        }
    }

    private static abstract class SignServiceFutureTask extends FutureTask<Bundle> implements ServiceConnection {
        private Context mContext;
        protected ISecurityDeviceSignResponse mResponse = new Response();

        /* access modifiers changed from: protected */
        public abstract void callServiceWork(ISecurityDeviceSignService iSecurityDeviceSignService) throws RemoteException;

        public SignServiceFutureTask(Context context) {
            super(new Callable<Bundle>() {
                public Bundle call() throws Exception {
                    throw new IllegalStateException("should not be call here!");
                }
            });
            this.mContext = context;
        }

        public SignServiceFutureTask start() {
            Intent intent = new Intent(SecurityDeviceSignManager.ACTION_SECURITY_DEVICE_SIGN);
            intent.setPackage("com.xiaomi.account");
            if (!this.mContext.bindService(intent, this, 1)) {
                setException(Build.VERSION.SDK_INT >= 15 ? new RemoteException("failed to bind service") : new RemoteException());
                unbind();
            }
            return this;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                callServiceWork(ISecurityDeviceSignService.Stub.asInterface(iBinder));
            } catch (RemoteException e) {
                setException(e);
                unbind();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            unbind();
        }

        private void unbind() {
            if (this.mContext != null) {
                this.mContext.unbindService(this);
                this.mContext = null;
            }
        }

        private class Response extends ISecurityDeviceSignResponse.Stub {
            private Response() {
            }

            public void onResult(Bundle bundle) throws RemoteException {
                SignServiceFutureTask.this.set(bundle);
            }
        }
    }
}
