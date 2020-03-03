package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class ResultExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7565a;

    public ResultExceptionHandler(Context context) {
        this.f7565a = context;
    }

    public Class<? extends Throwable> a() {
        return ResultException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 6);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.f7565a.getString(R.string.mibi_error_server_summary));
        }
        return exceptionDispatcher.a(th, bundle, PaymentException.class);
    }
}
