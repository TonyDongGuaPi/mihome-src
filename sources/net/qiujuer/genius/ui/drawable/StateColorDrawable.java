package net.qiujuer.genius.ui.drawable;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

public abstract class StateColorDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private ColorStateList f3130a;
    private int b;
    private int c = 255;

    /* access modifiers changed from: protected */
    public void a(int i) {
    }

    public StateColorDrawable(ColorStateList colorStateList) {
        a(colorStateList);
    }

    public boolean isStateful() {
        return this.f3130a.isStateful() || super.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        return d(this.f3130a.getColorForState(iArr, this.b));
    }

    public int getAlpha() {
        return this.c;
    }

    public void setAlpha(int i) {
        this.c = i;
        invalidateSelf();
    }

    public void c(int i) {
        a(ColorStateList.valueOf(i));
    }

    public void a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(-16777216);
        }
        this.f3130a = colorStateList;
        d(colorStateList.getColorForState(getState(), colorStateList.getDefaultColor()));
    }

    public ColorStateList d() {
        return this.f3130a;
    }

    public int e() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public boolean d(int i) {
        boolean z = this.b != i;
        if (z) {
            this.b = i;
            a(i);
            invalidateSelf();
        }
        return z;
    }
}
