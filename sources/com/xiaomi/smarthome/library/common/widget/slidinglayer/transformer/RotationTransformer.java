package com.xiaomi.smarthome.library.common.widget.slidinglayer.transformer;

import android.view.View;
import com.xiaomi.smarthome.library.common.widget.slidinglayer.LayerTransformer;

public final class RotationTransformer extends LayerTransformer {

    /* renamed from: a  reason: collision with root package name */
    private static final int f19074a = 10;
    private final float b;
    private float c;

    public void a(View view, float f, float f2) {
    }

    public RotationTransformer() {
        this(10.0f);
    }

    public RotationTransformer(float f) {
        this.b = f;
    }

    /* access modifiers changed from: protected */
    public void a(View view, int i) {
        int[] b2 = b(view, i);
        view.setPivotX((float) b2[0]);
        int i2 = 1;
        view.setPivotY((float) b2[1]);
        float f = this.b;
        if (i == -2 || i == -3) {
            i2 = -1;
        }
        this.c = f * ((float) i2);
    }

    /* access modifiers changed from: protected */
    public void a(View view, float f, float f2, int i) {
        view.setRotation(this.c * (1.0f - Math.max(f, f2)));
    }

    private int[] b(View view, int i) {
        switch (i) {
            case -4:
                return new int[]{0, view.getMeasuredHeight()};
            case -3:
                return new int[]{0, 0};
            case -2:
                return new int[]{0, view.getMeasuredHeight()};
            case -1:
                return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
            default:
                return new int[]{0, 0};
        }
    }
}
