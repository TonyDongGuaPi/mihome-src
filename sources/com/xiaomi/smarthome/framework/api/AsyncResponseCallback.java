package com.xiaomi.smarthome.framework.api;

public abstract class AsyncResponseCallback<T> {
    public abstract void a(int i);

    public abstract void a(int i, Object obj);

    public abstract void a(T t);
}