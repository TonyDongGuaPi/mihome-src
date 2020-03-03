package com.xiaomi.payment;

import android.util.Log;
import com.mibi.common.data.Client;
import com.mipay.core.runtime.BundleActivator;
import com.mipay.core.runtime.BundleContext;

public class Activator extends BundleActivator {
    private static final String TAG = "Activator";

    public boolean start(BundleContext bundleContext) {
        Log.d(TAG, "start: " + bundleContext);
        super.start(bundleContext);
        Client.a(getAppContext());
        return true;
    }
}
