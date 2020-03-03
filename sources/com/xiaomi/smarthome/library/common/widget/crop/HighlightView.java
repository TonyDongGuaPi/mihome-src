package com.xiaomi.smarthome.library.common.widget.crop;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.xiaomi.smarthome.R;

public class HighlightView {
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 4;
    public static final int e = 8;
    public static final int f = 16;
    public static final int g = 32;
    public static final int h = 64;
    public static final int i = 128;
    public static final int j = 256;
    public static final int k = 512;
    private static final String q = "HighlightView";
    private static final float r = 15.0f;
    private Drawable A;
    private Drawable B;
    private Drawable C;
    private Drawable D;
    private Drawable E;
    private final Paint F = new Paint();
    private final Paint G = new Paint();
    private final Paint H = new Paint();

    /* renamed from: a  reason: collision with root package name */
    View f19006a;
    boolean l;
    boolean m;
    Rect n;
    RectF o;
    Matrix p;
    private ModifyMode s = ModifyMode.None;
    private int t;
    private RectF u;
    private boolean v = false;
    private float w;
    private boolean x = false;
    private Drawable y;
    private Drawable z;

    enum ModifyMode {
        None,
        Move,
        Grow
    }

    public HighlightView(View view) {
        this.f19006a = view;
    }

    private void f() {
        Resources resources = this.f19006a.getResources();
        this.y = resources.getDrawable(R.drawable.camera_crop_width);
        this.z = resources.getDrawable(R.drawable.camera_crop_height);
        this.A = resources.getDrawable(R.drawable.indicator_autocrop);
        this.B = resources.getDrawable(R.drawable.crop_left);
        this.C = resources.getDrawable(R.drawable.crop_right);
        this.D = resources.getDrawable(R.drawable.crop_up);
        this.E = resources.getDrawable(R.drawable.crop_down);
    }

    public boolean a() {
        return this.l;
    }

    public void a(boolean z2) {
        this.l = z2;
    }

    public void b(boolean z2) {
        this.m = z2;
    }

    /* access modifiers changed from: protected */
    public void a(Canvas canvas) {
        if (!this.m) {
            Path path = new Path();
            if (!a()) {
                this.H.setColor(this.f19006a.getResources().getColor(R.color.outline_color));
                canvas.drawRect(this.n, this.H);
                b(canvas);
                return;
            }
            Rect rect = new Rect();
            this.f19006a.getDrawingRect(rect);
            if (this.x) {
                canvas.save();
                float width = ((float) this.n.width()) / 2.0f;
                path.addCircle(((float) this.n.left) + width, ((float) this.n.top) + (((float) this.n.height()) / 2.0f), width, Path.Direction.CW);
                this.H.setColor(this.f19006a.getResources().getColor(R.color.outline_color));
                canvas.clipPath(path, Region.Op.DIFFERENCE);
                canvas.drawRect(rect, a() ? this.F : this.G);
                canvas.restore();
            } else {
                Rect rect2 = new Rect(rect.left, rect.top, rect.right, this.n.top);
                if (rect2.width() > 0 && rect2.height() > 0) {
                    canvas.drawRect(rect2, a() ? this.F : this.G);
                }
                Rect rect3 = new Rect(rect.left, this.n.bottom, rect.right, rect.bottom);
                if (rect3.width() > 0 && rect3.height() > 0) {
                    canvas.drawRect(rect3, a() ? this.F : this.G);
                }
                Rect rect4 = new Rect(rect.left, rect2.bottom, this.n.left, rect3.top);
                if (rect4.width() > 0 && rect4.height() > 0) {
                    canvas.drawRect(rect4, a() ? this.F : this.G);
                }
                Rect rect5 = new Rect(this.n.right, rect2.bottom, rect.right, rect3.top);
                if (rect5.width() > 0 && rect5.height() > 0) {
                    canvas.drawRect(rect5, a() ? this.F : this.G);
                }
                path.addRect(new RectF(this.n), Path.Direction.CW);
                this.H.setColor(this.f19006a.getResources().getColor(R.color.outline_color));
            }
            canvas.drawPath(path, this.H);
            b(canvas);
        }
    }

