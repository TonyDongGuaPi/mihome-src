package com.tmall.wireless.vaf.virtualview.view.text;

import android.text.TextUtils;
import com.libra.Utils;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.Helper.RtlHelper;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public abstract class TextBase extends ViewBase {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9427a = "TextBase_TMTEST";
    protected String am = "";
    protected int an = -16777216;
    protected int ao = Utils.b(20.0d);
    protected int ap;
    protected String aq;
    protected int ar = -1;
    protected int as = -1;

    public TextBase(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.D = "title";
        this.ap = 0;
    }

    public void f(String str) {
        if (!TextUtils.equals(str, this.am)) {
            this.am = str;
            W();
        }
    }

    public String b() {
        return this.am;
    }

    public int am() {
        return this.an;
    }

    public void n(int i) {
        if (this.an != i) {
            this.an = i;
            this.j.setColor(this.an);
            W();
        }
    }

    public void f() {
        super.f();
        if (ak()) {
            this.N = RtlHelper.a(this.N);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        boolean a2 = super.a(i, str);
        if (a2) {
            return a2;
        }
        switch (i) {
            case StringBase.ap:
                this.c.a(this, StringBase.ap, str, 3);
                return a2;
            case StringBase.T:
                this.c.a(this, StringBase.T, str, 8);
                return a2;
            case StringBase.aq:
                this.c.a(this, StringBase.aq, str, 1);
                return a2;
            case StringBase.ag:
                this.aq = str;
                return a2;
            case StringBase.z:
                if (Utils.a(str)) {
                    this.c.a(this, StringBase.z, str, 2);
                    return a2;
                }
                this.am = str;
                return a2;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        if (b) {
            return b;
        }
        if (i != -1003668786) {
            return false;
        }
        this.ao = Utils.b((double) Math.round(f));
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, float f) {
        boolean a2 = super.a(i, f);
        if (a2) {
            return a2;
        }
        if (i != -1003668786) {
            return false;
        }
        this.ao = Utils.a((double) f);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        switch (i) {
            case StringBase.ap:
                this.an = i2;
                return true;
            case StringBase.T:
                this.ap = i2;
                return true;
            case StringBase.aq:
                this.ao = Utils.b((double) i2);
                return true;
            case StringBase.bf:
                this.ar = i2;
                return true;
            case StringBase.bg:
                this.as = i2;
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) {
        boolean b = super.b(i, i2);
        if (b) {
            return b;
        }
        if (i != -1003668786) {
            return false;
        }
        this.ao = Utils.a((double) i2);
        return true;
    }
}
