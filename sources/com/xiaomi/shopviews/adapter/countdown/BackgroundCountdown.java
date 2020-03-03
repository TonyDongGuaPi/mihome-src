package com.xiaomi.shopviews.adapter.countdown;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import com.libra.Color;
import com.xiaomi.shopviews.widget.R;

class BackgroundCountdown extends BaseCountdown {
    private static final float W = 0.5f;
    private static final float X = 1.0f;
    private boolean Y;
    private boolean Z;
    private int aa;
    private float ab;
    private float ac;
    private float ad;
    private int ae;
    private Paint af;
    private Paint ag;
    private Paint ah;
    private float ai;
    private float aj;
    private RectF ak;
    private RectF al;
    private RectF am;
    private RectF an;
    private RectF ao;
    private RectF ap;
    private RectF aq;
    private RectF ar;
    private RectF as;
    private RectF at;
    private float au;
    private float av;
    private boolean aw;
    private float ax;
    private float ay;
    private int az;

    BackgroundCountdown() {
    }

    public void a(Context context, TypedArray typedArray) {
        super.a(context, typedArray);
        this.ae = typedArray.getColor(R.styleable.CountdownView_timeBgColor, Color.b);
        this.ac = typedArray.getDimension(R.styleable.CountdownView_timeBgRadius, 0.0f);
        this.Z = typedArray.getBoolean(R.styleable.CountdownView_isShowTimeBgDivisionLine, true);
        this.aa = typedArray.getColor(R.styleable.CountdownView_timeBgDivisionLineColor, android.graphics.Color.parseColor("#30FFFFFF"));
        this.ab = typedArray.getDimension(R.styleable.CountdownView_timeBgDivisionLineSize, (float) Utils.a(context, 0.5f));
        this.ad = typedArray.getDimension(R.styleable.CountdownView_timeBgSize, 0.0f);
        this.ai = this.ad;
        this.ax = typedArray.getDimension(R.styleable.CountdownView_timeBgBorderSize, (float) Utils.a(context, 1.0f));
        this.ay = typedArray.getDimension(R.styleable.CountdownView_timeBgBorderRadius, 0.0f);
        this.az = typedArray.getColor(R.styleable.CountdownView_timeBgBorderColor, -16777216);
        boolean z = false;
        this.aw = typedArray.getBoolean(R.styleable.CountdownView_isShowTimeBgBorder, false);
        if (typedArray.hasValue(R.styleable.CountdownView_timeBgColor) || !this.aw) {
            z = true;
        }
        this.Y = z;
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.af = new Paint(1);
        this.af.setStyle(Paint.Style.FILL);
        this.af.setColor(this.ae);
        if (this.aw) {
            i();
        }
        if (this.Z) {
            j();
        }
    }

    private void i() {
        if (this.ag == null) {
            this.ag = new Paint(1);
            this.ag.setColor(this.az);
            if (!this.Y) {
                this.ag.setStrokeWidth(this.ax);
                this.ag.setStyle(Paint.Style.STROKE);
            }
        }
    }

