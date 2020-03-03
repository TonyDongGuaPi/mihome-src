package com.xiaomi.passport.servicetoken;

import android.content.Context;
import android.os.AsyncTask;
import com.xiaomi.accountsdk.futureservice.ClientFuture;
import com.xiaomi.accountsdk.futureservice.MiAccountManagerFuture;
import com.xiaomi.passport.servicetoken.data.XmAccountVisibility;
import java.util.concurrent.Executor;

abstract class ServiceTokenUtilImplBase implements IServiceTokenUtil {
    /* access modifiers changed from: private */
    public static Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;

    /* access modifiers changed from: protected */
    public abstract XmAccountVisibility canAccessAccountImpl(Context context);

    /* access modifiers changed from: protected */
    public abstract ServiceTokenResult getServiceTokenImpl(Context context, String str);

    /* access modifiers changed from: protected */
    public abstract ServiceTokenResult invalidateServiceTokenImpl(Context context, ServiceTokenResult serviceTokenResult);

    ServiceTokenUtilImplBase() {
    }

    public final ServiceTokenFuture getServiceToken(final Context context, final String str) {
        return new MyWorker() {
            /* access modifiers changed from: protected */
            public ServiceTokenResult realWork() {
                return ServiceTokenUtilImplBase.this.getServiceTokenImpl(context, str);
            }
        }.work();
    }

    public final ServiceTokenFuture invalidateServiceToken(final Context context, final ServiceTokenResult serviceTokenResult) {
        return new MyWorker() {
            /* access modifiers changed from: protected */
            public ServiceTokenResult realWork() {
                return ServiceTokenUtilImplBase.this.invalidateServiceTokenImpl(context, serviceTokenResult);
            }
        }.work();
    }

    public MiAccountManagerFuture<XmAccountVisibility> canAccessAccount(final Context context) {
        return new MiAccountManagerFuture<XmAccountVisibility>() {
            public XmAccountVisibility doWork() {
                return ServiceTokenUtilImplBase.this.canAccessAccountImpl(context);
            }
        }.start();
    }

    private static abstract class MyWorker {
        /* access modifiers changed from: protected */
        public abstract ServiceTokenResult realWork();

        private MyWorker() {
        }

        /* access modifiers changed from: package-private */
        public ServiceTokenFuture work() {
            final ServiceTokenFuture serviceTokenFuture = new ServiceTokenFuture((ClientFuture.ClientCallback<ServiceTokenResult>) null);
            ServiceTokenUtilImplBase.executor.execute(new Runnable() {
                public void run() {
                    try {
                        serviceTokenFuture.setServerData(MyWorker.this.realWork());
                    } catch (Throwable th) {
                        serviceTokenFuture.setServerSideThrowable(th);
                    }
                }
            });
            return serviceTokenFuture;
        }
    }
}
