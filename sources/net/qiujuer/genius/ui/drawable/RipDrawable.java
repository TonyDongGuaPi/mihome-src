package net.qiujuer.genius.ui.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.Random;

public class RipDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private Paint f3126a = new Paint(1);
    private Path b = new Path();
    private int c = 24;
    private int d = 4;
    private Rect e = new Rect();
    private int f;
    private boolean g = true;
    private Random h = new Random();

    public int getOpacity() {
        return 0;
    }

    public RipDrawable() {
        this.f3126a.setAntiAlias(true);
        this.f3126a.setStyle(Paint.Style.FILL);
        this.f3126a.setDither(true);
        this.e.top = 36;
        this.e.left = 25;
        this.e.right = 25;
        this.e.bottom = 36;
    }

    public void a(int i, int i2, int i3, int i4) {
        this.e.set(i, i2, i3, i4);
    }

    public void a(int i, int i2) {
        this.d = i;
        this.c = i2;
    }

    public void a(boolean z) {
        if (!z) {
            this.h = null;
        } else if (this.h == null) {
            this.h = new Random();
        }
    }

    public int a() {
        return this.f;
    }

    public void a(int i) {
        if (this.f != i) {
            this.f = i;
            this.f3126a.setColor(i);
            invalidateSelf();
        }
    }

    public void b(int i) {
        if (this.f != i) {
            this.f = i;
            this.f3126a.setColor(i);
        }
    }

    public Paint b() {
        return this.f3126a;
    }

    public void b(boolean z) {
        if (this.g != z) {
            this.g = z;
            a(getBounds());
        }
    }

    public boolean c() {
        return this.g;
    }

    public void c(boolean z) {
        if (!z) {
            this.h = null;
        } else if (this.h == null) {
            this.h = new Random();
        }
    }

    public boolean d() {
        return this.h != null;
    }

    public void draw(Canvas canvas) {
        a(canvas, this.b, this.f3126a);
    }

    /* access modifiers changed from: protected */
    public void a(Canvas canvas, Path path, Paint paint) {
        canvas.drawPath(path, paint);
    }

    public int getAlpha() {
        return this.f3126a.getAlpha();
    }

    public void setAlpha(int i) {
        this.f3126a.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f3126a.setColorFilter(colorFilter);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a(rect);
    }

    /* access modifiers changed from: protected */
    public void a(Rect rect) {
        if (this.g) {
            c(rect.left, rect.top, rect.right, rect.bottom);
        } else {
            b(rect.left, rect.top, rect.right, rect.bottom);
        }
        invalidateSelf();
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, int i3, int i4) {
        this.b.reset();
        float f2 = (float) i;
        float f3 = (float) i2;
        this.b.moveTo(f2, f3);
        int i5 = this.e.left;
        if (i5 > 0) {
            float f4 = ((float) (i4 - i2)) / ((float) i5);
            for (int i6 = 0; i6 < i5; i6++) {
                if (i6 % 2 == 0) {
                    this.b.lineTo(f2, (((float) i6) * f4) + f3);
                } else {
                    this.b.lineTo((float) (e() + i), (((float) i6) * f4) + f3);
                }
            }
        }
        float f5 = (float) i4;
        this.b.lineTo(f2, f5);
        int i7 = this.e.bottom;
        if (i7 > 0) {
            float f6 = ((float) (i3 - i)) / ((float) i7);
            for (int i8 = 0; i8 < i7; i8++) {
                if (i8 % 2 == 0) {
                    this.b.lineTo((((float) i8) * f6) + f2, f5);
                } else {
                    this.b.lineTo((((float) i8) * f6) + f2, (float) (i4 - e()));
                }
            }
        }
        float f7 = (float) i3;
        this.b.lineTo(f7, f5);
        int i9 = this.e.right;
        if (i9 > 0) {
            float f8 = ((float) (i4 - i2)) / ((float) i9);
            for (int i10 = 0; i10 < i9; i10++) {
                if (i10 % 2 == 0) {
                    this.b.lineTo(f7, f5 - (((float) i10) * f8));
                } else {
                    this.b.lineTo((float) (i3 - e()), f5 - (((float) i10) * f8));
                }
            }
        }
        this.b.lineTo(f7, f3);
        int i11 = this.e.top;
        if (i11 > 0) {
            float f9 = ((float) (i3 - i)) / ((float) i11);
            for (int i12 = 0; i12 < i11; i12++) {
                if (i12 % 2 == 0) {
                    this.b.lineTo(f7 - (((float) i12) * f9), f3);
                } else {
                    this.b.lineTo(f7 - (((float) i12) * f9), (float) (e() + i2));
                }
            }
        }
        this.b.lineTo(f2, f3);
        this.b.close();
    }

    /* access modifiers changed from: protected */
    public void c(int i, int i2, int i3, int i4) {
        float f2;
        float e2;
        float f3;
        int i5;
        int i6;
        int f4 = f();
        int i7 = i + (this.e.left > 0 ? f4 : 0);
        int i8 = i2 + (this.e.top > 0 ? f4 : 0);
        int i9 = i3 - (this.e.right > 0 ? f4 : 0);
        int i10 = i4 - (this.e.bottom > 0 ? f4 : 0);
        this.b.reset();
        float f5 = (float) i7;
        float f6 = (float) i8;
        this.b.moveTo(f5, f6);
        int i11 = this.e.left;
        if (i11 > 0) {
            float f7 = ((float) (i10 - i8)) / ((float) (i11 * 2));
            int i12 = i11 - 1;
            float f8 = f6;
            int i13 = 0;
            f2 = 0.0f;
            while (i13 < i12) {
                if (i13 % 2 == 0) {
                    i6 = -e();
                } else {
                    i6 = e();
                }
                f2 = (float) i6;
                float f9 = f8 + f7;
                float f10 = f9 + f7;
                this.b.quadTo(f5 + f2, f9, f5, f10);
                i13++;
                f8 = f10;
            }
            float f11 = (float) i10;
            this.b.quadTo(f5 + f2, f11, f5, f11);
        } else {
            this.b.lineTo(f5, (float) i10);
            f2 = 0.0f;
        }
        float f12 = (float) i10;
        int i14 = this.e.bottom;
        if (i14 > 0) {
            float f13 = ((float) (i9 - i7)) / ((float) (i14 * 2));
            int i15 = i14 - 1;
            float f14 = f5;
            float f15 = f2;
            int i16 = 0;
            while (i16 < i15) {
                if (i16 % 2 == 0) {
                    i5 = e();
                } else {
                    i5 = -e();
                }
                f15 = (float) i5;
                float f16 = f14 + f13;
                float f17 = f16 + f13;
                this.b.quadTo(f16, f12 + f15, f17, f12);
                i16++;
                f14 = f17;
            }
            float f18 = (float) i9;
            this.b.quadTo(f18, f12 + f15, f18, f12);
            f2 = f15;
        } else {
            this.b.lineTo((float) i9, f12);
        }
        float f19 = (float) i9;
        int i17 = this.e.right;
        if (i17 > 0) {
            float f20 = ((float) (i10 - i8)) / ((float) (i17 * 2));
            int i18 = i17 - 1;
            int i19 = 0;
            while (i19 < i18) {
                if (i19 % 2 == 0) {
                    f3 = (float) e();
                } else {
                    f3 = (float) (-e());
                }
                f2 = f3;
                float f21 = f12 - f20;
                float f22 = f21 - f20;
                this.b.quadTo(f19 + f2, f21, f19, f22);
                i19++;
                f12 = f22;
            }
            this.b.quadTo(f19 + f2, f6, f19, f6);
        } else {
            this.b.lineTo(f19, f6);
        }
        int i20 = this.e.top;
        if (i20 > 0) {
            float f23 = ((float) (i9 - i7)) / ((float) (i20 * 2));
            int i21 = i20 - 1;
            float f24 = f19;
            int i22 = 0;
            while (i22 < i21) {
                if (i22 % 2 == 0) {
                    e2 = (float) (-e());
                } else {
                    e2 = (float) e();
                }
                f2 = e2;
                float f25 = f24 - f23;
                float f26 = f25 - f23;
                this.b.quadTo(f25, f6 + f2, f26, f6);
                i22++;
                f24 = f26;
            }
            this.b.quadTo(f5, f2 + f6, f5, f6);
        } else {
            this.b.lineTo(f5, f6);
        }
        this.b.close();
    }

    private int e() {
        if (this.h != null) {
            return this.d + this.h.nextInt(this.c - this.d);
        }
        return (this.c + this.d) / 2;
    }

    private int f() {
        if (this.h != null) {
            return this.c;
        }
        return (this.c + this.d) / 2;
    }
}
