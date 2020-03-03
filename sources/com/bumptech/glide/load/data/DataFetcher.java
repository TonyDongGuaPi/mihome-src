package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;

public interface DataFetcher<T> {

    public interface DataCallback<T> {
        void a(@NonNull Exception exc);

        void a(@Nullable T t);
    }

    @NonNull
    Class<T> a();

    void a(@NonNull Priority priority, @NonNull DataCallback<? super T> dataCallback);

    void b();

    void c();

    @NonNull
    DataSource d();
}
