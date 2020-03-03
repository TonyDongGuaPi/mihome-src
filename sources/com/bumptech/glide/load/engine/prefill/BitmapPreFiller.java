package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.util.Util;
import java.util.HashMap;

public final class BitmapPreFiller {

    /* renamed from: a  reason: collision with root package name */
    private final MemoryCache f4928a;
    private final BitmapPool b;
    private final DecodeFormat c;
    private final Handler d = new Handler(Looper.getMainLooper());
    private BitmapPreFillRunner e;

    public BitmapPreFiller(MemoryCache memoryCache, BitmapPool bitmapPool, DecodeFormat decodeFormat) {
        this.f4928a = memoryCache;
        this.b = bitmapPool;
        this.c = decodeFormat;
    }

    public void a(PreFillType.Builder... builderArr) {
        if (this.e != null) {
            this.e.a();
        }
        PreFillType[] preFillTypeArr = new PreFillType[builderArr.length];
        for (int i = 0; i < builderArr.length; i++) {
            PreFillType.Builder builder = builderArr[i];
            if (builder.a() == null) {
                builder.a(this.c == DecodeFormat.PREFER_ARGB_8888 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            }
            preFillTypeArr[i] = builder.b();
        }
        this.e = new BitmapPreFillRunner(this.b, this.f4928a, a(preFillTypeArr));
        this.d.post(this.e);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public PreFillQueue a(PreFillType... preFillTypeArr) {
        long b2 = (this.f4928a.b() - this.f4928a.a()) + this.b.a();
        int i = 0;
        for (PreFillType d2 : preFillTypeArr) {
            i += d2.d();
        }
        float f = ((float) b2) / ((float) i);
        HashMap hashMap = new HashMap();
        for (PreFillType preFillType : preFillTypeArr) {
            hashMap.put(preFillType, Integer.valueOf(Math.round(((float) preFillType.d()) * f) / a(preFillType)));
        }
        return new PreFillQueue(hashMap);
    }

    private static int a(PreFillType preFillType) {
        return Util.a(preFillType.a(), preFillType.b(), preFillType.c());
    }
}
