package com.flipboard.bottomsheet;

import android.view.View;

public interface ViewTransformer {
    float a(float f, float f2, float f3, BottomSheetLayout bottomSheetLayout, View view);

    void b(float f, float f2, float f3, BottomSheetLayout bottomSheetLayout, View view);
}
