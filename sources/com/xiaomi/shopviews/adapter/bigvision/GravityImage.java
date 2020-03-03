package com.xiaomi.shopviews.adapter.bigvision;

import android.view.View;
import com.libra.Utils;
import com.taobao.weex.common.Constants;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.loader.StringLoader;

public class GravityImage extends ViewBase {

    /* renamed from: a  reason: collision with root package name */
    protected GravityImageVirtualView f13075a;
    private int ah;
    private int ai;
    private int aj;
    private boolean ak;
    private boolean al;
    private boolean am;

    public GravityImage(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.f13075a = new GravityImageVirtualView(vafContext.m());
        StringLoader o = vafContext.o();
        this.ah = o.a("enablePanoramaMode", false);
        this.ai = o.a("invertScrollDirection", false);
        this.aj = o.a(Constants.Name.SHOW_SCROLLBAR, false);
    }

    public void onComMeasure(int i, int i2) {
        this.f13075a.onComMeasure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f13075a.onComLayout(z, i, i2, i3, i4);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        super.comLayout(i, i2, i3, i4);
        this.f13075a.comLayout(i, i2, i3, i4);
    }

    public View g_() {
        return this.f13075a;
    }

    public int getComMeasuredWidth() {
        return this.f13075a.getComMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        return this.f13075a.getComMeasuredHeight();
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        if (i == this.ah) {
            if (Utils.a(str)) {
                this.c.a(this, this.ah, str, 4);
            } else {
                this.ak = Boolean.parseBoolean(str);
            }
        } else if (i == this.ai) {
            if (Utils.a(str)) {
                this.c.a(this, this.ai, str, 4);
            } else {
                this.al = Boolean.parseBoolean(str);
            }
        } else if (i != this.aj) {
            return super.a(i, str);
        } else {
            if (Utils.a(str)) {
                this.c.a(this, this.aj, str, 4);
            } else {
                this.am = Boolean.parseBoolean(str);
            }
        }
        return true;
    }

    public void e() {
        super.e();
    }

    public void f() {
        super.f();
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new GravityImage(vafContext, viewCache);
        }
    }
}
