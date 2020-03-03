package com.xiaomi.youpin.common.base;

import com.xiaomi.youpin.log.LogUtils;

public class ExceptionError extends YouPinError {

    /* renamed from: a  reason: collision with root package name */
    public Exception f23224a;

    public ExceptionError(int i, String str) {
        super(i, str);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this.f23224a == null) {
            str = "";
        } else {
            str = "Exception " + this.f23224a.getMessage();
        }
        sb.append(str);
        return sb.toString();
    }

    public String a() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this.f23224a == null) {
            str = "";
        } else {
            str = "Exception " + this.f23224a.getMessage() + " stackTrace " + LogUtils.getStackTraceString(this.f23224a);
        }
        sb.append(str);
        return sb.toString();
    }
}
