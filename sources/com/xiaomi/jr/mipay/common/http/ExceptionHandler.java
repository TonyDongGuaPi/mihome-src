package com.xiaomi.jr.mipay.common.http;

public class ExceptionHandler {
    public static void a(int i, String str) {
        if (i >= 1000000 && i <= 1999999) {
            return;
        }
        if (i >= 2000000 && i <= 2999999) {
            return;
        }
        if (i >= 3000000 && i <= 3999999) {
            return;
        }
        if (i >= 100000000 && i <= 199999999) {
            return;
        }
        if (i >= 200000000 && i <= 299999999) {
        }
    }
}
