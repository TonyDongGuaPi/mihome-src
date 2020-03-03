package com.alipay.mobile.security.bio.service;

import com.alipay.mobile.security.bio.utils.BioLog;

public abstract class BioService {
    /* access modifiers changed from: protected */
    public BioServiceManager mBioServiceManager;

    public void onCreate(BioServiceManager bioServiceManager) {
    }

    public void onDestroy() {
    }

    public final void create(BioServiceManager bioServiceManager) {
        this.mBioServiceManager = bioServiceManager;
        BioLog.i(getClass().getName() + ".onCreate() start.");
        onCreate(bioServiceManager);
        BioLog.i(getClass().getName() + ".onCreate() end.");
    }

    public final void destroy() {
        BioLog.i(getClass().getName() + ".onDestroy() start.");
        onDestroy();
        this.mBioServiceManager = null;
        BioLog.i(getClass().getName() + ".onDestroy() end.");
    }
}
