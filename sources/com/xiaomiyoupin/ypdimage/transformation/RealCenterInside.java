package com.xiaomiyoupin.ypdimage.transformation;

import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

public class RealCenterInside extends DownsampleStrategy {
    public float a(int i, int i2, int i3, int i4) {
        if (i > i3 || i2 > i4) {
            return Math.min(((float) i3) / ((float) i), ((float) i4) / ((float) i2));
        }
        return 1.0f;
    }

    public DownsampleStrategy.SampleSizeRounding b(int i, int i2, int i3, int i4) {
        return DownsampleStrategy.SampleSizeRounding.QUALITY;
    }
}
