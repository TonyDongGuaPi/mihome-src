package com.mi.blockcanary.ui;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

final class BlockCanaryUi {

    /* renamed from: a  reason: collision with root package name */
    static final int f6756a = -4539718;
    static final int b = -8083771;
    static final int c = -5155506;
    static final PorterDuffXfermode d = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

    private BlockCanaryUi() {
        throw new AssertionError();
    }

    static float a(float f, Resources resources) {
        return resources.getDisplayMetrics().density * f;
    }
}
