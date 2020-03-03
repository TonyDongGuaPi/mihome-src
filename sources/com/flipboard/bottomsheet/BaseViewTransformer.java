package com.flipboard.bottomsheet;

import android.view.View;

public abstract class BaseViewTransformer implements ViewTransformer {

    /* renamed from: a  reason: collision with root package name */
    public static final float f5296a = 0.7f;

    public float a(float f, float f2, float f3, BottomSheetLayout bottomSheetLayout, View view) {
        return (f / f2) * 0.7f;
    }
}
