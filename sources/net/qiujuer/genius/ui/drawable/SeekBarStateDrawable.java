package net.qiujuer.genius.ui.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import net.qiujuer.genius.ui.Ui;

public abstract class SeekBarStateDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private final Paint f3129a = new Paint(1);
    private int b = 255;
    private ColorStateList c;
    private int d;
    private ColorStateList e;
    private int f;
    private ColorStateList g;
    private int h;
    private int i;
    private int j;
    private int k;

    public abstract void a(Canvas canvas, Paint paint, int i2, int i3, int i4);

    public int getOpacity() {
        return -3;
    }

    public SeekBarStateDrawable(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3) {
        a(colorStateList);
        b(colorStateList2);
        c(colorStateList3);
    }

    public boolean isStateful() {
        return this.c.isStateful() || this.e.isStateful() || this.g.isStateful() || super.isStateful();
    }

    public void draw(Canvas canvas) {
        a(canvas, this.f3129a, this.i, this.j, this.k);
    }

    public boolean setState(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        boolean state = super.setState(iArr);
        if (a(iArr) || state) {
            return true;
        }
        return false;
    }

    public int getAlpha() {
        return this.b;
    }

    public void setAlpha(int i2) {
        this.b = i2;
        a();
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f3129a.setColorFilter(colorFilter);
    }

    public void a(ColorStateList colorStateList) {
        this.c = colorStateList;
        this.d = this.c.getDefaultColor();
        if (this.b < 255) {
            this.i = Ui.b(this.d, this.b);
        } else {
            this.i = this.d;
        }
    }

    public ColorStateList k() {
        return this.c;
    }

    public void b(ColorStateList colorStateList) {
        this.e = colorStateList;
        this.f = this.e.getDefaultColor();
        if (this.b < 255) {
            this.j = Ui.b(this.f, this.b);
        } else {
            this.j = this.f;
        }
    }

    public ColorStateList l() {
        return this.e;
    }

    public void c(ColorStateList colorStateList) {
        this.g = colorStateList;
        this.h = this.g.getDefaultColor();
        if (this.b < 255) {
            this.k = Ui.b(this.h, this.b);
        } else {
            this.k = this.h;
        }
    }

    public ColorStateList m() {
        return this.g;
    }

    public ColorStateList[] n() {
        return new ColorStateList[]{this.c, this.e, this.g};
    }

    public int[] o() {
        return new int[]{this.i, this.j, this.k};
    }

    private boolean a(int[] iArr) {
        int colorForState = this.c.getColorForState(iArr, this.d);
        int colorForState2 = this.e.getColorForState(iArr, this.f);
        int colorForState3 = this.g.getColorForState(iArr, this.h);
        if (colorForState == this.d && colorForState2 == this.f && colorForState3 == this.h) {
            return false;
        }
        this.d = colorForState;
        this.f = colorForState2;
        this.h = colorForState3;
        a();
        invalidateSelf();
        return true;
    }

    private void a() {
        if (this.b < 255) {
            this.i = Ui.b(this.d, this.b);
            this.j = Ui.b(this.f, this.b);
            this.k = Ui.b(this.h, this.b);
            return;
        }
        this.i = this.d;
        this.j = this.f;
        this.k = this.h;
    }
}
