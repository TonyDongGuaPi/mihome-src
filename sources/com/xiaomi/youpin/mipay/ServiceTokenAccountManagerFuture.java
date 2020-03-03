package com.xiaomi.youpin.mipay;

import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Bundle;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.passport.servicetoken.ServiceTokenFuture;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.youpin.mipay.ServiceTokenConverter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class ServiceTokenAccountManagerFuture implements AccountManagerFuture<Bundle> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23648a = "ServiceTokenAccountManagerFuture";
    private ServiceTokenFuture b;

    ServiceTokenAccountManagerFuture(ServiceTokenFuture serviceTokenFuture) {
        this.b = serviceTokenFuture;
    }

    public boolean cancel(boolean z) {
        return this.b != null && this.b.cancel(z);
    }

    public boolean isCancelled() {
        return this.b != null && this.b.isCancelled();
    }

    public boolean isDone() {
        return this.b != null && this.b.isDone();
    }

    /* renamed from: a */
    public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException {
        return a(this.b != null ? this.b.get() : null);
    }

    /* renamed from: a */
    public Bundle getResult(long j, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
        return a(this.b != null ? this.b.get(j, timeUnit) : null);
    }

    private Bundle a(ServiceTokenResult serviceTokenResult) {
        Bundle bundle;
        try {
            bundle = ServiceTokenConverter.a(serviceTokenResult);
        } catch (ServiceTokenConverter.ConvertException e) {
            e.printStackTrace();
            bundle = null;
        }
        if (serviceTokenResult.errorCode == ServiceTokenResult.ErrorCode.ERROR_NONE) {
            return bundle;
        }
        MifiLog.e(f23648a, "ServiceTokenResult Error: " + serviceTokenResult.errorMessage);
        return null;
    }
}
