package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.AccountChangedException;
import com.mibi.common.exception.AccountException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class AccountChangedExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7554a;

    public AccountChangedExceptionHandler(Context context) {
        this.f7554a = context;
    }

    public Class<? extends Throwable> a() {
        return AccountChangedException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 10);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.f7554a.getString(R.string.mibi_error_account_changed_summary));
        }
        return exceptionDispatcher.a(th, bundle, AccountException.class);
    }
}
