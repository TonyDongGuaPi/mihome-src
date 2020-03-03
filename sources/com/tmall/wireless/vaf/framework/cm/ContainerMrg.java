package com.tmall.wireless.vaf.framework.cm;

import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import java.util.LinkedList;
import java.util.List;

public abstract class ContainerMrg {

    /* renamed from: a  reason: collision with root package name */
    protected List<IContainer> f9364a = new LinkedList();

    public abstract IContainer a(VafContext vafContext);

    public void a(IContainer iContainer) {
        this.f9364a.add(iContainer);
    }

    public void a() {
        for (IContainer destroy : this.f9364a) {
            destroy.destroy();
        }
        this.f9364a.clear();
    }
}
