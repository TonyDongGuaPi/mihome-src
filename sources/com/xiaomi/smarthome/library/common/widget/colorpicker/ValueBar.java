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

public class ValueBar extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18991a = "parent";
    private static final String b = "color";
    private static final String c = "value";
    private static final String d = "orientation";
    private static final boolean e = true;
    private static final boolean f = false;
    private static final boolean g = true;
    private int A;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private Paint n;
    private Paint o;
    private Paint p;
    private RectF q = new RectF();
    private Shader r;
    private boolean s;
    private int t;
    private float[] u = new float[3];
    private float v;
    private float w;
    private ColorPicker x = null;
    private boolean y;
    private OnValueChangedListener z;

    public interface OnValueChangedListener {
        void a(int i);
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.z = onValueChangedListener;
    }

    public OnValueChangedListener getOnValueChangedListener() {
        return this.z;
    }

    public ValueBar(Context context) {
        super(context);
        a((AttributeSet) null, 0);
    }

    public ValueBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public ValueBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(attributeSet, i2);
    }

    private void a(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ColorBars, i2, 0);
        Resources resources = getContext().getResources();
        this.h = obtainStyledAttributes.getDimensionPixelSize(4, resources.getDimensionPixelSize(R.dimen.bar_thickness));
        this.i = obtainStyledAttributes.getDimensionPixelSize(0, resources.getDimensionPixelSize(R.dimen.bar_length));
        this.j = this.i;
        this.k = obtainStyledAttributes.getDimensionPixelSize(3, resources.getDimensionPixelSize(R.dimen.bar_pointer_radius));
        this.l = obtainStyledAttributes.getDimensionPixelSize(2, resources.getDimensionPixelSize(R.dimen.bar_pointer_halo_radius));
        this.y = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
        this.n = new Paint(1);
        this.n.setShader(this.r);
        this.m = this.l;
        this.p = new Paint(1);
        this.p.setColor(-16777216);
        this.p.setAlpha(80);
        this.o = new Paint(1);
        this.o.setColor(-8257792);
        this.v = 1.0f / ((float) this.i);
        this.w = ((float) this.i) / 1.0f;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int i4 = this.j + (this.l * 2);
        if (!this.y) {
            i2 = i3;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            i4 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i4 = Math.min(i4, size);
        }
        int i5 = this.l * 2;
        this.i = i4 - i5;
        if (!this.y) {
            setMeasuredDimension(i5, this.i + i5);
        } else {
            setMeasuredDimension(this.i + i5, i5);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.y) {
            i7 = this.i + this.l;
            i6 = this.h;
            this.i = i2 - (this.l * 2);
            this.q.set((float) this.l, (float) (this.l - (this.h / 2)), (float) (this.i + this.l), (float) (this.l + (this.h / 2)));
        } else {
            i7 = this.h;
            i6 = this.i + this.l;
            this.i = i3 - (this.l * 2);
            this.q.set((float) (this.l - (this.h / 2)), (float) this.l, (float) (this.l + (this.h / 2)), (float) (this.i + this.l));
        }
        if (!isInEditMode()) {
            this.r = new LinearGradient((float) this.l, 0.0f, (float) i7, (float) i6, new int[]{Color.HSVToColor(255, this.u), -16777216}, (float[]) null, Shader.TileMode.CLAMP);
        } else {
            this.r = new LinearGradient((float) this.l, 0.0f, (float) i7, (float) i6, new int[]{-8257792, -16777216}, (float[]) null, Shader.TileMode.CLAMP);
            Color.colorToHSV(-8257792, this.u);
        }
        this.n.setShader(this.r);
        this.v = 1.0f / ((float) this.i);
        this.w = ((float) this.i) / 1.0f;
        float[] fArr = new float[3];
        Color.colorToHSV(this.t, fArr);
        if (!isInEditMode()) {
            this.m = Math.round((((float) this.i) - (this.w * fArr[2])) + ((float) this.l));
        } else {
            this.m = this.l;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i2;
        int i3;
        canvas.drawRect(this.q, this.n);
        if (this.y) {
            i3 = this.m;
            i2 = this.l;
        } else {
            i3 = this.l;
            i2 = this.m;
        }
        float f2 = (float) i3;
        float f3 = (float) i2;
        canvas.drawCircle(f2, f3, (float) this.l, this.p);
        canvas.drawCircle(f2, f3, (float) this.k, this.o);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f2;
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.y) {
            f2 = motionEvent.getX();
        } else {
            f2 = motionEvent.getY();
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.s = true;
                if (f2 >= ((float) this.l) && f2 <= ((float) (this.l + this.i))) {
                    this.m = Math.round(f2);
                    a(Math.round(f2));
                    this.o.setColor(this.t);
                    invalidate();
                    break;
                }
            case 1:
                this.s = false;
                break;
            case 2:
                if (this.s) {
                    if (f2 >= ((float) this.l) && f2 <= ((float) (this.l + this.i))) {
                        this.m = Math.round(f2);
                        a(Math.round(f2));
                        this.o.setColor(this.t);
                        if (this.x != null) {
                            this.x.changeOpacityBarColor(this.t);
                        }
                        invalidate();
                    } else if (f2 < ((float) this.l)) {
                        this.m = this.l;
                        this.t = Color.HSVToColor(this.u);
                        this.o.setColor(this.t);
                        if (this.x != null) {
                            this.x.changeOpacityBarColor(this.t);
                        }
                        invalidate();
                    } else if (f2 > ((float) (this.l + this.i))) {
                        this.m = this.l + this.i;
                        this.t = -16777216;
                        this.o.setColor(this.t);
                        if (this.x != null) {
                            this.x.changeOpacityBarColor(this.t);
                        }
                        invalidate();
                    }
                }
                if (!(this.z == null || this.A == this.t)) {
                    this.z.a(this.t);
                    this.A = this.t;
                    break;
                }
                break;
        }
        return true;
    }

    public void setColor(int i2) {
        int i3;
        int i4;
        if (this.y) {
            i4 = this.i + this.l;
            i3 = this.h;
        } else {
            i4 = this.h;
            i3 = this.i + this.l;
        }
        Color.colorToHSV(i2, this.u);
        this.r = new LinearGradient((float) this.l, 0.0f, (float) i4, (float) i3, new int[]{i2, -16777216}, (float[]) null, Shader.TileMode.CLAMP);
        this.n.setShader(this.r);
        a(this.m);
        this.o.setColor(this.t);
        if (this.x != null && this.x.hasOpacityBar()) {
            this.x.changeOpacityBarColor(this.t);
        }
        invalidate();
    }

    public void setValue(float f2) {
        this.m = Math.round((((float) this.i) - (this.w * f2)) + ((float) this.l));
        a(this.m);
        this.o.setColor(this.t);
        if (this.x != null) {
            this.x.changeOpacityBarColor(this.t);
        }
        invalidate();
    }

    private void a(int i2) {
        int i3 = i2 - this.l;
        if (i3 < 0) {
            i3 = 0;
        } else if (i3 > this.i) {
            i3 = this.i;
        }
        this.t = Color.HSVToColor(new float[]{this.u[0], this.u[1], 1.0f - (this.v * ((float) i3))});
    }

    public int getColor() {
        return this.t;
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.x = colorPicker;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable(f18991a, onSaveInstanceState);
        bundle.putFloatArray("color", this.u);
        float[] fArr = new float[3];
        Color.colorToHSV(this.t, fArr);
        bundle.putFloat("value", fArr[2]);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable(f18991a));
        setColor(Color.HSVToColor(bundle.getFloatArray("color")));
        setValue(bundle.getFloat("value"));
    }
}
