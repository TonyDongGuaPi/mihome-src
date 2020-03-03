package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;

public interface Resource<Z> {
    @NonNull
    Class<Z> c();

    @NonNull
    Z d();

    int e();

    void f();
}
