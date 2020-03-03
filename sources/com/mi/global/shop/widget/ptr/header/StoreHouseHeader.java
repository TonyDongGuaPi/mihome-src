package com.mi.global.shop.widget.ptr.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import com.mi.global.shop.widget.ptr.PtrFrameLayout;
import com.mi.global.shop.widget.ptr.PtrUIHandler;
import com.mi.global.shop.widget.ptr.util.PtrLocalDisplay;
import java.util.ArrayList;

public class StoreHouseHeader extends View implements PtrUIHandler {

    /* renamed from: a  reason: collision with root package name */
    private int f7267a = PtrLocalDisplay.a(1.0f);
    private float b = 1.0f;
    private int c = PtrLocalDisplay.a(40.0f);
    private float d = 0.7f;
    private int e = (PtrLocalDisplay.f7270a / 2);
    private float f = 0.0f;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private float k = 0.4f;
    /* access modifiers changed from: private */
    public float l = 1.0f;
    /* access modifiers changed from: private */
    public float m = 0.4f;
    public ArrayList<StoreHouseBarItem> mItemList = new ArrayList<>();
    /* access modifiers changed from: private */
    public int n = 1000;
    /* access modifiers changed from: private */
    public int o = 1000;
    /* access modifiers changed from: private */
    public int p = 400;
    private Transformation q = new Transformation();
    private boolean r = false;
    private AniController s = new AniController();
    private int t = -1;

    private void a() {
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
    }

    public StoreHouseHeader(Context context) {
        super(context);
        a();
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void setProgress(float f2) {
        this.f = f2;
    }

    public int getLoadingAniDuration() {
        return this.n;
    }

    public void setLoadingAniDuration(int i2) {
        this.n = i2;
        this.o = i2;
    }

    public StoreHouseHeader setLineWidth(int i2) {
        this.f7267a = i2;
        for (int i3 = 0; i3 < this.mItemList.size(); i3++) {
            this.mItemList.get(i3).a(i2);
        }
        return this;
    }

    public StoreHouseHeader setTextColor(int i2) {
        this.t = i2;
        for (int i3 = 0; i3 < this.mItemList.size(); i3++) {
            this.mItemList.get(i3).b(i2);
        }
        return this;
    }

    public StoreHouseHeader setDropHeight(int i2) {
        this.c = i2;
        return this;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(getTopOffset() + this.h + getBottomOffset(), 1073741824));
        this.i = (getMeasuredWidth() - this.g) / 2;
        this.j = getTopOffset();
        this.c = getTopOffset();
    }

    private int getTopOffset() {
        return getPaddingTop() + PtrLocalDisplay.a(10.0f);
    }

    private int getBottomOffset() {
        return getPaddingBottom() + PtrLocalDisplay.a(10.0f);
    }

    public void initWithString(String str) {
        initWithString(str, 25);
    }

    public void initWithString(String str, int i2) {
        initWithPointList(StoreHousePath.a(str, ((float) i2) * 0.01f, 14));
    }

    public void initWithStringArray(int i2) {
        String[] stringArray = getResources().getStringArray(i2);
        ArrayList arrayList = new ArrayList();
        for (String split : stringArray) {
            String[] split2 = split.split(",");
            float[] fArr = new float[4];
            for (int i3 = 0; i3 < 4; i3++) {
                fArr[i3] = Float.parseFloat(split2[i3]);
            }
            arrayList.add(fArr);
        }
        initWithPointList(arrayList);
    }

    public float getScale() {
        return this.b;
    }

    public void setScale(float f2) {
        this.b = f2;
    }

    public void initWithPointList(ArrayList<float[]> arrayList) {
        boolean z = this.mItemList.size() > 0;
        this.mItemList.clear();
        int i2 = 0;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (i2 < arrayList.size()) {
            float[] fArr = arrayList.get(i2);
            PointF pointF = new PointF(((float) PtrLocalDisplay.a(fArr[0])) * this.b, ((float) PtrLocalDisplay.a(fArr[1])) * this.b);
            PointF pointF2 = new PointF(((float) PtrLocalDisplay.a(fArr[2])) * this.b, ((float) PtrLocalDisplay.a(fArr[3])) * this.b);
            float max = Math.max(Math.max(f2, pointF.x), pointF2.x);
            float max2 = Math.max(Math.max(f3, pointF.y), pointF2.y);
            StoreHouseBarItem storeHouseBarItem = new StoreHouseBarItem(i2, pointF, pointF2, this.t, this.f7267a);
            storeHouseBarItem.c(this.e);
            this.mItemList.add(storeHouseBarItem);
            i2++;
            f2 = max;
            f3 = max2;
        }
        this.g = (int) Math.ceil((double) f2);
        this.h = (int) Math.ceil((double) f3);
        if (z) {
            requestLayout();
        }
    }

