package com.xiaomi.shopviews.adapter.countdown;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.shopviews.adapter.countdown.DynamicConfig;
import com.xiaomi.shopviews.widget.R;

public class CountdownView extends View {

    /* renamed from: a  reason: collision with root package name */
    private BaseCountdown f13091a;
    private CustomCountDownTimer b;
    /* access modifiers changed from: private */
    public OnCountdownEndListener c;
    private OnCountdownIntervalListener d;
    private boolean e;
    private long f;
    private long g;
    private long h;

    public interface OnCountdownEndListener {
        void a(CountdownView countdownView);
    }

    public interface OnCountdownIntervalListener {
        void a(CountdownView countdownView, long j);
    }

    public CountdownView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CountdownView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CountdownView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CountdownView);
        this.e = obtainStyledAttributes.getBoolean(R.styleable.CountdownView_isHideTimeBackground, true);
        this.f13091a = this.e ? new BaseCountdown() : new BackgroundCountdown();
        this.f13091a.a(context, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        this.f13091a.e();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int c2 = this.f13091a.c();
        int d2 = this.f13091a.d();
        int a2 = a(1, c2, i);
        int a3 = a(2, d2, i2);
        setMeasuredDimension(a2, a3);
        this.f13091a.a((View) this, a2, a3, c2, d2);
    }

    private int a(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (mode == 1073741824) {
            return Math.max(i2, size);
        }
        if (i == 1) {
            return getPaddingLeft() + getPaddingRight() + i2;
        }
        return getPaddingTop() + getPaddingBottom() + i2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f13091a.a(canvas);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void a() {
        this.f13091a.h();
        requestLayout();
    }

    public void start(long j) {
        long j2;
        if (j > 0) {
            this.f = 0;
            if (this.b != null) {
                this.b.c();
                this.b = null;
            }
            if (this.f13091a.j) {
                j2 = 10;
                updateShow(j);
            } else {
                j2 = 1000;
            }
            this.b = new CustomCountDownTimer(j, j2) {
                public void a(long j) {
                    CountdownView.this.updateShow(j);
                }

                public void a() {
                    CountdownView.this.allShowZero();
                    if (CountdownView.this.c != null) {
                        CountdownView.this.c.a(CountdownView.this);
                    }
                }
            };
            this.b.b();
        }
    }

    public void stop() {
        if (this.b != null) {
            this.b.c();
        }
    }

    public void pause() {
        if (this.b != null) {
            this.b.d();
        }
    }

    public void restart() {
        if (this.b != null) {
            this.b.e();
        }
    }

    @Deprecated
    public void customTimeShow(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.f13091a.l = true;
        this.f13091a.m = true;
        if (this.f13091a.a(z, z2, z3, z4, z5)) {
            start(this.h);
        }
    }

    public void allShowZero() {
        this.f13091a.a(0, 0, 0, 0, 0);
        invalidate();
    }

    public void setOnCountdownEndListener(OnCountdownEndListener onCountdownEndListener) {
        this.c = onCountdownEndListener;
    }

    public void setOnCountdownIntervalListener(long j, OnCountdownIntervalListener onCountdownIntervalListener) {
        this.g = j;
        this.d = onCountdownIntervalListener;
    }

    public int getDay() {
        return this.f13091a.f13078a;
    }

    public int getHour() {
        return this.f13091a.b;
    }

    public int getMinute() {
        return this.f13091a.c;
    }

    public int getSecond() {
        return this.f13091a.d;
    }

    public long getRemainTime() {
        return this.h;
    }

    public void updateShow(long j) {
        this.h = j;
        a(j);
        if (this.g > 0 && this.d != null) {
            if (this.f == 0) {
                this.f = j;
            } else if (this.g + j <= this.f) {
                this.f = j;
                this.d.a(this, this.h);
            }
        }
        if (this.f13091a.f() || this.f13091a.g()) {
            a();
        } else {
            invalidate();
        }
    }

    private void a(long j) {
        int i;
        int i2;
        if (!this.f13091a.k) {
            i2 = (int) (j / 86400000);
            i = (int) ((j % 86400000) / 3600000);
        } else {
            i = (int) (j / 3600000);
            i2 = 0;
        }
        this.f13091a.a(i2, i, (int) ((j % 3600000) / 60000), (int) ((j % 60000) / 1000), (int) (j % 1000));
    }

    public void dynamicShow(DynamicConfig dynamicConfig) {
        boolean z;
        boolean z2;
        if (dynamicConfig != null) {
            Float a2 = dynamicConfig.a();
            if (a2 != null) {
                this.f13091a.g(a2.floatValue());
                z = true;
            } else {
                z = false;
            }
            Float d2 = dynamicConfig.d();
            if (d2 != null) {
                this.f13091a.h(d2.floatValue());
                z = true;
            }
            Integer b2 = dynamicConfig.b();
            if (b2 != null) {
                this.f13091a.d(b2.intValue());
                z2 = true;
            } else {
                z2 = false;
            }
            Integer e2 = dynamicConfig.e();
            if (e2 != null) {
                this.f13091a.e(e2.intValue());
                z2 = true;
            }
            Boolean c2 = dynamicConfig.c();
            if (c2 != null) {
                this.f13091a.c(c2.booleanValue());
                z = true;
            }
            Boolean f2 = dynamicConfig.f();
            if (f2 != null) {
                this.f13091a.d(f2.booleanValue());
                z = true;
            }
            String g2 = dynamicConfig.g();
            if (!TextUtils.isEmpty(g2)) {
                this.f13091a.a(g2);
                z = true;
            }
            if (this.f13091a.a(dynamicConfig.h(), dynamicConfig.i(), dynamicConfig.j(), dynamicConfig.k(), dynamicConfig.l())) {
                z = true;
            }
            Float n = dynamicConfig.n();
            if (n != null) {
                this.f13091a.i(n.floatValue());
                z = true;
            }
            if (this.f13091a.a(dynamicConfig.o(), dynamicConfig.p(), dynamicConfig.q(), dynamicConfig.r(), dynamicConfig.s(), dynamicConfig.t(), dynamicConfig.u(), dynamicConfig.v(), dynamicConfig.w())) {
                z = true;
            }
            Integer m = dynamicConfig.m();
            if (m != null) {
                this.f13091a.f(m.intValue());
                z = true;
            }
            Boolean y = dynamicConfig.y();
            Boolean z3 = dynamicConfig.z();
            Boolean A = dynamicConfig.A();
            Boolean B = dynamicConfig.B();
            Boolean C = dynamicConfig.C();
            if (!(y == null && z3 == null && A == null && B == null && C == null)) {
                boolean z4 = this.f13091a.f;
                if (y != null) {
                    z4 = y.booleanValue();
                    this.f13091a.l = true;
                } else {
                    this.f13091a.l = false;
                }
                boolean z5 = z4;
                boolean z6 = this.f13091a.g;
                if (z3 != null) {
                    z6 = z3.booleanValue();
                    this.f13091a.m = true;
                } else {
                    this.f13091a.m = false;
                }
                if (this.f13091a.a(z5, z6, A != null ? A.booleanValue() : this.f13091a.h, B != null ? B.booleanValue() : this.f13091a.i, C != null ? C.booleanValue() : this.f13091a.j)) {
                    start(this.h);
                }
                z = true;
            }
            DynamicConfig.BackgroundInfo D = dynamicConfig.D();
            if (!this.e && D != null) {
                BackgroundCountdown backgroundCountdown = (BackgroundCountdown) this.f13091a;
                Float f3 = D.f();
                if (f3 != null) {
                    backgroundCountdown.a(f3.floatValue());
                    z = true;
                }
                Integer a3 = D.a();
                if (a3 != null) {
                    backgroundCountdown.a(a3.intValue());
                    z2 = true;
                }
                Float e3 = D.e();
                if (e3 != null) {
                    backgroundCountdown.b(e3.floatValue());
                    z2 = true;
                }
                Boolean d3 = D.d();
                if (d3 != null) {
                    backgroundCountdown.a(d3.booleanValue());
                    if (d3.booleanValue()) {
                        Integer b3 = D.b();
                        if (b3 != null) {
                            backgroundCountdown.b(b3.intValue());
                        }
                        Float c3 = D.c();
                        if (c3 != null) {
                            backgroundCountdown.c(c3.floatValue());
                        }
                    }
                    z2 = true;
                }
                Boolean g3 = D.g();
                if (g3 != null) {
                    backgroundCountdown.b(g3.booleanValue());
                    if (g3.booleanValue()) {
                        Integer h2 = D.h();
                        if (h2 != null) {
                            backgroundCountdown.c(h2.intValue());
                        }
                        Float i = D.i();
                        if (i != null) {
                            backgroundCountdown.d(i.floatValue());
                        }
                        Float j = D.j();
                        if (j != null) {
                            backgroundCountdown.e(j.floatValue());
                        }
                    }
                    z = true;
                }
            }
            Boolean x = dynamicConfig.x();
            if (x != null && this.f13091a.e(x.booleanValue())) {
                a(getRemainTime());
                z = true;
            }
            if (z) {
                a();
            } else if (z2) {
                invalidate();
            }
        }
    }
}
