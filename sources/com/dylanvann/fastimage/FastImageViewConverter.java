package com.dylanvann.fastimage;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.xiaomi.smarthome.download.Downloads;
import java.util.HashMap;
import java.util.Map;

class FastImageViewConverter {

    /* renamed from: a  reason: collision with root package name */
    private static final Drawable f5287a = new ColorDrawable(0);
    private static final Map<String, FastImageCacheControl> b = new HashMap<String, FastImageCacheControl>() {
        {
            put("immutable", FastImageCacheControl.IMMUTABLE);
            put("web", FastImageCacheControl.WEB);
            put("cacheOnly", FastImageCacheControl.CACHE_ONLY);
        }
    };
    private static final Map<String, Priority> c = new HashMap<String, Priority>() {
        {
            put("low", Priority.LOW);
            put("normal", Priority.NORMAL);
            put("high", Priority.HIGH);
        }
    };
    private static final Map<String, ImageView.ScaleType> d = new HashMap<String, ImageView.ScaleType>() {
        {
            put("contain", ImageView.ScaleType.FIT_CENTER);
            put(PlaceFields.COVER, ImageView.ScaleType.CENTER_CROP);
            put("stretch", ImageView.ScaleType.FIT_XY);
            put("center", ImageView.ScaleType.CENTER);
        }
    };

    FastImageViewConverter() {
    }

    static FastImageSource a(Context context, ReadableMap readableMap) {
        return new FastImageSource(context, readableMap.getString("uri"), a(readableMap));
    }

    static Headers a(ReadableMap readableMap) {
        Headers headers = Headers.b;
        if (!readableMap.hasKey(Downloads.RequestHeaders.e)) {
            return headers;
        }
        ReadableMap map = readableMap.getMap(Downloads.RequestHeaders.e);
        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
        LazyHeaders.Builder builder = new LazyHeaders.Builder();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            builder.a(nextKey, map.getString(nextKey));
        }
        return builder.a();
    }

    static RequestOptions b(ReadableMap readableMap) {
        Priority d2 = d(readableMap);
        FastImageCacheControl c2 = c(readableMap);
        DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.e;
        Boolean bool = false;
        Boolean bool2 = false;
        switch (c2) {
            case WEB:
                diskCacheStrategy = DiskCacheStrategy.b;
                bool2 = true;
                break;
            case CACHE_ONLY:
                bool = true;
                break;
        }
        return (RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().a(diskCacheStrategy)).c(bool.booleanValue())).d(bool2.booleanValue())).a(d2)).c(f5287a);
    }

    private static FastImageCacheControl c(ReadableMap readableMap) {
        return (FastImageCacheControl) a("cache", "immutable", b, readableMap);
    }

    private static Priority d(ReadableMap readableMap) {
        return (Priority) a("priority", "normal", c, readableMap);
    }

    static ImageView.ScaleType a(String str) {
        return (ImageView.ScaleType) a("resizeMode", PlaceFields.COVER, d, str);
    }

    private static <T> T a(String str, String str2, Map<String, T> map, String str3) {
        if (str3 != null) {
            str2 = str3;
        }
        T t = map.get(str2);
        if (t != null) {
            return t;
        }
        throw new JSApplicationIllegalArgumentException("FastImage, invalid " + str + " : " + str2);
    }

    private static <T> T a(String str, String str2, Map<String, T> map, ReadableMap readableMap) {
        String str3 = null;
        if (readableMap != null) {
            try {
                str3 = readableMap.getString(str);
            } catch (NoSuchKeyException unused) {
            }
        }
        return a(str, str2, map, str3);
    }
}
