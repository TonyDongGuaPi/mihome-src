package com.xiaomi.smarthome.library.common.widget.slidinglayer;

import android.view.View;

public abstract class LayerTransformer {
    public abstract void a(View view, float f, float f2);

    /* access modifiers changed from: protected */
    public void a(View view, int i) {
    }

    /* access modifiers changed from: protected */
    public void a(View view, float f, float f2, int i) {
        a(view, f, f2);
    }
}
