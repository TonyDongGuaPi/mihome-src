package com.xiaomi.phonenum.obtain;

import com.xiaomi.phonenum.bean.Error;

public class PhoneException extends Exception {
    public final Error error;

    public PhoneException(Error error2) {
        this(error2, error2.name());
    }

    public PhoneException(Error error2, String str) {
        this(error2, str, (Throwable) null);
    }

    public PhoneException(Error error2, String str, Throwable th) {
        super(str, th);
        this.error = error2;
    }
}
