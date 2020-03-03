package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.AccountException;
import com.mibi.common.exception.AccountThrottingException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class AccountThrottingExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7556a;

    public AccountThrottingExceptionHandler(Context context) {
        this.f7556a = context;
    }

    public Class<? extends Throwable> a() {
        return AccountThrottingException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 15);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.f7556a.getString(R.string.mibi_error_account_throtting));
        }
        return exceptionDispatcher.a(th, bundle, AccountException.class);
    }
}
