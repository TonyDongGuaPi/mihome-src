package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.libra.Color;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.FragmentContainerHelper;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.model.PositionData;
import java.util.List;

public class TestPagerIndicator extends View implements IPagerIndicator {

    /* renamed from: a  reason: collision with root package name */
    private Paint f20933a;
    private int b;
    private int c;
    private RectF d = new RectF();
    private RectF e = new RectF();
    private List<PositionData> f;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageSelected(int i) {
    }

    public TestPagerIndicator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.f20933a = new Paint(1);
        this.f20933a.setStyle(Paint.Style.STROKE);
        this.b = -65536;
        this.c = Color.g;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.f20933a.setColor(this.b);
        canvas.drawRect(this.d, this.f20933a);
        this.f20933a.setColor(this.c);
        canvas.drawRect(this.e, this.f20933a);
    }

    public void onPageScrolled(int i, float f2, int i2) {
        if (this.f != null && !this.f.isEmpty()) {
            PositionData a2 = FragmentContainerHelper.a(this.f, i);
            PositionData a3 = FragmentContainerHelper.a(this.f, i + 1);
            this.d.left = ((float) a2.f20936a) + (((float) (a3.f20936a - a2.f20936a)) * f2);
            this.d.top = ((float) a2.b) + (((float) (a3.b - a2.b)) * f2);
            this.d.right = ((float) a2.c) + (((float) (a3.c - a2.c)) * f2);
            this.d.bottom = ((float) a2.d) + (((float) (a3.d - a2.d)) * f2);
            this.e.left = ((float) a2.e) + (((float) (a3.e - a2.e)) * f2);
            this.e.top = ((float) a2.f) + (((float) (a3.f - a2.f)) * f2);
            this.e.right = ((float) a2.g) + (((float) (a3.g - a2.g)) * f2);
            this.e.bottom = ((float) a2.h) + (((float) (a3.h - a2.h)) * f2);
            invalidate();
        }
    }

    public void onPositionDataProvide(List<PositionData> list) {
        this.f = list;
    }

    public int getOutRectColor() {
        return this.b;
    }

    public void setOutRectColor(int i) {
        this.b = i;
    }

    public int getInnerRectColor() {
        return this.c;
    }

    public void setInnerRectColor(int i) {
        this.c = i;
    }
}
