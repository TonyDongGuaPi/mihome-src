package org.xutils.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

final class ReusableBitmapDrawable extends BitmapDrawable implements ReusableDrawable {

    /* renamed from: a  reason: collision with root package name */
    private MemCacheKey f11940a;

    public ReusableBitmapDrawable(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    public MemCacheKey a() {
        return this.f11940a;
    }

    public void a(MemCacheKey memCacheKey) {
        this.f11940a = memCacheKey;
    }
}
