package com.facebook.react.bridge;

import android.support.annotation.Nullable;

public class JSApplicationCausedNativeException extends RuntimeException {
    public JSApplicationCausedNativeException(String str) {
        super(str);
    }

    public JSApplicationCausedNativeException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }
}
