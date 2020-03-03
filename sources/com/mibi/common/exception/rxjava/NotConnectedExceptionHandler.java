package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.NetworkException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class NotConnectedExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7562a;

    public NotConnectedExceptionHandler(Context context) {
        this.f7562a = context;
    }

    public Class<? extends Throwable> a() {
        return NetworkException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 3);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.f7562a.getString(R.string.mibi_error_network_summary) + ":NC");
        }
        return exceptionDispatcher.a(th, bundle, PaymentException.class);
    }
}
