package com.mipay.core.runtime;

public interface OSGiBundle {

    public enum STATE {
        UNINSTALLED,
        INSTALLED,
        RESOLVED,
        STARTING,
        STOPPING,
        ACTIVE
    }

    BundleContext getBundleContext();

    String getName();

    STATE getState();

    String getSymbolicName();
}
