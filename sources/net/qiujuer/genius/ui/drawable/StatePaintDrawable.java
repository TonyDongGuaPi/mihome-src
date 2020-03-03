package net.qiujuer.genius.ui.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import net.qiujuer.genius.ui.Ui;

public abstract class StatePaintDrawable extends StateColorDrawable {
    private PorterDuffColorFilter b;
    private ColorStateList c = null;
    private PorterDuff.Mode d = PorterDuff.Mode.SRC_IN;
    protected final Paint q_ = new Paint(1);

    public abstract void a(Canvas canvas, Paint paint);

    public StatePaintDrawable(ColorStateList colorStateList) {
        super(colorStateList);
        this.q_.setColor(e());
        this.b = a(this.b, this.c, this.d);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Paint paint = this.q_;
        if (paint != null && paint.getColorFilter() != colorFilter) {
            paint.setColorFilter(colorFilter);
            invalidateSelf();
        }
    }

    public int getOpacity() {
        Paint paint = this.q_;
        if (paint.getXfermode() != null) {
            return -3;
        }
        int alpha = paint.getAlpha();
        if (alpha == 0) {
            return -2;
        }
        return alpha == 255 ? -1 : -3;
    }

    public void setDither(boolean z) {
        this.q_.setDither(z);
        invalidateSelf();
    }

    public void setTintList(ColorStateList colorStateList) {
        if (this.c != colorStateList) {
            this.c = colorStateList;
            this.b = a(this.b, colorStateList, this.d);
            invalidateSelf();
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        if (mode != this.d || mode.compareTo(this.d) != 0) {
            this.d = mode;
            this.b = a(this.b, this.c, mode);
            invalidateSelf();
        }
    }

    public boolean isStateful() {
        return super.isStateful() || (this.c != null && this.c.isStateful());
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        if (this.c == null || this.d == null) {
            return onStateChange;
        }
        this.b = a(this.b, this.c, this.d);
        return true;
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        Paint paint = this.q_;
        if (paint != null && paint.getColor() != i) {
            paint.setColor(i);
        }
    }

    public void draw(Canvas canvas) {
        boolean z;
        Paint paint = this.q_;
        int alpha = paint.getAlpha();
        paint.setAlpha(Ui.a(alpha, getAlpha()));
        if (!(paint.getAlpha() == 0 && paint.getXfermode() == null)) {
            if (this.b == null || paint.getColorFilter() != null) {
                z = false;
            } else {
                paint.setColorFilter(this.b);
                z = true;
            }
            a(canvas, this.q_);
            if (z) {
                paint.setColorFilter((ColorFilter) null);
            }
        }
        paint.setAlpha(alpha);
    }

    public Paint f() {
        return this.q_;
    }

    /* access modifiers changed from: package-private */
    public PorterDuffColorFilter a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (porterDuffColorFilter == null) {
            return new PorterDuffColorFilter(colorForState, mode);
        }
        try {
            Class<?> cls = porterDuffColorFilter.getClass();
            cls.getMethod("setColor", new Class[]{Integer.class}).invoke(porterDuffColorFilter, new Object[]{Integer.valueOf(colorForState)});
            cls.getMethod("setMode", new Class[]{PorterDuff.Mode.class}).invoke(porterDuffColorFilter, new Object[]{mode});
            return porterDuffColorFilter;
        } catch (Exception unused) {
            return new PorterDuffColorFilter(colorForState, mode);
        }
    }
}
