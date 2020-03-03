package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;

class DataCacheWriter<DataType> implements DiskCache.Writer {

    /* renamed from: a  reason: collision with root package name */
    private final Encoder<DataType> f4857a;
    private final DataType b;
    private final Options c;

    DataCacheWriter(Encoder<DataType> encoder, DataType datatype, Options options) {
        this.f4857a = encoder;
        this.b = datatype;
        this.c = options;
    }

    public boolean a(@NonNull File file) {
        return this.f4857a.a(this.b, file, this.c);
    }
}
