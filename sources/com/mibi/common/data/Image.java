package com.mibi.common.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.HashMap;
import java.util.Map;

public class Image {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7521a = "PaymentImage";
    private static String b = "http://file.market.xiaomi.com/mfc/download/";
    private static String c = "http://file.market.xiaomi.com/mfc/thumbnail/";
    private Context d;
    private RequestBuilder<Drawable> e;
    private RequestOptions f = ((RequestOptions) new RequestOptions().a(DiskCacheStrategy.d));
    private String g;

    public interface LoadProcessCallBack {
        void a();

        void a(Drawable drawable);

        void a(String str);
    }

    private Image(Context context) {
        this.d = context.getApplicationContext();
    }

    public static Image a(Context context) {
        return new Image(context);
    }

    public final String a(String str, boolean z, ThumbnailFormat thumbnailFormat) {
        if (z) {
            return str;
        }
        if (thumbnailFormat == null) {
            return Utils.a(b, str);
        }
        String a2 = thumbnailFormat.a();
        if (TextUtils.isEmpty(a2)) {
            return Utils.a(b, str);
        }
        return Utils.a(Utils.a(c, a2), str);
    }

    public Image a(String str) {
        return a(str, false);
    }

    public Image a(String str, ThumbnailFormat thumbnailFormat) {
        return b(a(str, false, thumbnailFormat));
    }

    public Image a(String str, boolean z) {
        return b(a(str, z, (ThumbnailFormat) null));
    }

    private Image b(String str) {
        this.e = Glide.c(this.d).a(str).a(new DrawableTransitionOptions().e());
        return this;
    }

    public Image a(Drawable drawable) {
        if (drawable != null) {
            this.f = (RequestOptions) this.f.c(drawable);
        }
        return this;
    }

    public Image a(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.f = (RequestOptions) this.f.e(i, i2);
        }
        return this;
    }

    public Image b(Drawable drawable) {
        if (drawable != null) {
            this.f = (RequestOptions) this.f.e(drawable);
        }
        return this;
    }

    public void a(ImageView imageView) {
        this.e.b((BaseRequestOptions<?>) this.f).a(imageView);
    }

    public void a(final LoadProcessCallBack loadProcessCallBack) {
        this.e.b((BaseRequestOptions<?>) this.f).a(new SimpleTarget<Drawable>() {
            public void a(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                Log.d(Image.f7521a, "load image success");
                Image.this.c("success");
                if (loadProcessCallBack != null) {
                    loadProcessCallBack.a(drawable.getCurrent());
                }
            }

            public void g() {
                if (loadProcessCallBack != null) {
                    loadProcessCallBack.a();
                }
            }

            public void c(Drawable drawable) {
                Log.d(Image.f7521a, "load image failed");
                Image.this.c(LogCategory.CATEGORY_EXCEPTION);
                if (loadProcessCallBack != null) {
                    loadProcessCallBack.a((String) null);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", str);
        MistatisticUtils.a("image_load", (Map<String, String>) hashMap);
    }

    public static class ThumbnailFormat {

        /* renamed from: a  reason: collision with root package name */
        public static final int f7523a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 0;
        public static final int f = 1;
        public static final int g = 2;
        public static final int h = 80;
        private int i = 0;
        private int j = 0;
        private int k = 0;
        private int l;
        private int m;
        private int n = 80;

        public static ThumbnailFormat a(int i2, int i3, int i4) {
            ThumbnailFormat thumbnailFormat = new ThumbnailFormat();
            thumbnailFormat.i = i2;
            thumbnailFormat.j = i3;
            thumbnailFormat.l = 3;
            thumbnailFormat.m = i4;
            return thumbnailFormat;
        }

        public static ThumbnailFormat a(int i2, int i3) {
            ThumbnailFormat thumbnailFormat = new ThumbnailFormat();
            thumbnailFormat.k = i2;
            thumbnailFormat.l = 0;
            thumbnailFormat.m = i3;
            return thumbnailFormat;
        }

        public static ThumbnailFormat b(int i2, int i3) {
            ThumbnailFormat thumbnailFormat = new ThumbnailFormat();
            thumbnailFormat.i = i2;
            thumbnailFormat.l = 1;
            thumbnailFormat.m = i3;
            return thumbnailFormat;
        }

        public static ThumbnailFormat c(int i2, int i3) {
            ThumbnailFormat thumbnailFormat = new ThumbnailFormat();
            thumbnailFormat.i = i2;
            thumbnailFormat.l = 2;
            thumbnailFormat.m = i3;
            return thumbnailFormat;
        }

        public void a(int i2) {
            if (i2 > 100 || i2 < 0) {
                this.n = 80;
            }
            this.n = i2;
        }

        /* access modifiers changed from: package-private */
        public String a() {
            if (!b()) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            if (this.m == 0) {
                sb.append("jpeg");
            } else if (this.m == 1) {
                sb.append("png");
            } else if (this.m == 2) {
                sb.append("webp");
            }
            sb.append("/");
            if (this.l == 0) {
                sb.append("l" + this.k);
            } else if (this.l == 1) {
                sb.append("w" + this.i);
            } else if (this.l == 2) {
                sb.append("h" + this.j);
            } else if (this.l == 3) {
                sb.append("w" + this.i);
                sb.append("h" + this.j);
            }
            sb.append(DTransferConstants.F + this.n);
            return sb.toString();
        }

        public boolean b() {
            if (this.m != 0 && this.m != 1 && this.m != 2) {
                return false;
            }
            if (this.l == 0 && this.k > 0) {
                return true;
            }
            if (this.l == 1 && this.i > 0) {
                return true;
            }
            if (this.l != 2 || this.j <= 0) {
                return this.l == 3 && this.i > 0 && this.j > 0;
            }
            return true;
        }
    }
}
