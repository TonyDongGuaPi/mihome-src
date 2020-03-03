package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.AccountException;
import com.mibi.common.exception.CertificateDateNotValidException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class CertificateDateNotValidExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7557a;

    public CertificateDateNotValidExceptionHandler(Context context) {
        this.f7557a = context;
    }

    public Class<? extends Throwable> a() {
        return CertificateDateNotValidException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", 3);
        }
        if (!bundle.containsKey("errDesc")) {
            bundle.putString("errDesc", this.f7557a.getString(R.string.mibi_error_cert_date_summary));
        }
        return exceptionDispatcher.a(th, bundle, AccountException.class);
    }
}
