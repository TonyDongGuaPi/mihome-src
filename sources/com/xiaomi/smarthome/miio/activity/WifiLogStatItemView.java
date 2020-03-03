package com.xiaomi.smarthome.miio.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

public class WifiLogStatItemView extends View {

    /* renamed from: a  reason: collision with root package name */
    private Paint f11834a;
    private int b = 0;
    private int c = 0;
    private boolean d = false;
    private ArrayList<Time> e = new ArrayList<>();

    public WifiLogStatItemView(Context context) {
        super(context);
        init();
    }

    public WifiLogStatItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public WifiLogStatItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.f11834a = new Paint(1);
        this.f11834a.setAntiAlias(true);
        this.f11834a.setStyle(Paint.Style.FILL);
        this.f11834a.setColor(-251658241);
        this.f11834a.setStrokeWidth(1.0f);
    }

    public void setItemTime(Time time) {
        this.e.add(time);
    }

    public Time getItemTimeAt(int i) {
        return this.e.get(i);
    }

    public Time getItemTime(boolean z) {
        if (!z) {
            return this.e.get(this.b);
        }
        return this.e.get(this.c);
    }

    public Time getMaxTime() {
        return this.e.get(this.c);
    }

    public Time getMinTime() {
        return this.e.get(this.b);
    }

    public int getTimeCount() {
        return this.e.size();
    }

    public void setFocusPoint(boolean z) {
        this.d = z;
        invalidate();
    }

    public void setTimeEnd() {
        if (this.e.size() != 0) {
            int i = 1440;
            int i2 = 0;
            for (int i3 = 0; i3 < this.e.size(); i3++) {
                Time time = this.e.get(i3);
                int i4 = (time.hour * 60) + time.minute;
                if (i > i4) {
                    this.b = i3;
                    i = i4;
                }
                if (i2 < i4) {
                    this.c = i3;
                    i2 = i4;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawPoint(Canvas canvas, int i, boolean z) {
        int height = getHeight();
        int width = getWidth();
        float f = getContext().getResources().getDisplayMetrics().density;
        this.f11834a.setStyle(Paint.Style.FILL);
        this.f11834a.setColor(-251658241);
        double d2 = (double) (i * height);
        Double.isNaN(d2);
        float f2 = 5.0f * f;
        double d3 = (double) height;
        Double.isNaN(d3);
        float f3 = (float) (d3 - (d2 / 1440.0d));
        if (z && this.d) {
            this.f11834a.setStyle(Paint.Style.STROKE);
            this.f11834a.setColor(1090519039);
            this.f11834a.setStrokeWidth(f * 1.0f);
            canvas.drawCircle((float) (width / 2), f3, (f * 3.0f) + f2, this.f11834a);
        }
        this.f11834a.setStrokeWidth(1.0f);
        this.f11834a.setStyle(Paint.Style.FILL);
        this.f11834a.setColor(-1);
        float f4 = (float) (width / 2);
        canvas.drawCircle(f4, f3, f2, this.f11834a);
        if (!z) {
            this.f11834a.setColor(-10779402);
            canvas.drawCircle(f4, f3, f2 - 1.0f, this.f11834a);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.e.size() != 0) {
            Time time = this.e.get(this.c);
            int i = (time.hour * 60) + time.minute;
            if (i != 0) {
                int height = getHeight();
                int width = getWidth();
                double d2 = (double) (i * height);
                Double.isNaN(d2);
                this.f11834a.setAntiAlias(true);
                this.f11834a.setStyle(Paint.Style.FILL);
                this.f11834a.setColor(-251658241);
                this.f11834a.setStrokeWidth(1.0f);
                float f = (float) (width / 2);
                float f2 = (float) height;
                double d3 = (double) height;
                Double.isNaN(d3);
                canvas.drawLine(f, f2, f, (float) (d3 - (d2 / 1440.0d)), this.f11834a);
                int i2 = 0;
                int i3 = -1;
                while (i2 < this.e.size()) {
                    Time time2 = this.e.get(i2);
                    int i4 = (time2.hour * 60) + time2.minute;
                    if (i3 <= 0 || Math.abs(i3 - i4) >= 30 || i2 <= 0 || i2 >= this.e.size() - 1) {
                        drawPoint(canvas, i4, i2 == this.c);
                        i3 = i4;
                    }
                    i2++;
                }
            }
        }
    }
}
