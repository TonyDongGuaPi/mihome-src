package com.xiaomi.smarthome.library.common.widget.slidinglayer.transformer;

import android.view.View;
import com.xiaomi.smarthome.library.common.widget.slidinglayer.LayerTransformer;

public final class AlphaTransformer extends LayerTransformer {

    /* renamed from: a  reason: collision with root package name */
    private static final int f19073a = 1;
    private final float b;

    public AlphaTransformer() {
        this(1.0f);
    }

    public AlphaTransformer(float f) {
        this.b = f;
    }

    public void a(View view, float f, float f2) {
        view.setAlpha(Math.max(0.0f, Math.min(1.0f, Math.max(f, f2) * this.b)));
    }
}
