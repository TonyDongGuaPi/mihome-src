package com.xiaomi.smarthome.library.common.widget.slidinglayer.transformer;

import android.view.View;
import com.xiaomi.smarthome.library.common.widget.slidinglayer.LayerTransformer;
import com.xiaomi.smarthome.library.common.widget.slidinglayer.utils.Transitions;

public final class SlideJoyTransformer extends LayerTransformer {

    /* renamed from: a  reason: collision with root package name */
    private final float[] f19075a = {0.7f, 0.9f, 1.0f};
    private float[] b;
    private float[] c;

    public void a(View view, float f, float f2) {
    }

    /* access modifiers changed from: protected */
    public void a(View view, int i) {
        float[] a2 = a(-4.75f, i);
        this.b = new float[]{0.0f, a2[0], 0.0f};
        this.c = new float[]{0.0f, a2[1], 0.0f};
        int[] b2 = b(view, i);
        view.setPivotX((float) b2[0]);
        view.setPivotY((float) b2[1]);
    }

    /* access modifiers changed from: protected */
    public void a(View view, float f, float f2, int i) {
        float max = Math.max(f, f2);
        float b2 = Transitions.b(max, new float[]{0.9f, 1.0f});
        view.setScaleX(b2);
        view.setScaleY(b2);
        view.setRotationX(Transitions.a(max, this.f19075a, this.b));
        view.setRotationY(Transitions.a(max, this.f19075a, this.c));
    }

    private float[] a(float f, int i) {
        switch (i) {
            case -4:
                return new float[]{f, 0.0f};
            case -3:
                return new float[]{-f, 0.0f};
            case -2:
                return new float[]{0.0f, f};
            case -1:
                return new float[]{0.0f, -f};
            default:
                return new float[]{0.0f, 0.0f};
        }
    }

    private int[] b(View view, int i) {
        switch (i) {
            case -4:
                return new int[]{view.getMeasuredWidth() / 2, view.getMeasuredHeight()};
            case -3:
                return new int[]{view.getMeasuredWidth() / 2, 0};
            case -2:
                return new int[]{0, view.getMeasuredHeight() / 2};
            case -1:
                return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight() / 2};
            default:
                return new int[]{0, 0};
        }
    }
}
