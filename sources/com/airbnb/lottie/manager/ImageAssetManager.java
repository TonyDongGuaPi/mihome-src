package com.airbnb.lottie.manager;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieImageAsset;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageAssetManager {
    private static final Object bitmapHashLock = new Object();
    private final Context context;
    @Nullable
    private ImageAssetDelegate delegate;
    private final Map<String, LottieImageAsset> imageAssets;
    private String imagesFolder;

    public ImageAssetManager(Drawable.Callback callback, String str, ImageAssetDelegate imageAssetDelegate, Map<String, LottieImageAsset> map) {
        this.imagesFolder = str;
        if (!TextUtils.isEmpty(str) && this.imagesFolder.charAt(this.imagesFolder.length() - 1) != '/') {
            this.imagesFolder += IOUtils.f15883a;
        }
        if (!(callback instanceof View)) {
            Log.w(L.TAG, "LottieDrawable must be inside of a view for images to work.");
            this.imageAssets = new HashMap();
            this.context = null;
            return;
        }
        this.context = ((View) callback).getContext();
        this.imageAssets = map;
        setDelegate(imageAssetDelegate);
    }

    public void setDelegate(@Nullable ImageAssetDelegate imageAssetDelegate) {
        this.delegate = imageAssetDelegate;
    }

    @Nullable
    public Bitmap updateBitmap(String str, @Nullable Bitmap bitmap) {
        if (bitmap != null) {
            return putBitmap(str, bitmap);
        }
        LottieImageAsset lottieImageAsset = this.imageAssets.get(str);
        Bitmap bitmap2 = lottieImageAsset.getBitmap();
        lottieImageAsset.setBitmap((Bitmap) null);
        return bitmap2;
    }

    @Nullable
    public Bitmap bitmapForId(String str) {
        LottieImageAsset lottieImageAsset = this.imageAssets.get(str);
        if (lottieImageAsset == null) {
            return null;
        }
        Bitmap bitmap = lottieImageAsset.getBitmap();
        if (bitmap != null) {
            return bitmap;
        }
        if (this.delegate != null) {
            Bitmap fetchBitmap = this.delegate.fetchBitmap(lottieImageAsset);
            if (fetchBitmap != null) {
                putBitmap(str, fetchBitmap);
            }
            return fetchBitmap;
        }
        String fileName = lottieImageAsset.getFileName();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        if (!fileName.startsWith("data:") || fileName.indexOf("base64,") <= 0) {
            try {
                if (!TextUtils.isEmpty(this.imagesFolder)) {
                    AssetManager assets = this.context.getAssets();
                    return putBitmap(str, BitmapFactory.decodeStream(assets.open(this.imagesFolder + fileName), (Rect) null, options));
                }
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            } catch (IOException e) {
                Log.w(L.TAG, "Unable to open asset.", e);
                return null;
            }
        } else {
            try {
                byte[] decode = Base64.decode(fileName.substring(fileName.indexOf(44) + 1), 0);
                return putBitmap(str, BitmapFactory.decodeByteArray(decode, 0, decode.length, options));
            } catch (IllegalArgumentException e2) {
                Log.w(L.TAG, "data URL did not have correct base64 format.", e2);
                return null;
            }
        }
    }

    public void recycleBitmaps() {
        synchronized (bitmapHashLock) {
            for (Map.Entry<String, LottieImageAsset> value : this.imageAssets.entrySet()) {
                LottieImageAsset lottieImageAsset = (LottieImageAsset) value.getValue();
                Bitmap bitmap = lottieImageAsset.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                    lottieImageAsset.setBitmap((Bitmap) null);
                }
            }
        }
    }

    public boolean hasSameContext(Context context2) {
        return (context2 == null && this.context == null) || this.context.equals(context2);
    }

    private Bitmap putBitmap(String str, @Nullable Bitmap bitmap) {
        synchronized (bitmapHashLock) {
            this.imageAssets.get(str).setBitmap(bitmap);
        }
        return bitmap;
    }
}
