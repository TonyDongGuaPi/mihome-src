package com.xiaomi.smarthome.smoothcard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.xiaomi.smarthome.R;

public class SketchSmoothDelegate {

    /* renamed from: a  reason: collision with root package name */
    private final Paint f22724a;
    private Path b;
    private float c = 0.0f;
    private float d = 0.0f;
    private float e = 0.0f;
    private float f = 0.0f;
    private float g = 0.0f;
    private int h = -1;
    private boolean i = true;
    private boolean j = true;
    private boolean k = true;
    private boolean l = true;

    public SketchSmoothDelegate(Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SketchSmoothCardView);
            this.e = obtainStyledAttributes.getDimension(2, 0.0f);
            this.i = obtainStyledAttributes.getBoolean(4, true);
            this.j = obtainStyledAttributes.getBoolean(5, true);
            this.k = obtainStyledAttributes.getBoolean(0, true);
            this.l = obtainStyledAttributes.getBoolean(1, true);
            this.h = obtainStyledAttributes.getColor(3, -1);
            obtainStyledAttributes.recycle();
        }
        this.f22724a = new Paint();
        this.f22724a.setAntiAlias(true);
        this.f22724a.setColor(this.h);
        this.b = new Path();
    }

    public void a(Canvas canvas) {
        canvas.drawPath(this.b, this.f22724a);
    }

    public Path a() {
        return this.b;
    }

    public void a(int i2, int i3, int i4, int i5) {
        float f2 = (float) i2;
        this.c = f2;
        float f3 = (float) i3;
        this.d = f3;
        this.f = (f2 * 1.0f) / 2.0f;
        this.g = (f3 * 1.0f) / 2.0f;
        if ((i2 != i4 || i3 != i5) && this.e != 0.0f) {
            if (this.e == Math.min(this.c, this.d) / 2.0f) {
                this.b = b(0.0f, 0.0f, this.c, this.d, this.e, this.e, this.i, this.j, this.k, this.l);
                return;
            }
            this.b = a(0.0f, 0.0f, this.c, this.d, this.e, this.e, this.i, this.j, this.k, this.l);
        }
    }

    private Path a(float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2, boolean z3, boolean z4) {
        Path path = new Path();
        float f8 = f6 < 0.0f ? 0 : f6;
        int i2 = (f7 > 0.0f ? 1 : (f7 == 0.0f ? 0 : -1));
        float f9 = f4 - f2;
        float f10 = f5 - f3;
        float f11 = f9 / 2.0f;
        float f12 = this.f - f11;
        float f13 = f10 / 2.0f;
        float f14 = this.g - f13;
        float min = ((double) (f8 / Math.min(f11, f13))) > 0.5d ? 1.0f - (Math.min(1.0f, ((f8 / Math.min(f11, f13)) - 0.5f) / 0.4f) * 0.13877845f) : 1.0f;
        float min2 = ((double) (f8 / Math.min(f11, f13))) > 0.6d ? 1.0f + (Math.min(1.0f, ((f8 / Math.min(f11, f13)) - 0.6f) / 0.3f) * 0.042454004f) : 1.0f;
        path.moveTo(f12 + f11, f14);
        if (!z2) {
            path.lineTo(f12 + f9, f14);
        } else {
            float f15 = f8 / 100.0f;
            float f16 = f15 * 128.19f * min;
            path.lineTo(Math.max(f11, f9 - f16) + f12, f14);
            float f17 = f12 + f9;
            float f18 = f15 * 83.62f * min2;
            float f19 = f15 * 67.45f;
            float f20 = f15 * 4.64f;
            float f21 = f15 * 51.16f;
            float f22 = f15 * 13.36f;
            path.cubicTo(f17 - f18, f14, f17 - f19, f14 + f20, f17 - f21, f14 + f22);
            float f23 = f15 * 34.86f;
            float f24 = f15 * 22.07f;
            Path path2 = path;
            path2.cubicTo(f17 - f23, f14 + f24, f17 - f24, f14 + f23, f17 - f22, f14 + f21);
            path2.cubicTo(f17 - f20, f14 + f19, f17, f14 + f18, f17, f14 + Math.min(f13, f16));
        }
        if (!z4) {
            path.lineTo(f9 + f12, f14 + f10);
        } else {
            float f25 = f12 + f9;
            float f26 = f8 / 100.0f;
            float f27 = f26 * 128.19f * min;
            path.lineTo(f25, Math.max(f13, f10 - f27) + f14);
            float f28 = f14 + f10;
            float f29 = f26 * 83.62f * min2;
            float f30 = f26 * 4.64f;
            float f31 = f26 * 67.45f;
            float f32 = f26 * 13.36f;
            float f33 = f26 * 51.16f;
            path.cubicTo(f25, f28 - f29, f25 - f30, f28 - f31, f25 - f32, f28 - f33);
            float f34 = f26 * 22.07f;
            float f35 = f26 * 34.86f;
            Path path3 = path;
            path3.cubicTo(f25 - f34, f28 - f35, f25 - f35, f28 - f34, f25 - f33, f28 - f32);
            path3.cubicTo(f25 - f31, f28 - f30, f25 - f29, f28, f12 + Math.max(f11, f9 - f27), f28);
        }
        if (!z3) {
            path.lineTo(f12, f10 + f14);
        } else {
            float f36 = f8 / 100.0f;
            float f37 = f36 * 128.19f * min;
            float f38 = f14 + f10;
            path.lineTo(Math.min(f11, f37) + f12, f38);
            float f39 = f36 * 83.62f * min2;
            float f40 = f36 * 67.45f;
            float f41 = f36 * 4.64f;
            float f42 = f36 * 51.16f;
            float f43 = f36 * 13.36f;
            float f44 = f38;
            path.cubicTo(f12 + f39, f38, f12 + f40, f38 - f41, f12 + f42, f38 - f43);
            float f45 = f36 * 34.86f;
            float f46 = f36 * 22.07f;
            Path path4 = path;
            path4.cubicTo(f12 + f45, f44 - f46, f12 + f46, f44 - f45, f12 + f43, f44 - f42);
            path4.cubicTo(f12 + f41, f44 - f40, f12, f44 - f39, f12, f14 + Math.max(f13, f10 - f37));
        }
        if (!z) {
            path.lineTo(f12, f14);
        } else {
            float f47 = f8 / 100.0f;
            float f48 = 128.19f * f47 * min;
            path.lineTo(f12, Math.min(f13, f48) + f14);
            float f49 = 83.62f * f47 * min2;
            float f50 = 4.64f * f47;
            float f51 = 67.45f * f47;
            float f52 = 13.36f * f47;
            float f53 = 51.16f * f47;
            Path path5 = path;
            path5.cubicTo(f12, f14 + f49, f12 + f50, f14 + f51, f12 + f52, f14 + f53);
            float f54 = 22.07f * f47;
            float f55 = f47 * 34.86f;
            path5.cubicTo(f12 + f54, f14 + f55, f12 + f55, f14 + f54, f12 + f53, f14 + f52);
            path5.cubicTo(f12 + f51, f14 + f50, f12 + f49, f14, Math.min(f11, f48) + f12, f14);
        }
        path.close();
        return path;
    }

    private Path b(float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2, boolean z3, boolean z4) {
        RectF rectF = new RectF(new Rect((int) f2, (int) f3, (int) f4, (int) f5));
        Path path = new Path();
        float[] fArr = new float[8];
        float f8 = 0.0f;
        fArr[0] = !z ? 0.0f : f6;
        fArr[1] = !z ? 0.0f : f7;
        fArr[2] = !z2 ? 0.0f : f6;
        fArr[3] = !z2 ? 0.0f : f7;
        fArr[4] = !z3 ? 0.0f : f6;
        fArr[5] = !z3 ? 0.0f : f7;
        if (!z4) {
            f6 = 0.0f;
        }
        fArr[6] = f6;
        if (z4) {
            f8 = f7;
        }
        fArr[7] = f8;
        path.addRoundRect(rectF, fArr, Path.Direction.CCW);
        return path;
    }

    public void a(boolean z, boolean z2, boolean z3, boolean z4) {
        this.i = z;
        this.j = z2;
        this.k = z3;
        this.l = z4;
        a((int) this.c, (int) this.d, 0, 0);
    }

    public void a(int i2) {
        this.f22724a.setColor(i2);
    }
}
