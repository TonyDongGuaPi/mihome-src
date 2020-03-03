package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;

public interface ResourceDecoder<T, Z> {
    @Nullable
    Resource<Z> a(@NonNull T t, int i, int i2, @NonNull Options options) throws IOException;

    boolean a(@NonNull T t, @NonNull Options options) throws IOException;
}
