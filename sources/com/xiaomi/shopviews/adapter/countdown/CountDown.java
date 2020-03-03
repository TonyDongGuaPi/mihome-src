package com.xiaomi.shopviews.adapter.countdown;

import android.view.View;
import com.libra.Utils;
import com.taobao.weex.common.Constants;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.loader.StringLoader;
import com.xiaomi.shopviews.adapter.countdown.DynamicConfig;

public class CountDown extends ViewBase {

    /* renamed from: a  reason: collision with root package name */
    protected CountdownVirtualView f13082a;
    private int aA;
    private int aB;
    private int aC;
    private int aD;
    private int aE;
    private int aF;
    private int aG;
    private int aH;
    private int aI;
    private int aJ;
    private int aK;
    private int aL;
    private int aM;
    private int aN;
    private int aO;
    private int aP;
    private int aQ;
    private int aR;
    private int aS;
    private int aT;
    private int aU;
    private boolean aV;
    private boolean aW;
    private int aX;
    private int aY;
    private int aZ;
    private int ah;
    private int ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private int an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private int as;
    private int at;
    private int au;
    private int av;
    private int aw;
    private int ax;
    private int ay;
    private int az;
    private int bA;
    private int bB;
    private int bC;
    private int bD;
    private int bE;
    private boolean bF;
    private int bG;
    private int bH;
    private int bI;
    private int ba;
    private int bb;
    private boolean bc;
    private int bd;
    private int be;
    private boolean bf;
    private boolean bg;
    private boolean bh;
    private boolean bi;
    private boolean bj;
    private boolean bk;
    private boolean bl;
    private int bm;
    private String bn;
    private boolean bo;
    private String bp;
    private String bq;
    private String br;
    private String bs;
    private String bt;
    private Object bu;
    private int bv;
    private int bw;
    private int bx;
    private int by;
    private int bz;

    public CountDown(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.f13082a = new CountdownVirtualView(vafContext.m());
        StringLoader o = vafContext.o();
        this.ah = o.a("isHideTimeBackground", false);
        this.ai = o.a("isShowTimeBgDivisionLine", false);
        this.aj = o.a("timeBgDivisionLineColor", false);
        this.ak = o.a("timeBgDivisionLineSize", false);
        this.al = o.a("timeBgColor", false);
        this.am = o.a("timeBgSize", false);
        this.an = o.a("timeBgRadius", false);
        this.ao = o.a("isTimeTextBold", false);
        this.ap = o.a("timeTextSize", false);
        this.aq = o.a("timeTextColor", false);
        this.ar = o.a("isShowDay", false);
        this.as = o.a("isShowHour", false);
        this.at = o.a("isShowMinute", false);
        this.au = o.a("isShowSecond", false);
        this.av = o.a("isShowMillisecond", false);
        this.aw = o.a("isConvertDaysToHours", false);
        this.ax = o.a("isSuffixTextBold", false);
        this.ay = o.a("suffixTextSize", false);
        this.az = o.a("suffixTextColor", false);
        this.aA = o.a(Constants.Name.SUFFIX, false);
        this.aB = o.a("suffixDay", false);
        this.aC = o.a("suffixHour", false);
        this.aD = o.a("suffixMinute", false);
        this.aE = o.a("suffixSecond", false);
        this.aF = o.a("suffixMillisecond", false);
        this.aG = o.a("suffixGravity", false);
        this.aH = o.a("suffixLRMargin", false);
        this.aI = o.a("suffixDayLeftMargin", false);
        this.aJ = o.a("suffixDayRightMargin", false);
        this.aK = o.a("suffixHourLeftMargin", false);
        this.aL = o.a("suffixHourRightMargin", false);
        this.aM = o.a("suffixMinuteLeftMargin", false);
        this.aN = o.a("suffixMinuteRightMargin", false);
        this.aO = o.a("suffixSecondLeftMargin", false);
        this.aP = o.a("suffixSecondRightMargin", false);
        this.aQ = o.a("suffixMillisecondLeftMargin", false);
        this.aR = o.a("isShowTimeBgBorder", false);
        this.aS = o.a("timeBgBorderColor", false);
        this.aT = o.a("timeBgBorderSize", false);
        this.aU = o.a("timeBgBorderRadius", false);
    }

    public void onComMeasure(int i, int i2) {
        this.f13082a.onComMeasure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f13082a.onComLayout(z, i, i2, i3, i4);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        super.comLayout(i, i2, i3, i4);
        this.f13082a.comLayout(i, i2, i3, i4);
    }

    public View g_() {
        return this.f13082a;
    }

