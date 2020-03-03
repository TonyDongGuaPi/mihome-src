package com.tmall.wireless.vaf.virtualview.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.libra.Utils;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.core.VirtualViewBase;

public class VirtualGraph extends VirtualViewBase {
    private static final String am = "VirtualGraph_TMTEST";
    protected RectF ah;
    protected int ai;
    protected int aj;
    protected int ak;
    protected int al = 1;

    public VirtualGraph(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.f9392a.a(true);
    }

    public void n(int i) {
        i(i, 1);
    }

    public void i(int i, int i2) {
        this.al = i;
        if (i2 > 0) {
            W();
        }
    }

    public void o(int i) {
        j(i, 1);
    }

    public void j(int i, int i2) {
        this.ak = i;
        if (i2 > 0) {
            W();
        }
    }

    public void p(int i) {
        k(i, 1);
    }

    public void k(int i, int i2) {
        this.aj = i;
        if (1 == this.aj) {
            this.ak = this.aj;
        }
        if (i2 > 0) {
            W();
        }
    }

    public void q(int i) {
        l(i, 1);
    }

    public void l(int i, int i2) {
        this.ai = i;
        this.j.setColor(this.ai);
        if (i2 > 0) {
            W();
        }
    }

    /* access modifiers changed from: protected */
    public void aj() {
        if (this.Y == null) {
            this.Y = new Rect(0, 0, this.aj, this.ak);
        } else {
            this.Y.set(0, 0, this.aj, this.ak);
        }
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
        super.b(canvas);
        int i = this.J;
        int i2 = this.aj;
        int i3 = this.ak;
        if (this.aj <= 0) {
            i2 = (this.O - this.J) - this.K;
        } else if ((this.N & 2) != 0) {
            i = (this.O - this.K) - this.aj;
        } else if ((this.N & 4) != 0) {
            i = (this.O - this.aj) >> 1;
        }
        int i4 = this.L;
        if (this.ak <= 0) {
            i3 = (this.P - this.L) - this.M;
        } else if ((this.N & 16) != 0) {
            i4 = (this.P - this.M) - this.ak;
        } else if ((this.N & 32) != 0) {
            i4 = (this.P - this.ak) >> 1;
        }
        switch (this.al) {
            case 1:
                int i5 = i2 >> 1;
                canvas.drawCircle((float) (i + i5), (float) (i4 + i5), (float) i5, this.j);
                return;
            case 2:
                float f = (float) i4;
                canvas.drawRect((float) i, f, (float) (i + i2), (float) (i4 + i3), this.j);
                return;
            case 3:
                if (this.ah == null) {
                    this.ah = new RectF();
                }
                this.ah.set((float) i, (float) i4, (float) (i + i2), (float) (i4 + i3));
                canvas.drawOval(this.ah, this.j);
                return;
            default:
                return;
        }
    }

    public void f() {
        super.f();
        if (1 == this.al) {
            this.ak = this.aj;
        }
        this.j.setColor(this.ai);
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        if (b) {
            return b;
        }
        if (i != 793104392) {
            switch (i) {
                case StringBase.aY:
                    this.aj = Utils.b((double) f);
                    return true;
                case StringBase.aZ:
                    this.ak = Utils.b((double) f);
                    return true;
                default:
                    return false;
            }
        } else {
            this.j.setStrokeWidth((float) Utils.b((double) f));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        switch (i) {
            case StringBase.D:
                this.al = i2;
                return true;
            case StringBase.G:
                this.ai = i2;
                return true;
            case StringBase.by:
                switch (i2) {
                    case 1:
                        this.j.setStyle(Paint.Style.STROKE);
                        return true;
                    case 2:
                        this.j.setStyle(Paint.Style.FILL);
                        return true;
                    default:
                        return true;
                }
            case StringBase.aQ:
                this.j.setStrokeWidth((float) Utils.b((double) i2));
                return true;
            case StringBase.aY:
                this.aj = Utils.b((double) i2);
                return true;
            case StringBase.aZ:
                this.ak = Utils.b((double) i2);
                return true;
            default:
                return false;
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VirtualGraph(vafContext, viewCache);
        }
    }
}
