package com.bumptech.glide.load.engine;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;

interface DataFetcherGenerator {

    public interface FetcherReadyCallback {
        void a(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource);

        void a(Key key, @Nullable Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2);

        void c();
    }

    boolean a();

    void b();
}
