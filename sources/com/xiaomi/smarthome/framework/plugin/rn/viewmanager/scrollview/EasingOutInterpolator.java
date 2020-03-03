package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.scrollview;

import android.animation.TimeInterpolator;

public class EasingOutInterpolator implements TimeInterpolator {
    public float getInterpolation(float f) {
        double d = (double) f;
        Double.isNaN(d);
        return (float) ((Math.cos(d * 3.141592653589793d) - 1.0d) * -0.5d);
    }
}
