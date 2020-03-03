package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.PasswordErrorException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class PasswordErrorExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7563a = 3;
    private Context b;

    public PasswordErrorExceptionHandler(Context context) {
        this.b = context;
    }

    public Class<? extends Throwable> a() {
        return PasswordErrorException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 3);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.b.getString(R.string.mibi_password_error));
        }
        return exceptionDispatcher.a(th, bundle, PaymentException.class);
    }
}
