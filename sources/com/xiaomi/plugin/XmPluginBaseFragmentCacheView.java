package com.xiaomi.plugin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class XmPluginBaseFragmentCacheView extends XmPluginBaseFragment {
    boolean mIsViewCreated = false;
    protected View mRootView;

    /* access modifiers changed from: protected */
    public abstract void setupView(View view);

    public XmPluginBaseFragmentCacheView(XmPluginBaseFragment xmPluginBaseFragment, Intent intent) {
        super(xmPluginBaseFragment, intent);
    }

    public XmPluginBaseFragmentCacheView(Context context, XmPluginPackage xmPluginPackage, Intent intent) {
        super(context, xmPluginPackage, intent);
    }

    public XmPluginBaseFragmentCacheView() {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = onCreateView(this.mLayoutInflater, bundle);
        }
        return this.mRootView;
    }

    public final void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (!this.mIsViewCreated) {
            this.mIsViewCreated = true;
            setupView(view);
        }
    }
}
