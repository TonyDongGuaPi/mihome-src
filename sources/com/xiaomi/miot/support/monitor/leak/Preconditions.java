package com.xiaomi.miot.support.monitor.leak;

final class Preconditions {
    static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str + " must not be null");
    }

    private Preconditions() {
        throw new AssertionError();
    }
}
