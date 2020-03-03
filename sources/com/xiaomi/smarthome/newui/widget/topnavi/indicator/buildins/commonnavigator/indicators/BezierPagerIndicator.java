package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.FragmentContainerHelper;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.ArgbEvaluatorHolder;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.UIUtil;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.model.PositionData;
import java.util.Arrays;
import java.util.List;

public class BezierPagerIndicator extends View implements IPagerIndicator {

    /* renamed from: a  reason: collision with root package name */
    private List<PositionData> f20931a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private Paint i;
    private Path j = new Path();
    private List<Integer> k;
    private Interpolator l = new AccelerateInterpolator();
    private Interpolator m = new DecelerateInterpolator();

    public void onPageScrollStateChanged(int i2) {
    }

    public void onPageSelected(int i2) {
    }

    public BezierPagerIndicator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.i = new Paint(1);
        this.i.setStyle(Paint.Style.FILL);
        this.g = (float) UIUtil.a(context, 3.5d);
        this.h = (float) UIUtil.a(context, 2.0d);
        this.f = (float) UIUtil.a(context, 1.5d);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(this.c, (((float) getHeight()) - this.f) - this.g, this.b, this.i);
        canvas.drawCircle(this.e, (((float) getHeight()) - this.f) - this.g, this.d, this.i);
        a(canvas);
    }

    private void a(Canvas canvas) {
        this.j.reset();
        float height = (((float) getHeight()) - this.f) - this.g;
        this.j.moveTo(this.e, height);
        this.j.lineTo(this.e, height - this.d);
        this.j.quadTo(this.e + ((this.c - this.e) / 2.0f), height, this.c, height - this.b);
        this.j.lineTo(this.c, this.b + height);
        this.j.quadTo(this.e + ((this.c - this.e) / 2.0f), height, this.e, this.d + height);
        this.j.close();
        canvas.drawPath(this.j, this.i);
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.f20931a != null && !this.f20931a.isEmpty()) {
            if (this.k != null && this.k.size() > 0) {
                this.i.setColor(ArgbEvaluatorHolder.a(f2, this.k.get(Math.abs(i2) % this.k.size()).intValue(), this.k.get(Math.abs(i2 + 1) % this.k.size()).intValue()));
            }
            PositionData a2 = FragmentContainerHelper.a(this.f20931a, i2);
            PositionData a3 = FragmentContainerHelper.a(this.f20931a, i2 + 1);
            float f3 = (float) (a2.f20936a + ((a2.c - a2.f20936a) / 2));
            float f4 = ((float) (a3.f20936a + ((a3.c - a3.f20936a) / 2))) - f3;
            this.c = (this.l.getInterpolation(f2) * f4) + f3;
            this.e = f3 + (f4 * this.m.getInterpolation(f2));
            this.b = this.g + ((this.h - this.g) * this.m.getInterpolation(f2));
            this.d = this.h + ((this.g - this.h) * this.l.getInterpolation(f2));
            invalidate();
        }
    }

    public void onPositionDataProvide(List<PositionData> list) {
        this.f20931a = list;
    }

    public float getMaxCircleRadius() {
        return this.g;
    }

    public void setMaxCircleRadius(float f2) {
        this.g = f2;
    }

    public float getMinCircleRadius() {
        return this.h;
    }

    public void setMinCircleRadius(float f2) {
        this.h = f2;
    }

    public float getYOffset() {
        return this.f;
    }

    public void setYOffset(float f2) {
        this.f = f2;
    }

    public void setColors(Integer... numArr) {
        this.k = Arrays.asList(numArr);
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.l = interpolator;
        if (this.l == null) {
            this.l = new AccelerateInterpolator();
        }
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.m = interpolator;
        if (this.m == null) {
            this.m = new DecelerateInterpolator();
        }
    }
}
