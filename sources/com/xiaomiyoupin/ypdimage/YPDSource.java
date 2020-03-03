package com.xiaomiyoupin.ypdimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.uimanager.ThemedReactContext;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomiyoupin.ypdimage.okhttp.OkHttpProgress;
import com.xiaomiyoupin.ypdimage.transformation.BlurTransformation;
import com.xiaomiyoupin.ypdimage.transformation.FitXY;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class YPDSource implements RequestListener<Object>, OkHttpProgress.UIonProgressListener {
    private static final String c = "reload";
    private static final String d = "force-cache";
    private static BitmapTransformation l = new CenterCrop();
    private static BitmapTransformation m = new FitCenter();
    private static BitmapTransformation n = new FitXY();

    /* renamed from: a  reason: collision with root package name */
    private String f1783a;
    private String b;
    private SourceType e;
    private boolean f;
    private int g;
    private String h;
    private Drawable i;
    private ImageView.ScaleType j = ImageView.ScaleType.CENTER;
    private ImageView.ScaleType k;
    private OnLoadEvent o;

    public interface OnLoadEvent {
        void a();

        void a(float f, float f2);

        void b();

        void c();
    }

    public interface OnLoadPlaceholder {
        void a(Drawable drawable);
    }

    public enum SourceType {
        HTTP,
        HTTPS,
        FILE,
        DATA,
        ASSET,
        NONE
    }

    public float j() {
        return 0.1f;
    }

    public void a(OnLoadEvent onLoadEvent) {
        this.o = onLoadEvent;
    }

    public boolean a() {
        return this.o != null;
    }

    public void a(ImageView imageView) {
        RequestManager requestManager;
        if (imageView != null) {
            if (!TextUtils.isEmpty(b())) {
                OkHttpProgress.a(b(), this);
                if (this.o != null) {
                    this.o.c();
                }
            }
            Context context = imageView.getContext();
            if (context instanceof ThemedReactContext) {
                ThemedReactContext themedReactContext = (ThemedReactContext) context;
                if (themedReactContext.hasCurrentActivity()) {
                    Activity currentActivity = themedReactContext.getCurrentActivity();
                    boolean z = false;
                    if (Build.VERSION.SDK_INT >= 17 && (currentActivity == null || currentActivity.isDestroyed())) {
                        z = true;
                    }
                    if (currentActivity == null || z || currentActivity.isFinishing()) {
                        Log.d("YPDSource", "reload a destroyed activity");
                        return;
                    }
                } else {
                    return;
                }
            }
            try {
                requestManager = Glide.c(context);
            } catch (Exception e2) {
                e2.printStackTrace();
                requestManager = null;
            }
            if (requestManager != null) {
                requestManager.a((RequestListener<Object>) this);
                RequestBuilder<Drawable> b2 = requestManager.a(b());
                if (g() != null) {
                    b2 = (RequestBuilder) b2.c(g());
                }
                ArrayList arrayList = new ArrayList();
                BitmapTransformation a2 = a(h());
                if (a2 != null) {
                    arrayList.add(a2);
                } else {
                    imageView.setScaleType(h());
                    b2 = (RequestBuilder) b2.n();
                }
                if (this.g > 0) {
                    arrayList.add(new BlurTransformation(this.g));
                }
                if (arrayList.size() > 0) {
                    b2 = (RequestBuilder) b2.a((Transformation<Bitmap>) new MultiTransformation(arrayList));
                }
                if (TextUtils.equals(this.b, "reload")) {
                    b2 = (RequestBuilder) ((RequestBuilder) b2.a(DiskCacheStrategy.b)).d(true);
                }
                b2.a(imageView);
            }
        }
    }

    public String b() {
        return this.f1783a;
    }

    public void a(String str) {
        this.f1783a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public SourceType c() {
        return this.e;
    }

    public void a(SourceType sourceType) {
        this.e = sourceType;
    }

    public boolean d() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public float e() {
        return (float) this.g;
    }

    public void a(float f2) {
        this.g = (int) f2;
    }

    public String f() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public Drawable g() {
        return this.i;
    }

    public void a(Drawable drawable) {
        this.i = drawable;
    }

    public ImageView.ScaleType h() {
        return this.j;
    }

    public void d(String str) {
        this.j = h(str);
    }

    public ImageView.ScaleType i() {
        return this.k;
    }

    public void e(String str) {
        this.k = h(str);
    }

    public boolean a(@Nullable GlideException glideException, Object obj, Target target, boolean z) {
        if (this.o == null || obj == null) {
            return false;
        }
        this.o.b();
        return false;
    }

    public boolean a(Object obj, Object obj2, Target target, DataSource dataSource, boolean z) {
        if (!(this.o == null || obj2 == null || !TextUtils.equals(obj2.toString(), b()))) {
            int hashCode = target instanceof DrawableImageViewTarget ? ((ImageView) ((DrawableImageViewTarget) target).j()).hashCode() : 0;
            this.o.a();
            Log.d("YPDImage", "view = " + hashCode + ", after");
        }
        return false;
    }

    public void a(long j2, long j3) {
        if (this.o != null) {
            this.o.a((float) j2, (float) j3);
        }
    }

    public static String f(String str) {
        return a(str, (String) null);
    }

    public static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "uri";
        }
        try {
            Object obj = new JSONObject(str).get(str2);
            if (obj == null) {
                return null;
            }
            return obj.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static SourceType g(String str) {
        if (TextUtils.isEmpty(str)) {
            return SourceType.NONE;
        }
        if (str.startsWith(ConnectionHelper.HTTP_PREFIX)) {
            return SourceType.HTTP;
        }
        if (str.startsWith("https://")) {
            return SourceType.HTTPS;
        }
        if (str.startsWith("file://")) {
            return SourceType.FILE;
        }
        if (str.startsWith("asset:/")) {
            return SourceType.ASSET;
        }
        if (str.startsWith("data:")) {
            return SourceType.DATA;
        }
        return SourceType.NONE;
    }

    private static ImageView.ScaleType h(String str) {
        if (TextUtils.isEmpty(str)) {
            return ImageView.ScaleType.CENTER;
        }
        if (TextUtils.equals(str, PlaceFields.COVER)) {
            return ImageView.ScaleType.CENTER_CROP;
        }
        if (TextUtils.equals(str, "contain")) {
            return ImageView.ScaleType.FIT_CENTER;
        }
        if (TextUtils.equals(str, "stretch")) {
            return ImageView.ScaleType.FIT_XY;
        }
        if (TextUtils.equals(str, "center")) {
            return ImageView.ScaleType.CENTER_INSIDE;
        }
        return ImageView.ScaleType.MATRIX;
    }

    private BitmapTransformation a(ImageView.ScaleType scaleType) {
        if (scaleType == ImageView.ScaleType.CENTER_CROP) {
            return l;
        }
        if (scaleType == ImageView.ScaleType.FIT_CENTER) {
            return m;
        }
        if (scaleType == ImageView.ScaleType.CENTER_INSIDE) {
            return null;
        }
        if (scaleType == ImageView.ScaleType.FIT_XY) {
            return n;
        }
        return l;
    }

    public static void a(View view, String str, final OnLoadPlaceholder onLoadPlaceholder) {
        switch (g(str)) {
            case HTTP:
            case HTTPS:
            case FILE:
                Glide.c(view.getContext().getApplicationContext()).l().a(str).a(new CustomTarget<Drawable>() {
                    public void a(@Nullable Drawable drawable) {
                    }

                    public void a(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                        if (onLoadPlaceholder != null) {
                            onLoadPlaceholder.a(drawable);
                        }
                    }
                });
                return;
            case DATA:
                Glide.c(view.getContext().getApplicationContext()).l().a(Base64.decode(str.split(",")[1], 0)).a(new CustomTarget<Drawable>() {
                    public void a(@Nullable Drawable drawable) {
                    }

                    public void a(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                        if (onLoadPlaceholder != null) {
                            onLoadPlaceholder.a(drawable);
                        }
                    }
                });
                return;
            case ASSET:
                String substring = str.substring(str.indexOf("/") + 1);
                Glide.c(view.getContext().getApplicationContext()).l().a("file:///android_asset/" + substring).a(new CustomTarget<Drawable>() {
                    public void a(@Nullable Drawable drawable) {
                    }

                    public void a(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                        if (onLoadPlaceholder != null) {
                            onLoadPlaceholder.a(drawable);
                        }
                    }
                });
                return;
            default:
                return;
        }
    }
}