    private void b(Canvas canvas) {
        int i2;
        Canvas canvas2 = canvas;
        int i3 = this.n.left + 1;
        int i4 = this.n.right + 1;
        int i5 = this.n.top + 4;
        int intrinsicWidth = this.B.getIntrinsicWidth() / 2;
        int intrinsicHeight = this.B.getIntrinsicHeight() / 2;
        int intrinsicWidth2 = this.C.getIntrinsicWidth() / 2;
        int intrinsicHeight2 = this.C.getIntrinsicHeight() / 2;
        int intrinsicWidth3 = this.D.getIntrinsicWidth() / 2;
        int intrinsicHeight3 = this.D.getIntrinsicHeight() / 2;
        int intrinsicWidth4 = this.E.getIntrinsicWidth() / 2;
        int i6 = this.n.bottom + 3;
        int i7 = this.n.left + ((this.n.right - this.n.left) / 2);
        int intrinsicHeight4 = this.E.getIntrinsicHeight() / 2;
        int i8 = this.n.top + ((this.n.bottom - this.n.top) / 2);
        if (this.s != ModifyMode.Move) {
            if (this.s != ModifyMode.Grow) {
                int i9 = intrinsicWidth4;
                this.B.setBounds(i3 - intrinsicWidth, i8 - intrinsicHeight, i3 + intrinsicWidth, intrinsicHeight + i8);
                this.B.draw(canvas2);
                this.C.setBounds(i4 - intrinsicWidth2, i8 - intrinsicHeight2, i4 + intrinsicWidth2, i8 + intrinsicHeight2);
                this.C.draw(canvas2);
                this.D.setBounds(i7 - intrinsicWidth3, i5 - intrinsicHeight3, intrinsicWidth3 + i7, i5 + intrinsicHeight3);
                this.D.draw(canvas2);
                this.E.setBounds(i7 - i9, i6 - intrinsicHeight4, i7 + i9, i6 + intrinsicHeight4);
                this.E.draw(canvas2);
            } else if (this.x) {
                int intrinsicWidth5 = this.A.getIntrinsicWidth();
                int intrinsicHeight5 = this.A.getIntrinsicHeight();
                double cos = Math.cos(0.7853981633974483d);
                double width = (double) this.n.width();
                Double.isNaN(width);
                int round = (int) Math.round(cos * (width / 2.0d));
                int width2 = ((this.n.left + (this.n.width() / 2)) + round) - (intrinsicWidth5 / 2);
                int height = ((this.n.top + (this.n.height() / 2)) - round) - (intrinsicHeight5 / 2);
                this.A.setBounds(width2, height, this.A.getIntrinsicWidth() + width2, this.A.getIntrinsicHeight() + height);
                this.A.draw(canvas2);
            } else {
                if ((this.t & 2) != 0) {
                    i2 = intrinsicWidth4;
                    this.B.setBounds(i3 - intrinsicWidth, i8 - intrinsicHeight, i3 + intrinsicWidth, intrinsicHeight + i8);
                    this.B.draw(canvas2);
                } else {
                    i2 = intrinsicWidth4;
                }
                if ((this.t & 4) != 0) {
                    this.C.setBounds(i4 - intrinsicWidth2, i8 - intrinsicHeight2, i4 + intrinsicWidth2, i8 + intrinsicHeight2);
                    this.C.draw(canvas2);
                }
                if ((this.t & 8) != 0) {
                    this.D.setBounds(i7 - intrinsicWidth3, i5 - intrinsicHeight3, intrinsicWidth3 + i7, i5 + intrinsicHeight3);
                    this.D.draw(canvas2);
                }
                if ((this.t & 16) != 0) {
                    this.E.setBounds(i7 - i2, i6 - intrinsicHeight4, i7 + i2, i6 + intrinsicHeight4);
                    this.E.draw(canvas2);
                }
            }
        }
    }

    public ModifyMode b() {
        return this.s;
    }

    public void a(ModifyMode modifyMode) {
        if (modifyMode != this.s) {
            this.s = modifyMode;
            this.f19006a.invalidate();
        }
    }

