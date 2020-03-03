package com.xiaomi.shopviews.adapter.countdown;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import com.xiaomi.shopviews.widget.R;

class BaseCountdown {
    private static final String W = ":";
    private static final float X = 3.0f;
    protected Paint A;
    protected Paint B;
    protected Paint C;
    protected float D;
    protected float E;
    protected float F;
    protected float G;
    protected float H;
    protected float I;
    protected float J;
    protected float K;
    protected float L;
    protected float M;
    protected float N;
    protected float O;
    protected float P;
    protected float Q;
    protected float R;
    protected float S;
    protected float T;
    protected float U;
    protected int V;
    private boolean Y;
    private boolean Z;

    /* renamed from: a  reason: collision with root package name */
    public int f13078a;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private float ae;
    private int af;
    private float ag;
    private boolean ah;
    private int ai;
    private float aj;
    private boolean ak;
    private float al;
    private float am;
    private float an;
    private float ao;
    private float ap;
    private float aq;
    private float ar;
    private float as;
    private float at;
    private float au;
    private float av;
    private float aw;
    private String ax;
    private String ay;
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    protected Context n;
    protected String o;
    protected String p;
    protected String q;
    protected String r;
    protected String s;
    protected String t;
    protected float u;
    protected float v;
    protected float w;
    protected float x;
    protected float y;
    protected boolean z;

    BaseCountdown() {
    }

    public void a(Context context, TypedArray typedArray) {
        this.n = context;
        this.ah = typedArray.getBoolean(R.styleable.CountdownView_isTimeTextBold, false);
        this.ag = typedArray.getDimension(R.styleable.CountdownView_timeTextSize, Utils.b(this.n, 12.0f));
        this.af = typedArray.getColor(R.styleable.CountdownView_timeTextColor, -16777216);
        this.f = typedArray.getBoolean(R.styleable.CountdownView_isShowDay, false);
        this.g = typedArray.getBoolean(R.styleable.CountdownView_isShowHour, false);
        this.h = typedArray.getBoolean(R.styleable.CountdownView_isShowMinute, true);
        this.i = typedArray.getBoolean(R.styleable.CountdownView_isShowSecond, true);
        this.j = typedArray.getBoolean(R.styleable.CountdownView_isShowMillisecond, false);
        if (typedArray.getBoolean(R.styleable.CountdownView_isHideTimeBackground, true)) {
            this.k = typedArray.getBoolean(R.styleable.CountdownView_isConvertDaysToHours, false);
        }
        this.ak = typedArray.getBoolean(R.styleable.CountdownView_isSuffixTextBold, false);
        this.aj = typedArray.getDimension(R.styleable.CountdownView_suffixTextSize, Utils.b(this.n, 12.0f));
        this.ai = typedArray.getColor(R.styleable.CountdownView_suffixTextColor, -16777216);
        this.o = typedArray.getString(R.styleable.CountdownView_suffix);
        this.p = typedArray.getString(R.styleable.CountdownView_suffixDay);
        this.q = typedArray.getString(R.styleable.CountdownView_suffixHour);
        this.r = typedArray.getString(R.styleable.CountdownView_suffixMinute);
        this.s = typedArray.getString(R.styleable.CountdownView_suffixSecond);
        this.t = typedArray.getString(R.styleable.CountdownView_suffixMillisecond);
        this.V = typedArray.getInt(R.styleable.CountdownView_suffixGravity, 1);
        this.ae = typedArray.getDimension(R.styleable.CountdownView_suffixLRMargin, -1.0f);
        this.E = typedArray.getDimension(R.styleable.CountdownView_suffixDayLeftMargin, -1.0f);
        this.F = typedArray.getDimension(R.styleable.CountdownView_suffixDayRightMargin, -1.0f);
        this.I = typedArray.getDimension(R.styleable.CountdownView_suffixHourLeftMargin, -1.0f);
        this.J = typedArray.getDimension(R.styleable.CountdownView_suffixHourRightMargin, -1.0f);
        this.K = typedArray.getDimension(R.styleable.CountdownView_suffixMinuteLeftMargin, -1.0f);
        this.L = typedArray.getDimension(R.styleable.CountdownView_suffixMinuteRightMargin, -1.0f);
        this.G = typedArray.getDimension(R.styleable.CountdownView_suffixSecondLeftMargin, -1.0f);
        this.H = typedArray.getDimension(R.styleable.CountdownView_suffixSecondRightMargin, -1.0f);
        this.M = typedArray.getDimension(R.styleable.CountdownView_suffixMillisecondLeftMargin, -1.0f);
        this.l = typedArray.hasValue(R.styleable.CountdownView_isShowDay);
        this.m = typedArray.hasValue(R.styleable.CountdownView_isShowHour);
        j();
        if (!this.f && !this.g && !this.h) {
            this.i = true;
        }
        if (!this.i) {
            this.j = false;
        }
    }

