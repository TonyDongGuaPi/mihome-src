package com.tmall.wireless.vaf.framework.cm;

import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.container.Container;
import com.tmall.wireless.vaf.virtualview.core.IContainer;

public class NormalManager extends ContainerMrg {
    public IContainer a(VafContext vafContext) {
        if (this.f9364a.size() > 0) {
            return (IContainer) this.f9364a.remove(0);
        }
        return new Container(vafContext.m());
    }
}
