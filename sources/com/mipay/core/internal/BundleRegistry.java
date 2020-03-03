package com.mipay.core.internal;

import java.util.HashMap;
import java.util.Map;

public class BundleRegistry {
    private final Map<String, OSGiBundleImpl> mRegistry = new HashMap();

    public void addBundle(OSGiBundleImpl oSGiBundleImpl) {
        this.mRegistry.put(oSGiBundleImpl.getSymbolicName(), oSGiBundleImpl);
    }

    public void removeBundle(String str) {
        this.mRegistry.remove(str);
    }

    public boolean hasBundle(String str) {
        return this.mRegistry.containsKey(str);
    }
}
