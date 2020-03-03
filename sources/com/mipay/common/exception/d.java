package com.mipay.common.exception;

import org.cybergarage.http.HTTP;

public class d extends f {
    public d() {
    }

    public d(String str) {
        super(str);
    }

    public d(String str, Throwable th) {
        super(str, th);
    }

    public d(Throwable th) {
        super(th);
    }

    public String a() {
        return HTTP.NT;
    }
}
