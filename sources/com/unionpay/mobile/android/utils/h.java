package com.unionpay.mobile.android.utils;

import android.content.res.ColorStateList;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import java.util.Arrays;

public final class h {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f9741a = {16842910};
    private static final int[] b = {16842908};
    private static final int[] c = {-16842910};
    private static final int[] d = {16842910, 16842919};
    private static final int[] e = {16842910, 16842908};
    private static final int[] f = {16842910, 16842912};

    public static ColorStateList a(int i, int i2) {
        return a(i, i2, i2, i);
    }

    public static ColorStateList a(int i, int i2, int i3, int i4) {
        int[] iArr = {i2, i3, i, i3, i4, i4};
        return new ColorStateList(new int[][]{new int[]{16842919, 16842910}, new int[]{16842910, 16842908}, new int[]{16842910}, new int[]{16842908}, new int[]{16842909}, new int[0]}, iArr);
    }

    public static Drawable a(int i, int[] iArr, float[] fArr, float f2, float f3, float f4, float f5) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(a(i, 18.0f), (RectF) null, (float[]) null));
        shapeDrawable.getPaint().setShader(new LinearGradient(f2, f3, f4, f5, iArr, fArr, Shader.TileMode.CLAMP));
        return shapeDrawable;
    }

    public static Drawable a(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(d, drawable2);
        stateListDrawable.addState(e, drawable2);
        if (drawable4 != null) {
            stateListDrawable.addState(f, drawable4);
        }
        stateListDrawable.addState(f9741a, drawable);
        stateListDrawable.addState(b, drawable2);
        if (drawable3 != null) {
            stateListDrawable.addState(c, drawable3);
        }
        return stateListDrawable;
    }

    public static ShapeDrawable a(int i, int i2, float f2) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(a(i2, f2), (RectF) null, (float[]) null));
        shapeDrawable.getPaint().setStrokeWidth(1.0f);
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        return shapeDrawable;
    }

    private static float[] a(int i, float f2) {
        float[] fArr = new float[8];
        Arrays.fill(fArr, 0.0f);
        if (i == 0) {
            return fArr;
        }
        if ((i & 1) != 0) {
            fArr[1] = f2;
            fArr[0] = f2;
        }
        if ((i & 2) != 0) {
            fArr[3] = f2;
            fArr[2] = f2;
        }
        if ((i & 4) != 0) {
            fArr[5] = f2;
            fArr[4] = f2;
        }
        if ((i & 8) != 0) {
            fArr[7] = f2;
            fArr[6] = f2;
        }
        return fArr;
    }
}
