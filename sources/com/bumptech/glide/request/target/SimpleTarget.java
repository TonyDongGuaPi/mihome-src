package com.bumptech.glide.request.target;

import android.support.annotation.NonNull;
import com.bumptech.glide.util.Util;

@Deprecated
public abstract class SimpleTarget<Z> extends BaseTarget<Z> {

    /* renamed from: a  reason: collision with root package name */
    private final int f5074a;
    private final int b;

    public void b(@NonNull SizeReadyCallback sizeReadyCallback) {
    }

    public SimpleTarget() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public SimpleTarget(int i, int i2) {
        this.f5074a = i;
        this.b = i2;
    }

    public final void a(@NonNull SizeReadyCallback sizeReadyCallback) {
        if (Util.a(this.f5074a, this.b)) {
            sizeReadyCallback.a(this.f5074a, this.b);
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + this.f5074a + " and height: " + this.b + ", either provide dimensions in the constructor or call override()");
    }
}
