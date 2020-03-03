package com.tmall.wireless.vaf.virtualview.Helper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;

public class VirtualViewUtils {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f9373a = true;
    private static RectF b = new RectF();
    private static Path c = new Path();
    private static Paint d;
    private static Paint e;

    private static boolean a(int i, int i2, int i3, int i4) {
        return i > 0 || i2 > 0 || i3 > 0 || i4 > 0;
    }

    public static void a(boolean z) {
        f9373a = z;
    }

    public static void a(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        float f;
        int i13 = i;
        int i14 = i2;
        int i15 = i3;
        int i16 = i4;
        if (canvas != null && i16 > 0 && i13 != 0) {
            if (d == null) {
                d = new Paint();
                d.setAntiAlias(true);
                d.setStyle(Paint.Style.STROKE);
            }
            d.setColor(i13);
            float f2 = (float) i16;
            d.setStrokeWidth(f2);
            if (!f9373a) {
                i12 = 0;
                i11 = 0;
                i10 = 0;
                i9 = 0;
            } else {
                i12 = i5;
                i10 = i6;
                i11 = i7;
                i9 = i8;
            }
            float f3 = f2 / 2.0f;
            canvas.drawLine(f3, i12 > 0 ? ((float) i12) + f3 : 0.0f, f3, i11 > 0 ? ((float) (i15 - i11)) - f3 : (float) i15, d);
            canvas.drawLine(i12 > 0 ? ((float) i12) + f3 : 0.0f, f3, i10 > 0 ? ((float) (i14 - i10)) - f3 : (float) i14, f3, d);
            float f4 = (float) i14;
            float f5 = f4 - f3;
            canvas.drawLine(f5, i10 > 0 ? ((float) i10) + f3 : 0.0f, f5, i9 > 0 ? ((float) (i15 - i9)) - f3 : (float) i15, d);
            float f6 = (float) i15;
            float f7 = f6 - f3;
            float f8 = f6;
            canvas.drawLine(i11 > 0 ? ((float) i11) + f3 : 0.0f, f7, i9 > 0 ? ((float) (i14 - i9)) - f3 : f4, f7, d);
            if (i12 > 0) {
                float f9 = (float) (i12 * 2);
                b.set(0.0f, 0.0f, f9, f9);
                b.offset(f3, f3);
                canvas.drawArc(b, 179.0f, 91.0f, false, d);
            }
            if (i10 > 0) {
                int i17 = i10 * 2;
                b.set((float) (i14 - i17), 0.0f, f4, (float) i17);
                b.offset(-f3, f3);
                canvas.drawArc(b, 269.0f, 91.0f, false, d);
            }
            if (i9 > 0) {
                int i18 = i9 * 2;
                f = f8;
                b.set((float) (i14 - i18), (float) (i15 - i18), f4, f);
                float f10 = -f3;
                b.offset(f10, f10);
                canvas.drawArc(b, -1.0f, 91.0f, false, d);
            } else {
                f = f8;
            }
            if (i11 > 0) {
                int i19 = i11 * 2;
                b.set(0.0f, (float) (i15 - i19), (float) i19, f);
                b.offset(f3, -f3);
                canvas.drawArc(b, 89.0f, 91.0f, false, d);
            }
        }
    }

    public static void b(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        Canvas canvas2 = canvas;
        int i13 = i2;
        int i14 = i3;
        int i15 = i4;
        if (canvas2 != null) {
            if (e == null) {
                e = new Paint();
                e.setAntiAlias(true);
            }
            e.setColor(i);
            if (!f9373a) {
                i12 = 0;
                i11 = 0;
                i10 = 0;
                i9 = 0;
            } else {
                i12 = i5;
                i11 = i6;
                i9 = i7;
                i10 = i8;
            }
            float f = (float) i15;
            float f2 = f / 2.0f;
            c.reset();
            c.moveTo((float) ((i12 > 0 ? i12 : 0) + i15), f);
            int i16 = i13 - i15;
            c.lineTo((float) (i16 - (i11 > 0 ? i11 : 0)), f);
            if (i11 > 0) {
                int i17 = i11 * 2;
                b.set((float) (i13 - i17), 0.0f, (float) i13, (float) i17);
                b.offset(-f2, f2);
                c.arcTo(b, 270.0f, 90.0f);
            }
            float f3 = (float) i16;
            int i18 = i14 - i15;
            c.lineTo(f3, (float) (i18 - (i10 > 0 ? i10 : 0)));
            if (i10 > 0) {
                int i19 = i10 * 2;
                b.set((float) (i13 - i19), (float) (i14 - i19), (float) i13, (float) i14);
                float f4 = -f2;
                b.offset(f4, f4);
                c.arcTo(b, 0.0f, 90.0f);
            }
            c.lineTo((float) ((i9 > 0 ? i9 : 0) + i15), (float) i18);
            if (i9 > 0) {
                int i20 = i9 * 2;
                b.set(0.0f, (float) (i14 - i20), (float) i20, (float) i14);
                b.offset(f2, -f2);
                c.arcTo(b, 90.0f, 90.0f);
            }
            c.lineTo(f, (float) (i15 + (i12 > 0 ? i12 : 0)));
            if (i12 > 0) {
                float f5 = (float) (i12 * 2);
                b.set(0.0f, 0.0f, f5, f5);
                b.offset(f2, f2);
                c.arcTo(b, 180.0f, 90.0f);
            }
            canvas2.drawPath(c, e);
        }
    }

    public static void a(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        a((View) null, canvas, i, i2, i3, i4, i5, i6, i7);
    }

    public static void a(View view, Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (canvas != null) {
            int i8 = 0;
            if (!f9373a) {
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
            }
            if (a(i4, i5, i6, i7)) {
                c.reset();
                c.moveTo((float) (i4 > 0 ? i4 : 0), 0.0f);
                c.lineTo((float) (i - (i5 > 0 ? i5 : 0)), 0.0f);
                if (i5 > 0) {
                    int i9 = i5 * 2;
                    b.set((float) (i - i9), 0.0f, (float) i, (float) i9);
                    c.arcTo(b, 270.0f, 90.0f);
                }
                float f = (float) i;
                c.lineTo(f, (float) (i2 - (i7 > 0 ? i7 : 0)));
                if (i7 > 0) {
                    int i10 = i7 * 2;
                    b.set((float) (i - i10), (float) (i2 - i10), f, (float) i2);
                    c.arcTo(b, 0.0f, 90.0f);
                }
                float f2 = (float) i2;
                c.lineTo((float) (i6 > 0 ? i6 : 0), f2);
                if (i6 > 0) {
                    int i11 = i6 * 2;
                    b.set(0.0f, (float) (i2 - i11), (float) i11, f2);
                    c.arcTo(b, 90.0f, 90.0f);
                }
                Path path = c;
                if (i4 > 0) {
                    i8 = i4;
                }
                path.lineTo(0.0f, (float) i8);
                if (i4 > 0) {
                    float f3 = (float) (i4 * 2);
                    b.set(0.0f, 0.0f, f3, f3);
                    c.arcTo(b, 180.0f, 90.0f);
                }
                if (canvas.isHardwareAccelerated() && Build.VERSION.SDK_INT < 18 && view != null) {
                    view.setLayerType(1, (Paint) null);
                }
                canvas.clipPath(c);
            }
        }
    }
}