    public void e() {
        i();
        a();
        k();
        if (!this.i) {
            this.j = false;
        }
        b();
    }

    private void i() {
        this.Y = !TextUtils.isEmpty(this.p);
        this.Z = !TextUtils.isEmpty(this.q);
        this.aa = !TextUtils.isEmpty(this.r);
        this.ab = !TextUtils.isEmpty(this.s);
        this.ac = !TextUtils.isEmpty(this.t);
        if ((this.f && this.Y) || ((this.g && this.Z) || ((this.h && this.aa) || ((this.i && this.ab) || (this.j && this.ac))))) {
            this.ad = true;
        }
        this.ax = this.r;
        this.ay = this.s;
    }

    private void j() {
        this.ao = this.E;
        this.ap = this.F;
        this.aq = this.I;
        this.ar = this.J;
        this.as = this.K;
        this.at = this.L;
        this.au = this.G;
        this.av = this.H;
        this.aw = this.M;
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.A = new Paint(1);
        this.A.setColor(this.af);
        this.A.setTextAlign(Paint.Align.CENTER);
        this.A.setTextSize(this.ag);
        if (this.ah) {
            this.A.setFakeBoldText(true);
        }
        this.B = new Paint(1);
        this.B.setColor(this.ai);
        this.B.setTextSize(this.aj);
        if (this.ak) {
            this.B.setFakeBoldText(true);
        }
        this.C = new Paint();
        this.C.setTextSize(this.ag);
        if (this.ah) {
            this.C.setFakeBoldText(true);
        }
    }

    private void k() {
        float f2;
        boolean z2;
        float measureText = this.B.measureText(":");
        if (!TextUtils.isEmpty(this.o)) {
            z2 = false;
            f2 = this.B.measureText(this.o);
        } else {
            z2 = true;
            f2 = 0.0f;
        }
        if (!this.f) {
            this.u = 0.0f;
        } else if (this.Y) {
            this.u = this.B.measureText(this.p);
        } else if (!z2) {
            this.p = this.o;
            this.u = f2;
        } else if (!this.ad) {
            this.p = ":";
            this.u = measureText;
        }
        if (!this.g) {
            this.v = 0.0f;
        } else if (this.Z) {
            this.v = this.B.measureText(this.q);
        } else if (!z2) {
            this.q = this.o;
            this.v = f2;
        } else if (!this.ad) {
            this.q = ":";
            this.v = measureText;
        }
        if (!this.h) {
            this.w = 0.0f;
        } else if (this.aa) {
            this.w = this.B.measureText(this.r);
        } else if (!this.i) {
            this.w = 0.0f;
        } else if (!z2) {
            this.r = this.o;
            this.w = f2;
        } else if (!this.ad) {
            this.r = ":";
            this.w = measureText;
        }
        if (!this.i) {
            this.x = 0.0f;
        } else if (this.ab) {
            this.x = this.B.measureText(this.s);
        } else if (!this.j) {
            this.x = 0.0f;
        } else if (!z2) {
            this.s = this.o;
            this.x = f2;
        } else if (!this.ad) {
            this.s = ":";
            this.x = measureText;
        }
        if (!this.j || !this.ad || !this.ac) {
            this.y = 0.0f;
        } else {
            this.y = this.B.measureText(this.t);
        }
        l();
    }

