package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.L;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class ViewAware implements ImageAware {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8491a = "Can't set a drawable into view. You should call ImageLoader on UI thread for it.";
    public static final String b = "Can't set a bitmap into view. You should call ImageLoader on UI thread for it.";
    protected Reference<View> c;
    protected boolean d;

    /* access modifiers changed from: protected */
    public abstract void a(Bitmap bitmap, View view);

    /* access modifiers changed from: protected */
    public abstract void a(Drawable drawable, View view);

    public ViewAware(View view) {
        this(view, true);
    }

    public ViewAware(View view, boolean z) {
        if (view != null) {
            this.c = new WeakReference(view);
            this.d = z;
            return;
        }
        throw new IllegalArgumentException("view must not be null");
    }

    public int a() {
        View view = this.c.get();
        int i = 0;
        if (view == null) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(!this.d || layoutParams == null || layoutParams.width == -2)) {
            i = view.getWidth();
        }
        return (i > 0 || layoutParams == null) ? i : layoutParams.width;
    }

    public int b() {
        View view = this.c.get();
        int i = 0;
        if (view == null) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(!this.d || layoutParams == null || layoutParams.height == -2)) {
            i = view.getHeight();
        }
        return (i > 0 || layoutParams == null) ? i : layoutParams.height;
    }

    public ViewScaleType c() {
        return ViewScaleType.CROP;
    }

    public View d() {
        return this.c.get();
    }

    public boolean e() {
        return this.c.get() == null;
    }

    public int f() {
        View view = this.c.get();
        return view == null ? super.hashCode() : view.hashCode();
    }

    public boolean a(Drawable drawable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = this.c.get();
            if (view != null) {
                a(drawable, view);
                return true;
            }
        } else {
            L.c(f8491a, new Object[0]);
        }
        return false;
    }

    public boolean a(Bitmap bitmap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = this.c.get();
            if (view != null) {
                a(bitmap, view);
                return true;
            }
        } else {
            L.c(b, new Object[0]);
        }
        return false;
    }
}
