package com.mics.network;

public abstract class GoInterceptor {
    /* access modifiers changed from: protected */
    public boolean a(String str, Object obj) {
        return obj instanceof GoFailure;
    }

    /* access modifiers changed from: protected */
    public GoFailure b(String str, Object obj) {
        if (obj instanceof GoFailure) {
            return (GoFailure) obj;
        }
        return new GoFailure();
    }
}
