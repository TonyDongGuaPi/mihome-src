package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.mapsweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

public class MapView extends View {

    /* renamed from: a  reason: collision with root package name */
    private Context f17619a;
    private Paint b;
    private Path c;
    private int d;
    private float e;
    private float f;
    private boolean g;

    public void drawHistoryPoints(List<MapPoint> list) {
    }

    public void drawNewPoint(MapPoint mapPoint) {
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
    }

    public MapView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = Color.parseColor("#ffffff");
        this.g = true;
        a(context);
    }

    private void a(Context context) {
        this.f17619a = context;
        a();
    }

    private void a() {
        this.b = new Paint();
        this.b.setColor(this.d);
        this.b.setStrokeWidth(0.2f);
        this.b.setStyle(Paint.Style.STROKE);
        this.b.setAntiAlias(true);
        this.c = new Path();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.e = (float) View.MeasureSpec.getSize(i);
        this.f = (float) View.MeasureSpec.getSize(i2);
        super.onMeasure(i, i2);
    }

    public float getViewWidth() {
        return this.e;
    }

    public float getViewHeight() {
        return this.f;
    }

    public void setViewWidth(float f2) {
        this.e = f2;
    }

    public void setViewHeight(float f2) {
        this.f = f2;
    }

    public void setLineColor(int i) {
        this.d = i;
        if (this.b != null) {
            this.b.setColor(this.d);
        }
    }

    public void cleanPath() {
        if (this.c != null) {
            this.c.reset();
            invalidate();
        }
        this.g = true;
    }
}
