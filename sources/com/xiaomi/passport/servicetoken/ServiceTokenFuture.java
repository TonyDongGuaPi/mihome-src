package com.xiaomi.passport.servicetoken;

import android.os.RemoteException;
import com.xiaomi.accountsdk.futureservice.ClientFuture;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ServiceTokenFuture extends ClientFuture<ServiceTokenResult, ServiceTokenResult> {
    /* access modifiers changed from: protected */
    public ServiceTokenResult convertServerDataToClientData(ServiceTokenResult serviceTokenResult) throws Throwable {
        return serviceTokenResult;
    }

    public ServiceTokenFuture(ClientFuture.ClientCallback<ServiceTokenResult> clientCallback) {
        super(clientCallback);
    }

    public void interpretExecutionException(ExecutionException executionException) throws Exception {
        throw new IllegalStateException("not going here");
    }

    public ServiceTokenResult get() {
        return getInternal((Long) null, (TimeUnit) null);
    }

    public ServiceTokenResult get(long j, TimeUnit timeUnit) {
        return getInternal(Long.valueOf(j), timeUnit);
    }

    private ServiceTokenResult getInternal(Long l, TimeUnit timeUnit) {
        if (l == null || timeUnit == null) {
            return (ServiceTokenResult) super.get();
        }
        try {
            return (ServiceTokenResult) super.get(l.longValue(), timeUnit);
        } catch (InterruptedException e) {
            return new ServiceTokenResult.Builder((String) null).errorCode(ServiceTokenResult.ErrorCode.ERROR_CANCELLED).errorMessage(e.getMessage()).build();
        } catch (ExecutionException e2) {
            if (e2.getCause() instanceof RemoteException) {
                return new ServiceTokenResult.Builder((String) null).errorCode(ServiceTokenResult.ErrorCode.ERROR_REMOTE_EXCEPTION).errorMessage(e2.getMessage()).build();
            }
            return new ServiceTokenResult.Builder((String) null).errorCode(ServiceTokenResult.ErrorCode.ERROR_UNKNOWN).errorMessage(e2.getCause() != null ? e2.getCause().getMessage() : e2.getMessage()).build();
        } catch (TimeoutException unused) {
            ServiceTokenResult.Builder errorCode = new ServiceTokenResult.Builder((String) null).errorCode(ServiceTokenResult.ErrorCode.ERROR_TIME_OUT);
            return errorCode.errorMessage("time out after " + l + " " + timeUnit).build();
        }
    }

    public static abstract class ServiceTokenCallback implements ClientFuture.ClientCallback<ServiceTokenResult> {
        /* access modifiers changed from: protected */
        public abstract void call(ServiceTokenFuture serviceTokenFuture);

        public final void call(ClientFuture clientFuture) {
            call((ServiceTokenFuture) clientFuture);
        }
    }
}
