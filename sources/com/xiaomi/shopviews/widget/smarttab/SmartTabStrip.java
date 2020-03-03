package com.xiaomi.shopviews.widget.smarttab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.smarttab.SmartTabLayout;

class SmartTabStrip extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f13351a = -1;
    private static final byte b = 38;
    private static final int c = 2;
    private static final byte d = 32;
    private static final float e = 0.0f;
    private static final int f = 1;
    private static final boolean g = false;
    private static final float h = 0.0f;
    private static final int i = 0;
    private static final boolean j = false;
    private static final boolean k = false;
    private static final boolean l = false;
    private static final int m = -13388315;
    private static final byte n = 38;
    private static final int o = 0;
    private static final int p = 0;
    private static final int q = 2;
    private static final int r = 1;
    private static final int s = 8;
    private final int A;
    private final boolean B;
    private SmartTabIndicationInterpolator C;
    private final boolean D;
    private final float E;
    private final int F;
    private final boolean G;
    private final Paint H;
    private final RectF I = new RectF();
    private final int J;
    private final int K;
    private final boolean L;
    private int M;
    private int N;
    private float O;
    private final int P;
    private final int Q;
    private final Paint t;
    private final int u;
    private final int v;
    private SmartTabLayout.TabColorizer w;
    private final SimpleTabColorizer x;
    private final float y;
    private final Paint z;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SmartTabStrip(Context context, AttributeSet attributeSet) {
        super(context);
        int i2;
        int[] intArray;
        int[] iArr;
        setWillNotDraw(false);
        float f2 = getResources().getDisplayMetrics().density;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842800, typedValue, true);
        int i3 = typedValue.data;
        float f3 = f2 * 0.0f;
        int a2 = a(i3, (byte) Constants.TagName.QUERY_RECORD_COUNT);
        int i4 = (int) f3;
        int a3 = a(i3, (byte) Constants.TagName.QUERY_RECORD_COUNT);
        int a4 = a(i3, (byte) 32);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.mi_stl_SmartTabLayout);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorAlwaysInCenter, false);
        boolean z3 = obtainStyledAttributes.getBoolean(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorWithoutPadding, false);
        boolean z4 = obtainStyledAttributes.getBoolean(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorInFront, false);
        int i5 = obtainStyledAttributes.getInt(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorInterpolation, 0);
        int i6 = obtainStyledAttributes.getInt(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorGravity, 0);
        int i7 = i5;
        int color = obtainStyledAttributes.getColor(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorColor, m);
        int i8 = i6;
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorColors, -1);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorThickness, (int) (8.0f * f2));
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorWidth, -1);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.mi_stl_SmartTabLayout_mi_stl_indicatorCornerRadius, f3);
        int color2 = obtainStyledAttributes.getColor(R.styleable.mi_stl_SmartTabLayout_mi_stl_overlineColor, a2);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.mi_stl_SmartTabLayout_mi_stl_overlineThickness, i4);
        int color3 = obtainStyledAttributes.getColor(R.styleable.mi_stl_SmartTabLayout_mi_stl_underlineColor, a3);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.mi_stl_SmartTabLayout_mi_stl_underlineThickness, (int) (2.0f * f2));
        int color4 = obtainStyledAttributes.getColor(R.styleable.mi_stl_SmartTabLayout_mi_stl_dividerColor, a4);
        float f4 = dimension;
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.mi_stl_SmartTabLayout_mi_stl_dividerColors, -1);
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.mi_stl_SmartTabLayout_mi_stl_dividerThickness, (int) (f2 * 1.0f));
        boolean z5 = obtainStyledAttributes.getBoolean(R.styleable.mi_stl_SmartTabLayout_mi_stl_drawDecorationAfterTab, false);
        obtainStyledAttributes.recycle();
        if (resourceId == -1) {
            i2 = 1;
            intArray = new int[]{color};
        } else {
            i2 = 1;
            intArray = getResources().getIntArray(resourceId);
        }
        if (resourceId2 == -1) {
            iArr = new int[i2];
            iArr[0] = color4;
        } else {
            iArr = getResources().getIntArray(resourceId2);
        }
        SimpleTabColorizer simpleTabColorizer = new SimpleTabColorizer();
        this.x = simpleTabColorizer;
        simpleTabColorizer.b(intArray);
        this.x.a(iArr);
        this.Q = dimensionPixelSize2;
        this.P = color2;
        this.v = dimensionPixelSize3;
        this.u = color3;
        this.t = new Paint(1);
        this.D = z2;
        this.L = z3;
        this.G = z4;
        this.J = dimensionPixelSize;
        this.K = layoutDimension;
        this.H = new Paint(1);
        this.E = f4;
        this.F = i8;
        this.y = 0.0f;
        Paint paint = new Paint(1);
        this.z = paint;
        int i9 = dimensionPixelSize4;
        paint.setStrokeWidth((float) i9);
        this.A = i9;
        this.B = z5;
        this.C = SmartTabIndicationInterpolator.a(i7);
    }

    private static int a(int i2, int i3, float f2) {
        float f3 = 1.0f - f2;
        return Color.rgb((int) ((((float) Color.red(i2)) * f2) + (((float) Color.red(i3)) * f3)), (int) ((((float) Color.green(i2)) * f2) + (((float) Color.green(i3)) * f3)), (int) ((f2 * ((float) Color.blue(i2))) + (f3 * ((float) Color.blue(i3)))));
    }

    private void a(Canvas canvas) {
        int i2;
        int i3;
        Canvas canvas2 = canvas;
        int height = getHeight();
        int width = getWidth();
        int childCount = getChildCount();
        SmartTabLayout.TabColorizer a2 = a();
        boolean l2 = SmartTablayoutUtils.l(this);
        if (this.G) {
            a(canvas2, 0, width);
            a(canvas2, 0, width, height);
        }
        if (childCount > 0) {
            View childAt = getChildAt(this.N);
            int b2 = SmartTablayoutUtils.b(childAt, this.L);
            int a3 = SmartTablayoutUtils.a(childAt, this.L);
            if (!l2) {
                int i4 = b2;
                b2 = a3;
                a3 = i4;
            }
            int b3 = a2.b(this.N);
            float f2 = (float) this.J;
            if (this.O > 0.0f && this.N < getChildCount() - 1) {
                int b4 = a2.b(this.N + 1);
                if (b3 != b4) {
                    b3 = a(b4, b3, this.O);
                }
                float a4 = this.C.a(this.O);
                float b5 = this.C.b(this.O);
                float c2 = this.C.c(this.O);
                View childAt2 = getChildAt(this.N + 1);
                int b6 = SmartTablayoutUtils.b(childAt2, this.L);
                int a5 = SmartTablayoutUtils.a(childAt2, this.L);
                if (l2) {
                    i2 = (int) ((((float) a5) * b5) + ((1.0f - b5) * ((float) a3)));
                    i3 = (int) ((((float) b6) * a4) + ((1.0f - a4) * ((float) b2)));
                } else {
                    i2 = (int) ((((float) b6) * a4) + ((1.0f - a4) * ((float) a3)));
                    i3 = (int) ((((float) a5) * b5) + ((1.0f - b5) * ((float) b2)));
                }
                f2 *= c2;
                b2 = i3;
                a3 = i2;
            }
            a(canvas, a3, b2, height, f2, b3);
        }
        if (!this.G) {
            a(canvas2, 0, width);
            a(canvas2, 0, getWidth(), height);
        }
        b(canvas2, height, childCount);
    }

    private void a(Canvas canvas, int i2, int i3, int i4, float f2, int i5) {
        float f3;
        float f4;
        if (this.J > 0 && this.K != 0) {
            switch (this.F) {
                case 1:
                    float f5 = ((float) this.J) / 2.0f;
                    float f6 = f2 / 2.0f;
                    f4 = f5 - f6;
                    f3 = f5 + f6;
                    break;
                case 2:
                    float f7 = ((float) i4) / 2.0f;
                    float f8 = f2 / 2.0f;
                    f4 = f7 - f8;
                    f3 = f7 + f8;
                    break;
                default:
                    float f9 = ((float) i4) - (((float) this.J) / 2.0f);
                    float f10 = f2 / 2.0f;
                    f4 = f9 - f10;
                    f3 = f9 + f10;
                    break;
            }
            this.H.setColor(i5);
            if (this.K == -1) {
                this.I.set((float) i2, f4, (float) i3, f3);
            } else {
                float abs = ((float) (Math.abs(i2 - i3) - this.K)) / 2.0f;
                this.I.set(((float) i2) + abs, f4, ((float) i3) - abs, f3);
            }
            if (this.E > 0.0f) {
                canvas.drawRoundRect(this.I, this.E, this.E, this.H);
            } else {
                canvas.drawRect(this.I, this.H);
            }
        }
    }

    private void a(Canvas canvas, int i2, int i3) {
        if (this.Q > 0) {
            this.t.setColor(this.P);
            canvas.drawRect((float) i2, 0.0f, (float) i3, (float) this.Q, this.t);
        }
    }

    private void b(Canvas canvas, int i2, int i3) {
        int i4 = i2;
        if (this.A > 0) {
            int min = (int) (Math.min(Math.max(0.0f, this.y), 1.0f) * ((float) i4));
            SmartTabLayout.TabColorizer a2 = a();
            int i5 = (i4 - min) / 2;
            int i6 = min + i5;
            boolean l2 = SmartTablayoutUtils.l(this);
            for (int i7 = 0; i7 < i3 - 1; i7++) {
                View childAt = getChildAt(i7);
                int a3 = SmartTablayoutUtils.a(childAt);
                int b2 = SmartTablayoutUtils.b(childAt);
                int i8 = l2 ? a3 - b2 : a3 + b2;
                this.z.setColor(a2.a(i7));
                float f2 = (float) i8;
                canvas.drawLine(f2, (float) i5, f2, (float) i6, this.z);
            }
        }
    }

    private void a(Canvas canvas, int i2, int i3, int i4) {
        if (this.v > 0) {
            this.t.setColor(this.u);
            canvas.drawRect((float) i2, (float) (i4 - this.v), (float) i3, (float) i4, this.t);
        }
    }

    private static int a(int i2, byte b2) {
        return Color.argb(b2, Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.B) {
            a(canvas);
        }
    }

    /* access modifiers changed from: package-private */
    public SmartTabLayout.TabColorizer a() {
        if (this.w != null) {
            return this.w;
        }
        return this.x;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.D;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!this.B) {
            a(canvas);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, float f2) {
        this.N = i2;
        this.O = f2;
        if (f2 == 0.0f && this.M != this.N) {
            this.M = this.N;
        }
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void a(SmartTabLayout.TabColorizer tabColorizer) {
        this.w = tabColorizer;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void a(int... iArr) {
        this.w = null;
        this.x.a(iArr);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void a(SmartTabIndicationInterpolator smartTabIndicationInterpolator) {
        this.C = smartTabIndicationInterpolator;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void b(int... iArr) {
        this.w = null;
        this.x.b(iArr);
        invalidate();
    }

    private static class SimpleTabColorizer implements SmartTabLayout.TabColorizer {

        /* renamed from: a  reason: collision with root package name */
        private int[] f13352a;
        private int[] b;

        private SimpleTabColorizer() {
        }

        public final int a(int i) {
            return this.f13352a[i % this.f13352a.length];
        }

        public final int b(int i) {
            return this.b[i % this.b.length];
        }

        /* access modifiers changed from: package-private */
        public void a(int... iArr) {
            this.f13352a = iArr;
        }

        /* access modifiers changed from: package-private */
        public void b(int... iArr) {
            this.b = iArr;
        }
    }
}
