package com.xiaomi.zxing;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.common.detector.MathUtils;

public class ResultPoint {

    /* renamed from: a  reason: collision with root package name */
    private final float f1632a;
    private final float b;

    public ResultPoint(float f, float f2) {
        this.f1632a = f;
        this.b = f2;
    }

    public final float a() {
        return this.f1632a;
    }

    public final float b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ResultPoint)) {
            return false;
        }
        ResultPoint resultPoint = (ResultPoint) obj;
        if (this.f1632a == resultPoint.f1632a && this.b == resultPoint.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f1632a) * 31) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(25);
        sb.append(Operators.BRACKET_START);
        sb.append(this.f1632a);
        sb.append(',');
        sb.append(this.b);
        sb.append(Operators.BRACKET_END);
        return sb.toString();
    }

    public static void a(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        float a2 = a(resultPointArr[0], resultPointArr[1]);
        float a3 = a(resultPointArr[1], resultPointArr[2]);
        float a4 = a(resultPointArr[0], resultPointArr[2]);
        if (a3 >= a2 && a3 >= a4) {
            resultPoint3 = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint = resultPointArr[2];
        } else if (a4 < a3 || a4 < a2) {
            resultPoint3 = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint = resultPointArr[1];
        } else {
            resultPoint3 = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint = resultPointArr[2];
        }
        if (a(resultPoint2, resultPoint3, resultPoint) < 0.0f) {
            ResultPoint resultPoint4 = resultPoint;
            resultPoint = resultPoint2;
            resultPoint2 = resultPoint4;
        }
        resultPointArr[0] = resultPoint2;
        resultPointArr[1] = resultPoint3;
        resultPointArr[2] = resultPoint;
    }

    public static float a(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.a(resultPoint.f1632a, resultPoint.b, resultPoint2.f1632a, resultPoint2.b);
    }

    private static float a(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        float f = resultPoint2.f1632a;
        float f2 = resultPoint2.b;
        return ((resultPoint3.f1632a - f) * (resultPoint.b - f2)) - ((resultPoint3.b - f2) * (resultPoint.f1632a - f));
    }
}
