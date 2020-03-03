package com.tmall.wireless.vaf.framework.cm;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.framework.ViewManager;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import java.util.ArrayList;
import java.util.List;

public class ContainerService {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9365a = 20;
    public static final int b = 0;
    @Deprecated
    public static final int c = 1;
    private static final String e = "ContainerService_TMTEST";
    protected ComContainerTypeMap d;
    private VafContext f;
    private ViewManager g;
    private List<ContainerMrg> h = new ArrayList(20);

    public ContainerService() {
        a((ContainerMrg) new NormalManager(), 0);
        a((ContainerMrg) new NormalManager(), 1);
    }

    public void a(ContainerMrg containerMrg, int i) {
        if (containerMrg == null || i < 0 || i >= 20) {
            Log.e(e, "param invalidate containerID:" + i);
            return;
        }
        this.h.add(i, containerMrg);
    }

    public void a(VafContext vafContext) {
        this.f = vafContext;
        this.g = this.f.p();
        this.d = this.f.a();
    }

    public ComContainerTypeMap a() {
        return this.d;
    }

    public void b() {
        for (ContainerMrg next : this.h) {
            if (next != null) {
                next.a();
            }
        }
        this.g = null;
        this.f = null;
    }

    public void a(IContainer iContainer) {
        a(iContainer, true);
    }

    public void a(IContainer iContainer, boolean z) {
        if (iContainer != null) {
            if (z) {
                ViewBase virtualView = iContainer.getVirtualView();
                if (virtualView != null) {
                    this.g.a(virtualView);
                    if (iContainer instanceof ViewGroup) {
                        ((ViewGroup) iContainer).removeAllViews();
                    }
                } else {
                    Log.e(e, "recycle viewbase is null");
                }
            }
            int type = iContainer.getType();
            if (type > -1) {
                ContainerMrg containerMrg = this.h.get(type);
                if (containerMrg != null) {
                    containerMrg.a(iContainer);
                    return;
                }
                Log.e(e, "recycle container type is invalidate:" + iContainer.getType());
            }
        }
    }

    public View a(String str) {
        return a(str, true);
    }

    public View a(String str, int i) {
        return a(str, i, true);
    }

    public View a(String str, boolean z) {
        int a2 = this.d.a(str);
        if (a2 <= -1) {
            a2 = 0;
        }
        return a(str, a2, z);
    }

    public View a(String str, int i, boolean z) {
        IContainer iContainer;
        ViewBase c2 = this.g.c(str);
        if (c2 == null) {
            c2 = this.g.c();
            c2.b(str);
        }
        if (c2.m()) {
            iContainer = (IContainer) c2.g_();
        } else {
            ContainerMrg containerMrg = this.h.get(i);
            if (containerMrg != null) {
                iContainer = containerMrg.a(this.f);
            } else {
                Log.e(e, "getContainer type invalidate:" + i);
                iContainer = null;
            }
        }
        if (iContainer != null) {
            iContainer.setVirtualView(c2);
            if (z) {
                Layout.Params ae = c2.ae();
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ae.f9382a, ae.b);
                marginLayoutParams.leftMargin = ae.d;
                marginLayoutParams.topMargin = ae.h;
                marginLayoutParams.rightMargin = ae.f;
                marginLayoutParams.bottomMargin = ae.j;
                ((View) iContainer).setLayoutParams(marginLayoutParams);
            }
            iContainer.attachViews();
        }
        return (View) iContainer;
    }
}
