package com.mics.exception;

public class InitializationException extends RuntimeException {
    public InitializationException() {
    }

    public InitializationException(String str) {
        super(str);
    }

    public InitializationException(String str, Throwable th) {
        super(str, th);
    }

    public InitializationException(Throwable th) {
        super(th);
    }
}
