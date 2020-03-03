package com.scwang.smartrefresh.layout.internal;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public abstract class PaintDrawable extends Drawable {
    protected Paint m = new Paint();

    public int getOpacity() {
        return -3;
    }

    protected PaintDrawable() {
        this.m.setStyle(Paint.Style.FILL);
        this.m.setAntiAlias(true);
        this.m.setColor(-5592406);
    }

    public void c(int i) {
        this.m.setColor(i);
    }

    public void setAlpha(int i) {
        this.m.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.m.setColorFilter(colorFilter);
    }
}
