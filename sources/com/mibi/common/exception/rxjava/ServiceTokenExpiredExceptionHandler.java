package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ServiceTokenExpiredException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class ServiceTokenExpiredExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7568a;

    public ServiceTokenExpiredExceptionHandler(Context context) {
        this.f7568a = context;
    }

    public Class<? extends PaymentException> a() {
        return ServiceTokenExpiredException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 6);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.f7568a.getString(R.string.mibi_error_server_summary));
        }
        return exceptionDispatcher.a(th, bundle, PaymentException.class);
    }
}