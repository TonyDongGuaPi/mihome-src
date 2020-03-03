package com.mipay.sdk.exception;

import android.os.Bundle;

public class MipayException extends Exception {
    private static final long c = 1;

    /* renamed from: a  reason: collision with root package name */
    private int f8168a;
    private Bundle b;

    public MipayException(int i, String str) {
        this(i, str, (Bundle) null);
    }

    public MipayException(int i, String str, Bundle bundle) {
        super(str);
        this.f8168a = i;
        if (bundle == null) {
            this.b = new Bundle();
        } else {
            this.b = bundle;
        }
    }

    public MipayException(int i, Throwable th) {
        super(th);
        this.f8168a = i;
        this.b = new Bundle();
    }

    public int getError() {
        return this.f8168a;
    }

    public Bundle getErrorResult() {
        return this.b;
    }
}
