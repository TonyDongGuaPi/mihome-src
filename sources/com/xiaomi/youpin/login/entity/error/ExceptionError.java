package com.xiaomi.youpin.login.entity.error;

import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.other.log.LogUtils;

public class ExceptionError extends Error {

    /* renamed from: a  reason: collision with root package name */
    public Exception f23518a;

    public ExceptionError(int i, String str) {
        super(i, str);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this.f23518a == null) {
            str = "";
        } else {
            str = "Exception " + this.f23518a.getMessage();
        }
        sb.append(str);
        return sb.toString();
    }

    public String c() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this.f23518a == null) {
            str = "";
        } else {
            str = "Exception " + this.f23518a.getMessage() + " stackTrace " + LogUtils.b(this.f23518a);
        }
        sb.append(str);
        return sb.toString();
    }
}
