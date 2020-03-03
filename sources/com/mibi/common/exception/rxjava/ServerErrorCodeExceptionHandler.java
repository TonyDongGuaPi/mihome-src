package com.mibi.common.exception.rxjava;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.R;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ServerErrorCodeException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import junit.framework.Assert;

public class ServerErrorCodeExceptionHandler implements ExceptionDispatcher.ExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private Context f7566a;

    /* access modifiers changed from: protected */
    public boolean a(int i, String str, Object obj) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return false;
    }

    public ServerErrorCodeExceptionHandler(Context context) {
        this.f7566a = context;
    }

    public Class<? extends PaymentException> a() {
        return ServerErrorCodeException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        boolean z;
        Assert.assertTrue(th instanceof ServerErrorCodeException);
        ServerErrorCodeException serverErrorCodeException = (ServerErrorCodeException) th;
        int serverErrorCode = serverErrorCodeException.getServerErrorCode();
        if (serverErrorCode == 1992) {
            z = b();
        } else if (serverErrorCode == 1993) {
            z = c();
        } else if (serverErrorCode == 1569) {
            z = d();
        } else if (serverErrorCode == 8000) {
            z = e();
        } else {
            z = a(serverErrorCode, this.f7566a.getString(R.string.mibi_error_server_summary), serverErrorCodeException.getResult());
        }
        if (z) {
            return true;
        }
        if (!bundle.containsKey("errcode")) {
            bundle.putInt("errcode", serverErrorCode);
        }
        if (!bundle.containsKey("errDesc")) {
            String serverErrorDesc = serverErrorCodeException.getServerErrorDesc();
            if (TextUtils.isEmpty(serverErrorDesc)) {
                serverErrorDesc = this.f7566a.getString(R.string.mibi_error_server_summary);
            }
            bundle.putString("errDesc", serverErrorDesc);
        }
        return exceptionDispatcher.a(th, bundle, PaymentException.class);
    }
}