    private void l() {
        int a2 = Utils.a(this.n, X);
        boolean z2 = this.ae < 0.0f;
        if (!this.f || this.u <= 0.0f) {
            this.E = 0.0f;
            this.F = 0.0f;
        } else {
            if (this.E < 0.0f) {
                if (!z2) {
                    this.E = this.ae;
                } else {
                    this.E = (float) a2;
                }
            }
            if (this.F < 0.0f) {
                if (!z2) {
                    this.F = this.ae;
                } else {
                    this.F = (float) a2;
                }
            }
        }
        if (!this.g || this.v <= 0.0f) {
            this.I = 0.0f;
            this.J = 0.0f;
        } else {
            if (this.I < 0.0f) {
                if (!z2) {
                    this.I = this.ae;
                } else {
                    this.I = (float) a2;
                }
            }
            if (this.J < 0.0f) {
                if (!z2) {
                    this.J = this.ae;
                } else {
                    this.J = (float) a2;
                }
            }
        }
        if (!this.h || this.w <= 0.0f) {
            this.K = 0.0f;
            this.L = 0.0f;
        } else {
            if (this.K < 0.0f) {
                if (!z2) {
                    this.K = this.ae;
                } else {
                    this.K = (float) a2;
                }
            }
            if (!this.i) {
                this.L = 0.0f;
            } else if (this.L < 0.0f) {
                if (!z2) {
                    this.L = this.ae;
                } else {
                    this.L = (float) a2;
                }
            }
        }
        if (this.i) {
            if (this.x > 0.0f) {
                if (this.G < 0.0f) {
                    if (!z2) {
                        this.G = this.ae;
                    } else {
                        this.G = (float) a2;
                    }
                }
                if (!this.j) {
                    this.H = 0.0f;
                } else if (this.H < 0.0f) {
                    if (!z2) {
                        this.H = this.ae;
                    } else {
                        this.H = (float) a2;
                    }
                }
            } else {
                this.G = 0.0f;
                this.H = 0.0f;
            }
            if (!this.j || this.y <= 0.0f) {
                this.M = 0.0f;
            } else if (this.M >= 0.0f) {
            } else {
                if (!z2) {
                    this.M = this.ae;
                } else {
                    this.M = (float) a2;
                }
            }
        } else {
            this.G = 0.0f;
            this.H = 0.0f;
            this.M = 0.0f;
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        Rect rect = new Rect();
        this.A.getTextBounds("00", 0, 2, rect);
        this.S = (float) rect.width();
        this.T = (float) rect.height();
        this.U = (float) rect.bottom;
    }

    private void a(int i2, int i3, int i4) {
        if (i3 == i4) {
            this.an = (((float) (i2 / 2)) + (this.T / 2.0f)) - this.U;
        } else {
            this.an = (((float) (i2 - (i2 - i3))) + this.T) - this.U;
        }
        if (this.f && this.u > 0.0f) {
            this.N = b(this.p);
        }
        if (this.g && this.v > 0.0f) {
            this.O = b(this.q);
        }
        if (this.h && this.w > 0.0f) {
            this.P = b(this.r);
        }
        if (this.x > 0.0f) {
            this.Q = b(this.s);
        }
        if (this.j && this.y > 0.0f) {
            this.R = b(this.t);
        }
    }

    private float b(String str) {
        Rect rect = new Rect();
        this.B.getTextBounds(str, 0, str.length(), rect);
        int i2 = this.V;
        if (i2 == 0) {
            return (this.an - this.T) - ((float) rect.top);
        }
        if (i2 != 2) {
            return (this.an - (this.T / 2.0f)) + ((float) (rect.height() / 2));
        }
        return this.an - ((float) rect.bottom);
    }

    /* access modifiers changed from: protected */
    public final float f(float f2) {
        float f3 = this.u + this.v + this.w + this.x + this.y + this.E + this.F + this.I + this.J + this.K + this.L + this.G + this.H + this.M;
        if (this.k) {
            f3 += m();
        } else if (this.g) {
            f3 += f2;
        }
        if (this.h) {
            f3 += f2;
        }
        if (this.i) {
            f3 += f2;
        }
        return this.j ? f3 + f2 : f3;
    }

    private float m() {
        Rect rect = new Rect();
        float f2 = 0.0f;
        if (this.f) {
            String a2 = Utils.a(this.f13078a);
            this.A.getTextBounds(a2, 0, a2.length(), rect);
            this.al = (float) rect.width();
            f2 = 0.0f + this.al;
        }
        if (!this.g) {
            return f2;
        }
        String a3 = Utils.a(this.b);
        this.C.getTextBounds(a3, 0, a3.length(), rect);
        this.am = (float) rect.width();
        return f2 + this.am;
    }

    public int c() {
        float f2 = f(this.S);
        if (!this.k && this.f) {
            if (this.z) {
                Rect rect = new Rect();
                String valueOf = String.valueOf(this.f13078a);
                this.A.getTextBounds(valueOf, 0, valueOf.length(), rect);
                this.al = (float) rect.width();
                f2 += this.al;
            } else {
                this.al = this.S;
                f2 += this.S;
            }
        }
        return (int) Math.ceil((double) f2);
    }

    public int d() {
        return (int) this.T;
    }

    public void a(View view, int i2, int i3, int i4, int i5) {
        a(i3, view.getPaddingTop(), view.getPaddingBottom());
        this.D = view.getPaddingLeft() == view.getPaddingRight() ? (float) ((i2 - i4) / 2) : (float) view.getPaddingLeft();
    }

    public void a(Canvas canvas) {
        float f2;
        if (this.f) {
            canvas.drawText(Utils.a(this.f13078a), this.D + (this.al / 2.0f), this.an, this.A);
            if (this.u > 0.0f) {
                canvas.drawText(this.p, this.D + this.al + this.E, this.N, this.B);
            }
            f2 = this.D + this.al + this.u + this.E + this.F;
        } else {
            f2 = this.D;
        }
        if (this.g) {
            float f3 = this.k ? this.am : this.S;
            canvas.drawText(Utils.a(this.b), (f3 / 2.0f) + f2, this.an, this.A);
            if (this.v > 0.0f) {
                canvas.drawText(this.q, f2 + f3 + this.I, this.O, this.B);
            }
            f2 = f2 + f3 + this.v + this.I + this.J;
        }
        if (this.h) {
            canvas.drawText(Utils.a(this.c), (this.S / 2.0f) + f2, this.an, this.A);
            if (this.w > 0.0f) {
                canvas.drawText(this.r, this.S + f2 + this.K, this.P, this.B);
            }
            f2 = f2 + this.S + this.w + this.K + this.L;
        }
        if (this.i) {
            canvas.drawText(Utils.a(this.d), (this.S / 2.0f) + f2, this.an, this.A);
            if (this.x > 0.0f) {
                canvas.drawText(this.s, this.S + f2 + this.G, this.Q, this.B);
            }
            if (this.j) {
                float f4 = f2 + this.S + this.x + this.G + this.H;
                canvas.drawText(Utils.b(this.e), (this.S / 2.0f) + f4, this.an, this.A);
                if (this.y > 0.0f) {
                    canvas.drawText(this.t, f4 + this.S + this.M, this.R, this.B);
                }
            }
        }
    }

    public boolean a(boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        boolean z7 = false;
        if (!z5) {
            z6 = false;
        }
        if (this.f != z2) {
            this.f = z2;
            if (z2) {
                this.E = this.ao;
                this.F = this.ap;
            }
        }
        if (this.g != z3) {
            this.g = z3;
            if (z3) {
                this.I = this.aq;
                this.J = this.ar;
            }
        }
        if (this.h != z4) {
            this.h = z4;
            if (z4) {
                this.K = this.as;
                this.L = this.at;
                this.r = this.ax;
            }
        }
        if (this.i != z5) {
            this.i = z5;
            if (z5) {
                this.G = this.au;
                this.H = this.av;
                this.s = this.ay;
            } else {
                this.r = this.ax;
            }
            this.K = this.as;
            this.L = this.at;
            z7 = true;
        }
        if (this.j == z6) {
            return z7;
        }
        this.j = z6;
        if (z6) {
            this.M = this.aw;
        } else {
            this.s = this.ay;
        }
        this.G = this.au;
        this.H = this.av;
        return true;
    }

    public boolean f() {
        if (!this.l) {
            if (this.f || this.f13078a <= 0) {
                if (this.f && this.f13078a == 0) {
                    a(false, this.g, this.h, this.i, this.j);
                    return true;
                } else if (!this.m) {
                    if (!this.g && (this.f13078a > 0 || this.b > 0)) {
                        a(this.f, true, this.h, this.i, this.j);
                        return true;
                    } else if (this.g && this.f13078a == 0 && this.b == 0) {
                        a(false, false, this.h, this.i, this.j);
                        return true;
                    }
                }
            } else if (!this.m) {
                a(true, true, this.h, this.i, this.j);
                return true;
            } else {
                a(true, this.g, this.h, this.i, this.j);
                return true;
            }
        } else if (!this.m) {
            if (!this.g && (this.f13078a > 0 || this.b > 0)) {
                a(this.f, true, this.h, this.i, this.j);
                return true;
            } else if (this.g && this.f13078a == 0 && this.b == 0) {
                a(this.f, false, this.h, this.i, this.j);
                return true;
            }
        }
        return false;
    }

    public boolean g() {
        if (this.f) {
            if (!this.z && this.f13078a > 99) {
                this.z = true;
                return true;
            } else if (this.z && this.f13078a <= 99) {
                this.z = false;
                return true;
            }
        }
        return false;
    }

    public void a(int i2, int i3, int i4, int i5, int i6) {
        this.f13078a = i2;
        this.b = i3;
        this.c = i4;
        this.d = i5;
        this.e = i6;
    }

    public void h() {
        k();
        b();
    }

    public void g(float f2) {
        if (f2 > 0.0f) {
            this.ag = Utils.b(this.n, f2);
            this.A.setTextSize(this.ag);
        }
    }

    public void d(int i2) {
        this.af = i2;
        this.A.setColor(this.af);
    }

    public void c(boolean z2) {
        this.ah = z2;
        this.A.setFakeBoldText(this.ah);
    }

    public void h(float f2) {
        if (f2 > 0.0f) {
            this.aj = Utils.b(this.n, f2);
            this.B.setTextSize(this.aj);
        }
    }

    public void e(int i2) {
        this.ai = i2;
        this.B.setColor(this.ai);
    }

    public void d(boolean z2) {
        this.ak = z2;
        this.B.setFakeBoldText(this.ak);
    }

    public void a(String str) {
        this.o = str;
        a(this.o, this.o, this.o, this.o, this.o);
    }

    public boolean e(boolean z2) {
        if (this.k == z2) {
            return false;
        }
        this.k = z2;
        return true;
    }

    public boolean a(String str, String str2, String str3, String str4, String str5) {
        boolean z2;
        if (str != null) {
            this.p = str;
            z2 = true;
        } else {
            z2 = false;
        }
        if (str2 != null) {
            this.q = str2;
            z2 = true;
        }
        if (str3 != null) {
            this.r = str3;
            z2 = true;
        }
        if (str4 != null) {
            this.s = str4;
            z2 = true;
        }
        if (str5 != null) {
            this.t = str5;
            z2 = true;
        }
        if (z2) {
            i();
        }
        return z2;
    }

    public void i(float f2) {
        this.ae = (float) Utils.a(this.n, f2);
        a(Float.valueOf(this.E), Float.valueOf(this.E), Float.valueOf(this.E), Float.valueOf(this.E), Float.valueOf(this.E), Float.valueOf(this.E), Float.valueOf(this.E), Float.valueOf(this.E), Float.valueOf(this.E));
    }

    public boolean a(Float f2, Float f3, Float f4, Float f5, Float f6, Float f7, Float f8, Float f9, Float f10) {
        boolean z2;
        if (f2 != null) {
            this.E = (float) Utils.a(this.n, f2.floatValue());
            z2 = true;
        } else {
            z2 = false;
        }
        if (f3 != null) {
            this.F = (float) Utils.a(this.n, f3.floatValue());
            z2 = true;
        }
        if (f4 != null) {
            this.I = (float) Utils.a(this.n, f4.floatValue());
            z2 = true;
        }
        if (f5 != null) {
            this.J = (float) Utils.a(this.n, f5.floatValue());
            z2 = true;
        }
        if (f6 != null) {
            this.K = (float) Utils.a(this.n, f6.floatValue());
            z2 = true;
        }
        if (f7 != null) {
            this.L = (float) Utils.a(this.n, f7.floatValue());
            z2 = true;
        }
        if (f8 != null) {
            this.G = (float) Utils.a(this.n, f8.floatValue());
            z2 = true;
        }
        if (f9 != null) {
            this.H = (float) Utils.a(this.n, f9.floatValue());
            z2 = true;
        }
        if (f10 != null) {
            this.M = (float) Utils.a(this.n, f10.floatValue());
            z2 = true;
        }
        if (z2) {
            j();
        }
        return z2;
    }

    public void f(int i2) {
        this.V = i2;
    }
}
