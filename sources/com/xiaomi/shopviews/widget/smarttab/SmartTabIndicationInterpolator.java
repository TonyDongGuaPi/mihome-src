package com.xiaomi.shopviews.widget.smarttab;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public abstract class SmartTabIndicationInterpolator {

    /* renamed from: a  reason: collision with root package name */
    static final int f13345a = 1;
    static final int b = 2;
    public static final SmartTabIndicationInterpolator c = new LinearIndicationInterpolator();
    public static final SmartTabIndicationInterpolator d = new SmartIndicationInterpolator();

    public static class LinearIndicationInterpolator extends SmartTabIndicationInterpolator {
        public float a(float f) {
            return f;
        }

        public float b(float f) {
            return f;
        }
    }

    public abstract float a(float f);

    public abstract float b(float f);

    public float c(float f) {
        return 1.0f;
    }

    public static SmartTabIndicationInterpolator a(int i) {
        switch (i) {
            case 0:
                return d;
            case 1:
                return c;
            default:
                throw new IllegalArgumentException("Unknown id: " + i);
        }
    }

    public static class SmartIndicationInterpolator extends SmartTabIndicationInterpolator {
        private static final float e = 3.0f;
        private final Interpolator f;
        private final Interpolator g;

        public SmartIndicationInterpolator() {
            this(e);
        }

        public SmartIndicationInterpolator(float f2) {
            this.f = new AccelerateInterpolator(f2);
            this.g = new DecelerateInterpolator(f2);
        }

        public float a(float f2) {
            return this.f.getInterpolation(f2);
        }

        public float b(float f2) {
            return this.g.getInterpolation(f2);
        }

        public float c(float f2) {
            return 1.0f / ((1.0f - a(f2)) + b(f2));
        }
    }
}
