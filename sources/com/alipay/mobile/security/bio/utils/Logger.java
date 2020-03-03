package com.alipay.mobile.security.bio.utils;

public abstract class Logger {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    /* renamed from: a  reason: collision with root package name */
    private int f1025a = 2;

    /* access modifiers changed from: protected */
    public abstract String a(Throwable th);

    public abstract int debug(String str, String str2);

    public abstract int error(String str, String str2);

    public abstract int info(String str, String str2);

    public abstract int verbose(String str, String str2);

    public abstract int warn(String str, String str2);

    public void setLogLevel(int i) {
        if (i < 2 || i > 7) {
            throw new RuntimeException("The level value should between [2 , 7]");
        }
        this.f1025a = i;
    }

    public int v(String str, String str2) {
        if (this.f1025a <= 2) {
            return verbose(str, str2);
        }
        return -1;
    }

    public int v(String str, String str2, Throwable th) {
        if (this.f1025a > 2) {
            return -1;
        }
        return verbose(str, str2 + 10 + a(th));
    }

    public int v(String str, Throwable th) {
        if (this.f1025a <= 2) {
            return verbose(str, a(th));
        }
        return -1;
    }

    public int d(String str, String str2) {
        if (this.f1025a <= 3) {
            return debug(str, str2);
        }
        return -1;
    }

    public int d(String str, String str2, Throwable th) {
        if (this.f1025a > 3) {
            return -1;
        }
        return debug(str, str2 + 10 + a(th));
    }

    public int d(String str, Throwable th) {
        if (this.f1025a <= 3) {
            return debug(str, a(th));
        }
        return -1;
    }

    public int i(String str, String str2) {
        if (this.f1025a <= 4) {
            return info(str, str2);
        }
        return -1;
    }

    public int i(String str, String str2, Throwable th) {
        if (this.f1025a > 4) {
            return -1;
        }
        return info(str, str2 + 10 + a(th));
    }

    public int i(String str, Throwable th) {
        if (this.f1025a <= 4) {
            return info(str, a(th));
        }
        return -1;
    }

    public int w(String str, String str2) {
        if (this.f1025a <= 5) {
            return warn(str, str2);
        }
        return -1;
    }

    public int w(String str, String str2, Throwable th) {
        if (this.f1025a > 5) {
            return -1;
        }
        return warn(str, str2 + 10 + a(th));
    }

    public int w(String str, Throwable th) {
        if (this.f1025a <= 5) {
            return warn(str, a(th));
        }
        return -1;
    }

    public int e(String str, String str2) {
        if (this.f1025a <= 6) {
            return error(str, str2);
        }
        return -1;
    }

    public int e(String str, String str2, Throwable th) {
        if (this.f1025a > 6) {
            return -1;
        }
        return error(str, str2 + 10 + a(th));
    }

    public int e(String str, Throwable th) {
        if (this.f1025a <= 6) {
            return error(str, a(th));
        }
        return -1;
    }
}
