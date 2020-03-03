package com.tmall.wireless.vaf.virtualview.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Trace;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import com.libra.Utils;
import com.libra.expr.common.ExprCode;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.expr.engine.ExprEngine;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.Helper.ImageLoader;
import com.tmall.wireless.vaf.virtualview.Helper.RtlHelper;
import com.tmall.wireless.vaf.virtualview.Helper.VirtualViewUtils;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import com.tmall.wireless.vaf.virtualview.view.nlayout.INativeLayoutImpl;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ViewBase implements IView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9384a = "ViewBase_TMTEST";
    public static final String b = "type";
    protected int A = 1;
    protected Object B;
    protected String C;
    protected String D;
    protected String E;
    protected String F;
    protected int G = 0;
    protected float H = 1.0f;
    protected float I = 1.0f;
    protected int J;
    protected int K;
    protected int L;
    protected int M;
    protected int N;
    protected int O;
    protected int P;
    protected int Q;
    protected int R;
    protected int S;
    protected int T;
    protected String U;
    protected IBean V;
    protected VafContext W;
    protected Layout X;
    protected Rect Y;
    protected Layout.Params Z;
    protected String aa;
    protected Object ab;
    protected ExprCode ac;
    protected ExprCode ad;
    protected ExprCode ae;
    protected ExprCode af;
    protected SparseArray<UserVarItem> ag;
    private int ah;
    private boolean ai;
    private boolean aj;
    private boolean ak;
    private boolean al;
    private SimpleArrayMap<String, Object> am;
    private boolean an;
    protected ViewCache c;
    protected String d;
    protected int e;
    protected boolean f;
    protected View g;
    protected int h;
    protected int i;
    protected Paint j;
    protected int k;
    protected String l;
    protected Bitmap m = null;
    protected Matrix n = null;
    protected int o = 0;
    protected int p = -16777216;
    protected int q = 0;
    protected int r = 0;
    protected int s = 0;
    protected int t = 0;
    protected int u = 0;
    protected float v = Float.NaN;
    protected int w;
    protected int x = 1;
    protected String y;
    protected String z;

    public interface IBuilder {
        ViewBase a(VafContext vafContext, ViewCache viewCache);
    }

    @Deprecated
    public void R() {
    }

    public final int V() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void aj() {
    }

    public ViewBase b(int i2) {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i2, Object obj) {
        return false;
    }

    public View g_() {
        return null;
    }

    public boolean m() {
        return false;
    }

    static class UserVarItem {

        /* renamed from: a  reason: collision with root package name */
        int f9386a;
        Object b;

        public UserVarItem(int i, Object obj) {
            this.f9386a = i;
            this.b = obj;
        }
    }

    public ViewBase(VafContext vafContext, ViewCache viewCache) {
        this.W = vafContext;
        this.c = viewCache;
        this.k = 0;
        this.N = 9;
        this.Q = 0;
        this.J = 0;
        this.L = 0;
        this.K = 0;
        this.M = 0;
        this.R = 0;
        this.S = 0;
        this.w = -1;
        this.aa = "";
        this.d = "";
        this.e = 0;
        this.T = 0;
    }

    public void a(View view) {
        this.g = view;
    }

    public View h() {
        return this.g;
    }

    public String i() {
        return this.E;
    }

    public String j() {
        return this.F;
    }

    public Object k() {
        return this.B;
    }

    public void d(int i2) {
        this.o = i2;
        W();
    }

    public void e(int i2) {
        this.p = i2;
        W();
    }

    public ViewCache l() {
        return this.c;
    }

    public void f(int i2) {
        this.k = i2;
        W();
    }

    /* access modifiers changed from: protected */
    public void c(int i2) {
        this.k = i2;
        View g_ = g_();
        if (g_ != null && !(g_ instanceof INativeLayoutImpl)) {
            g_.setBackgroundColor(i2);
        }
    }

    public void b(int i2, int i3, int i4) {
        if (this.ag == null) {
            this.ag = new SparseArray<>();
        }
        Object obj = null;
        switch (i2) {
            case 1:
                obj = Integer.valueOf(i4);
                break;
            case 2:
                obj = Float.valueOf(Float.intBitsToFloat(i4));
                break;
            case 3:
                obj = this.W.o().a(i4);
                break;
        }
        this.ag.put(i3, new UserVarItem(i2, obj));
    }

    public Object g(int i2) {
        UserVarItem userVarItem;
        if (this.ag == null || (userVarItem = this.ag.get(i2)) == null) {
            return null;
        }
        return userVarItem.b;
    }

    public boolean a(int i2, Object obj) {
        UserVarItem userVarItem;
        if (!(this.ag == null || (userVarItem = this.ag.get(i2)) == null)) {
            switch (userVarItem.f9386a) {
                case 1:
                    if (!(obj instanceof Integer)) {
                        Log.e(f9384a, "setUserVar set int failed");
                        break;
                    } else {
                        userVarItem.b = obj;
                        return true;
                    }
                case 2:
                    if (!(obj instanceof Float)) {
                        Log.e(f9384a, "setUserVar set float failed");
                        break;
                    } else {
                        userVarItem.b = obj;
                        return true;
                    }
                case 3:
                    if (!(obj instanceof String)) {
                        Log.e(f9384a, "setUserVar set string failed");
                        break;
                    } else {
                        userVarItem.b = obj;
                        return true;
                    }
            }
        }
        return false;
    }

    public int n() {
        return this.k;
    }

    public int o() {
        return this.o;
    }

    public int p() {
        return this.q;
    }

    public int q() {
        return this.r;
    }

    public int r() {
        return this.s;
    }

    public int s() {
        return this.t;
    }

    public int t() {
        return this.u;
    }

    public int u() {
        return this.N;
    }

    public int v() {
        return this.T;
    }

    public ViewBase w() {
        if (this.X == null) {
            return ((IContainer) this.c.a().getParent()).getVirtualView();
        }
        return this.X;
    }

    public boolean x() {
        return this.X == null;
    }

    public int y() {
        if (this.X == null) {
            return this.x;
        }
        int y2 = this.X.y();
        if (y2 == 1) {
            return this.x;
        }
        return y2 == 0 ? 0 : 2;
    }

    public String z() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public int A() {
        return this.e;
    }

    public void h(int i2) {
        this.e = i2;
    }

    public void a(Object obj) {
        this.ab = obj;
    }

    public Object B() {
        return this.ab;
    }

    public Object c(String str) {
        if (this.am != null) {
            return this.am.get(str);
        }
        return null;
    }

    public void a(String str, Object obj) {
        if (this.am == null) {
            this.am = new SimpleArrayMap<>();
        }
        this.am.put(str, obj);
    }

    public IBean C() {
        return this.V;
    }

    public void d() {
        this.W = null;
        this.V = null;
        this.ag = null;
    }

    public final boolean D() {
        return (this.Q & 4) != 0;
    }

    public final boolean E() {
        return (this.Q & 16) != 0 && U();
    }

    public final boolean F() {
        return (this.Q & 32) != 0;
    }

    public final boolean G() {
        return (this.Q & 64) != 0;
    }

    public final boolean H() {
        return (this.Q & 128) != 0;
    }

    public void i(int i2) {
        if (this.x != i2) {
            this.x = i2;
            if (!g()) {
                W();
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        int y2 = y();
        View g_ = g_();
        if (g_ != null) {
            switch (y2) {
                case 0:
                    g_.setVisibility(4);
                    return true;
                case 1:
                    g_.setVisibility(0);
                    return true;
                case 2:
                    g_.setVisibility(8);
                    return true;
                default:
                    return true;
            }
        } else if (!m()) {
            return false;
        } else {
            switch (y2) {
                case 0:
                    this.c.a().setVisibility(4);
                    return true;
                case 1:
                    this.c.a().setVisibility(0);
                    return true;
                case 2:
                    this.c.a().setVisibility(8);
                    return true;
                default:
                    return true;
            }
        }
    }

    public boolean I() {
        return this.x == 2;
    }

    public int J() {
        return this.x;
    }

    public int K() {
        return this.P;
    }

    public int L() {
        return this.O;
    }

    public void j(int i2) {
        this.w = i2;
    }

    public int M() {
        return this.w;
    }

    public void d(String str) {
        this.aa = str;
    }

    public String N() {
        return this.aa;
    }

    public String O() {
        return this.y;
    }

    public String P() {
        return this.z;
    }

    public int Q() {
        return this.A;
    }

    public boolean a(int i2, int i3) {
        return k(this.w);
    }

    /* access modifiers changed from: protected */
    public boolean k(int i2) {
        boolean l2 = l(i2);
        return (l2 || this.X == null) ? l2 : this.X.k(i2);
    }

    /* access modifiers changed from: protected */
    public boolean l(int i2) {
        return F() || G() || H();
    }

    /* access modifiers changed from: protected */
    public boolean a(int i2, boolean z2) {
        boolean z3;
        if (z2) {
            z3 = m(i2);
        } else {
            z3 = onClick(i2);
        }
        return (z3 || this.X == null) ? z3 : this.X.a(this.X.w, z2);
    }

    public boolean a(int i2, int i3, boolean z2) {
        return a(this.w, z2);
    }

    /* access modifiers changed from: protected */
    public boolean m(int i2) {
        if (this.V != null) {
            this.V.a(i2, true);
        }
        if (G()) {
            return this.W.e().a(4, EventData.a(this.W, this));
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onClick(int i2) {
        if (this.V != null) {
            this.V.a(i2, false);
        }
        if (this.ac != null) {
            ExprEngine i3 = this.W.i();
            if (i3 != null) {
                i3.a().f().a((JSONObject) l().b());
            }
            if (i3 == null || !i3.a(this, this.ac)) {
                Log.e(f9384a, "onClick execute failed");
            }
        }
        if (F()) {
            return this.W.e().a(0, EventData.a(this.W, this));
        }
        return false;
    }

    public boolean a(View view, MotionEvent motionEvent) {
        if (H()) {
            return this.W.e().a(5, EventData.a(this.W, this, view, motionEvent));
        }
        return false;
    }

    public final int S() {
        return this.h;
    }

    public final int T() {
        return this.i;
    }

    public final void b(View view) {
        this.c.a(view);
        if (X()) {
            view.setLayerType(1, (Paint) null);
        }
    }

    public final boolean U() {
        return this.x == 1;
    }

    public void e() {
        this.Y = null;
        this.f = false;
    }

    public void W() {
        a(this.h, this.i, this.h + this.O, this.i + this.P);
    }

    public void a(int i2, int i3, int i4, int i5) {
        if (this.g != null) {
            this.g.invalidate(i2, i3, i4, i5);
        }
    }

    public ViewBase a(int i2) {
        if (this.w == i2) {
            return this;
        }
        return null;
    }

    public ViewBase a(String str) {
        if (TextUtils.equals(this.aa, str)) {
            return this;
        }
        return null;
    }

    public boolean X() {
        return (this.Q & 8) != 0;
    }

    public boolean Y() {
        return this.x == 1;
    }

    @Deprecated
    public final boolean Z() {
        return (this.Q & 2) != 0;
    }

    public final int aa() {
        return this.J;
    }

    public final int ab() {
        return this.L;
    }

    public final int ac() {
        return this.K;
    }

    public final int ad() {
        return this.M;
    }

    public final void a(Layout.Params params) {
        this.Z = params;
    }

    public Layout.Params ae() {
        return this.Z;
    }

    /* access modifiers changed from: protected */
    public final void d(int i2, int i3) {
        this.O = i2;
        this.P = i3;
    }

    public void comLayout(int i2, int i3, int i4, int i5) {
        this.h = i2;
        this.i = i3;
        onComLayout(true, i2, i3, i4, i5);
    }

    public void measureComponent(int i2, int i3) {
        if (this.G > 0) {
            switch (this.G) {
                case 1:
                    if (1073741824 == View.MeasureSpec.getMode(i2)) {
                        i3 = View.MeasureSpec.makeMeasureSpec((int) ((((float) View.MeasureSpec.getSize(i2)) * this.I) / this.H), 1073741824);
                        break;
                    }
                    break;
                case 2:
                    if (1073741824 == View.MeasureSpec.getMode(i3)) {
                        i2 = View.MeasureSpec.makeMeasureSpec((int) ((((float) View.MeasureSpec.getSize(i3)) * this.H) / this.I), 1073741824);
                        break;
                    }
                    break;
            }
        }
        onComMeasure(i2, i3);
    }

    public final int af() {
        return getComMeasuredWidth() + this.Z.d + this.Z.f;
    }

    public final int ag() {
        return getComMeasuredHeight() + this.Z.h + this.Z.j;
    }

    public void ah() {
        if (this.ad != null) {
            ExprEngine i2 = this.W.i();
            if (i2 == null || !i2.a(this, this.ad)) {
                Log.e(f9384a, "mBeforeLoadDataCode execute failed");
            }
        }
    }

    public void a(boolean z2) {
        if (this.ae != null) {
            ExprEngine i2 = this.W.i();
            if (i2 == null || !i2.a(this, this.ae)) {
                Log.e(f9384a, "BeforeLoadDataCode execute failed");
            }
        }
    }

    public final void b(Object obj) {
        a(obj, false);
    }

    public final void a(Object obj, boolean z2) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection("ViewBase.setVData");
        }
        this.c.a(obj);
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            boolean optBoolean = jSONObject.optBoolean(ViewCache.Item.f9389a);
            List<ViewBase> c2 = this.c.c();
            if (c2 != null) {
                int size = c2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ViewBase viewBase = c2.get(i2);
                    List<ViewCache.Item> b2 = this.c.b(viewBase);
                    if (b2 != null) {
                        int size2 = b2.size();
                        for (int i3 = 0; i3 < size2; i3++) {
                            ViewCache.Item item = b2.get(i3);
                            if (optBoolean) {
                                item.a(obj.hashCode());
                            }
                            item.a(obj, z2);
                        }
                        viewBase.f();
                        if (!viewBase.x() && viewBase.E()) {
                            this.W.e().a(1, EventData.a(this.W, viewBase));
                        }
                    }
                }
            }
            jSONObject.remove(ViewCache.Item.f9389a);
        }
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Bitmap bitmap) {
        this.m = bitmap;
        W();
    }

    public void e(String str) {
        this.l = str;
        this.m = null;
        if (this.n == null) {
            this.n = new Matrix();
        }
        this.W.h().a(str, this.O, this.P, (ImageLoader.Listener) new ImageLoader.Listener() {
            public void a() {
            }

            public void a(Drawable drawable) {
            }

            public void a(Bitmap bitmap) {
                ViewBase.this.a(bitmap);
            }
        });
    }

    public void c() {
        R();
    }

    public void c(Object obj) {
        this.B = obj;
        if (this.V != null) {
            this.V.a(obj);
        }
        if (this.af != null) {
            ExprEngine i2 = this.W.i();
            if (i2 == null || !i2.a(this, this.af)) {
                Log.e(f9384a, "setData execute failed");
            }
        }
    }

    public void d(Object obj) {
        if (this.V != null) {
            this.V.b(obj);
        }
    }

    public void a(Canvas canvas) {
        canvas.save();
        canvas.translate((float) this.h, (float) this.i);
        b(canvas);
        canvas.restore();
        this.f = true;
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
        if (g_() != null) {
            return;
        }
        if (this.k != 0) {
            VirtualViewUtils.b(canvas, this.k, this.O, this.P, this.o, this.r, this.s, this.t, this.u);
        } else if (this.m != null) {
            this.n.setScale(((float) this.O) / ((float) this.m.getWidth()), ((float) this.P) / ((float) this.m.getHeight()));
            canvas.drawBitmap(this.m, this.n, (Paint) null);
        }
    }

    public void c(Canvas canvas) {
        VirtualViewUtils.a(canvas, this.p, this.O, this.P, this.o, this.r, this.s, this.t, this.u);
    }

    public void f() {
        al();
        if (g_() != null) {
            g_().setPadding(this.J, this.L, this.K, this.M);
        }
        if (!TextUtils.isEmpty(this.U)) {
            b();
        }
    }

    private void b() {
        try {
            Class<? extends IBean> a2 = this.W.f().a(this.U);
            if (a2 != null && this.V == null) {
                Object newInstance = a2.newInstance();
                if (newInstance instanceof IBean) {
                    this.V = (IBean) newInstance;
                    this.V.a(this.W.m(), this);
                    return;
                }
                Log.e(f9384a, this.U + " is not bean");
            }
        } catch (InstantiationException e2) {
            Log.e(f9384a, "error:" + e2);
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            Log.e(f9384a, "error:" + e3);
            e3.printStackTrace();
        }
    }

    public int getComMeasuredWidth() {
        return this.O;
    }

    public int getComMeasuredHeight() {
        return this.P;
    }

    public final boolean a(int i2, ExprCode exprCode) {
        boolean b2 = b(i2, exprCode);
        return (b2 || this.Z == null) ? b2 : this.Z.a(i2, exprCode);
    }

    public String ai() {
        return this.D;
    }

    public final boolean c(int i2, float f2) {
        boolean a2 = a(i2, f2);
        return (a2 || this.Z == null) ? a2 : this.Z.a(i2, f2);
    }

    public final boolean d(int i2, float f2) {
        boolean b2 = b(i2, f2);
        return (b2 || this.Z == null) ? b2 : this.Z.b(i2, f2);
    }

    public final boolean e(int i2, int i3) {
        boolean h2 = h(i2, i3);
        return (h2 || this.Z == null) ? h2 : this.Z.a(i2, i3);
    }

    public final boolean f(int i2, int i3) {
        boolean b2 = b(i2, i3);
        return (b2 || this.Z == null) ? b2 : this.Z.b(i2, i3);
    }

    public final boolean g(int i2, int i3) {
        boolean c2 = c(i2, i3);
        return (c2 || this.Z == null) ? c2 : this.Z.c(i2, i3);
    }

    /* access modifiers changed from: protected */
    public boolean b(int i2, ExprCode exprCode) {
        if (i2 == -1351902487) {
            this.ac = exprCode;
        } else if (i2 == -974184371) {
            this.af = exprCode;
        } else if (i2 == -251005427) {
            this.ae = exprCode;
        } else if (i2 != 361078798) {
            return false;
        } else {
            this.ad = exprCode;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i2, float f2) {
        switch (i2) {
            case StringBase.w:
                this.Z.h = Utils.a((double) f2);
                this.Z.i = true;
                return true;
            case StringBase.q:
                this.J = Utils.a((double) f2);
                this.ai = true;
                return true;
            case StringBase.ai:
                this.R = Utils.a((double) f2);
                return true;
            case StringBase.bZ:
                this.r = Utils.a((double) f2);
                return true;
            case StringBase.cf:
                this.ah = Utils.a((double) f2);
                if (!this.ai) {
                    this.J = this.ah;
                }
                if (!this.aj) {
                    this.K = this.ah;
                }
                if (!this.ak) {
                    this.L = this.ah;
                }
                if (this.al) {
                    return true;
                }
                this.M = this.ah;
                return true;
            case StringBase.aj:
                this.S = Utils.a((double) f2);
                return true;
            case StringBase.v:
                this.Z.f = Utils.a((double) f2);
                this.Z.g = true;
                return true;
            case StringBase.s:
                this.L = Utils.a((double) f2);
                this.ak = true;
                return true;
            case StringBase.t:
                this.M = Utils.a((double) f2);
                this.al = true;
                return true;
            case StringBase.ca:
                this.s = Utils.a((double) f2);
                return true;
            case StringBase.cb:
                this.t = Utils.a((double) f2);
                return true;
            case StringBase.cc:
                this.u = Utils.a((double) f2);
                return true;
            case StringBase.r:
                this.K = Utils.a((double) f2);
                this.aj = true;
                return true;
            case StringBase.bI:
                this.o = Utils.a((double) f2);
                return true;
            case StringBase.u:
                this.Z.d = Utils.a((double) f2);
                this.Z.e = true;
                return true;
            case StringBase.bY:
                this.q = Utils.a((double) f2);
                if (this.r <= 0) {
                    this.r = this.q;
                }
                if (this.s <= 0) {
                    this.s = this.q;
                }
                if (this.t <= 0) {
                    this.t = this.q;
                }
                if (this.u > 0) {
                    return true;
                }
                this.u = this.q;
                return true;
            case StringBase.x:
                this.Z.j = Utils.a((double) f2);
                this.Z.k = true;
                return true;
            case StringBase.p:
                if (f2 > -1.0f) {
                    this.Z.b = Utils.a((double) f2);
                    return true;
                }
                this.Z.b = (int) f2;
                return true;
            case StringBase.cg:
                this.Z.c = Utils.a((double) f2);
                if (!this.Z.e) {
                    this.Z.d = this.Z.c;
                }
                if (!this.Z.g) {
                    this.Z.f = this.Z.c;
                }
                if (!this.Z.i) {
                    this.Z.h = this.Z.c;
                }
                if (this.Z.k) {
                    return true;
                }
                this.Z.j = this.Z.c;
                return true;
            case StringBase.o:
                if (f2 > -1.0f) {
                    this.Z.f9382a = Utils.a((double) f2);
                    return true;
                }
                this.Z.f9382a = (int) f2;
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i2, float f2) {
        switch (i2) {
            case StringBase.w:
                this.Z.h = Utils.b((double) f2);
                this.Z.i = true;
                return true;
            case StringBase.q:
                this.J = Utils.b((double) f2);
                this.ai = true;
                return true;
            case StringBase.ai:
                this.R = Utils.b((double) f2);
                return true;
            case StringBase.bZ:
                this.r = Utils.b((double) f2);
                return true;
            case StringBase.cf:
                this.ah = Utils.b((double) f2);
                if (!this.ai) {
                    this.J = this.ah;
                }
                if (!this.aj) {
                    this.K = this.ah;
                }
                if (!this.ak) {
                    this.L = this.ah;
                }
                if (this.al) {
                    return true;
                }
                this.M = this.ah;
                return true;
            case StringBase.aj:
                this.S = Utils.b((double) f2);
                return true;
            case StringBase.v:
                this.Z.f = Utils.b((double) f2);
                this.Z.g = true;
                return true;
            case StringBase.s:
                this.L = Utils.b((double) f2);
                this.ak = true;
                return true;
            case StringBase.bW:
                this.v = f2;
                return true;
            case StringBase.t:
                this.M = Utils.b((double) f2);
                this.al = true;
                return true;
            case StringBase.ca:
                this.s = Utils.b((double) f2);
                return true;
            case StringBase.cb:
                this.t = Utils.b((double) f2);
                return true;
            case StringBase.cc:
                this.u = Utils.b((double) f2);
                return true;
            case StringBase.r:
                this.K = Utils.b((double) f2);
                this.aj = true;
                return true;
            case StringBase.bI:
                this.o = Utils.b((double) f2);
                return true;
            case StringBase.u:
                this.Z.d = Utils.b((double) f2);
                this.Z.e = true;
                return true;
            case StringBase.bY:
                this.q = Utils.b((double) f2);
                if (this.r <= 0) {
                    this.r = this.q;
                }
                if (this.s <= 0) {
                    this.s = this.q;
                }
                if (this.t <= 0) {
                    this.t = this.q;
                }
                if (this.u > 0) {
                    return true;
                }
                this.u = this.q;
                return true;
            case StringBase.bi:
                this.H = f2;
                return true;
            case StringBase.bj:
                this.I = f2;
                return true;
            case StringBase.x:
                this.Z.j = Utils.b((double) f2);
                this.Z.k = true;
                return true;
            case StringBase.p:
                if (f2 > -1.0f) {
                    this.Z.b = Utils.b((double) f2);
                    return true;
                }
                this.Z.b = (int) f2;
                return true;
            case StringBase.cg:
                this.Z.c = Utils.b((double) f2);
                if (!this.Z.e) {
                    this.Z.d = this.Z.c;
                }
                if (!this.Z.g) {
                    this.Z.f = this.Z.c;
                }
                if (!this.Z.i) {
                    this.Z.h = this.Z.c;
                }
                if (this.Z.k) {
                    return true;
                }
                this.Z.j = this.Z.c;
                return true;
            case StringBase.o:
                if (f2 > -1.0f) {
                    this.Z.f9382a = Utils.b((double) f2);
                    return true;
                }
                this.Z.f9382a = (int) f2;
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i2, String str) {
        switch (i2) {
            case StringBase.w:
                this.c.a(this, StringBase.w, str, 1);
                break;
            case StringBase.q:
                this.c.a(this, StringBase.q, str, 1);
                break;
            case StringBase.P:
                if (!Utils.a(str)) {
                    this.E = str;
                    break;
                } else {
                    this.c.a(this, StringBase.P, str, 2);
                    break;
                }
            case StringBase.bh:
                this.c.a(this, StringBase.bh, str, 0);
                break;
            case StringBase.F:
                this.c.a(this, StringBase.F, str, 3);
                break;
            case StringBase.bZ:
                this.c.a(this, StringBase.bZ, str, 1);
                break;
            case StringBase.cf:
                this.c.a(this, StringBase.cf, str, 1);
                break;
            case StringBase.bB:
                if (!Utils.a(str)) {
                    this.z = str;
                    break;
                } else {
                    this.c.a(this, StringBase.bB, str, 2);
                    break;
                }
            case StringBase.cd:
                if (!Utils.a(str)) {
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            Iterator<String> keys = jSONObject.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                a(next, (Object) jSONObject.getString(next));
                            }
                            break;
                        } catch (JSONException unused) {
                            this.ab = str;
                            break;
                        }
                    }
                } else {
                    this.c.a(this, StringBase.cd, str, 2);
                    break;
                }
                break;
            case StringBase.M:
                if (!Utils.a(str)) {
                    this.C = str;
                    break;
                } else {
                    this.c.a(this, StringBase.M, str, 2);
                    break;
                }
            case StringBase.B:
                if (!Utils.a(str)) {
                    this.aa = str;
                    break;
                } else {
                    this.c.a(this, StringBase.B, str, 2);
                    break;
                }
            case StringBase.v:
                this.c.a(this, StringBase.v, str, 1);
                break;
            case StringBase.s:
                this.c.a(this, StringBase.s, str, 1);
                break;
            case StringBase.bW:
                this.c.a(this, StringBase.bW, str, 1);
                break;
            case StringBase.al:
                if (!Utils.a(str)) {
                    this.U = str;
                    break;
                } else {
                    this.c.a(this, StringBase.al, str, 2);
                    break;
                }
            case StringBase.t:
                this.c.a(this, StringBase.t, str, 1);
                break;
            case StringBase.E:
                this.c.a(this, StringBase.E, str, 6);
                break;
            case StringBase.ca:
                this.c.a(this, StringBase.ca, str, 1);
                break;
            case StringBase.cb:
                this.c.a(this, StringBase.cb, str, 1);
                break;
            case StringBase.cc:
                this.c.a(this, StringBase.cc, str, 1);
                break;
            case StringBase.r:
                this.c.a(this, StringBase.r, str, 1);
                break;
            case StringBase.bJ:
                this.c.a(this, StringBase.bJ, str, 3);
                break;
            case StringBase.bI:
                this.c.a(this, StringBase.bI, str, 1);
                break;
            case StringBase.u:
                this.c.a(this, StringBase.u, str, 1);
                break;
            case StringBase.bv:
                if (!Utils.a(str)) {
                    e(str);
                    break;
                } else {
                    this.c.a(this, StringBase.bv, str, 2);
                    break;
                }
            case StringBase.bY:
                this.c.a(this, StringBase.bY, str, 1);
                break;
            case StringBase.bi:
                this.c.a(this, StringBase.bi, str, 1);
                break;
            case StringBase.bj:
                this.c.a(this, StringBase.bj, str, 1);
                break;
            case StringBase.N:
                if (!Utils.a(str)) {
                    this.D = str;
                    break;
                } else {
                    this.c.a(this, StringBase.N, str, 7);
                    break;
                }
            case StringBase.ar:
                if (!Utils.a(str)) {
                    this.y = str;
                    break;
                } else {
                    this.c.a(this, StringBase.ar, str, 2);
                    break;
                }
            case StringBase.x:
                this.c.a(this, StringBase.x, str, 1);
                break;
            case StringBase.p:
                this.c.a(this, StringBase.p, str, 1);
                this.Z.b = -2;
                break;
            case StringBase.Q:
                if (!Utils.a(str)) {
                    this.F = str;
                    break;
                } else {
                    this.c.a(this, StringBase.Q, str, 2);
                    break;
                }
            case StringBase.cg:
                this.c.a(this, StringBase.cg, str, 1);
                break;
            case StringBase.aU:
                this.c.a(this, StringBase.aU, str, 5);
                break;
            case StringBase.o:
                this.c.a(this, StringBase.o, str, 1);
                this.Z.f9382a = -2;
                break;
            default:
                return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean h(int i2, int i3) {
        return a(i2, this.W.o().a(i3));
    }

    public int hashCode() {
        return super.hashCode();
    }

    /* access modifiers changed from: protected */
    public boolean b(int i2, int i3) {
        switch (i2) {
            case StringBase.w:
                this.Z.h = Utils.a((double) i3);
                this.Z.i = true;
                return true;
            case StringBase.q:
                this.J = Utils.a((double) i3);
                this.ai = true;
                return true;
            case StringBase.ai:
                this.R = Utils.a((double) i3);
                return true;
            case StringBase.bZ:
                this.r = Utils.a((double) i3);
                return true;
            case StringBase.cf:
                this.ah = Utils.a((double) i3);
                if (!this.ai) {
                    this.J = this.ah;
                }
                if (!this.aj) {
                    this.K = this.ah;
                }
                if (!this.ak) {
                    this.L = this.ah;
                }
                if (this.al) {
                    return true;
                }
                this.M = this.ah;
                return true;
            case StringBase.aj:
                this.S = Utils.a((double) i3);
                return true;
            case StringBase.v:
                this.Z.f = Utils.a((double) i3);
                this.Z.g = true;
                return true;
            case StringBase.s:
                this.L = Utils.a((double) i3);
                this.ak = true;
                return true;
            case StringBase.t:
                this.M = Utils.a((double) i3);
                this.al = true;
                return true;
            case StringBase.ca:
                this.s = Utils.a((double) i3);
                return true;
            case StringBase.cb:
                this.t = Utils.a((double) i3);
                return true;
            case StringBase.cc:
                this.u = Utils.a((double) i3);
                return true;
            case StringBase.r:
                this.K = Utils.a((double) i3);
                this.aj = true;
                return true;
            case StringBase.bI:
                this.o = Utils.a((double) i3);
                return true;
            case StringBase.u:
                this.Z.d = Utils.a((double) i3);
                this.Z.e = true;
                return true;
            case StringBase.bY:
                this.q = Utils.a((double) i3);
                if (this.r <= 0) {
                    this.r = this.q;
                }
                if (this.s <= 0) {
                    this.s = this.q;
                }
                if (this.t <= 0) {
                    this.t = this.q;
                }
                if (this.u > 0) {
                    return true;
                }
                this.u = this.q;
                return true;
            case StringBase.x:
                this.Z.j = Utils.a((double) i3);
                this.Z.k = true;
                return true;
            case StringBase.p:
                if (i3 > -1) {
                    this.Z.b = Utils.a((double) i3);
                    return true;
                }
                this.Z.b = i3;
                return true;
            case StringBase.cg:
                this.Z.c = Utils.a((double) i3);
                if (!this.Z.e) {
                    this.Z.d = this.Z.c;
                }
                if (!this.Z.g) {
                    this.Z.f = this.Z.c;
                }
                if (!this.Z.i) {
                    this.Z.h = this.Z.c;
                }
                if (this.Z.k) {
                    return true;
                }
                this.Z.j = this.Z.c;
                return true;
            case StringBase.o:
                if (i3 > -1) {
                    this.Z.f9382a = Utils.a((double) i3);
                    return true;
                }
                this.Z.f9382a = i3;
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i2, int i3) {
        switch (i2) {
            case StringBase.w:
                this.Z.h = Utils.b((double) i3);
                this.Z.i = true;
                return true;
            case StringBase.q:
                this.J = Utils.b((double) i3);
                this.ai = true;
                return true;
            case StringBase.bh:
                this.G = i3;
                return true;
            case StringBase.ai:
                this.R = Utils.b((double) i3);
                return true;
            case StringBase.F:
                c(i3);
                return true;
            case StringBase.bZ:
                this.r = Utils.b((double) i3);
                return true;
            case StringBase.cf:
                this.ah = Utils.b((double) i3);
                if (!this.ai) {
                    this.J = this.ah;
                }
                if (!this.aj) {
                    this.K = this.ah;
                }
                if (!this.ak) {
                    this.L = this.ah;
                }
                if (this.al) {
                    return true;
                }
                this.M = this.ah;
                return true;
            case StringBase.aj:
                this.S = Utils.b((double) i3);
                return true;
            case StringBase.n:
                this.w = i3;
                return true;
            case StringBase.L:
                this.Q = i3;
                return true;
            case StringBase.aD:
                this.T = i3;
                return true;
            case StringBase.v:
                this.Z.f = Utils.b((double) i3);
                this.Z.g = true;
                return true;
            case StringBase.s:
                this.L = Utils.b((double) i3);
                this.ak = true;
                return true;
            case StringBase.t:
                this.M = Utils.b((double) i3);
                this.al = true;
                return true;
            case StringBase.E:
                this.N = i3;
                return true;
            case StringBase.ca:
                this.s = Utils.b((double) i3);
                return true;
            case StringBase.cb:
                this.t = Utils.b((double) i3);
                return true;
            case StringBase.cc:
                this.u = Utils.b((double) i3);
                return true;
            case StringBase.r:
                this.K = Utils.b((double) i3);
                this.aj = true;
                return true;
            case StringBase.bJ:
                this.p = i3;
                return true;
            case StringBase.bI:
                this.o = Utils.b((double) i3);
                return true;
            case StringBase.u:
                this.Z.d = Utils.b((double) i3);
                this.Z.e = true;
                return true;
            case StringBase.bY:
                this.q = Utils.b((double) i3);
                if (this.r <= 0) {
                    this.r = this.q;
                }
                if (this.s <= 0) {
                    this.s = this.q;
                }
                if (this.t <= 0) {
                    this.t = this.q;
                }
                if (this.u > 0) {
                    return true;
                }
                this.u = this.q;
                return true;
            case StringBase.bi:
                this.H = (float) i3;
                return true;
            case StringBase.bj:
                this.I = (float) i3;
                return true;
            case StringBase.x:
                this.Z.j = Utils.b((double) i3);
                this.Z.k = true;
                return true;
            case StringBase.p:
                if (i3 > -1) {
                    this.Z.b = Utils.b((double) i3);
                    return true;
                }
                this.Z.b = i3;
                return true;
            case StringBase.cg:
                this.Z.c = Utils.b((double) i3);
                if (!this.Z.e) {
                    this.Z.d = this.Z.c;
                }
                if (!this.Z.g) {
                    this.Z.f = this.Z.c;
                }
                if (!this.Z.i) {
                    this.Z.h = this.Z.c;
                }
                if (this.Z.k) {
                    return true;
                }
                this.Z.j = this.Z.c;
                return true;
            case StringBase.bD:
                this.A = i3;
                return true;
            case StringBase.aU:
                this.x = i3;
                g();
                return true;
            case StringBase.o:
                if (i3 > -1) {
                    this.Z.f9382a = Utils.b((double) i3);
                    return true;
                }
                this.Z.f9382a = i3;
                return true;
            default:
                return false;
        }
    }

    protected class VirtualViewImp implements IView {

        /* renamed from: a  reason: collision with root package name */
        protected ViewBase f9387a;
        protected int b = 0;
        protected int c = 0;
        protected boolean d;

        public void comLayout(int i, int i2, int i3, int i4) {
        }

        public int getComMeasuredHeight() {
            return 0;
        }

        public int getComMeasuredWidth() {
            return 0;
        }

        public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        public VirtualViewImp() {
            ViewBase.this.j = new Paint();
            ViewBase.this.j.setAntiAlias(true);
            a();
        }

        public void a(ViewBase viewBase) {
            this.f9387a = viewBase;
        }

        public void a(boolean z) {
            ViewBase.this.j.setAntiAlias(z);
        }

        public void a() {
            this.b = 0;
            this.c = 0;
            this.d = false;
            ViewBase.this.m = null;
            ViewBase.this.l = null;
        }

        public void measureComponent(int i, int i2) {
            if (i != this.b || i2 != this.c || this.d) {
                onComMeasure(i, i2);
                this.b = i;
                this.c = i2;
                this.d = false;
            }
        }

        public void onComMeasure(int i, int i2) {
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int size2 = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            if (ViewBase.this.Y == null) {
                ViewBase.this.aj();
            }
            int i3 = this.f9387a.G;
            float f = this.f9387a.H;
            float f2 = this.f9387a.I;
            if (i3 > 0) {
                switch (i3) {
                    case 1:
                        if (1073741824 == View.MeasureSpec.getMode(i)) {
                            ViewBase.this.O = View.MeasureSpec.getSize(i);
                            ViewBase.this.P = (int) ((((float) ViewBase.this.O) * f2) / f);
                            return;
                        }
                        return;
                    case 2:
                        if (1073741824 == View.MeasureSpec.getMode(i2)) {
                            ViewBase.this.P = View.MeasureSpec.getSize(i2);
                            ViewBase.this.O = (int) ((((float) ViewBase.this.P) * f) / f2);
                            return;
                        }
                        return;
                }
            }
            if (-2 == ViewBase.this.Z.f9382a) {
                if (ViewBase.this.Y != null) {
                    ViewBase.this.O = ViewBase.this.Y.width() + ViewBase.this.J + ViewBase.this.K;
                } else {
                    ViewBase.this.O = ViewBase.this.R;
                }
            } else if (-1 == ViewBase.this.Z.f9382a) {
                if (1073741824 == mode) {
                    ViewBase.this.O = size;
                } else {
                    ViewBase.this.O = 0;
                }
            } else if (1073741824 == mode) {
                ViewBase.this.O = size;
            } else {
                ViewBase.this.O = ViewBase.this.Z.f9382a;
            }
            if (-2 == ViewBase.this.Z.b) {
                if (ViewBase.this.Y != null) {
                    ViewBase.this.P = ViewBase.this.Y.height() + ViewBase.this.L + ViewBase.this.M;
                    return;
                }
                ViewBase.this.P = ViewBase.this.S;
            } else if (-1 == ViewBase.this.Z.b) {
                if (1073741824 == mode2) {
                    ViewBase.this.P = size2;
                } else {
                    ViewBase.this.P = 0;
                }
            } else if (1073741824 == mode2) {
                ViewBase.this.P = size2;
            } else {
                ViewBase.this.P = ViewBase.this.Z.b;
            }
        }
    }

    public boolean ak() {
        return RtlHelper.a() && !this.an;
    }

    public void al() {
        if (ak()) {
            int i2 = this.J;
            this.J = this.K;
            this.K = i2;
        }
    }
}
