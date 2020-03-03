package com.xiaomi.base.imageloader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.concurrent.Future;

public class GlideImageLoader implements ImageLoaderInterface {

    /* renamed from: a  reason: collision with root package name */
    private static final RequestOptions f10009a = new RequestOptions();

    public Future<Drawable> a(Context context, String str) {
        try {
            return a(context, str, Option.f10012a);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Future<Drawable> a(Context context, String str, Option option) {
        try {
            return Glide.c(context).l().a(str).b((BaseRequestOptions<?>) a(option, context)).b();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Future<Drawable> a(Activity activity, String str, Option option) {
        try {
            return Glide.a(activity).l().a(str).b((BaseRequestOptions<?>) a(option, (Context) activity)).b();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Future<Drawable> a(Fragment fragment, String str, Option option) {
        try {
            return Glide.a(fragment).l().a(str).b((BaseRequestOptions<?>) a(option, (Context) fragment.getActivity())).b();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(String str, ImageView imageView) {
        a(str, imageView, Option.f10012a);
    }

    public void a(String str, ImageView imageView, Option option) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "http";
            }
            ViewTarget<ImageView, GifDrawable> viewTarget = null;
            if (str.toLowerCase().endsWith(".gif")) {
                viewTarget = a(imageView);
            }
            if (viewTarget == null) {
                Glide.a((View) imageView).a(str).b((BaseRequestOptions<?>) a(option, imageView.getContext())).a(imageView);
            } else {
                Glide.a((View) imageView).k().a(str).b((BaseRequestOptions<?>) a(option, imageView.getContext())).a(viewTarget);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private ViewTarget<ImageView, GifDrawable> a(ImageView imageView) {
        return new ViewTarget<ImageView, GifDrawable>(imageView) {
            /* JADX WARNING: Removed duplicated region for block: B:16:0x002d  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(com.bumptech.glide.load.resource.gif.GifDrawable r4, com.bumptech.glide.request.transition.Transition<? super com.bumptech.glide.load.resource.gif.GifDrawable> r5) {
                /*
                    r3 = this;
                    r5 = 0
                    pl.droidsonroids.gif.GifDrawable r0 = new pl.droidsonroids.gif.GifDrawable     // Catch:{ IOException -> 0x0019, all -> 0x0014 }
                    java.nio.ByteBuffer r1 = r4.d()     // Catch:{ IOException -> 0x0019, all -> 0x0014 }
                    r0.<init>((java.nio.ByteBuffer) r1)     // Catch:{ IOException -> 0x0019, all -> 0x0014 }
                    android.view.View r5 = r3.f5075a     // Catch:{ IOException -> 0x0012 }
                    android.widget.ImageView r5 = (android.widget.ImageView) r5     // Catch:{ IOException -> 0x0012 }
                    r5.setImageDrawable(r0)     // Catch:{ IOException -> 0x0012 }
                    goto L_0x0029
                L_0x0012:
                    r5 = move-exception
                    goto L_0x001d
                L_0x0014:
                    r0 = move-exception
                    r2 = r0
                    r0 = r5
                    r5 = r2
                    goto L_0x002b
                L_0x0019:
                    r0 = move-exception
                    r2 = r0
                    r0 = r5
                    r5 = r2
                L_0x001d:
                    r5.printStackTrace()     // Catch:{ all -> 0x002a }
                    if (r0 != 0) goto L_0x0029
                    android.view.View r5 = r3.f5075a
                    android.widget.ImageView r5 = (android.widget.ImageView) r5
                    r5.setImageDrawable(r4)
                L_0x0029:
                    return
                L_0x002a:
                    r5 = move-exception
                L_0x002b:
                    if (r0 != 0) goto L_0x0034
                    android.view.View r0 = r3.f5075a
                    android.widget.ImageView r0 = (android.widget.ImageView) r0
                    r0.setImageDrawable(r4)
                L_0x0034:
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.imageloader.GlideImageLoader.AnonymousClass1.a(com.bumptech.glide.load.resource.gif.GifDrawable, com.bumptech.glide.request.transition.Transition):void");
            }
        };
    }

    public void a(String str, ImageView imageView, Activity activity, Option option) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "http";
            }
            ViewTarget<ImageView, GifDrawable> viewTarget = null;
            if (str.toLowerCase().endsWith(".gif")) {
                viewTarget = a(imageView);
            }
            if (viewTarget == null) {
                Glide.a(activity).a(str).b((BaseRequestOptions<?>) a(option, imageView.getContext())).a(imageView);
            } else {
                Glide.a(activity).k().a(str).b((BaseRequestOptions<?>) a(option, imageView.getContext())).a(viewTarget);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str, ImageView imageView, Fragment fragment, Option option) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "http";
            }
            ViewTarget<ImageView, GifDrawable> viewTarget = null;
            if (str.toLowerCase().endsWith(".gif")) {
                viewTarget = a(imageView);
            }
            if (viewTarget == null) {
                Glide.a(fragment).a(str).b((BaseRequestOptions<?>) a(option, imageView.getContext())).a(imageView);
            } else {
                Glide.a(fragment).k().a(str).b((BaseRequestOptions<?>) a(option, imageView.getContext())).a(viewTarget);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Future<Drawable> a(Context context, int i) {
        return a(context, i, Option.f10012a);
    }

    public Future<Drawable> a(Context context, int i, Option option) {
        return Glide.c(context).l().a(Integer.valueOf(i)).b((BaseRequestOptions<?>) a(option, context)).b();
    }

    public void a(int i, ImageView imageView) {
        a(i, imageView, Option.f10012a);
    }

    public void a(int i, ImageView imageView, Option option) {
        Glide.a((View) imageView).a(Integer.valueOf(i)).b((BaseRequestOptions<?>) a(option, imageView.getContext())).a(imageView);
    }

    @SuppressLint({"CheckResult"})
    private RequestOptions a(Option option, Context context) {
        if (option == null) {
            return f10009a;
        }
        RequestOptions requestOptions = new RequestOptions();
        if (option.c() != 0) {
            requestOptions = (RequestOptions) requestOptions.a(option.c());
        } else if (option.d() != null) {
            requestOptions = (RequestOptions) requestOptions.c(option.d());
        }
        if (option.e() != 0) {
            requestOptions = (RequestOptions) requestOptions.c(option.e());
        } else if (option.f() != null) {
            requestOptions = (RequestOptions) requestOptions.e(option.f());
        }
        if (option.a() > 0) {
            if (option.f) {
                CornerTransformNew cornerTransformNew = new CornerTransformNew((float) option.a());
                cornerTransformNew.a(option.b, option.c, option.d, option.e);
                Transformation transformation = null;
                if (option.g == ImageView.ScaleType.CENTER_CROP) {
                    transformation = new CenterCrop();
                } else if (option.g == ImageView.ScaleType.FIT_CENTER) {
                    transformation = new FitCenter();
                } else if (option.g == ImageView.ScaleType.CENTER_INSIDE) {
                    transformation = new CenterInside();
                }
                if (transformation != null) {
                    requestOptions.a((Transformation<Bitmap>) new MultiTransformation((Transformation<T>[]) new Transformation[]{transformation, cornerTransformNew}));
                } else {
                    requestOptions.a((Transformation<Bitmap>) cornerTransformNew);
                }
            } else {
                requestOptions.a((Transformation<Bitmap>) b(option, context));
            }
        }
        if (option.b()) {
            requestOptions.q();
        }
        if (option.g() == null) {
            return requestOptions;
        }
        switch (option.g()) {
            case ALL:
                return (RequestOptions) requestOptions.a(DiskCacheStrategy.f4865a);
            case NONE:
                return (RequestOptions) requestOptions.a(DiskCacheStrategy.b);
            case AUTOMATIC:
                return (RequestOptions) requestOptions.a(DiskCacheStrategy.e);
            default:
                return requestOptions;
        }
    }

    private CornerTransform b(Option option, Context context) {
        CornerTransform cornerTransform = new CornerTransform(context, (float) option.a());
        cornerTransform.a(option.b, option.c, option.d, option.e);
        return cornerTransform;
    }
}
