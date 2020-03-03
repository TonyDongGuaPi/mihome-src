package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;

public class JsError {

    /* renamed from: a  reason: collision with root package name */
    private final IX5JsError f9075a;

    protected JsError(IX5JsError iX5JsError) {
        this.f9075a = iX5JsError;
    }

    public String getMessage() {
        return this.f9075a.getMessage();
    }

    public String getStack() {
        return this.f9075a.getStack();
    }
}
