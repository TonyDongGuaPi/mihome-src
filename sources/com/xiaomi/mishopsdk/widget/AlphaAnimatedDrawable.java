package com.xiaomi.mishopsdk.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class AlphaAnimatedDrawable extends BitmapDrawable {
    private static final int PRESSED_ALPHA = 120;
    private static final int REGULAR_ALPHA = 255;

    public boolean isStateful() {
        return true;
    }

    public AlphaAnimatedDrawable(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
        setState(new int[]{16842919, 16842913, 16842910});
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        for (int i : iArr) {
            if (i == 16842919) {
                setAlpha(120);
            } else if (i == 16842913) {
                setAlpha(255);
            } else if (i == 16842910) {
                setAlpha(255);
            }
        }
        return true;
    }
}
