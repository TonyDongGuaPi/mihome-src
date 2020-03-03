package com.xiaomi.smarthome.library.common.widget.colorpicker;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.xiaomi.smarthome.R;

public class SVBar extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18983a = "parent";
    private static final String b = "color";
    private static final String c = "saturation";
    private static final String d = "value";
    private static final String e = "orientation";
    private static final boolean f = true;
    private static final boolean g = false;
    private static final boolean h = true;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private Paint o;
    private Paint p;
    private Paint q;
    private RectF r = new RectF();
    private Shader s;
    private boolean t;
    private int u;
    private float[] v = new float[3];
    private float w;
    private float x;
    private ColorPicker y = null;
    private boolean z;

    public SVBar(Context context) {
        super(context);
        a((AttributeSet) null, 0);
    }

    public SVBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public SVBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(attributeSet, i2);
    }

    private void a(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ColorBars, i2, 0);
        Resources resources = getContext().getResources();
        this.i = obtainStyledAttributes.getDimensionPixelSize(4, resources.getDimensionPixelSize(R.dimen.bar_thickness));
        this.j = obtainStyledAttributes.getDimensionPixelSize(0, resources.getDimensionPixelSize(R.dimen.bar_length));
        this.k = this.j;
        this.l = obtainStyledAttributes.getDimensionPixelSize(3, resources.getDimensionPixelSize(R.dimen.bar_pointer_radius));
        this.m = obtainStyledAttributes.getDimensionPixelSize(2, resources.getDimensionPixelSize(R.dimen.bar_pointer_halo_radius));
        this.z = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
        this.o = new Paint(1);
        this.o.setShader(this.s);
        this.n = (this.j / 2) + this.m;
        this.q = new Paint(1);
        this.q.setColor(-16777216);
        this.q.setAlpha(80);
        this.p = new Paint(1);
        this.p.setColor(-8257792);
        this.w = 1.0f / (((float) this.j) / 2.0f);
        this.x = (((float) this.j) / 2.0f) / 1.0f;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int i4 = this.k + (this.m * 2);
        if (!this.z) {
            i2 = i3;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            i4 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i4 = Math.min(i4, size);
        }
        int i5 = this.m * 2;
        this.j = i4 - i5;
        if (!this.z) {
            setMeasuredDimension(i5, this.j + i5);
        } else {
            setMeasuredDimension(this.j + i5, i5);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.z) {
            i7 = this.j + this.m;
            i6 = this.i;
            this.j = i2 - (this.m * 2);
            this.r.set((float) this.m, (float) (this.m - (this.i / 2)), (float) (this.j + this.m), (float) (this.m + (this.i / 2)));
        } else {
            i7 = this.i;
            i6 = this.j + this.m;
            this.j = i3 - (this.m * 2);
            this.r.set((float) (this.m - (this.i / 2)), (float) this.m, (float) (this.m + (this.i / 2)), (float) (this.j + this.m));
        }
        if (!isInEditMode()) {
            this.s = new LinearGradient((float) this.m, 0.0f, (float) i7, (float) i6, new int[]{-1, Color.HSVToColor(this.v), -16777216}, (float[]) null, Shader.TileMode.CLAMP);
        } else {
            this.s = new LinearGradient((float) this.m, 0.0f, (float) i7, (float) i6, new int[]{-1, -8257792, -16777216}, (float[]) null, Shader.TileMode.CLAMP);
            Color.colorToHSV(-8257792, this.v);
        }
        this.o.setShader(this.s);
        this.w = 1.0f / (((float) this.j) / 2.0f);
        this.x = (((float) this.j) / 2.0f) / 1.0f;
        float[] fArr = new float[3];
        Color.colorToHSV(this.u, fArr);
        if (fArr[1] < fArr[2]) {
            this.n = Math.round((this.x * fArr[1]) + ((float) this.m));
        } else {
            this.n = Math.round((this.x * (1.0f - fArr[2])) + ((float) this.m) + ((float) (this.j / 2)));
        }
        if (isInEditMode()) {
            this.n = (this.j / 2) + this.m;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i2;
        int i3;
        canvas.drawRect(this.r, this.o);
        if (this.z) {
            i3 = this.n;
            i2 = this.m;
        } else {
            i3 = this.m;
            i2 = this.n;
        }
        float f2 = (float) i3;
        float f3 = (float) i2;
        canvas.drawCircle(f2, f3, (float) this.m, this.q);
        canvas.drawCircle(f2, f3, (float) this.l, this.p);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f2;
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.z) {
            f2 = motionEvent.getX();
        } else {
            f2 = motionEvent.getY();
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.t = true;
                if (f2 >= ((float) this.m) && f2 <= ((float) (this.m + this.j))) {
                    this.n = Math.round(f2);
                    a(Math.round(f2));
                    this.p.setColor(this.u);
                    invalidate();
                    break;
                }
            case 1:
                this.t = false;
                break;
            case 2:
                if (this.t) {
                    if (f2 < ((float) this.m) || f2 > ((float) (this.m + this.j))) {
                        if (f2 >= ((float) this.m)) {
                            if (f2 > ((float) (this.m + this.j))) {
                                this.n = this.m + this.j;
                                this.u = -16777216;
                                this.p.setColor(this.u);
                                if (this.y != null) {
                                    this.y.changeOpacityBarColor(this.u);
                                }
                                invalidate();
                                break;
                            }
                        } else {
                            this.n = this.m;
                            this.u = -1;
                            this.p.setColor(this.u);
                            if (this.y != null) {
                                this.y.changeOpacityBarColor(this.u);
                            }
                            invalidate();
                            break;
                        }
                    } else {
                        this.n = Math.round(f2);
                        a(Math.round(f2));
                        this.p.setColor(this.u);
                        if (this.y != null) {
                            this.y.changeOpacityBarColor(this.u);
                        }
                        invalidate();
                        break;
                    }
                }
                break;
        }
        return true;
    }

    public void setSaturation(float f2) {
        this.n = Math.round((this.x * f2) + ((float) this.m));
        a(this.n);
        this.p.setColor(this.u);
        if (this.y != null) {
            this.y.changeOpacityBarColor(this.u);
        }
        invalidate();
    }

    public void setValue(float f2) {
        this.n = Math.round((this.x * (1.0f - f2)) + ((float) this.m) + ((float) (this.j / 2)));
        a(this.n);
        this.p.setColor(this.u);
        if (this.y != null) {
            this.y.changeOpacityBarColor(this.u);
        }
        invalidate();
    }

    public void setColor(int i2) {
        int i3;
        int i4;
        if (this.z) {
            i4 = this.j + this.m;
            i3 = this.i;
        } else {
            i4 = this.i;
            i3 = this.j + this.m;
        }
        Color.colorToHSV(i2, this.v);
        this.s = new LinearGradient((float) this.m, 0.0f, (float) i4, (float) i3, new int[]{-1, i2, -16777216}, (float[]) null, Shader.TileMode.CLAMP);
        this.o.setShader(this.s);
        a(this.n);
        this.p.setColor(this.u);
        if (this.y != null && this.y.hasOpacityBar()) {
            this.y.changeOpacityBarColor(this.u);
        }
        invalidate();
    }

    private void a(int i2) {
        int i3 = i2 - this.m;
        if (i3 > this.j / 2 && i3 < this.j) {
            this.u = Color.HSVToColor(new float[]{this.v[0], 1.0f, 1.0f - (this.w * ((float) (i3 - (this.j / 2))))});
        } else if (i3 > 0 && i3 < this.j) {
            this.u = Color.HSVToColor(new float[]{this.v[0], this.w * ((float) i3), 1.0f});
        } else if (i3 == this.j / 2) {
            this.u = Color.HSVToColor(new float[]{this.v[0], 1.0f, 1.0f});
        } else if (i3 <= 0) {
            this.u = -1;
        } else if (i3 >= this.j) {
            this.u = -16777216;
        }
    }

    public int getColor() {
        return this.u;
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.y = colorPicker;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable(f18983a, onSaveInstanceState);
        bundle.putFloatArray("color", this.v);
        float[] fArr = new float[3];
        Color.colorToHSV(this.u, fArr);
        if (fArr[1] < fArr[2]) {
            bundle.putFloat(c, fArr[1]);
        } else {
            bundle.putFloat("value", fArr[2]);
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable(f18983a));
        setColor(Color.HSVToColor(bundle.getFloatArray("color")));
        if (bundle.containsKey(c)) {
            setSaturation(bundle.getFloat(c));
        } else {
            setValue(bundle.getFloat("value"));
        }
    }
}
