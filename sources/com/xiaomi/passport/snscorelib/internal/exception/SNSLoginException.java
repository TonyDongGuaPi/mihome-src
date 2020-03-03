package com.xiaomi.passport.snscorelib.internal.exception;

public class SNSLoginException extends Exception {
    private final int code;

    public SNSLoginException(int i, String str) {
        super(str);
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
