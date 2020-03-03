package com.bumptech.glide.load.engine.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import java.io.File;

public interface DiskCache {

    public interface Factory {

        /* renamed from: a  reason: collision with root package name */
        public static final int f4903a = 262144000;
        public static final String b = "image_manager_disk_cache";

        @Nullable
        DiskCache a();
    }

    public interface Writer {
        boolean a(@NonNull File file);
    }

    @Nullable
    File a(Key key);

    void a();

    void a(Key key, Writer writer);

    void b(Key key);
}
