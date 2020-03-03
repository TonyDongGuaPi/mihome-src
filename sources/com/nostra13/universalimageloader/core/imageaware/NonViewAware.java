package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;

public class NonViewAware implements ImageAware {

    /* renamed from: a  reason: collision with root package name */
    protected final String f8490a;
    protected final ImageSize b;
    protected final ViewScaleType c;

    public boolean a(Bitmap bitmap) {
        return true;
    }

    public boolean a(Drawable drawable) {
        return true;
    }

    public View d() {
        return null;
    }

    public boolean e() {
        return false;
    }

    public NonViewAware(ImageSize imageSize, ViewScaleType viewScaleType) {
        this((String) null, imageSize, viewScaleType);
    }

    public NonViewAware(String str, ImageSize imageSize, ViewScaleType viewScaleType) {
        if (imageSize == null) {
            throw new IllegalArgumentException("imageSize must not be null");
        } else if (viewScaleType != null) {
            this.f8490a = str;
            this.b = imageSize;
            this.c = viewScaleType;
        } else {
            throw new IllegalArgumentException("scaleType must not be null");
        }
    }

    public int a() {
        return this.b.a();
    }

    public int b() {
        return this.b.b();
    }

    public ViewScaleType c() {
        return this.c;
    }

    public int f() {
        return TextUtils.isEmpty(this.f8490a) ? super.hashCode() : this.f8490a.hashCode();
    }
}
