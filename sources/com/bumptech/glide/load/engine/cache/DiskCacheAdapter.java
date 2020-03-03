package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;

public class DiskCacheAdapter implements DiskCache {
    public File a(Key key) {
        return null;
    }

    public void a() {
    }

    public void a(Key key, DiskCache.Writer writer) {
    }

    public void b(Key key) {
    }

    public static final class Factory implements DiskCache.Factory {
        public DiskCache a() {
            return new DiskCacheAdapter();
        }
    }
}
