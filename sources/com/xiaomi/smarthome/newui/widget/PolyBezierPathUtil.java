package com.xiaomi.smarthome.newui.widget;

import android.graphics.Path;
import java.util.Collection;
import java.util.List;

public class PolyBezierPathUtil {
    public static Path a(List<EPointF> list) {
        a((Collection<EPointF>) list);
        Path path = new Path();
        int i = 0;
        EPointF ePointF = list.get(0);
        path.moveTo(ePointF.a(), ePointF.b());
        int size = list.size() - 1;
        if (size == 1) {
            EPointF ePointF2 = list.get(1);
            path.lineTo(ePointF2.a(), ePointF2.b());
        } else {
            EPointF[] a2 = a(size, list);
            while (i < size) {
                int i2 = i + 1;
                a(path, a2[i], a2[i + size], list.get(i2));
                i = i2;
            }
        }
        return path;
    }

    private static EPointF[] a(int i, List<EPointF> list) {
        int i2 = i;
        List<EPointF> list2 = list;
        int i3 = i2 * 2;
        EPointF[] ePointFArr = new EPointF[i3];
        EPointF[] b = b(i, list);
        int i4 = i2 - 1;
        Float[] a2 = a(i4);
        Float[] b2 = b(i);
        Float[] c = c(i4);
        EPointF[] ePointFArr2 = new EPointF[i2];
        Float[] fArr = new Float[i4];
        fArr[0] = Float.valueOf(c[0].floatValue() / b2[0].floatValue());
        float f = 1.0f;
        ePointFArr2[0] = b[0].a(1.0f / b2[0].floatValue());
        for (int i5 = 1; i5 < i4; i5++) {
            int i6 = i5 - 1;
            fArr[i5] = Float.valueOf(c[i5].floatValue() / (b2[i5].floatValue() - (a2[i6].floatValue() * fArr[i6].floatValue())));
        }
        int i7 = 1;
        while (i7 < i2) {
            int i8 = i7 - 1;
            ePointFArr2[i7] = b[i7].b(ePointFArr2[i8].a(a2[i8].floatValue())).a(f / (b2[i7].floatValue() - (a2[i8].floatValue() * fArr[i8].floatValue())));
            i7++;
            f = 1.0f;
        }
        ePointFArr[i4] = ePointFArr2[i4];
        for (int i9 = i2 - 2; i9 >= 0; i9--) {
            ePointFArr[i9] = ePointFArr2[i9].b(fArr[i9].floatValue(), ePointFArr[i9 + 1]);
        }
        int i10 = 0;
        while (i10 < i4) {
            int i11 = i2 + i10;
            i10++;
            ePointFArr[i11] = list2.get(i10).a(2.0f).b(ePointFArr[i10]);
        }
        ePointFArr[i3 - 1] = list2.get(i2).a(ePointFArr[i4]).a(0.5f);
        return ePointFArr;
    }

    private static EPointF[] b(int i, List<EPointF> list) {
        EPointF[] ePointFArr = new EPointF[i];
        ePointFArr[0] = list.get(0).a(2.0f, list.get(1));
        int i2 = 1;
        while (true) {
            int i3 = i - 1;
            if (i2 < i3) {
                int i4 = i2 + 1;
                ePointFArr[i2] = list.get(i2).a(2.0f).a(list.get(i4)).a(2.0f);
                i2 = i4;
            } else {
                ePointFArr[ePointFArr.length - 1] = list.get(i3).a(8.0f).a(list.get(i));
                return ePointFArr;
            }
        }
    }

    private static Float[] a(int i) {
        Float[] fArr = new Float[i];
        for (int i2 = 0; i2 < fArr.length - 1; i2++) {
            fArr[i2] = Float.valueOf(1.0f);
        }
        fArr[fArr.length - 1] = Float.valueOf(2.0f);
        return fArr;
    }

    private static Float[] b(int i) {
        Float[] fArr = new Float[i];
        fArr[0] = Float.valueOf(2.0f);
        for (int i2 = 1; i2 < fArr.length - 1; i2++) {
            fArr[i2] = Float.valueOf(4.0f);
        }
        fArr[fArr.length - 1] = Float.valueOf(7.0f);
        return fArr;
    }

    private static Float[] c(int i) {
        Float[] fArr = new Float[i];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = Float.valueOf(1.0f);
        }
        return fArr;
    }

    private static void a(Path path, EPointF ePointF, EPointF ePointF2, EPointF ePointF3) {
        path.cubicTo(ePointF.a(), ePointF.b(), ePointF2.a(), ePointF2.b(), ePointF3.a(), ePointF3.b());
    }

    private static void a(Collection<EPointF> collection) {
        if (collection.size() < 2) {
            throw new IllegalArgumentException("Collection must contain at least two knots");
        }
    }
}
