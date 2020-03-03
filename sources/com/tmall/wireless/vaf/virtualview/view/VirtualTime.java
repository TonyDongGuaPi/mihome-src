package com.tmall.wireless.vaf.virtualview.view;

import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

@Deprecated
public class VirtualTime extends ViewBase {
    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public void onComMeasure(int i, int i2) {
    }

    public VirtualTime(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VirtualTime(vafContext, viewCache);
        }
    }
}
