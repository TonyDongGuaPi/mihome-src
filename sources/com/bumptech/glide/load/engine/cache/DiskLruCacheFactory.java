package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;

public class DiskLruCacheFactory implements DiskCache.Factory {
    private final long c;
    private final CacheDirectoryGetter d;

    public interface CacheDirectoryGetter {
        File a();
    }

    public DiskLruCacheFactory(final String str, long j) {
        this((CacheDirectoryGetter) new CacheDirectoryGetter() {
            public File a() {
                return new File(str);
            }
        }, j);
    }

    public DiskLruCacheFactory(final String str, final String str2, long j) {
        this((CacheDirectoryGetter) new CacheDirectoryGetter() {
            public File a() {
                return new File(str, str2);
            }
        }, j);
    }

    public DiskLruCacheFactory(CacheDirectoryGetter cacheDirectoryGetter, long j) {
        this.c = j;
        this.d = cacheDirectoryGetter;
    }

    public DiskCache a() {
        File a2 = this.d.a();
        if (a2 == null) {
            return null;
        }
        if (a2.mkdirs() || (a2.exists() && a2.isDirectory())) {
            return DiskLruCacheWrapper.b(a2, this.c);
        }
        return null;
    }
}
