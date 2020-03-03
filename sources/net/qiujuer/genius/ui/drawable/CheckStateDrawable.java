package net.qiujuer.genius.ui.drawable;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import net.qiujuer.genius.ui.Ui;

public abstract class CheckStateDrawable extends Drawable {
    protected boolean c = false;
    private ColorStateList d;
    private int e;
    private int f = 255;
    protected boolean r_ = false;
    protected boolean s_ = true;

    /* access modifiers changed from: protected */
    public abstract void a(int i, boolean z, boolean z2);

    public CheckStateDrawable(ColorStateList colorStateList) {
        a(colorStateList);
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean isStateful() {
        return this.d.isStateful() || super.isStateful();
    }

    public boolean setState(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        boolean state = super.setState(iArr);
        boolean z = this.r_;
        boolean z2 = this.s_;
        this.r_ = false;
        this.s_ = true;
        for (int i : iArr) {
            if (i == 16842912) {
                this.r_ = true;
            } else if (i == -16842910) {
                this.s_ = false;
            }
        }
        if (!(!state && z == this.r_ && z2 == this.s_)) {
            a(b(), z, this.r_);
            invalidateSelf();
        }
        return state;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int defaultColor = iArr == null ? this.d.getDefaultColor() : this.d.getColorForState(iArr, this.e);
        boolean z = this.e != defaultColor;
        if (z) {
            this.e = defaultColor;
        }
        return z;
    }

    public int getAlpha() {
        return this.f;
    }

    public void setAlpha(int i) {
        if (i != this.f) {
            this.f = i;
            onStateChange(getState());
        }
    }

    public void a(int i) {
        a(ColorStateList.valueOf(i));
    }

    public void a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(-16777216);
        }
        this.d = colorStateList;
        onStateChange(getState());
    }

    public ColorStateList a() {
        return this.d;
    }

    public int b() {
        return b(this.e);
    }

    public int c() {
        int[] iArr = new int[2];
        iArr[0] = this.s_ ? 16842910 : -16842910;
        iArr[1] = 16842912;
        return b(this.d.getColorForState(iArr, this.e));
    }

    public int d() {
        int[] iArr = new int[2];
        iArr[0] = this.s_ ? 16842910 : -16842910;
        iArr[1] = -16842912;
        return b(this.d.getColorForState(iArr, this.e));
    }

    /* access modifiers changed from: protected */
    public int b(int i) {
        return this.f < 255 ? Ui.b(i, this.f) : i;
    }

    public boolean e() {
        return this.r_;
    }

    public boolean f() {
        return this.s_;
    }
}
