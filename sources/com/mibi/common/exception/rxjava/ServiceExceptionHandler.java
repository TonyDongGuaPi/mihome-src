package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ServerException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import junit.framework.Assert;

public class ServiceExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7567a;

    public ServiceExceptionHandler(Context context) {
        this.f7567a = context;
    }

    public Class<? extends PaymentException> a() {
        return ServerException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        Assert.assertTrue(th instanceof ServerException);
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 6);
        }
        String str = this.f7567a.getString(R.string.mibi_error_server_summary) + ((ServerException) th).getResponseCode();
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", str);
        }
        return exceptionDispatcher.a(th, bundle, PaymentException.class);
    }
}
