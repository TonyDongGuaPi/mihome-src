package com.miui.supportlite.internal.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ActivityWrapperLayout extends LinearLayout {
    public ActivityWrapperLayout(Context context) {
        super(context);
    }

    public ActivityWrapperLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivityWrapperLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public final boolean fitSystemWindows(Rect rect) {
        if (Build.VERSION.SDK_INT >= 19) {
            rect.left = 0;
            rect.top = 0;
            rect.right = 0;
        }
        return super.fitSystemWindows(rect);
    }
}
