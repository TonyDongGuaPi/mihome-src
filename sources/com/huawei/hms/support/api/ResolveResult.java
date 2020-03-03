package com.huawei.hms.support.api;

import com.huawei.hms.support.api.client.Result;

public class ResolveResult<T> extends Result {

    /* renamed from: a  reason: collision with root package name */
    private T f5873a;

    public ResolveResult() {
        this.f5873a = null;
    }

    public ResolveResult(T t) {
        this.f5873a = t;
    }

    public T getValue() {
        return this.f5873a;
    }
}