    public int getComMeasuredWidth() {
        return this.f13082a.getComMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        return this.f13082a.getComMeasuredHeight();
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        if (i == this.ah) {
            if (Utils.a(str)) {
                this.c.a(this, this.ah, str, 4);
            }
        } else if (i == this.ai) {
            if (Utils.a(str)) {
                this.c.a(this, this.ai, str, 4);
            }
        } else if (i == this.aj) {
            if (Utils.a(str)) {
                this.c.a(this, this.aj, str, 3);
            }
        } else if (i == this.ar) {
            if (Utils.a(str)) {
                this.c.a(this, this.ar, str, 4);
            }
        } else if (i == this.as) {
            if (Utils.a(str)) {
                this.c.a(this, this.as, str, 4);
            }
        } else if (i == this.av) {
            if (Utils.a(str)) {
                this.c.a(this, this.av, str, 4);
            }
        } else if (i == this.at) {
            if (Utils.a(str)) {
                this.c.a(this, this.at, str, 4);
            }
        } else if (i == this.au) {
            if (Utils.a(str)) {
                this.c.a(this, this.au, str, 4);
            }
        } else if (i == this.aR) {
            if (Utils.a(str)) {
                this.c.a(this, this.aR, str, 4);
            }
        } else if (i == this.aS) {
            if (Utils.a(str)) {
                this.c.a(this, this.aS, str, 3);
            }
        } else if (i == this.aU) {
            if (Utils.a(str)) {
                this.c.a(this, this.aU, str, 0);
            }
        } else if (i == this.aT) {
            if (Utils.a(str)) {
                this.c.a(this, this.aT, str, 0);
            }
        } else if (i == this.al) {
            if (Utils.a(str)) {
                this.c.a(this, this.al, str, 3);
            }
        } else if (i == this.ak) {
            if (Utils.a(str)) {
                this.c.a(this, this.ak, str, 0);
            }
        } else if (i == this.an) {
            if (Utils.a(str)) {
                this.c.a(this, this.an, str, 0);
            }
        } else if (i == this.am) {
            if (Utils.a(str)) {
                this.c.a(this, this.am, str, 0);
            }
        } else if (i == this.aq) {
            if (Utils.a(str)) {
                this.c.a(this, this.aq, str, 3);
            }
        } else if (i != this.ap) {
            return super.a(i, str);
        } else {
            if (Utils.a(str)) {
                this.c.a(this, this.ap, str, 0);
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        if (i == this.aU) {
            this.bI = i2;
            return true;
        } else if (i == this.aT) {
            this.bH = i2;
            return true;
        } else if (i == this.ak) {
            this.aY = i2;
            return true;
        } else if (i == this.an) {
            this.bb = i2;
            return true;
        } else if (i == this.am) {
            this.ba = i2;
            return true;
        } else if (i == this.ap) {
            this.bd = i2;
            return true;
        } else {
            boolean z = false;
            if (i == this.ah) {
                if (i2 == 1) {
                    z = true;
                }
                this.aV = z;
                return true;
            } else if (i == this.ai) {
                if (i2 == 1) {
                    z = true;
                }
                this.aW = z;
                return true;
            } else if (i == this.ar) {
                if (i2 == 1) {
                    z = true;
                }
                this.bf = z;
                return true;
            } else if (i == this.as) {
                if (i2 == 1) {
                    z = true;
                }
                this.bg = z;
                return true;
            } else if (i == this.av) {
                if (i2 == 1) {
                    z = true;
                }
                this.bj = z;
                return true;
            } else if (i == this.at) {
                if (i2 == 1) {
                    z = true;
                }
                this.bh = z;
                return true;
            } else if (i == this.au) {
                if (i2 == 1) {
                    z = true;
                }
                this.bi = z;
                return true;
            } else if (i == this.aR) {
                if (i2 == 1) {
                    z = true;
                }
                this.bF = z;
                return true;
            } else if (i == this.aj) {
                this.aX = i2;
                return true;
            } else if (i == this.aS) {
                this.bG = i2;
                return true;
            } else if (i == this.al) {
                this.aZ = i2;
                return true;
            } else if (i != this.aq) {
                return super.c(i, i2);
            } else {
                this.be = i2;
                return true;
            }
        }
    }

    public void e() {
        super.e();
    }

    public void f() {
        super.f();
        DynamicConfig.Builder builder = new DynamicConfig.Builder();
        builder.b(Boolean.valueOf(this.bf));
        builder.c(Boolean.valueOf(this.bg));
        builder.f(Boolean.valueOf(this.bj));
        builder.d(Boolean.valueOf(this.bh));
        builder.e(Boolean.valueOf(this.bi));
        builder.a(this.be);
        builder.b((float) this.bd);
        DynamicConfig.BackgroundInfo backgroundInfo = new DynamicConfig.BackgroundInfo();
        backgroundInfo.b(Boolean.valueOf(this.bF));
        backgroundInfo.c(Integer.valueOf(this.bG));
        backgroundInfo.e(Float.valueOf((float) this.bI));
        backgroundInfo.d(Float.valueOf((float) this.bH));
        backgroundInfo.a(Integer.valueOf(this.aZ));
        backgroundInfo.c(Float.valueOf((float) this.aY));
        backgroundInfo.b(Float.valueOf((float) this.bb));
        backgroundInfo.a(Float.valueOf((float) this.ba));
        builder.a(backgroundInfo);
        this.f13082a.isHideTimeBackground = this.aV;
        this.f13082a.dynamicShow(builder.a());
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new CountDown(vafContext, viewCache);
        }
    }
}
