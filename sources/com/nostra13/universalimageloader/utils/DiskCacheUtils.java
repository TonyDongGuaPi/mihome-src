package com.nostra13.universalimageloader.utils;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import java.io.File;

public final class DiskCacheUtils {
    private DiskCacheUtils() {
    }

    public static File a(String str, DiskCache diskCache) {
        File a2 = diskCache.a(str);
        if (a2 == null || !a2.exists()) {
            return null;
        }
        return a2;
    }

    public static boolean b(String str, DiskCache diskCache) {
        File a2 = diskCache.a(str);
        return a2 != null && a2.exists() && a2.delete();
    }
}
