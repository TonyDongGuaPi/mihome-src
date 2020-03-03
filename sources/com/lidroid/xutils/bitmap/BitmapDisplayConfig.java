package com.lidroid.xutils.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.task.Priority;

public class BitmapDisplayConfig {

    /* renamed from: a  reason: collision with root package name */
    private BitmapSize f6291a;
    private Animation b;
    private Drawable c;
    private Drawable d;
    private boolean e = false;
    private boolean f = false;
    private Bitmap.Config g = Bitmap.Config.RGB_565;
    private BitmapFactory h;
    private Priority i;

    public BitmapSize a() {
        return this.f6291a == null ? BitmapSize.f6302a : this.f6291a;
    }

    public void a(BitmapSize bitmapSize) {
        this.f6291a = bitmapSize;
    }

    public Animation b() {
        return this.b;
    }

    public void a(Animation animation) {
        this.b = animation;
    }

    public Drawable c() {
        return this.c;
    }

    public void a(Drawable drawable) {
        this.c = drawable;
    }

    public Drawable d() {
        return this.d;
    }

    public void b(Drawable drawable) {
        this.d = drawable;
    }

    public boolean e() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean f() {
        return this.f;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public Bitmap.Config g() {
        return this.g;
    }

    public void a(Bitmap.Config config) {
        this.g = config;
    }

    public BitmapFactory h() {
        return this.h;
    }

    public void a(BitmapFactory bitmapFactory) {
        this.h = bitmapFactory;
    }

    public Priority i() {
        return this.i;
    }

    public void a(Priority priority) {
        this.i = priority;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(String.valueOf(f() ? "" : this.f6291a.toString()));
        sb.append(this.h == null ? "" : this.h.getClass().getName());
        return sb.toString();
    }

    public BitmapDisplayConfig j() {
        BitmapDisplayConfig bitmapDisplayConfig = new BitmapDisplayConfig();
        bitmapDisplayConfig.f6291a = this.f6291a;
        bitmapDisplayConfig.b = this.b;
        bitmapDisplayConfig.c = this.c;
        bitmapDisplayConfig.d = this.d;
        bitmapDisplayConfig.e = this.e;
        bitmapDisplayConfig.f = this.f;
        bitmapDisplayConfig.g = this.g;
        bitmapDisplayConfig.h = this.h;
        bitmapDisplayConfig.i = this.i;
        return bitmapDisplayConfig;
    }
}
