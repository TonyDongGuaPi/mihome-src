package com.transitionseverywhere;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;

public abstract class PathMotion {

    /* renamed from: a  reason: collision with root package name */
    public static final PathMotion f9461a = new PathMotion() {
        public Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    };

    public abstract Path a(float f, float f2, float f3, float f4);

    public PathMotion() {
    }

    public PathMotion(Context context, AttributeSet attributeSet) {
    }
}
