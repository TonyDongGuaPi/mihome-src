package org.xutils.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.taobao.weex.annotation.JSMethod;
import java.lang.reflect.Field;
import org.xutils.common.util.DensityUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

public class ImageOptions {

    /* renamed from: a  reason: collision with root package name */
    public static final ImageOptions f11937a = new ImageOptions();
    private int b = 0;
    private int c = 0;
    /* access modifiers changed from: private */
    public int d = 0;
    /* access modifiers changed from: private */
    public int e = 0;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public int g = 0;
    /* access modifiers changed from: private */
    public boolean h = false;
    /* access modifiers changed from: private */
    public boolean i = false;
    /* access modifiers changed from: private */
    public boolean j = false;
    private boolean k = true;
    /* access modifiers changed from: private */
    public Bitmap.Config l = Bitmap.Config.RGB_565;
    /* access modifiers changed from: private */
    public boolean m = true;
    /* access modifiers changed from: private */
    public int n = 0;
    /* access modifiers changed from: private */
    public int o = 0;
    /* access modifiers changed from: private */
    public Drawable p = null;
    /* access modifiers changed from: private */
    public Drawable q = null;
    /* access modifiers changed from: private */
    public boolean r = true;
    /* access modifiers changed from: private */
    public ImageView.ScaleType s = ImageView.ScaleType.CENTER_INSIDE;
    /* access modifiers changed from: private */
    public ImageView.ScaleType t = ImageView.ScaleType.CENTER_CROP;
    /* access modifiers changed from: private */
    public boolean u = false;
    /* access modifiers changed from: private */
    public Animation v = null;
    /* access modifiers changed from: private */
    public boolean w = true;
    /* access modifiers changed from: private */
    public ParamsBuilder x;

    public interface ParamsBuilder {
        RequestParams a(RequestParams requestParams, ImageOptions imageOptions);
    }

    protected ImageOptions() {
    }

