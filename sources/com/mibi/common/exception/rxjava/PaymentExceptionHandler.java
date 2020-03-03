package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.taobao.weex.el.parse.Operators;

public abstract class PaymentExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    protected Context f7564a;

    /* access modifiers changed from: protected */
    public abstract void a(int i, String str, Throwable th);

    public PaymentExceptionHandler(Context context) {
        this.f7564a = context;
    }

    public Class<? extends Throwable> a() {
        return PaymentException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 5);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.f7564a.getString(R.string.mibi_error_auth_summary));
        }
        int i = bundle.getInt("errcode");
        a(i, bundle.getString("errDesc") + Operators.ARRAY_START_STR + ((PaymentException) th).getFullIdentifier() + Operators.ARRAY_END_STR, th);
        return true;
    }
}
