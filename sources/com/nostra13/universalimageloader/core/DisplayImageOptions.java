package com.nostra13.universalimageloader.core;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

public final class DisplayImageOptions {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final int f8456a;
    /* access modifiers changed from: private */
    public final int b;
    /* access modifiers changed from: private */
    public final int c;
    /* access modifiers changed from: private */
    public final Drawable d;
    /* access modifiers changed from: private */
    public final Drawable e;
    /* access modifiers changed from: private */
    public final Drawable f;
    /* access modifiers changed from: private */
    public final boolean g;
    /* access modifiers changed from: private */
    public final boolean h;
    /* access modifiers changed from: private */
    public final boolean i;
    /* access modifiers changed from: private */
    public final ImageScaleType j;
    /* access modifiers changed from: private */
    public final BitmapFactory.Options k;
    /* access modifiers changed from: private */
    public final int l;
    /* access modifiers changed from: private */
    public final boolean m;
    /* access modifiers changed from: private */
    public final Object n;
    /* access modifiers changed from: private */
    public final BitmapProcessor o;
    /* access modifiers changed from: private */
    public final BitmapProcessor p;
    /* access modifiers changed from: private */
    public final BitmapDisplayer q;
    /* access modifiers changed from: private */
    public final Handler r;
    /* access modifiers changed from: private */
    public final boolean s;

    private DisplayImageOptions(Builder builder) {
        this.f8456a = builder.f8457a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
        this.l = builder.l;
        this.m = builder.m;
        this.n = builder.n;
        this.o = builder.o;
        this.p = builder.p;
        this.q = builder.q;
        this.r = builder.r;
        this.s = builder.s;
    }

    public boolean a() {
        return (this.d == null && this.f8456a == 0) ? false : true;
    }

    public boolean b() {
        return (this.e == null && this.b == 0) ? false : true;
    }

    public boolean c() {
        return (this.f == null && this.c == 0) ? false : true;
    }

    public boolean d() {
        return this.o != null;
    }

    public boolean e() {
        return this.p != null;
    }

    public boolean f() {
        return this.l > 0;
    }

    public Drawable a(Resources resources) {
        return this.f8456a != 0 ? resources.getDrawable(this.f8456a) : this.d;
    }

    public Drawable b(Resources resources) {
        return this.b != 0 ? resources.getDrawable(this.b) : this.e;
    }

