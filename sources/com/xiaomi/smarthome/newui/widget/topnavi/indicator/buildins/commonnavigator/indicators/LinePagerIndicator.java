package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.FragmentContainerHelper;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.ArgbEvaluatorHolder;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.UIUtil;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.model.PositionData;
import java.util.Arrays;
import java.util.List;

public class LinePagerIndicator extends View implements IPagerIndicator {
    public static final int MODE_EXACTLY = 2;
    public static final int MODE_MATCH_EDGE = 0;
    public static final int MODE_WRAP_CONTENT = 1;

    /* renamed from: a  reason: collision with root package name */
    private int f20932a;
    private Interpolator b = new LinearInterpolator();
    private Interpolator c = new LinearInterpolator();
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private Paint i;
    private List<PositionData> j;
    private List<Integer> k;
    private RectF l = new RectF();
    private OnPageSelectedListener m;

    public interface OnPageSelectedListener {
        void a(LinePagerIndicator linePagerIndicator, int i);
    }

    public void onPageScrollStateChanged(int i2) {
    }

    public LinePagerIndicator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.i = new Paint(1);
        this.i.setPathEffect(new CornerPathEffect((float) UIUtil.a(context, 1.0d)));
        this.i.setStyle(Paint.Style.FILL);
        this.e = (float) UIUtil.a(context, 2.0d);
        this.g = (float) UIUtil.a(context, 10.0d);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawRoundRect(this.l, this.h, this.h, this.i);
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        float f3;
        float f4;
        float f5;
        float f6;
        if (this.j != null && !this.j.isEmpty()) {
            if (this.k != null && this.k.size() > 0) {
                this.i.setColor(ArgbEvaluatorHolder.a(f2, this.k.get(Math.abs(i2) % this.k.size()).intValue(), this.k.get(Math.abs(i2 + 1) % this.k.size()).intValue()));
            }
            PositionData a2 = FragmentContainerHelper.a(this.j, i2);
            PositionData a3 = FragmentContainerHelper.a(this.j, i2 + 1);
            if (this.f20932a == 0) {
                f6 = ((float) a2.f20936a) + this.f;
                f5 = ((float) a3.f20936a) + this.f;
                f3 = ((float) a2.c) - this.f;
                f4 = ((float) a3.c) - this.f;
            } else if (this.f20932a == 1) {
                f6 = ((float) a2.e) + this.f;
                f5 = ((float) a3.e) + this.f;
                f3 = ((float) a2.g) - this.f;
                f4 = ((float) a3.g) - this.f;
            } else {
                f6 = ((float) a2.f20936a) + ((((float) a2.a()) - this.g) / 2.0f);
                f5 = ((float) a3.f20936a) + ((((float) a3.a()) - this.g) / 2.0f);
                f3 = ((((float) a2.a()) + this.g) / 2.0f) + ((float) a2.f20936a);
                f4 = ((((float) a3.a()) + this.g) / 2.0f) + ((float) a3.f20936a);
            }
            this.l.left = f6 + ((f5 - f6) * this.b.getInterpolation(f2));
            this.l.right = f3 + ((f4 - f3) * this.c.getInterpolation(f2));
            this.l.top = (((float) getHeight()) - this.e) - this.d;
            this.l.bottom = ((float) getHeight()) - this.d;
            invalidate();
        }
    }

    public void onPageSelected(int i2) {
        if (this.m != null) {
            this.m.a(this, i2);
        }
    }

    public void setOnPageSelectedListener(OnPageSelectedListener onPageSelectedListener) {
        this.m = onPageSelectedListener;
    }

    public void onPositionDataProvide(List<PositionData> list) {
        this.j = list;
    }

    public float getYOffset() {
        return this.d;
    }

    public void setYOffset(float f2) {
        this.d = f2;
    }

    public float getXOffset() {
        return this.f;
    }

    public void setXOffset(float f2) {
        this.f = f2;
    }

    public float getLineHeight() {
        return this.e;
    }

    public void setLineHeight(float f2) {
        this.e = f2;
    }

    public float getLineWidth() {
        return this.g;
    }

    public void setLineWidth(float f2) {
        this.g = f2;
    }

    public float getRoundRadius() {
        return this.h;
    }

    public void setRoundRadius(float f2) {
        this.h = f2;
    }

    public int getMode() {
        return this.f20932a;
    }

    public void setMode(int i2) {
        if (i2 == 2 || i2 == 0 || i2 == 1) {
            this.f20932a = i2;
            return;
        }
        throw new IllegalArgumentException("mode " + i2 + " not supported.");
    }

    public Paint getPaint() {
        return this.i;
    }

    public List<Integer> getColors() {
        return this.k;
    }

    public void setColors(Integer... numArr) {
        this.k = Arrays.asList(numArr);
        this.i.setColor(numArr[0].intValue());
    }

    public Interpolator getStartInterpolator() {
        return this.b;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.b = interpolator;
        if (this.b == null) {
            this.b = new LinearInterpolator();
        }
    }

    public Interpolator getEndInterpolator() {
        return this.c;
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.c = interpolator;
        if (this.c == null) {
            this.c = new LinearInterpolator();
        }
    }
}