    /* access modifiers changed from: package-private */
    public final void a(ImageView imageView) {
        if (this.d <= 0 || this.e <= 0) {
            int b2 = DensityUtil.b();
            int c2 = DensityUtil.c();
            if (this.d < 0) {
                this.b = (b2 * 3) / 2;
                this.k = false;
            }
            if (this.e < 0) {
                this.c = (c2 * 3) / 2;
                this.k = false;
            }
            if (imageView != null || this.b > 0 || this.c > 0) {
                int i2 = this.b;
                int i3 = this.c;
                if (imageView != null) {
                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                    if (layoutParams != null) {
                        if (i2 <= 0) {
                            if (layoutParams.width > 0) {
                                i2 = layoutParams.width;
                                if (this.d <= 0) {
                                    this.d = i2;
                                }
                            } else if (layoutParams.width != -2) {
                                i2 = imageView.getWidth();
                            }
                        }
                        if (i3 <= 0) {
                            if (layoutParams.height > 0) {
                                i3 = layoutParams.height;
                                if (this.e <= 0) {
                                    this.e = i3;
                                }
                            } else if (layoutParams.height != -2) {
                                i3 = imageView.getHeight();
                            }
                        }
                    }
                    if (i2 <= 0) {
                        i2 = a(imageView, "mMaxWidth");
                    }
                    if (i3 <= 0) {
                        i3 = a(imageView, "mMaxHeight");
                    }
                }
                if (i2 > 0) {
                    b2 = i2;
                }
                if (i3 > 0) {
                    c2 = i3;
                }
                this.b = b2;
                this.c = c2;
                return;
            }
            this.b = b2;
            this.c = c2;
            return;
        }
        this.b = this.d;
        this.c = this.e;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public boolean e() {
        return this.f;
    }

    public int f() {
        return this.g;
    }

    public boolean g() {
        return this.h;
    }

    public boolean h() {
        return this.i;
    }

    public boolean i() {
        return this.m;
    }

    public boolean j() {
        return this.j;
    }

    public boolean k() {
        return this.k;
    }

    public Bitmap.Config l() {
        return this.l;
    }

    public Drawable b(ImageView imageView) {
        if (this.p == null && this.n > 0 && imageView != null) {
            try {
                this.p = imageView.getResources().getDrawable(this.n);
            } catch (Throwable th) {
                LogUtil.b(th.getMessage(), th);
            }
        }
        return this.p;
    }

    public Drawable c(ImageView imageView) {
        if (this.q == null && this.o > 0 && imageView != null) {
            try {
                this.q = imageView.getResources().getDrawable(this.o);
            } catch (Throwable th) {
                LogUtil.b(th.getMessage(), th);
            }
        }
        return this.q;
    }

    public boolean m() {
        return this.u;
    }

    public Animation n() {
        return this.v;
    }

    public ImageView.ScaleType o() {
        return this.s;
    }

    public ImageView.ScaleType p() {
        return this.t;
    }

    public boolean q() {
        return this.r;
    }

    public boolean r() {
        return this.w;
    }

    public ParamsBuilder s() {
        return this.x;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImageOptions imageOptions = (ImageOptions) obj;
        if (this.b == imageOptions.b && this.c == imageOptions.c && this.d == imageOptions.d && this.e == imageOptions.e && this.f == imageOptions.f && this.g == imageOptions.g && this.h == imageOptions.h && this.i == imageOptions.i && this.j == imageOptions.j && this.k == imageOptions.k && this.l == imageOptions.l) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((this.b * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + (this.f ? 1 : 0)) * 31) + this.g) * 31) + (this.h ? 1 : 0)) * 31) + (this.i ? 1 : 0)) * 31) + (this.j ? 1 : 0)) * 31) + (this.k ? 1 : 0)) * 31) + (this.l != null ? this.l.hashCode() : 0);
    }

    public String toString() {
        return JSMethod.NOT_SET + this.b + JSMethod.NOT_SET + this.c + JSMethod.NOT_SET + this.d + JSMethod.NOT_SET + this.e + JSMethod.NOT_SET + this.g + JSMethod.NOT_SET + this.l + JSMethod.NOT_SET + (this.f ? 1 : 0) + (this.h ? 1 : 0) + (this.i ? 1 : 0) + (this.j ? 1 : 0) + (this.k ? 1 : 0);
    }

    private static int a(ImageView imageView, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(imageView)).intValue();
            if (intValue <= 0 || intValue >= Integer.MAX_VALUE) {
                return 0;
            }
            return intValue;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        protected ImageOptions f11938a;

        public Builder() {
            a();
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.f11938a = new ImageOptions();
        }

        public ImageOptions b() {
            return this.f11938a;
        }

        public Builder a(int i, int i2) {
            int unused = this.f11938a.d = i;
            int unused2 = this.f11938a.e = i2;
            return this;
        }

        public Builder a(boolean z) {
            boolean unused = this.f11938a.f = z;
            return this;
        }

        public Builder a(int i) {
            int unused = this.f11938a.g = i;
            return this;
        }

        public Builder b(boolean z) {
            boolean unused = this.f11938a.h = z;
            return this;
        }

        public Builder c(boolean z) {
            boolean unused = this.f11938a.i = z;
            return this;
        }

        public Builder d(boolean z) {
            boolean unused = this.f11938a.j = z;
            return this;
        }

        public Builder a(Bitmap.Config config) {
            Bitmap.Config unused = this.f11938a.l = config;
            return this;
        }

        public Builder e(boolean z) {
            boolean unused = this.f11938a.m = z;
            return this;
        }

        public Builder b(int i) {
            int unused = this.f11938a.n = i;
            return this;
        }

        public Builder a(Drawable drawable) {
            Drawable unused = this.f11938a.p = drawable;
            return this;
        }

        public Builder c(int i) {
            int unused = this.f11938a.o = i;
            return this;
        }

        public Builder b(Drawable drawable) {
            Drawable unused = this.f11938a.q = drawable;
            return this;
        }

        public Builder f(boolean z) {
            boolean unused = this.f11938a.u = z;
            return this;
        }

        public Builder a(Animation animation) {
            Animation unused = this.f11938a.v = animation;
            return this;
        }

        public Builder a(ImageView.ScaleType scaleType) {
            ImageView.ScaleType unused = this.f11938a.s = scaleType;
            return this;
        }

        public Builder b(ImageView.ScaleType scaleType) {
            ImageView.ScaleType unused = this.f11938a.t = scaleType;
            return this;
        }

        public Builder g(boolean z) {
            boolean unused = this.f11938a.r = z;
            return this;
        }

        public Builder h(boolean z) {
            boolean unused = this.f11938a.w = z;
            return this;
        }

        public Builder a(ParamsBuilder paramsBuilder) {
            ParamsBuilder unused = this.f11938a.x = paramsBuilder;
            return this;
        }
    }
}
