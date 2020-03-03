package com.xiaomi.smarthome.international;

import com.xiaomi.smarthome.frame.Error;

public class ApiErrorWrapper extends Exception {
    public int code = -1;
    public String detail = "";
    public String mExtra;

    public ApiErrorWrapper(Error error) {
        super(error.b());
        this.detail = error.b();
        this.code = error.a();
        this.mExtra = error.c();
    }
}
