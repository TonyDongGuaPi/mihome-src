package com.xiaomi.jr.appbase.accounts;

import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Bundle;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.account.Constants;
import com.xiaomi.passport.servicetoken.ServiceTokenFuture;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServiceTokenAccountManagerFuture implements AccountManagerFuture<Bundle> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10314a = "ServiceTokenAccountManagerFuture";
    private ServiceTokenFuture b;

    public ServiceTokenAccountManagerFuture(ServiceTokenFuture serviceTokenFuture) {
        this.b = serviceTokenFuture;
        if (serviceTokenFuture == null) {
            QualityMonitor.a(Constants.j, "get_account_info", "ServiceTokenFuture from sdk is null");
        }
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
        String str = null;
        if (serviceTokenResult == null) {
            QualityMonitor.a(Constants.j, "get_account_info", "ServiceTokenResult is null");
            return null;
        } else if (serviceTokenResult.errorCode == ServiceTokenResult.ErrorCode.ERROR_NONE || serviceTokenResult.errorCode == ServiceTokenResult.ErrorCode.ERROR_USER_INTERACTION_NEEDED) {
            try {
                bundle = ServiceTokenConverter.a(serviceTokenResult);
            } catch (Exception e) {
                str = "convert error: " + e.getMessage();
                bundle = null;
            }
            if (bundle == null) {
                QualityMonitor.a(Constants.j, "get_account_info", "ServiceTokenResult covert to bundle failed: " + str);
            }
            return bundle;
        } else {
            QualityMonitor.a(Constants.j, "get_account_info", "ServiceTokenResult Error(" + serviceTokenResult.errorCode + "): " + serviceTokenResult.errorMessage);
            return null;
        }
    }
}
