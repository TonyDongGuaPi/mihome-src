package com.mipay.core.internal;

import android.text.TextUtils;
import android.util.Log;
import com.mipay.core.runtime.BundleActivator;
import com.mipay.core.runtime.BundleContext;
import com.mipay.core.runtime.Extension;
import com.mipay.core.runtime.ExtensionPoint;
import com.mipay.core.runtime.OSGiBundle;

public class BundleContextImpl extends BundleContext {
    private static final String TAG = "BundleContextImpl";
    private BundleActivator mActivator;
    private final OSGiBundleImpl mBundle;
    private final BundleManager mBundleManager;

    public boolean installBundle() {
        return false;
    }

    public BundleContextImpl(OSGiBundleImpl oSGiBundleImpl, BundleManager bundleManager) {
        this.mBundle = oSGiBundleImpl;
        this.mBundleManager = bundleManager;
    }

    public OSGiBundle getBundle() {
        return this.mBundle;
    }

    /* access modifiers changed from: protected */
    public boolean start() {
        this.mActivator = loadBundleActivator();
        if (this.mActivator != null) {
            return this.mActivator.start(this);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean stop() {
        if (this.mActivator != null) {
            return this.mActivator.stop(this);
        }
        return true;
    }

    private BundleActivator loadBundleActivator() {
        String activator = this.mBundle.getActivator();
        if (TextUtils.isEmpty(activator)) {
            return null;
        }
        try {
            return (BundleActivator) Class.forName(activator).newInstance();
        } catch (Exception unused) {
            Log.d(TAG, "cannot initialize bundle activator");
            return null;
        }
    }

    public Extension getExtension(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.lastIndexOf(46) == -1) {
            str = this.mBundle.getSymbolicName() + '.' + str;
        }
        return this.mBundleManager.getExtensionRegistry().getExtension(str);
    }

    public ExtensionPoint getExtensionPoint(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.lastIndexOf(46) == -1) {
            str = this.mBundle.getSymbolicName() + '.' + str;
        }
        return this.mBundleManager.getExtensionRegistry().getExtensionPoint(str);
    }
}
