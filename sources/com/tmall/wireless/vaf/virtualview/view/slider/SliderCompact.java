package com.tmall.wireless.vaf.virtualview.view.slider;

import android.util.Log;
import com.libra.Utils;
import com.libra.expr.common.ExprCode;
import com.tmall.wireless.vaf.expr.engine.ExprEngine;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.NativeViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.view.slider.SliderView;
import org.json.JSONObject;

public class SliderCompact extends NativeViewBase implements SliderView.Listener {
    private static final String al = "Slider_TMTEST";
    protected SliderCompactImp ah;
    protected ExprCode ai;
    protected int aj;
    protected int ak;

    public boolean m() {
        return true;
    }

    public SliderCompact(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.ah = new SliderCompactImp(vafContext);
        this.f9383a = this.ah;
        this.ah.setListener(this);
    }

    public void e() {
        super.e();
        this.ah.reset();
    }

    public int b() {
        return this.aj;
    }

    public int am() {
        return this.ak;
    }

    public void c(Object obj) {
        this.ah.setData(obj);
        super.c(obj);
    }

    public void an() {
        if (this.ai != null) {
            ExprEngine i = this.W.i();
            if (i != null) {
                i.a().f().a((JSONObject) l().b());
            }
            if (i == null || !i.a(this, this.ai)) {
                Log.e(al, "callPageFlip execute failed");
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, ExprCode exprCode) {
        boolean b = super.b(i, exprCode);
        if (b) {
            return b;
        }
        if (i != 1490730380) {
            return false;
        }
        this.ai = exprCode;
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        if (b) {
            return b;
        }
        if (i == 3536714) {
            this.ah.setSpan(Utils.b((double) f));
            return true;
        } else if (i != 2146088563) {
            return false;
        } else {
            this.ah.setItemWidth(Utils.b((double) f));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        if (i == -1439500848) {
            this.ah.setOrientation(i2);
            return true;
        } else if (i == 3536714) {
            this.ah.setSpan(Utils.b((double) i2));
            return true;
        } else if (i != 2146088563) {
            return false;
        } else {
            this.ah.setItemWidth(Utils.b((double) i2));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, float f) {
        boolean a2 = super.a(i, f);
        if (a2) {
            return a2;
        }
        if (i == 3536714) {
            this.ah.setSpan(Utils.a((double) f));
            return true;
        } else if (i != 2146088563) {
            return false;
        } else {
            this.ah.setItemWidth(Utils.a((double) f));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) {
        boolean b = super.b(i, i2);
        if (b) {
            return b;
        }
        if (i == 3536714) {
            this.ah.setSpan(Utils.a((double) i2));
            return true;
        } else if (i != 2146088563) {
            return false;
        } else {
            this.ah.setItemWidth(Utils.a((double) i2));
            return true;
        }
    }

    public void i(int i, int i2) {
        this.aj = i;
        this.ak = i2;
        an();
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new SliderCompact(vafContext, viewCache);
        }
    }
}
