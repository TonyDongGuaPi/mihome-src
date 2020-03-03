package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;

class Brush {

    /* renamed from: a  reason: collision with root package name */
    private final BrushType f5805a;
    private final SVGLength[] b;
    private ReadableArray c;
    private final boolean d;
    private boolean e;
    private Matrix f;
    private Rect g;
    private PatternView h;

    enum BrushType {
        LINEAR_GRADIENT,
        RADIAL_GRADIENT,
        PATTERN
    }

    enum BrushUnits {
        OBJECT_BOUNDING_BOX,
        USER_SPACE_ON_USE
    }

    Brush(BrushType brushType, SVGLength[] sVGLengthArr, BrushUnits brushUnits) {
        this.f5805a = brushType;
        this.b = sVGLengthArr;
        this.d = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    /* access modifiers changed from: package-private */
    public void a(BrushUnits brushUnits) {
        this.e = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    /* access modifiers changed from: package-private */
    public void a(PatternView patternView) {
        this.h = patternView;
    }

    private static void a(ReadableArray readableArray, int i, float[] fArr, int[] iArr, float f2) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            fArr[i2] = (float) readableArray.getDouble(i3);
            int i4 = readableArray.getInt(i3 + 1);
            iArr[i2] = (i4 & 16777215) | (Math.round(((float) (i4 >>> 24)) * f2) << 24);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Rect rect) {
        this.g = rect;
    }

    /* access modifiers changed from: package-private */
    public void a(ReadableArray readableArray) {
        this.c = readableArray;
    }

    /* access modifiers changed from: package-private */
    public void a(Matrix matrix) {
        this.f = matrix;
    }

    private RectF a(RectF rectF) {
        float f2;
        if (!this.d) {
            rectF = new RectF(this.g);
        }
        float width = rectF.width();
        float height = rectF.height();
        float f3 = 0.0f;
        if (this.d) {
            f3 = rectF.left;
            f2 = rectF.top;
        } else {
            f2 = 0.0f;
        }
        return new RectF(f3, f2, width + f3, height + f2);
    }

    private double a(SVGLength sVGLength, double d2, float f2, float f3) {
        double d3;
        if (!this.d) {
            SVGLength sVGLength2 = sVGLength;
        } else if (sVGLength.b == SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER) {
            d3 = d2;
            return PropHelper.a(sVGLength, d2, 0.0d, d3, (double) f3);
        }
        d3 = (double) f2;
        return PropHelper.a(sVGLength, d2, 0.0d, d3, (double) f3);
    }

    /* access modifiers changed from: package-private */
    public void a(Paint paint, RectF rectF, float f2, float f3) {
        float[] fArr;
        int[] iArr;
        Paint paint2 = paint;
        float f4 = f2;
        float f5 = f3;
        RectF a2 = a(rectF);
        float width = a2.width();
        float height = a2.height();
        float f6 = a2.left;
        float f7 = a2.top;
        float textSize = paint.getTextSize();
        if (this.f5805a == BrushType.PATTERN) {
            double d2 = (double) width;
            double d3 = d2;
            double a3 = a(this.b[0], d2, f2, textSize);
            double d4 = (double) height;
            double d5 = a3;
            double a4 = a(this.b[1], d4, f2, textSize);
            double d6 = a4;
            double a5 = a(this.b[2], d3, f2, textSize);
            SVGLength sVGLength = this.b[3];
            double d7 = a5;
            double a6 = a(sVGLength, d4, f2, textSize);
            if (d7 > 1.0d && a6 > 1.0d) {
                Bitmap createBitmap = Bitmap.createBitmap((int) d7, (int) a6, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                RectF e2 = this.h.e();
                if (e2 != null && e2.width() > 0.0f && e2.height() > 0.0f) {
                    canvas.concat(ViewBox.a(e2, new RectF((float) d5, (float) d6, (float) d7, (float) a6), this.h.b, this.h.c));
                }
                if (this.e) {
                    float f8 = f2;
                    canvas.scale(width / f8, height / f8);
                }
                this.h.draw(canvas, new Paint(), f5);
                Matrix matrix = new Matrix();
                if (this.f != null) {
                    matrix.preConcat(this.f);
                }
                BitmapShader bitmapShader = new BitmapShader(createBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
                bitmapShader.setLocalMatrix(matrix);
                paint.setShader(bitmapShader);
                return;
            }
            return;
        }
        Paint paint3 = paint2;
        float f9 = f4;
        int size = this.c.size() / 2;
        int[] iArr2 = new int[size];
        float[] fArr2 = new float[size];
        a(this.c, size, fArr2, iArr2, f5);
        if (fArr2.length == 1) {
            iArr = new int[]{iArr2[0], iArr2[0]};
            fArr = new float[]{fArr2[0], fArr2[0]};
            FLog.w(ReactConstants.TAG, "Gradient contains only on stop");
        } else {
            iArr = iArr2;
            fArr = fArr2;
        }
        if (this.f5805a == BrushType.LINEAR_GRADIENT) {
            double d8 = (double) width;
            double d9 = (double) f6;
            double d10 = (double) f9;
            double d11 = (double) textSize;
            double a7 = PropHelper.a(this.b[0], d8, d9, d10, d11);
            double d12 = (double) height;
            double d13 = (double) f7;
            double d14 = d10;
            double d15 = d11;
            double a8 = PropHelper.a(this.b[1], d12, d13, d14, d15);
            float[] fArr3 = fArr;
            LinearGradient linearGradient = new LinearGradient((float) a7, (float) a8, (float) PropHelper.a(this.b[2], d8, d9, d14, d15), (float) PropHelper.a(this.b[3], d12, d13, d14, d15), iArr, fArr3, Shader.TileMode.CLAMP);
            if (this.f != null) {
                Matrix matrix2 = new Matrix();
                matrix2.preConcat(this.f);
                linearGradient.setLocalMatrix(matrix2);
            }
            paint.setShader(linearGradient);
            return;
        }
        int[] iArr3 = iArr;
        float[] fArr4 = fArr;
        if (this.f5805a == BrushType.RADIAL_GRADIENT) {
            double d16 = (double) width;
            double d17 = (double) f9;
            double d18 = (double) textSize;
            double a9 = PropHelper.a(this.b[2], d16, 0.0d, d17, d18);
            double d19 = (double) height;
            double d20 = d17;
            double d21 = d18;
            double a10 = PropHelper.a(this.b[3], d19, 0.0d, d20, d21);
            double a11 = PropHelper.a(this.b[4], d16, (double) f6, d20, d21);
            double d22 = a10 / a9;
            RadialGradient radialGradient = new RadialGradient((float) a11, (float) (PropHelper.a(this.b[5], d19, (double) f7, d20, d21) / d22), (float) a9, iArr3, fArr4, Shader.TileMode.CLAMP);
            Matrix matrix3 = new Matrix();
            matrix3.preScale(1.0f, (float) d22);
            if (this.f != null) {
                matrix3.preConcat(this.f);
            }
            radialGradient.setLocalMatrix(matrix3);
            paint.setShader(radialGradient);
        }
    }
}
