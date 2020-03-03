package com.transitionseverywhere;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;

public class PatternPathMotion extends PathMotion {
    private Path b;
    private final Path c = new Path();
    private final Matrix d = new Matrix();

    public PatternPathMotion() {
        this.c.lineTo(1.0f, 0.0f);
        this.b = this.c;
    }

    public PatternPathMotion(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PatternPathMotion);
        try {
            String string = obtainStyledAttributes.getString(R.styleable.PatternPathMotion_patternPathData);
            if (string != null) {
                a(PathParser.a(string));
                return;
            }
            throw new RuntimeException("pathData must be supplied for patternPathMotion");
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public PatternPathMotion(Path path) {
        a(path);
    }

    public Path a() {
        return this.b;
    }

    public void a(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float[] fArr = new float[2];
        pathMeasure.getPosTan(pathMeasure.getLength(), fArr, (float[]) null);
        float f = fArr[0];
        float f2 = fArr[1];
        pathMeasure.getPosTan(0.0f, fArr, (float[]) null);
        float f3 = fArr[0];
        float f4 = fArr[1];
        if (f3 == f && f4 == f2) {
            throw new IllegalArgumentException("pattern must not end at the starting point");
        }
        this.d.setTranslate(-f3, -f4);
        double d2 = (double) (f - f3);
        double d3 = (double) (f2 - f4);
        float hypot = 1.0f / ((float) Math.hypot(d2, d3));
        this.d.postScale(hypot, hypot);
        this.d.postRotate((float) Math.toDegrees(-Math.atan2(d3, d2)));
        path.transform(this.d, this.c);
        this.b = path;
    }

    public Path a(float f, float f2, float f3, float f4) {
        double d2 = (double) (f3 - f);
        double d3 = (double) (f4 - f2);
        float hypot = (float) Math.hypot(d2, d3);
        double atan2 = Math.atan2(d3, d2);
        this.d.setScale(hypot, hypot);
        this.d.postRotate((float) Math.toDegrees(atan2));
        this.d.postTranslate(f, f2);
        Path path = new Path();
        this.c.transform(this.d, path);
        return path;
    }
}
