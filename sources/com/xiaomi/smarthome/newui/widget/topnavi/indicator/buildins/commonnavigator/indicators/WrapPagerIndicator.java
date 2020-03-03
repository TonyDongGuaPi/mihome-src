package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.FragmentContainerHelper;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.UIUtil;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.model.PositionData;
import java.util.List;

public class WrapPagerIndicator extends View implements IPagerIndicator {

    /* renamed from: a  reason: collision with root package name */
    private int f20935a;
    private int b;
    private int c;
    private float d;
    private Interpolator e = new LinearInterpolator();
    private Interpolator f = new LinearInterpolator();
    private List<PositionData> g;
    private Paint h;
    private RectF i = new RectF();
    private boolean j;

    public void onPageScrollStateChanged(int i2) {
    }

    public void onPageSelected(int i2) {
    }

    public WrapPagerIndicator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.h = new Paint(1);
        this.h.setStyle(Paint.Style.FILL);
        this.f20935a = UIUtil.a(context, 6.0d);
        this.b = UIUtil.a(context, 10.0d);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.h.setColor(this.c);
        canvas.drawRoundRect(this.i, this.d, this.d, this.h);
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.g != null && !this.g.isEmpty()) {
            PositionData a2 = FragmentContainerHelper.a(this.g, i2);
            PositionData a3 = FragmentContainerHelper.a(this.g, i2 + 1);
            this.i.left = ((float) (a2.e - this.b)) + (((float) (a3.e - a2.e)) * this.f.getInterpolation(f2));
            this.i.top = (float) (a2.f - this.f20935a);
            this.i.right = ((float) (a2.g + this.b)) + (((float) (a3.g - a2.g)) * this.e.getInterpolation(f2));
            this.i.bottom = (float) (a2.h + this.f20935a);
            if (!this.j) {
                this.d = this.i.height() / 2.0f;
            }
            invalidate();
        }
    }

    public void onPositionDataProvide(List<PositionData> list) {
        this.g = list;
    }

    public Paint getPaint() {
        return this.h;
    }

    public int getVerticalPadding() {
        return this.f20935a;
    }

    public void setVerticalPadding(int i2) {
        this.f20935a = i2;
    }

    public int getHorizontalPadding() {
        return this.b;
    }

    public void setHorizontalPadding(int i2) {
        this.b = i2;
    }

    public int getFillColor() {
        return this.c;
    }

    public void setFillColor(int i2) {
        this.c = i2;
    }

    public float getRoundRadius() {
        return this.d;
    }

    public void setRoundRadius(float f2) {
        this.d = f2;
        this.j = true;
    }

    public Interpolator getStartInterpolator() {
        return this.e;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.e = interpolator;
        if (this.e == null) {
            this.e = new LinearInterpolator();
        }
    }

    public Interpolator getEndInterpolator() {
        return this.f;
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.f = interpolator;
        if (this.f == null) {
            this.f = new LinearInterpolator();
        }
    }
}
