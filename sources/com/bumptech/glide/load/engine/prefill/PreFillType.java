package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.util.Preconditions;
import com.taobao.weex.el.parse.Operators;

public final class PreFillType {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final Bitmap.Config f4930a = Bitmap.Config.RGB_565;
    private final int b;
    private final int c;
    private final Bitmap.Config d;
    private final int e;

    PreFillType(int i, int i2, Bitmap.Config config, int i3) {
        this.d = (Bitmap.Config) Preconditions.a(config, "Config must not be null");
        this.b = i;
        this.c = i2;
        this.e = i3;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public Bitmap.Config c() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PreFillType)) {
            return false;
        }
        PreFillType preFillType = (PreFillType) obj;
        if (this.c == preFillType.c && this.b == preFillType.b && this.e == preFillType.e && this.d == preFillType.d) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.b * 31) + this.c) * 31) + this.d.hashCode()) * 31) + this.e;
    }

    public String toString() {
        return "PreFillSize{width=" + this.b + ", height=" + this.c + ", config=" + this.d + ", weight=" + this.e + Operators.BLOCK_END;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final int f4931a;
        private final int b;
        private Bitmap.Config c;
        private int d;

        public Builder(int i) {
            this(i, i);
        }

        public Builder(int i, int i2) {
            this.d = 1;
            if (i <= 0) {
                throw new IllegalArgumentException("Width must be > 0");
            } else if (i2 > 0) {
                this.f4931a = i;
                this.b = i2;
            } else {
                throw new IllegalArgumentException("Height must be > 0");
            }
        }

        public Builder a(@Nullable Bitmap.Config config) {
            this.c = config;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Bitmap.Config a() {
            return this.c;
        }

        public Builder a(int i) {
            if (i > 0) {
                this.d = i;
                return this;
            }
            throw new IllegalArgumentException("Weight must be > 0");
        }

        /* access modifiers changed from: package-private */
        public PreFillType b() {
            return new PreFillType(this.f4931a, this.b, this.c, this.d);
        }
    }
}
