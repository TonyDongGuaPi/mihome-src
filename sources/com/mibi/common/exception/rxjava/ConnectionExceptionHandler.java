package com.mibi.common.exception.rxjava;

import android.os.Bundle;
import com.mibi.common.exception.CertificateDateNotValidException;
import com.mibi.common.exception.ConnectionException;
import com.mibi.common.exception.NetworkException;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import junit.framework.Assert;

public class ConnectionExceptionHandler implements ExceptionDispatcher.ExceptionHandler {
    public Class<? extends Throwable> a() {
        return ConnectionException.class;
    }

    public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
        Assert.assertTrue(th instanceof ConnectionException);
        Throwable cause = th.getCause();
        if (cause == null || !(cause instanceof CertificateDateNotValidException)) {
            return exceptionDispatcher.a(th, bundle, NetworkException.class);
        }
        return exceptionDispatcher.a(cause, bundle, CertificateDateNotValidException.class);
    }
}