    public Drawable c(Resources resources) {
        return this.c != 0 ? resources.getDrawable(this.c) : this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public boolean i() {
        return this.i;
    }

    public ImageScaleType j() {
        return this.j;
    }

    public BitmapFactory.Options k() {
        return this.k;
    }

    public int l() {
        return this.l;
    }

    public boolean m() {
        return this.m;
    }

    public Object n() {
        return this.n;
    }

    public BitmapProcessor o() {
        return this.o;
    }

    public BitmapProcessor p() {
        return this.p;
    }

    public BitmapDisplayer q() {
        return this.q;
    }

    public Handler r() {
        return this.r;
    }

    /* access modifiers changed from: package-private */
    public boolean s() {
        return this.s;
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f8457a = 0;
        /* access modifiers changed from: private */
        public int b = 0;
        /* access modifiers changed from: private */
        public int c = 0;
        /* access modifiers changed from: private */
        public Drawable d = null;
        /* access modifiers changed from: private */
        public Drawable e = null;
        /* access modifiers changed from: private */
        public Drawable f = null;
        /* access modifiers changed from: private */
        public boolean g = false;
        /* access modifiers changed from: private */
        public boolean h = false;
        /* access modifiers changed from: private */
        public boolean i = false;
        /* access modifiers changed from: private */
        public ImageScaleType j = ImageScaleType.IN_SAMPLE_POWER_OF_2;
        /* access modifiers changed from: private */
        public BitmapFactory.Options k = new BitmapFactory.Options();
        /* access modifiers changed from: private */
        public int l = 0;
        /* access modifiers changed from: private */
        public boolean m = false;
        /* access modifiers changed from: private */
        public Object n = null;
        /* access modifiers changed from: private */
        public BitmapProcessor o = null;
        /* access modifiers changed from: private */
        public BitmapProcessor p = null;
        /* access modifiers changed from: private */
        public BitmapDisplayer q = DefaultConfigurationFactory.c();
        /* access modifiers changed from: private */
        public Handler r = null;
        /* access modifiers changed from: private */
        public boolean s = false;

        @Deprecated
        public Builder a(int i2) {
            this.f8457a = i2;
            return this;
        }

        public Builder b(int i2) {
            this.f8457a = i2;
            return this;
        }

        public Builder a(Drawable drawable) {
            this.d = drawable;
            return this;
        }

        public Builder c(int i2) {
            this.b = i2;
            return this;
        }

        public Builder b(Drawable drawable) {
            this.e = drawable;
            return this;
        }

        public Builder d(int i2) {
            this.c = i2;
            return this;
        }

        public Builder c(Drawable drawable) {
            this.f = drawable;
            return this;
        }

        public Builder a() {
            this.g = true;
            return this;
        }

        public Builder a(boolean z) {
            this.g = z;
            return this;
        }

        @Deprecated
        public Builder b() {
            this.h = true;
            return this;
        }

        public Builder b(boolean z) {
            this.h = z;
            return this;
        }

        @Deprecated
        public Builder c() {
            return d(true);
        }

        @Deprecated
        public Builder c(boolean z) {
            return d(z);
        }

        public Builder d(boolean z) {
            this.i = z;
            return this;
        }

        public Builder a(ImageScaleType imageScaleType) {
            this.j = imageScaleType;
            return this;
        }

        public Builder a(Bitmap.Config config) {
            if (config != null) {
                this.k.inPreferredConfig = config;
                return this;
            }
            throw new IllegalArgumentException("bitmapConfig can't be null");
        }

        public Builder a(BitmapFactory.Options options) {
            if (options != null) {
                this.k = options;
                return this;
            }
            throw new IllegalArgumentException("decodingOptions can't be null");
        }

        public Builder e(int i2) {
            this.l = i2;
            return this;
        }

        public Builder a(Object obj) {
            this.n = obj;
            return this;
        }

        public Builder e(boolean z) {
            this.m = z;
            return this;
        }

        public Builder a(BitmapProcessor bitmapProcessor) {
            this.o = bitmapProcessor;
            return this;
        }

        public Builder b(BitmapProcessor bitmapProcessor) {
            this.p = bitmapProcessor;
            return this;
        }

        public Builder a(BitmapDisplayer bitmapDisplayer) {
            if (bitmapDisplayer != null) {
                this.q = bitmapDisplayer;
                return this;
            }
            throw new IllegalArgumentException("displayer can't be null");
        }

        /* access modifiers changed from: package-private */
        public Builder f(boolean z) {
            this.s = z;
            return this;
        }

        public Builder a(Handler handler) {
            this.r = handler;
            return this;
        }

        public Builder a(DisplayImageOptions displayImageOptions) {
            this.f8457a = displayImageOptions.f8456a;
            this.b = displayImageOptions.b;
            this.c = displayImageOptions.c;
            this.d = displayImageOptions.d;
            this.e = displayImageOptions.e;
            this.f = displayImageOptions.f;
            this.g = displayImageOptions.g;
            this.h = displayImageOptions.h;
            this.i = displayImageOptions.i;
            this.j = displayImageOptions.j;
            this.k = displayImageOptions.k;
            this.l = displayImageOptions.l;
            this.m = displayImageOptions.m;
            this.n = displayImageOptions.n;
            this.o = displayImageOptions.o;
            this.p = displayImageOptions.p;
            this.q = displayImageOptions.q;
            this.r = displayImageOptions.r;
            this.s = displayImageOptions.s;
            return this;
        }

        public DisplayImageOptions d() {
            return new DisplayImageOptions(this);
        }
    }

    public static DisplayImageOptions t() {
        return new Builder().d();
    }
}
