package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.libra.Color;
import com.xiaomi.smarthome.miotcard.R;

public class PalletView extends View {

    /* renamed from: a  reason: collision with root package name */
    private Paint f20521a;
    private Paint b;
    private Paint c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private final int i;
    boolean isCanSlideThumb;
    boolean isPressed;
    boolean isThumbPressed;
    private final int j;
    private final int k;
    private final int l;
    private final int m;
    float mPalletHeight;
    float mPalletWidth;
    float mPointerX;
    float mPointerY;
    private final int n;
    private float o;
    private float p;
    private int q;
    private int r;
    private int s;
    private int t;
    private Bitmap u;
    private int v;
    private int w;
    private int x;

    private void a(@NonNull Canvas canvas) {
    }

    public void setmThumbRadiusOutter(int i2) {
        this.f = i2;
    }

    public void setmThumbRadiusInner(int i2) {
        this.g = i2;
    }

    public void setmThumbShadowRadius(int i2) {
        this.h = i2;
    }

    private void a() {
        this.c.setColor(Color.d);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setStrokeWidth(3.0f);
        this.c.setAlpha(150);
    }

    public void setColorThumbOuter(int i2) {
        this.w = i2;
    }

    public void setColorThumbInner(int i2) {
        this.x = i2;
    }

    public void setThumbRadiusOutter(int i2) {
        this.f = i2;
    }

    public void setThumbRadiusInner(int i2) {
        this.g = i2;
    }

    public PalletView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PalletView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PalletView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f20521a = new Paint();
        this.b = new Paint(1);
        this.c = new Paint(1);
        this.d = -1;
        this.e = -1;
        this.o = -1.0f;
        this.p = -1.0f;
        this.mPalletWidth = -1.0f;
        this.mPalletHeight = -1.0f;
        this.isPressed = false;
        this.isCanSlideThumb = true;
        this.isThumbPressed = false;
        this.u = BitmapFactory.decodeResource(getResources(), R.drawable.pallet_nor);
        this.v = 0;
        a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PalletView, i2, 0);
        this.i = (int) obtainStyledAttributes.getDimension(R.styleable.PalletView_thumb_radius_outter, 0.0f);
        this.j = (int) obtainStyledAttributes.getDimension(R.styleable.PalletView_thumb_radius_outter_pressed, 0.0f);
        this.k = (int) obtainStyledAttributes.getDimension(R.styleable.PalletView_thumb_radius_inner, 0.0f);
        this.l = (int) obtainStyledAttributes.getDimension(R.styleable.PalletView_thumb_radius_inner_pressed, 0.0f);
        this.m = (int) obtainStyledAttributes.getDimension(R.styleable.PalletView_thumb_shadow_radius, 0.0f);
        this.n = (int) obtainStyledAttributes.getDimension(R.styleable.PalletView_thumb_shadow_radius_pressed, 0.0f);
        this.x = obtainStyledAttributes.getColor(R.styleable.PalletView_thumb_inner_color, context.getResources().getColor(R.color.gradient_yellow_start));
        this.w = obtainStyledAttributes.getColor(R.styleable.PalletView_thumb_outter_color, context.getResources().getColor(R.color.gradient_yellow_end));
        this.f = this.i;
        this.g = this.k;
        this.h = this.m;
    }

    public void setColorTempratureMax(int i2) {
        this.q = i2;
    }

    public void setBrightMax(int i2) {
        this.r = i2;
    }

    public void setBrightMin(int i2) {
        this.s = i2;
    }

    public void setColorTempratureMin(int i2) {
        this.t = i2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getWidth() > 0 && getHeight() > 0) {
            a(canvas);
            b(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 > 0 && i3 > 0 && this.o == -1.0f && this.p == -1.0f) {
            this.o = (float) (getWidth() / 2);
            this.p = (float) (getHeight() / 2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.isCanSlideThumb) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.mPointerX = motionEvent.getX();
                this.mPointerY = motionEvent.getY();
                if (!a(this.mPointerX, this.mPointerY)) {
                    this.isThumbPressed = false;
                    break;
                } else {
                    this.isThumbPressed = true;
                    break;
                }
            case 1:
            case 3:
                if (this.isThumbPressed) {
                    float x2 = motionEvent.getX();
                    float y = motionEvent.getY();
                    this.o += x2 - this.mPointerX;
                    this.p += y - this.mPointerY;
                    this.mPointerX = x2;
                    this.mPointerY = y;
                    invalidate();
                }
                this.isThumbPressed = false;
                break;
            case 2:
                if (this.isThumbPressed) {
                    float x3 = motionEvent.getX();
                    float y2 = motionEvent.getY();
                    this.o += x3 - this.mPointerX;
                    this.p += y2 - this.mPointerY;
                    this.mPointerX = x3;
                    this.mPointerY = y2;
                    invalidate();
                    break;
                }
                break;
        }
        return true;
    }

    public void refreshState(boolean z) {
        if (z) {
            if (this.v == -1) {
                this.v = 0;
                this.u = BitmapFactory.decodeResource(getResources(), R.drawable.pallet_nor);
            }
        } else if (this.v != -1) {
            this.v = -1;
            this.u = BitmapFactory.decodeResource(getResources(), R.drawable.pallet_disable);
        }
    }

    private void b(@NonNull Canvas canvas) {
        this.b.setShadowLayer((float) this.h, 2.0f, 2.0f, 1145324612);
        this.b.setAntiAlias(true);
        this.b.setColor(this.w);
        canvas.drawCircle(this.o, this.p, (float) this.f, this.b);
        canvas.drawCircle(this.o, this.p, (float) this.f, this.c);
        this.b.clearShadowLayer();
        this.b.setColor(this.x);
        canvas.drawCircle(this.o, this.p, (float) this.g, this.b);
    }

    private boolean a(float f2, float f3) {
        double sqrt = Math.sqrt((double) ((Math.abs(f2 - this.o) * Math.abs(f2 - this.o)) + (Math.abs(f3 - this.p) * Math.abs(f3 - this.p))));
        Log.i("PalletView", "distance:" + sqrt + ",radius:" + this.f + "pointerX:" + f2 + " ,pointerY:" + f3 + ",centerX:" + this.o + " ,centerY:" + this.p);
        return sqrt <= ((double) this.f);
    }
}
