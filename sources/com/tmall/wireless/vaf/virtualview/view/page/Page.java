package com.tmall.wireless.vaf.virtualview.view.page;

import android.support.annotation.Keep;
import android.util.Log;
import com.libra.expr.common.ExprCode;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.expr.engine.ExprEngine;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.IBean;
import com.tmall.wireless.vaf.virtualview.core.NativeViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import com.tmall.wireless.vaf.virtualview.view.page.PageView;
import org.json.JSONObject;

public class Page extends NativeViewBase implements PageView.Listener {
    private static final String am = "Page_TMTEST";
    protected PageImp ah;
    protected ExprCode ai;
    protected int aj = 0;
    protected int ak = 0;
    protected int al;

    public boolean m() {
        return true;
    }

    public Page(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.ah = new PageImp(vafContext);
        this.f9383a = this.ah;
        this.ah.setListener(this);
    }

    public void e() {
        super.e();
        this.ah.reset();
    }

    public int b() {
        return this.ak;
    }

    public int am() {
        return this.aj;
    }

    public int an() {
        return this.al;
    }

    public int ao() {
        return this.ah.size();
    }

    public void ap() {
        this.W.e().a(3, new EventData(this.W, this));
        if (this.ai != null) {
            ExprEngine i = this.W.i();
            if (i != null) {
                try {
                    i.a().f().a((JSONObject) l().b());
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (i == null || !i.a(this, this.ai)) {
                Log.e(am, "callPageFlip execute failed");
            }
        }
    }

    public void c(Object obj) {
        this.ah.setData(obj);
        super.c(obj);
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, ExprCode exprCode) {
        boolean b = super.b(i, exprCode);
        if (b) {
            return b;
        }
        if (i != -665970021) {
            return false;
        }
        this.ai = exprCode;
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        boolean z = false;
        if (c) {
            return c;
        }
        switch (i) {
            case StringBase.y:
                PageImp pageImp = this.ah;
                if (1 == i2) {
                    z = true;
                }
                pageImp.setOrientation(z);
                break;
            case StringBase.aN:
                this.ah.setAnimationStyle(i2);
                break;
            case StringBase.aI:
                PageImp pageImp2 = this.ah;
                if (i2 > 0) {
                    z = true;
                }
                pageImp2.setAutoSwitch(z);
                break;
            case StringBase.aJ:
                PageImp pageImp3 = this.ah;
                if (i2 > 0) {
                    z = true;
                }
                pageImp3.setSlide(z);
                break;
            case StringBase.aM:
                this.ah.setAutoSwitchTimeInterval(i2);
                break;
            case StringBase.bl:
                this.ah.setContainerId(i2);
                break;
            case StringBase.aK:
                this.ah.setStayTime(i2);
                break;
            case StringBase.aL:
                this.ah.setAnimatorTimeInterval(i2);
                break;
            case StringBase.aO:
                PageImp pageImp4 = this.ah;
                if (i2 == 0) {
                    z = true;
                }
                pageImp4.setLayoutOrientation(z);
                break;
            default:
                return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        boolean a2 = super.a(i, str);
        if (a2) {
            return a2;
        }
        switch (i) {
            case StringBase.aI:
                this.c.a(this, StringBase.aI, str, 4);
                return true;
            case StringBase.aJ:
                this.c.a(this, StringBase.aJ, str, 4);
                return true;
            case StringBase.aM:
                this.c.a(this, StringBase.aM, str, 0);
                return true;
            case StringBase.aK:
                this.c.a(this, StringBase.aK, str, 0);
                return true;
            case StringBase.aL:
                this.c.a(this, StringBase.aL, str, 0);
                return true;
            default:
                return false;
        }
    }

    public void i(int i, int i2) {
        this.ak = this.aj;
        this.aj = i - 1;
        this.al = i2;
        aq();
        ap();
    }

    @Keep
    public void onScroll(int i) {
        Log.d(am, "page scroll " + i);
    }

    private void aq() {
        IBean C = C();
        if (C != null) {
            C.a(3, 0, (Object) null);
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new Page(vafContext, viewCache);
        }
    }
}
