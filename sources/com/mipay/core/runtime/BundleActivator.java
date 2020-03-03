package com.mipay.core.runtime;

import android.content.Context;
import com.mipay.core.internal.BundleManager;

public abstract class BundleActivator {
    private static Context sApplicationContext;
    private static BundleContext sBundleContext;

    public boolean stop(BundleContext bundleContext) {
        return true;
    }

    public boolean start(BundleContext bundleContext) {
        sBundleContext = bundleContext;
        sApplicationContext = BundleManager.get().getContext();
        return true;
    }

    public static BundleContext getBundleContext() {
        return sBundleContext;
    }

    public static Context getAppContext() {
        return sApplicationContext;
    }
}
