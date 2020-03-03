package com.mipay.core.internal;

import com.mipay.core.runtime.BundleContext;
import com.mipay.core.runtime.OSGiBundle;

public class OSGiBundleImpl implements OSGiBundle {
    private String mActivator;
    private BundleContextImpl mContext;
    private long mId;
    private final BundleManager mManager;
    private String mName;
    private final String mPath;
    private String mRequiredBundle;
    private volatile OSGiBundle.STATE mState;
    private String mSymbolicName;

    OSGiBundleImpl(BundleManager bundleManager, String str) {
        this.mManager = bundleManager;
        this.mPath = str;
    }

    public void setState(OSGiBundle.STATE state) {
        this.mState = state;
    }

    public OSGiBundle.STATE getState() {
        return this.mState;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public long getId() {
        return this.mId;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public void setSymbolicName(String str) {
        this.mSymbolicName = str;
    }

    public String getSymbolicName() {
        return this.mSymbolicName;
    }

    public void setActivator(String str) {
        this.mActivator = str;
    }

    /* access modifiers changed from: package-private */
    public String getActivator() {
        return this.mActivator;
    }

    public void setRequiredBundle(String str) {
        this.mRequiredBundle = str;
    }

    /* access modifiers changed from: package-private */
    public String getRequiredBundle() {
        return this.mRequiredBundle;
    }

    /* access modifiers changed from: package-private */
    public String getPath() {
        return this.mPath;
    }

    public boolean start() {
        if (getState() != OSGiBundle.STATE.RESOLVED) {
            return false;
        }
        setState(OSGiBundle.STATE.STARTING);
        boolean start = ((BundleContextImpl) getBundleContext()).start();
        setState(start ? OSGiBundle.STATE.ACTIVE : OSGiBundle.STATE.RESOLVED);
        return start;
    }

    public boolean stop() {
        if (getState() != OSGiBundle.STATE.ACTIVE) {
            return false;
        }
        setState(OSGiBundle.STATE.STOPPING);
        boolean stop = ((BundleContextImpl) getBundleContext()).stop();
        setState(stop ? OSGiBundle.STATE.RESOLVED : OSGiBundle.STATE.ACTIVE);
        return stop;
    }

    public BundleContext getBundleContext() {
        BundleContextImpl bundleContextImpl;
        synchronized (this) {
            if (this.mContext == null) {
                this.mContext = new BundleContextImpl(this, this.mManager);
            }
            bundleContextImpl = this.mContext;
        }
        return bundleContextImpl;
    }

    public String toString() {
        return "Bundle: " + this.mName;
    }
}
