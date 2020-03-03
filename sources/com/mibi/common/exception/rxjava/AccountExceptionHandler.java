package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.AccountException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class AccountExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7555a;

    public AccountExceptionHandler(Context context) {
        this.f7555a = context;
    }

    public Class<? extends Throwable> a() {
        return AccountException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 5);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.f7555a.getString(R.string.mibi_error_auth_summary));
        }
        return exceptionDispatcher.a(th, bundle, PaymentException.class);
    }
}
