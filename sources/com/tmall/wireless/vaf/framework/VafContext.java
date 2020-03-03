package com.tmall.wireless.vaf.framework;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewConfiguration;
import com.libra.expr.common.StringSupport;
import com.tmall.wireless.vaf.expr.engine.ExprEngine;
import com.tmall.wireless.vaf.expr.engine.NativeObjectManager;
import com.tmall.wireless.vaf.framework.cm.ComContainerTypeMap;
import com.tmall.wireless.vaf.framework.cm.ContainerService;
import com.tmall.wireless.vaf.virtualview.Helper.BeanManager;
import com.tmall.wireless.vaf.virtualview.Helper.DataOpt;
import com.tmall.wireless.vaf.virtualview.Helper.ImageLoader;
import com.tmall.wireless.vaf.virtualview.Helper.NativeViewManager;
import com.tmall.wireless.vaf.virtualview.Helper.ServiceManager;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.event.ClickProcessorManager;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import com.tmall.wireless.vaf.virtualview.event.EventManager;
import com.tmall.wireless.vaf.virtualview.loader.StringLoader;

public class VafContext {
    public static int b = 0;
    protected static StringLoader h = new StringLoader();
    private static final String q = "PageContext_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    protected Context f9361a;
    protected ExprEngine c;
    protected ViewManager d;
    protected BeanManager e;
    protected NativeViewManager f;
    protected NativeObjectManager g;
    protected ContainerService i;
    protected ImageLoader j;
    protected EventManager k;
    protected UserData l;
    protected ComContainerTypeMap m;
    protected ServiceManager n;
    protected ClickProcessorManager o;
    protected Activity p;

    public ComContainerTypeMap a() {
        return this.m;
    }

    public VafContext(Context context) {
        this(context, false);
    }

    public VafContext(Activity activity) {
        this(activity.getApplicationContext(), false);
        this.p = activity;
    }

    public VafContext(Context context, boolean z) {
        this.c = new ExprEngine();
        this.d = new ViewManager();
        this.e = new BeanManager();
        this.f = new NativeViewManager();
        this.g = new NativeObjectManager();
        this.k = new EventManager();
        this.l = new UserData();
        this.m = new ComContainerTypeMap();
        this.n = new ServiceManager();
        this.o = new ClickProcessorManager();
        this.f9361a = context;
        DataOpt.a(h);
        this.d.a(this);
        this.g.a((StringSupport) h);
        this.c.a(this.g);
        this.c.a((StringSupport) h);
        this.c.c();
        if (!z) {
            this.i = new ContainerService();
            this.i.a(this);
        }
        this.j = ImageLoader.a(context);
        b = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void a(Context context) {
        this.f9361a = context;
    }

    public void b() {
        this.f9361a = null;
        this.p = null;
        EventData.a();
    }

    public void a(int i2) {
        if (i2 > -1) {
            h.c(i2);
        }
    }

    public void b(int i2) {
        if (i2 > -1) {
            h.d(i2);
        }
    }

    public UserData c() {
        return this.l;
    }

    public ClickProcessorManager d() {
        return this.o;
    }

    public void a(Activity activity) {
        this.p = activity;
    }

    public final EventManager e() {
        return this.k;
    }

    public final BeanManager f() {
        return this.e;
    }

    public final NativeViewManager g() {
        return this.f;
    }

    public final ImageLoader h() {
        return this.j;
    }

    public final void a(ImageLoader.IImageLoaderAdapter iImageLoaderAdapter) {
        this.j.a(iImageLoaderAdapter);
    }

    public final ExprEngine i() {
        return this.c;
    }

    @Deprecated
    public final Context j() {
        return this.f9361a;
    }

    public final Context k() {
        return this.f9361a.getApplicationContext();
    }

    public final Context l() {
        return this.p;
    }

    public final Context m() {
        return this.p != null ? this.p : this.f9361a;
    }

    public final NativeObjectManager n() {
        return this.g;
    }

    public final StringLoader o() {
        return h;
    }

    public final ViewManager p() {
        return this.d;
    }

    public final ContainerService q() {
        return this.i;
    }

    public final Activity r() {
        return this.p;
    }

    public View a(String str) {
        return this.i.a(str);
    }

    public void a(IContainer iContainer) {
        this.i.a(iContainer, false);
    }

    public ViewBase b(String str) {
        return this.d.c(str);
    }

    public void a(ViewBase viewBase) {
        this.d.a(viewBase);
    }

    public <S> void a(@NonNull Class<S> cls, @NonNull S s) {
        this.n.a(cls, s);
    }

    public <S> S a(@NonNull Class<S> cls) {
        return this.n.a(cls);
    }

    public void s() {
        this.f9361a = null;
        this.p = null;
        EventData.a();
        if (this.c != null) {
            this.c.b();
            this.c = null;
        }
        if (this.g != null) {
            this.g.a();
            this.g = null;
        }
        if (this.d != null) {
            this.d.b();
            this.d = null;
        }
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
    }
}
