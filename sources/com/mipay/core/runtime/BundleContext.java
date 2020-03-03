package com.mipay.core.runtime;

public abstract class BundleContext {
    public abstract OSGiBundle getBundle();

    public abstract Extension getExtension(String str);

    public abstract ExtensionPoint getExtensionPoint(String str);

    public abstract boolean installBundle();
}
