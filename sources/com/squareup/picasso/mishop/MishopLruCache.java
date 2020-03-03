package com.squareup.picasso.mishop;

import android.graphics.Bitmap;
import android.os.Build;
import java.util.Iterator;
import java.util.Map;

public class MishopLruCache extends MishopBaseLruCache<String, Bitmap> implements Cache {
    private static MishopLruCache sInstance = null;
    private static int sLruCacheMaxSize = 31457280;

    public /* bridge */ /* synthetic */ Bitmap get(String str) {
        return (Bitmap) super.get(str);
    }

    public static synchronized void setMaxSizeBeforCreate(int i) {
        synchronized (MishopLruCache.class) {
            sLruCacheMaxSize = i;
        }
    }

    public static synchronized MishopLruCache getInstance() {
        MishopLruCache mishopLruCache;
        synchronized (MishopLruCache.class) {
            if (sInstance == null) {
                sInstance = new MishopLruCache(sLruCacheMaxSize);
            }
            mishopLruCache = sInstance;
        }
        return mishopLruCache;
    }

    protected MishopLruCache(int i) {
        super(i);
    }

    public int getItemCount() {
        return this.map.size();
    }

    /* access modifiers changed from: protected */
    public int sizeOf(String str, Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public void set(String str, Bitmap bitmap) {
        super.put(str, bitmap);
    }

    public void clear() {
        super.evictAll();
    }

    public void clearKeyUri(String str) {
        int length = str.length();
        Iterator it = this.map.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str2 = (String) entry.getKey();
            Bitmap bitmap = (Bitmap) entry.getValue();
            int indexOf = str2.indexOf(10);
            if (indexOf == length && str2.substring(0, indexOf).equals(str)) {
                it.remove();
                this.size -= safeSizeOf(str2, bitmap);
                z = true;
            }
        }
        if (z) {
            trimToSize(this.maxSize);
        }
    }

    public final boolean contains(String str) {
        boolean containsKey;
        if (str == null) {
            return false;
        }
        synchronized (this) {
            containsKey = this.map.containsKey(str);
        }
        return containsKey;
    }

    public final int getCurrentSize() {
        int i;
        synchronized (this) {
            i = this.size;
        }
        return i;
    }

    public final int getMaxSize() {
        int i;
        synchronized (this) {
            i = this.maxSize;
        }
        return i;
    }
}