    private void j() {
        if (this.ah == null) {
            this.ah = new Paint(1);
            this.ah.setColor(this.aa);
            this.ah.setStrokeWidth(this.ab);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        super.b();
        if (this.ai == 0.0f || this.ad < this.S) {
            this.ad = this.S + ((float) (Utils.a(this.n, 2.0f) * 4));
        }
    }

    private void j(float f) {
        float f2;
        boolean z = false;
        if (this.f) {
            if (this.aw) {
                this.ap = new RectF(this.D, f, this.D + this.aj + (this.ax * 2.0f), this.ad + f + (this.ax * 2.0f));
                this.ak = new RectF(this.D + this.ax, this.ax + f, this.D + this.aj + this.ax, this.ad + f + this.ax);
            } else {
                this.ak = new RectF(this.D, f, this.D + this.aj, this.ad + f);
            }
            f2 = this.D + this.aj + this.u + this.E + this.F + (this.ax * 2.0f);
            if (!this.g && !this.h && !this.i) {
                a(this.ak);
                z = true;
            }
        } else {
            f2 = this.D;
        }
        if (this.g) {
            if (this.aw) {
                this.aq = new RectF(f2, f, this.ad + f2 + (this.ax * 2.0f), this.ad + f + (this.ax * 2.0f));
                this.al = new RectF(this.ax + f2, this.ax + f, this.ad + f2 + this.ax, this.ad + f + this.ax);
            } else {
                this.al = new RectF(f2, f, this.ad + f2, this.ad + f);
            }
            f2 = f2 + this.ad + this.v + this.I + this.J + (this.ax * 2.0f);
            if (!z) {
                a(this.al);
                z = true;
            }
        }
        if (this.h) {
            if (this.aw) {
                this.ar = new RectF(f2, f, this.ad + f2 + (this.ax * 2.0f), this.ad + f + (this.ax * 2.0f));
                this.am = new RectF(this.ax + f2, this.ax + f, this.ad + f2 + this.ax, this.ad + f + this.ax);
            } else {
                this.am = new RectF(f2, f, this.ad + f2, this.ad + f);
            }
            f2 = f2 + this.ad + this.w + this.K + this.L + (this.ax * 2.0f);
            if (!z) {
                a(this.am);
                z = true;
            }
        }
        if (this.i) {
            if (this.aw) {
                this.as = new RectF(f2, f, this.ad + f2 + (this.ax * 2.0f), this.ad + f + (this.ax * 2.0f));
                this.an = new RectF(this.ax + f2, this.ax + f, this.ad + f2 + this.ax, this.ad + f + this.ax);
            } else {
                this.an = new RectF(f2, f, this.ad + f2, this.ad + f);
            }
            if (this.j) {
                float f3 = f2 + this.ad + this.x + this.G + this.H + (this.ax * 2.0f);
                if (this.aw) {
                    this.at = new RectF(f3, f, this.ad + f3 + (this.ax * 2.0f), this.ad + f + (this.ax * 2.0f));
                    this.ao = new RectF(this.ax + f3, this.ax + f, f3 + this.ad + this.ax, f + this.ad + this.ax);
                } else {
                    this.ao = new RectF(f3, f, this.ad + f3, this.ad + f);
                }
            }
            if (!z) {
                a(this.an);
            }
        }
    }

    private float a(String str, float f) {
        Rect rect = new Rect();
        this.B.getTextBounds(str, 0, str.length(), rect);
        int i = this.V;
        if (i == 0) {
            return f - ((float) rect.top);
        }
        if (i != 2) {
            return ((f + this.ad) - (this.ad / 2.0f)) + ((float) (rect.height() / 2)) + this.ax;
        }
        return ((f + this.ad) - ((float) rect.bottom)) + (this.ax * 2.0f);
    }

    private void a(RectF rectF) {
        Paint.FontMetrics fontMetrics = this.A.getFontMetrics();
        this.av = ((rectF.top + ((((rectF.bottom - rectF.top) - fontMetrics.bottom) + fontMetrics.top) / 2.0f)) - fontMetrics.top) - this.U;
        this.au = rectF.centerY() + (this.ab == ((float) Utils.a(this.n, 0.5f)) ? this.ab : this.ab / 2.0f);
    }

    private float a(int i, int i2, int i3, int i4) {
        float f = i2 == i3 ? (float) ((i - i4) / 2) : (float) i2;
        if (this.f && this.u > 0.0f) {
            this.N = a(this.p, f);
        }
        if (this.g && this.v > 0.0f) {
            this.O = a(this.q, f);
        }
        if (this.h && this.w > 0.0f) {
            this.P = a(this.r, f);
        }
        if (this.x > 0.0f) {
            this.Q = a(this.s, f);
        }
        if (this.j && this.y > 0.0f) {
            this.R = a(this.t, f);
        }
        return f;
    }

    public int c() {
        float f;
        float f2 = f(this.ad + (this.ax * 2.0f));
        if (this.f) {
            if (this.z) {
                Rect rect = new Rect();
                String valueOf = String.valueOf(this.f13078a);
                this.A.getTextBounds(valueOf, 0, valueOf.length(), rect);
                this.aj = (float) (rect.width() + (Utils.a(this.n, 2.0f) * 4));
                f = f2 + this.aj;
            } else {
                this.aj = this.ad;
                f = f2 + this.ad;
            }
            f2 = f + (this.ax * 2.0f);
        }
        return (int) Math.ceil((double) f2);
    }

    public int d() {
        return (int) (this.ad + (this.ax * 2.0f));
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        float a2 = a(i2, view.getPaddingTop(), view.getPaddingBottom(), i4);
        this.D = view.getPaddingLeft() == view.getPaddingRight() ? (float) ((i - i3) / 2) : (float) view.getPaddingLeft();
        j(a2);
    }

    public void a(Canvas canvas) {
        float f;
        if (this.f) {
            if (this.aw && this.ap != null) {
                canvas.drawRoundRect(this.ap, this.ay, this.ay, this.ag);
            }
            if (this.Y) {
                canvas.drawRoundRect(this.ak, this.ac, this.ac, this.af);
                if (this.Z) {
                    canvas.drawLine(this.D + this.ax, this.au, this.D + this.aj + this.ax, this.au, this.ah);
                }
            }
            canvas.drawText(Utils.a(this.f13078a), this.ak.centerX(), this.av, this.A);
            if (this.u > 0.0f) {
                canvas.drawText(this.p, this.D + this.aj + this.E + (this.ax * 2.0f), this.N, this.B);
            }
            f = this.D + this.aj + this.u + this.E + this.F + (this.ax * 2.0f);
        } else {
            f = this.D;
        }
        if (this.g) {
            if (this.aw) {
                canvas.drawRoundRect(this.aq, this.ay, this.ay, this.ag);
            }
            if (this.Y) {
                canvas.drawRoundRect(this.al, this.ac, this.ac, this.af);
                if (this.Z) {
                    canvas.drawLine(f + this.ax, this.au, this.ad + f + this.ax, this.au, this.ah);
                }
            }
            canvas.drawText(Utils.a(this.b), this.al.centerX(), this.av, this.A);
            if (this.v > 0.0f) {
                canvas.drawText(this.q, this.ad + f + this.I + (this.ax * 2.0f), this.O, this.B);
            }
            f = f + this.ad + this.v + this.I + this.J + (this.ax * 2.0f);
        }
        if (this.h) {
            if (this.aw) {
                canvas.drawRoundRect(this.ar, this.ay, this.ay, this.ag);
            }
            if (this.Y) {
                canvas.drawRoundRect(this.am, this.ac, this.ac, this.af);
                if (this.Z) {
                    canvas.drawLine(f + this.ax, this.au, this.ad + f + this.ax, this.au, this.ah);
                }
            }
            canvas.drawText(Utils.a(this.c), this.am.centerX(), this.av, this.A);
            if (this.w > 0.0f) {
                canvas.drawText(this.r, this.ad + f + this.K + (this.ax * 2.0f), this.P, this.B);
            }
            f = f + this.ad + this.w + this.K + this.L + (this.ax * 2.0f);
        }
        if (this.i) {
            if (this.aw) {
                canvas.drawRoundRect(this.as, this.ay, this.ay, this.ag);
            }
            if (this.Y) {
                canvas.drawRoundRect(this.an, this.ac, this.ac, this.af);
                if (this.Z) {
                    canvas.drawLine(f + this.ax, this.au, this.ad + f + this.ax, this.au, this.ah);
                }
            }
            canvas.drawText(Utils.a(this.d), this.an.centerX(), this.av, this.A);
            if (this.x > 0.0f) {
                canvas.drawText(this.s, this.ad + f + this.G + (this.ax * 2.0f), this.Q, this.B);
            }
            if (this.j) {
                if (this.aw) {
                    canvas.drawRoundRect(this.at, this.ay, this.ay, this.ag);
                }
                float f2 = f + this.ad + this.x + this.G + this.H + (this.ax * 2.0f);
                if (this.Y) {
                    canvas.drawRoundRect(this.ao, this.ac, this.ac, this.af);
                    if (this.Z) {
                        canvas.drawLine(f2 + this.ax, this.au, this.ad + f2 + this.ax, this.au, this.ah);
                    }
                }
                canvas.drawText(Utils.b(this.e), this.ao.centerX(), this.av, this.A);
                if (this.y > 0.0f) {
                    canvas.drawText(this.t, f2 + this.ad + this.M + (this.ax * 2.0f), this.R, this.B);
                }
            }
        }
    }

    public void a(float f) {
        this.ad = (float) Utils.a(this.n, f);
    }

    public void a(int i) {
        this.ae = i;
        this.af.setColor(this.ae);
        if (i != 0 || !this.aw) {
            this.Y = true;
            if (this.aw) {
                this.ag.setStrokeWidth(0.0f);
                this.ag.setStyle(Paint.Style.FILL);
                return;
            }
            return;
        }
        this.Y = false;
        this.ag.setStrokeWidth(this.ax);
        this.ag.setStyle(Paint.Style.STROKE);
    }

    public void b(float f) {
        this.ac = (float) Utils.a(this.n, f);
    }

    public void a(boolean z) {
        this.Z = z;
        if (this.Z) {
            j();
        } else {
            this.ah = null;
        }
    }

    public void b(int i) {
        this.aa = i;
        if (this.ah != null) {
            this.ah.setColor(this.aa);
        }
    }

    public void c(float f) {
        this.ab = (float) Utils.a(this.n, f);
        if (this.ah != null) {
            this.ah.setStrokeWidth(this.ab);
        }
    }

    public void b(boolean z) {
        this.aw = z;
        if (this.aw) {
            i();
            return;
        }
        this.ag = null;
        this.ax = 0.0f;
    }

    public void c(int i) {
        this.az = i;
        if (this.ag != null) {
            this.ag.setColor(this.az);
        }
    }

    public void d(float f) {
        this.ax = (float) Utils.a(this.n, f);
        if (this.ag != null && !this.Y) {
            this.ag.setStrokeWidth(this.ax);
            this.ag.setStyle(Paint.Style.STROKE);
        }
    }

    public void e(float f) {
        this.ay = (float) Utils.a(this.n, f);
    }
}
