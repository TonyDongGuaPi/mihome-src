package com.xiaomi.mishopsdk.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.squareup.picasso.mishop.Cache;
import com.squareup.picasso.mishop.MishopLruCache;
import com.squareup.picasso.mishop.Picasso;
import com.squareup.picasso.mishop.RequestCreator;
import java.io.File;

public class PicUtil {
    private static final int MIN_CACHE_MEMORY_SIZE = 16777216;
    private static int sCacheMemorySize;
    private Cache mHomeMemoryCache;
    private Picasso mPicasso;

    private PicUtil() {
        MishopLruCache.setMaxSizeBeforCreate(getCacheMemorySize());
        this.mHomeMemoryCache = MishopLruCache.getInstance();
    }

    @Deprecated
    public static void setCacheMemorySize(int i) {
        sCacheMemorySize = i;
    }

    private static int getCacheMemorySize() {
        if (sCacheMemorySize < 16777216) {
            double maxMemory = (double) Runtime.getRuntime().maxMemory();
            Double.isNaN(maxMemory);
            sCacheMemorySize = (int) (maxMemory / 3.5d);
        }
        return sCacheMemorySize;
    }

    public void clearMemoryCache() {
        if (this.mHomeMemoryCache != null) {
            this.mHomeMemoryCache.clear();
        }
    }

    public Picasso getPicasso() {
        return this.mPicasso;
    }

    public RequestCreator load(Uri uri) {
        return this.mPicasso.load(uri);
    }

    public RequestCreator load(String str) {
        Picasso picasso = this.mPicasso;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        return picasso.load(str);
    }

    public RequestCreator load(File file) {
        return this.mPicasso.load(file);
    }

    public RequestCreator load(int i) {
        return this.mPicasso.load(i);
    }

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static PicUtil INSTANCE = new PicUtil();

        private SingletonHolder() {
        }
    }

    public static PicUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(Context context) {
        this.mPicasso = new Picasso.Builder(context).downloader(new VolleyDownloader(context)).memoryCache(this.mHomeMemoryCache).build();
    }

    public int getCacheSize() {
        return ScreenInfo.getInstance().getScreenWidth() * ScreenInfo.getInstance().getScreenHigth() * 4 * 6;
    }
}
