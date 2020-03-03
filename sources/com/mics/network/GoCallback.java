package com.mics.network;

public interface GoCallback<T> {
    void a(String str, GoFailure goFailure);

    void a(String str, T t);
}
