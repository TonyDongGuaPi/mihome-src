package com.lidroid.xutils.bitmap.callback;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

public abstract class BitmapLoadCallBack<T extends View> {

    /* renamed from: a  reason: collision with root package name */
    private BitmapSetter<T> f6295a;

    public abstract void a(T t, String str, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom);

    public abstract void a(T t, String str, Drawable drawable);

    public void a(T t, String str, BitmapDisplayConfig bitmapDisplayConfig) {
    }

    public void a(T t, String str, BitmapDisplayConfig bitmapDisplayConfig, long j, long j2) {
    }

    public void b(T t, String str, BitmapDisplayConfig bitmapDisplayConfig) {
    }

    public void a(BitmapSetter<T> bitmapSetter) {
        this.f6295a = bitmapSetter;
    }

    public void a(T t, Bitmap bitmap) {
        if (this.f6295a != null) {
            this.f6295a.a(t, bitmap);
        } else if (t instanceof ImageView) {
            ((ImageView) t).setImageBitmap(bitmap);
        } else {
            t.setBackgroundDrawable(new BitmapDrawable(t.getResources(), bitmap));
        }
    }

    public void a(T t, Drawable drawable) {
        if (this.f6295a != null) {
            this.f6295a.a(t, drawable);
        } else if (t instanceof ImageView) {
            ((ImageView) t).setImageDrawable(drawable);
        } else {
            t.setBackgroundDrawable(drawable);
        }
    }

    public Drawable a(T t) {
        if (this.f6295a != null) {
            return this.f6295a.a(t);
        }
        if (t instanceof ImageView) {
            return ((ImageView) t).getDrawable();
        }
        return t.getBackground();
    }
}
