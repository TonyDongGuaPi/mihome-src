package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import java.io.IOException;

public interface DataRewinder<T> {

    public interface Factory<T> {
        @NonNull
        DataRewinder<T> a(@NonNull T t);

        @NonNull
        Class<T> a();
    }

    @NonNull
    T a() throws IOException;

    void b();
}
