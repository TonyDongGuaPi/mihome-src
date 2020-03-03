package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.mapsweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapBackgroundView extends View {
    private static final float g = 4.0f;

    /* renamed from: a  reason: collision with root package name */
    private Context f17615a;
    private Paint b;
    private int c;
    private List<MapPoint> d;
    private Paint e;
    private int f;
    private float h;
    private HashMap<String, MapPoint> i;
    private HashMap<String, MapPoint> j;

    public MapBackgroundView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public MapBackgroundView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MapBackgroundView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.c = Color.parseColor("#468ad6");
        this.f = Color.parseColor("#333333");
        this.h = g;
        this.i = new HashMap<>();
        this.j = new HashMap<>();
        a(context);
    }

    private void a(Context context) {
        this.f17615a = context;
        a();
        b();
    }

    private void a() {
        this.d = new ArrayList();
    }

    private void b() {
        this.b = new Paint();
        this.b.setColor(this.c);
        this.b.setStrokeWidth(this.h);
        this.b.setStyle(Paint.Style.STROKE);
        this.e = new Paint();
        this.e.setColor(this.f);
        this.e.setStrokeWidth(this.h);
        this.e.setStrokeCap(Paint.Cap.SQUARE);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        float size = ((float) View.MeasureSpec.getSize(i2)) / 256.0f;
        if (size > g) {
            this.h = size;
        }
        this.e.setStrokeWidth(this.h);
        this.b.setStrokeWidth(this.h + 1.0f);
        super.onMeasure(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        a(canvas);
        b(canvas);
    }

    private void a(Canvas canvas) {
        if (this.i != null && this.i.size() != 0) {
            int size = this.i.size() * 2;
            float[] fArr = new float[size];
            int i2 = 0;
            for (Map.Entry next : this.i.entrySet()) {
                fArr[i2] = (float) ((MapPoint) next.getValue()).f17616a;
                fArr[i2 + 1] = (float) ((MapPoint) next.getValue()).b;
                i2 += 2;
                if (i2 >= size) {
                    break;
                }
            }
            canvas.drawPoints(fArr, this.e);
        }
    }

    private void b(Canvas canvas) {
        if (this.j != null && this.j.size() != 0) {
            int size = this.j.size() * 2;
            float[] fArr = new float[size];
            int i2 = 0;
            for (Map.Entry next : this.j.entrySet()) {
                fArr[i2] = (float) ((MapPoint) next.getValue()).f17616a;
                fArr[i2 + 1] = (float) ((MapPoint) next.getValue()).b;
                i2 += 2;
                if (i2 >= size) {
                    break;
                }
            }
            canvas.drawPoints(fArr, this.b);
        }
    }

    public void setSquareColor(int i2) {
        this.f = i2;
        if (this.e != null) {
            this.e.setColor(this.f);
        }
    }

    public void setFloorColor(int i2) {
        this.c = i2;
        this.b.setColor(this.c);
    }

    public void addNewPointsToMapBackgroundView(List<MapPoint> list, List<MapPoint> list2) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.i.put(list.get(i2).c(), list.get(i2));
            }
        }
        if (list2 != null && list2.size() > 0) {
            int size2 = list2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                this.j.put(list2.get(i3).c(), list2.get(i3));
            }
        }
        invalidate();
    }

    public void cleanFloorAndSquare() {
        if (this.d != null) {
            this.d.clear();
        }
        if (this.i != null) {
            this.i.clear();
        }
        if (this.j != null) {
            this.j.clear();
        }
        invalidate();
    }
}
