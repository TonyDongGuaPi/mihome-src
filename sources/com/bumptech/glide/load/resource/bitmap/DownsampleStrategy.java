package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.Option;

public abstract class DownsampleStrategy {

    /* renamed from: a  reason: collision with root package name */
    public static final DownsampleStrategy f4999a = new FitCenter();
    public static final DownsampleStrategy b = new CenterOutside();
    public static final DownsampleStrategy c = new AtLeast();
    public static final DownsampleStrategy d = new AtMost();
    public static final DownsampleStrategy e = new CenterInside();
    public static final DownsampleStrategy f = new None();
    public static final DownsampleStrategy g = b;
    public static final Option<DownsampleStrategy> h = Option.a("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", g);

    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    public abstract float a(int i, int i2, int i3, int i4);

    public abstract SampleSizeRounding b(int i, int i2, int i3, int i4);

    private static class FitCenter extends DownsampleStrategy {
        FitCenter() {
        }

        public float a(int i, int i2, int i3, int i4) {
            return Math.min(((float) i3) / ((float) i), ((float) i4) / ((float) i2));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class CenterOutside extends DownsampleStrategy {
        CenterOutside() {
        }

        public float a(int i, int i2, int i3, int i4) {
            return Math.max(((float) i3) / ((float) i), ((float) i4) / ((float) i2));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class AtLeast extends DownsampleStrategy {
        AtLeast() {
        }

        public float a(int i, int i2, int i3, int i4) {
            int min = Math.min(i2 / i4, i / i3);
            if (min == 0) {
                return 1.0f;
            }
            return 1.0f / ((float) Integer.highestOneBit(min));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class AtMost extends DownsampleStrategy {
        AtMost() {
        }

        public float a(int i, int i2, int i3, int i4) {
            int ceil = (int) Math.ceil((double) Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3)));
            int i5 = 1;
            int max = Math.max(1, Integer.highestOneBit(ceil));
            if (max >= ceil) {
                i5 = 0;
            }
            return 1.0f / ((float) (max << i5));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.MEMORY;
        }
    }

    private static class None extends DownsampleStrategy {
        public float a(int i, int i2, int i3, int i4) {
            return 1.0f;
        }

        None() {
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class CenterInside extends DownsampleStrategy {
        CenterInside() {
        }

        public float a(int i, int i2, int i3, int i4) {
            return Math.min(1.0f, f4999a.a(i, i2, i3, i4));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }
}
