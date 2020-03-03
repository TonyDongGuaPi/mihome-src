package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import java.io.File;

public interface Encoder<T> {
    boolean a(@NonNull T t, @NonNull File file, @NonNull Options options);
}
