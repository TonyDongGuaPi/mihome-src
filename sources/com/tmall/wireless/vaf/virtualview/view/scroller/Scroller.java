package com.tmall.wireless.vaf.virtualview.view.scroller;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.libra.Utils;
import com.libra.expr.common.ExprCode;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.expr.engine.ExprEngine;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.NativeViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import org.json.JSONObject;

public class Scroller extends NativeViewBase {
    private static final String ar = "Scroller_TMTEST";
    protected ScrollerImp ah;
    protected int ai = 1;
    protected int aj = 1;
    protected ExprCode ak;
    protected boolean al = false;
    protected int am = 0;
    protected int an = 5;
    protected int ao = 0;
    protected int ap = 0;
    protected int aq = 0;

    public boolean m() {
        return true;
    }

    public Scroller(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.ah = new ScrollerImp(vafContext, this);
        this.f9383a = this.ah;
    }

    public int b() {
        return this.ai;
    }

    public void n(int i) {
        this.ah.setAutoRefreshThreshold(i);
    }

    public void c(Object obj) {
        super.c(obj);
        if (obj instanceof JSONObject) {
            obj = ((JSONObject) obj).optJSONArray(this.D);
        }
        this.ah.setData(obj);
    }

    public void d(Object obj) {
        super.d(obj);
        if (obj instanceof JSONObject) {
            obj = ((JSONObject) obj).opt(this.D);
        }
        this.ah.appendData(obj);
    }

    public void am() {
        if (this.ak != null) {
            ExprEngine i = this.W.i();
            if (i != null) {
                i.a().f().a((JSONObject) l().b());
            }
            if (i == null || !i.a(this, this.ak)) {
                Log.e(ar, "callAutoRefresh execute failed");
            }
        }
        this.W.e().a(2, EventData.a(this.W, this));
    }

    public void d() {
        super.d();
        this.ah.destroy();
        this.ah = null;
    }

    public void f() {
        super.f();
        if (!(this.ao == 0 && this.ap == 0 && this.aq == 0)) {
            this.ah.addItemDecoration(new SpaceItemDecoration(this, this.ao, this.ap, this.aq));
        }
        this.ah.setModeOrientation(this.aj, this.ai);
        this.ah.setSupportSticky(this.al);
        if (!this.al) {
            this.f9383a = this.ah;
        } else if (this.ah.getParent() == null) {
            ScrollerStickyParent scrollerStickyParent = new ScrollerStickyParent(this.W.m());
            scrollerStickyParent.addView(this.ah, this.Z.f9382a, this.Z.b);
            this.f9383a = scrollerStickyParent;
        }
        this.ah.setBackgroundColor(this.k);
        this.ah.setAutoRefreshThreshold(this.an);
        this.ah.setSpan(this.am);
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, ExprCode exprCode) {
        boolean b = super.b(i, exprCode);
        if (b) {
            return b;
        }
        if (i != 173466317) {
            return false;
        }
        this.ak = exprCode;
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        if (b) {
            return b;
        }
        if (i == -1807275662) {
            this.ao = Utils.b((double) f);
            return true;
        } else if (i == -172008394) {
            this.ap = Utils.b((double) f);
            return true;
        } else if (i == 3536714) {
            this.am = Utils.b((double) f);
            return true;
        } else if (i != 2002099216) {
            return false;
        } else {
            this.aq = Utils.b((double) f);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, float f) {
        boolean a2 = super.a(i, f);
        if (a2) {
            return a2;
        }
        if (i == -1807275662) {
            this.ao = Utils.a((double) f);
            return true;
        } else if (i == -172008394) {
            this.ap = Utils.a((double) f);
            return true;
        } else if (i == 3536714) {
            this.am = Utils.a((double) f);
            return true;
        } else if (i != 2002099216) {
            return false;
        } else {
            this.aq = Utils.a((double) f);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) {
        boolean b = super.b(i, i2);
        if (b) {
            return b;
        }
        if (i == -1807275662) {
            this.ao = Utils.a((double) i2);
            return true;
        } else if (i == -172008394) {
            this.ap = Utils.a((double) i2);
            return true;
        } else if (i == 3536714) {
            this.am = Utils.a((double) i2);
            return true;
        } else if (i != 2002099216) {
            return false;
        } else {
            this.aq = Utils.a((double) i2);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        boolean z = false;
        if (c) {
            return c;
        }
        switch (i) {
            case StringBase.bM:
                this.ao = Utils.b((double) i2);
                break;
            case StringBase.y:
                if (i2 != 1) {
                    if (i2 == 0) {
                        this.ai = 1;
                        break;
                    }
                } else {
                    this.ai = 0;
                    break;
                }
                break;
            case StringBase.aW:
                if (i2 > 0) {
                    z = true;
                }
                this.al = z;
                break;
            case StringBase.bN:
                this.ap = Utils.b((double) i2);
                break;
            case StringBase.bC:
                this.an = i2;
                break;
            case StringBase.aV:
                this.aj = i2;
                break;
            case StringBase.bx:
                this.am = Utils.b((double) i2);
                break;
            case StringBase.bO:
                this.aq = Utils.b((double) i2);
                break;
            default:
                return false;
        }
        return true;
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        private Scroller f9415a;
        private int b;
        private int c;
        private int d;

        public SpaceItemDecoration(Scroller scroller, int i, int i2, int i3) {
            this.f9415a = scroller;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }

        public void a(int i, int i2, int i3) {
            this.b = i;
            this.c = i2;
            this.d = i3;
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            ScrollerImp scrollerImp;
            if (this.c != 0 && recyclerView.getChildPosition(view) == 0) {
                if (this.f9415a.b() == 0) {
                    rect.left = this.c;
                } else {
                    rect.top = this.c;
                }
            }
            if (this.d != 0) {
                View g_ = this.f9415a.g_();
                if (g_ instanceof ScrollerStickyParent) {
                    scrollerImp = (ScrollerImp) ((ScrollerStickyParent) g_).getChildAt(0);
                } else {
                    scrollerImp = (ScrollerImp) this.f9415a.g_();
                }
                RecyclerView.Adapter adapter = scrollerImp.getAdapter();
                if (adapter != null && adapter.getItemCount() - 1 == recyclerView.getChildPosition(view)) {
                    if (this.f9415a.b() == 0) {
                        rect.right = this.d;
                    } else {
                        rect.bottom = this.d;
                    }
                }
            }
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new Scroller(vafContext, viewCache);
        }
    }
}