    public int a(float f2, float f3) {
        int i2;
        Rect g2 = g();
        int i3 = 4;
        int i4 = 16;
        if (this.x) {
            float centerX = f2 - ((float) g2.centerX());
            float centerY = f3 - ((float) g2.centerY());
            int sqrt = (int) Math.sqrt((double) ((centerX * centerX) + (centerY * centerY)));
            int width = this.n.width() / 2;
            if (((float) Math.abs(sqrt - width)) <= 20.0f) {
                return Math.abs(centerY) > Math.abs(centerX) ? centerY < 0.0f ? 8 : 16 : centerX < 0.0f ? 2 : 4;
            }
            if (sqrt < width) {
                return 32;
            }
        } else if (f2 >= ((float) g2.left) - 20.0f && f2 <= ((float) g2.right) + 20.0f && f3 >= ((float) g2.top) - 20.0f && f3 <= ((float) g2.bottom) + 20.0f) {
            if (f2 > ((float) g2.left) + 20.0f && f2 < ((float) g2.right) - 20.0f && f3 > ((float) g2.top) + 20.0f && f3 < ((float) g2.bottom) - 20.0f) {
                return 32;
            }
            boolean z2 = f3 >= ((float) g2.top) - 20.0f && f3 < ((float) g2.bottom) + 20.0f;
            boolean z3 = f2 >= ((float) g2.left) - 20.0f && f2 < ((float) g2.right) + 20.0f;
            if (z2) {
                boolean z4 = Math.abs(f2 - ((float) g2.left)) <= 20.0f;
                boolean z5 = Math.abs(f2 - ((float) g2.right)) <= 20.0f;
                if (z4 && z5) {
                    z4 = Math.abs(f2 - ((float) g2.left)) < Math.abs(f2 - ((float) g2.right));
                    z5 = !z4;
                }
                int i5 = z4 ? 67 : 1;
                if (z5) {
                    i5 = i5 | 4 | 128;
                }
                if (f3 <= ((float) ((g2.top + g2.bottom) / 2))) {
                    i4 = 8;
                }
                i2 = i5 | i4;
            } else {
                i2 = 1;
            }
            if (!z3) {
                return i2;
            }
            boolean z6 = Math.abs(f3 - ((float) g2.top)) <= 20.0f;
            boolean z7 = Math.abs(f3 - ((float) g2.bottom)) <= 20.0f;
            if (z6 && z7) {
                z6 = Math.abs(f3 - ((float) g2.top)) < Math.abs(f3 - ((float) g2.bottom));
                z7 = !z6;
            }
            if (z6) {
                i2 = i2 | 8 | 256;
            }
            if (z7) {
                i2 = i2 | 16 | 512;
            }
            if (f2 <= ((float) ((g2.left + g2.right) / 2))) {
                i3 = 2;
            }
            return i2 | i3;
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, float f2, float f3) {
        Rect g2 = g();
        int i3 = 1;
        if (i2 != 1) {
            if (i2 == 32) {
                b(f2 * (this.o.width() / ((float) g2.width())), f3 * (this.o.height() / ((float) g2.height())));
                return;
            }
            float width = f2 * (this.o.width() / ((float) g2.width()));
            float height = f3 * (this.o.height() / ((float) g2.height()));
            float f4 = ((float) ((i2 & 64) != 0 ? -1 : 1)) * width;
            if ((i2 & 256) != 0) {
                i3 = -1;
            }
            b(i2, f4, ((float) i3) * height);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(float f2, float f3) {
        Rect rect = new Rect(this.n);
        if (this.o.left + f2 < this.u.left) {
            f2 = this.u.left - this.o.left;
        }
        if (this.o.right + f2 > this.u.right) {
            f2 = this.u.right - this.o.right;
        }
        if (this.o.top + f3 < this.u.top) {
            f3 = this.u.top - this.o.top;
        }
        if (this.o.bottom + f3 > this.u.bottom) {
            f3 = this.u.bottom - this.o.bottom;
        }
        this.o.offset(f2, f3);
        this.n = g();
        rect.union(this.n);
        rect.inset(-10, -10);
        this.f19006a.invalidate(rect);
    }

    /* access modifiers changed from: package-private */
    public void b(int i2, float f2, float f3) {
        if (this.v) {
            if ((i2 & 192) != 0) {
                f3 = f2 / this.w;
            } else if ((i2 & 768) != 0) {
                f2 = this.w * f3;
            }
        }
        RectF rectF = new RectF(this.o);
        if (f2 > 0.0f && f3 > 0.0f) {
            float f4 = Float.MAX_VALUE;
            if ((i2 & 4) != 0 && rectF.right + f2 > this.u.right) {
                f4 = Math.min(Float.MAX_VALUE, this.u.right - rectF.right);
            }
            if ((i2 & 2) != 0 && rectF.left - f2 < this.u.left) {
                f4 = Math.min(f4, this.u.left - this.u.left);
            }
            if ((i2 & 8) != 0 && rectF.top - f3 < this.u.top) {
                f4 = Math.min(f4, this.u.top - this.u.top);
            }
            if ((i2 & 16) != 0 && rectF.bottom + f3 > this.u.bottom) {
                f4 = Math.min(f4, this.u.bottom - rectF.bottom);
            }
            if ((i2 & 192) != 0) {
                f2 = Math.min(f2, f4);
                if (this.v) {
                    f3 = f2 / this.w;
                }
            } else if ((i2 & 768) != 0) {
                f3 = Math.min(f3, f4);
                if (this.v) {
                    f2 = this.w * f3;
                }
            }
        } else if (f2 < 0.0f && f3 < 0.0f) {
            float f5 = -1.0E12f;
            if ((i2 & 4) != 0 && rectF.right + f2 < rectF.left + r) {
                f5 = Math.max(-1.0E12f, (rectF.left - rectF.right) + r);
            }
            if ((i2 & 2) != 0 && rectF.left - f2 > rectF.right - r) {
                f5 = Math.max(f5, (rectF.left - rectF.right) + r);
            }
            if ((i2 & 8) != 0 && rectF.top - f3 > rectF.bottom - r) {
                f5 = Math.max(f5, (rectF.top - rectF.bottom) + r);
            }
            if ((i2 & 16) != 0 && rectF.bottom + f3 < rectF.top + r) {
                f5 = Math.max(f5, (rectF.top - rectF.bottom) + r);
            }
            if ((i2 & 192) != 0) {
                f2 = Math.max(f2, f5);
                if (this.v) {
                    f3 = f2 / this.w;
                }
            } else if ((i2 & 768) != 0) {
                f3 = Math.max(f3, f5);
                if (this.v) {
                    f2 = this.w * f3;
                }
            }
        }
        a(rectF, i2, f2, f3);
        this.o.set(rectF);
        this.n = g();
        this.f19006a.invalidate();
    }

    private void a(RectF rectF, int i2, float f2, float f3) {
        if ((i2 & 4) != 0) {
            rectF.right += f2;
        }
        if ((i2 & 2) != 0) {
            rectF.left -= f2;
        }
        if ((i2 & 8) != 0) {
            rectF.top -= f3;
        }
        if ((i2 & 16) != 0) {
            rectF.bottom += f3;
        }
    }

    public Rect c() {
        return new Rect((int) this.o.left, (int) this.o.top, (int) this.o.right, (int) this.o.bottom);
    }

    private Rect g() {
        RectF rectF = new RectF(this.o.left, this.o.top, this.o.right, this.o.bottom);
        this.p.mapRect(rectF);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public void d() {
        this.n = g();
    }

    public void a(Matrix matrix, Rect rect, RectF rectF, boolean z2, boolean z3) {
        if (z2) {
            z3 = true;
        }
        this.p = new Matrix(matrix);
        this.o = rectF;
        this.u = new RectF(rect);
        this.v = z3;
        this.x = z2;
        this.w = this.o.width() / this.o.height();
        this.n = g();
        this.F.setARGB(125, 50, 50, 50);
        this.G.setARGB(125, 50, 50, 50);
        this.H.setStrokeWidth(3.0f);
        this.H.setStyle(Paint.Style.STROKE);
        this.H.setAntiAlias(true);
        this.s = ModifyMode.None;
        f();
    }

    public int e() {
        return this.t;
    }

    public void a(int i2) {
        this.t = i2;
    }
}
