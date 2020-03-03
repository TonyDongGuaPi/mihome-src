package com.seek.biscuit;

public class CompressException extends Exception {
    public String originalPath = null;

    public CompressException(String str, String str2) {
        super(str);
        this.originalPath = str2;
    }

    public CompressException(String str, String str2, Throwable th) {
        super(str, th);
        this.originalPath = str2;
    }
}