    private void b() {
        this.r = true;
        this.s.a();
        invalidate();
    }

    private void c() {
        this.r = false;
        this.s.b();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f2 = this.f;
        int save = canvas.save();
        int size = this.mItemList.size();
        for (int i2 = 0; i2 < size; i2++) {
            canvas.save();
            StoreHouseBarItem storeHouseBarItem = this.mItemList.get(i2);
            float f3 = ((float) this.i) + storeHouseBarItem.f7266a.x;
            float f4 = ((float) this.j) + storeHouseBarItem.f7266a.y;
            if (this.r) {
                storeHouseBarItem.getTransformation(getDrawingTime(), this.q);
                canvas.translate(f3, f4);
            } else {
                float f5 = 0.0f;
                if (f2 == 0.0f) {
                    storeHouseBarItem.c(this.e);
                } else {
                    float f6 = ((1.0f - this.d) * ((float) i2)) / ((float) size);
                    float f7 = (1.0f - this.d) - f6;
                    if (f2 == 1.0f || f2 >= 1.0f - f7) {
                        canvas.translate(f3, f4);
                        storeHouseBarItem.a(this.k);
                    } else {
                        if (f2 > f6) {
                            f5 = Math.min(1.0f, (f2 - f6) / this.d);
                        }
                        float f8 = 1.0f - f5;
                        float f9 = f3 + (storeHouseBarItem.b * f8);
                        float f10 = f4 + (((float) (-this.c)) * f8);
                        Matrix matrix = new Matrix();
                        matrix.postRotate(360.0f * f5);
                        matrix.postScale(f5, f5);
                        matrix.postTranslate(f9, f10);
                        storeHouseBarItem.a(this.k * f5);
                        canvas.concat(matrix);
                    }
                }
            }
            storeHouseBarItem.a(canvas);
            canvas.restore();
        }
        if (this.r) {
            invalidate();
        }
        canvas.restoreToCount(save);
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        c();
        for (int i2 = 0; i2 < this.mItemList.size(); i2++) {
            this.mItemList.get(i2).c(this.e);
        }
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        b();
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        c();
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b2, int i2, int i3, float f2, float f3) {
        setProgress(Math.min(1.0f, f3));
        invalidate();
    }

    private class AniController implements Runnable {
        private int b;
        private int c;
        private int d;
        private int e;
        private boolean f;

        private AniController() {
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = true;
        }

        /* access modifiers changed from: private */
        public void a() {
            this.f = true;
            this.b = 0;
            this.e = StoreHouseHeader.this.n / StoreHouseHeader.this.mItemList.size();
            this.c = StoreHouseHeader.this.o / this.e;
            this.d = (StoreHouseHeader.this.mItemList.size() / this.c) + 1;
            run();
        }

        public void run() {
            int i = this.b % this.c;
            for (int i2 = 0; i2 < this.d; i2++) {
                int i3 = (this.c * i2) + i;
                if (i3 <= this.b) {
                    StoreHouseBarItem storeHouseBarItem = StoreHouseHeader.this.mItemList.get(i3 % StoreHouseHeader.this.mItemList.size());
                    storeHouseBarItem.setFillAfter(false);
                    storeHouseBarItem.setFillEnabled(true);
                    storeHouseBarItem.setFillBefore(false);
                    storeHouseBarItem.setDuration((long) StoreHouseHeader.this.p);
                    storeHouseBarItem.a(StoreHouseHeader.this.l, StoreHouseHeader.this.m);
                }
            }
            this.b++;
            if (this.f) {
                StoreHouseHeader.this.postDelayed(this, (long) this.e);
            }
        }

        /* access modifiers changed from: private */
        public void b() {
            this.f = false;
            StoreHouseHeader.this.removeCallbacks(this);
        }
    }
}
