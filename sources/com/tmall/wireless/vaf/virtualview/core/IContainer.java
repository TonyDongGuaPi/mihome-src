package com.tmall.wireless.vaf.virtualview.core;

import android.view.View;

public interface IContainer {
    void attachViews();

    void destroy();

    View getHolderView();

    int getType();

    ViewBase getVirtualView();

    void setVirtualView(ViewBase viewBase);
}
