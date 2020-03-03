package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.exception.NoPrivacyRightException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;

public class NoPrivacyRightExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7561a;

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        return false;
    }

    public NoPrivacyRightExceptionHandler(Context context) {
        this.f7561a = context;
    }

    public Class<? extends PaymentException> a() {
        return NoPrivacyRightException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        String string = this.f7561a.getString(R.string.mibi_error_privacy_summary);
        bundle.putString("errDesc", string);
        bundle.putInt("errcode", 18);
        if (a(18, string)) {
            return true;
        }
        return exceptionDispatcher.a(th, bundle, PaymentException.class);
    }
}
