package com.tmall.wireless.vaf.virtualview.view.slider;

import android.support.v7.widget.LinearSnapHelper;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.view.scroller.Scroller;

public class Slider extends Scroller {
    public Slider(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        new LinearSnapHelper().attachToRecyclerView(this.ah);
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new Slider(vafContext, viewCache);
        }
    }
}
