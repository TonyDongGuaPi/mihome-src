package com.nostra13.universalimageloader.cache.memory;

import android.graphics.Bitmap;
import java.util.Collection;

public interface MemoryCache {
    Bitmap a(String str);

    Collection<String> a();

    boolean a(String str, Bitmap bitmap);

    Bitmap b(String str);

    void b();
}
