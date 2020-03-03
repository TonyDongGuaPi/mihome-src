package com.mipay.common.exception;

import android.text.TextUtils;
import com.mipay.common.data.d;

public abstract class f extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8138a = "PaymentException";
    private static final String b = "|";
    private String c;

    public f() {
    }

    public f(String str) {
        super(str);
    }

    public f(String str, Throwable th) {
        super(str, th);
    }

    public f(Throwable th) {
        super(th);
    }

    public abstract String a();

    public String c() {
        if (TextUtils.isEmpty(this.c)) {
            String a2 = a();
            Throwable cause = getCause();
            if (cause != null && (cause instanceof f)) {
                a2 = (a2 + b) + ((f) cause).c();
            }
            this.c = a2;
        }
        return this.c;
    }

    public void d() {
        if (d.DEBUG) {
            printStackTrace();
        } else {
            e();
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        System.err.append(f());
    }

    /* access modifiers changed from: package-private */
    public String f() {
        StringBuilder sb = new StringBuilder();
        for (Throwable th = this; th != null; th = th.getCause()) {
            if (th != this) {
                sb.append("\tCaused by ");
            }
            sb.append(th.toString() + "\n");
        }
        return sb.toString();
    }

    public Throwable g() {
        Throwable th = null;
        for (Throwable th2 = this; th2 != null; th2 = th2.getCause()) {
            th = th2;
        }
        return th;
    }
}
