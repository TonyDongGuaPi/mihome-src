package com.trello.rxlifecycle2.internal;

public final class Preconditions {
    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}