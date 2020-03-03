package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.AccountException;
import com.mibi.common.exception.IllegalDeviceException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class IllegalDeviceExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7559a;

    public IllegalDeviceExceptionHandler(Context context) {
        this.f7559a = context;
    }

    public Class<? extends Throwable> a() {
        return IllegalDeviceException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 5);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.f7559a.getString(R.string.mibi_error_invalid_device));
        }
        return exceptionDispatcher.a(th, bundle, AccountException.class);
    }
}
