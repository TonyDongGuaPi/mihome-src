package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.FragmentContainerHelper;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.UIUtil;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.model.PositionData;
import java.util.List;

public class TriangularPagerIndicator extends View implements IPagerIndicator {

    /* renamed from: a  reason: collision with root package name */
    private List<PositionData> f20934a;
    private Paint b;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private float h;
    private Path i = new Path();
    private Interpolator j = new LinearInterpolator();
    private float k;

    public void onPageScrollStateChanged(int i2) {
    }

    public void onPageSelected(int i2) {
    }

    public TriangularPagerIndicator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.b = new Paint(1);
        this.b.setStyle(Paint.Style.FILL);
        this.c = UIUtil.a(context, 3.0d);
        this.f = UIUtil.a(context, 14.0d);
        this.e = UIUtil.a(context, 8.0d);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.b.setColor(this.d);
        if (this.g) {
            canvas.drawRect(0.0f, (((float) getHeight()) - this.h) - ((float) this.e), (float) getWidth(), ((((float) getHeight()) - this.h) - ((float) this.e)) + ((float) this.c), this.b);
        } else {
            canvas.drawRect(0.0f, ((float) (getHeight() - this.c)) - this.h, (float) getWidth(), ((float) getHeight()) - this.h, this.b);
        }
        this.i.reset();
        if (this.g) {
            this.i.moveTo(this.k - ((float) (this.f / 2)), (((float) getHeight()) - this.h) - ((float) this.e));
            this.i.lineTo(this.k, ((float) getHeight()) - this.h);
            this.i.lineTo(this.k + ((float) (this.f / 2)), (((float) getHeight()) - this.h) - ((float) this.e));
        } else {
            this.i.moveTo(this.k - ((float) (this.f / 2)), ((float) getHeight()) - this.h);
            this.i.lineTo(this.k, ((float) (getHeight() - this.e)) - this.h);
            this.i.lineTo(this.k + ((float) (this.f / 2)), ((float) getHeight()) - this.h);
        }
        this.i.close();
        canvas.drawPath(this.i, this.b);
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.f20934a != null && !this.f20934a.isEmpty()) {
            PositionData a2 = FragmentContainerHelper.a(this.f20934a, i2);
            PositionData a3 = FragmentContainerHelper.a(this.f20934a, i2 + 1);
            float f3 = (float) (a2.f20936a + ((a2.c - a2.f20936a) / 2));
            this.k = f3 + ((((float) (a3.f20936a + ((a3.c - a3.f20936a) / 2))) - f3) * this.j.getInterpolation(f2));
            invalidate();
        }
    }

    public void onPositionDataProvide(List<PositionData> list) {
        this.f20934a = list;
    }

    public int getLineHeight() {
        return this.c;
    }

    public void setLineHeight(int i2) {
        this.c = i2;
    }

    public int getLineColor() {
        return this.d;
    }

    public void setLineColor(int i2) {
        this.d = i2;
    }

    public int getTriangleHeight() {
        return this.e;
    }

    public void setTriangleHeight(int i2) {
        this.e = i2;
    }

    public int getTriangleWidth() {
        return this.f;
    }

    public void setTriangleWidth(int i2) {
        this.f = i2;
    }

    public Interpolator getStartInterpolator() {
        return this.j;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.j = interpolator;
        if (this.j == null) {
            this.j = new LinearInterpolator();
        }
    }

    public boolean isReverse() {
        return this.g;
    }

    public void setReverse(boolean z) {
        this.g = z;
    }

    public float getYOffset() {
        return this.h;
    }

    public void setYOffset(float f2) {
        this.h = f2;
    }
}
