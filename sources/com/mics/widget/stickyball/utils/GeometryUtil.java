package com.mics.widget.stickyball.utils;

import android.graphics.PointF;

public class GeometryUtil {
    public static float a(PointF pointF, PointF pointF2) {
        return (float) Math.sqrt(Math.pow((double) (pointF.y - pointF2.y), 2.0d) + Math.pow((double) (pointF.x - pointF2.x), 2.0d));
    }

    public static PointF b(PointF pointF, PointF pointF2) {
        return new PointF((pointF.x + pointF2.x) / 2.0f, (pointF.y + pointF2.y) / 2.0f);
    }

    public static PointF a(PointF pointF, PointF pointF2, float f, float f2) {
        float a2 = a(pointF, pointF2);
        float f3 = (f2 + (((a2 - f) - f2) / 2.0f)) / a2;
        return new PointF(((pointF.x - pointF2.x) * f3) + pointF2.x, ((pointF.y - pointF2.y) * f3) + pointF2.y);
    }

    public static PointF a(PointF pointF, PointF pointF2, float f) {
        return new PointF(a(f, (Number) Float.valueOf(pointF.x), (Number) Float.valueOf(pointF2.x)), a(f, (Number) Float.valueOf(pointF.y), (Number) Float.valueOf(pointF2.y)));
    }

    public static float a(float f, Number number, Number number2) {
        return number.floatValue() + ((number2.floatValue() - number.floatValue()) * f);
    }

    public static PointF[] a(PointF pointF, PointF pointF2, float f, float f2, double d) {
        PointF pointF3;
        PointF pointF4;
        float f3;
        float f4;
        float f5;
        float f6;
        PointF pointF5 = pointF;
        PointF pointF6 = pointF2;
        float f7 = f;
        float f8 = f2;
        double d2 = d;
        PointF pointF7 = new PointF();
        PointF pointF8 = new PointF();
        PointF pointF9 = new PointF();
        PointF pointF10 = new PointF();
        float atan = (float) Math.atan(d);
        float a2 = ((a(pointF, pointF2) - f7) - f8) / 2.0f;
        float asin = (float) Math.asin((double) (f7 / (f7 + a2)));
        float acos = (float) Math.acos((double) (f8 / (a2 + f8)));
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            double d3 = (double) f7;
            pointF4 = pointF9;
            pointF3 = pointF10;
            double d4 = (double) asin;
            double cos = Math.cos(d4);
            Double.isNaN(d3);
            float f9 = (float) (d3 * cos);
            double sin = Math.sin(d4);
            Double.isNaN(d3);
            float f10 = (float) (d3 * sin);
            if (pointF5.y - pointF6.y > 0.0f) {
                pointF7.set(pointF5.x - f9, pointF5.y - f10);
            } else {
                pointF7.set(pointF5.x - f9, pointF5.y + f10);
            }
        } else {
            double d5 = (double) f7;
            double d6 = (double) asin;
            Double.isNaN(d6);
            double d7 = 1.5707963267948966d - d6;
            double d8 = (double) atan;
            Double.isNaN(d8);
            double d9 = d7 - d8;
            double cos2 = Math.cos(d9);
            Double.isNaN(d5);
            float f11 = (float) (cos2 * d5);
            double sin2 = Math.sin(d9);
            Double.isNaN(d5);
            pointF7.set(pointF5.x - f11, pointF5.y + ((float) (d5 * sin2)));
            pointF4 = pointF9;
            pointF3 = pointF10;
        }
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            double d10 = (double) f8;
            double d11 = (double) acos;
            double sin3 = Math.sin(d11);
            Double.isNaN(d10);
            float f12 = (float) (sin3 * d10);
            double cos3 = Math.cos(d11);
            Double.isNaN(d10);
            float f13 = (float) (d10 * cos3);
            if (pointF5.y - pointF6.y > 0.0f) {
                pointF8.set(pointF6.x - f12, pointF6.y + f13);
            } else {
                pointF8.set(pointF6.x - f12, pointF6.y - f13);
            }
        } else {
            double d12 = (double) f8;
            double d13 = (double) (acos + atan);
            double cos4 = Math.cos(d13);
            Double.isNaN(d12);
            double sin4 = Math.sin(d13);
            Double.isNaN(d12);
            pointF8.set(pointF6.x + ((float) (cos4 * d12)), pointF6.y + ((float) (d12 * sin4)));
        }
        float[] fArr = {pointF7.x - pointF6.x, pointF7.y - pointF6.y};
        float[] fArr2 = {pointF8.x - pointF6.x, pointF8.y - pointF6.y};
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            f3 = fArr[1];
            f5 = fArr2[1];
            float f14 = -fArr[0];
            f4 = -fArr2[0];
            f6 = f14;
        } else {
            double d14 = (double) fArr[0];
            double d15 = d;
            double d16 = d15 * 2.0d;
            double d17 = (double) fArr[0];
            Double.isNaN(d17);
            double d18 = (double) fArr[1];
            Double.isNaN(d18);
            Double.isNaN(d14);
            f6 = (float) (d14 - ((((d17 * d15) + (d18 * -1.0d)) * d16) / (Math.pow(d15, 2.0d) + Math.pow(-1.0d, 2.0d))));
            double d19 = (double) fArr[1];
            double d20 = (double) fArr[0];
            Double.isNaN(d20);
            double d21 = (double) fArr[1];
            Double.isNaN(d21);
            Double.isNaN(d19);
            float pow = (float) (d19 - ((((d20 * d15) + (d21 * -1.0d)) * -2.0d) / (Math.pow(d15, 2.0d) + Math.pow(-1.0d, 2.0d))));
            double d22 = (double) fArr2[0];
            double d23 = (double) fArr2[0];
            Double.isNaN(d23);
            double d24 = (double) fArr2[1];
            Double.isNaN(d24);
            Double.isNaN(d22);
            f4 = (float) (d22 - ((d16 * ((d23 * d15) + (d24 * -1.0d))) / (Math.pow(d15, 2.0d) + Math.pow(-1.0d, 2.0d))));
            double d25 = (double) fArr2[1];
            double d26 = (double) fArr2[0];
            Double.isNaN(d26);
            f3 = pow;
            double d27 = (double) fArr2[1];
            Double.isNaN(d27);
            Double.isNaN(d25);
            f5 = (float) (d25 - ((((d26 * d15) + (d27 * -1.0d)) * -2.0d) / (Math.pow(d15, 2.0d) + Math.pow(-1.0d, 2.0d))));
        }
        PointF pointF11 = pointF4;
        pointF11.set(pointF6.x + f6, pointF6.y + f3);
        float f15 = pointF6.y + f5;
        PointF pointF12 = pointF3;
        pointF12.set(pointF6.x + f4, f15);
        return new PointF[]{pointF7, pointF8, pointF11, pointF12};
    }
}
