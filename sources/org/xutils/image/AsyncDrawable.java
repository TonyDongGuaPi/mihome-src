package org.xutils.image;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

public final class AsyncDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<ImageLoader> f11923a;
    private Drawable b;

    public AsyncDrawable(ImageLoader imageLoader, Drawable drawable) {
        if (imageLoader != null) {
            this.b = drawable;
            while (this.b instanceof AsyncDrawable) {
                this.b = ((AsyncDrawable) this.b).b;
            }
            this.f11923a = new WeakReference<>(imageLoader);
            return;
        }
        throw new IllegalArgumentException("imageLoader may not be null");
    }

    public ImageLoader a() {
        return (ImageLoader) this.f11923a.get();
    }

    public void a(Drawable drawable) {
        this.b = drawable;
    }

    public Drawable b() {
        return this.b;
    }

    public void draw(Canvas canvas) {
        if (this.b != null) {
            this.b.draw(canvas);
        }
    }

    public void setAlpha(int i) {
        if (this.b != null) {
            this.b.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
        }
    }

    public int getOpacity() {
        if (this.b == null) {
            return -3;
        }
        return this.b.getOpacity();
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        if (this.b != null) {
            this.b.setBounds(i, i2, i3, i4);
        }
    }

    public void setBounds(Rect rect) {
        if (this.b != null) {
            this.b.setBounds(rect);
        }
    }

    public void setChangingConfigurations(int i) {
        if (this.b != null) {
            this.b.setChangingConfigurations(i);
        }
    }

    public int getChangingConfigurations() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getChangingConfigurations();
    }

    public void setDither(boolean z) {
        if (this.b != null) {
            this.b.setDither(z);
        }
    }

    public void setFilterBitmap(boolean z) {
        if (this.b != null) {
            this.b.setFilterBitmap(z);
        }
    }

    public void invalidateSelf() {
        if (this.b != null) {
            this.b.invalidateSelf();
        }
    }

    public void scheduleSelf(Runnable runnable, long j) {
        if (this.b != null) {
            this.b.scheduleSelf(runnable, j);
        }
    }

    public void unscheduleSelf(Runnable runnable) {
        if (this.b != null) {
            this.b.unscheduleSelf(runnable);
        }
    }

    public void setColorFilter(int i, PorterDuff.Mode mode) {
        if (this.b != null) {
            this.b.setColorFilter(i, mode);
        }
    }

    public void clearColorFilter() {
        if (this.b != null) {
            this.b.clearColorFilter();
        }
    }

    public boolean isStateful() {
        return this.b != null && this.b.isStateful();
    }

    public boolean setState(int[] iArr) {
        return this.b != null && this.b.setState(iArr);
    }

    public int[] getState() {
        if (this.b == null) {
            return null;
        }
        return this.b.getState();
    }

    public Drawable getCurrent() {
        if (this.b == null) {
            return null;
        }
        return this.b.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return this.b != null && this.b.setVisible(z, z2);
    }

    public Region getTransparentRegion() {
        if (this.b == null) {
            return null;
        }
        return this.b.getTransparentRegion();
    }

    public int getIntrinsicWidth() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getIntrinsicHeight();
    }

    public int getMinimumWidth() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getMinimumWidth();
    }

    public int getMinimumHeight() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.b != null && this.b.getPadding(rect);
    }

    public Drawable mutate() {
        if (this.b == null) {
            return null;
        }
        return this.b.mutate();
    }

    public Drawable.ConstantState getConstantState() {
        if (this.b == null) {
            return null;
        }
        return this.b.getConstantState();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        ImageLoader a2 = a();
        if (a2 != null) {
            a2.a();
        }
    }
}
