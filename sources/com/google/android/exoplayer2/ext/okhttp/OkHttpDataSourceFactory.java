package com.google.android.exoplayer2.ext.okhttp;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Predicate;
import okhttp3.CacheControl;
import okhttp3.Call;

public final class OkHttpDataSourceFactory extends HttpDataSource.BaseFactory {
    @Nullable
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    @Nullable
    private final TransferListener listener;
    @Nullable
    private final String userAgent;

    public OkHttpDataSourceFactory(Call.Factory factory, @Nullable String str) {
        this(factory, str, (TransferListener) null, (CacheControl) null);
    }

    public OkHttpDataSourceFactory(Call.Factory factory, @Nullable String str, @Nullable CacheControl cacheControl2) {
        this(factory, str, (TransferListener) null, cacheControl2);
    }

    public OkHttpDataSourceFactory(Call.Factory factory, @Nullable String str, @Nullable TransferListener transferListener) {
        this(factory, str, transferListener, (CacheControl) null);
    }

    public OkHttpDataSourceFactory(Call.Factory factory, @Nullable String str, @Nullable TransferListener transferListener, @Nullable CacheControl cacheControl2) {
        this.callFactory = factory;
        this.userAgent = str;
        this.listener = transferListener;
        this.cacheControl = cacheControl2;
    }

    /* access modifiers changed from: protected */
    public OkHttpDataSource createDataSourceInternal(HttpDataSource.RequestProperties requestProperties) {
        OkHttpDataSource okHttpDataSource = new OkHttpDataSource(this.callFactory, this.userAgent, (Predicate<String>) null, this.cacheControl, requestProperties);
        if (this.listener != null) {
            okHttpDataSource.addTransferListener(this.listener);
        }
        return okHttpDataSource;
    }
}
